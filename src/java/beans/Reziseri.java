/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import DAO.ReziseriDAO;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;

@ManagedBean(name = "reziseri")
public class Reziseri {
    int idReziser;
    String imeReziseri;
    
    private List<String> sviReziseriLista;

    public Reziseri() {
    }

    public Reziseri(int idReziser, String imeReziseri) {
        this.idReziser = idReziser;
        this.imeReziseri = imeReziseri;
    }

    public int getIdReziser() {
        return idReziser;
    }

    public void setIdReziser(int idReziser) {
        this.idReziser = idReziser;
    }

    public String getImeReziseri() {
        return imeReziseri;
    }

    public void setImeReziseri(String imeReziseri) {
        this.imeReziseri = imeReziseri;
    }


    public List<String> getSviReziseriLista() {
        return sviReziseriLista;
    }

    public void setSviReziseriLista(List<String> sviReziseriLista) {
        this.sviReziseriLista = sviReziseriLista;
    }
    
        // metoda za vracanje svih rezisera za SelectOneMenu
    public List<SelectItem> dohvatiRezisere() {
        return ReziseriDAO.dohvatiSveRezisere().stream().map(x -> new SelectItem(x.getIdReziser(), x.getImeReziseri())).collect(Collectors.toList());


    }
  
}
