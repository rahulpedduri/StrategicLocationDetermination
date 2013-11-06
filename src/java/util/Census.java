/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import beans.State;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;


/**
 *
 * @author Phani Rahul
 */
public class Census {
    private static String readAll(Reader rd) throws IOException {
    StringBuilder sb = new StringBuilder();
    int cp;
    while ((cp = rd.read()) != -1) {
      sb.append((char) cp);
    }
    return sb.toString();
  }

  public static String readJsonFromUrl(String url) throws IOException, Exception {
    InputStream is = new URL(url).openStream();
    try {
      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
      String jsonText = readAll(rd);
//      JSONObject json = new JSONObject();
//      json.putAll(json);
      return jsonText;
    } finally {
      is.close();
    }
  }

  public static void main(String[] args) throws IOException {
        try {
            System.out.println(readJsonFromUrl("http://api.usatoday.com/open/census/hou?api_key=b3vtugkgn7qapq2xew2uvp8y&keypat=NC&sumlevid=4,6"));
        } catch (Exception ex) {
            Logger.getLogger(Census.class.getName()).log(Level.SEVERE, null, ex);
        }
  
  }
    
    public static List getStateCodes(){        
        return null;
    }
    public static List getPlaceObjects(State state){
        return null;
    }
    public static List getPlaces(State state){
        return null;
    }
}
