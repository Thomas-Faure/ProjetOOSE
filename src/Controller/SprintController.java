package Controller;

import Facade.ISprintFacade;

public class SprintController {
    public static SprintController instance;
    private ISprintFacade sFacade;
    public static SprintController getInstance(){
        if(instance == null){
            instance= new SprintController();
        }
        return instance;
    }
}
