package entity;

import java.util.Collection;
import java.util.Date;

public class Vaga {

        private Integer id;
        
        private String areaProfissional;

        private String uf;
 
	private String cargo;
	 
	private String descricao;
	 
	private Date dataDePublicacao;
	 
	private String observacoes;
	 
	private PessoaJuridica pessoaJuridica;
		 
	private Collection candidaturas;

    

    /**
     * @return the cargo
     */
    public String getCargo() {
        return cargo;
    }

    /**
     * @param cargo the cargo to set
     */
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the dataDePublicacao
     */
    public Date getDataDePublicacao() {
        return dataDePublicacao;
    }

    /**
     * @param dataDePublicacao the dataDePublicacao to set
     */
    public void setDataDePublicacao(Date dataDePublicacao) {
        this.dataDePublicacao = dataDePublicacao;
    }

    /**
     * @return the observacoes
     */
    public String getObservacoes() {
        return observacoes;
    }

    /**
     * @param observacoes the observacoes to set
     */
    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
   

    /**
     * @return the pessoaJuridica
     */
    public PessoaJuridica getPessoaJuridica() {
        return pessoaJuridica;
    }

    /**
     * @param pessoaJuridica the pessoaJuridica to set
     */
    public void setPessoaJuridica(PessoaJuridica pessoaJuridica) {
        this.pessoaJuridica = pessoaJuridica;
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
     * @return the candidaturas
     */
    public Collection getCandidaturas() {
        return candidaturas;
    }

    /**
     * @param candidaturas the candidaturas to set
     */
    public void setCandidaturas(Collection candidaturas) {
        this.candidaturas = candidaturas;
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

  
	 
}
 
