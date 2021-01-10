package mecanismoDeBusca;

import entity.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Searcher;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.index.TermFreqVector;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.store.LockObtainFailedException;
import repositorio.PessoaRepositorio;
import utils.Registro;

public class Engine implements Runnable {

private static File indexCurriculo = null;
private static File indexVaga = null;
private static long intervaloDeIndexacao;
private static Analyzer analyzer = new SbcAnalyserMaker(Version.LUCENE_CURRENT).getAnalyzer();
private static final String[] camposCurriculo = {"id", "pid","nome", "UF", "areaProfissional", "descExperiencia", "descConhecimentos", "descIdiomas", "escolaridade", "descFormacaoAcademica", "observacoes"};
private static final String[] camposVaga = {"id", "pid","UF", "areaProfissional", "cargo", "descricao", "observacoes"};

public Engine() {

if ((indexCurriculo == null) || (indexVaga == null)) {
    Registro reg = new Registro("MecanismoDeBusca.properties");
    indexCurriculo = new File(reg.lerValor("indiceCurriculo"));
    indexVaga = new File(reg.lerValor("indiceVaga"));
    intervaloDeIndexacao = Long.valueOf(reg.lerValor("intervaloDeIndexacao"));
}

long now = System.currentTimeMillis();
long modfTimeCur = getLasModified(indexCurriculo);
long modfTimeVaga = getLasModified(indexVaga);

if (now - Math.min(modfTimeCur, modfTimeVaga) > intervaloDeIndexacao) {
//dispara a indexacao
    Thread crawler = new Thread(this);
    crawler.setPriority(Thread.MIN_PRIORITY);
    crawler.start();
}


}

private long getLasModified(File indexDir) {
long result = 0;
try {
    result = IndexReader.lastModified(FSDirectory.open(indexDir));
} catch (CorruptIndexException ex) {
    Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
} catch (IOException ex) {
    Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
}
return result;
}

private String filtrar(String s) {
return (s != null ? s : "").trim();
}

private String[] getHtmlHighlight(Analyzer analyzer, Query query,
    String fieldName, String fieldContents, int fragmentNumber, int fragmentSize) throws Exception {

TokenStream stream = TokenSources.getTokenStream(fieldName, fieldContents, analyzer);
QueryScorer scorer = new QueryScorer(query, fieldName);
Fragmenter fragmenter = new SimpleSpanFragmenter(scorer, fragmentSize);

Highlighter highlighter = new Highlighter(scorer);
highlighter.setTextFragmenter(fragmenter);
highlighter.setMaxDocCharsToAnalyze(Integer.MAX_VALUE);

String[] fragments = highlighter.getBestFragments(stream, fieldContents, fragmentNumber);

return fragments;
}

public ArrayList<ResultInfo> fndCurriculos(String userQuery, int pagina, int quantidade) {

String[] userQueryArray = new String[camposCurriculo.length];
Arrays.fill(userQueryArray, userQuery);
ArrayList<ResultInfo> resultados =  null;
IndexReader reader = null;
TopDocs hits = null;

try {
    reader = IndexReader.open(FSDirectory.open(indexCurriculo), true);
    Searcher searcher = new IndexSearcher(reader);
    Query query = MultiFieldQueryParser.parse(Version.LUCENE_CURRENT, userQueryArray, camposCurriculo, analyzer);

    hits = searcher.search(query, reader.numDocs());
    int totais = hits.totalHits;

    resultados =  new ArrayList<ResultInfo>();
//verificacao da range do numero da pagina enviada
    int numPgs = (totais % quantidade) == 0 ? (totais / quantidade) : ((totais / quantidade) + 1);
    if (pagina > numPgs) {
        pagina = 1;
    }

    int indiceInferior = (pagina - 1) * quantidade;
    int indiceSuperior = pagina * quantidade;

    for (int i = indiceInferior; i < Math.min(indiceSuperior, totais); i++) {

        Document doc = searcher.doc(hits.scoreDocs[i].doc);
        ResultInfo res = new ResultInfo();

        res.setTotais(totais);
        res.setId(Integer.parseInt(doc.get("id")));
        res.setPid(Integer.parseInt(doc.get("pid")));
        res.setNome(doc.get("nome"));

        String[] campos = {"descExperiencia", "descConhecimentos", "observacoes"};
        ArrayList<String> listaFinalHihglight = new ArrayList<String>();

        for (String campo : campos) {
            for (String s : getHtmlHighlight(analyzer, query, campo, doc.get(campo), 3, 30)) {
                listaFinalHihglight.add(s);
            }
        }

        res.setHighlight(listaFinalHihglight);
      

        ArrayList<String> listaFinalTermos = new ArrayList<String>();

        for (TermFreq termo : obterTermosMaisFrequentes(hits.scoreDocs[i].doc, indexCurriculo, camposCurriculo, 5)) {
            listaFinalTermos.add(termo.getTerm());
        }

        res.setTermos(listaFinalTermos);
      
        resultados.add(res);
    }

} catch (Exception ex) {
    Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
} finally {
    try {
        reader.close();
        hits = null;
        reader = null;
    } catch (CorruptIndexException ex) {
        Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
        Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
    }
}

return resultados;
}

public ArrayList<ResultInfo> fndVagas(String userQuery, int pagina, int quantidade) {

String[] userQueryArray = new String[camposVaga.length];
Arrays.fill(userQueryArray, userQuery);
ArrayList<ResultInfo> resultados = new ArrayList<ResultInfo>();
IndexReader reader = null;
TopDocs hits = null;

try {
    reader = IndexReader.open(FSDirectory.open(indexVaga), true);
    Searcher searcher = new IndexSearcher(reader);
    Query query = MultiFieldQueryParser.parse(Version.LUCENE_CURRENT, userQueryArray, camposVaga, analyzer);

    hits = searcher.search(query, reader.numDocs());
    int totais = hits.totalHits;

//verificacao da range do numero da pagina enviada
    int numPgs = (totais % quantidade) == 0 ? (totais / quantidade) : ((totais / quantidade) + 1);
    if (pagina > numPgs) {
        pagina = 1;
    }

    int indiceInferior = (pagina - 1) * quantidade;
    int indiceSuperior = pagina * quantidade;

    for (int i = indiceInferior; i < Math.min(indiceSuperior, totais); i++) {
        ResultInfo res = new ResultInfo();
        Document doc = searcher.doc(hits.scoreDocs[i].doc);

        res.setTotais(totais);
        res.setId(Integer.parseInt(doc.get("id")));
        res.setPid(Integer.parseInt(doc.get("pid")));
        res.setNome(doc.get("cargo"));

        String[] campos = {"descricao", "observacoes"};
        ArrayList<String> listaFinalHihglight = new ArrayList<String>();

        for (String campo : campos) {
            for (String s : getHtmlHighlight(analyzer, query, campo, doc.get(campo), 3, 30)) {
                listaFinalHihglight.add(s);
            }
        }
 
          res.setHighlight(listaFinalHihglight);
       
        ArrayList<String> listaFinalTermos = new ArrayList<String>();

        for (TermFreq termo : obterTermosMaisFrequentes(hits.scoreDocs[i].doc, indexVaga, camposVaga, 5)) {
            listaFinalTermos.add(termo.getTerm());
        }

          res.setTermos(listaFinalTermos);
    
        resultados.add(res);
    }

} catch (Exception ex) {
    Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
} finally {
    try {
        reader.close();
        hits = null;
        reader = null;
    } catch (CorruptIndexException ex) {
        Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
        Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
    }
}

return resultados;
}

private void indexarTudo() {

IndexWriter indexWriterCurriculo = null;
IndexWriter indexWriterVaga = null;
PessoaRepositorio prep = new PessoaRepositorio();
try {
    if (IndexWriter.isLocked(FSDirectory.open(indexCurriculo)) || IndexWriter.isLocked(FSDirectory.open(indexVaga))) {
        return;
    }
} catch (IOException ex) {
    Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
}

try {
    indexWriterCurriculo = new IndexWriter(FSDirectory.open(indexCurriculo), analyzer, true, IndexWriter.MaxFieldLength.LIMITED);

    for (Iterator<PessoaFisica> it  = prep.listarPessoasFisicas().iterator();it.hasNext();) {
         Document doc = new Document();
         PessoaFisica pf = it.next();
        doc.add(new Field("id", String.valueOf(pf.getCurriculo().getId()), Field.Store.YES, Field.Index.NO));
        doc.add(new Field("pid", String.valueOf(pf.getId()), Field.Store.YES, Field.Index.NO));
        doc.add(new Field("nome", filtrar(pf.getNome()), Field.Store.YES, Field.Index.NO));
        doc.add(new Field("UF", filtrar(pf.getUf()), Field.Store.NO, Field.Index.ANALYZED));
        doc.add(new Field("areaProfissional", filtrar(pf.getCurriculo().getAreaProfissional()), Field.Store.NO, Field.Index.ANALYZED));
        doc.add(new Field("descExperiencia", filtrar(pf.getCurriculo().getDescExperiencia()), Field.Store.YES, Field.Index.ANALYZED, Field.TermVector.YES));
        doc.add(new Field("descConhecimentos", filtrar(pf.getCurriculo().getDescConhecimentos()), Field.Store.YES, Field.Index.ANALYZED, Field.TermVector.YES));
        doc.add(new Field("descIdiomas", filtrar(pf.getCurriculo().getDescIdiomas()), Field.Store.NO, Field.Index.ANALYZED, Field.TermVector.YES));
        doc.add(new Field("escolaridade", filtrar(pf.getCurriculo().getEscolaridade()), Field.Store.NO, Field.Index.ANALYZED));
        doc.add(new Field("descFormacaoAcademica", filtrar(pf.getCurriculo().getDescFormacaoAcademica()), Field.Store.NO, Field.Index.ANALYZED, Field.TermVector.YES));
        doc.add(new Field("observacoes", filtrar(pf.getCurriculo().getObservacoes()), Field.Store.YES, Field.Index.ANALYZED, Field.TermVector.YES));
        indexWriterCurriculo.addDocument(doc);
    }

} catch (LockObtainFailedException ex) {
//um outro thread esta tentando escrever equanto este escreve...nao fazer nada
} catch (CorruptIndexException ex) {
    Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
} catch (IOException ex) {
    Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
} finally {

    try {
        indexWriterCurriculo.close();
    } catch (CorruptIndexException ex) {
        Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
        Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
    }
}


try {
    indexWriterVaga = new IndexWriter(FSDirectory.open(indexVaga), analyzer, true, IndexWriter.MaxFieldLength.LIMITED);

    for(Iterator<PessoaJuridica> itpj = prep.listarPessoasJuridicas().iterator(); itpj.hasNext();){
        PessoaJuridica pj = itpj.next();
    for (Iterator<Vaga> itv = pj.getVagas().iterator();itv.hasNext();) {
        Vaga v = itv.next();
        Document doc = new Document();
        doc.add(new Field("id", String.valueOf(v.getId()), Field.Store.YES, Field.Index.NO));
        doc.add(new Field("pid", String.valueOf(pj.getId()), Field.Store.YES, Field.Index.NO));
        doc.add(new Field("UF", filtrar(v.getUf()), Field.Store.NO, Field.Index.ANALYZED));
        doc.add(new Field("areaProfissional", filtrar(v.getAreaProfissional()), Field.Store.NO, Field.Index.ANALYZED));
        doc.add(new Field("cargo", filtrar(v.getCargo()), Field.Store.YES, Field.Index.ANALYZED, Field.TermVector.YES));
        doc.add(new Field("descricao", filtrar(v.getDescricao()), Field.Store.YES, Field.Index.ANALYZED, Field.TermVector.YES));
        doc.add(new Field("observacoes", filtrar(v.getObservacoes()), Field.Store.YES, Field.Index.ANALYZED, Field.TermVector.YES));
        indexWriterVaga.addDocument(doc);
    }
    }

} catch (LockObtainFailedException ex) {
//um outro thread esta tentando escrever equanto este escreve...nao fazer nada
} catch (CorruptIndexException ex) {
    Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
} catch (IOException ex) {
    Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
} finally {

    try {
        indexWriterVaga.close();
    } catch (CorruptIndexException ex) {
        Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
        Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
    }
}

}

private TermFreq[] obterTermosMaisFrequentes(int docNumber, File indexDir, String[] campos, int quantidadeMaxima) {

HashMap<String, Integer> masterTerms = new HashMap<String, Integer>();

try {
    IndexReader reader = IndexReader.open(FSDirectory.open(indexDir), true);

    for (String campo : campos) {

        TermFreqVector tfv = reader.getTermFreqVector(docNumber, campo);

        if (tfv != null) {

            int[] freqs = tfv.getTermFrequencies();
            String[] terms = tfv.getTerms();

            for (int i = 0; i < freqs.length; i++) {

                if (masterTerms.containsKey(terms[i])) {
                    Integer valor = masterTerms.get(terms[i]);
                    valor += freqs[i];
                    masterTerms.put(terms[i], valor);
                } else {
                    masterTerms.put(terms[i], freqs[i]);
                }
            }
        }
    }

} catch (IOException ex) {
    Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
}

Iterator i = masterTerms.keySet().iterator();
TermFreq[] frequencias = new TermFreq[masterTerms.size()];

int indice = 0;
while (i.hasNext()) {
    String key = (String) i.next();
    frequencias[indice] = (new TermFreq(key, masterTerms.get(key)));
    indice++;
}

masterTerms = null;

Arrays.sort(frequencias);

return Arrays.copyOf(frequencias, Math.min(quantidadeMaxima, frequencias.length));

}

public void run() {
long inicio = System.currentTimeMillis();
this.indexarTudo();
long fim = System.currentTimeMillis();
System.out.println("indexado em " + (fim - inicio) + " milissegundos");
}
}
