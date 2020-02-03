package no.nav.elin.stub.data.dto;

import java.math.BigDecimal;
import javax.persistence.MappedSuperclass;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@MappedSuperclass
public class Person {

  @ApiModelProperty(required = true, example = "15000")
  private BigDecimal dtotLopBidrag;

  @ApiModelProperty(required = true, example = "10000")
  private BigDecimal dgjeldGebyrIlagtTI;

  @ApiModelProperty(required = true, example = "50000")
  private BigDecimal dinnbetBelopUford;

  @ApiModelProperty(required = false, example = "Status innkrevingsbeskrivelse")
  private String statusInnkrevBeskr;

  @ApiModelProperty(required = false, example = "Autogiro")
  private String fakturaMaateBeskr;

  @ApiModelProperty(required = false, example = "Innbetaling")
  private String sisteAktivitetBeskr;

}
