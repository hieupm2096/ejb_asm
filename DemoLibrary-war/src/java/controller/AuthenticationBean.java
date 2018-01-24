/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Librarian;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import session.LibrarianFacade;

/**
 *
 * @author oswal
 */
public class AuthenticationBean implements Serializable{

    @EJB
    private LibrarianFacade librarianFacade;

    private String username;
    private String password;
    private Librarian loggedInLibrarian;

    public static final String HOME_PAGE_REDIRECT = "home?faces-redirect=true";
    public static final String LOGIN_PAGE_REDIRECT = "login?faces-redirect=true";

    public AuthenticationBean() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Librarian getLoggedInLibrarian() {
        return loggedInLibrarian;
    }

    public String login() {
        loggedInLibrarian = librarianFacade.login(username, password);
        if (loggedInLibrarian != null) {
            return HOME_PAGE_REDIRECT;
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Login failed", "Invalid or unknown credential"));
        return LOGIN_PAGE_REDIRECT;
    }

    public String logout() {

        // invalidate the session
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return LOGIN_PAGE_REDIRECT;
    }

    private boolean isLoggedIn() {
        return loggedInLibrarian != null;
    }

    public String isLoggedInForwardHome() {
        if (isLoggedIn()) {
            return HOME_PAGE_REDIRECT;
        }
        return null;
    }
}
