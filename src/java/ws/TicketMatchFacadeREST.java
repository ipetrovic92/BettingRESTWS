/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ws;

import domain.TicketMatch;
import domain.TicketMatchPK;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.PathSegment;

/**
 *
 * @author Ivan
 */
@Stateless
@Path("/ticketmatch/")
public class TicketMatchFacadeREST extends AbstractFacade<TicketMatch> {
    @PersistenceContext(unitName = "BettingRESTWSPU")
    private EntityManager em;

    private TicketMatchPK getPrimaryKey(PathSegment pathSegment) {
        /*
         * pathSemgent represents a URI path segment and any associated matrix parameters.
         * URI path part is supposed to be in form of 'somePath;emailUser=emailUserValue;ticketId=ticketIdValue;matchId=matchIdValue'.
         * Here 'somePath' is a result of getPath() method invocation and
         * it is ignored in the following code.
         * Matrix parameters are used as field names to build a primary key instance.
         */
        domain.TicketMatchPK key = new domain.TicketMatchPK();
        javax.ws.rs.core.MultivaluedMap<String, String> map = pathSegment.getMatrixParameters();
        java.util.List<String> emailUser = map.get("emailUser");
        if (emailUser != null && !emailUser.isEmpty()) {
            key.setEmailUser(emailUser.get(0));
        }
        java.util.List<String> ticketId = map.get("ticketId");
        if (ticketId != null && !ticketId.isEmpty()) {
            key.setTicketId(ticketId.get(0));
        }
        java.util.List<String> matchId = map.get("matchId");
        if (matchId != null && !matchId.isEmpty()) {
            key.setMatchId(matchId.get(0));
        }
        return key;
    }

    public TicketMatchFacadeREST() {
        super(TicketMatch.class);
    }

    @POST
    @Override
    @Consumes("application/json")
    public void create(TicketMatch entity) {
        super.create(entity);
    }
    
    @GET
    @Path("{ticketId}")
    @Produces("application/json")
    public List<TicketMatch> getTicketMatchList (@PathParam("ticketId") String ticketId){
         Query userQuery = em.createNamedQuery("TicketMatch.findByTicketId");
        List<TicketMatch> ticketMatchList = userQuery.setParameter("ticketId", ticketId).getResultList();
        return ticketMatchList; 
    }
    

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
