package no.nav.elin.stub.data.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import no.nav.elin.stub.data.dao.PersonDo;

public interface PersonRepository extends CrudRepository<PersonDo, Integer> {

  @Query("select p from PersonDo p inner join p.rolleDoPerson r inner join r.fnrDo f "
      + "where f.fnr = :fnr")
  PersonDo hentPersonByFnr(String fnr);

}
