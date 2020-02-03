package no.nav.elin.stub.data.dto;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SakDto {

  @ApiModelProperty(required = true, example = "1900000")
  private int saksnr;

  @ApiModelProperty(required = true, example = "5000")
  private BigDecimal dbmGjeldFastsGebyr;

  @ApiModelProperty(required = true, example = "0")
  private BigDecimal dbmGjeldRest;

  @ApiModelProperty(required = true, example = "8000")
  private BigDecimal dbpGjeldFastGebyr;

  @ApiModelProperty(required = true, example = "02077727550")
  private String fnrBp;

  @ApiModelProperty(required = true, example = "14117777430")
  private String fnrBm;

  private Set<BarnDto> barn;

  private Set<TransaksjonDto> transaksjoner = new HashSet<>();

}
