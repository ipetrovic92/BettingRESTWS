/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import domain.BettingUser;
import domain.Ticket;
import domain.TicketMatch;
import domain.TicketPK;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.PathSegment;

/**
 *
 * @author Ivan
 */
@Stateless
@Path("/ticket/")
public class TicketFacadeREST extends AbstractFacade<Ticket> {

    @PersistenceContext(unitName = "BettingRESTWSPU")
    private EntityManager em;

    private TicketPK getPrimaryKey(PathSegment pathSegment) {
        /*
         * pathSemgent represents a URI path segment and any associated matrix parameters.
         * URI path part is supposed to be in form of 'somePath;emailUser=emailUserValue;id=idValue'.
         * Here 'somePath' is a result of getPath() method invocation and
         * it is ignored in the following code.
         * Matrix parameters are used as field names to build a primary key instance.
         */
        domain.TicketPK key = new domain.TicketPK();
        javax.ws.rs.core.MultivaluedMap<String, String> map = pathSegment.getMatrixParameters();
        java.util.List<String> emailUser = map.get("emailUser");
        if (emailUser != null && !emailUser.isEmpty()) {
            key.setEmailUser(emailUser.get(0));
        }
        java.util.List<String> id = map.get("id");
        if (id != null && !id.isEmpty()) {
            key.setId(id.get(0));
        }
        return key;
    }

    public TicketFacadeREST() {
        super(Ticket.class);
    }

    @GET
    @Path("newid")
    @Produces("application/json")
    public String getNewTicketId() {
        List<Ticket> resultList = em.createNamedQuery("Ticket.findLatestPlayedTickets").getResultList();

        if (resultList == null || resultList.isEmpty()) {
            return "1";
        }

        int maxId = -1;
        for (Ticket t : resultList) {
            int ticketId = Integer.parseInt(t.getTicketPK().getId());
            if (ticketId > maxId) {
                maxId = ticketId;
            }
        }
        return (maxId + 1) + "";
    }

    @POST
    @Path("saveticket")
    @Consumes("application/json")
    public void saveTicket(Ticket t) throws Exception {
        Query userQuery = em.createNamedQuery("Ticket.findByEmailUserAndId");
        userQuery.setParameter("emailUser", t.getTicketPK().getEmailUser());
        List<Ticket> userList = userQuery.setParameter("id", t.getTicketPK().getId()).getResultList();

        if (!userList.isEmpty()) {
            throw new Exception("Exception_TicketAlreadyExist");
        }
        
        super.create(t);
    }

    @POST
    @Path("usertickets")
    @Consumes("application/json")
    public List<Ticket> getUserTickets(BettingUser bettingUser) {
        Query ticketQuery = em.createNamedQuery("Ticket.findByEmailUser");
        List<Ticket> ticketList = ticketQuery.setParameter("emailUser", bettingUser.getEmail()).getResultList();
        return ticketList;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
