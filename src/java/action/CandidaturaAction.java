package action;

import com.opensymphony.xwork2.ActionSupport;
import entity.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.struts2.interceptor.SessionAware;
import repositorio.*;

public class CandidaturaAction extends ActionSupport implements SessionAware {

private Map<String, Object> session;
private String mensagemCandidato;
private String candidatarIdVaga;
private String listaPorPf;
private String listaPorVaga;
private PessoaRepositorio prep = new PessoaRepositorio();
private ArrayList<Candidatura> cands = null;

@Override
public String execute() throws Exception {

    int tipo = 0;
    int id = 0;

    if (getSession().get("tipo") != null) {
        tipo = (Integer) getSession().get("tipo"); //tipo do usuario
    }
    if (getSession().get("id") != null) {
        id = (Integer) getSession().get("id"); //id do usuario
    }

    if (candidatarIdVaga != null) {
        setarCamposEmUTF8(mensagemCandidato);
        //candRep.criarCandidatura(id, Integer.parseInt(candidatarIdVaga), mensagemCandidato);
        PessoaFisica pf = prep.lerPessoaFisica(id);
        pf.candidatarParaVaga(Integer.parseInt(candidatarIdVaga),mensagemCandidato);
        prep.atualizarPessoaFisica(pf);
        addActionMessage("Candidatura efetuada com sucesso.");
        return "sucesso";
    } else if (listaPorPf != null) {
        //cands = new ArrayList<Candidatura>();
        //cands = candRep.listarCandidaturasPorPf(id);
        PessoaFisica pf = prep.lerPessoaFisica(id);
        cands = pf.exibirCandidaturas();
        if (cands.size() == 0) {
            cands = null;
        }
        return "listaPf";
    } else if (listaPorVaga != null) {
        PessoaJuridica pj = prep.lerPessoaJuridica(id);
        cands = pj.listarCandidaturasParaVaga(Integer.parseInt(listaPorVaga));
        //cands = new ArrayList<Candidatura>();
        //cands = candRep.listarCandidaturasPorIdVaga(Integer.parseInt(listaPorVaga));
        if (cands.size() == 0) {
            cands = null;
        }
        return "listaVaga";

    } else {
        return NONE;
    }

}

public Map<String, Object> getSession() {
    return session;
}

/**
 * @param session the session to set
 */
public void setSession(Map<String, Object> session) {
    this.session = session;
}

/**
 * @return the candidatar
 */
public String getCandidatarIdVaga() {
    return candidatarIdVaga;
}

/**
 * @param candidatar the candidatar to set
 */
public void setCandidatarIdVaga(String candidatarIdVaga) {
    this.candidatarIdVaga = candidatarIdVaga;
}

/**
 * @return the cands
 */
public ArrayList<Candidatura> getCands() {
    return cands;
}

/**
 * @param cands the cands to set
 */
public void setCands(ArrayList<Candidatura> cands) {
    this.cands = cands;
}

/**
 * @return the listaPorVaga
 */
public String getListaPorVaga() {
    return listaPorVaga;
}

/**
 * @param listaPorVaga the listaPorVaga to set
 */
public void setListaPorVaga(String listaPorVaga) {
    this.listaPorVaga = listaPorVaga;
}

/**
 * @return the listaPorPf
 */
public String getListaPorPf() {
    return listaPorPf;
}

/**
 * @param listaPorPf the listaPorPf to set
 */
public void setListaPorPf(String listaPorPf) {
    this.listaPorPf = listaPorPf;
}

/**
 * @return the mensagemCandidato
 */
public String getMensagemCandidato() {
    return mensagemCandidato;
}

/**
 * @param mensagemCandidato the mensagemCandidato to set
 */
public void setMensagemCandidato(String mensagemCandidato) {
    this.mensagemCandidato = mensagemCandidato;
}

private void setarCamposEmUTF8(String msg) {
    try {
        this.mensagemCandidato = new String(msg.getBytes(), "UTF8");
    } catch (Exception ex) {
        Logger.getLogger(CandidaturaAction.class.getName()).log(Level.SEVERE, null, ex);
    }
}
}
