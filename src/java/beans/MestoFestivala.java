
package beans;

import DAO.MestoFestivalaDAO;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Named;
import javax.faces.model.SelectItem;

@Named(value = "mestoFestivala")
@javax.faces.bean.SessionScoped
public class MestoFestivala {
        int idMesto;
        String imeMesto;
        
        
    public MestoFestivala() {
    }

    public MestoFestivala(int idMesto, String imeMesto) {
        this.idMesto = idMesto;
        this.imeMesto = imeMesto;
    }

    public int getIdMesto() {
        return idMesto;
    }

    public void setIdMesto(int idMesto) {
        this.idMesto = idMesto;
    }

    public String getImeMesto() {
        return imeMesto;
    }

    public void setImeMesto(String imeMesto) {
        this.imeMesto = imeMesto;
    }
    
        public List<SelectItem> dohvatiMesta() {
        return MestoFestivalaDAO.dohvatiSvaMesta().stream().map(x -> new SelectItem(x.getIdMesto(),x.getImeMesto())).collect(Collectors.toList());
        
    }
    
}
