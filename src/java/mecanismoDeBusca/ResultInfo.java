package mecanismoDeBusca;

import java.util.ArrayList;

public class ResultInfo {

    private int id;
    private int pid;
    private String nome = null;
    private ArrayList<String> highlight = null;
    private ArrayList<String> termos = null;
    private int totais;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the highlight
     */
    public ArrayList<String> getHighlight() {
        return highlight;
    }

    /**
     * @param highlight the highlight to set
     */
    public void setHighlight(ArrayList<String> highlight) {
        this.highlight = highlight;
    }

    /**
     * @return the termos
     */
    public ArrayList<String> getTermos() {
        return termos;
    }

    /**
     * @param termos the termos to set
     */
    public void setTermos(ArrayList<String> termos) {
        this.termos = termos;
    }

    /**
     * @return the totais
     */
    public int getTotais() {
        return totais;
    }

    /**
     * @param totais the totais to set
     */
    public void setTotais(int totais) {
        this.totais = totais;
    }

    /**
     * @return the pid
     */
    public int getPid() {
        return pid;
    }

    /**
     * @param pid the pid to set
     */
    public void setPid(int pid) {
        this.pid = pid;
    }



}
