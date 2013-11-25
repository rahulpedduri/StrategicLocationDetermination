/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import beans.SelectedLocationField;
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
    
    private HttpSession session=null;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        ArrayList<SelectedLocationField> list=null;
        
         session = (HttpSession) request.getSession();
        
        try {
            /* TODO output your page here. You may use following sample code. */
            if(request.getParameter("field_json")!=null){
                try {
                    JSONArray jsonArray=(JSONArray) new JSONParser().parse((String)request.getParameter("field_json"));
                    list= new ArrayList<SelectedLocationField>();
                    for(Object json : jsonArray) {
                        String state = (String)((JSONObject)json).get("state");
                        String place = (String)((JSONObject)json).get("place");
                        list.add(new SelectedLocationField(state, place));
                    }
                        

                } catch (ParseException ex) {
                    Logger.getLogger(ProcessInput.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }if(request.getParameter("maps_json")!=null){
                
                 try {
                    JSONArray jsonArray=(JSONArray) new JSONParser().parse((String)request.getParameter("maps_json"));
                    list= new ArrayList<SelectedLocationField>();
                    for(Object json : jsonArray) {
                        String latitude = (String)((JSONObject)json).get("ob");
                        String longitude = (String)((JSONObject)json).get("pb");
                        list.add(new SelectedLocationField(latitude, longitude));
                    }
                        

                } catch (ParseException ex) {
                    Logger.getLogger(ProcessInput.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
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
        processRequest(request, response);
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
        processRequest(request, response);
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
