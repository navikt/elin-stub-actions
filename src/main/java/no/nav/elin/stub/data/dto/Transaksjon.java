package no.nav.elin.stub.data.dto;

import java.math.BigDecimal;
import javax.persistence.MappedSuperclass;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@MappedSuperclass
public class Transaksjon {

  @ApiModelProperty(required = true, example = "B1")
  private String stransKode;

  @ApiModelProperty(required = false, example = "Privat bidrag")
  private String stransBeskr;

  @ApiModelProperty(required = false, example = "sbehandKode")
  private String sbehandKode;

  @ApiModelProperty(required = false, example = "sbehandBeskr")
  private String sbehandBeskr;

  @ApiModelProperty(required = true, example = "2019-11-11T15:00:00.000")
  private String dtpostDato;

  @ApiModelProperty(required = false, example = "1650")
  private BigDecimal dopprBelop;

  @ApiModelProperty(required = false, example = "0")
  private BigDecimal drestBelop;

  @ApiModelProperty(required = false, example = "0")
  private BigDecimal dvalutaOpprBelop;

  @ApiModelProperty(required = false, example = "false")
  private boolean motpost;

  @ApiModelProperty(required = false, example = "NOK")
  private String svalutaKode;

  @ApiModelProperty(required = true, example = "1980-01-01T15:00:00.000")
  private String dtbidPerDatoFom;

  @ApiModelProperty(required = true, example = "2099-01-01T15:00:00.000")
  private String dtbidPerDatoTom;

  @ApiModelProperty(required = false, example = "1")
  private String sbidragId;

  @ApiModelProperty(required = false, example = "MP")
  private String sSoknType;

}
