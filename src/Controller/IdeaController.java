package Controller;

import Facade.IIdeaFacade;

public class IdeaController {
    static IdeaController  instance;
    private IdeaController(){

    }
    private IIdeaFacade iFacade;
    public static IdeaController getInstance(){
        if(instance == null){
            instance= new IdeaController();
        }
        return instance;
    }
}
