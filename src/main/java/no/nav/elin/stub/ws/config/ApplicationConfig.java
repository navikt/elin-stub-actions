package no.nav.elin.stub.ws.config;

import java.util.Arrays;
import javax.xml.ws.Endpoint;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.ext.logging.LoggingFeature;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import no.nav.elin.stub.rs.health.Healthcheck;
import no.nav.elin.stub.ws.endpoint.BisysReskWSSoapEndpoint;

@Configuration
public class ApplicationConfig {

  @Bean(name = Bus.DEFAULT_BUS_ID)
  public SpringBus springBus(LoggingFeature loggingFeature) {
    SpringBus cxfBus = new SpringBus();
    cxfBus.getFeatures().add(loggingFeature);
    return cxfBus;
  }

  @Bean
  public LoggingFeature loggingFeature() {
    LoggingFeature loggingFeature = new LoggingFeature();
    loggingFeature.setPrettyLogging(true);
    return loggingFeature;
  }

  @Bean
  public Endpoint endpoint(Bus bus, BisysReskWSSoapEndpoint bisysReskWSSoapEndpoint) {
    EndpointImpl endpoint = new EndpointImpl(bus, bisysReskWSSoapEndpoint);
    endpoint.publish("/soap-api/elin-stub");
    return endpoint;
  }

  @Bean
  public Server rsServer(Bus bus) {
    JAXRSServerFactoryBean endpoint = new JAXRSServerFactoryBean();
    endpoint.setBus(bus);
    endpoint.setAddress("/");
    endpoint.setServiceBeans(Arrays.<Object>asList(new Healthcheck()));
    return endpoint.create();
  }
}
