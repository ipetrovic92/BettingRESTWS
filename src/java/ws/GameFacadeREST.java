/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ws;

import domain.Game;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author Ivan
 */
@Stateless
@Path("/game/")
public class GameFacadeREST extends AbstractFacade<Game> {
    @PersistenceContext(unitName = "BettingRESTWSPU")
    private EntityManager em;

    public GameFacadeREST() {
        super(Game.class);
    }

    @PUT
    @Path("{id}")
    @Consumes("application/json")
    public void edit(@PathParam("id") String id, Game entity) {
        super.edit(entity);
    }

    @GET
    @Override
    @Produces("application/json")
    public List<Game> findAll() {
        return super.findAll();
    }

    @GET
    @Path("gameafter/{stringdate}")
    @Produces("application/json")
    public List<Game> getGameStartingAfter(@PathParam("stringdate") String stringDate) {
        Long longDate = Long.parseLong(stringDate);
        Date date = new Date(longDate);
        Query ticketQuery = em.createNamedQuery("Game.findGameStartingAfterTime");
        return ticketQuery.setParameter("gameTime", date).getResultList();
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
