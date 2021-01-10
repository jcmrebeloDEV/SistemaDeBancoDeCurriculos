package action;

import com.opensymphony.xwork2.ActionSupport;
import entity.PessoaFisica;
import entity.PessoaJuridica;
import mecanismoDeBusca.*;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BuscaAction extends ActionSupport {

private int pg = 1;
private int maxResultsPg;
private Integer totalResults;
private String userQuery;
private int tipoDeBusca;
private String uf;
private String areaProfissional;
private String escolaridade;
private ArrayList<ResultInfo> resultados = new ArrayList<ResultInfo>();

private static byte buscarCurriculo = 0;
private static byte buscarVaga = 1;

@Override
public String execute() throws Exception {

    setarCamposEmUTF8(); 

if (tipoDeBusca == buscarCurriculo) {

    resultados = PessoaJuridica.buscarCurriculos(filtrarQuery(userQuery.trim()), pg, maxResultsPg);

    if (resultados == null) {
        addActionError("Query inválida.");
        return "busca";
    } else if (resultados.size() == 0) {
        addActionMessage("Sua busca não retornou nenhum resultado");
        return "busca";
    }

    totalResults = resultados.get(0).getTotais();
    
    return "busca";

} else if (tipoDeBusca == buscarVaga) {

   resultados = PessoaFisica.buscarVagas(filtrarQuery(userQuery.trim()), pg, maxResultsPg);

    if (resultados == null) {
        addActionError("Query inválida.");
        return "busca";
    } else if (resultados.size() == 0) {
        addActionMessage("Sua busca não retornou nenhum resultado");
        return "busca";
    }

    totalResults = resultados.get(0).getTotais();

    return "busca";
}

return NONE;
}

private String filtrarQuery(String query) {

String queryFiltrada = "";

if (query.isEmpty()) {
    queryFiltrada += " UF:[a TO z] ";
} else {
    queryFiltrada += query;
}

if (uf != null) {
    if (!uf.equals("*")) {
        queryFiltrada += " AND UF:\"" + uf + "\" ";
    }
}

if (areaProfissional != null) {
    if (!areaProfissional.equals("*")) {
        queryFiltrada += " AND areaProfissional:\"" + areaProfissional + "\" ";
    }
}

if (escolaridade != null) {
    if (!escolaridade.equals("*")) {
        queryFiltrada += " AND escolaridade:\"" + escolaridade + "\" ";
    }
}

return queryFiltrada;

}

private void setarCamposEmUTF8() {

try {
    userQuery = new String(userQuery.getBytes(), "UTF8");
    areaProfissional = new String(areaProfissional.getBytes(), "UTF8");
    if(escolaridade!=null) {escolaridade = new String(escolaridade.getBytes(), "UTF8");}

} catch (UnsupportedEncodingException ex) {
    Logger.getLogger(BuscaAction.class.getName()).log(Level.SEVERE, null, ex);
}

}

/**
* @return the pg
*/
public int getPg() {
return pg;
}

/**
* @param pg the pg to set
*/
public void setPg(int pg) {
this.pg = pg;
}

/**
* @return the maxResultsPg
*/
public int getMaxResultsPg() {
return maxResultsPg;
}

/**
* @param maxResultsPg the maxResultsPg to set
*/
public void setMaxResultsPg(int maxResultsPg) {
this.maxResultsPg = maxResultsPg;
}

/**
* @return the userQuery
*/
public String getUserQuery() {
return userQuery;
}

/**
* @param userQuery the userQuery to set
*/
public void setUserQuery(String userQuery) {
this.userQuery = userQuery;
}

/**
* @return the tipoDeBusca
*/
public int getTipoDeBusca() {
return tipoDeBusca;
}

/**
* @param tipoDeBusca the tipoDeBusca to set
*/
public void setTipoDeBusca(int tipoDeBusca) {
this.tipoDeBusca = tipoDeBusca;
}

/**
* @return the uf
*/
public String getUf() {
return uf;
}

/**
* @param uf the uf to set
*/
public void setUf(String uf) {
this.uf = uf;
}

/**
* @return the areaProfissional
*/
public String getAreaProfissional() {
return areaProfissional;
}

/**
* @param areaProfissional the areaProfissional to set
*/
public void setAreaProfissional(String areaProfissional) {
this.areaProfissional = areaProfissional;
}

/**
* @return the escolaridade
*/
public String getEscolaridade() {
return escolaridade;
}

/**
* @param escolaridade the escolaridade to set
*/
public void setEscolaridade(String escolaridade) {
this.escolaridade = escolaridade;
}

/**
* @return the resultados
*/
public ArrayList<ResultInfo> getResultados() {
return resultados;
}

/**
* @param resultados the resultados to set
*/
public void setResultados(ArrayList<ResultInfo> resultados) {
this.resultados = resultados;
}

/**
* @return the totalResults
*/
public Integer getTotalResults() {
return totalResults;
}

/**
* @param totalResults the totalResults to set
*/
public void setTotalResults(Integer totalResults) {
this.totalResults = totalResults;
}
}
