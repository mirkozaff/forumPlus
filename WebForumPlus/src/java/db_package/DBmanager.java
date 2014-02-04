package db_package;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import utility_package.Gruppo;
import utility_package.Post;
import utility_package.User;


public class DBmanager implements Serializable{
    
    private transient Connection con;

    public DBmanager () throws SQLException{
       try {
          Class.forName("org.apache.derby.jdbc.ClientDriver",true,getClass().getClassLoader());
           System.out.println("caricato driver");
       }catch(Exception e){
           throw new RuntimeException(e.toString(), e);
       }
       this.con = DriverManager.getConnection("jdbc:derby://localhost:1527/DBforum","forum","forum");
        System.out.println("connessione al db fatta");
    }
    public static void shutdown(){
        try{
            DriverManager.getConnection("jdbc:derby://localhost:1527/DBforum;shutdown=true");
        } catch (SQLException ex){
            Logger.getLogger(DBmanager.class.getName()).info(ex.getMessage());
        }
    }
    public boolean authenticate(String name, String password) throws SQLException{
        PreparedStatement stm = con.prepareStatement("SELECT * FROM utenti WHERE name= ? AND password = ?");
        if(name != null && password != null){
        try{
            stm.setString(1, name);
            stm.setString(2, password);
            ResultSet rs = stm.executeQuery();
            try{
            if(rs.next()){
                return true;
            } else {
                return false;
                }
            } finally {
            rs.close();
            }
        }finally {
         stm.close();
        } 
        } 
        return false;
    }
    
    public void listagruppi(String name, ArrayList<String> listagruppi, ArrayList<String> listaadmin ) throws SQLException{
         
         // trovo i gruppi a cui l'utente è iscritto o di cui è amministratore
        PreparedStatement stm = con.prepareStatement("SELECT DISTINCT GNAME,GADMIN FROM gruppi where UTENTE=? AND INVITATO=false");
        try{
            stm.setString(1, name);
            ResultSet rs = stm.executeQuery();
            System.out.println("appena prima di entrare nella query");
            try{
            while(rs.next()){
                listagruppi.add(rs.getString("GNAME"));
                listaadmin.add(rs.getString("GADMIN"));
            }
            } finally {
            rs.close();
            }
        }finally {
         stm.close();
        }
   }
    
        public void listagruppiModeratore(ArrayList<String> listagruppi, ArrayList<String> listaadmin ) throws SQLException{
         
         // trovo i gruppi a cui l'utente è iscritto o di cui è amministratore
        PreparedStatement stm = con.prepareStatement("SELECT DISTINCT GNAME,GADMIN FROM gruppi");
        try{
            ResultSet rs = stm.executeQuery();
            try{
            while(rs.next()){
                listagruppi.add(rs.getString("GNAME"));
                listaadmin.add(rs.getString("GADMIN"));
            }
            } finally {
            rs.close();
            }
        }finally {
         stm.close();
        }
   }
    
    public void listaGruppiPubblici(ArrayList<String> listagruppi, ArrayList<String> listaadmin ) throws SQLException{
        
        PreparedStatement stm = con.prepareStatement("SELECT DISTINCT GNAME,GADMIN FROM gruppi where PUBBLICO=true");
        try{
            ResultSet rs = stm.executeQuery();
            try{
            while(rs.next()){
                listagruppi.add(rs.getString("GNAME"));
                listaadmin.add(rs.getString("GADMIN"));
            }
            } finally {
            rs.close();
            }
        }finally {
         stm.close();
        }
   }
    

    
    public void getImageURL(String name, HttpSession session) throws SQLException{
        PreparedStatement stm = con.prepareStatement("SELECT URL_IMAGE FROM utenti WHERE NAME= ?");
        try{
            stm.setString(1, name);
            ResultSet rs = stm.executeQuery();
            try{
                while(rs.next()){
                    session.setAttribute("img",(rs.getString(1)));
                }
            }finally {
                rs.close();
            }
        }finally {
         stm.close();
        }    
    }
    
