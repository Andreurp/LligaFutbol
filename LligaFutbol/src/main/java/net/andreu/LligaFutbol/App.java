package net.andreu.LligaFutbol;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

/**
 * Hello world!
 *
 */
public class App 
{
	private static String[] FITXER = {"/lliga1.xml", "/lliga2.xml", "/lliga3.xml"};
	private static ArrayList<Processar> processos = new ArrayList<Processar>();
	
    public static void main( String[] args ) throws ParserConfigurationException, SAXException, IOException
    {
    	 SAXParserFactory fabrica=SAXParserFactory.newInstance();
         fabrica.setNamespaceAware(true);
         SAXParser parser=fabrica.newSAXParser();
         for(int i=0; i<FITXER.length; i++){
         	InputStream File = App.class.getResourceAsStream(FITXER[i]);
         	processos.add(new Processar());
         	parser.parse(File,processos.get(i));
         }
         
         for(Processar p : processos){
        	 for(Equip e : p.getClubs()){
            	 System.out.println(e.toString());
             }
        	 System.out.println("*****************************");
        	 
         }
    }		
}
