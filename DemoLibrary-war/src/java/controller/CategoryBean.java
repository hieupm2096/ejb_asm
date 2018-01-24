/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Category;
import java.util.List;
import javax.ejb.EJB;
import session.CategoryFacade;

/**
 *
 * @author oswal
 */
public class CategoryBean {

    @EJB
    private CategoryFacade categoryFacade;

    private List<Category> categories;

    public CategoryBean() {
    }

    public List<Category> getCategories() {
        return categoryFacade.findAll();
    }

}
