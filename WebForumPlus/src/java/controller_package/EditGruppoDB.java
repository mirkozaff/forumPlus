package controller_package;

import db_package.DBmanager;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utility_package.User;
import utility_package.Variabili;


public class EditGruppoDB extends HttpServlet {
    private DBmanager manager;


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        
    this.manager = (DBmanager)super.getServletContext().getAttribute("dbmanager");  //mi connetto al database
    HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute(Variabili.USER);
               
        String nomegruppo = request.getParameter("nomegruppo");
        String[] utentiNuovoGruppo = request.getParameterValues("utente");
        
        String bottone =request.getParameter("bottone");
        String gname = request.getParameter("gname");
        Boolean visibility = Boolean.valueOf(request.getParameter("visibility"));

        if(bottone.toString().equals("crea gruppo")){
            //provo a creare il gruppo richiesto
            manager.aggiornalistagruppi(nomegruppo,user.getUsername(), utentiNuovoGruppo, visibility);
        }
        else if(bottone.toString().equals("modifica gruppo") && gname.toString()!=null){
            manager.modificagruppo(gname, nomegruppo ,user.getUsername(), utentiNuovoGruppo, visibility);
        }
        
        

        RequestDispatcher rd = request.getRequestDispatcher("/forumJSP/MostraGruppi.jsp");
        rd.forward(request, response);
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
            Logger.getLogger(EditGruppoDB.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(EditGruppoDB.class.getName()).log(Level.SEVERE, null, ex);
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
