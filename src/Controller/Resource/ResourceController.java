package Controller.Resource;

import BuisnessLogic.Announcement.Announcement;
import Facade.IResourceFacade;
import Main.App;
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

        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(App.getInstanceStage());
        String path_file = file.getAbsolutePath();
        String filename = file.getName();
        System.out.println(path_file);
        System.out.println(filename);

        //DropBoxConnexion dropboxPPM = new DropBoxConnexion();
        //dropboxPPM.upload(path_file);

        //ResourceFacade.getInstance().addResource(<dropbox_path_file>,filename);
    }
}
