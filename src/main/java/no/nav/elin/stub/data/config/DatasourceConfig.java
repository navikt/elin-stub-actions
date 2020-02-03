package no.nav.elin.stub.data.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Data;
import no.nav.vault.jdbc.hikaricp.HikariCPVaultUtil;
import no.nav.vault.jdbc.hikaricp.VaultError;

@Configuration
@Profile(SpringProfile.PREPROD)
public class DatasourceConfig {

  @Data
  @Configuration
  @ConfigurationProperties(prefix = "vault")
  public static class VaultConfig {

    private boolean enabled = true;
    private String databaseBackend;
    private String databaseRole;
    private String databaseAdminrole;
  }

  @Bean
  public HikariDataSource dataSource(DataSourceProperties properties, VaultConfig vaultConfig)
      throws VaultError {
    HikariConfig config = createHikariConfig(properties);
    if (vaultConfig.enabled) {
      return HikariCPVaultUtil.createHikariDataSourceWithVaultIntegration(config,
          vaultConfig.databaseBackend, vaultConfig.databaseRole);
    }
    config.setUsername(properties.getUsername());
    config.setPassword(properties.getPassword());
    return new HikariDataSource(config);
  }

  static HikariConfig createHikariConfig(DataSourceProperties properties) {
    HikariConfig config = new HikariConfig();
    config.setJdbcUrl(properties.getUrl());
    config.setMinimumIdle(1);
    config.setMaximumPoolSize(2);
    return config;
  }

}
