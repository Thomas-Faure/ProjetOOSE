package Controller.Meeting;

import BusinessLogic.Meeting.AbstractMeeting;
import BusinessLogic.Meeting.Meeting;
import BusinessLogic.Project.AbstractProject;
import Facade.Meeting.IMeetingFacade;
import Facade.Meeting.MeetingFacade;
import Main.App;
import UI.Meeting.AddMeetingUI;
import UI.Meeting.MeetingUI;
import UI.Meeting.UpdateMeetingUI;
import UI.Project.ReadProjectUI;
import UI.UIError;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


/**
 * Ce controller permet de gérer l'UI Meeting
 * @author Rémi Salmi
 */
public class MeetingController implements Initializable {

    private static AbstractProject project;

    private IMeetingFacade meetingFacade = MeetingFacade.getInstance();

    @FXML
    private TextField inputSearch ;

    @FXML
    private ListView<AbstractMeeting> meetingsList ;

    @FXML
    private Text pathIndication;

    private static AbstractMeeting toManage;

    public MeetingController(){};
    public MeetingController(AbstractProject project){
        this.project=project;
    }

    //permet de garder la liste de base
    private static ObservableList<AbstractMeeting> listViewTemp;


    /**
     * Permet de rechercher un meeting dans la liste
     * @author Rémi Salmi
     */
    @FXML
    public void searchBar(KeyEvent keyEvent) {

        if(!(inputSearch.getText().length() == 0)) {
            ArrayList<AbstractMeeting> array = new ArrayList<>(listViewTemp);
            ArrayList<AbstractMeeting> toDelete = new ArrayList<>();
            for (int i = 0; i < array.size(); ++i) {
                String inputS =inputSearch.getText();
                if(inputS.charAt(0) == '*'){
                    inputS= "\\"+inputS;
                }
                String regex = "(.*)" + inputS + "(.*)";
                if (array.get(i).getDate().toString().matches(regex)) {

                } else {
                    toDelete.add(array.get(i));
                }
            }

            for (AbstractMeeting i : toDelete) {
                array.remove(i);
            }
            ObservableList<AbstractMeeting> listViewT = FXCollections.observableArrayList(array);
            meetingsList.setItems(listViewT);

        }else{
            meetingsList.setItems(listViewTemp);
        }

    }

    /**
     * Initialisation de l'UI
     * @author Rémi Salmi
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        if(meetingsList != null){
            ArrayList<Meeting> listeElement = ((ArrayList) meetingFacade.getMeetingByProject(project));
            ObservableList<AbstractMeeting> listView = FXCollections.observableArrayList(listeElement);
            listViewTemp = FXCollections.observableArrayList(listeElement);
            meetingsList.setItems(listView);
            meetingsList.setCellFactory(param -> new MeetingController.Cell());
            pathIndication.setText("/Projects/" + project.getName() + "/Meetings");
        }

    }


    /**
     * Permet de valider la suppression d'un meeting
     * @author Rémi Salmi
     */
    public void validation(ActionEvent actionEvent) {
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(!(meetingFacade.deleteMeeting(toManage))){
            UIError error = new UIError(new MeetingUI(project));
            box.getChildren().add(error.loadScene().getRoot());
            if(box.getChildren().size() >1 )
                box.getChildren().remove(2);
        }else {
            AnchorPane toHide = (AnchorPane) App.getInstanceScene().lookup("#confirm");
            toHide.setVisible(false);
            AnchorPane toShow = (AnchorPane) App.getInstanceScene().lookup("#manager");
            toShow.setVisible(true);
            meetingsList.getItems().remove(toManage);
            listViewTemp.remove(toManage);
            toManage = null;
        }
    }

    /**
     * Permet d'annuler la suppression d'un meeting
     * @author Rémi Salmi
     */
    public void refuse(ActionEvent actionEvent) {
        AnchorPane toHide = (AnchorPane) App.getInstanceScene().lookup("#confirm");
        toHide.setVisible(false);
        AnchorPane toShow = (AnchorPane) App.getInstanceScene().lookup("#manager");
        toShow.setVisible(true);
    }

    /**
     * Permet de se diriger vers la page de création d'un meeting
     * @author Rémi Salmi
     */
    @FXML
    void newMeeting(ActionEvent actionEvent) {
        AddMeetingUI addMeeting = new AddMeetingUI(project);
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");

        box.getChildren().remove(1);
        box.getChildren().add(addMeeting.loadScene().getRoot());
    }

    /**
     * Permet de retourner sur la page précédente
     * @author Rémi Salmi
     */
    @FXML
    void back(ActionEvent actionEvent){
        ReadProjectUI projectPage = new ReadProjectUI(project);
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(projectPage.loadScene().getRoot());
    }


    static class Cell extends ListCell<AbstractMeeting> {
        AbstractMeeting meeting;
        HBox hbox = new HBox();
        Image image = new Image("megaphone.png");
        ImageView img = new ImageView(image);
        Button btnD = new Button("Delete");
        Button btnU = new Button("Update");
        Label label = new Label("");
        Pane pane = new Pane();

        public Cell() {
            super();
            hbox.setSpacing(10);
            img.setFitHeight(20);
            img.setFitWidth(20);

            hbox.getChildren().addAll(img, label, pane, btnD, btnU);
            btnD.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    toManage = meeting;
                    AnchorPane toHide = (AnchorPane) App.getInstanceScene().lookup("#manager");
                    toHide.setVisible(false);
                    AnchorPane toShow = (AnchorPane) App.getInstanceScene().lookup("#confirm");
                    toShow.setVisible(true);
                }
            });
            btnU.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    UpdateMeetingUI updatePage = new UpdateMeetingUI(meeting, project );
                    HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");

                    box.getChildren().remove(1);
                    box.getChildren().add(updatePage.loadScene().getRoot());

                }
            });


        }
        @Override
        public void updateItem(AbstractMeeting name, boolean empty){
            super.updateItem(name,empty);
            setText(null);
            setGraphic(null);
            if(name != null && !empty){
                meeting = name;
                label.setText(meeting.getDate() + " | " + meeting.getPlace());
                setGraphic(hbox);
            }

        }

    }

}
