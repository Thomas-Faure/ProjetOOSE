package Controller.Resource;

import BusinessLogic.Project.AbstractProject;
import BusinessLogic.Ressource.AbstractResource;
import BusinessLogic.Ressource.Resource;
import Facade.ResourceFacade;
import Main.App;
import UI.Ressource.ResourceUI;
import UI.Ticket.TicketUI;
import UI.UIError;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import java.io.File;


public class ResourceController implements Initializable {

    @FXML
    private Text titlePath;

    @FXML
    private Button importFile;

    @FXML
    private TextField inputSearch;

    @FXML
    private Button buttonSearch;

    @FXML
    private Button backButton;

    @FXML
    private ListView<AbstractResource> resourceList;

    private static ObservableList<AbstractResource> listViewTemp;
    private static AbstractResource resourceToManage;

    private AbstractProject project;


    private DropBoxConnexion dropboxPPM;

    public ResourceController(AbstractProject project){
        this.project = project;
        this.dropboxPPM = new DropBoxConnexion();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        titlePath.setText("Project/"+project.getName()+"/Resources/");
        if(resourceList != null){
            List<AbstractResource> listeElement = ResourceFacade.getInstance().getListResourceByProject(project.getId());
            ObservableList<AbstractResource> listView = FXCollections.observableArrayList(listeElement);
            listViewTemp = FXCollections.observableArrayList(listeElement);
            resourceList.setItems(listView);
            resourceList.setCellFactory(param -> new Cell(project));
        }
    }

    @FXML
    void uploadFile(ActionEvent actionEvent) {

        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(App.getInstanceStage());
        String path_file = file.getAbsolutePath();
        String filename = file.getName();



        String dropboxpath_file = "/"+project.getName()+"/"+filename;

        dropboxPPM.uploadFile(path_file,dropboxpath_file);

        AbstractResource resource = new Resource(dropboxpath_file,filename,project.getId());
        ResourceFacade.getInstance().addResource(resource);

        ResourceUI resourceUI = new ResourceUI(project);
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(resourceUI.loadScene().getRoot());
    }

    @FXML
    public void validation(ActionEvent actionEvent) {

        DropBoxConnexion dropboxPPM = new DropBoxConnexion();
        dropboxPPM.deleteFile(resourceToManage.getPath());
        ResourceFacade.getInstance().deleteResource(resourceToManage.getResourceID());

        ResourceUI resourceUI = new ResourceUI(project);
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(resourceUI.loadScene().getRoot());
    }

    @FXML
    public void refuse(ActionEvent actionEvent) {
        AnchorPane toHide = (AnchorPane) App.getInstanceScene().lookup("#confirm");
        toHide.setVisible(false);
        AnchorPane toShow = (AnchorPane) App.getInstanceScene().lookup("#manager");
        toShow.setVisible(true);
    }


    static class Cell extends ListCell<AbstractResource> {
        AbstractResource resource;
        AbstractProject project;
        HBox hbox = new HBox();
        Button btnDelete = new Button("Delete");
        Button btnDownload = new Button("Download");
        Label label = new Label("");
        Pane pane = new Pane();

        public Cell(AbstractProject project){
            super();
            this.project = project;
            hbox.setSpacing(10);
            hbox.getChildren().addAll(label,pane,btnDownload,btnDelete);
            btnDelete.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    resourceToManage = resource;
                    AnchorPane toHide = (AnchorPane) App.getInstanceScene().lookup("#manager");
                    toHide.setVisible(false);
                    AnchorPane toShow = (AnchorPane) App.getInstanceScene().lookup("#confirm");
                    toShow.setVisible(true);

                }
            });

            btnDownload.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    DropBoxConnexion dropboxPPM = new DropBoxConnexion();

                    FileChooser fileChooser = new FileChooser();
                    File file = fileChooser.showSaveDialog(App.getInstanceStage());
                    String downloadpath = file.getAbsolutePath();

                    dropboxPPM.downloadFile(downloadpath,resource.getPath());
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
