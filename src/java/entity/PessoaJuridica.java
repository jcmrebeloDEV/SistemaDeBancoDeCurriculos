package entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import mecanismoDeBusca.*;

public class PessoaJuridica extends Pessoa {

    private Integer id;
    private String cnpj;
    private String siteCorporativo;
    private Collection vagas;

    public PessoaJuridica() {
    }

    public PessoaJuridica(Pessoa p) {

        this.setCep(p.getCep());
        this.setCidade(p.getCidade());
        this.setEmail(p.getEmail());
        this.setEndereco(p.getEndereco());
        this.setNome(p.getNome());
        this.setTelefone(p.getTelefone());
        this.setUf(p.getUf());
    }

    public Vaga lerVaga(Integer idVaga) {
        Vaga vg = null;
        for (Iterator<Vaga> i = getVagas().iterator(); i.hasNext();) {
            Vaga vtemp = i.next();
            if (vtemp.getId().intValue() == idVaga.intValue()) {
                vg = vtemp;
                vg.setPessoaJuridica(this);
            }
        }
        return vg;
    }

    public ArrayList<Vaga> listarVagas() {
        ArrayList<Vaga> vgs = new ArrayList<Vaga>();
        vgs.addAll(vagas);
        return vgs;
    }

    public static ArrayList<ResultInfo> buscarCurriculos(String userQuery, int pg, int maxResultsPg) {
        Engine eng = new Engine();
        return eng.fndCurriculos(userQuery, pg, maxResultsPg);
    }

  
    public ArrayList<Candidatura> listarCandidaturasParaVaga(Integer idVaga) {
        //PessoaRepositorio pess = new PessoaRepositorio();
        //return pess.listarCandidaturasPorIdVaga(idVaga);
        ArrayList<Candidatura> cands = new ArrayList<Candidatura>();
        cands.addAll(this.lerVaga(idVaga).getCandidaturas());
        return cands;

    }

    /**
     * @return the cnpj
     */
    public String getCnpj() {
        return cnpj;
    }

    /**
     * @param cnpj the cnpj to set
     */
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the vagas
     */
    public Collection getVagas() {
        return vagas;
    }

    /**
     * @param vagas the vagas to set
     */
    public void setVagas(Collection vagas) {
        this.vagas = vagas;
    }

    /**
     * @return the siteCorporativo
     */
    public String getSiteCorporativo() {
        return siteCorporativo;
    }

    /**
     * @param siteCorporativo the siteCorporativo to set
     */
    public void setSiteCorporativo(String siteCorporativo) {
        this.siteCorporativo = siteCorporativo;
    }
}
 
