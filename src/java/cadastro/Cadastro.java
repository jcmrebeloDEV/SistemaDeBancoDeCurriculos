package cadastro;

import repositorio.*;
import entity.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.HashSenha;

public class Cadastro {

UsuarioRepositorio usrRep = new UsuarioRepositorio();
PessoaRepositorio pessRep = new PessoaRepositorio();
//CurriculoRepositorio currRep = new CurriculoRepositorio();
//VagaRepositorio vagaRep = new VagaRepositorio();
//CandidaturaRepositorio candRep = new CandidaturaRepositorio();
public static final char administrador = 1;
public static final char pessoaFisica = 2;
public static final char pessoaJuridica = 3;

public void criarCadastro(Usuario usr, PessoaFisica pf) {

    Integer id = pessRep.criarPessoa(pf);
    try {
        //a senha  vem descriptografada, deve ser entao criptografada
        usr.setSenha(HashSenha.criptografar(usr.getSenha(), "md5"));
    } catch (Exception ex) {
        Logger.getLogger(Cadastro.class.getName()).log(Level.SEVERE, null, ex);
    }

    usrRep.criarUsuario(usr, id);

    //Curriculo c = new Curriculo();
    //Calendar now = Calendar.getInstance();
    //c.setDataUltimaAlteracao(now.getTime());
    //currRep.criarCurriculo(c, id);

}

public void criarCadastro(Usuario usr, PessoaJuridica pj) {

    Integer id = pessRep.criarPessoa(pj);
    try {
        //a senha  vem descriptografada, deve ser entao criptografada
        usr.setSenha(HashSenha.criptografar(usr.getSenha(), "md5"));
    } catch (Exception ex) {
        Logger.getLogger(Cadastro.class.getName()).log(Level.SEVERE, null, ex);
    }
    usrRep.criarUsuario(usr, id);

}

public void atualizarCadastro(PessoaJuridica pj) {
    pessRep.atualizarPessoaJuridica(pj);
}

public void atualizarCadastro(PessoaFisica pf) {
    pessRep.atualizarPessoaFisica(pf);
}

public ArrayList<Usuario> listarCadastro(String filtro) {
    return usrRep.listarUsuarios(filtro);
}

public void excluirCadastro(Integer[] pessoaIds) {

    //deve ser nessa ordem:
   // candRep.excluirCandidatura(pessoaIds);
    //vagaRep.excluirVagaPorIdPessoaJuridica(pessoaIds);
    //currRep.excluirCurriculo(pessoaIds);
    //usrRep.excluirUsuario(pessoaIds);
    //pessRep.excluirPessoa(pessoaIds);
    usrRep.excluirUsuarios(pessoaIds);

}

public void atualizarSenha(String novaSenha, Integer id) {
    //a senha  vem descriptografada, deve ser entao criptografada
    try {
        novaSenha = HashSenha.criptografar(novaSenha, "md5");
    } catch (Exception ex) {
        Logger.getLogger(Cadastro.class.getName()).log(Level.SEVERE, null, ex);
    }
    Usuario u = usrRep.lerUsuario(id);
    u.setSenha(novaSenha);
    usrRep.atualizarUsuario(u);

}

public Usuario validarLogin(String login, String passwd) {

    try {
        passwd = HashSenha.criptografar(passwd, "md5");
    } catch (Exception ex) {
        Logger.getLogger(Cadastro.class.getName()).log(Level.SEVERE, null, ex);
    }
    return usrRep.validarLogin(login, passwd);
}


public Integer obterTipoDoUsuario(int id){

    return usrRep.obterTipoDoUsuario(id);

}

public Usuario lerUsuario(int idPessoa) {
    return usrRep.lerUsuario(idPessoa);
}

public PessoaFisica lerPessoaFisica(int idPessoa) {
    return pessRep.lerPessoaFisica(idPessoa);
}

public PessoaJuridica lerPessoaJuridica(int idPessoa) {
    return pessRep.lerPessoaJuridica(idPessoa);
}
}
