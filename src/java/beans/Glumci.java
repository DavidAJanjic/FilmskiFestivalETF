/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;


import DAO.GlumciDAO;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;

@ManagedBean(name = "glumci")
public class Glumci implements Serializable{
    int idGlumac;
    String imeGlumac;
    
    private List<String> sviGlumciLista;

    public List<String> getSviGlumci() {
        return sviGlumciLista;
    }

    public void setSviGlumci(List<String> sviGlumci) {
        this.sviGlumciLista = sviGlumciLista;
    }

    public Glumci(int idGlumac, String imeGlumac) {
        this.idGlumac = idGlumac;
        this.imeGlumac = imeGlumac;
    }

    public Glumci() {
    }

    public int getIdGlumac() {
        return idGlumac;
    }

    public void setIdGlumac(int idGlumac) {
        this.idGlumac = idGlumac;
    }

    public String getImeGlumac() {
        return imeGlumac;
    }

    public void setImeGlumac(String imeGlumac) {
        this.imeGlumac = imeGlumac;
    }
    
    public List<SelectItem> dohvatiGlumce() {
        return GlumciDAO.dohvatiSveGlumce().stream().map(x -> new SelectItem(x.getIdGlumac(),x.getImeGlumac())).collect(Collectors.toList());
        
    }
    
}
