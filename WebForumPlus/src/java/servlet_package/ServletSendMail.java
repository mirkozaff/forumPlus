package servlet_package;

import db_package.DBmanager;
import java.io.IOException;
import java.security.Security;
import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
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
        throws ServletException, IOException, SQLException, AddressException, MessagingException{
        
        
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());

        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

        // Get a Properties object
        Properties props = System.getProperties();
        props.setProperty("mail.smtp.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.auth", "true");
        props.put( "mail.debug", "true" );

        final String usernamemail = "pincopanco.forum@gmail.com";
        final String password = "qwerty123<";

        Session session = Session.getDefaultInstance(props, new Authenticator(){
        protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(usernamemail, password);
        }});

        // Create a new message
        Message msg = new MimeMessage(session);

        // Set the FROM and TO fields
        msg.setFrom(new InternetAddress(usernamemail + ""));
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailto,false));
        msg.setSubject("Recupero Password");
        msg.setText("La tua password è: "+ manager.getPassword(username));
        msg.setSentDate(new Date());

        Transport.send(msg);
        }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, AddressException, MessagingException {
        
        String mailto = request.getParameter("mailto");
        String username = request.getParameter("username");
        if(manager.getMail(username).equals(mailto)){
            inviaMailRecupero(request, response, mailto, username);
            ServletContext sc = getServletContext(); 
            RequestDispatcher rd = sc.getRequestDispatcher("/forumJSP/Login.jsp"); 
            rd.forward(request,response);
        }else{
            boolean errorenelrecupero = true;
            request.setAttribute("errorenelrecupero", errorenelrecupero);
            forward(request, response, "/forumJSP/RecuperoPass.jsp");
            
        }
    }
        private void forward(HttpServletRequest request, HttpServletResponse response, String page)
            throws ServletException, IOException {
        
        ServletContext sc = getServletContext(); 
        RequestDispatcher rd = sc.getRequestDispatcher(page); 
        rd.forward(request,response);
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
            try {
                processRequest(request, response);
            } catch (MessagingException ex) {
                Logger.getLogger(ServletSendMail.class.getName()).log(Level.SEVERE, null, ex);
            }
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
            try {
                processRequest(request, response);
            } catch (MessagingException ex) {
                Logger.getLogger(ServletSendMail.class.getName()).log(Level.SEVERE, null, ex);
            }
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
