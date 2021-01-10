package action;

import com.opensymphony.xwork2.ActionSupport;
import entity.*;
import java.util.Calendar;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.struts2.interceptor.SessionAware;
import repositorio.*;

public class ManterCurriculoAction extends ActionSupport implements SessionAware {

private Map session;
private Curriculo c;
private String update; //id para atualizar
private String edit;   //id para editar
private String view;   //id para ler
private PessoaRepositorio prep = new PessoaRepositorio();

@Override
public String execute() throws Exception {

    int id = 0;

    if (getSession().get("id") != null) {
        id = (Integer) getSession().get("id"); //id do usuario
    }


    if (view != null) {

        //c = curRep.lerCurriculo(Integer.parseInt(view));
        PessoaFisica pf = prep.lerPessoaFisica(Integer.parseInt(view));
        c = pf.exibirCurriculo();
        return "formView";

    } else if (edit != null) {

        //c = curRep.lerCurriculo(id);
        PessoaFisica pf = prep.lerPessoaFisica(id);
        c = pf.exibirCurriculo();
        return "formEdit";

    } else if (update != null) {
        PessoaFisica pf = prep.lerPessoaFisica(id);
        c.setId(id);
        Calendar now = Calendar.getInstance();
        c.setDataUltimaAlteracao(now.getTime());
        this.setarCamposEmUTF8(c);
        c.setPessoaFisica(pf);
        pf.setCurriculo(c);
        prep.atualizarPessoaFisica(pf);
        //curRep.atualizarCurriculo(c);
        addActionMessage("Currículo atualizado com sucesso!");
        return "mensagem";

    }

    return "formEdit";
}

private void setarCamposEmUTF8(Curriculo c) {
    try {
        c.setAreaProfissional(new String(c.getAreaProfissional().getBytes(), "UTF8"));
        c.setDescConhecimentos(new String(c.getDescConhecimentos().getBytes(), "UTF8"));
        c.setDescExperiencia(new String(c.getDescExperiencia().getBytes(), "UTF8"));
        c.setDescFormacaoAcademica(new String(c.getDescFormacaoAcademica().getBytes(), "UTF8"));
        c.setDescIdiomas(new String(c.getDescIdiomas().getBytes(), "UTF8"));
        c.setEscolaridade(new String(c.getEscolaridade().getBytes(), "UTF8"));
        c.setObservacoes(new String(c.getObservacoes().getBytes(), "UTF8"));

    } catch (Exception ex) {
        Logger.getLogger(ManterCurriculoAction.class.getName()).log(Level.SEVERE, null, ex);
    }
}

/**
 * @return the c
 */
public Curriculo getC() {
    return c;
}

/**
 * @param c the c to set
 */
public void setC(Curriculo c) {
    this.c = c;
}

/**
 * @return the update
 */
public String getUpdate() {
    return update;
}

/**
 * @param update the update to set
 */
public void setUpdate(String update) {
    this.update = update;
}

/**
 * @return the edit
 */
public String getEdit() {
    return edit;
}

/**
 * @param edit the edit to set
 */
public void setEdit(String edit) {
    this.edit = edit;
}

/**
 * @return the view
 */
public String getView() {
    return view;
}

/**
 * @param view the view to set
 */
public void setView(String view) {
    this.view = view;
}

/**
 * @return the session
 */
public Map<String, Object> getSession() {
    return session;
}

/**
 * @param session the session to set
 */
public void setSession(Map<String, Object> session) {
    this.session = session;
}
}
