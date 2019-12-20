package Controller;

import Facade.ISessionFacade;

public class LoginController {
    private ISessionFacade sFacade;
    public static LoginController instance;

    public static LoginController getInstance(){
        if(instance == null){
            instance= new LoginController();
        }
        return instance;
    }
}
