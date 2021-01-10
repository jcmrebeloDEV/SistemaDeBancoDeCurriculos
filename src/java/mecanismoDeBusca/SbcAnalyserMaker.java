package mecanismoDeBusca;

import java.util.HashSet;
import java.util.Set;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;

public class SbcAnalyserMaker {

private Analyzer analyzer = null;
private static Set<String> stopwords = new HashSet<String>();
private static final String[] listaStopWords = {
    "a",
    "ainda",
    "alem",
    "ambas",
    "ambos",
    "antes",
    "ao",
    "aonde",
    "aos",
    "apos",
    "aquele",
    "aqueles",
    "as",
    "assim",
    "com",
    "como",
    "contra",
    "contudo",
    "cuja",
    "cujas",
    "cujo",
    "cujos",
    "da",
    "das",
    "de",
    "dela",
    "dele",
    "deles",
    "demais",
    "depois",
    "desde",
    "desta",
    "deste",
    "dispoe",
    "dispoem",
    "diversa",
    "diversas",
    "diversos",
    "do",
    "dos",
    "durante",
    "e",
    "ela",
    "elas",
    "ele",
    "eles",
    "em",
    "entao",
    "entre",
    "essa",
    "essas",
    "esse",
    "esses",
    "esta",
    "estas",
    "este",
    "estes",
    "ha",
    "isso",
    "isto",
    "logo",
    "mais",
    "mas",
    "mediante",
    "menos",
    "mesma",
    "mesmas",
    "mesmo",
    "mesmos",
    "na",
    "nas",
    "nao",
    "nas",
    "nem",
    "nesse",
    "neste",
    "nos",
    "o",
    "os",
    "ou",
    "pelas",
    "pelas",
    "pelo",
    "pelos",
    "perante",
    "pois",
    "por",
    "porque",
    "portanto",
    "proprio",
    "propios",
    "quais",
    "qual",
    "qualquer",
    "quando",
    "quanto",
    "que",
    "quem",
    "quer",
    "seja",
    "sem",
    "sendo",
    "seu",
    "seus",
    "sob",
    "sobre",
    "sua",
    "suas",
    "tal",
    "tambem",
    "teu",
    "teus",
    "toda",
    "todas",
    "todo",
    "todos",
    "tua",
    "tuas",
    "tudo",
    "um",
    "uma",
    "umas",
    "uns"};

public SbcAnalyserMaker(org.apache.lucene.util.Version versao) {
    for (String s : listaStopWords) {
        stopwords.add(s);
    }
    analyzer = new BrazilianAnalyzer(versao, stopwords);
}

/**
 * @return the analyzer
 */
public Analyzer getAnalyzer() {
    return analyzer;
}

/**
 * @param aAnalyzer the analyzer to set
 */
public void setAnalyzer(Analyzer aAnalyzer) {
    analyzer = aAnalyzer;
}

}
