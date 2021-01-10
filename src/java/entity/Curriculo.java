package entity;

import java.util.Date;

public class Curriculo {
 
        private Integer id;
 
        private String areaProfissional;

	private String descExperiencia;
	 
	private String descConhecimentos;
	 
	private String descIdiomas;
	 
	private String escolaridade;

	private Date dataUltimaAlteracao;

	private String descFormacaoAcademica;
	 	 
	private String observacoes;
		 
	private PessoaFisica pessoaFisica;
	

    /**
     * @return the descExperiencia
     */
    public String getDescExperiencia() {
        return descExperiencia;
    }

    /**
     * @param descExperiencia the descExperiencia to set
     */
    public void setDescExperiencia(String descExperiencia) {
        this.descExperiencia = descExperiencia;
    }

    /**
     * @return the descConhecimentos
     */
    public String getDescConhecimentos() {
        return descConhecimentos;
    }

    /**
     * @param descConhecimentos the descConhecimentos to set
     */
    public void setDescConhecimentos(String descConhecimentos) {
        this.descConhecimentos = descConhecimentos;
    }

    /**
     * @return the descIdiomas
     */
    public String getDescIdiomas() {
        return descIdiomas;
    }

    /**
     * @param descIdiomas the descIdiomas to set
     */
    public void setDescIdiomas(String descIdiomas) {
        this.descIdiomas = descIdiomas;
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
     * @return the descFormacaoAcademica
     */
    public String getDescFormacaoAcademica() {
        return descFormacaoAcademica;
    }

    /**
     * @param descFormacaoAcademica the descFormacaoAcademica to set
     */
    public void setDescFormacaoAcademica(String descFormacaoAcademica) {
        this.descFormacaoAcademica = descFormacaoAcademica;
    }

    /**
     * @return the dataUltimaAlteracao
     */
    public Date getDataUltimaAlteracao() {
        return dataUltimaAlteracao;
    }

    /**
     * @param dataUltimaAlteracao the dataUltimaAlteracao to set
     */
    public void setDataUltimaAlteracao(Date dataUltimaAlteracao) {
        this.dataUltimaAlteracao = dataUltimaAlteracao;
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
     * @return the pessoaFisica
     */
    public PessoaFisica getPessoaFisica() {
        return pessoaFisica;
    }

    /**
     * @param pessoaFisica the pessoaFisica to set
     */
    public void setPessoaFisica(PessoaFisica pessoaFisica) {
        this.pessoaFisica = pessoaFisica;
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
	 
}
 
