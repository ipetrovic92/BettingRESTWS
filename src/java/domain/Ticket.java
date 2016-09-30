/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ivan
 */
@Entity
@Table(name = "ticket")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ticket.findAll", query = "SELECT t FROM Ticket t"),
    @NamedQuery(name = "Ticket.findByEmailUser", query = "SELECT t FROM Ticket t WHERE t.ticketPK.emailUser = :emailUser ORDER BY t.paymentTime DESC"),
    @NamedQuery(name = "Ticket.findById", query = "SELECT t FROM Ticket t WHERE t.ticketPK.id = :id"),
    @NamedQuery(name = "Ticket.findByEmailUserAndId", query = "SELECT t FROM Ticket t WHERE t.ticketPK.emailUser = :emailUser AND t.ticketPK.id = :id"),
    @NamedQuery(name = "Ticket.findByPaymentTime", query = "SELECT t FROM Ticket t WHERE t.paymentTime = :paymentTime"),
    @NamedQuery(name = "Ticket.findByStake", query = "SELECT t FROM Ticket t WHERE t.stake = :stake"),
    @NamedQuery(name = "Ticket.findByTotalOdds", query = "SELECT t FROM Ticket t WHERE t.totalOdds = :totalOdds"),
    @NamedQuery(name = "Ticket.findLatestPlayedTickets", query = "SELECT t FROM Ticket t WHERE t.paymentTime = (SELECT MAX(ti.paymentTime) from Ticket ti)"),
    @NamedQuery(name = "Ticket.findByPotentialWinnings", query = "SELECT t FROM Ticket t WHERE t.potentialWinnings = :potentialWinnings")})
public class Ticket implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TicketPK ticketPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "payment_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date paymentTime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "stake")
    private double stake;
    @Basic(optional = false)
    @NotNull
    @Column(name = "total_odds")
    private double totalOdds;
    @Basic(optional = false)
    @NotNull
    @Column(name = "potential_winnings")
    private double potentialWinnings;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER , mappedBy = "ticket")
    private List<TicketMatch> ticketMatchList;
    @JoinColumn(name = "email_user", referencedColumnName = "email", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private BettingUser bettingUser;
    @JoinColumn(name = "status", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Status status;

    public Ticket() {
    }

    public Ticket(TicketPK ticketPK) {
        this.ticketPK = ticketPK;
    }

    public Ticket(TicketPK ticketPK, Date paymentTime, double stake, double totalOdds, double potentialWinnings) {
        this.ticketPK = ticketPK;
        this.paymentTime = paymentTime;
        this.stake = stake;
        this.totalOdds = totalOdds;
        this.potentialWinnings = potentialWinnings;
    }

    public Ticket(String emailUser, String id) {
        this.ticketPK = new TicketPK(emailUser, id);
    }

    public TicketPK getTicketPK() {
        return ticketPK;
    }

    public void setTicketPK(TicketPK ticketPK) {
        this.ticketPK = ticketPK;
    }

    public Date getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    public double getStake() {
        return stake;
    }

    public void setStake(double stake) {
        this.stake = stake;
    }

    public double getTotalOdds() {
        return totalOdds;
    }

    public void setTotalOdds(double totalOdds) {
        this.totalOdds = totalOdds;
    }

    public double getPotentialWinnings() {
        return potentialWinnings;
    }

    public void setPotentialWinnings(double potentialWinnings) {
        this.potentialWinnings = potentialWinnings;
    }

    @XmlTransient
    public List<TicketMatch> getTicketMatchList() {
        return ticketMatchList;
    }

    public void setTicketMatchList(List<TicketMatch> ticketMatchList) {
        this.ticketMatchList = ticketMatchList;
    }

    public BettingUser getBettingUser() {
        return bettingUser;
    }

    public void setBettingUser(BettingUser bettingUser) {
        this.bettingUser = bettingUser;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ticketPK != null ? ticketPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Ticket)) {
            return false;
        }
        Ticket other = (Ticket) object;
        return other.getTicketPK().equals(this.getTicketPK());
    }

    @Override
    public String toString() {
        String result = ""; 
        
        StringBuilder sb = new StringBuilder(); 
        sb.append("****************************************\n");
        sb.append("User: ").append(bettingUser.getFirstName()).append(" ").append(bettingUser.getLastName()).append(". \n"); 
        sb.append("ID: ").append(getTicketPK().getId()).append(". Time: ").append(new SimpleDateFormat("dd.MM.yyyy").format(paymentTime)).append(". \n"); 
        sb.append("Stake: ").append(stake).append(". Total Odds: ").append(totalOdds).append(". Potential winning amount: ").append(potentialWinnings).append(". \n"); 
        
        for (TicketMatch ticketMatch : ticketMatchList) {
            sb.append(ticketMatch.getGame().toString()).append(". Prediction: ").append(ticketMatch.getResultPrediction()).append(". Status: ").append(ticketMatch.getStatus()).append(". \n"); 
        }
        sb.append("****************************************");
        
        return result;
    }
}
