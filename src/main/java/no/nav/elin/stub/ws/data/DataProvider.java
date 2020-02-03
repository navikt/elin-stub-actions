package no.nav.elin.stub.ws.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.joda.time.LocalDateTime;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;
import no.nav.elin.stub.data.dao.PersonDo;
import no.nav.elin.stub.data.dao.SakDo;
import no.nav.elin.stub.data.dao.TransaksjonDo;
import no.nav.elin.stub.data.service.DataManagementService;
import no.nav.elin.stub.ws.data.mapper.DataMapper;
import no.spn.rtv.webservices.bisysreskws.ArrayOfCbidragSak;
import no.spn.rtv.webservices.bisysreskws.ArrayOfCgjeldBetOrdn;
import no.spn.rtv.webservices.bisysreskws.ArrayOfCinnkrevAktivHist;
import no.spn.rtv.webservices.bisysreskws.ArrayOfCnyBetOrdn;
import no.spn.rtv.webservices.bisysreskws.ArrayOfCpersOrg;
import no.spn.rtv.webservices.bisysreskws.ArrayOfCtransaksjon;
import no.spn.rtv.webservices.bisysreskws.CbidragSak;
import no.spn.rtv.webservices.bisysreskws.CpersOrg;
import no.spn.rtv.webservices.bisysreskws.Ctransaksjon;
import no.spn.rtv.webservices.bisysreskws.CutParameter;

@Slf4j
@Service
public class DataProvider {

  @Autowired
  private DataManagementService dataManagementService;

  private DataMapper dataMapper = Mappers.getMapper(DataMapper.class);

  public XMLGregorianCalendar localDateTimeToGregorian(LocalDateTime postDate) {
    XMLGregorianCalendar gregCal = null;
    try {
      gregCal = DatatypeFactory.newInstance().newXMLGregorianCalendar(postDate.toString());
    } catch (DatatypeConfigurationException e) {
      log.error("Datoformateringsfeil", e);
    }

    return gregCal;
  }

  @Transactional
  public CutParameter hentCutParameterForMotpost(int sTransID, XMLGregorianCalendar dtdatoFom,
      XMLGregorianCalendar dtdatoTom) {

    CutParameter cut = initCutParameter(dtdatoFom, dtdatoTom);

    TransaksjonDo transaksjonDo = dataManagementService.hentTransaksjon(sTransID);
    SakDo sakDo = transaksjonDo.getSakDo();
    CbidragSak csak = dataMapper.toCbidragSak(sakDo);

    cut.getColCbidragSak().getCbidragSak().add(csak);

    List<TransaksjonDo> motposter = finnMotposter(transaksjonDo);

    cut.getColCtransaksjon().getCtransaksjon().addAll(dataMapper.toCtransaksjon(motposter));

    return cut;
  }

  @Transactional
  public CutParameter hentCutParameterForPerson(String fnr, XMLGregorianCalendar dtdatoFom,
      XMLGregorianCalendar dtdatoTom) {

    CutParameter cut = initCutParameter(dtdatoFom, dtdatoTom);

    List<SakDo> sakerPerson = dataManagementService.hentSakerForPerson(fnr);

    if (sakerPerson.isEmpty()) {
      return cut;
    }

    List<CbidragSak> csaker = dataMapper.toCbidragSaker(sakerPerson);
    cut.getColCbidragSak().getCbidragSak().addAll(csaker);

    List<TransaksjonDo> transaksjonerPerson = new ArrayList<>();

    for (SakDo sak : sakerPerson) {
      transaksjonerPerson.addAll(new ArrayList<>(sak.getTransaksjonDos()));
    }

    List<Ctransaksjon> ctranser = dataMapper.toCtransaksjon(transaksjonerPerson);
    cut.getColCtransaksjon().getCtransaksjon().addAll(ctranser);

    CpersOrg person = new CpersOrg();

    PersonDo personDo = dataManagementService.hentPerson(fnr);

    if (personDo != null) {
      person.setDtotLopBidrag(personDo.getDtotLopBidrag());
      person.setDgjeldGebyrIlagtTI(personDo.getDgjeldGebyrIlagtTI());
      person.setDinnbetBelopUford(personDo.getDinnbetBelopUford());
      person.setFakturaMaateBeskr(personDo.getFakturaMaateBeskr());
      person.setSisteAktivitetBeskr(personDo.getSisteAktivitetBeskr());
      person.setStatusInnkrevBeskr(personDo.getStatusInnkrevBeskr());
    }

    person.setSfnr(fnr);

    cut.getColCpersOrg().getCpersOrg().add(person);

    return cut;
  }

  @Transactional
  public CutParameter hentCutParameterForSak(int saksnr, XMLGregorianCalendar dtdatoFom,
      XMLGregorianCalendar dtdatoTom) {

    CutParameter cut = initCutParameter(dtdatoFom, dtdatoTom);

    Optional<SakDo> sakDo = dataManagementService.hentSak(saksnr);

    if (sakDo.isPresent()) {
      CbidragSak csak = dataMapper.toCbidragSak(sakDo.get());
      cut.getColCbidragSak().getCbidragSak().add(csak);
      List<TransaksjonDo> dos = new ArrayList<>(sakDo.get().getTransaksjonDos());
      List<Ctransaksjon> ctranser = dataMapper.toCtransaksjon(dos);
      cut.getColCtransaksjon().getCtransaksjon().addAll(ctranser);
    }

    return cut;
  }

  private List<TransaksjonDo> finnMotposter(TransaksjonDo referanse) {
    List<TransaksjonDo> motposter = new ArrayList<>();

    for (TransaksjonDo transaksjon : referanse.getSakDo().getTransaksjonDos()) {
      if (transaksjon.isMotpost()) {
        motposter.add(transaksjon);
      }
    }

    return motposter;
  }

  private CutParameter initCutParameter(XMLGregorianCalendar dtdatoFom,
      XMLGregorianCalendar dtdatoTom) {

    CutParameter ut = new CutParameter();
    ut.setColCbidragSak(new ArrayOfCbidragSak());
    ut.setColCgjeldBetOrdn(new ArrayOfCgjeldBetOrdn());
    ut.setColCinnkrevAktivHist(new ArrayOfCinnkrevAktivHist());
    ut.setColCnyBetOrdn(new ArrayOfCnyBetOrdn());
    ut.setColCpersOrg(new ArrayOfCpersOrg());
    ut.setColCtransaksjon(new ArrayOfCtransaksjon());
    ut.setSjournalpost(null);

    return ut;

  }

}
