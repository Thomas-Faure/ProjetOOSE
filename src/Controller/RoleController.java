package Controller;

import Facade.IRoleFacade;

public class RoleController {
    public static RoleController instance;
    private IRoleFacade rFacade;
    public static RoleController getInstance(){
        if(instance == null){
            instance= new RoleController();
        }
        return instance;
    }
}
