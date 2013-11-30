/*
*Author : Jagan Vujjini
*/

package util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import beans.Property;

public class PricesFromTruliaAPI {
	
	private static final String URL = "http://api.trulia.com/webservices.php?";
	private static final String LIB = "library=";
	private static final String FUNC = "function=";
	private static final String CITY = "city=";
	private static final String STATE = "state=";
	private static final String SDATE = "startDate=2013-02-06";
	private static final String EDATE = "endDate=2013-02-07";
	private static final String API_KEY = "apikey=qnc4366ett3bxqhnq6c85hgd";
	private static List<Property> propertyList = new ArrayList<Property>();
	static Property property;
	static String parserValue="";
	
	public static List<Property> readXMLFromUrl(String url) throws IOException, Exception {
        
		InputStream is = null;
		
        try {
        	
        	is = new URL(url).openStream();
        	
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            
            DefaultHandler dHandler = new DefaultHandler() {

				@Override
				public void characters(char[] ch, int start, int length) {
					// TODO Auto-generated method stub
					try {
						super.characters(ch, start, length);
						
						parserValue = new String(ch, start, length);
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						//e.printStackTrace();
						
						parserValue = "0";
						
					}
					
					
					
				}

				@Override
				public void endElement(String uri, String localName,
						String qName) throws SAXException {
					// TODO Auto-generated method stub
					super.endElement(uri, localName, qName);
					
					if(qName.equals("subcategory")){
						propertyList.add(property);
					}
					
					if (qName.equalsIgnoreCase("type")) {
					    property.setType(parserValue);
			        }
					
					if (qName.equalsIgnoreCase("averageListingPrice")) {
					    property.setAverageListingPrice(parserValue);
			        }
					
				}

				@Override
				public void startElement(String uri, String localName,
						String qName, Attributes attributes)
						throws SAXException {
					// TODO Auto-generated method stub
					super.startElement(uri, localName, qName, attributes);
					
					if (qName.equalsIgnoreCase("subcategory")) {
						property = new Property();
					}
				
				}
            	
            };
            
            saxParser.parse(is, dHandler);
            
            return propertyList;
            
        } catch(Exception e) {
        	return propertyList;
        }finally {
            is.close();
        }
    }
	
	public static int getPrice(String cityName, String stateName) throws IOException, Exception {
		
		String libName = "TruliaStats";
		String funcName = "getCityStats";
		
		String url = URL + LIB + libName + "&" + FUNC + funcName + "&" + CITY + cityName + "&" + STATE + stateName + "&" + SDATE + "&" + EDATE + "&" + API_KEY;
		
		List<Property> prices = readXMLFromUrl(url);
		
		/*for(Property p : prices){
			System.out.println(p.getType() +" : "+p.getAverageListingPrice());
		}*/
		
		return Integer.parseInt(prices.get(0).getAverageListingPrice());
		
	}
	
	/*public static void main(String[] args) throws IOException, Exception {
		getPrice("Charlotte", "NC");
	}*/

}
