package ejb;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Properties;

@Path("/hr")
public class HrServer {
    private static final String SOA_EJB_JNDI = "java:global/SOA3/HrStatelessBean!ejb.HrStatelessBeanRemote";

    private static HrStatelessBeanRemote lookupHrStatelessBean() throws NamingException {
        Properties environment = new Properties();
        InitialContext ejbRemoteContext = new InitialContext(environment);
        String ejb_jndi = System.getenv("SOA_EJB_JNDI");
        if (ejb_jndi == null || ejb_jndi.isEmpty()) ejb_jndi = SOA_EJB_JNDI;
        return (HrStatelessBeanRemote) ejbRemoteContext.lookup(ejb_jndi);
    }

    @GET
    @Path("/welcome/{name}")
    public Response getMsg(@PathParam("name") String name) {
        String output = "Welcome   : " + name;
        return Response.status(200).entity(output).build();
    }

    @GET
    @Path("/hello")
    public Response getHello() throws NamingException {
        return Response.status(lookupHrStatelessBean().sayHello()).build();
    }

    @POST
    @Path("/hire/{person-id}/{org-id}/{position}/{status}/{start-date}")
    public Response hrHire(@PathParam("person-id") long personId, @PathParam("org-id") long orgId,
                           @PathParam("position") String position, @PathParam("status") String status,
                           @PathParam("start-date") String date) throws NamingException {
        return Response.status(lookupHrStatelessBean().callXmlHrHireWorkerDTO(personId, orgId, position, status, date)).build();
    }

    @POST
    @Path("/fire/{id}")
    public Response hrHire(@PathParam("id") long id) throws NamingException {
        return Response.status(lookupHrStatelessBean().callXmlHrHireWorkerDTO(id)).build();
    }
}
