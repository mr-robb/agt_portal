package mx.com.ebs.inter.bean;

import mx.com.ebs.inter.data.bo.RecAccesoSearchBo;
import mx.com.ebs.inter.data.bo.UserDataBo;
import mx.com.ebs.inter.data.model.RecAcceso;
import mx.com.ebs.inter.exception.ValidationException;
import mx.com.ebs.inter.service.RecAccesoService;
import mx.com.ebs.inter.util.*;
import mx.com.ebs.inter.util.file.FileMenuManager;
import mx.com.ebs.inter.util.mail.EmailSender;
import org.apache.commons.mail.EmailException;
import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static mx.com.ebs.inter.util.FacesMessageUtil.showFacesMessage;
import static mx.com.ebs.inter.util.mail.EmailProperties.EMAIL_PASSWORD_CONTENT;

public class RecAccesoClientesBean extends AbstractBean<RecAcceso> implements Serializable{

    private static final Logger LOGGER = Logger.getLogger(RecAccesoClientesBean.class);
    private static final String PERFIL_ADMINISTRACION_VENTAS = "ADMINISTRACIONVENTAS";
    private static final String PERFIL_PROVEEDOR = "PROVEEDOR";
    private static final String PREFIX_CLIENTE_ID = "AGTE";
    private static final int DEFAULT_PASSWORD_SIZE=8;

    @Autowired
    private RecAccesoService recAccesoService;

    private RecAcceso recAcceso;
    private RecAccesoSearchBo recAccesoSearchBo;
    private List<String> tipoUserInList;
    private List<String> recPerfilesList;

    @PostConstruct
    public void init(){
        recAccesoSearchBo = new RecAccesoSearchBo();
        recAcceso = new RecAcceso();
        recPerfilesList = new ArrayList<String>();
        loadProfileList();

        if( tipoUserInList == null ){
            tipoUserInList = new ArrayList<String>();
            tipoUserInList.add("AGENTE");
            tipoUserInList.add(PERFIL_PROVEEDOR);
            //verifyUserInSession();
        }
        recAccesoSearchBo.setTipoUserInList(tipoUserInList);
        createModel();
    }

