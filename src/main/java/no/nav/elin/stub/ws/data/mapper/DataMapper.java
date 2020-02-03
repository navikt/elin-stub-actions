package no.nav.elin.stub.ws.data.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import no.nav.elin.stub.data.dao.BarnDo;
import no.nav.elin.stub.data.dao.SakDo;
import no.nav.elin.stub.data.dao.TransaksjonDo;
import no.nav.elin.stub.data.dto.Barn;
import no.nav.elin.stub.data.dto.Person;
import no.spn.rtv.webservices.bisysreskws.ArrayOfCbarnISak;
import no.spn.rtv.webservices.bisysreskws.CbarnISak;
import no.spn.rtv.webservices.bisysreskws.CbidragSak;
import no.spn.rtv.webservices.bisysreskws.CpersOrg;
import no.spn.rtv.webservices.bisysreskws.Ctransaksjon;

@Mapper
public abstract class DataMapper {

  public CbidragSak toCbidragSak(SakDo sakDo) {
    CbidragSak csak = new CbidragSak();
    csak.setDbmGjeldFastsGebyr(sakDo.getDbmGjeldFastsGebyr());
    csak.setDbmGjeldRest(sakDo.getDbmGjeldRest());
    csak.setDbpGjeldFastGebyr(sakDo.getDbpGjeldFastGebyr());
    csak.setNBidragsSaksnr(sakDo.getSaksnr());

    csak.setColCbarnISak(new ArrayOfCbarnISak());

    for (BarnDo barnISak : sakDo.getBarnDos()) {
      CbarnISak cBarnISak = barnToCbarnISak(barnISak);
      cBarnISak.setSFnr(barnISak.getRolleDo().getFnrDo().getFnr());
      csak.getColCbarnISak().getCbarnISak().add(cBarnISak);
    }

    return csak;
  }

  protected abstract CbarnISak barnToCbarnISak(Barn barn);

  public abstract List<CbidragSak> toCbidragSaker(List<SakDo> sakDos);

  @Mapping(target = "stransID", source = "transaksjonDo.id")
  @Mapping(target = "nbidragSaksnr", source = "transaksjonDo.sakDo.saksnr")
  public abstract Ctransaksjon toCtransaksjon(TransaksjonDo transaksjonDo);

  public abstract List<Ctransaksjon> toCtransaksjon(List<TransaksjonDo> transaksjonDos);

  protected abstract CpersOrg personToCpersOrg(Person person);

}
