/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Author;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import session.AuthorFacade;

/**
 *
 * @author hieupm2096
 */
public class AuthorBean {

    @EJB
    private AuthorFacade authorFacade;

    private final String READER_LIST_PAGE_REDIRECT = "author_list?faces-redirect=true";

    private List<Author> items;

    private int id;
    private String name;
    private boolean status;

    public List<Author> getItems() {
        return authorFacade.findAll();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public AuthorBean() {
    }

    public String create() {
        try {
            Author author = new Author();
            author.setName(name);
            author.setStatus(true);
            authorFacade.create(author);
            return READER_LIST_PAGE_REDIRECT;
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Create fail", "There was error while creating new author"));
            return null;
        }
    }

    public void initUpdate() {
        id = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id"));
        Author tmp = authorFacade.find(id);
        name = tmp.getName();
        status = tmp.getStatus();
    }

    public String update() {
        try {
            Author author = authorFacade.find(id);
            author.setName(name);
            author.setStatus(status);
            authorFacade.edit(author);
            return READER_LIST_PAGE_REDIRECT;
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Update fail", "There was error while creating updating author"));
            return null;
        }
    }
}
