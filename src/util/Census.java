/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import beans.Place;
import beans.State;
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

/**
 *
 * @author Phani Rahul
 */
public class Census {

    private static final String URL = "http://api.usatoday.com/open/census/";
    private static final String ALL = "loc";
    private static final String LESS = "hou";
    private static final String LEVEL = "sumlevid";
    private static final String STATE_LEVEL = "2";
    private static final String TOWN_LEVEL = "4,6";
    private static final String STATE = "keypat";
    private static final String API_KEY = "api_key=b3vtugkgn7qapq2xew2uvp8y";

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

    public static void main(String[] args) throws IOException {
        try {
//            getPlaces("NC");
            getStates();
        } catch (Exception ex) {
            Logger.getLogger(Census.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static List<State> getStates() {
        String url = URL + LESS + "?" + API_KEY + "&" + LEVEL + "=" + STATE_LEVEL;
        ArrayList<State> states = new ArrayList<State>();
        try {
            JSONObject root = readJsonFromUrl(url);
            JSONArray list = (JSONArray) root.get("response");
            Iterator<JSONObject> listItr = list.iterator();

            while (listItr.hasNext()) {
                JSONObject data = listItr.next();
                String name = (String) data.get("PlaceName");
                String code = (String) data.get("StatePostal");
                states.add(new State(name, code));
            }
           

        } catch (IOException ex) {
            Logger.getLogger(Census.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Census.class.getName()).log(Level.SEVERE, null, ex);
        }
        return states;
    }


    public static List<Place> getPlaces(String stateCode) {
        String url = URL + ALL + "?" + API_KEY + "&" + LEVEL + "=" + TOWN_LEVEL + "&" + STATE + "=" + stateCode;
        ArrayList<Place> places = new ArrayList<Place>();
        try {

            JSONObject root = readJsonFromUrl(url);
            JSONArray list = (JSONArray) root.get("response");
            Iterator<JSONObject> listItr = list.iterator();

            while (listItr.hasNext()) {
                JSONObject data = listItr.next();
                String name = (String) data.get("Placename");
                String code = (String) data.get("PlacenameFull");
                String population = (String) data.get("Pop");
                String latitude = (String) data.get("Lat");
                String longitude = (String) data.get("Long");
                String state = (String) data.get("StatePostal");
                Place p = new Place(name, code, latitude, longitude, population);
                p.setState(state);
                places.add(p);
                
            }

        } catch (IOException ex) {
            Logger.getLogger(Census.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Census.class.getName()).log(Level.SEVERE, null, ex);
        }
        return places;
    }
}
