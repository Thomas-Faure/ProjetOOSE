package Controller;

import Main.App;
import UI.UIGlobal;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;

import java.net.URL;
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
