package no.nav.elin.stub.config;

import javax.xml.ws.Endpoint;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.ext.logging.LoggingFeature;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import no.nav.elin.stub.data.config.SpringProfile;
import no.nav.elin.stub.ws.endpoint.BisysReskWSSoapEndpoint;
import no.spn.rtv.webservices.bisysreskws.BisysReskWSSoap;

@Configuration
@Profile(SpringProfile.TEST)
@ComponentScan("no.nav.elin.stub")
public class TestConfig {

  public static final String SERVICE_URL = "http://localhost:8085/services/elin-stub";

  @Bean("bisysReskWSSoapClient")
  public BisysReskWSSoap bisysReskWSSoapClient() {
    JaxWsProxyFactoryBean client = new JaxWsProxyFactoryBean();
    client.setServiceClass(BisysReskWSSoap.class);
    client.setAddress(SERVICE_URL);
    return (BisysReskWSSoap) client.create();
  }

  @Bean(name = Bus.DEFAULT_BUS_ID)
  public SpringBus springBus(LoggingFeature loggingFeature) {

    SpringBus bus = new SpringBus();
    bus.getFeatures().add(loggingFeature);

    return bus;
  }

  @Bean
  public LoggingFeature loggingFeature() {

    LoggingFeature loggingFeature = new LoggingFeature();
    loggingFeature.setPrettyLogging(true);

    return loggingFeature;
  }

  @Bean
  public Endpoint endpoint(Bus bus, LoggingFeature loggingFeature,
      BisysReskWSSoapEndpoint bisysReskWSSoapEndpoint) {

    EndpointImpl endpoint = new EndpointImpl(bus, bisysReskWSSoapEndpoint);
    endpoint.publish(SERVICE_URL);

    return endpoint;
  }

}
