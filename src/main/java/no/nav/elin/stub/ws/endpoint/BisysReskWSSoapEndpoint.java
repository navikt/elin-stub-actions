package no.nav.elin.stub.ws.endpoint;

import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.BindingType;
import javax.xml.ws.soap.SOAPBinding;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import no.nav.elin.stub.ws.data.DataProvider;
import no.spn.rtv.webservices.bisysreskws.BisysReskWSSoap;
import no.spn.rtv.webservices.bisysreskws.CResknObjectHolder;
import no.spn.rtv.webservices.bisysreskws.CinnParameter;
import no.spn.rtv.webservices.bisysreskws.Cretur;
import no.spn.rtv.webservices.bisysreskws.CutParameter;
import no.spn.rtv.webservices.bisysreskws.ObjectFactory;

@BindingType(SOAPBinding.SOAP12HTTP_BINDING)
@Service
public class BisysReskWSSoapEndpoint implements BisysReskWSSoap {

  @Autowired
  private DataProvider dataProvider;

  private static final LocalDateTime DT_DATO_FOM_MIN = new LocalDateTime(1936, 1, 1, 0, 0, 0);
  private static final LocalDateTime DT_DATO_TOM_MAX = new LocalDateTime(9999, 12, 31, 23, 59, 59);


  @Override
  public CResknObjectHolder rohTransPrTransID(int naksjonsKode, String sTransID,
      XMLGregorianCalendar dtdatoFom, XMLGregorianCalendar dtdatoTom) {

    ObjectFactory factory = new ObjectFactory();

    CinnParameter inn = new CinnParameter();
    inn.setNaksjonsKode(naksjonsKode);
    inn.setStransID(sTransID);
    inn.setDtdatoFom(dtdatoFom);
    inn.setDtdatoTom(dtdatoTom);

    CutParameter ut =
        dataProvider.hentCutParameterForMotpost(Integer.parseInt(sTransID), dtdatoFom, dtdatoTom);

    Cretur retur = new Cretur();
    retur.setSbeskr("rohTransPrTransID OK");
    retur.setSkode("0");

    CResknObjectHolder response = factory.createCResknObjectHolder();
    response.setInnParameter(inn);
    response.setUtParameter(ut);
    response.setRetur(retur);

    return response;
  }

  @Override
  public CResknObjectHolder rohPrSakPrBarn(int naksjonskode, int nBidragsaksnr) {

    ObjectFactory factory = new ObjectFactory();

    CinnParameter inn = new CinnParameter();
    inn.setNaksjonsKode(naksjonskode);
    inn.setNbidragSaksnr(nBidragsaksnr);

    CutParameter ut = dataProvider.hentCutParameterForSak(nBidragsaksnr,
        dataProvider.localDateTimeToGregorian(DT_DATO_FOM_MIN),
        dataProvider.localDateTimeToGregorian(DT_DATO_TOM_MAX));

    Cretur retur = new Cretur();
    retur.setSbeskr("rohPrSakPrBarn OK");
    retur.setSkode("0");

    CResknObjectHolder response = factory.createCResknObjectHolder();
    response.setInnParameter(inn);
    response.setUtParameter(ut);
    response.setRetur(retur);

    return response;
  }

  @Override
  public CResknObjectHolder rohTransPrSak(int nAksjonskode, int nBidragsaksnr,
      XMLGregorianCalendar dtdatoFom, XMLGregorianCalendar dtdatoTom, int nmaxReturTrans) {

    ObjectFactory factory = new ObjectFactory();

    XMLGregorianCalendar today = dataProvider.localDateTimeToGregorian(LocalDateTime.now());
    XMLGregorianCalendar validFrom =
        dataProvider.localDateTimeToGregorian(LocalDateTime.now().minusDays(10));

    CinnParameter inn = new CinnParameter();
    inn.setNaksjonsKode(nAksjonskode);
    inn.setNbidragSaksnr(nBidragsaksnr);
    inn.setDtdatoFom(dtdatoFom);
    inn.setDtdatoTom(dtdatoTom);
    inn.setNmaxReturTrans(nmaxReturTrans);
    inn.setDtDagensDato(today);
    inn.setDtgjelderDatoFOM(validFrom);

    CutParameter ut = dataProvider.hentCutParameterForSak(nBidragsaksnr, dtdatoFom, dtdatoTom);

    Cretur retur = new Cretur();
    retur.setSbeskr("rohTransPrSak OK");
    retur.setSkode("0");

    CResknObjectHolder response = factory.createCResknObjectHolder();
    response.setInnParameter(inn);
    response.setUtParameter(ut);
    response.setRetur(retur);

    return response;
  }

