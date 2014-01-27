package controller_package;

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
import javax.servlet.http.HttpSession;
import utility_package.User;
import utility_package.Variabili;


@WebServlet(name = "ControllerWeb", urlPatterns = {"/ControllerWeb"})
public class ControllerWeb extends HttpServlet {
    
    private DBmanager manager;
    
    @Override
    public void init() throws ServletException {
        // inizializza il DBManager dagli attributi di Application
        this.manager = (DBmanager)super.getServletContext().getAttribute("dbmanager");
    }

    private void forward(HttpServletRequest request, HttpServletResponse response, String page)
            throws ServletException, IOException {
        
        ServletContext sc = getServletContext(); 
        RequestDispatcher rd = sc.getRequestDispatcher(page); 
        rd.forward(request,response);
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        
        String op = request.getParameter(Variabili.OP);
        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute(Variabili.USER);
        
        if((user == null || op == null) && !Variabili.LOGIN.equals(op)){
            forward(request,response,"/forumJSP/Login.jsp");
            return;
        }       
        if(Variabili.LOGIN.equals(op) ){
            
            String username = request.getParameter(Variabili.USERNAME);
            String password = request.getParameter(Variabili.PASSWORD);
            
            if (manager.authenticate(username, password)){
                user = manager.caricaBeanUtente(username, password);
                user.setUsername(username);
                user.setPassword(password);
                session.setAttribute(Variabili.USER, user);
                forward(request,response, "/forumJSP/HomePage.jsp");
            }
            return;
        }
        if(Variabili.REG.equals(op)){
            String username = request.getParameter(Variabili.USERNAME);
            String password = request.getParameter(Variabili.PASSWORD);
            String email = request.getParameter(Variabili.EMAIL);
            manager.registrazione(username, password, email);
            user = manager.caricaBeanUtente(username, password);
            user.setUsername(username);
            user.setPassword(password);
            session.setAttribute(Variabili.USER, user);
            forward(request,response, "/forumJSP/HomePage.jsp");
            return;
       }
    return;
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
            Logger.getLogger(ControllerWeb.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ControllerWeb.class.getName()).log(Level.SEVERE, null, ex);
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
