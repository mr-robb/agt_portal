package mx.com.ebs.inter.data.bo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by robb on 30/05/2015.
 */
public class RecAccesoSearchBo {

    private String user;
    private String username;
    private BigDecimal status;
    private String tipoUser;
    private Date fechaModificacion1;
    private Date fechaModificacion2;
    private String email;
    private String noCte;
    private List<String> tipoUserInList;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public BigDecimal getStatus() {
        return status;
    }

    public void setStatus(BigDecimal status) {
        this.status = status;
    }

    public String getTipoUser() {
        return tipoUser;
    }

    public void setTipoUser(String tipoUser) {
        this.tipoUser = tipoUser;
    }

    public Date getFechaModificacion1() {
        return fechaModificacion1;
    }

    public void setFechaModificacion1(Date fechaModificacion1) {
        this.fechaModificacion1 = fechaModificacion1;
    }

    public Date getFechaModificacion2() {
        return fechaModificacion2;
    }

    public void setFechaModificacion2(Date fechaModificacion2) {
        this.fechaModificacion2 = fechaModificacion2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getTipoUserInList() {
        return tipoUserInList;
    }

    public void setTipoUserInList(List<String> tipoUserInList) {
        this.tipoUserInList = tipoUserInList;
    }

    public String getNoCte() {
        return noCte;
    }

    public void setNoCte(String noCte) {
        this.noCte = noCte;
    }
}
