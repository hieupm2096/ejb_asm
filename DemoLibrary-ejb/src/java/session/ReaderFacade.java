/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Reader;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author oswal
 */
@Stateless
public class ReaderFacade extends AbstractFacade<Reader> {

    @PersistenceContext(unitName = "DemoLibrary-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReaderFacade() {
        super(Reader.class);
    }

    public Reader findByCode(String code) {
        return em.createNamedQuery("Reader.findByCode", Reader.class).setParameter("code", code).getSingleResult();
    }

//    public boolean create(String name, String email, String phone, String dob, boolean gender, String address) {
//        StoredProcedureQuery query = em.createStoredProcedureQuery("insert_reader", Integer.class);
//        query.setParameter("name", name);
//        query.setParameter("email", email);
//        query.setParameter("phone", phone);
//        query.setParameter("dob", dob);
//        query.setParameter("gender", gender);
//        query.setParameter("address", address);
//        return query.executeUpdate() != -1;
//    }
    
    public int findLastId() {
        TypedQuery<Reader> query = em.createQuery("SELECT r FROM Reader r ORDER BY r.id DESC", Reader.class);
        return query.setMaxResults(1).getSingleResult().getId();
    }
}
