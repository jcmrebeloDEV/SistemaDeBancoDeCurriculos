package action;

import com.opensymphony.xwork2.ActionSupport;
import entity.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Map;
import repositorio.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import org.apache.struts2.interceptor.SessionAware;

public class ManterVagaAction extends ActionSupport implements SessionAware {

    private Map session;
    private boolean showBox = false;
    private String list;
    private String view;
    private Integer pid;
    private String delete; //id para deletar
    private String create;
    private String update; //id para atualizar
    private String edit;   //id para editar
    private Integer[] ids; //lista de ids enviada pelo formulario para deletar
    private Vaga vg = null;
    private ArrayList<Vaga> vgs = null;
    private PessoaRepositorio prep = new PessoaRepositorio();

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


        if (list != null && tipo == 3) {
            PessoaJuridica pj = prep.lerPessoaJuridica(id);
            vgs = pj.listarVagas();
            //vgs = vgRep.listarVagas(id);
            if (vgs.size() == 0) {
                vgs = null;
            }
            return "lista";

        } else if (delete != null && tipo == 3) {
            PessoaJuridica pj = prep.lerPessoaJuridica(id);
            Collection<Vaga> origem = pj.getVagas();

            for (Iterator<Vaga> i = origem.iterator(); i.hasNext();) {
                Vaga vTemp = i.next();

                for (int a = 0; a < ids.length; a++) {

                    if (vTemp.getId().intValue() == ids[a]) {
                        i.remove();//destino.add(vTemp);

                    }

                }


            }

            prep.atualizarPessoaJuridica(pj);
            //vgRep.excluirVaga(getIds());
            addActionMessage("Vaga(s) excluidas");
            return "mensagem";

        } else if (create != null && tipo == 3) {
            this.setarCamposEmUTF8(vg);
            PessoaJuridica pj = prep.lerPessoaJuridica(id);
            vg.setPessoaJuridica(pj);
            Calendar now = Calendar.getInstance();
            vg.setDataDePublicacao(now.getTime());
            pj.getVagas().add(vg);
            prep.atualizarPessoaJuridica(pj);
            //vgRep.criarVaga(vg, id);
            addActionMessage("Vaga cadastrada com sucesso!");
            return "mensagem";


        } else if (update != null && tipo == 3) {
            this.setarCamposEmUTF8(vg);
            PessoaJuridica pj = prep.lerPessoaJuridica(id);
            Collection<Vaga> vagas = pj.getVagas();
            for (Iterator<Vaga> i = vagas.iterator(); i.hasNext();) {
                Vaga vTemp = i.next();
                if (vTemp.getId() == Integer.parseInt(update)) {
                    vTemp.setAreaProfissional(vg.getAreaProfissional());
                    vTemp.setUf(vg.getUf());
                    vTemp.setCargo(vg.getCargo());
                    vTemp.setDescricao(vg.getDescricao());
                    vTemp.setObservacoes(vg.getObservacoes());
                }
            }
            pj.setVagas(vagas);
            prep.atualizarPessoaJuridica(pj);
            // vg.setId(Integer.parseInt(update));
            // vgRep.atualizarVaga(vg);
            addActionMessage("Vaga atualizada com sucesso!");
            return "mensagem";

        } else if (edit != null && tipo == 3) {

            PessoaJuridica pj = prep.lerPessoaJuridica(id);
            vg = pj.lerVaga(Integer.parseInt(edit));
            update = edit; //valor campo update = valor campo edit
            return "formEdit";


        } else if (view != null) {
            PessoaJuridica pj = null;
            if (pid != null) {
                pj = prep.lerPessoaJuridica(pid);
            } else {
                pj = prep.lerPessoaJuridica(id);
            }

            vg = pj.lerVaga(Integer.parseInt(view));
            return "formView";
        } else {
            return NONE;
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
     * @return the vg
     */
    public Vaga getVg() {
        return vg;
    }

    /**
     * @param vg the vg to set
     */
    public void setVg(Vaga vg) {
        this.vg = vg;
    }

    /**
     * @return the vgs
     */
    public ArrayList<Vaga> getVgs() {
        return vgs;
    }

    /**
     * @param vgs the vgs to set
     */
    public void setVgs(ArrayList<Vaga> vgs) {
        this.vgs = vgs;
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
        this.list = list;
    }

    private void setarCamposEmUTF8(Vaga vg) {
        try {

            vg.setAreaProfissional(new String(vg.getAreaProfissional().getBytes(), "UTF8"));
            vg.setCargo(new String(vg.getCargo().getBytes(), "UTF8"));
            vg.setDescricao(new String(vg.getDescricao().getBytes(), "UTF8"));
            vg.setObservacoes(new String(vg.getObservacoes().getBytes(), "UTF8"));

        } catch (Exception ex) {
            Logger.getLogger(ManterVagaAction.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * @return the showBox
     */
    public boolean isShowBox() {
        return showBox;
    }

    /**
     * @param showBox the showBox to set
     */
    public void setShowBox(boolean showBox) {
        this.showBox = showBox;
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
     * @return the pid
     */
    public Integer getPid() {
        return pid;
    }

    /**
     * @param pid the pid to set
     */
    public void setPid(Integer pid) {
        this.pid = pid;
    }
}
