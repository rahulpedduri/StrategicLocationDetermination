/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import beans.*;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;
import org.apache.catalina.Session;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import util.Census;
/**
 *
 * @author san
 */
public class handlerequests extends HttpServlet {

    
private HttpSession session=null;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        session = (HttpSession) request.getSession();
        String requestType=request.getParameter("requestType");
        if(requestType.equals("states")){
        ArrayList<State> state;
        state=(ArrayList<State>)Census.getStates();
        response.setContentType("application/json");
        PrintWriter out=response.getWriter();
        JSONObject obj=new JSONObject();
        JSONArray arr;
        for(int i=0;i<state.size();i++)
        {
            arr = new JSONArray();
            arr.add(0,state.get(i).getCode());
            arr.add(1,state.get(i).getName());
            obj.put(i,arr);
        }
        out.println(obj);
       out.close();
        }
        
        else
        {
        String state=request.getParameter("place");
        ArrayList<Place> place;
        place=(ArrayList<Place>)Census.getPlaces(state);
        response.setContentType("application/json");
        PrintWriter out=response.getWriter();
        JSONObject obj=new JSONObject();
        JSONArray arr;
        for(int i=0;i<place.size();i++)
        {
            arr = new JSONArray();
            arr.add(0,place.get(i).getCode());
            arr.add(1,place.get(i).getName());
            obj.put(i,arr);
        }
        out.println(obj);
       out.close();
        }
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
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
