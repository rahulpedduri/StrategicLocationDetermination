/*
 * Author : Jagan Vujjini
 */

package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import beans.Competitors;

public class CompetitorsFromPlacesAPI {

    
	private static String mylat;
	private static String mylng;
    
    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, Exception {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = (JSONObject) new JSONParser().parse(jsonText);

//     
            return json;
        } finally {
            is.close();
        }
    }

    /*public static void main(String[] args) throws IOException {
        try {
            getCompetitors("NC");
        } catch (Exception ex) {
            Logger.getLogger(Census.class.getName()).log(Level.SEVERE, null, ex);
        }

    }*/

    public static List<Competitors> getCompetitors(String lat, String lng) {
        
    	mylat = lat;
    	mylng = lng;
    	
    	final String URL = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?"+
				"key=AIzaSyD71X-H1EzIK3fKgk_pIQ9QfBS6-OVlURo&location=" + 
				mylat + "," + mylng + "&radius=200&sensor=false&types=clothing_store";
    	
        ArrayList<Competitors> places = new ArrayList<Competitors>();
        try {

            JSONObject json = readJsonFromUrl(URL);
            JSONArray list = (JSONArray) json.get("results");
            Iterator<JSONObject> listItr = list.iterator();

            while (listItr.hasNext()) {
                JSONObject data = listItr.next();
                String name = (String) data.get("name");
                String address = (String) data.get("vicinity");
                JSONObject geometry = (JSONObject) data.get("geometry");
                JSONObject location = (JSONObject) geometry.get("location");
                String latitude = (String) location.get("lat");
                String longitude = (String) location.get("lng");
                places.add(new Competitors(name, address, latitude, longitude));
            }

        } catch (IOException ex) {
            Logger.getLogger(Census.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Census.class.getName()).log(Level.SEVERE, null, ex);
        }
        return places;
    }
}