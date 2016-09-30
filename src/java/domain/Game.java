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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "game")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Game.findAll", query = "SELECT g FROM Game g ORDER BY g.gameTime DESC"),
    @NamedQuery(name = "Game.findById", query = "SELECT g FROM Game g WHERE g.id = :id"),
    @NamedQuery(name = "Game.findByGameTime", query = "SELECT g FROM Game g WHERE g.gameTime = :gameTime"),
    @NamedQuery(name = "Game.findByHomeOdds", query = "SELECT g FROM Game g WHERE g.homeOdds = :homeOdds"),
    @NamedQuery(name = "Game.findByAwayOdds", query = "SELECT g FROM Game g WHERE g.awayOdds = :awayOdds"),
    @NamedQuery(name = "Game.findByDrawOdds", query = "SELECT g FROM Game g WHERE g.drawOdds = :drawOdds"),
    @NamedQuery(name = "Game.findGameStartingAfterTime", query = "SELECT g FROM Game g WHERE g.gameTime >= :gameTime"),
    @NamedQuery(name = "Game.findByScore", query = "SELECT g FROM Game g WHERE g.score = :score")})
public class Game implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "game_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date gameTime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "home_odds")
    private double homeOdds;
    @Basic(optional = false)
    @NotNull
    @Column(name = "away_odds")
    private double awayOdds;
    @Basic(optional = false)
    @NotNull
    @Column(name = "draw_odds")
    private double drawOdds;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "score")
    private String score;
    @JoinColumn(name = "away_team", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Team awayTeam;
    @JoinColumn(name = "email_administrator", referencedColumnName = "email")
    @ManyToOne(optional = false)
    private Administrator emailAdministrator;
    @JoinColumn(name = "home_team", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Team homeTeam;
    @JoinColumn(name = "processing_status", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Processing processingStatus;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "game")
    private List<TicketMatch> ticketMatchList;

    public Game() {
    }

    public Game(String id) {
        this.id = id;
    }

    public Game(String id, Date gameTime, double homeOdds, double awayOdds, double drawOdds, String score) {
        this.id = id;
        this.gameTime = gameTime;
        this.homeOdds = homeOdds;
        this.awayOdds = awayOdds;
        this.drawOdds = drawOdds;
        this.score = score;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getGameTime() {
        return gameTime;
    }

    public void setGameTime(Date gameTime) {
        this.gameTime = gameTime;
    }

    public double getHomeOdds() {
        return homeOdds;
    }

    public void setHomeOdds(double homeOdds) {
        this.homeOdds = homeOdds;
    }

    public double getAwayOdds() {
        return awayOdds;
    }

    public void setAwayOdds(double awayOdds) {
        this.awayOdds = awayOdds;
    }

    public double getDrawOdds() {
        return drawOdds;
    }

    public void setDrawOdds(double drawOdds) {
        this.drawOdds = drawOdds;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    public Administrator getEmailAdministrator() {
        return emailAdministrator;
    }

    public void setEmailAdministrator(Administrator emailAdministrator) {
        this.emailAdministrator = emailAdministrator;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Processing getProcessingStatus() {
        return processingStatus;
    }

    public void setProcessingStatus(Processing processingStatus) {
        this.processingStatus = processingStatus;
    }

    @XmlTransient
    public List<TicketMatch> getTicketMatchList() {
        return ticketMatchList;
    }

    public void setTicketMatchList(List<TicketMatch> ticketMatchList) {
        this.ticketMatchList = ticketMatchList;
    }

     @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Game)) {
            return false;
        }
        Game other = (Game) object;
        return other.getId().equals(this.getId());
    }

    @Override
    public String toString() {
        return "Game --> Home: " + homeTeam.getName() + " vs Away: " + awayTeam.getName() + ". ";
    }
}
