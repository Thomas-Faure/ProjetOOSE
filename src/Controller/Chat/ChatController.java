package Controller;


import Facade.IChatFacade;

public class ChatController {
    static ChatController instance;
    private ChatController(){

    }
    private IChatFacade cFacade;

    public static ChatController getInstance(){
        if(instance == null){
            instance= new ChatController();
        }
        return instance;
    }
}
