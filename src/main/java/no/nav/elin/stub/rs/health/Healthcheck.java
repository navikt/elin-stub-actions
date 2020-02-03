package no.nav.elin.stub.rs.health;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.stereotype.Service;

@Path("/health")
@Service
public class Healthcheck {

  @GET
  @Path("/isReady")
  @Produces(MediaType.TEXT_PLAIN)
  public String isReady() {
    return "OK";
  }

  @GET
  @Path("/isAlive")
  @Produces(MediaType.TEXT_PLAIN)
  public String isAlive() {
    return "OK";
  }
}
