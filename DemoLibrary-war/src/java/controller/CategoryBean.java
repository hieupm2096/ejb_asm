/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Category;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import session.CategoryFacade;

/**
 *
 * @author oswal
 */
public class CategoryBean implements Serializable {

    @EJB
    private CategoryFacade categoryFacade;

    private static final String CATEGORY_LIST_PAGE_REDIRECT = "category_list?faces-redirect=true";

    private List<Category> categories;

    private int id;
    private String name;
    private boolean status;

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

    public CategoryBean() {
    }

    public List<Category> getCategories() {
        return categoryFacade.findAll();
    }

    public String create() {
        try {
            Category cate = new Category();
            cate.setName(name);
            cate.setStatus(true);
            categoryFacade.create(cate);
            return CATEGORY_LIST_PAGE_REDIRECT;
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Create fail", "There was error while creating new category"));
            return null;
        }
    }

    public void initUpdate() {
        id = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id"));
        Category tmp = categoryFacade.find(id);
        name = tmp.getName();
        status = tmp.getStatus();
    }

    public String update() {
        try {
            Category tmp = categoryFacade.find(id);
            tmp.setName(name);
            tmp.setStatus(status);
            categoryFacade.edit(tmp);
            return CATEGORY_LIST_PAGE_REDIRECT;
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Update fail", "There was error while updating category"));
            return null;
        }
    }
}
