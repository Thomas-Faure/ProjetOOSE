package Controller.Idea;


import Main.App;
import UI.Idea.AddIdeaUI;
import UI.Idea.IdeaBoxUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class IdeaBoxController implements Initializable {

    int id;

    @FXML
    private TextField name;
    @FXML
    private TextField subject;
    @FXML
    private TextField description;

    public IdeaBoxController(){
    }
    public IdeaBoxController(int id){
        this.id=id;
    }

    @FXML
    public void backToIdeaPage(ActionEvent actionEvent) {
        IdeaBoxUI idea = new IdeaBoxUI();
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(idea.loadScene().getRoot());
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        /*AbstractIdea ideas = IdeaFacade.getInstance().getIdeaById(id);
        if(ideaToModify != null) {
            name.setText(ideaToModify.getName());
            subject.setText(ideaToModify.getSubject());
            description.setText(ideaToModify.getDescription());
        }else{
            UIError error = new UIError(new UITaskManagement());
            HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
            box.getChildren().add(error.loadScene().getRoot());
            if(box.getChildren().size() >1 )
                box.getChildren().remove(2);
        }    */

    }

    public void addNewIdea(ActionEvent actionEvent) {
        AddIdeaUI idea = new AddIdeaUI();
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(idea.loadScene().getRoot());
    }
}
