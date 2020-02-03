package no.nav.elin.stub.data.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import no.nav.elin.stub.data.dao.RolleDo;

public interface RolleRepository extends CrudRepository<RolleDo, Integer> {

  @Query("select r from RolleDo r inner join r.fnrDo f where f.fnr = :fnr")
  RolleDo hentRolleByFnr(String fnr);

}
