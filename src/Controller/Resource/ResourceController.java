package Controller.Resource;

import BuisnessLogic.Announcement.AbstractAnnouncement;
import BuisnessLogic.Announcement.Announcement;
import BuisnessLogic.Ressource.AbstractResource;
import BuisnessLogic.Ressource.Resource;
import Controller.Announcement.AnnouncementController;
import Facade.Announcement.AnnouncementFacade;
import Facade.IResourceFacade;
import Facade.ResourceFacade;
import Main.App;
import UI.Announcement.UIModifyAnnouncement;
import UI.Announcement.UIReadAnnouncement;
import UI.Ressource.ResourceUI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import java.io.File;


public class ResourceController implements Initializable {


    @FXML
    private Button importFile;

    @FXML
    private TextField inputSearch;

    @FXML
    private Button buttonSearch;

    @FXML
    private ListView<AbstractResource> resourceList;

    private static ObservableList<AbstractResource> listViewTemp;

    private int projectID;

    private DropBoxConnexion dropboxPPM;

    public ResourceController(int projectID){
        this.projectID = projectID;
        this.dropboxPPM = new DropBoxConnexion();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(resourceList != null){

            List<AbstractResource> listeElement = ResourceFacade.getInstance().getListResourceByProject(1);
            System.out.println("listeElement size :"+listeElement.size());
            ObservableList<AbstractResource> listView = FXCollections.observableArrayList(listeElement);
            listViewTemp = FXCollections.observableArrayList(listeElement);
            resourceList.setItems(listView);
            resourceList.setCellFactory(param -> new Cell());
            System.out.println(resourceList.getItems());
        }
    }

    @FXML
    void uploadFile(ActionEvent actionEvent) {

        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(App.getInstanceStage());
        String path_file = file.getAbsolutePath();
        String filename = file.getName();
        System.out.println(path_file);
        System.out.println(filename);

        System.out.println("ID DU PROJET COURANT: "+projectID);


        //Project p = ProjectFacade.getInstance().getProjectById();
        //
        //String dropboxpath_file = "/p.getName()/<filename>"
        String dropboxpath_file = "/test/"+filename;
        dropboxPPM.uploadFile(path_file,dropboxpath_file);

        AbstractResource resource = new Resource(dropboxpath_file,filename);
        ResourceFacade.getInstance().addResource(resource);
    }


    static class Cell extends ListCell<AbstractResource> {
        AbstractResource resource;
        HBox hbox = new HBox();
        Button btnDelete = new Button("Delete");
        Button btnDownload = new Button("Download");
        Label label = new Label("");
        Pane pane = new Pane();
        public Cell(){
            super();
            hbox.setSpacing(10);
            hbox.getChildren().addAll(label,pane,btnDownload,btnDelete);
            btnDelete.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {

                    System.out.println("Supprimer le fichier !!");
                }
            });

            btnDownload.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {

                    System.out.println("Télécharger le fichier !!");
                }
            });


        }
        @Override
        public void updateItem(AbstractResource name, boolean empty){
            super.updateItem(name,empty);
            setText(null);
            setGraphic(null);
            if(name != null && !empty){
                resource = name;
                label.setText(name.getFilename());
                setGraphic(hbox);
            }
        }

    }
}
