package Controller.Meeting;

import BuisnessLogic.Meeting.AbstractMeeting;
import BuisnessLogic.Meeting.Meeting;
import BuisnessLogic.Project.AbstractProject;
import BuisnessLogic.Ticket.AbstractTicket;
import BuisnessLogic.Ticket.Ticket;
import Facade.Meeting.IMeetingFacade;
import Facade.Meeting.MeetingFacade;
import Facade.Ticket.ITicketFacade;
import Facade.Ticket.TicketFacade;
import Main.App;
import UI.Meeting.MeetingsUI;
import UI.Ticket.MyTicketUI;
import UI.Ticket.TicketUI;
import UI.UIError;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AddMeetingController implements Initializable {

    private AbstractProject project;
    private IMeetingFacade meetingFacade = MeetingFacade.getInstance();

    @FXML
    private Text projectTitle;

    @FXML
    private Text pathIndication;

    @FXML
    private DatePicker date;

    @FXML
    private TextArea place;

    public AddMeetingController(){}
    public AddMeetingController(AbstractProject project){
        this.project=project;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        pathIndication.setText("/Projects/" + project.getName() + "/New meeting");
        projectTitle.setText("project : " + project.getName());
    }

    @FXML
    void cancel(ActionEvent actionEvent){
        MeetingsUI meetingsPage = new MeetingsUI(project);
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(meetingsPage.loadScene().getRoot());
    }

    @FXML
    void addNewMeeting(ActionEvent actionEvent){
        AbstractMeeting meeting = new Meeting(1,date.getValue(),place.getText(), project.getId());
        if(meetingFacade.addMeeting(meeting)){
            MeetingsUI meetingsPage = new MeetingsUI(project);
            HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
            if(box.getChildren().size() >1 )
                box.getChildren().remove(1);
            box.getChildren().add(meetingsPage.loadScene().getRoot());
        }else{
            UIError error = new UIError(new MeetingsUI(project));
            HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
            box.getChildren().add(error.loadScene().getRoot());
            if(box.getChildren().size() >1 )
                box.getChildren().remove(1);
        }
    }
}
