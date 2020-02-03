package no.nav.elin.stub.data.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import no.nav.elin.stub.data.dao.BarnDo;
import no.nav.elin.stub.data.dao.FnrDo;
import no.nav.elin.stub.data.dao.RolleDo;
import no.nav.elin.stub.data.dao.SakDo;
import no.nav.elin.stub.data.dao.TransaksjonDo;
import no.nav.elin.stub.data.dto.BarnDto;
import no.nav.elin.stub.data.dto.SakDto;
import no.nav.elin.stub.data.dto.TransaksjonDto;

@Mapper
public abstract class SakMapper {

  private TransaksjonMapper transaksjonMapper = Mappers.getMapper(TransaksjonMapper.class);

  public SakDo toSakDo(SakDto sakDto) {

    SakDo sakDo = new SakDo();
    sakDo.setSaksnr(sakDto.getSaksnr());
    sakDo.setDbmGjeldFastsGebyr(sakDto.getDbmGjeldFastsGebyr());
    sakDo.setDbmGjeldRest(sakDto.getDbmGjeldRest());
    sakDo.setDbpGjeldFastGebyr(sakDto.getDbpGjeldFastGebyr());

    FnrDo fnrDoBm = new FnrDo();
    fnrDoBm.setFnr(sakDto.getFnrBm());
    RolleDo rolleDoBm = new RolleDo();
    rolleDoBm.setFnrDo(fnrDoBm);
    rolleDoBm.setRolleType(RolleDo.rolleType.BM.toString());
    rolleDoBm.getSakDosBm().add(sakDo);
    sakDo.setRolleDoBm(rolleDoBm);

    FnrDo fnrDoBp = new FnrDo();
    fnrDoBp.setFnr(sakDto.getFnrBp());
    RolleDo rolleDoBp = new RolleDo();
    rolleDoBp.setFnrDo(fnrDoBp);
    rolleDoBp.setRolleType(RolleDo.rolleType.BP.toString());
    rolleDoBp.getSakDosBp().add(sakDo);
    sakDo.setRolleDoBp(rolleDoBp);

    for (BarnDto barnDto : sakDto.getBarn()) {
      FnrDo fnrDoBa = new FnrDo();
      fnrDoBa.setFnr(barnDto.getSfnr());
      RolleDo rolleDoBa = new RolleDo();
      rolleDoBa.setFnrDo(fnrDoBa);
      rolleDoBa.setRolleType(RolleDo.rolleType.BA.toString());

      Set<SakDo> saker = new HashSet<>();
      saker.add(sakDo);
      BarnDo barnDo = toBarnDo(barnDto, rolleDoBa);
      barnDo.setSakDo(sakDo);
      sakDo.getBarnDos().add(barnDo);
    }

    List<TransaksjonDto> transaksjonDtos = new ArrayList<>(sakDto.getTransaksjoner());

    for (TransaksjonDto transaksjonDto : transaksjonDtos) {
      TransaksjonDo transaksjonDo = transaksjonMapper.toTransaksjonDo(transaksjonDto, sakDo);
      sakDo.getTransaksjonDos().add(transaksjonDo);

    }

    return sakDo;
  }

  @Mapping(target = "rolle", source = "rolle")
  public abstract BarnDo toBarnDo(BarnDto barnDto, RolleDo rolle);

  public abstract List<SakDo> toSakDo(Collection<SakDto> dtos);

}
