/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import domain.Status;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author Ivan
 */
@Stateless
@Path("/status/")
public class StatusFacadeREST extends AbstractFacade<Status> {

    @PersistenceContext(unitName = "BettingRESTWSPU")
    private EntityManager em;

    public StatusFacadeREST() {
        super(Status.class);
    }

    @GET
    @Path("{id}")
    @Produces("application/json")
    public Status find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
