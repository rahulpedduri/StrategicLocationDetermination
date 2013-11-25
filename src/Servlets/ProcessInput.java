/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import beans.Place;
import beans.SelectedLocationField;
import beans.SelectedLocationMap;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import util.Goodness;
import util.PricesFromTruliaAPI;
import util.ReverseGeocoding;

/**
 *
 * @author Phani Rahul
 */
public class ProcessInput extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private HttpSession session = null;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        ArrayList<SelectedLocationField> list = null;
        ArrayList<SelectedLocationMap> maps = null;

        session = (HttpSession) request.getSession();

        ArrayList<Place> selected = (ArrayList<Place>) session.getAttribute("selected");
        if (selected == null) {
            selected = new ArrayList<Place>();
            session.setAttribute("selected", selected);
        }
        ArrayList<Place> dump = (ArrayList<Place>) session.getAttribute("dump");
        if (dump == null) {
            dump = new ArrayList<Place>();
            session.setAttribute("dump", dump);
        }

        try {
            /* TODO output your page here. You may use following sample code. */
            if (request.getParameter("field_json") != null) {
                try {
                    JSONArray jsonArray = (JSONArray) new JSONParser().parse((String) request.getParameter("field_json"));
                    list = new ArrayList<SelectedLocationField>();
                    for (Object json : jsonArray) {
                        String state = (String) ((JSONObject) json).get("state");
                        String place = (String) ((JSONObject) json).get("place");
                        list.add(new SelectedLocationField(state, place));

                        //looking for it in dump
                        for (Place p : dump) {
                            if (p.getCode().equalsIgnoreCase(place)) {
                                if (!selected.contains(p)) {
                                    selected.add(p);
                                }
                            }
                        }
                    }


                } catch (ParseException ex) {
                    Logger.getLogger(ProcessInput.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            if (request.getParameter("maps_json") != null) {

                try {
                    JSONArray jsonArray = (JSONArray) new JSONParser().parse((String) request.getParameter("maps_json"));
                    maps = new ArrayList<SelectedLocationMap>();
                    for (Object json : jsonArray) {
                        String latitude = (String) ((JSONObject) json).get("ob");
                        String longitude = (String) ((JSONObject) json).get("pb");
                        maps.add(new SelectedLocationMap(latitude, longitude));
                    }


                } catch (ParseException ex) {
                    Logger.getLogger(ProcessInput.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                ArrayList<Place> places = (ArrayList<Place>) session.getAttribute("selected");
                
                for (Place p : places) {
                	
                	String stateName  = ReverseGeocoding.getState(new SelectedLocationMap(p.getLatitude(), p.getLongitude()));
                	p.setCost(PricesFromTruliaAPI.getPrice(p.getCode(), stateName));
                
                }

                Goodness goodness=new Goodness(places);
                goodness.computeCustomerIndicator();
                goodness.computeCompetitorIndicator();
                places= goodness.computeRealEstateIndicator();
                session.setAttribute("selected", places);
                
            }
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
			processRequest(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
			processRequest(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
