package no.nav.elin.stub.service;

import static org.junit.Assert.assertTrue;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import no.nav.elin.stub.config.TestConfig;
import no.nav.elin.stub.data.config.SpringProfile;
import no.nav.elin.stub.data.service.DataManagementService;
import no.spn.rtv.webservices.bisysreskws.BisysReskWSSoap;
import no.spn.rtv.webservices.bisysreskws.CResknObjectHolder;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles(profiles = SpringProfile.TEST)
@ContextConfiguration(classes = TestConfig.class)
public class BisysReskWSSoapEndpointTest {

  private static final int AKSJONSKODE = 1;
  private static final int SAKSNR = 1900000;

  @Mock
  private DataManagementService dataManagementService;

  @Autowired
  @Qualifier("bisysReskWSSoapClient")
  private BisysReskWSSoap bisysReskWSSoapClient;

  @Test
  public void loadContext() {}

  @Ignore
  @Test
  public void getNaksjonsKodeFromRohPrSakPrBarn() {

    CResknObjectHolder response =
        (CResknObjectHolder) bisysReskWSSoapClient.rohPrSakPrBarn(AKSJONSKODE, SAKSNR);

    assertTrue(response != null);

  }

}
