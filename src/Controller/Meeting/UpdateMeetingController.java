package Controller.Meeting;

import BuisnessLogic.Meeting.AbstractMeeting;
import BuisnessLogic.Ticket.AbstractTicket;
import Facade.Meeting.IMeetingFacade;
import Facade.Meeting.MeetingFacade;
import Main.App;
import UI.Meeting.MeetingsUI;
import UI.Ticket.TicketUI;
import UI.UIError;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class UpdateMeetingController implements Initializable {

    private AbstractMeeting meeting;
    private IMeetingFacade meetingFacade = MeetingFacade.getInstance();


    @FXML
    private Text pathIndication;

    @FXML
    private DatePicker date;

    @FXML
    private TextArea place;

    public UpdateMeetingController(){}
    public UpdateMeetingController(AbstractMeeting meeting){
        this.meeting=meeting;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        pathIndication.setText("/Projects/" + meeting.getIdProject() + "/update meeting");
        date.setValue(meeting.getDate());
        place.setText(meeting.getPlace());
    }

    @FXML
    void cancel(ActionEvent actionEvent){
        MeetingsUI meetingsPage = new MeetingsUI();
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(meetingsPage.loadScene().getRoot());
    }

    @FXML
    void updateMeeting(ActionEvent actionEvent){
        meeting.setDate(date.getValue());
        meeting.setPlace(place.getText());
        if(meetingFacade.update(meeting)){
            MeetingsUI meetingsPage = new MeetingsUI();
            HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
            if(box.getChildren().size() >1 )
                box.getChildren().remove(1);
            box.getChildren().add(meetingsPage.loadScene().getRoot());
        }else{
            UIError error = new UIError(new MeetingsUI());
            HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
            box.getChildren().add(error.loadScene().getRoot());
            if(box.getChildren().size() >1 )
                box.getChildren().remove(1);
        }
    }
}
