package no.nav.elin.stub.data.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import no.nav.elin.stub.data.dao.PersonDo;
import no.nav.elin.stub.data.dao.RolleDo;
import no.nav.elin.stub.data.dto.Person;

@Mapper
public interface PersonMapper {

  @Mapping(target = "rollePerson", source = "rollePerson")
  PersonDo toPersonDo(Person person, RolleDo rollePerson);

}
