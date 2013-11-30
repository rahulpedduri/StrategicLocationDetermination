/*
*Author : Jagan Vujjini
*/

package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class RealEstate {
	
	private static final String URL = "http://api.trulia.com/webservices.php?";
	private static final String LIB = "library=";
	private static final String FUNC = "function=";
	private static final String CITY = "city=";
	private static final String STATE = "state=";
	private static final String SDATE = "startDate=2013-02-06";
	private static final String EDATE = "endDate=2013-02-07";
	private static final String API_KEY = "apikey=qnc4366ett3bxqhnq6c85hgd";
	
	private static XmlPullParserFactory factory;
	private static XmlPullParser xpp;
	
	/*private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }*/
	
	public static void processDocument(XmlPullParser xpp) throws XmlPullParserException, IOException {
        
		int eventType = xpp.getEventType();
        do {
            if(eventType == xpp.START_DOCUMENT) {
                System.out.println("Start document");
            } else if(eventType == xpp.END_DOCUMENT) {
                System.out.println("End document");
            } else if(eventType == xpp.START_TAG) {
                System.out.println("Start Tag : " + xpp.getName());
            } else if(eventType == xpp.END_TAG) {
            	System.out.println("End Tag : " + xpp.getName());
            } else if(eventType == xpp.TEXT) {
            	System.out.println("Start Tag : " + xpp.getText());
            }
            eventType = xpp.next();
        } while (eventType != xpp.END_DOCUMENT);
    }
	
	public static XmlPullParser readXMLFromUrl(String url) throws IOException, Exception {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            xpp = factory.newPullParser();            
            xpp.setInput(rd);
            return xpp;
            
        } finally {
            is.close();
        }
    }
	
	public static void getPrice(String cityName, String stateName) throws IOException, Exception {
		
		String libName = "";
		String funcName = "";
		
		String url = URL + LIB + libName + "&" + FUNC + funcName + "&" + CITY + cityName + "&" + STATE + stateName + "&" + SDATE + "&" + EDATE + "&" + API_KEY;
		
		XmlPullParser parser = readXMLFromUrl(url);
		
		processDocument(parser);
		
	}
	
	public static void main(String[] args) throws IOException, Exception {
		
		getPrice("Charlotte", "NC");
		
	}

}
