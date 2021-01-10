package action;

import cadastro.Cadastro;
import com.opensymphony.xwork2.ActionSupport;
import entity.*;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import org.apache.struts2.interceptor.SessionAware;

public class ManterUsuarioAction extends ActionSupport implements SessionAware {

private Map session;
private String list; //string de busca para listagem
private String delete; //id para deletar
private String create;
private Integer update; //id para atualizar
private Integer edit;   //id para editar
private boolean trocarSenha = false;
private Integer[] ids; //lista de ids enviada pelo formulario para deletar
private String novaSenha;
private String senhaConfirma;
private String dataDeNascimento;
private Usuario usr = null;
private PessoaJuridica pj = null;
private PessoaFisica pf = null;
private ArrayList<Usuario> usrs = null;
private Cadastro cad = new Cadastro();

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


if (list != null && tipo == 1) { //somente para adms

    usrs = cad.listarCadastro(list);
    if (usrs.size() == 0) {
        usrs = null;
        addActionMessage("Nenhum usuário localizado");
    }
    return "lista";

} else if (delete != null && tipo == 1) { //somente para adms

    cad.excluirCadastro(getIds());
    addActionMessage("Usuário(s) excluidos");
    return "mensagem";

} else if (create != null) { //todos

    if (usr.getTipo() == cad.pessoaFisica) {
        this.setarCamposEmUTF8(pf);

        if (!senhaConfirma.equals(novaSenha)) {
            addActionError("Senha e confirmação de senha não conferem.");
            return "formAddPf";
        }

        usr.setSenha(novaSenha);
        pf.setDataDeNascimento((java.util.Date) new SimpleDateFormat("dd-MM-yyyy").parse(dataDeNascimento));
        cad.criarCadastro(usr, pf);
        addActionMessage("Usuário cadastrado com sucesso!");
        return "sucesso";

    } else if (usr.getTipo() == cad.pessoaJuridica) {
        this.setarCamposEmUTF8(pj);

        if (!senhaConfirma.equals(novaSenha)) {
            addActionError("Senha e confirmação de senha não conferem.");
            return "formAddPj";
        }

        usr.setSenha(novaSenha);
        cad.criarCadastro(usr, pj);
        addActionMessage("Usuário cadastrado com sucesso!");
        return "sucesso";

    }

    return NONE;

} else if (update != null && (update.intValue() == id || tipo == 1)) { //somente para adms ou o usuario proprietario

    if (cad.obterTipoDoUsuario(update.intValue()) == cad.pessoaFisica) {
        this.setarCamposEmUTF8(pf);

        if (isTrocarSenha()) {
            if (!senhaConfirma.equals(novaSenha)) {
                addActionError("Senha e confirmação de senha não conferem.");
                return "formEditPf";
            }
            cad.atualizarSenha(novaSenha, update.intValue());
        }

        pf.setDataDeNascimento((java.util.Date) new SimpleDateFormat("dd-MM-yyyy").parse(dataDeNascimento));
        pf.setId(update.intValue());
        cad.atualizarCadastro(pf);
        addActionMessage("Perfil atualizado com sucesso!");
        return "mensagem";

    } else if (cad.obterTipoDoUsuario(update.intValue()) == cad.pessoaJuridica) {
        this.setarCamposEmUTF8(pj);

        if (isTrocarSenha()) {
            if (!senhaConfirma.equals(novaSenha)) {
                addActionError("Senha e confirmação de senha não conferem.");
                return "formEditPj";
            }
            cad.atualizarSenha(novaSenha, update.intValue());
        }

        pj.setId(update.intValue());
        cad.atualizarCadastro(pj);
        addActionMessage("Perfil atualizado com sucesso!");
        return "mensagem";

    }

    return NONE;

} else if (edit != null && (edit.intValue() == id || tipo == 1)) { //somente para adms ou o usuario proprietario

    usr = cad.lerUsuario(edit.intValue());
    setUpdate(getEdit()); //valor campo update = valor campo edit

    if (cad.obterTipoDoUsuario(edit.intValue()) == cad.pessoaFisica) {

        pf = cad.lerPessoaFisica(edit.intValue());
        return "formEditPf";

    } else if (cad.obterTipoDoUsuario(edit.intValue()) == cad.pessoaJuridica) {

        pj = cad.lerPessoaJuridica(edit.intValue());
        return "formEditPj";
    }

    return NONE;

} else {
    return NONE;
}

}

