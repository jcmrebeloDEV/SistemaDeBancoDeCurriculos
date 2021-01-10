package entity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import mecanismoDeBusca.*;
import repositorio.PessoaRepositorio;

public class PessoaFisica extends Pessoa {

    private Integer id;
    private String cpf;
    private char sexo;
    private Date dataDeNascimento;
    private Curriculo curriculo;
    private Collection candidaturas;

    public PessoaFisica() {

    }

    public PessoaFisica(Pessoa p) {

        this.setCep(p.getCep());
        this.setCidade(p.getCidade());
        this.setEmail(p.getEmail());
        this.setEndereco(p.getEndereco());
        this.setNome(p.getNome());
        this.setTelefone(p.getTelefone());
        this.setUf(p.getUf());

    }

    public void candidatarParaVaga(Integer idVaga, String msg) {

        //PessoaRepositorio pess = new PessoaRepositorio();
        //pess.efetuarCandidatura(this.getId(), idVaga, msg);
        Calendar now = Calendar.getInstance();
        Vaga v = new Vaga();
        v.setId(idVaga);
        Candidatura cd = new Candidatura();
        cd.setMensagem(msg);
        cd.setDataCandidatura(now.getTime());
        cd.setPessoaFisica(this);
        cd.setVaga(v);
        this.getCandidaturas().add(cd);


    }

    public static ArrayList<ResultInfo> buscarVagas(String userQuery, int pg, int maxResultsPg) {
        Engine eng = new Engine();
        return eng.fndVagas(userQuery, pg, maxResultsPg);
    }

    public ArrayList<Candidatura> exibirCandidaturas() {
        ArrayList<Candidatura> cands = new ArrayList<Candidatura>();
        cands.addAll(candidaturas);
        return cands;
    }

    public Curriculo exibirCurriculo() {
        getCurriculo().setPessoaFisica(this);
        return curriculo;
    }

    public int exibirIdade() {
        Calendar now = Calendar.getInstance();

        Calendar ani = new GregorianCalendar();
        ani.setTime(this.getDataDeNascimento());

        int mesAtual = now.get(Calendar.MONTH);
        int diaAtual = now.get(Calendar.DAY_OF_MONTH);
        int anoAtual = now.get(Calendar.YEAR);

        int mesAni = ani.get(Calendar.MONTH);
        int diaAni = ani.get(Calendar.DAY_OF_MONTH);
        int anoAni = ani.get(Calendar.YEAR);

        int difAnos = anoAtual - anoAni;

        if (mesAtual - mesAni < 0) {
            difAnos--;
        } else if (mesAtual - mesAni == 0) {

            if (diaAtual - diaAni < 0) {
                difAnos--;
            }
        }
        return difAnos;
    }

    /**
     * @return the cpf
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * @param cpf the cpf to set
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * @return the curriculo
     */
    public Curriculo getCurriculo() {
        return curriculo;
    }

    /**
     * @param curriculo the curriculo to set
     */
    public void setCurriculo(Curriculo curriculo) {
        this.curriculo = curriculo;
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
     * @return the dataDeNascimento
     */
    public Date getDataDeNascimento() {
        return dataDeNascimento;
    }

    /**
     * @param dataDeNascimento the dataDeNascimento to set
     */
    public void setDataDeNascimento(Date dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    /**
     * @return the sexo
     */
    public char getSexo() {
        return sexo;
    }

    /**
     * @param sexo the sexo to set
     */
    public void setSexo(char sexo) {
        this.sexo = sexo;
    }
}
 
