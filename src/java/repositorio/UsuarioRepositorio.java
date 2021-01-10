package repositorio;

import dao.*;
import entity.*;
import java.util.ArrayList;

public class UsuarioRepositorio {

    private UsuarioDao usr = new UsuarioDao();

    public void criarUsuario(Usuario u, Integer idP) {

        usr.criarUsuario(u, idP);

    }

    
    public void excluirUsuarios(Integer[] pessoaIds) {

        usr.excluirUsuarios(pessoaIds);

    }

    public void atualizarUsuario(Usuario u) {

        usr.atualizarUsuario(u);

    }

    public Usuario validarLogin(String login, String passwd) {

        return usr.validarLogin(login, passwd);

    }

    public Usuario lerUsuario(int idPessoa) {

        return usr.lerUsuario(idPessoa);
    }

    public ArrayList<Usuario> listarUsuarios(String filtro) {

        return usr.listarUsuarios(filtro);
    }

    public Integer obterTipoDoUsuario(int id) {

        return usr.obterTipoDoUsuario(id);

    }
}