    public String getPassword(String name) throws SQLException{
        
        String password = "";
        
        PreparedStatement stm = con.prepareStatement("SELECT PASSWORD FROM utenti WHERE NAME= ?");
        try{
            stm.setString(1, name);
            ResultSet rs = stm.executeQuery();
            try{
                while(rs.next()){
                    password = rs.getString(1);
                }
            }finally {
                rs.close();
            }
        }finally {
         stm.close();
        } 
        return password;
    }
    
    public String getMail(String name) throws SQLException{
        
        String email = "";
        
        PreparedStatement stm = con.prepareStatement("SELECT EMAIL FROM utenti WHERE NAME= ?");
        try{
            stm.setString(1, name);
            ResultSet rs = stm.executeQuery();
            try{
                while(rs.next()){
                    email = rs.getString(1);
                }
            }finally {
                rs.close();
            }
        }finally {
         stm.close();
        } 
        return email;
    }
    
    public String getAvatar(String name) throws SQLException{
        PreparedStatement stm = con.prepareStatement("SELECT URL_IMAGE FROM utenti WHERE NAME= ?");
        try{
            stm.setString(1, name);
            ResultSet rs = stm.executeQuery();
            try{
                while(rs.next()){
                    return rs.getString(1);
                }
            }finally {
                rs.close();
            }
        }finally {
         stm.close();
        }  
        return "noIMG";
    }
    
    public int getGroupID(String gname, String gadmin) throws SQLException{
        PreparedStatement stm = con.prepareStatement("SELECT ID FROM Gruppi WHERE GNAME=? AND GADMIN=?");
        try{
            stm.setString(1, gname);
            stm.setString(2, gadmin);
            ResultSet rs = stm.executeQuery();
            try{
                while(rs.next()){
                    return rs.getInt(1);
                }
            }finally {
                rs.close();
            }
        }finally {
         stm.close();
        }  
        return 0;
    }
    
    public Boolean getGroupVisibility(String gname, String gadmin) throws SQLException{
        PreparedStatement stm = con.prepareStatement("SELECT PUBBLICO FROM Gruppi WHERE GNAME=? AND GADMIN=?");
        try{
            stm.setString(1, gname);
            stm.setString(2, gadmin);
            ResultSet rs = stm.executeQuery();
            try{
                while(rs.next()){
                    return rs.getBoolean(1);
                }
            }finally {
                rs.close();
            }
        }finally {
         stm.close();
        }  
        return false;
    }
    
    public void setImageURL(String name, String imgURL, HttpSession session) throws SQLException{
        PreparedStatement stm = con.prepareStatement("UPDATE utenti SET URL_IMAGE= ? WHERE NAME= ?");
        
        try{
            stm.setString(1, imgURL);
            stm.setString(2, name);
            session.setAttribute("img", imgURL);
            stm.execute();
         }finally {
         stm.close();
        }    
    }
    
    public void setPassword(String name, String password) throws SQLException{
        PreparedStatement stm = con.prepareStatement("UPDATE utenti SET PASSWORD= ? WHERE NAME= ?");
        
        try{
            stm.setString(1, password);
            stm.setString(2, name);
            stm.execute();
         }finally {
         stm.close();
        }    
    }
    
     public void listanomi(ArrayList<String> listanomi) throws SQLException{
        PreparedStatement stm = con.prepareStatement("SELECT NAME FROM utenti");
        try{
            ResultSet rs = stm.executeQuery();
            try{
            while(rs.next()){
                listanomi.add(rs.getString(1));
            }
            } finally {
            rs.close();
            }
        }finally {
         stm.close();
        }
     }
      public void listaiscritti(String gname, String gadmin, ArrayList<String> listanomi) throws SQLException{
        PreparedStatement stm = con.prepareStatement("SELECT DISTINCT UTENTE FROM gruppi WHERE GNAME=? AND GADMIN=?");
        try{
            stm.setString(1, gname);
            stm.setString(2, gadmin);
            ResultSet rs = stm.executeQuery();
            try{
            while(rs.next()){
                listanomi.add(rs.getString(1));
            }
            } finally {
            rs.close();
            }
        }finally {
         stm.close();
        }
     }
      
