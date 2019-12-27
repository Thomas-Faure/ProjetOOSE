package Controller.Resource;

import BuisnessLogic.Announcement.Announcement;
import Facade.IResourceFacade;
import Main.App;
import UI.Announcement.UIAddAnnouncement;
import UI.Ressource.ResourceUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;

public class ResourceController implements Initializable {


    @FXML
    private Button importFile;

    @FXML
    private TextField inputSearch;

    @FXML
    private Button buttonSearch;

    @FXML
    private ListView<Announcement> fileList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void uploadFile(ActionEvent actionEvent) {
        //Creation d'une FileChooser UI que l'on appelle
        //Nous retourne le path du fichier
        //Connection au DropBox
        //Preparation de l'import
        //Realisation de l'import
    }
}