  @Override
  public CResknObjectHolder rohKontoutdrag(int naksjonsKode, String sysId, long brevRef,
      int nbidragSaksnr, String sFNRORGNR, String srapportType, XMLGregorianCalendar dtdatoFom,
      XMLGregorianCalendar dtdatoTom, String mqReplyQueue) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public CResknObjectHolder rohRMEndreFNR(int naksjonsKode, String stypeEndring, int nbidragSaksnr,
      String sfnr, String sfnrGjelder, String sfnrNy, XMLGregorianCalendar dtdatoGjelderFOM) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public CResknObjectHolder rohPrPersPrSakPrBarn(int naksjonsKode, String sFNRORGNR) {

    CinnParameter inn = new CinnParameter();
    inn.setNaksjonsKode(naksjonsKode);
    inn.setSfOnr(sFNRORGNR);

    ObjectFactory factory = new ObjectFactory();

    CutParameter ut = dataProvider.hentCutParameterForPerson(sFNRORGNR,
        dataProvider.localDateTimeToGregorian(DT_DATO_FOM_MIN),
        dataProvider.localDateTimeToGregorian(DT_DATO_TOM_MAX));

    Cretur retur = new Cretur();
    retur.setSbeskr("rohPrPersPrSakPrBarn OK");
    retur.setSkode("0");

    CResknObjectHolder response = factory.createCResknObjectHolder();
    response.setInnParameter(inn);
    response.setUtParameter(ut);
    response.setRetur(retur);

    return response;
  }

  @Override
  public CResknObjectHolder rohInnkrevInfo(int naksjonsKode, String sFNRORGNR) {
    CinnParameter inn = new CinnParameter();
    inn.setNaksjonsKode(naksjonsKode);
    inn.setSfOnr(sFNRORGNR);

    ObjectFactory factory = new ObjectFactory();

    CutParameter ut = dataProvider.hentCutParameterForPerson(sFNRORGNR,
        dataProvider.localDateTimeToGregorian(DT_DATO_FOM_MIN),
        dataProvider.localDateTimeToGregorian(DT_DATO_TOM_MAX));

    Cretur retur = new Cretur();
    retur.setSbeskr("rohInnkrevInfo OK");
    retur.setSkode("0");

    CResknObjectHolder response = factory.createCResknObjectHolder();
    response.setInnParameter(inn);
    response.setUtParameter(ut);
    response.setRetur(retur);

    return response;
  }

  @Override
  public CResknObjectHolder rohTransPrPersPrOrg(int naksjonsKode, String sFNRORGNR,
      XMLGregorianCalendar dtdatoFom, XMLGregorianCalendar dtdatoTom, int nmaxReturTrans) {

    CinnParameter inn = new CinnParameter();
    inn.setNaksjonsKode(naksjonsKode);
    inn.setSfOnr(sFNRORGNR);

    ObjectFactory factory = new ObjectFactory();

    CutParameter ut = dataProvider.hentCutParameterForPerson(sFNRORGNR,
        dataProvider.localDateTimeToGregorian(DT_DATO_FOM_MIN),
        dataProvider.localDateTimeToGregorian(DT_DATO_TOM_MAX));

    Cretur retur = new Cretur();
    retur.setSbeskr("rohInnkrevInfo OK");
    retur.setSkode("0");

    CResknObjectHolder response = factory.createCResknObjectHolder();
    response.setInnParameter(inn);
    response.setUtParameter(ut);
    response.setRetur(retur);

    return response;
  }
}
