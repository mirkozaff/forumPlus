package controller_package;

import db_package.DBmanager;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
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
import utility_package.Functions;
import utility_package.Gruppo;
import utility_package.Post;
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
        System.out.println("op: " + op);
        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute(Variabili.USER);
        
        
        if(Variabili.RECUPERO_PASS.equals(op)){
            forward(request, response, "/ServletSendMail");
            return;
        }
                if(Variabili.GRUPPIPUBBLICI.equals(op)){
           forward(request, response, "/forumJSP/MostraGruppiPubblici.jsp");
            return; 
        }
        if ((user == null || op == null) && !Variabili.LOGIN.equals(op)){
            forward(request,response,"/forumJSP/Login.jsp");
            return;
        }       
        if (Variabili.LOGIN.equals(op) ){
            
            String username = request.getParameter(Variabili.USERNAME);
            String password = request.getParameter(Variabili.PASSWORD);
            
            if (manager.authenticate(username, password)){
                user = manager.caricaBeanUtente(username, password);
                session.setAttribute(Variabili.USER, user);
                Date data = new java.util.Date();
                Timestamp timestamp = new Timestamp(data.getTime());
                manager.setNewTimestamp(user.getId(), timestamp);
                
                ArrayList listagname = new ArrayList();
                ArrayList listagadmin = new ArrayList();
        //chiedo se ci sono inviti per l'utente
        manager.getinviti(Functions.getUserName(request),listagname, listagadmin);
        request.setAttribute("listagname", listagname);
        request.setAttribute("listagadmin", listagadmin);
                forward(request,response, "/forumJSP/HomePage2.jsp");
            }else{
                boolean LoginFail = true;
                request.setAttribute("LoginFail", LoginFail);
              forward(request,response,"/forumJSP/Login.jsp");  
            }
            return;
         }
                if(Variabili.HOME.equals(op)){
                                    ArrayList listagname = new ArrayList();
                ArrayList listagadmin = new ArrayList();
        //chiedo se ci sono inviti per l'utente
        manager.getinviti(Functions.getUserName(request),listagname, listagadmin);
        request.setAttribute("listagname", listagname);
        request.setAttribute("listagadmin", listagadmin);
            forward(request, response, "/forumJSP/HomePage2.jsp");
            return;
        }
         if (Variabili.REGISTRAZIONE.equals(op)){
           
            String password = request.getParameter(Variabili.PASSWORD);
            String nickname = request.getParameter(Variabili.NICKNAME); 
            String email = request.getParameter(Variabili.EMAIL);
            
            //creo la data di registrazione che metto nel db come ultimo accesso
            Date data = new java.util.Date();
            Timestamp timestamp = new Timestamp(data.getTime());
            
            //aggiungo l'utente al db usando i dati di registrazione, l'immagine di default, la data appena calcolata e moderatore=false
            manager.aggiungiUtente(nickname, password, email, timestamp);
            
            //creo un'istanza di utente prendendo i dati appena salvati nel db
            user = manager.caricaBeanUtente(nickname, password);
            
            //metto l'utente in sessione così posso accederci dalle .jsp
            session.setAttribute(Variabili.USER, user);
            forward(request,response,"/forumJSP/HomePage2.jsp");
            return;
        }
        if (Variabili.LOGOUT.equals(op) ){
            forward(request, response, "/ServletLogout");
            return;
        }
        if(Variabili.CREAGRUPPO.equals(op)){          
            ArrayList<String> listanomi = new <String>ArrayList();
            String gname = null;
  
            manager.listanomi(listanomi); 

            if(listanomi.contains(user.getUsername())){
                listanomi.remove(user.getUsername());
            }

            request.setAttribute("listavisualizzata", listanomi);
            request.setAttribute("gname", gname);
            forward(request,response,"/forumJSP/EditGruppo.jsp");
            return;   
        }
         if(Variabili.MODIFICAGRUPPO.equals(op)){  

            String gname =request.getParameter("gname");
            String gadmin=request.getParameter("gadmin");

            if(gname!=null && gadmin!=null){

                ArrayList<String> listanomi = new <String>ArrayList();
                ArrayList<String> listaiscritti = new <String>ArrayList();
                ArrayList<String> listavisualizzata = new <String>ArrayList();

                manager.listanomi(listanomi); 
                manager.listaiscritti(gname, gadmin, listaiscritti);

                if(listanomi.contains(Functions.getUserName(request))){
                    listanomi.remove(Functions.getUserName(request));
                }
                for (String s : listanomi) {  
                    if(!listaiscritti.contains(s)){
                        listavisualizzata.add(s);
                    }
                }
                
                Boolean visibility = manager.getGroupVisibility(gname, gadmin);           

                request.setAttribute("listavisualizzata", listavisualizzata);
                request.setAttribute("gname", gname);
                request.setAttribute("visibility", visibility);
                forward(request,response,"/forumJSP/EditGruppo.jsp");
            }
            return; 
        }
        if(Variabili.RISPOSTAINVITO.equals(op)){
            String bottone=request.getParameter("bottone");
            String gname=request.getParameter("gname");
            String gadmin=request.getParameter("gadmin");

            manager.aggiornarecordinviti(gname, Functions.getUserName(request), gadmin, bottone);
        
            forward(request, response, "/forumJSP/HomePage2.jsp");
        
            return;
        } 
        if(Variabili.ALLEGATO.equals(op) || Variabili.AVATAR.equals(op) || Variabili.AVATAR_IMG.equals(op) || Variabili.PDF.equals(op)){
            request.setAttribute(Variabili.OP, op);
            forward(request, response, "/file/*");
            return;
        }
        if(Variabili.CAMBIO_PASS.equals(op)){
            forward(request, response, "/ServletCambioPassword");
            return;
        }
        if(Variabili.VISUALIZZAPOST.equals(op)){
            String gname = request.getParameter(Variabili.GNAME);
            String gadmin = request.getParameter(Variabili.GADMIN);
            String bottone = request.getParameter(Variabili.BOTTONE);
            ArrayList<Post> listapost = new ArrayList<Post>();
            Gruppo gruppo;
            boolean entrato_come_moderatore = false;
       
            manager.getpost(gname, gadmin, listapost);
            gruppo = manager.getGroupInfo(gname, gadmin, user.getUsername());
            
            if(bottone != null){
            if(bottone.equals("entra_moderatore")){
                entrato_come_moderatore = true;
            }
            }
            
            request.setAttribute("gruppo", gruppo);
            request.setAttribute("listapost", listapost);
            request.setAttribute("gname", gname);
            request.setAttribute("gadmin", gadmin);
            request.setAttribute("bottone", entrato_come_moderatore);
            forward(request, response, "/forumJSP/VisualizzaPost.jsp");
            return;
        }

        if(Variabili.JSON.equals(op)){
            forward(request, response, "/ServletLoadTable");
            return;
        }
        if("chiudigruppo".equals(op)){
            
            String gname = request.getParameter(Variabili.GNAME);
            String gadmin = request.getParameter(Variabili.GADMIN);
            
            request.setAttribute("gname", gname);
            request.setAttribute("gadmin", gadmin);
            
            forward(request, response, "/ServletChiudiGruppo");
            return;
        }

        if(Variabili.MOSTRAGRUPPILOGGATO.equals(op)){
    
            ArrayList listagruppipubblici = new ArrayList();
            ArrayList listaadminpubblici = new ArrayList();
            manager.listaGruppiPubblici(listagruppipubblici, listaadminpubblici);
            request.setAttribute("listagruppipubblici", listagruppipubblici);
            request.setAttribute("listaadminpubblici", listaadminpubblici);
    
            ArrayList listagruppi = new ArrayList();
            ArrayList listaadmin = new ArrayList();
            manager.listagruppi(user.getUsername(), listagruppi, listaadmin);
            request.setAttribute("listagruppi", listagruppi);
            request.setAttribute("listaadmin", listaadmin);

            forward(request, response, "/forumJSP/MostraGruppi.jsp");
            return;
        }
        if(Variabili.MOSTRAGRUPPIMODERATORE.equals(op)){
            System.out.println("moderator table");
            forward(request, response, "/forumJSP/ModeratorTable.jsp");
            return;
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
