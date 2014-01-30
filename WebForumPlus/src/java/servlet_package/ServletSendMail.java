package servlet_package;

import db_package.DBmanager;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "ServletSendMail", urlPatterns = {"/ServletSendMail"})
public class ServletSendMail extends HttpServlet {
    
    private DBmanager manager;
    
    @Override
    public void init() throws ServletException {
        // inizializza il DBManager dagli attributi di Application
        this.manager = (DBmanager)super.getServletContext().getAttribute("dbmanager");
    }
    
    public void inviaMailRecupero(HttpServletRequest request, HttpServletResponse response, String mailto, String username)
        throws ServletException, IOException, SQLException{
        
        try {

            Properties props = System.getProperties();
            props.put( "mail.smtp.host", "mail.unitn.it" );
            props.put( "mail.debug", "true" );
            
            Session session = Session.getDefaultInstance( props );
            Message message = new MimeMessage( session );
            
            InternetAddress from = new InternetAddress("WebForum@unitn.it");
            InternetAddress to[] = InternetAddress.parse(mailto);
            
            message.setFrom( from );
            message.setRecipients( Message.RecipientType.TO, to );
            message.setSubject( "Recupero password" );
            message.setSentDate( new Date() );
            message.setText( "La tua password è: " + manager.getPassword(username));

            Transport.send(message);

        }catch(MessagingException e) {
                e.printStackTrace();
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        
        String mailto = request.getParameter("mailto");
        String username = request.getParameter("username");
        if(manager.getMail(username).equals(mailto)){
            inviaMailRecupero(request, response, mailto, username);
            ServletContext sc = getServletContext(); 
            RequestDispatcher rd = sc.getRequestDispatcher("/forumJSP/Login.jsp"); 
            rd.forward(request,response);
        }else{
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
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
            Logger.getLogger(ServletSendMail.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ServletSendMail.class.getName()).log(Level.SEVERE, null, ex);
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
