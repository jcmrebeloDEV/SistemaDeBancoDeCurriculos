package dao;

import entity.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UsuarioDao {

    public void criarUsuario(Usuario u, Integer idP) {

        Session ss = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = ss.beginTransaction();
            //Pessoa p = (Pessoa) ss.load(Pessoa.class, idP);
            Query q = ss.createQuery("from Pessoa p where p.id=:id");
            q.setInteger("id", idP);
            Pessoa p = (Pessoa) q.uniqueResult();
            //u.setSenha(HashSenha.byteArrayToHexString(HashSenha.digest(u.getSenha().getBytes(), "md5")));
            u.setPessoa(p);
            ss.save(u);
            tx.commit();
        } catch (Exception ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
            tx.rollback();
        } finally {
            ss.close();
        }

    }

   /* public void excluirUsuario(Integer[] pessoaIds) {

        String sql = "DELETE FROM Usuario u WHERE u.pessoa.id IN (:ids)";

        Session ss = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = ss.beginTransaction();
            Query q = ss.createQuery(sql);
            q.setParameterList("ids", pessoaIds);
            q.executeUpdate();
            tx.commit();
        } catch (Exception ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
            tx.rollback();
        } finally {
            ss.close();
        }

    }*/

    public void excluirUsuarios(Integer[] pessoaIds) {

        String sql = "DELETE FROM Usuario u WHERE u.pessoa.id IN (:ids)";

        Session ss = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = ss.beginTransaction();

            Query q = ss.createQuery("DELETE FROM Candidatura c WHERE c.pessoaFisica.id IN (:ids)");
            q.setParameterList("ids", pessoaIds);
            q.executeUpdate();

            q = ss.createQuery("DELETE FROM Candidatura c WHERE c.vaga.id IN (Select v.id from Vaga v where v.pessoaJuridica.id in(:ids) )");
            q.setParameterList("ids", pessoaIds);
            q.executeUpdate();

            q = ss.createQuery("DELETE FROM Vaga v WHERE v.pessoaJuridica.id IN (:ids)");
            q.setParameterList("ids", pessoaIds);
            q.executeUpdate();

            q = ss.createQuery("DELETE FROM Curriculo c WHERE c.id IN (:ids)");
            q.setParameterList("ids", pessoaIds);
            q.executeUpdate();


            q = ss.createQuery("DELETE FROM Usuario u WHERE u.pessoa.id IN (:ids)");

            q.setParameterList("ids", pessoaIds);
            q.executeUpdate();



            q = ss.createQuery("DELETE FROM PessoaFisica pf WHERE pf.id IN (:ids)");
            q.setParameterList("ids", pessoaIds);
            q.executeUpdate();

            q = ss.createQuery("DELETE FROM PessoaJuridica pj WHERE pj.id IN (:ids)");
            q.setParameterList("ids", pessoaIds);
            q.executeUpdate();


            tx.commit();
        } catch (Exception ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
            tx.rollback();
        } finally {
            ss.close();
        }
    }

    public void atualizarUsuario(Usuario u) {

        Session ss = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = ss.beginTransaction();
            ss.update(u);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ss.close();
        }

    }

    public Usuario validarLogin(String login, String passwd) {

        Usuario usr = null;

        Session ss = HibernateUtil.getSession();
        try {
            Query q = ss.createQuery("from Usuario u left join fetch u.pessoa where u.login=:login AND u.senha=:senha");
            q.setString("login", login);
            q.setString("senha", passwd);
            usr = (Usuario) q.uniqueResult();
        } catch (Exception ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ss.close();
        }
        return usr;

    }

    public Usuario lerUsuario(int idPessoa) {

        Usuario usr = null;

        Session ss = HibernateUtil.getSession();
        try {
            Query q = ss.createQuery("from Usuario u left join fetch u.pessoa where u.id=:id");
            q.setInteger("id", idPessoa);
            usr = (Usuario) q.uniqueResult();
        } catch (Exception ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ss.close();
        }
        return usr;
    }

    public ArrayList<Usuario> listarUsuarios(String filtro) {

        ArrayList<Usuario> usrs = new ArrayList<Usuario>();
        Session ss = HibernateUtil.getSession();
        try {
            Query q = ss.createQuery("from Usuario u left join fetch u.pessoa WHERE u.pessoa.nome LIKE '%" + filtro + "%' ");
            usrs = (ArrayList<Usuario>) q.list();
        } catch (Exception ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ss.close();
        }
        return usrs;
    }

    public Integer obterTipoDoUsuario(int id) {
        Integer tipo = null;

        Session ss = HibernateUtil.getSession();
        try {
            tipo = (Integer) ss.createQuery("select u.tipo from Usuario u where u.pessoa.id=:id ").setParameter("id", id).iterate().next();
        } catch (Exception ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ss.close();
        }

        return tipo;

    }
}
