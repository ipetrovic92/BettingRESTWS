/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ivan
 */
@Entity
@Table(name = "processing")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Processing.findAll", query = "SELECT p FROM Processing p"),
    @NamedQuery(name = "Processing.findById", query = "SELECT p FROM Processing p WHERE p.id = :id"),
    @NamedQuery(name = "Processing.findByProcessingOutcome", query = "SELECT p FROM Processing p WHERE p.processingOutcome = :processingOutcome")})
public class Processing implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "processing_outcome")
    private String processingOutcome;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "processingStatus")
    private List<Game> gameList;

    public Processing() {
    }

    public Processing(Integer id) {
        this.id = id;
    }

    public Processing(Integer id, String processingOutcome) {
        this.id = id;
        this.processingOutcome = processingOutcome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProcessingOutcome() {
        return processingOutcome;
    }

    public void setProcessingOutcome(String processingOutcome) {
        this.processingOutcome = processingOutcome;
    }

    @XmlTransient
    public List<Game> getGameList() {
        return gameList;
    }

    public void setGameList(List<Game> gameList) {
        this.gameList = gameList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Processing)) {
            return false;
        }
        Processing other = (Processing) object;
        return other.getId().equals(this.getId());
    }

    @Override
    public String toString() {
        return "Processing --> " + processingOutcome + ". ";
    }
}