      public boolean accessAllowed(String gname, String gadmin, String userName) throws SQLException{
        PreparedStatement stm = con.prepareStatement("SELECT DISTINCT UTENTE FROM gruppi WHERE GNAME=? AND GADMIN=?");
        try{
            stm.setString(1, gname);
            stm.setString(2, gadmin);
            ResultSet rs = stm.executeQuery();
            try{
                while(rs.next()){
                    if(rs.getString(1).equals(userName)){
                        return true;
                    }
                }
            } finally {
                rs.close();
            }
        }finally {
            stm.close();
        }
        return false;
     }
      
      public int getMaxID() throws SQLException{
          int a = 0;
          PreparedStatement stm = con.prepareStatement("SELECT MAX(ID) FROM Gruppi"); 
          try{
            ResultSet rs = stm.executeQuery();
            try{
                while(rs.next()){
                    a = rs.getInt(1);
                }
            }finally {
                rs.close();
            }
        }finally {
         stm.close();
        }  
        return a;
      }
      
      
     public void aggiornalistagruppi(String gname, String adminname, String[] utentiNuovoGruppo, boolean visibility) throws SQLException{  
         
         int idValue = getMaxID();
         //cerco se esiste già un record nella tabella "gruppi" in cui il nome del gruppo e l'admin sono uguali a quelli che voglio creare
        PreparedStatement stm = con.prepareStatement("SELECT DISTINCT GNAME,GADMIN FROM gruppi where GNAME=? AND GADMIN=?"); 
        PreparedStatement stm2 = con.prepareStatement("INSERT INTO gruppi(ID,GNAME,UTENTE,GADMIN,INVITATO,PUBBLICO) VALUES (?,?,?,?,?,?)");
        try{
            stm.setString(1, gname);
            stm.setString(2, adminname);
            ResultSet rs = stm.executeQuery();
            
            // se il gruppo che voglio creare è nuovo(ossia la coppia gname e gadmin che voglio creare non è gia presente) lo creo
            if(rs.next()==false){
                
                //aggiorno il db con il record riguardante l'admin (id,gname, admin, admin)
                stm2.setInt(1, idValue+1);
                stm2.setString(2, gname);
                stm2.setString(3, adminname);
                stm2.setString(4, adminname);
                stm2.setBoolean(5, false);
                stm2.setBoolean(6, visibility);
                stm2.execute();
                
                //aggiorno il db con il record riguardante gli invitati (gname, admin, admin)
                if(utentiNuovoGruppo!=null){
                for(int i=0; i<utentiNuovoGruppo.length;i++){
                stm2.setInt(1, idValue+1);
                stm2.setString(2, gname);
                stm2.setString(3, utentiNuovoGruppo[i]);
                stm2.setString(4, adminname);
                stm2.setBoolean(5, true);
                stm2.setBoolean(6, visibility);
                stm2.execute();
                }
                }
            }else{
                System.out.println(" gruppo e admin gia esistenti");
            }
        }finally {
         stm.close();
         stm2.close();
        }
    }
     public void getpost(String gname, String gadmin, ArrayList<Post> listapost) throws SQLException{
        PreparedStatement stm = con.prepareStatement("SELECT POST,UTENTE_POSTANTE,DATA FROM post WHERE GNAME=? AND GADMIN=?");
        try{
            stm.setString(1, gname);
            stm.setString(2, gadmin);
            ResultSet rs = stm.executeQuery();
            try{
               while(rs.next()){
                String testo = rs.getString("POST");
                String utente_postante = rs.getString("UTENTE_POSTANTE");
                String data = rs.getString("DATA");
                Post post = new Post(testo,utente_postante,gname,gadmin,data);   
                listapost.add(post);
               }
            } finally {
            rs.close();
            }
        }finally {
         stm.close();
        }
     }
     public void getinviti(String name, ArrayList<String> listagname, ArrayList<String> listagadmin) throws SQLException{
        PreparedStatement stm = con.prepareStatement("SELECT GNAME,GADMIN FROM gruppi WHERE UTENTE=? AND INVITATO=true");
        try{
            stm.setString(1, name);
            ResultSet rs = stm.executeQuery();
            try{
               while(rs.next()){
                String gname = rs.getString("GNAME");
                String gadmin = rs.getString("GADMIN");  
                listagname.add(gname);
                listagadmin.add(gadmin);
               }
            } finally {
            rs.close();
            }
        }finally {
         stm.close();
        }
     }
     public void aggiornapost(String post, String utente_postante,String gname,String gadmin,String data) throws SQLException{
         
         //creo un record nella tabella post con il nuovo post creato
         PreparedStatement stm = con.prepareStatement("INSERT INTO post(POST,UTENTE_POSTANTE,GNAME,GADMIN,DATA) VALUES (?,?,?,?,?)");
         try{
             stm.setString(1, post);
             stm.setString(2, utente_postante);
             stm.setString(3, gname);
             stm.setString(4, gadmin);
             stm.setString(5, data);
             stm.execute();
             System.out.println("post aggiornato");
         }finally{
             stm.close();
         }
     }
     public void aggiornarecordinviti(String gname, String utente,String gadmin, String bottone) throws SQLException{
         
         
         System.out.println("sono nell'aggiornainviti");
         //creo un record nella tabella post con il nuovo post creato
         PreparedStatement stm = con.prepareStatement("UPDATE gruppi SET INVITATO=false WHERE GNAME=? AND UTENTE=? AND GADMIN=?");
         PreparedStatement stm2 = con.prepareStatement("DELETE FROM gruppi WHERE GNAME=? AND UTENTE=? AND GADMIN=?");
         if(bottone.toString().equals("accetta")){
         try{
             stm.setString(1, gname);
             stm.setString(2, utente);
             stm.setString(3, gadmin);
             stm.execute();
             System.out.println("record aggiornato con invitato=false");
         }finally{
             stm.close();
         }
         }
         if(bottone.toString().equals("rifiuta")){
          try{
             stm2.setString(1, gname);
             stm2.setString(2, utente);
             stm2.setString(3, gadmin);
             stm2.execute();
             System.out.println("record cancellato");
         }finally{
             stm2.close();
         }   
         }
     }

