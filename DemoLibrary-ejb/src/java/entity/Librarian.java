/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author oswal
 */
@Entity
@Table(name = "Librarian")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Librarian.findAll", query = "SELECT l FROM Librarian l")
    , @NamedQuery(name = "Librarian.findById", query = "SELECT l FROM Librarian l WHERE l.id = :id")
    , @NamedQuery(name = "Librarian.findByUsername", query = "SELECT l FROM Librarian l WHERE l.username = :username")
    , @NamedQuery(name = "Librarian.findByPassword", query = "SELECT l FROM Librarian l WHERE l.password = :password")
    , @NamedQuery(name = "Librarian.findByName", query = "SELECT l FROM Librarian l WHERE l.name = :name")
    , @NamedQuery(name = "Librarian.findByEmail", query = "SELECT l FROM Librarian l WHERE l.email = :email")
    , @NamedQuery(name = "Librarian.findByPhone", query = "SELECT l FROM Librarian l WHERE l.phone = :phone")
    , @NamedQuery(name = "Librarian.findByDob", query = "SELECT l FROM Librarian l WHERE l.dob = :dob")
    , @NamedQuery(name = "Librarian.findByAddress", query = "SELECT l FROM Librarian l WHERE l.address = :address")
    , @NamedQuery(name = "Librarian.findByGender", query = "SELECT l FROM Librarian l WHERE l.gender = :gender")
    , @NamedQuery(name = "Librarian.findByStatus", query = "SELECT l FROM Librarian l WHERE l.status = :status")})
public class Librarian implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "_id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "_username")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "_password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "_name")
    private String name;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "_email")
    private String email;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "_phone")
    private String phone;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "_dob")
    private String dob;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "_address")
    private String address;
    @Basic(optional = false)
    @NotNull
    @Column(name = "_gender")
    private boolean gender;
    @Basic(optional = false)
    @NotNull
    @Column(name = "_status")
    private boolean status;

    public Librarian() {
    }

    public Librarian(Integer id) {
        this.id = id;
    }

    public Librarian(Integer id, String username, String password, String name, String email, String phone, String dob, String address, boolean gender, boolean status) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.dob = dob;
        this.address = address;
        this.gender = gender;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean getGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Librarian)) {
            return false;
        }
        Librarian other = (Librarian) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Librarian[ id=" + id + " ]";
    }
    
}
