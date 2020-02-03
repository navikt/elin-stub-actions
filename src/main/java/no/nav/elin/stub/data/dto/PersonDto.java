package no.nav.elin.stub.data.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonDto extends Person {

  @ApiModelProperty(required = true, example = "02077727550")
  private String sfnr;

}
