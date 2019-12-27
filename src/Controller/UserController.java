package Controller;

import Facade.User.IUserFacade;

public class UserController {
    public static UserController instance;
    private IUserFacade uFacade;
    private UserController(){

    }
    public static UserController getInstance(){
        if(instance == null){
            instance= new UserController();
        }
        return instance;
    }
}
