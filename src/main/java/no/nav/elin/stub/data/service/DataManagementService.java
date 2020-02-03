package no.nav.elin.stub.data.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.transaction.Transactional;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import no.nav.elin.stub.data.dao.BarnDo;
import no.nav.elin.stub.data.dao.PersonDo;
import no.nav.elin.stub.data.dao.RolleDo;
import no.nav.elin.stub.data.dao.SakDo;
import no.nav.elin.stub.data.dao.TransaksjonDo;
import no.nav.elin.stub.data.dto.PersonDto;
import no.nav.elin.stub.data.dto.SakDto;
import no.nav.elin.stub.data.exception.MissingRolleException;
import no.nav.elin.stub.data.mapper.PersonMapper;
import no.nav.elin.stub.data.mapper.SakMapper;
import no.nav.elin.stub.data.repository.BarnRepository;
import no.nav.elin.stub.data.repository.PersonRepository;
import no.nav.elin.stub.data.repository.RolleRepository;
import no.nav.elin.stub.data.repository.SakRepository;
import no.nav.elin.stub.data.repository.TransaksjonRepository;

@Service
public class DataManagementService {

  @Autowired
  private BarnRepository barnRepo;

  @Autowired
  private PersonRepository personRepo;

  @Autowired
  private RolleRepository rolleRepo;

  @Autowired
  private SakRepository sakRepo;

  @Autowired
  private TransaksjonRepository transaksjonRepo;

  private SakMapper sakMapper = Mappers.getMapper(SakMapper.class);

  private PersonMapper personMapper = Mappers.getMapper(PersonMapper.class);

  @Transactional
  public void lagreSaker(List<SakDto> sakDtos) {
    List<SakDo> saker = sakMapper.toSakDo(sakDtos);
    sakRepo.saveAll(saker);
  }

  @Transactional
  public void lagrePersoner(List<PersonDto> personDtos) {
    for (PersonDto dto : personDtos) {
      RolleDo rolle = rolleRepo.hentRolleByFnr(dto.getSfnr());
      if (rolle != null) {
        PersonDo personDo = personMapper.toPersonDo(dto, rolle);
        personRepo.save(personDo);
      } else {
        throw new MissingRolleException();
      }
    }
  }

  public TransaksjonDo hentTransaksjon(int transaksjonId) {
    return transaksjonRepo.findById(transaksjonId).get();
  }

  public Optional<SakDo> hentSak(int saksnr) {
    return Optional.ofNullable(sakRepo.findBySaksnr(saksnr));
  }

  public BarnDo hentBarn(int fnrBarn) {
    return barnRepo.findById(fnrBarn).get();
  }

  @Transactional
  public List<SakDo> hentSakerForPerson(String fnr) {
    RolleDo rolle = rolleRepo.hentRolleByFnr(fnr);

    List<SakDo> alleSaker = new ArrayList<>();

    if (rolle == null) {
      return alleSaker;
    }

    if (rolle.getSakDosBp() != null) {
      List<SakDo> sakerBp = new ArrayList<>(rolle.getSakDosBp());
      alleSaker.addAll(sakerBp);
    }

    if (rolle.getSakDosBm() != null) {
      List<SakDo> sakerBm = new ArrayList<>(rolle.getSakDosBm());
      alleSaker.addAll(sakerBm);
    }

    if (rolle.getBarnDos() != null) {
      Set<SakDo> unikeSakerBa = new HashSet<SakDo>();

      for (BarnDo barn : rolle.getBarnDos()) {
        unikeSakerBa.add(barn.getSakDo());

      }
      alleSaker.addAll(unikeSakerBa);
    }

    return alleSaker;
  }

  public PersonDo hentPerson(String fnr) {
    return personRepo.hentPersonByFnr(fnr);
  }

}
