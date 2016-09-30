/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Ivan
 */
@Embeddable
public class TicketMatchPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "email_user")
    private String emailUser;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "ticket_id")
    private String ticketId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "match_id")
    private String matchId;

    public TicketMatchPK() {
    }

    public TicketMatchPK(String emailUser, String ticketId, String matchId) {
        this.emailUser = emailUser;
        this.ticketId = ticketId;
        this.matchId = matchId;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (emailUser != null ? emailUser.hashCode() : 0);
        hash += (ticketId != null ? ticketId.hashCode() : 0);
        hash += (matchId != null ? matchId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof TicketMatchPK)) {
            return false;
        }
        TicketMatchPK other = (TicketMatchPK) object;
        return other.getEmailUser().equals(this.getEmailUser()) && other.getTicketId().equals(this.getTicketId()) && other.getMatchId().equals(this.getMatchId());
    }

    @Override
    public String toString() {
        return "TicketMatch --> Email user: " + emailUser + "; Ticket ID: " + ticketId + "; Match ID: " + matchId + ". ";
    }
}
