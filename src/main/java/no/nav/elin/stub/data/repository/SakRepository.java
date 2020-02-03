package no.nav.elin.stub.data.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import no.nav.elin.stub.data.dao.SakDo;

public interface SakRepository extends CrudRepository<SakDo, Integer> {

  @Query("select s from SakDo s where s.saksnr = :saksnr")
  SakDo findBySaksnr(@Param("saksnr") int saksnr);
}
