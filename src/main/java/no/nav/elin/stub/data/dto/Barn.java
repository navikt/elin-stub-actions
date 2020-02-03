package no.nav.elin.stub.data.dto;

import javax.persistence.MappedSuperclass;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@MappedSuperclass
public class Barn {

  @ApiModelProperty(required = true, example = "45000")
  private String dbpBelGjeldOff;

  @ApiModelProperty(required = true, example = "65000")
  private String dbpBelGjeldPrivTot;

  @ApiModelProperty(required = true, example = "165000")
  private String dbidUtbetTot;

  @ApiModelProperty(required = true, example = "23000")
  private String dforskUtbetTot;

  @ApiModelProperty(required = true, example = "40000")
  private String dbpBelGjeldPrivAndel;

  @ApiModelProperty(required = true, example = "129000")
  private String dbidUtbetAndel;

  @ApiModelProperty(required = true, example = "35000")
  private String dforskUtbetAndel;

  @ApiModelProperty(required = false, example = "2010-01-01T08:00:00.000")
  private String dtbidPerSisteDatoFom;

  @ApiModelProperty(required = false, example = "2099-12-31T16:00:00.000")
  private String dtbidPerSisteDatoTom;

  private String sstoppFord;
}
