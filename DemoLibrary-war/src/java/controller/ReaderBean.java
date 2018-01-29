/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Reader;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import session.ReaderFacade;

/**
 *
 * @author oswal
 */
public class ReaderBean implements Serializable {
    
    private static final String READER_LIST_PAGE_REDIRECT = "reader_list?faces-redirect=true";

    @EJB
    private ReaderFacade readerFacade;

    private String name;
    private String email;
    private String phone;
    private String dob;
    private boolean gender;
    private String address;
    private boolean status;
    private String code;

    private List<Reader> readers;

    private Reader currentReader;

    public ReaderBean() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Reader> getReaders() {
        readers = readerFacade.findAll();
        return readers;
    }

    public void updateInit() {
        code = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");

        currentReader = readerFacade.findByCode(code);
        if (currentReader != null) {
            name = currentReader.getName();
            email = currentReader.getEmail();
            phone = currentReader.getPhone();
            dob = currentReader.getDob();
            gender = currentReader.getGender();
            address = currentReader.getAddress();
            status = currentReader.getStatus();
        }
    }

    public void update() {
        try {
            currentReader = readerFacade.findByCode(code);

            if (currentReader != null) {
                currentReader.setName(name);
                currentReader.setEmail(email);
                currentReader.setDob(dob);
                currentReader.setPhone(phone);
                currentReader.setGender(gender);
                currentReader.setAddress(address);
                currentReader.setStatus(status);

                readerFacade.edit(currentReader);

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Update success", "Reader successfully updated"));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Update fail", "There was error while updating reader"));
        }
    }

    public String create() {
        try {
            Reader reader = new Reader();
            
            reader.setName(name);
            reader.setEmail(email);
            reader.setDob(dob);
            reader.setPhone(phone);
            reader.setGender(gender);
            reader.setAddress(address);
            reader.setStatus(true);
            
            int lastId = readerFacade.findLastId();
            reader.setCode("R" + String.format("%07d", lastId + 1));

            readerFacade.create(reader);
            
            return READER_LIST_PAGE_REDIRECT;
            
            
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Create fail", "There was error while creating new reader"));
            return null;
        }
    }
}
