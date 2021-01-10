package entity;

import java.util.Date;

public class Candidatura {

    private Integer id;
    private Date dataCandidatura;
    private String mensagem;
    private PessoaFisica pessoaFisica;
    private Vaga vaga;

    /**
     * @return the dataCandidatura
     */
    public Date getDataCandidatura() {
        return dataCandidatura;
    }

    /**
     * @param dataCandidatura the dataCandidatura to set
     */
    public void setDataCandidatura(Date dataCandidatura) {
        this.dataCandidatura = dataCandidatura;
    }

    /**
     * @return the mensagem
     */
    public String getMensagem() {
        return mensagem;
    }

    /**
     * @param mensagem the mensagem to set
     */
    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
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
     * @return the vaga
     */
    public Vaga getVaga() {
        return vaga;
    }

    /**
     * @param vaga the vaga to set
     */
    public void setVaga(Vaga vaga) {
        this.vaga = vaga;
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

  
}
 
