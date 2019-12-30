package Controller.Meeting;

import Facade.Meeting.IMeetingFacade;

public class MeetingController {
    public static MeetingController instance;
    private IMeetingFacade mFacade;
    private MeetingController(){

    }
    public static MeetingController getInstance(){
        if(instance == null){
            instance= new MeetingController();
        }
        return instance;
    }
}
