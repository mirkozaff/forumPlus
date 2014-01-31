

package utility_package;

import java.util.Scanner;

public class Post {
    
    String testo;
    String utente_postante;
    String gname;
    String gadmin; 
    String data;

    public Post(String testo, String utente_postante,String gname,String gadmin, String data) {
        this.testo = modifica(testo);
        this.utente_postante = utente_postante;
        this.gname = gname;
        this.gadmin = gadmin;
        this.data = data;
    }

    public String getTesto() {
        return testo;
    }

    public String getUtente_postante() {
        return utente_postante;
    }

    public String getGname() {
        return gname;
    }

    public String getGadmin() {
        return gadmin;
    }

    public String getData() {
        return data;
    }
    public String modifica(String testo){
        Scanner s = new Scanner(testo);
        String testoFinale ="";
        String nomeLink = "";
        String modifing;
        while(s.hasNext())
        {
            modifing=s.next();
            char [] caratteri = new char[modifing.length()];
            for (int i=0; i<caratteri.length; i++) {
            caratteri[i] = modifing.charAt(i);}
            
            if(caratteri[0]=='$' && caratteri[1]=='$' && caratteri[caratteri.length-1]=='$' && caratteri[caratteri.length-2]=='$'){
                String tmp;
                for(int y = 2; y<caratteri.length-2; y++){ 
                tmp = Character.toString(caratteri[y]);    
                nomeLink = nomeLink+tmp;       //questo è il nome del link
                modifing = "<a href=\""+nomeLink+"\" target=\"_blank\">"+nomeLink+"</a>"; 
                }  
            }
            testoFinale = testoFinale+" "+modifing;
       
        }return testoFinale;
    }    

    
}
