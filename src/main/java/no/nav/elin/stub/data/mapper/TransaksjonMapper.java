package no.nav.elin.stub.data.mapper;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.mapstruct.Mapper;
import no.nav.elin.stub.data.dao.BarnDo;
import no.nav.elin.stub.data.dao.RolleDo;
import no.nav.elin.stub.data.dao.SakDo;
import no.nav.elin.stub.data.dao.TransaksjonDo;
import no.nav.elin.stub.data.dto.Transaksjon;
import no.nav.elin.stub.data.dto.TransaksjonDto;

@Mapper
public abstract class TransaksjonMapper {

  public TransaksjonDo toTransaksjonDo(TransaksjonDto transaksjonDto, SakDo sakDo) {
    TransaksjonDo transaksjonDo = transaksjonToTransaksjonDo((Transaksjon) transaksjonDto);

    Set<RolleDo> foresatteISak = new HashSet<>();
    foresatteISak.add(sakDo.getRolleDoBp());
    foresatteISak.add(sakDo.getRolleDoBm());

    transaksjonDo.setRolleDoTransaksjonBetaler(
        hentRolleForFnr(foresatteISak, transaksjonDto.getStransKildeFOnr()).get());
    transaksjonDo.setRolleDoTransaksjonMottaker(
        hentRolleForFnr(foresatteISak, transaksjonDto.getStransMottakerFOnr()).get());

    for (BarnDo barn : sakDo.getBarnDos()) {
      if (transaksjonDto.getSbarnFnr().equals(barn.getRolleDo().getFnrDo().getFnr())) {
        transaksjonDo.setRolleDoBarn(barn.getRolleDo());
      }
    }

    transaksjonDo.setSakDo(sakDo);

    return transaksjonDo;
  }

  private Optional<RolleDo> hentRolleForFnr(Set<RolleDo> roller, String fnr) {
    for (RolleDo rolle : roller) {
      if (rolle.getFnrDo().getFnr().equals(fnr)) {
        return Optional.of(rolle);
      }
    }

    return Optional.empty();
  }

  protected abstract TransaksjonDo transaksjonToTransaksjonDo(Transaksjon transaksjon);

}
