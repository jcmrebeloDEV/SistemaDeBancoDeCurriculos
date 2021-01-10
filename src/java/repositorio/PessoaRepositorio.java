package repositorio;

import dao.*;
import entity.*;
import java.util.ArrayList;

public class PessoaRepositorio {
    
    private PessoaDao pess = new PessoaDao();

    public Integer criarPessoa(PessoaFisica pf) {

        return pess.criarPessoa(pf);

    }

     public Integer criarPessoa(PessoaJuridica pj) {

        return pess.criarPessoa(pj);

    }

    public void excluirPessoa(Integer[] pessoaIds) {

        pess.excluirPessoa(pessoaIds);

    }

    public void atualizarPessoaFisica(PessoaFisica pf) {

        pess.atualizarPessoaFisica(pf);

    }


     public void atualizarPessoaJuridica(PessoaJuridica pj) {

        pess.atualizarPessoaJuridica(pj);

    }

    public PessoaFisica lerPessoaFisica(int idPessoa) {

        return pess.lerPessoaFisica(idPessoa);
    }

    public PessoaJuridica lerPessoaJuridica(int idPessoa) {

        return pess.lerPessoaJuridica(idPessoa);
    }

    public ArrayList<PessoaFisica> listarPessoasFisicas(){
       return pess.listarPessoasFisicas();
    }

    
    public ArrayList<PessoaJuridica> listarPessoasJuridicas(){
        return pess.listarPessoasJuridicas();
    }

   /* public void efetuarCandidatura(Integer idUsr, Integer idVaga, String msg){
       pess.criarCandidatura(idUsr, idVaga, msg);
    }*/

    /* public ArrayList<Candidatura> listarCandidaturasPorIdVaga(Integer idVaga) {
         return pess.listarCandidaturasPorIdVaga(idVaga);
     }*/

}
