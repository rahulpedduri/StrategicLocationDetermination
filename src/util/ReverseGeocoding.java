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
        JSONObject fourth = (JSONObject) address.get(3);
            
		return (String) fourth.get("short_name");
		
	}
	
	public static String getState(SelectedLocationMap map) throws IOException, Exception {
		
		String url = "http://maps.googleapis.com/maps/api/geocode/json?" + 
				 "latlng=" + map.getLatitude() + "," + map.getLongitude() + "&sensor=false";
		
        JSONObject json = readJsonFromUrl(url);
        
        JSONArray results = (JSONArray) json.get("results");
        JSONObject result = (JSONObject) results.get(0);
        JSONArray address = (JSONArray) result.get("address_components");
        JSONObject state = (JSONObject) address.get(5);
		
        return (String) state.get("short_name");
        
	}

}