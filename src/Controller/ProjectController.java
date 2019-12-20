package Controller;

import Facade.IProjectFacade;

public class ProjectController {
    public static ProjectController instance;
    private IProjectFacade pFacade;
    public static ProjectController getInstance(){
        if(instance == null){
            instance= new ProjectController();
        }
        return instance;
    }
}
