package Controller.Announcement;

import BuisnessLogic.Announcement.AbstractAnnouncement;
import BuisnessLogic.Announcement.Announcement;
import BuisnessLogic.Task.AbstractTask;
import BuisnessLogic.Task.Task;

import BuisnessLogic.User.User;
import Facade.AnnouncementFacade;
import Facade.SessionFacade;
import Facade.TaskFacade;
import Main.App;
import UI.Announcement.UIModifyAnnouncement;
import UI.Announcement.UIReadAnnouncement;
import UI.Task.TaskUI;
import UI.Task.UIAddTask;
import UI.Task.UIModifyTask;
import UI.Task.UIReadTask;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import javax.xml.soap.Text;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AnnouncementController implements Initializable {


    //Task Page
    @FXML
    private TextField inputSearch;
    @FXML
    private Button buttonSearch;
    @FXML
    private ListView<Announcement> announcementList;
    @FXML
    private Button addAnAnnouncement;


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        //uniquement pour la page des taches manager
        if(announcementList != null){
            //si on peut récuperer les annonces
            if(AnnouncementFacade.getInstance().getAllAnnouncements()) {
                ArrayList<Announcement> listeElement = ((ArrayList) AnnouncementFacade.getInstance().getListAnnouncements());
                ObservableList<Announcement> listView = FXCollections.observableArrayList(listeElement);

                announcementList.setItems(listView);
                announcementList.setCellFactory(param -> new Cell());
            }
        }

    }

    static class Cell extends ListCell<Announcement> {
        Announcement announcement;
        HBox hbox = new HBox();
        Image image = new Image("megaphone.png");
        ImageView img = new ImageView(image);
        Button btnR = new Button("Read");
        Button btnD = new Button("Delete");
        Button btnM = new Button("Modify");
        Label label = new Label("");
        Pane pane = new Pane();

        public Cell(){
            super();
            hbox.setSpacing(10);
            img.setFitHeight(20);
            img.setFitWidth(20);


            hbox.getChildren().addAll(img,label,pane,btnR,btnM,btnD);
            btnD.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    getListView().getItems().remove(getItem());

                    AnnouncementFacade.getInstance().deleteAnnouncement(announcement);

                }
            });
            btnM.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    UIModifyAnnouncement modifyAnnouncement = new UIModifyAnnouncement(announcement.getId());
                    HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");

                    box.getChildren().remove(1);
                    box.getChildren().add(modifyAnnouncement.loadScene().getRoot());

                }
            });
            btnR.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    System.out.println(announcement.getId()+"");
                    UIReadAnnouncement read = new UIReadAnnouncement(announcement.getId());
                    HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");

                    box.getChildren().remove(1);
                    box.getChildren().add(read.loadScene().getRoot());

                }
            });


        }
        @Override
        public void updateItem(Announcement name, boolean empty){
            super.updateItem(name,empty);
            setText(null);
            setGraphic(null);

            if(name != null && !empty){
                announcement = name;
                label.setText(name.getId()+" "+name.getTitle());
                setGraphic(hbox);
            }

        }


    }

    public AnnouncementController(){
    }

    @FXML
    void addAnnouncementPage(ActionEvent actionEvent) {
        UIAddTask addTask = new UIAddTask();
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");

        box.getChildren().remove(1);
        box.getChildren().add(addTask.loadScene().getRoot());
    }

    void search(String search){
        List<AbstractAnnouncement> AnnouncementsSearched = AnnouncementFacade.getInstance().getAnnouncementByTitle(search);

    }







}
