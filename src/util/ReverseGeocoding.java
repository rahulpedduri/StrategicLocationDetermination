/*
*Author : Jagan Vujjini
*/

package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import beans.SelectedLocationMap;
import java.util.Iterator;

public class ReverseGeocoding {
	
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

            return json;
        } finally {
            is.close();
        }
    }

	public static String getPlace(SelectedLocationMap map) throws IOException, Exception {
		String url = "http://maps.googleapis.com/maps/api/geocode/json?" + 
				 "latlng=" + map.getLatitude() + "," + map.getLongitude() + "&sensor=false";
        JSONObject json = readJsonFromUrl(url);
        
        JSONArray results = (JSONArray) json.get("results");
        JSONObject result = (JSONObject) results.get(0);
        JSONArray address = (JSONArray) result.get("address_components");
        String place = "";
        
        Iterator<JSONObject> addressComps = address.iterator();
        
        while(addressComps.hasNext()){
            JSONObject object = addressComps.next();
            if(object.get("types") instanceof JSONArray){
               JSONArray array = (JSONArray)object.get("types");
               String value = (String) array.get(0);
               if(value.equals("administrative_area_level_3") || value.equals("locality")) {
                   place = (String)object.get("short_name");
               }
            }
            
        }
        
        return place;
        
	}
	
	public static String getState(SelectedLocationMap map) throws IOException, Exception {
		
		String url = "http://maps.googleapis.com/maps/api/geocode/json?" + 
				 "latlng=" + map.getLatitude() + "," + map.getLongitude() + "&sensor=false";
		
        JSONObject json = readJsonFromUrl(url);
        String state = "";
        JSONArray results = (JSONArray) json.get("results");
        JSONObject result = (JSONObject) results.get(0);
        JSONArray address = (JSONArray) result.get("address_components");
        Iterator<JSONObject> addressComps = address.iterator();
        
        while(addressComps.hasNext()){
            JSONObject object = addressComps.next();
                if(object.get("types") instanceof JSONArray){
                                      
               JSONArray array = (JSONArray)object.get("types");
               String value = (String) array.get(0);
               if(value.equals("administrative_area_level_1")) {
                   state = (String)object.get("short_name");
                   break;
               }
            }
            
        }
        
        return state;
        
	}

}
