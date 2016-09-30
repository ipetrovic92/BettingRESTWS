/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ws;

import domain.Administrator;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author Ivan
 */
@Stateless
@Path("/administrator/")
public class AdministratorFacadeREST extends AbstractFacade<Administrator> {
    @PersistenceContext(unitName = "BettingRESTWSPU")
    private EntityManager em;

    public AdministratorFacadeREST() {
        super(Administrator.class);
    }

    @POST
    @Path("login")
    @Consumes("application/json")
    @Produces("application/json")
    public Administrator logIn(Administrator a){
        Query userQuery = em.createNamedQuery("Administrator.findByEmailAndPassword");
        userQuery.setParameter("email", a.getEmail());
        List<Administrator> adminList = userQuery.setParameter("password", a.getPassword()).getResultList();

        if (adminList.isEmpty()) {
            return null;
        }
        return adminList.get(0);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
