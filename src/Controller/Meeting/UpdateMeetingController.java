package Controller.Meeting;

import BuisnessLogic.Meeting.AbstractMeeting;
import BuisnessLogic.Project.AbstractProject;
import Facade.Meeting.IMeetingFacade;
import Facade.Meeting.MeetingFacade;
import Main.App;
import UI.Meeting.MeetingsUI;
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

/**
 * Ce controller permet la gestion de l'UI de mise a jour d'un meeting
 * @author Rémi Salmi
 */
public class UpdateMeetingController implements Initializable {

    private AbstractMeeting meeting;
    private AbstractProject project;
    private IMeetingFacade meetingFacade = MeetingFacade.getInstance();


    @FXML
    private Text pathIndication;

    @FXML
    private DatePicker date;

    @FXML
    private TextArea place;

    public UpdateMeetingController(){}
    public UpdateMeetingController(AbstractMeeting meeting, AbstractProject project){

        this.meeting=meeting;
        this.project = project;
    }

    /**
     * Initialisation de l'UI
     * @author Rémi Salmi
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        pathIndication.setText("/Projects/" + meeting.getIdProject() + "/update meeting");
        date.setValue(meeting.getDate());
        place.setText(meeting.getPlace());
    }

    /**
     * Permet d'annuler la modification d'un meeting
     * @author Rémi Salmi
     */
    @FXML
    void cancel(ActionEvent actionEvent){
        MeetingsUI meetingsPage = new MeetingsUI(project);
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(meetingsPage.loadScene().getRoot());
    }

    /**
     * Permet de modifier le meeting
     * @author Rémi Salmi
     */
    @FXML
    void updateMeeting(ActionEvent actionEvent){
        meeting.setDate(date.getValue());
        meeting.setPlace(place.getText());
        if(meetingFacade.update(meeting)){
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
