package net.andreu.LligaFutbol;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Processar extends DefaultHandler{

	boolean llistatEquips = false;
	boolean nom = false;
	boolean partit = false;
	boolean equip = false;
	boolean local = false;
	boolean visitant = false;
	boolean resultat = false;

	String eLocal, eVisitant;
	int golsLocal, golsVisitant;

	private List<Equip> clubs = new ArrayList<Equip>();

	public void startElement(String uri, String localName, String qName,
			Attributes attributes) {

		switch (qName) {
		case "llista-equips":
			llistatEquips = true;
			break;
		case "nom":
			nom = true;
			break;
		case "partit":
			partit = true;
			break;
		case "equip":
			equip = true;
			if (attributes.getValue("juga").equals("local")) {
				local = true;
			} else {
				visitant = true;
			}
			break;
		case "resultat":
			resultat = true;
			break;
		}
	}

	public void characters(char[] ch, int start, int length) {
		if (llistatEquips && nom) {
			clubs.add(new Equip(new String(ch, start, length)));
		}
		if (partit) {
			if (equip) {
				if (local) {
					if (nom) {
						eLocal = new String(ch, start, length);
					} else if (resultat) {
						golsLocal = Integer.parseInt(new String(ch, start, length));
					}
				} else if (visitant) {
					if (nom) {
						eVisitant = new String(ch, start, length);
					} else if (resultat) {
						golsVisitant = Integer.parseInt(new String(ch, start, length));
					}		
				}
			}
		}
	}

	public void endElement(String uri, String localName, String qName) throws SAXException {

		switch (qName) {
		case "llista-equips":
			llistatEquips = false;
			break;
		case "nom":
			nom = false;
			break;
		case "partit":
			partit = false;
			calcularResultat();
			break;
		case "equip":
			equip = false;
			local = false;
			visitant = false;
			break;
		case "resultat":
			resultat = false;
			break;
		}

	}

	private void calcularResultat() {
		
		for(Equip e : clubs){
			if(e.getNom().equals(eLocal)){
				e.setResultat(golsLocal, golsVisitant);
			}else if(e.getNom().equals(eVisitant)){
				e.setResultat(golsVisitant, golsLocal);
			}
		}
	}

	public List<Equip> getClubs() {
		Collections.sort(clubs, new ComparaEquips());
		return clubs;
	}

	public void endDocument() {
		
	}
}
