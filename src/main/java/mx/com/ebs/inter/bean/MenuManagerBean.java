package mx.com.ebs.inter.bean;

import mx.com.ebs.inter.data.bo.MenuBo;
import mx.com.ebs.inter.util.UnicodeCommonWords;
import mx.com.ebs.inter.util.Validator;
import mx.com.ebs.inter.util.file.FileMenuManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

//import javax.faces.bean.ManagedBean;
import javax.annotation.PostConstruct;
//import javax.faces.bean.ViewScoped;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static mx.com.ebs.inter.util.FacesMessageUtil.showFacesMessage;

/**
 * Created by robb on 13/06/2015.
 */
//@ManagedBean
//@ViewScoped
//@Component
public class MenuManagerBean implements Serializable{

    private static final Logger LOGGER = Logger.getLogger(MenuManagerBean.class);

    private MenuBo menuBo;
    private String currentProfile;
    private String newProfile;
    private List<String> menuOptionsList;
    private List<String> profileList;
    private List<String> resourcesList;

    public MenuManagerBean(){
        super();
        try {
            menuBo = FileMenuManager.read();
        } catch (IOException e) {
            LOGGER.error("An IOException occurred while reading Menu object from file" , e);
        } catch (ClassNotFoundException e) {
            LOGGER.error("An ClassNotFoundException occurred while reading Menu object from file", e);
        }
    }

    @PostConstruct
    public void init(){
        menuOptionsList = new ArrayList<String>();
        profileList = new ArrayList<String>();
        profileList.addAll(menuBo.getPerfilesMapList().keySet());
        resourcesList = new ArrayList<String>();
        resourcesList.addAll( menuBo.getResourcesMap().keySet() );
    }

    public void addProfile(){
        if(Validator.isEmptyString(newProfile) ){
            showFacesMessage("Perfil no v" + UnicodeCommonWords.AACUTE_LOWER + "lido","El perfil no puede ser nulo", "error");
            return;
        }
        if(menuBo.getPerfilesMapList().containsKey(newProfile) ){
            showFacesMessage("Perfil no v" + UnicodeCommonWords.AACUTE_LOWER + "lido","Ya existe el perfil que intentas crear", "error");
        }else{
            menuBo.getPerfilesMapList().put(newProfile, new ArrayList<String>());
            try {
                FileMenuManager.save(menuBo);
                currentProfile = newProfile;
                newProfile = null;
                profileList.clear();
                profileList.addAll(menuBo.getPerfilesMapList().keySet());
                changeOptionValue();
                showFacesMessage(UnicodeCommonWords.OPERACION + " exitosa", "El nuevo perfil ha sido agregado" ,null);
            } catch (IOException e) {
                showFacesMessage("Se encontr" + UnicodeCommonWords.OACUTE_LOWER +" un error inesperado al intentar guardar el perfil",
                        "Error", "error");
            }

        }
    }

    public void addOptions(){
        try {
            if( currentProfile == null ){
                showFacesMessage("Error de validaci"+ UnicodeCommonWords.OACUTE_LOWER+"n","Selecciona un perfil", "error");
                return;
            }
            LOGGER.debug("Adding options to Profile "+ currentProfile);
            for( String option : menuOptionsList ){
                LOGGER.debug(option);
            }
            if( menuBo.getPerfilesMapList().containsKey(currentProfile)  ){
                menuBo.getPerfilesMapList().get(currentProfile).clear();
                menuBo.getPerfilesMapList().get(currentProfile).addAll(menuOptionsList);
                FileMenuManager.save(menuBo);
                showFacesMessage("Se han agregado las opciones al perfil", UnicodeCommonWords.OPERACION + " exitosa", null);
            }

        } catch (IOException e) {
            showFacesMessage("Se encontr" + UnicodeCommonWords.OACUTE_LOWER +" un error inesperado al intentar guardar el perfil",
                    "Error", "error");
        }
    }

    public void changeOptionValue(){
        menuOptionsList.clear();
        LOGGER.debug("Selected profile:" + currentProfile);
        if( menuBo.getPerfilesMapList().containsKey(currentProfile)  ){
            menuOptionsList.addAll(menuBo.getPerfilesMapList().get(currentProfile));
        }
        for( String item : menuOptionsList ){
            LOGGER.debug(item);
        }

    }

    public MenuBo getMenuBo() {
        return menuBo;
    }

    public void setMenuBo(MenuBo menuBo) {
        this.menuBo = menuBo;
    }

    public String getCurrentProfile() {
        return currentProfile;
    }


    public void setCurrentProfile(String currentProfile) {
        this.currentProfile = currentProfile;
    }

    public List<String> getMenuOptionsList() {
        return menuOptionsList;
    }

    public void setMenuOptionsList(List<String> menuOptionsList) {
        this.menuOptionsList = menuOptionsList;
    }

    public String getNewProfile() {
        return newProfile;
    }

    public void setNewProfile(String newProfile) {
        this.newProfile = newProfile;
    }

    public List<String> getProfileList() {
        return profileList;
    }

    public void setProfileList(List<String> profileList) {
        this.profileList = profileList;
    }

    public List<String> getResourcesList() {
        return resourcesList;
    }

    public void setResourcesList(List<String> resourcesList) {
        this.resourcesList = resourcesList;
    }
}
