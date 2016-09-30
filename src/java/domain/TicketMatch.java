/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ivan
 */
@Entity
@Table(name = "ticket_match")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TicketMatch.findAll", query = "SELECT t FROM TicketMatch t"),
    @NamedQuery(name = "TicketMatch.findByEmailUser", query = "SELECT t FROM TicketMatch t WHERE t.ticketMatchPK.emailUser = :emailUser"),
    @NamedQuery(name = "TicketMatch.findByTicketId", query = "SELECT t FROM TicketMatch t WHERE t.ticketMatchPK.ticketId = :ticketId"),
    @NamedQuery(name = "TicketMatch.findByMatchId", query = "SELECT t FROM TicketMatch t WHERE t.ticketMatchPK.matchId = :matchId"),
    @NamedQuery(name = "TicketMatch.findByEmailUserAndTicketId", query = "SELECT t FROM TicketMatch t WHERE t.ticketMatchPK.emailUser = :emailUser AND t.ticketMatchPK.ticketId = :ticketId"),
    @NamedQuery(name = "TicketMatch.findByResultPrediction", query = "SELECT t FROM TicketMatch t WHERE t.resultPrediction = :resultPrediction"),
    @NamedQuery(name = "TicketMatch.findByOdd", query = "SELECT t FROM TicketMatch t WHERE t.odd = :odd")})
public class TicketMatch implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TicketMatchPK ticketMatchPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "result_prediction")
    private int resultPrediction;
    @Basic(optional = false)
    @NotNull
    @Column(name = "odd")
    private double odd;
    @JoinColumn(name = "match_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Game game;
    @JoinColumn(name = "status", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Status status;
    @JoinColumns({
        @JoinColumn(name = "email_user", referencedColumnName = "email_user", insertable = false, updatable = false),
        @JoinColumn(name = "ticket_id", referencedColumnName = "id", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Ticket ticket;

    public TicketMatch() {
    }

    public TicketMatch(TicketMatchPK ticketMatchPK) {
        this.ticketMatchPK = ticketMatchPK;
    }

    public TicketMatch(TicketMatchPK ticketMatchPK, int resultPrediction, double odd) {
        this.ticketMatchPK = ticketMatchPK;
        this.resultPrediction = resultPrediction;
        this.odd = odd;
    }

    public TicketMatch(String emailUser, String ticketId, String matchId) {
        this.ticketMatchPK = new TicketMatchPK(emailUser, ticketId, matchId);
    }

    public TicketMatchPK getTicketMatchPK() {
        return ticketMatchPK;
    }

    public void setTicketMatchPK(TicketMatchPK ticketMatchPK) {
        this.ticketMatchPK = ticketMatchPK;
    }

    public int getResultPrediction() {
        return resultPrediction;
    }

    public void setResultPrediction(int resultPrediction) {
        this.resultPrediction = resultPrediction;
    }

    public double getOdd() {
        return odd;
    }

    public void setOdd(double odd) {
        this.odd = odd;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ticketMatchPK != null ? ticketMatchPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof TicketMatch)) {
            return false;
        }
        TicketMatch other = (TicketMatch) object;
        return other.getTicketMatchPK().equals(this.getTicketMatchPK());
    }

    @Override
    public String toString() {
        return "TicketMatch --> " + ticketMatchPK + ". ";
    }
}
