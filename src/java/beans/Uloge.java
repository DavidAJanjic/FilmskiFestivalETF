
package beans;

import javax.inject.Named;
import javax.enterprise.context.Dependent;

@Named(value = "uloge")
public class Uloge {

    int idFilm;
    int idGlumac;
    
    public Uloge() {
    }
    
    public Uloge(int idFilm, int idGlumac) {
        this.idFilm = idFilm;
        this.idGlumac = idGlumac;
    }

    public int getIdFilm() {
        return idFilm;
    }

    public void setIdFilm(int idFilm) {
        this.idFilm = idFilm;
    }

    public int getIdGlumac() {
        return idGlumac;
    }

    public void setIdGlumac(int idGlumac) {
        this.idGlumac = idGlumac;
    }   
    
}
