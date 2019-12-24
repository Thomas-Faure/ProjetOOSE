package Controller;

import BuisnessLogic.Task.Task;

import BuisnessLogic.User.User;
import Facade.SessionFacade;
import Facade.TaskFacade;
import Main.App;
import UI.Task.TaskUI;
import UI.Task.UIAddTask;
import UI.Task.UIModifyTask;
import UI.Task.UITaskManagement;
import UI.UIError;
import UI.UIGlobal;
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

import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.soap.Text;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ErrorController implements Initializable {
    UIGlobal returnPage;

    public ErrorController(UIGlobal returnPage){
        this.returnPage = returnPage;
    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void returnAction(ActionEvent actionEvent) {
        System.out.println(returnPage.getClass().toString());
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(returnPage.loadScene().getRoot());
    }
}
