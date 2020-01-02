package Controller.Idea;

import BuisnessLogic.Idea.AbstractIdea;
import BuisnessLogic.Idea.Idea;
import BuisnessLogic.User.AbstractUser;
import BuisnessLogic.User.User;
import Facade.Idea.IdeaFacade;
import Main.App;
import UI.Idea.IdeaBoxUI;
import UI.Task.UITaskManagement;
import UI.UIError;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ModifyIdeaController implements Initializable {

    int id;
    private static AbstractIdea toModify;

    @FXML
    private TextField name;
    @FXML
    private TextField subject;
    @FXML
    private TextField description;
    @FXML
    private Button backButton;
    @FXML
    private Button addIdeaButton;

    public ModifyIdeaController(){
    }
    public ModifyIdeaController(int id){
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

    @FXML
    public void modifyIdea(ActionEvent actionEvent) {
        AbstractUser test = new User(0, "lauren", "lauren", "unquera","");
        //test = SessionFacade.getInstance().getUser();
        Idea idea = new Idea(0, name.getText(), description.getText(), subject.getText(), test);
        toModify = idea;
        AnchorPane toHide = (AnchorPane) App.getInstanceScene().lookup("#manager");
        toHide.setVisible(false);
        AnchorPane toShow = (AnchorPane) App.getInstanceScene().lookup("#confirm");
        toShow.setVisible(true);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        AbstractIdea ideaToModify = IdeaFacade.getInstance().getIdeaById(id);
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
        }
    }

    public void validation(ActionEvent actionEvent) {
        if(IdeaFacade.getInstance().modifyIdea((Idea)toModify)){
            HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
            IdeaBoxUI ideabox = new IdeaBoxUI();
            if(box.getChildren().size() >1 )
                box.getChildren().remove(1);
            box.getChildren().add(ideabox.loadScene().getRoot());
        }else{
            UIError error = new UIError(new UITaskManagement());
            HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
            box.getChildren().add(error.loadScene().getRoot());
            if(box.getChildren().size() >1 )
                box.getChildren().remove(1);
        }
    }

    public void refuse(ActionEvent actionEvent) {
        AnchorPane toHide = (AnchorPane) App.getInstanceScene().lookup("#confirm");
        toHide.setVisible(false);
        AnchorPane toShow = (AnchorPane) App.getInstanceScene().lookup("#manager");
        toShow.setVisible(true);
    }
}