/**
* @return the list
*/
public String getList() {
return list;
}

/**
* @param list the list to set
*/
public void setList(String list) {
try {
    this.list = new String(list.getBytes(), "UTF8");
} catch (Exception e) {
    System.out.println(e.toString());
}
}

/**
* @return the delete
*/
public String getDelete() {
return delete;
}

/**
* @param delete the delete to set
*/
public void setDelete(String delete) {
this.delete = delete;
}

/**
* @return the create
*/
public String getCreate() {
return create;
}

/**
* @param create the create to set
*/
public void setCreate(String create) {
this.create = create;
}

/**
* @return the usr
*/
public Usuario getUsr() {
return usr;
}

/**
* @param usr the usr to set
*/
public void setUsr(Usuario usr) {
this.usr = usr;
}

/**
* @return the senhaConfirma
*/
public String getSenhaConfirma() {
return senhaConfirma;
}

/**
* @param senhaConfirma the senhaConfirma to set
*/
public void setSenhaConfirma(String senhaConfirma) {
this.senhaConfirma = senhaConfirma;
}

/**
* @return the usrs
*/
public ArrayList<Usuario> getUsrs() {
return usrs;
}

/**
* @param usrs the usrs to set
*/
public void setUsrs(ArrayList<Usuario> usrs) {
this.usrs = usrs;
}

/**
* @return the novaSenha
*/
public String getNovaSenha() {
return novaSenha;
}

/**
* @param novaSenha the novaSenha to set
*/
public void setNovaSenha(String novaSenha) {
this.novaSenha = novaSenha;
}

/**
* @return the dataDeNascimento
*/
public String getDataDeNascimento() {
return dataDeNascimento;
}

/**
* @param dataDeNascimento the dataDeNascimento to set
*/
public void setDataDeNascimento(String dataDeNascimento) {
this.dataDeNascimento = dataDeNascimento;
}

private void setarCamposEmUTF8(PessoaFisica pf) {

try {
    pf.setCidade(new String(pf.getCidade().getBytes(), "UTF8"));
    pf.setEndereco(new String(pf.getEndereco().getBytes(), "UTF8"));
    pf.setNome(new String(pf.getNome().getBytes(), "UTF8"));

} catch (UnsupportedEncodingException ex) {
    Logger.getLogger(ManterUsuarioAction.class.getName()).log(Level.SEVERE, null, ex);
}

}

private void setarCamposEmUTF8(PessoaJuridica pj) {

try {
    pj.setCidade(new String(pj.getCidade().getBytes(), "UTF8"));
    pj.setEndereco(new String(pj.getEndereco().getBytes(), "UTF8"));
    pj.setNome(new String(pj.getNome().getBytes(), "UTF8"));

} catch (UnsupportedEncodingException ex) {
    Logger.getLogger(ManterUsuarioAction.class.getName()).log(Level.SEVERE, null, ex);
}

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

/**
* @return the trocarSenha
*/
public boolean isTrocarSenha() {
return trocarSenha;
}

/**
* @param trocarSenha the trocarSenha to set
*/
public void setTrocarSenha(boolean trocarSenha) {
this.trocarSenha = trocarSenha;
}

/**
* @return the ids
*/
public Integer[] getIds() {
return ids;
}

/**
* @param ids the ids to set
*/
public void setIds(Integer[] ids) {
this.ids = ids;
}

/**
* @return the pj
*/
public PessoaJuridica getPj() {
return pj;
}

/**
* @param pj the pj to set
*/
public void setPj(PessoaJuridica pj) {
this.pj = pj;
}

/**
* @return the pf
*/
public PessoaFisica getPf() {
return pf;
}

/**
* @param pf the pf to set
*/
public void setPf(PessoaFisica pf) {
this.pf = pf;
}

/**
* @return the update
*/
public Integer getUpdate() {
return update;
}

/**
* @param update the update to set
*/
public void setUpdate(Integer update) {
this.update = update;
}

/**
* @return the edit
*/
public Integer getEdit() {
return edit;
}

/**
* @param edit the edit to set
*/
public void setEdit(Integer edit) {
this.edit = edit;
}
}
