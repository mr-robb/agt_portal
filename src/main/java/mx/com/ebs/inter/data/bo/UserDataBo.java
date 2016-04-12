package mx.com.ebs.inter.data.bo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by robb on 09/06/2015.
 */
public class UserDataBo implements Serializable {

    private String ebsUserId;
    private String ebsUsername;
    private String ebsTipoUser;
    private String numAgt;
    private String perfil;
    private Long numPerfil;
    List<String> menuOptionsList;

    public String getEbsUserId() {
        return ebsUserId;
    }

    public void setEbsUserId(String ebsUserId) {
        this.ebsUserId = ebsUserId;
    }

    public String getEbsUsername() {
        return ebsUsername;
    }

    public void setEbsUsername(String ebsUsername) {
        this.ebsUsername = ebsUsername;
    }

    public String getEbsTipoUser() {
        return ebsTipoUser;
    }

    public void setEbsTipoUser(String ebsTipoUser) {
        this.ebsTipoUser = ebsTipoUser;
    }

    public String getNumAgt() {
        return numAgt;
    }

    public void setNumAgt(String numAgt) {
        this.numAgt = numAgt;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public Long getNumPerfil() {
        return numPerfil;
    }

    public void setNumPerfil(Long numPerfil) {
        this.numPerfil = numPerfil;
    }

    public String toString(){
        StringBuilder st = new StringBuilder();
        st.append("\nebsUserId:"+ebsUserId);
        st.append("\nebsUsername:"+ ebsUsername);
        st.append("\nebsTipoUser:"+ebsTipoUser);
        st.append("\nnumAgt:"+numAgt);
        st.append("\nperfil:"+perfil);
        st.append("\nnumPerfil:"+numPerfil);
        return st.toString();
    }

    public List<String> getMenuOptionsList() {
        return menuOptionsList;
    }

    public void setMenuOptionsList(List<String> menuOptionsList) {
        this.menuOptionsList = menuOptionsList;
    }
}
