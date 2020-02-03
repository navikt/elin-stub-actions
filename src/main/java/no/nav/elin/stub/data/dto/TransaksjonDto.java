package no.nav.elin.stub.data.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransaksjonDto extends Transaksjon {

  @ApiModelProperty(required = false, example = "02077727550")
  private String stransKildeFOnr;

  @ApiModelProperty(required = false, example = "14117777430")
  private String stransMottakerFOnr;

  @ApiModelProperty(required = true, example = "05101407448")
  private String sbarnFnr;

}
