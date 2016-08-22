package Handler;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

import Bean.Model;


/**
 * Created by parth on 7/1/2016.
 */
public class XmlHandler extends DefaultHandler {

    Boolean currentElement = false;
    String currentValue = "";
    Model item = null;
    private ArrayList<Model> itemsList = new ArrayList<Model>();


    public ArrayList<Model> getItemsList() {
        return itemsList;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);

        if (currentElement) {
            currentValue = currentValue + new String(ch, start, length);
        }
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);

        currentElement = true;
        currentValue = "";

        if (localName.equalsIgnoreCase("table")) {
            item = new Model();
        } else if (localName.equalsIgnoreCase("record")) {
            item = new Model();
        }else if (localName.equalsIgnoreCase("ROW")) {
            item = new Model();
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);

        currentElement = false;


        if (localName.equalsIgnoreCase("country_id")) {
            item.setCountry_id(currentValue);
        } else if (localName.equalsIgnoreCase("errormessage")) {
            item.setErrormessage(currentValue);
        } else if (localName.equalsIgnoreCase("countryname")) {
            item.setCountry_name(currentValue);
        } else if (localName.equalsIgnoreCase("state_id")) {
            item.setState_id(currentValue);
        } else if (localName.equalsIgnoreCase("statename")) {
            item.setStatename(currentValue);
        } else if (localName.equalsIgnoreCase("shade")) {
            item.setShade(currentValue);
        } else if (localName.equalsIgnoreCase("tableinclusion")) {
            item.setTableinclusion(currentValue);
        } else if (localName.equalsIgnoreCase("sideinclusion")) {
            item.setSideinclusion(currentValue);
        } else if (localName.equalsIgnoreCase("username")) {
            item.setUsername(currentValue);
        } else if (localName.equalsIgnoreCase("graining")) {
            item.setGraining(currentValue);
        } else if (localName.equalsIgnoreCase("availibility")) {
            item.setAvailibility(currentValue);
        } else if (localName.equalsIgnoreCase("starlength")) {
            item.setStarlength(currentValue);
        } else if (localName.equalsIgnoreCase("lowerhalve")) {
            item.setLowerhalve(currentValue);
        } else if (localName.equalsIgnoreCase("openinclusion")) {
            item.setOpeninclusion(currentValue);
        } else if (localName.equalsIgnoreCase("extrafacet")) {
            item.setExtrafacet(currentValue);
        } else if (localName.equalsIgnoreCase("natural")) {
            item.setNatural(currentValue);
        } else if (localName.equalsIgnoreCase("title")) {
            item.setTitle(currentValue);
        } else if (localName.equalsIgnoreCase("webstatus")) {
            item.setWebstatus(currentValue);
        } else if (localName.equalsIgnoreCase("stone_no")) {
            item.setStone_no(currentValue);
        } else if (localName.equalsIgnoreCase("laboratory")) {
            item.setLaboratory(currentValue);
        } else if (localName.equalsIgnoreCase("labreportno")) {
            item.setLabreportno(currentValue);
        } else if (localName.equalsIgnoreCase("shape")) {
            item.setShape(currentValue);
        } else if (localName.equalsIgnoreCase("weightincarats")) {
            item.setWeightincarats(currentValue);
        } else if (localName.equalsIgnoreCase("color")) {
            item.setColor(currentValue);
        } else if (localName.equalsIgnoreCase("clarity")) {
            item.setClarity(currentValue);
        } else if (localName.equalsIgnoreCase("cut")) {
            item.setCut(currentValue);
        } else if (localName.equalsIgnoreCase("polish")) {
            item.setPolish(currentValue);
        } else if (localName.equalsIgnoreCase("symmetry")) {
            item.setSymmetry(currentValue);
        } else if (localName.equalsIgnoreCase("fluorescenceintensity")) {
            item.setFluorescenceintensity(currentValue);
        } else if (localName.equalsIgnoreCase("fluorescencecolor")) {
            item.setFluorescencecolor(currentValue);
        } else if (localName.equalsIgnoreCase("liveraparate")) {
            item.setLiveraparate(currentValue);
        } else if (localName.equalsIgnoreCase("websitediscount")) {
            item.setWebsitediscount(currentValue);
        } else if (localName.equalsIgnoreCase("websiterate")) {
            item.setWebsiterate(currentValue);
        } else if (localName.equalsIgnoreCase("websiteamount")) {
            item.setWebsiteamount(currentValue);
        } else if (localName.equalsIgnoreCase("lddiscount")) {
            item.setLddiscount(currentValue);
        } else if (localName.equalsIgnoreCase("ldrate")) {
            item.setLdrate(currentValue);
        } else if (localName.equalsIgnoreCase("ldamount")) {
            item.setLdamount(currentValue);
        } else if (localName.equalsIgnoreCase("measurement")) {
            item.setMeasurement(currentValue);
        } else if (localName.equalsIgnoreCase("totaldepthper")) {
            item.setTotaldepthper(currentValue);
        } else if (localName.equalsIgnoreCase("locationname")) {
            item.setLocationname(currentValue);
        } else if (localName.equalsIgnoreCase("handa")) {
            item.setHanda(currentValue);
        } else if (localName.equalsIgnoreCase("blackinclusion")) {
            item.setBlackinclusion(currentValue);
        } else if (localName.equalsIgnoreCase("tinge")) {
            item.setTinge(currentValue);
        } else if (localName.equalsIgnoreCase("milky")) {
            item.setMilky(currentValue);
        } else if (localName.equalsIgnoreCase("luster")) {
            item.setLuster(currentValue);
        } else if (localName.equalsIgnoreCase("eyeclean")) {
            item.setEyeclean(currentValue);
        } else if (localName.equalsIgnoreCase("crownheight")) {
            item.setCrownheight(currentValue);
        } else if (localName.equalsIgnoreCase("crownangle")) {
            item.setCrownangle(currentValue);
        } else if (localName.equalsIgnoreCase("pavillionheight")) {
            item.setPavillionheight(currentValue);
        } else if (localName.equalsIgnoreCase("pavillionangle")) {
            item.setPavillionangle(currentValue);
        } else if (localName.equalsIgnoreCase("keytosymbols")) {
            item.setKeytosymbols(currentValue);
        } else if (localName.equalsIgnoreCase("imageexist")) {
            item.setImageexist(currentValue);
        } else if (localName.equalsIgnoreCase("certificateexist")) {
            item.setCertificateexist(currentValue);
        } else if (localName.equalsIgnoreCase("videoexist")) {
            item.setVideoexist(currentValue);
        } else if (localName.equalsIgnoreCase("imageurl")) {
            item.setImageurl(currentValue);
        } else if (localName.equalsIgnoreCase("certificateurl")) {
            item.setCertificateurl(currentValue);
        } else if (localName.equalsIgnoreCase("verifycertificate")) {
            item.setVerifycertificate(currentValue);
        } else if (localName.equalsIgnoreCase("user_id")) {
            item.setUser_id(currentValue);
        } else if (localName.equalsIgnoreCase("recordno")) {
            item.setRecordno(currentValue);
        } else if (localName.equalsIgnoreCase("pagesize")) {
            item.setPagesize(currentValue);
        } else if (localName.equalsIgnoreCase("totalpages")) {
            item.setTotalpages(currentValue);
        }else if (localName.equalsIgnoreCase("totalpage")) {
            item.setTotalpage(currentValue);
        } else if (localName.equalsIgnoreCase("laserinscription")) {
            item.setLaserinscription(currentValue);
        } else if (localName.equalsIgnoreCase("luster")) {
            item.setLuster(currentValue);
        } else if (localName.equalsIgnoreCase("eyeclean")) {
            item.setEyeclean(currentValue);
        } else if (localName.equalsIgnoreCase("crownheight")) {
            item.setCrownheight(currentValue);
        } else if (localName.equalsIgnoreCase("pavillionangle")) {
            item.setPavillionangle(currentValue);
        } else if (localName.equalsIgnoreCase("pavillionheight")) {
            item.setPavillionheight(currentValue);
        } else if (localName.equalsIgnoreCase("tablediameterper")) {
            item.setTablediameterper(currentValue);
        } else if (localName.equalsIgnoreCase("crownheight")) {
            item.setCrownheight(currentValue);
        } else if (localName.equalsIgnoreCase("totalrecord")) {
            item.setTotalrecord(currentValue);
        } else if (localName.equalsIgnoreCase("offer_id")) {
            item.setOffer_id(currentValue);
        } else if (localName.equalsIgnoreCase("pageno")) {
            item.setPageno(currentValue);
        } else if (localName.equalsIgnoreCase("offerno")) {
            item.setOfferno(currentValue);
        } else if (localName.equalsIgnoreCase("offerdate")) {
            item.setOfferdate(currentValue);
        } else if (localName.equalsIgnoreCase("offerstatus")) {
            item.setOfferstatus(currentValue);
        } else if (localName.equalsIgnoreCase("counterofferno")) {
            item.setCounterofferno(currentValue);
        } else if (localName.equalsIgnoreCase("totalstone")) {
            item.setTotalstone(currentValue);
        } else if (localName.equalsIgnoreCase("totalcarat")) {
            item.setTotalcarat(currentValue);
        } else if (localName.equalsIgnoreCase("avgraparate")) {
            item.setAvgraparate(currentValue);
        } else if (localName.equalsIgnoreCase("offeravgdiscount")) {
            item.setOfferavgdiscount(currentValue);
        } else if (localName.equalsIgnoreCase("offeravgrate")) {
            item.setOfferavgrate(currentValue);
        } else if (localName.equalsIgnoreCase("offertotalamount")) {
            item.setOffertotalamount(currentValue);
        } else if (localName.equalsIgnoreCase("STEP1")) {
            item.setSTEP1(currentValue);
        } else if (localName.equalsIgnoreCase("STEP2")) {
            item.setSTEP2(currentValue);
        } else if (localName.equalsIgnoreCase("STEP3")) {
            item.setSTEP3(currentValue);
        } else if (localName.equalsIgnoreCase("STEP4")) {
            item.setSTEP4(currentValue);
        } else if (localName.equalsIgnoreCase("STEP5")) {
            item.setSTEP5(currentValue);
        } else if (localName.equalsIgnoreCase("STEP6")) {
            item.setSTEP6(currentValue);
        } else if (localName.equalsIgnoreCase("STEP7")) {
            item.setSTEP7(currentValue);
        } else if (localName.equalsIgnoreCase("STEP8")) {
            item.setSTEP8(currentValue);
        } else if (localName.equalsIgnoreCase("STEP9")) {
            item.setSTEP9(currentValue);
        } else if (localName.equalsIgnoreCase("STEP10")) {
            item.setSTEP10(currentValue);
        } else if (localName.equalsIgnoreCase("STEP11")) {
            item.setSTEP11(currentValue);
        } else if (localName.equalsIgnoreCase("STEP12")) {
            item.setSTEP12(currentValue);
        } else if (localName.equalsIgnoreCase("STEP13")) {
            item.setSTEP13(currentValue);
        } else if (localName.equalsIgnoreCase("girdle")) {
            item.setGirdle(currentValue);
        } else if (localName.equalsIgnoreCase("culetsize")) {
            item.setCuletsize(currentValue);
        } else if (localName.equalsIgnoreCase("cavity")) {
            item.setCavity(currentValue);
        } else if (localName.equalsIgnoreCase("comment")) {
            item.setComment(currentValue);
        } else if (localName.equalsIgnoreCase("laxmicomment")) {
            item.setLaxmicomment(currentValue);
        }else if (localName.equalsIgnoreCase("shape_color")) {
            item.setShape_color(currentValue);
        }else if (localName.equalsIgnoreCase("isfirstrow")) {
            item.setIsfirstrow(currentValue);
        }else if (localName.equalsIgnoreCase("isfancy")) {
            item.setIsfancy(currentValue);
        }else if (localName.equalsIgnoreCase("column1")) {
            item.setColumn1(currentValue);
        }else if (localName.equalsIgnoreCase("column2")) {
            item.setColumn2(currentValue);
        } else if (localName.equalsIgnoreCase("record")) {
            itemsList.add(item);
        }else if (localName.equalsIgnoreCase("ROW")) {
            itemsList.add(item);
        }


    }
}
