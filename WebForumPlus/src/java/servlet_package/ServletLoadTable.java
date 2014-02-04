package servlet_package;

import db_package.DBmanager;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class ServletLoadTable extends HttpServlet {

    private DBmanager manager;
    
    @Override
    public void init() throws ServletException {
        // inizializza il DBManager dagli attributi di Application
        this.manager = (DBmanager)super.getServletContext().getAttribute("dbmanager");
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGetPost(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGetPost(req, resp);
    }

    private void doGetPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        try {
            HttpSession session = req.getSession();
            
            ArrayList listagruppi = new ArrayList();
            ArrayList listaadmin = new ArrayList();
            manager.listagruppiModeratore(listagruppi, listaadmin);
            
            String gname;
            String gadmin;
                
            // ritorna i gruppi in formato JSON per la chiamata AJAX
            resp.setContentType("text/plain");
            PrintWriter pw = resp.getWriter();
                
            pw.print("{ \"aaData\":[");
            
            for (int i = 0; i < listagruppi.size(); i++ ) {
                
                gname = listagruppi.get(i).toString();
                gadmin = listaadmin.get(i).toString();
                
                final String buttonClose = ("<form action=\"/Controller?op=chiudigruppo\" method=POST>" +
                                            "<input class=\"btn btn-lg btn-danger\" type=\"submit\" value=\"Chiudi\">" +
                                            "<input type=\"hidden\" name=\"gname\" value=\""+gname+"\">" +
                                            "<input type=\"hidden\" name=\"gadmin\" value=\""+gadmin+"\">" +
                                            "</form>").replace("\"", "\\\"");
                
                final String buttonIn= ("<form action=\"/Controller?op=visualizzapost\" method=POST>" +
                                            "<input class=\"btn btn-lg btn-success\" type=\"submit\" value=\"Entra\">" +
                                            "<input type=\"hidden\" name=\"gname\" value=\""+gname+"\">" +
                                            "<input type=\"hidden\" name=\"gadmin\" value=\""+gadmin+"\">" +
                                            "</form>").replace("\"", "\\\"");
                
                pw.append("[\"").append(gname).append("\",");
                pw.append("\"").append(gadmin).append("\",");
                pw.append("\"").append(String.valueOf(manager.getNumeroUtentiPartecipanti(gname, gadmin))).append("\",");
                if(manager.getGroupVisibility(gname, gadmin)){
                    pw.append("\"").append("Pubblico").append("\",");
                }else{
                    pw.append("\"").append("Privato").append("\",");   
                }
            
                pw.append("\"").append(String.valueOf(manager.numeroPostPDF(gname, gadmin))).append("\",");
                pw.append("\"").append(buttonIn).append("\",");
                pw.append("\"").append(buttonClose).append("\"]");
                if(i < listagruppi.size()-1){
                    pw.print(",");
                }
                
            }
            
            pw.print("]}");
            System.out.print(pw);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ServletLoadTable.class.getName()).log(Level.SEVERE, ex.toString(), ex);
            throw new ServletException(ex);
        }
    }
}
