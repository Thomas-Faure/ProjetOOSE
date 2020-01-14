package Controller;

import Main.App;
import UI.UIGlobal;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller of the error page
 */
public class ErrorController{
    UIGlobal returnPage;

    public ErrorController(UIGlobal returnPage){
        this.returnPage = returnPage;
    }


    /**Method called when the user click on the "back" button on the error page , and display a specific page (defined on the controller constructor)
     * @param actionEvent
     */
    public void returnAction(ActionEvent actionEvent) {
        System.out.println(returnPage.getClass().toString());
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(returnPage.loadScene().getRoot());
    }
}
