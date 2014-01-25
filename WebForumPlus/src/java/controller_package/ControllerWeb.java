package controller_package;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utility_package.User;


@WebServlet(name = "ControllerWeb", urlPatterns = {"/ControllerWeb"})
public class ControllerWeb extends HttpServlet {

    private void forward(HttpServletRequest request, HttpServletResponse response, String page)
            throws ServletException, IOException {
        
        ServletContext sc = getServletContext(); 
        RequestDispatcher rd = sc.getRequestDispatcher(page); 
        rd.forward(request,response);
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String op = request.getParameter("op");
        HttpSession session = request.getSession(true);
        User u = (User) session.getAttribute("user");
        
        if ((u==null || op==null) && !"login".equals(op)){
            forward(request,response,"/login.jsp");
            return;
        }       
        if ("login".equals(op) ){
            
            u = new User(request.getParameter("name"));
            if (u==null){ //|| ! u.checkPassword(request.getParameter("password")){
                forward(request,response,"/login.jsp");
            }
            else{
                session.setAttribute("user",u);
                forward(request,response,"/home.jsp");
            }
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
        processRequest(request, response);
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
