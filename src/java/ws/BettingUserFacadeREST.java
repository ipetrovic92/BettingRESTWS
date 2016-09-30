/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ws;

import domain.Administrator;
import domain.BettingUser;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author Ivan
 */
@Stateless
@Path("/user/")
public class BettingUserFacadeREST extends AbstractFacade<BettingUser> {
    @PersistenceContext(unitName = "BettingRESTWSPU")
    private EntityManager em;

    public BettingUserFacadeREST() {
        super(BettingUser.class);
    }
    
    @POST
    @Path("login")
    @Consumes("application/json")
    @Produces("application/json")
    public BettingUser logIn(BettingUser u) {
        Query userQuery = em.createNamedQuery("BettingUser.findByEmailAndPassword");
        userQuery.setParameter("email", u.getEmail());
        List<BettingUser> userList = userQuery.setParameter("password", u.getPassword()).getResultList();

        if (userList.isEmpty()) {
            return null;
        }
        return userList.get(0);
    }

    @PUT
    @Path("{id}")
    @Consumes("application/json")
    public void edit(@PathParam("id") String id, BettingUser entity) {
        super.edit(entity);
    }

    @GET
    @Override
    @Produces("application/json")
    public List<BettingUser> findAll() {
        return super.findAll();
    }
    
    @POST
    @Path("register")
    @Consumes("application/json")
    @Produces("application/json")
    public void registerNewUser(BettingUser entity) throws Exception {
        Query adminQuery = em.createNamedQuery("Administrator.findByEmail");
        List<Administrator> adminList = adminQuery.setParameter("email", entity.getEmail()).getResultList();
        if (!adminList.isEmpty()) {
            throw new Exception("Exception_AdministratorWithEmailExist");
        }

        Query userQuery = em.createNamedQuery("BettingUser.findByEmail");
        List<BettingUser> userList = userQuery.setParameter("email", entity.getEmail()).getResultList();
        if (!userList.isEmpty()) {
            throw new Exception("Exception_UserWithEmailExist");
        }
        super.create(entity);
    }

    @GET
    @Path("{id}")
    @Produces("application/json")
    public BettingUser find(@PathParam("id") String id) {
        return super.find(id);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