     public void modificagruppo(String gname, String newgname, String gadmin, String[] utentiNuovoGruppo, Boolean visibility) throws SQLException{
         
         //faccio l'update del nome del gruppo
         PreparedStatement stm = con.prepareStatement("UPDATE gruppi SET GNAME=? WHERE GNAME=? AND GADMIN=?");
         //aggiungo i nuovi utenti invitati
         PreparedStatement stm2 = con.prepareStatement("INSERT INTO gruppi(ID,GNAME,UTENTE,GADMIN,INVITATO,PUBBLICO) VALUES (?,?,?,?,?,?)");
         PreparedStatement stm3 = con.prepareStatement("UPDATE post SET GNAME=? WHERE GNAME=?");
         //modifico visibilità
         PreparedStatement stm4 = con.prepareStatement("UPDATE gruppi SET PUBBLICO=? WHERE GNAME=? AND GADMIN=?");
         
         try{
             stm.setString(1, newgname);
             stm.setString(2, gname);
             stm.setString(3, gadmin);
             stm.execute();
             System.out.println("nomegruppo aggiornato");
             stm3.setString(1, newgname);
             stm3.setString(2, gname);
             stm3.execute();
             //modifico visibilità
             stm4.setBoolean(1, visibility);
             stm4.setString(2, newgname);
             stm4.setString(3, gadmin);
             stm4.execute();
         }finally{
             stm.close();
         }
       if(utentiNuovoGruppo!=null){
         try
         {
                //aggiorno il db con il record riguardante gli invitati (gname, admin, admin)
                for(int i=0; i<utentiNuovoGruppo.length;i++){
                    stm2.setInt(1, getGroupID(newgname, gadmin));
                    stm2.setString(2, newgname);
                    stm2.setString(3, utentiNuovoGruppo[i]);
                    stm2.setString(4, gadmin);
                    stm2.setBoolean(5, true);
                    stm2.setBoolean(6, visibility);
                    stm2.execute();
                    System.out.println("aggiunti i nuovi tizi invitati");
                }
         }finally {
            stm2.close();
         }
       }
     }
     
