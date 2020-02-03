package no.nav.elin.stub.data.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@Profile(SpringProfile.INTEGRATION_TEST)
public class DatasourceConfigIntegrationTest {

  @Value("${datasource.driverClassName}")
  private String driverClass;
  @Value("${datasource.url}")
  private String url;
  @Value("${datasource.username}")
  private String username;
  @Value("${datasource.password}")
  private String password;

  @Bean
  public DataSource dataSource() {
    System.out.println(driverClass + " " + url + " " + username + " " + password);
    DriverManagerDataSource source = new DriverManagerDataSource();
    source.setDriverClassName(driverClass);
    source.setUrl(url);
    source.setUsername(username);
    source.setPassword(password);
    return source;
  }

  @Bean
  public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
    NamedParameterJdbcTemplate namedParameterJdbcTemplate =
        new NamedParameterJdbcTemplate(this.dataSource());
    return namedParameterJdbcTemplate;
  }

}
