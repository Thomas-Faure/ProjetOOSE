package Controller;

import Facade.IMeetingFacade;

import javax.sound.midi.MetaEventListener;

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
