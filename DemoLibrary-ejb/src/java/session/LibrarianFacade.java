/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Librarian;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author oswal
 */
@Stateless
public class LibrarianFacade extends AbstractFacade<Librarian> {

    @PersistenceContext(unitName = "DemoLibrary-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LibrarianFacade() {
        super(Librarian.class);
    }

    public List<Librarian> getAll() {
        TypedQuery<Librarian> query = em.createNamedQuery("Librarian.findAll", Librarian.class);
        return query.getResultList();
    }

    public Librarian login(String username, String password) {
        TypedQuery<Librarian> query = em.createQuery("SELECT l FROM Librarian l WHERE l.username = :username AND l.password = :password", Librarian.class);
        query.setParameter("username", username);
        query.setParameter("password", password);
        return query.getSingleResult();
    }
}
