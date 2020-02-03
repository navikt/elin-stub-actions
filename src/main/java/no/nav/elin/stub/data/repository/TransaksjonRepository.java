package no.nav.elin.stub.data.repository;

import org.springframework.data.repository.CrudRepository;
import no.nav.elin.stub.data.dao.TransaksjonDo;

public interface TransaksjonRepository extends CrudRepository<TransaksjonDo, Integer> {


}
