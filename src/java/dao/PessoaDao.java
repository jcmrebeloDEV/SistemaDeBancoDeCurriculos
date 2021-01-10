package dao;

import entity.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class PessoaDao {

    public Integer criarPessoa(PessoaFisica pf) {
        Session ss = HibernateUtil.getSession();
        Transaction tx = null;
        Integer id = null;
        try {
            tx = ss.beginTransaction();

            Curriculo c = new Curriculo();
            Calendar now = Calendar.getInstance();
            c.setDataUltimaAlteracao(now.getTime());
            c.setPessoaFisica(pf);
            pf.setCurriculo(c);

            id = (Integer) ss.save(pf);

            tx.commit();

        } catch (Exception ex) {
            Logger.getLogger(PessoaDao.class.getName()).log(Level.SEVERE, null, ex);
            tx.rollback();
        } finally {
            ss.close();
        }

        return id;
    }

    public Integer criarPessoa(PessoaJuridica pj) {
        Session ss = HibernateUtil.getSession();
        Transaction tx = null;
        Integer id = null;
        try {
            tx = ss.beginTransaction();

            id = (Integer) ss.save(pj);

            tx.commit();

        } catch (Exception ex) {
            Logger.getLogger(PessoaDao.class.getName()).log(Level.SEVERE, null, ex);
            tx.rollback();
        } finally {
            ss.close();
        }

        return id;
    }

    public void excluirPessoa(Integer[] pessoaIds) {

        String sql = null;

        Session ss = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = ss.beginTransaction();

            Query q = ss.createQuery("DELETE FROM PessoaFisica pf WHERE pf.id IN (:ids)");
            q.setParameterList("ids", pessoaIds);
            q.executeUpdate();

            q = ss.createQuery("DELETE FROM PessoaJuridica pj WHERE pj.id IN (:ids)");
            q.setParameterList("ids", pessoaIds);
            q.executeUpdate();

            tx.commit();
        } catch (Exception ex) {
            Logger.getLogger(PessoaDao.class.getName()).log(Level.SEVERE, null, ex);
            tx.rollback();
        } finally {
            ss.close();
        }

    }

    public void atualizarPessoaFisica(PessoaFisica pf) {

        Session ss = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = ss.beginTransaction();
            ss.update(pf);
            tx.commit();
        } catch (Exception ex) {
            Logger.getLogger(PessoaDao.class.getName()).log(Level.SEVERE, null, ex);
            tx.rollback();
        } finally {
            ss.close();
        }

    }

    public void atualizarPessoaJuridica(PessoaJuridica pj) {

        Session ss = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = ss.beginTransaction();
            ss.update(pj);
            tx.commit();
        } catch (Exception ex) {
            Logger.getLogger(PessoaDao.class.getName()).log(Level.SEVERE, null, ex);
            tx.rollback();
        } finally {
            ss.close();
        }

    }

    public PessoaFisica lerPessoaFisica(int idPessoa) {

        PessoaFisica p = null;
        Session ss = HibernateUtil.getSession();
        try {
            Query q = ss.createQuery("from PessoaFisica p left join fetch p.candidaturas where p.id=:id");
            q.setInteger("id", idPessoa);
            p = (PessoaFisica) q.uniqueResult();
         
        } catch (Exception ex) {
            Logger.getLogger(PessoaDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ss.close();
        }
        return p;
    }

    public PessoaJuridica lerPessoaJuridica(int idPessoa) {

        PessoaJuridica p = null;
        Session ss = HibernateUtil.getSession();
        try {
            Query q = ss.createQuery("from PessoaJuridica p left join fetch p.vagas v left join fetch v.candidaturas where p.id=:id");
            q.setInteger("id", idPessoa);
            p = (PessoaJuridica) q.uniqueResult();
        } catch (Exception ex) {
            Logger.getLogger(PessoaDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ss.close();
        }
        return p;
    }

    public ArrayList<PessoaFisica> listarPessoasFisicas() {
        ArrayList<PessoaFisica> pessoasFisicas = new ArrayList<PessoaFisica>();
        Session ss = HibernateUtil.getSession();
        try {
            Query q = ss.createQuery("from PessoaFisica");
            List lista = q.list();

            pessoasFisicas.addAll(lista);
        } catch (Exception ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ss.close();
        }


        return pessoasFisicas;
    }

     public ArrayList<PessoaJuridica> listarPessoasJuridicas() {
        ArrayList<PessoaJuridica> pessoasJuridicas = new ArrayList<PessoaJuridica>();
        Session ss = HibernateUtil.getSession();
        try {
            Query q = ss.createQuery("from PessoaJuridica");
            List lista = q.list();

            pessoasJuridicas.addAll(lista);
        } catch (Exception ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ss.close();
        }


        return pessoasJuridicas;
    }

   
}
