package mx.com.ebs.inter.util.file;

import mx.com.ebs.inter.data.bo.MenuBo;
import mx.com.ebs.inter.util.PropertiesLoader;
import mx.com.ebs.inter.util.UnicodeCommonWords;
import mx.com.ebs.inter.util.Variables;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.*;

public class FileMenuManager {

    private static final String ABSOLUT_FILENAME = System.getProperty("user.home")+System.getProperty("file.separator")+"appMenu.dat";
    private static final Logger LOGGER = Logger.getLogger(FileMenuManager.class);

    public static void save(MenuBo menuBo) throws IOException {
        ObjectOutputStream out = null;
        //String filename = PropertiesLoader.getProperty(Variables.APP_MENU_ABSOLUTPATH);
        File menuFile = new File(ABSOLUT_FILENAME);
        LOGGER.debug("Guardando archivo de menu en:" + ABSOLUT_FILENAME);
        out = new ObjectOutputStream( new FileOutputStream(menuFile,false));
        out.writeObject(menuBo);
        out.flush();
        out.close();
    }

    public static MenuBo read() throws IOException, ClassNotFoundException {
        //String filename = PropertiesLoader.getProperty(Variables.APP_MENU_ABSOLUTPATH);
        File menuFile = new File(ABSOLUT_FILENAME);
        MenuBo menuBo = null;
        if( menuFile.exists() && menuFile.canRead() ){
            ObjectInputStream input = new ObjectInputStream(new FileInputStream(menuFile));
            menuBo = (MenuBo)input.readObject();
        }else{
            menuBo = MenuBo.getInstance();
            populateDefaultMenu(menuBo);
            save(menuBo);
        }
        return menuBo;
    }

    private static void populateDefaultMenu( MenuBo menuBo){
        menuBo.setResourcesMap(createMapResources());
        menuBo.setPerfilesMapList(creteDefaultPerfilesMapList(menuBo.getResourcesMap().keySet()));
    }

    private static Map<String, List<String>> creteDefaultPerfilesMapList(Set<String> defaultKeys) {
        Map<String, List<String>> perfilesMapList = new TreeMap<String, List<String>>();
        List<String> listValues = new ArrayList<String>();
        listValues.addAll(defaultKeys);
        perfilesMapList.put("ADMINISTRADORGRAL",listValues);
        return perfilesMapList;
    }

    private static Map<String,String> createMapResources(){
        Map<String,String> mapResources = new HashMap<String, String>();
        mapResources.put("facturas recibidas","index.xhtml");
        mapResources.put("facturas automaticas","facturasAutomaticas.xhtml");
        mapResources.put("carga de facturas","cargarFactura.xhtml");
        mapResources.put("facturas con error","facturasError.xhtml");
        mapResources.put("facturas automaticas con error","facturasAutomaticasError.xhtml");
        mapResources.put("enviados","documentosEnviados.xhtml");
        mapResources.put("facturas","facturasAgtCfd.xhtml");
        mapResources.put("facturas CFDI","facturasAgtCfdi.xhtml");
        mapResources.put("usuarios","adminRecAcceso.xhtml");
        mapResources.put("perfiles","adminPerfiles.xhtml");
        mapResources.put("proveedores y agentes","adminRecAccesoClientes.xhtml");
        mapResources.put("administrar procesos","adminProcesos.xhtml");
        mapResources.put("cambiar contrase"+ UnicodeCommonWords.NTILDE_LOWER+"a","changePassword.xhtml");
        return mapResources;
    }

}