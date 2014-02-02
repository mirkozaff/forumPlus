/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utility_package;

/**
 *
 * @author giovanni
 */
public class Gruppo {
    
   private int ID;
   private String gname;
   private String gadmin;
   private String utente;
   private boolean inscritto;
   private boolean pubblico;
   private boolean closed;

    public Gruppo() {
    }
   
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public String getGadmin() {
        return gadmin;
    }

    public void setGadmin(String gadmin) {
        this.gadmin = gadmin;
    }

    public String getUtente() {
        return utente;
    }

    public void setUtente(String utente) {
        this.utente = utente;
    }

    public boolean isInscritto() {
        return inscritto;
    }

    public void setInscritto(boolean inscritto) {
        this.inscritto = inscritto;
    }



    public boolean isPubblico() {
        return pubblico;
    }

    public void setPubblico(boolean pubblico) {
        this.pubblico = pubblico;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }
    
   
}
