package Controller;

import Facade.IResourceFacade;

public class ResourceController {
    private IResourceFacade rFacade;
    public static ResourceController instance;
    public static ResourceController getInstance(){
        if(instance == null){
            instance= new ResourceController();
        }
        return instance;
    }
}