     public ArrayList<String> utentiPartecipantiPDF(String gname, String gadmin)throws SQLException{
        
        ArrayList <String> utentiPartecipanti = new ArrayList <String>();
        PreparedStatement stm = con.prepareStatement("SELECT * FROM gruppi WHERE gname= ? AND gadmin = ?");
        try{
            stm.setString(1, gname);
            stm.setString(2, gadmin);
            ResultSet rs = stm.executeQuery();
            try{
                while(rs.next()){
                    utentiPartecipanti.add(rs.getString("UTENTE"));
                }
            } finally {
            rs.close();
            }
        }finally {
         stm.close();
        } 
        return utentiPartecipanti;
     }
     
    public int getNumeroUtentiPartecipanti(String gname, String gadmin)throws SQLException{
        
        int count= 0;
        PreparedStatement stm = con.prepareStatement("SELECT * FROM gruppi WHERE gname= ? AND gadmin = ? AND INVITATO=false");
        try{
            stm.setString(1, gname);
            stm.setString(2, gadmin);
            ResultSet rs = stm.executeQuery();
            try{
                while(rs.next()){
                    count++;
                }
            } finally {
            rs.close();
            }
        }finally {
         stm.close();
        } 
        return count;
     }
     
     public int numeroPostPDF(String gname, String gadmin)throws SQLException{
        
        int numPost = 0;
        PreparedStatement stm = con.prepareStatement("SELECT * FROM post WHERE gname= ? AND gadmin = ?");
        try{
            stm.setString(1, gname);
            stm.setString(2, gadmin);
            ResultSet rs = stm.executeQuery();
            try{
                while(rs.next()){
                    numPost++;
                }
            } finally {
            rs.close();
            }
        }finally {
         stm.close();
        } 
        return numPost;
     }
     
     public String ultimaDataPDF(String gname, String gadmin)throws SQLException{
        
        String ultimaData = "";
        PreparedStatement stm = con.prepareStatement("SELECT data FROM post WHERE gname= ? AND gadmin = ?");
        try{
            stm.setString(1, gname);
            stm.setString(2, gadmin);
            ResultSet rs = stm.executeQuery();
            try{
                while(rs.next()){
                    ultimaData = rs.getString("DATA");
                }
            } finally {
            rs.close();
            }
        }finally {
         stm.close();
        } 
        return ultimaData;
     }
      public User caricaBeanUtente(String username, String password) throws SQLException{
        User user = new User();
        
        //seleziono tutti i dati dell'utente dal db
        PreparedStatement stm = con.prepareStatement("SELECT * FROM utenti WHERE name=? AND password=?");
        try{
            stm.setString(1, username);
            stm.setString(2, password);
            ResultSet rs = stm.executeQuery();
            try{
               rs.next();
               String url_image = rs.getString("URL_IMAGE");
               boolean moderatore = rs.getBoolean("MODERATORE");
               Timestamp ultimo_accesso = rs.getTimestamp("ULTIMO_ACCESSO");
               int id = rs.getInt("ID");
               String email = rs.getString("EMAIL");
               
               //metto i dati estratti nell'istanza user
               user.setImageURL(url_image);
               user.setPassword(password);
               user.setUsername(username);
               user.setModeratore(moderatore);
               user.setUltimo_accesso(ultimo_accesso);
               user.setId(id);
               user.setEmail(email);
            } finally {
                rs.close();
            }
        }finally {
            stm.close();
        }
        
        //ritorno l'user caricato
        return user;
     }
      
