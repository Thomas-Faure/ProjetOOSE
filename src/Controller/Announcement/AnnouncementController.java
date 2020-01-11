package Controller.Announcement;
/**
 *
 * @author Thomas Faure
 */
import BuisnessLogic.Announcement.AbstractAnnouncement;
import BuisnessLogic.Announcement.Announcement;
import Facade.Announcement.AnnouncementFacade;
import Main.App;
import UI.Announcement.UIAddAnnouncement;
import UI.Announcement.UIAnnouncementManagement;
import UI.Announcement.UIModifyAnnouncement;
import UI.Announcement.UIReadAnnouncement;
import UI.Task.UITaskManagement;
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
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 *  Controller of the announcement Management page
 */
public class AnnouncementController implements Initializable {


    @FXML
    private TextField inputSearch;

    @FXML
    private ListView<AbstractAnnouncement> announcementList;

    private static AbstractAnnouncement toManage;


    //permet de garder la liste de base
    private static ObservableList<AbstractAnnouncement> listViewTemp;

    public AnnouncementController(){
    }

    /** Method call after typing a caracter on the UI's input search bar, the goal of that method is to manage the ListView to show only announcements wich correspond to the input caracters
     * @param keyEvent
     */
    @FXML
    public void searchBar(KeyEvent keyEvent) {
        if(!(inputSearch.getText().length() == 0)) {
            ArrayList<AbstractAnnouncement> array = new ArrayList<>(listViewTemp);
            ArrayList<AbstractAnnouncement> toDelete = new ArrayList<>();
            for (int i = 0; i < array.size(); ++i) {
                String inputS =inputSearch.getText();
                if(inputS.charAt(0) == '*'){
                    inputS= "\\"+inputS;
                }
                String regex = "(.*)" + inputS + "(.*)";
                if (array.get(i).getTitle().matches(regex)) {

                } else {
                    toDelete.add(array.get(i));
                }
            }
            for (AbstractAnnouncement i : toDelete) {
                array.remove(i);
            }
            ObservableList<AbstractAnnouncement> listViewT = FXCollections.observableArrayList(array);
            announcementList.setItems(listViewT);
        }else{
            announcementList.setItems(listViewTemp);
        }
    }

    /**
     * Method call on the controller creation to get announcements and put them on the listview
     * @param arg0
     * @param arg1
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        //uniquement pour la page des taches manager
        if(announcementList != null){
            //si on peut récuperer les annonces
            if(AnnouncementFacade.getInstance().getAllAnnouncements()) {
                ArrayList<Announcement> listeElement = ((ArrayList) AnnouncementFacade.getInstance().getListAnnouncements());
                ObservableList<AbstractAnnouncement> listView = FXCollections.observableArrayList(listeElement);
                listViewTemp = FXCollections.observableArrayList(listeElement);
                announcementList.setItems(listView);
                announcementList.setCellFactory(param -> new Cell());
            }
        }
    }

    public void validation(ActionEvent actionEvent) {
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(!(AnnouncementFacade.getInstance().deleteAnnouncement(toManage))){
            UIError error = new UIError(new UIAnnouncementManagement());
            box.getChildren().add(error.loadScene().getRoot());
            if(box.getChildren().size() >1 )
                box.getChildren().remove(2);
        }else {
            AnchorPane toHide = (AnchorPane) App.getInstanceScene().lookup("#confirm");
            toHide.setVisible(false);
            AnchorPane toShow = (AnchorPane) App.getInstanceScene().lookup("#manager");
            toShow.setVisible(true);
            announcementList.getItems().remove(toManage);
            listViewTemp.remove(toManage);
            toManage = null;
        }
    }

    public void refuse(ActionEvent actionEvent) {
        AnchorPane toHide = (AnchorPane) App.getInstanceScene().lookup("#confirm");
        toHide.setVisible(false);
        AnchorPane toShow = (AnchorPane) App.getInstanceScene().lookup("#manager");
        toShow.setVisible(true);
    }

    static class Cell extends ListCell<AbstractAnnouncement> {
        AbstractAnnouncement announcement;
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
                    toManage = announcement;
                    AnchorPane toHide = (AnchorPane) App.getInstanceScene().lookup("#manager");
                    toHide.setVisible(false);
                    AnchorPane toShow = (AnchorPane) App.getInstanceScene().lookup("#confirm");
                    toShow.setVisible(true);
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
                    UIReadAnnouncement read = new UIReadAnnouncement(announcement.getId(),true);
                    HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
                    box.getChildren().remove(1);
                    box.getChildren().add(read.loadScene().getRoot());
                }
            });
        }
        @Override
        public void updateItem(AbstractAnnouncement name, boolean empty){
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

    @FXML
    void addAnnouncementPage(ActionEvent actionEvent) {
        UIAddAnnouncement addAnnouncement = new UIAddAnnouncement();
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");

        box.getChildren().remove(1);
        box.getChildren().add(addAnnouncement.loadScene().getRoot());
    }








}