    private void createModel(){
        model = new LazyDataModel<RecAcceso>() {
            @Override
            public List<RecAcceso> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
                model.setRowCount(recAccesoService.countRowsUsingFilter(recAccesoSearchBo));
                return recAccesoService.getListUsingFilter(recAccesoSearchBo,first,pageSize,sortField,sortOrder);
            }
        };
    }

    public void executeSearch(){
        try {
            PropertiesCleaner.cleanObjectUsingCapitalizedMetods(recAccesoSearchBo);
        } catch (IllegalAccessException e) {
            LOGGER.error("At cleaning String fields from recAccesoSearchBo", e);
        } catch (NoSuchMethodException e) {
            LOGGER.error("At cleaning String fields from recAccesoSearchBo", e);
        } catch (InvocationTargetException e) {
            LOGGER.error("At cleaning String fields from recAccesoSearchBo", e);
        }finally {
            verifyUserInSession();
            createModel();
        }
    }

    public void cleanForm(ActionEvent actionEvent){
        recAccesoSearchBo = new RecAccesoSearchBo();
        recAcceso = new RecAcceso();
        recPerfilesList.clear();
    }

    private void verifyUserInSession() {
        UserDataBo userDataBo = SessionReader.getUserDataBo();
        if( userDataBo != null ){
            if( !PERFIL_ADMINISTRACION_VENTAS.equals( userDataBo.getEbsTipoUser()) && !tipoUserInList.contains(PERFIL_PROVEEDOR)){
                tipoUserInList.add(PERFIL_PROVEEDOR);
            }

        }
    }

    public RecAccesoService getRecAccesoService() {
        return recAccesoService;
    }

    public void setRecAccesoService(RecAccesoService recAccesoService) {
        this.recAccesoService = recAccesoService;
    }

    public RecAccesoSearchBo getRecAccesoSearchBo() {
        return recAccesoSearchBo;
    }

    public void setRecAccesoSearchBo(RecAccesoSearchBo recAccesoSearchBo) {
        this.recAccesoSearchBo = recAccesoSearchBo;
    }

    public void deleteRecAcceso(final String ebsUserId){
        try{
            if(recAccesoService.delete(ebsUserId)){
                showFacesMessage("Registro eliminado exitosamente", "", null);
            }else{
                showFacesMessage("No es posible eliminar el registro, intente más tarde", "", null);
            }
        }catch(Exception e) {
            showFacesMessage("Algún error al intentar eliminar el registro", "","error");
        }finally {
            init();
        }
    }

    public void insertRecAccesoCliente(){
        try{
            LOGGER.debug("TipoUser:"+ recAcceso.getEBS_TIPO_USER());
            PropertiesCleaner.cleanObjectUsingUppercaseMethods(recAcceso);
            recAcceso.setSTATUS(BigDecimal.ONE);
            String msgValidation = validateRecAccesCliente();
            if( msgValidation == null ){
                recAcceso.setEBS_USER_ID( PREFIX_CLIENTE_ID + recAcceso.getNUMERO_CLIENTE()  );
                if( useridExists() ){
                    showFacesMessage("Este número de cliente ya ha sido registrado, seleccione uno diferente","","error");
                    return;
                }
                if( emailExists() ){
                    showFacesMessage("El email proporcionado ya fue registrado, use uno diferente","","error");
                    return;
                }
                if(recAccesoService.save(recAcceso)){
                    showFacesMessage("Registro guardado exitosamente","",null);
                    RequestContext.getCurrentInstance().reset("form:eventDetails");
                    init();
                }else{
                    showFacesMessage("No es posible guardar el registro", "","error");
                }
            }else{
                //showFacesMessage("Faltan campos requeridos", "Al menos falta uno de los campos requeridos","error");
                showFacesMessage("Faltan campos requeridos", msgValidation,"error");
            }
        }catch(Exception e) {
            showFacesMessage("Algún error al intentar guardar", "","error");
        }finally {
            //RequestContext.getCurrentInstance().reset("form:eventDetails");
            //recAcceso = new RecAcceso();
        }
    }

    private String validateRecAccesCliente(){
        if( recAcceso == null ){
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        if( Validator.isEmptyString(recAcceso.getNUMERO_CLIENTE()) ){
            stringBuilder.append("Número de cliente es requerido<br/>");
        }
        if( Validator.isEmptyString( recAcceso.getEBS_NOMBRE() ) ){
            stringBuilder.append("Nombre de Usuario es requerido<br/>");
        }
        if( Validator.isEmptyString( recAcceso.getEBS_RFC() ) ){
            stringBuilder.append("RFC es requerido<br/>");
        }
        if( Validator.isEmptyString( recAcceso.getEBS_EMAIL() ) ){
            stringBuilder.append("Email es requerido<br/>");
        }
        if( Validator.isEmptyString( recAcceso.getEBS_TIPO_USER() ) ){
            stringBuilder.append("Tipo de usuario es requerido<br/>");
        }
        if( stringBuilder.length() == 0 ){
            return null;
        }
        return stringBuilder.toString();
    }

    private boolean useridExists(){
        if( Validator.isEmptyString(recAcceso.getEBS_USER_ID()) ){
            return false;
        }
        try {
            RecAcceso recAcceso1 = recAccesoService.findByEbsUserId(recAcceso.getEBS_USER_ID());
            if( recAcceso1 != null ){
                return true;
            }
        } catch (ValidationException e) {
            LOGGER.error("Algun error al intentar validar el Usuario",e);
        }
        return false;
    }

    private boolean emailExists (){
        RecAccesoSearchBo recAccesoSearchBoEmail = new RecAccesoSearchBo();
        recAccesoSearchBoEmail.setEmail(recAcceso.getEBS_EMAIL());
        List<RecAcceso> recAccesoList1 = recAccesoService.getListUsingFilter(recAccesoSearchBoEmail,0,100,null,null);
        if( recAccesoList1 != null && !recAccesoList1.isEmpty() ){
            return true;
        }
        return false;
    }

    public RecAcceso getRecAcceso() {
        return recAcceso;
    }

    public void setRecAcceso(RecAcceso recAcceso) {
        this.recAcceso = recAcceso;
    }

    public void sendPassword(String email, String user){
        LOGGER.debug("Envio de password al email "+ email);
        try {
            RecAcceso recAcceso = recAccesoService.findByEbsUserId(user);
            if( recAcceso != null ) {
                recAcceso.setNINTENTOS(BigDecimal.ZERO);
                String generatedPassword = RandomGenerator.generateAlphanumericValue(DEFAULT_PASSWORD_SIZE);
                recAcceso.setEBS_PW_ACTUAL(Protection.encodePassword(recAcceso.getEBS_USER_ID(), generatedPassword));
                if(recAccesoService.update(recAcceso)){
                    String content = EmailSender.properties.getProperty(EMAIL_PASSWORD_CONTENT);
                    content = content.replaceAll("~0", user);
                    content = content.replaceAll("~1", generatedPassword);
                    EmailSender.sendSimple(content, email);
                    showFacesMessage("Operaci\u00F3n exitosa", "Se a enviado el password al email " + email, null);
                }else{
                    showFacesMessage("Se genero un error", "No fue posible generar nuevo password", "error");
                }

            }else{
                showFacesMessage("Se genero un error", "No se encuentra el usuario en la BD", "error");
            }
        } catch (EmailException e) {
            LOGGER.error("Some error occurred at sending password", e);
            showFacesMessage("Se genero un error", "No fue posible enviar password", "error");
        } catch (ValidationException e) {
            LOGGER.error("Some error occurred at sending password", e);
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("Some error occurred at sending password", e);
        }
    }

    public void loadProfileList(){
        try {
            recPerfilesList.clear();
            recPerfilesList.addAll(FileMenuManager.read().getPerfilesMapList().keySet());
        } catch (IOException e) {
            LOGGER.error("IOException while reading menu from file");
        } catch (ClassNotFoundException e) {
            LOGGER.error("ClassNotFoundException while reading menu from file");
        }
    }

    public List<String> getRecPerfilesList() {
        return recPerfilesList;
    }

    public void setRecPerfilesList(List<String> recPerfilesList) {
        this.recPerfilesList = recPerfilesList;
    }

    public void unlockUser(String userId){
        recAccesoService.updateStatus(userId);
        init();
        showFacesMessage("Operaci\u00F3n exitosa", "Usuario desbloqueado", null);
    }

}