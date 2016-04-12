package mx.com.ebs.inter.data.bo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by robb on 12/06/2015.
 */
public class MenuBo implements Serializable{
    /**
     * It will store "resource name" linked with "URL resource" ( XHTML files )
     */
    private Map<String , String> resourcesMap;
    private Map<String , List<String>> perfilesMapList;

    private static MenuBo instance;

    private MenuBo(){
    }

    public static MenuBo getInstance(){
        if( instance == null ){
            instance = new MenuBo();
        }
        return instance;
    }

    public Map<String, String> getResourcesMap() {
        return resourcesMap;
    }

    public void setResourcesMap(Map<String, String> resourcesMap) {
        this.resourcesMap = resourcesMap;
    }

    public Map<String, List<String>> getPerfilesMapList() {
        return perfilesMapList;
    }

    public void setPerfilesMapList(Map<String, List<String>> perfilesMapList) {
        this.perfilesMapList = perfilesMapList;
    }
}