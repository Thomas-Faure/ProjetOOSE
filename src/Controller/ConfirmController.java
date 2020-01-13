package Controller;

import Main.App;
import javafx.scene.Node;
import javafx.scene.layout.HBox;

public class ConfirmController {
    String object;
    String action;
    Object obj;
    Node node;
    public ConfirmController(String object, String action, Object obj, Node node) {
        this.action=action;
        this.object=object;
        this.obj=obj;
        this.node=node;
    }

    public void validation(javafx.event.ActionEvent actionEvent) {


    }

    public void refuse(javafx.event.ActionEvent actionEvent) {
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(node);

    }
}
