
package beans;

import DAO.ZemljePoreklaDAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

@ManagedBean(name = "zemljePorekla")
@SessionScoped
public class ZemljePorekla {
    private int idZemljePorekla;
    private String imeZemljePorekla;
    
    private List<String> zemljePoreklaLista;

    public ZemljePorekla(int idZemljePorekla, String imeZemljePorekla) {
        this.idZemljePorekla = idZemljePorekla;
        this.imeZemljePorekla = imeZemljePorekla;
    }

    public ZemljePorekla() {
    }
    

    public int getIdZemljePorekla() {
        return idZemljePorekla;
    }

    public void setIdZemljePorekla(int idZemljePorekla) {
        this.idZemljePorekla = idZemljePorekla;
    }

    public String getImeZemljePorekla() {
        return imeZemljePorekla;
    }

    public void setImeZemljePorekla(String imeZemljePorekla) {
        this.imeZemljePorekla = imeZemljePorekla;
    }

    public List<String> getZemljePoreklaLista() {
        return zemljePoreklaLista;
    }

    public void setZemljePoreklaLista(List<String> zemljePoreklaLista) {
        this.zemljePoreklaLista = zemljePoreklaLista;
    }
    
    public List<SelectItem> pregledListeZemalja() {
        return ZemljePoreklaDAO.dohvatiZemljePorekla().stream().map(x -> new SelectItem(x.getIdZemljePorekla(),x.getImeZemljePorekla())).collect(Collectors.toList());
        
    }
    
}