      public void setNewTimestamp(int id, Timestamp data) throws SQLException{
          
          //setto una nuova data all'utente il cui id è quello passato come parametro
        PreparedStatement stm = con.prepareStatement("UPDATE utenti SET ultimo_accesso = ? WHERE id = ?"); 
        try{
            stm.setTimestamp(1, data);
            stm.setInt(2, id);
            stm.execute();
         }finally {
         stm.close();
        }    
    }
      
      public void aggiungiUtente(String nickname, String password, String email, Timestamp data) throws SQLException{
         
          //provo a cercare l'utente nel db per vedere se esiste già
         PreparedStatement stm2 = con.prepareStatement("SELECT DISTINCT NAME,PASSWORD FROM utenti where NAME=? AND PASSWORD=?"); 
         //creo un record nella tabella utenti con il nuovo utente creato
         PreparedStatement stm = con.prepareStatement("INSERT INTO utenti(NAME,PASSWORD,URL_IMAGE,MODERATORE,ULTIMO_ACCESSO,EMAIL) VALUES (?,?,?,?,?,?)");
         
         try{
             stm2.setString(1, nickname);
             stm2.setString(2, password);
             ResultSet rs = stm2.executeQuery();
             
             //se l'utente non esiste lo posso registrare
         if(rs.next()==false){
             stm.setString(1, nickname);
             stm.setString(2, password);
             stm.setString(3, "/forumIMG/default-no-profile-pic.jpg");
             stm.setBoolean(4, false);
             stm.setTimestamp(5, data);
             stm.setString(6, email);
             stm.execute();
         }else{
                System.out.println(" nome e password gia esistenti");
            }
         }finally{
             stm2.close();
             stm.close();
         }
     }
      public Gruppo getGroupInfo(String gname, String gadmin, String user) throws SQLException{
        
          Gruppo gruppo = new Gruppo();
        
        //seleziono tutti i dati dell'utente dal db
        PreparedStatement stm = con.prepareStatement("SELECT DISTINCT pubblico,closed FROM gruppi WHERE gname=? AND gadmin=?");
        PreparedStatement stm2 = con.prepareStatement("SELECT DISTINCT * FROM gruppi where GNAME =? AND GADMIN=? AND UTENTE=? AND INVITATO=false");
        try{
            stm.setString(1, gname);
            stm.setString(2, gadmin);        
            ResultSet rs = stm.executeQuery();           
            try{          
               rs.next();              
               boolean pubblico = rs.getBoolean("PUBBLICO");
               boolean chiuso = rs.getBoolean("CLOSED");
               
               //metto i dati estratti nell'istanza gruppo
               gruppo.setPubblico(pubblico);
               gruppo.setClosed(chiuso);
               gruppo.setUtente(user);
               gruppo.setGname(gname);
               if(gadmin.equals(user)){
                   gruppo.setGadmin(user);
               }else{gruppo.setGadmin(gadmin);}          
               
            } finally {
                rs.close();
            }          
        }finally {
            stm.close();
        }
          try {
              stm2.setString(1, gname);
              stm2.setString(2, gadmin);
              stm2.setString(3, user);
              ResultSet rs2 = stm2.executeQuery();

              try {
                  if (rs2.next()) {
                      gruppo.setInscritto(true);
                  } else {
                      gruppo.setInscritto(false);
                  }
              } finally {
                  rs2.close();
              }
          } finally {
              stm2.close();
          }
        
        //ritorno l'user caricato
        return gruppo;
     }
      
}


