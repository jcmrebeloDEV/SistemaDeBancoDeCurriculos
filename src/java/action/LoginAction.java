package action;

import cadastro.Cadastro;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;
import entity.*;
import javax.naming.InitialContext;

public class LoginAction extends ActionSupport implements SessionAware {

    private String usrname;
    private String pswd;
    private String logout = null;
    private Map session;

    public String execute() throws Exception {

         InitialContext context = new InitialContext();
  System.out.println((String) context.lookup("java:comp/env/param1"));

        if (logout != null) {
            getSession().remove("nome");
            getSession().remove("id");
            getSession().remove("tipo");
            return INPUT;
        }

        Cadastro cad = new Cadastro();
        Usuario u = cad.validarLogin(usrname,pswd);

        if (u != null) {
            getSession().put("nome", u.getPessoa().getNome());
            getSession().put("id", u.getPessoa().getId());
            getSession().put("tipo", u.getTipo());
            return SUCCESS;

        } else {
            addActionError("Usuário ou senha inválidos");
            return INPUT;
        }

    }



    /**
     * @return the pswd
     */
    public String getPswd() {
        return pswd;
    }

    /**
     * @param pswd the pswd to set
     */
    public void setPswd(String pswd) {
        this.pswd = pswd;
    }

    /**
     * @return the session
     */
    public Map getSession() {
        return session;
    }

    /**
     * @param session the session to set
     */
    public void setSession(Map session) {
        this.session = session;
    }

    /**
     * @return the logout
     */
    public String getLogout() {
        return logout;
    }

    /**
     * @param logout the logout to set
     */
    public void setLogout(String logout) {
        this.logout = logout;
    }

    /**
     * @return the usrname
     */
    public String getUsrname() {
        return usrname;
    }

    /**
     * @param usrname the usrname to set
     */
    public void setUsrname(String usrname) {
        this.usrname = usrname;
    }
}
