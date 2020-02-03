package no.nav.elin.stub.data.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BarnDto extends Barn {

  @ApiModelProperty(required = true, example = "05101407448")
  private String sfnr;

}
