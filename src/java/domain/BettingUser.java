/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ivan
 */
@Entity
@Table(name = "betting_user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BettingUser.findAll", query = "SELECT b FROM BettingUser b"),
    @NamedQuery(name = "BettingUser.findByEmail", query = "SELECT b FROM BettingUser b WHERE b.email = :email"),
    @NamedQuery(name = "BettingUser.findByFirstName", query = "SELECT b FROM BettingUser b WHERE b.firstName = :firstName"),
    @NamedQuery(name = "BettingUser.findByLastName", query = "SELECT b FROM BettingUser b WHERE b.lastName = :lastName"),
    @NamedQuery(name = "BettingUser.findByDateOfBirth", query = "SELECT b FROM BettingUser b WHERE b.dateOfBirth = :dateOfBirth"),
    @NamedQuery(name = "BettingUser.findByEmailAndPassword", query = "SELECT b FROM BettingUser b WHERE b.email = :email AND b.password = :password"),
    @NamedQuery(name = "BettingUser.findByPassword", query = "SELECT b FROM BettingUser b WHERE b.password = :password"),
    @NamedQuery(name = "BettingUser.findByBalance", query = "SELECT b FROM BettingUser b WHERE b.balance = :balance")})
public class BettingUser implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "first_name")
    private String firstName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "last_name")
    private String lastName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Column(name = "balance")
    private double balance;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bettingUser")
    private List<Ticket> ticketList;

    public BettingUser() {
    }

    public BettingUser(String email) {
        this.email = email;
    }

    public BettingUser(String email, String firstName, String lastName, Date dateOfBirth, String password, double balance) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.password = password;
        this.balance = balance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @XmlTransient
    public List<Ticket> getTicketList() {
        return ticketList;
    }

    public void setTicketList(List<Ticket> ticketList) {
        this.ticketList = ticketList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (email != null ? email.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof BettingUser)) {
            return false;
        }
        BettingUser other = (BettingUser) object;
        return other.getEmail().equals(this.getEmail());
    }

    @Override
    public String toString() {
        return "User --> " + firstName +  " " + lastName + "; Email: " + email + ". ";
    }
}
