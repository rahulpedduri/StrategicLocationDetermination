/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import beans.Place;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Phani Rahul
 */
public class Detail extends HttpServlet {

    private HttpSession session = null;

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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        session = (HttpSession) request.getSession();
        ArrayList<Place> selected = (ArrayList<Place>) session.getAttribute("selected");
        if (selected == null) {
            selected = new ArrayList<Place>();
            session.setAttribute("selected", selected);
        }
        try {
            /* TODO output your page here. You may use following sample code. */
            if (request.getParameter("name") != null) {
                String name = (String) request.getParameter("name");
                Place place = null;
                for (Place p : selected) {
                    if (p.getCode().equalsIgnoreCase(name) || p.getName().equalsIgnoreCase(name)) {
                        place = p;
                    }
                }

                if (place != null) {
                    session.setAttribute("detail", place);
                    response.sendRedirect("detail.jsp");
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
