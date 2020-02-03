package no.nav.elin.stub;

import javax.sql.DataSource;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import lombok.extern.slf4j.Slf4j;
import no.nav.elin.stub.data.config.SpringProfile;

@Slf4j
@SpringBootApplication
public class ElinStubWs {

  public static void main(String[] args) {
    String profile = args.length < 1 ? SpringProfile.DEFAULT : args[0];

    log.info("Setting active profile: {}", profile);
    new SpringApplicationBuilder().sources(ElinStubWs.class).profiles(profile).run(args);
  }

  @Bean
  @Profile(SpringProfile.LOCAL)
  public DataSourceInitializer dataSourceInitializer(DataSource dataSource) {
    DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
    dataSourceInitializer.setDataSource(dataSource);
    ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
    databasePopulator
        .addScript(new DefaultResourceLoader().getResource("classpath:testdata/testdataLocal.sql"));
    dataSourceInitializer.setDatabasePopulator(databasePopulator);
    return dataSourceInitializer;
  }
}
