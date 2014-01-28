package servlet_package;

import db_package.DBmanager;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utility_package.Functions;


@WebServlet(name = "ServletCambioPassword_1", urlPatterns = {"/ServletCambioPassword_1"})
public class ServletCambioPassword extends HttpServlet {
    
    private DBmanager manager;
    String vecchiaPassword;
    String nuovaPassword;
    String ripetiPassword;
    
    @Override
    public void init() throws ServletException {
        // inizializza il DBManager dagli attributi di Application
        this.manager = (DBmanager)super.getServletContext().getAttribute("dbmanager");
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException{
        
        vecchiaPassword = request.getAttribute("old_password").toString();
        nuovaPassword = request.getAttribute("password").toString();
        ripetiPassword = request.getAttribute("pass2").toString();
        
        if(!manager.getPassword(Functions.getUserName(request)).equals(vecchiaPassword)){
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        else{
            manager.setPassword(Functions.getUserName(request), nuovaPassword);
            ServletContext sc = getServletContext(); 
            RequestDispatcher rd = sc.getRequestDispatcher("/forumJSP/DatiUtente.jsp"); 
            rd.forward(request,response);
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ServletCambioPassword.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ServletCambioPassword.class.getName()).log(Level.SEVERE, null, ex);
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
