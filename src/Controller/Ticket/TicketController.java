package Controller;

import Facade.ITicketFacade;

public class TicketController {

    public static TicketController instance;
    private ITicketFacade tFacade;
    private TicketController(){

    }
    public static TicketController getInstance(){
        if(instance == null){
            instance = new TicketController();
        }
        return instance;
    }
}
