package filter_package;

import db_package.DBmanager;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utility_package.Functions;
import utility_package.User;
import utility_package.Variabili;


@WebFilter(filterName = "FiltroLogin", urlPatterns = {"/*"})
public class Filtro implements Filter {
    
    private static final boolean debug = true;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;
    private DBmanager manager;
    
    public Filtro() {
    }    
    
    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("FiltroLogin:DoBeforeProcessing");
        }

	// Write code here to process the request and/or response before
        // the rest of the filter chain is invoked.
	// For example, a logging filter might log items on the request object,
        // such as the parameters.
	/*
         for (Enumeration en = request.getParameterNames(); en.hasMoreElements(); ) {
         String name = (String)en.nextElement();
         String values[] = request.getParameterValues(name);
         int n = values.length;
         StringBuffer buf = new StringBuffer();
         buf.append(name);
         buf.append("=");
         for(int i=0; i < n; i++) {
         buf.append(values[i]);
         if (i < n-1)
         buf.append(",");
         }
         log(buf.toString());
         }
         */
    }    
    
    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("FiltroLogin:DoAfterProcessing");
        }

	// Write code here to process the request and/or response after
        // the rest of the filter chain is invoked.
	// For example, a logging filter might log the attributes on the
        // request object after the request has been processed. 
	/*
         for (Enumeration en = request.getAttributeNames(); en.hasMoreElements(); ) {
         String name = (String)en.nextElement();
         Object value = request.getAttribute(name);
         log("attribute: " + name + "=" + value.toString());

         }
         */
	// For example, a filter might append something to the response.
	/*
         PrintWriter respOut = new PrintWriter(response.getWriter());
         respOut.println("<P><B>This has been appended by an intrusive filter.</B>");
         */
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)      
            throws IOException, ServletException {
        
        this.manager = (DBmanager) getFilterConfig().getServletContext().getAttribute("dbmanager");
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession();    
        User user = (User)session.getAttribute("user");

        
        String loginURL = req.getContextPath() + "/forumJSP/Login.jsp";
        String registrazioneURL = req.getContextPath() + "/forumJSP/Registrazione.jsp";
        String recuperoPassURL = req.getContextPath() + "/forumJSP/RecuperoPass.jsp"; 
        
        Boolean controller = req.getRequestURI().contains("Controller");
        Boolean login = req.getRequestURI().contains("login");
        
        if (req.getRequestURI().matches(".*(css|jpg|png|gif|js)")) {
            chain.doFilter(request, response);
            return;
        }else if(user == null && (req.getRequestURI().equals("/forumJSP/CambioPass.jsp") || req.getRequestURI().equals("/forumJSP/DatiUtente.jsp")
                        || req.getRequestURI().equals("/forumJSP/EditGruppo.jsp") || req.getRequestURI().equals("/forumJSP/HomePage2.jsp")
                        || req.getRequestURI().equals("/forumJSP/ModeratorTable.jsp")|| req.getRequestURI().equals("/forumJSP/MostraGruppi.jsp"))){
            System.out.println("filtro: mi spiace nn sei autenticato. vai al login");
            res.sendRedirect("/forumJSP/Login.jsp");           
        }else if(user != null && user.getUsername()==""){
            res.sendRedirect("/forumJSP/Login.jsp"); 
        }
        else if(user!= null && user.getUsername()!=null && req.getRequestURI().equals("/forumJSP/Login.jsp")){
            res.sendRedirect("/Controller?op=home");
        }
        else if(controller){
            chain.doFilter(request, response);
        }
        else if(req.getRequestURI().equals("/forumJSP/VisualizzaPost.jsp")){
            
            try {
                String userName = Functions.getUserName(req);
                String gname = request.getParameter(Variabili.GNAME);
                String gadmin = request.getParameter(Variabili.GADMIN);
                System.out.println("ho chiamato il filtro dei gruppi");
                if(manager.accessAllowed(gname, gadmin, userName) || manager.getGroupInfo(gname, gadmin, userName).isPubblico()){
                    System.out.println("filtro: hai il permesso per accedere alla pagina gruppi");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/forumJSP/VisualizzaPost.jsp");
                    dispatcher.forward(request, response);

                }
                else if(!manager.accessAllowed(gname, gadmin, userName)){
                    res.sendError(HttpServletResponse.SC_FORBIDDEN);
                }
            } catch (SQLException ex) {
                System.err.println("errore nel filtro");
            }
            
        }
        else{
            chain.doFilter(request, response);
        }
     }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {        
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {        
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {                
                log("FiltroLogin:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("FiltroLogin()");
        }
        StringBuffer sb = new StringBuffer("FiltroLogin(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }
    
    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);        
        
        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);                
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");                
                pw.print(stackTrace);                
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }
    
    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }
    
    public void log(String msg) {
        filterConfig.getServletContext().log(msg);        
    }
    
}
