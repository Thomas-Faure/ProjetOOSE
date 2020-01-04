package Controller.Idea;

import BuisnessLogic.Idea.AbstractIdea;
import BuisnessLogic.Idea.Idea;
import Facade.Idea.IIdeaFacade;
import Facade.Idea.IdeaFacade;
import Main.App;
import UI.Idea.IdeaBoxUI;
import UI.UIError;
import UI.UIGlobal;
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

    private IIdeaFacade ideaFacade = IdeaFacade.getInstance();
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

    public ModifyIdeaController(AbstractIdea idea) {
        toModify = idea;
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
    void modifyIdea(ActionEvent actionEvent){
        toModify.setName(name.getText());
        toModify.setDescription(description.getText());
        toModify.setSubject(subject.getText());
        if(ideaFacade.modifyIdea(toModify)){
            IdeaBoxUI ideaPage = new IdeaBoxUI();
            HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
            if(box.getChildren().size() >1 )
                box.getChildren().remove(1);
            box.getChildren().add(ideaPage.loadScene().getRoot());
        }else{
            UIError error = new UIError((UIGlobal) new IdeaBoxUI());
            HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
            box.getChildren().add(error.loadScene().getRoot());
            if(box.getChildren().size() >1 )
                box.getChildren().remove(1);
        }
    }
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        name.setText(toModify.getName());
        subject.setText(toModify.getSubject());
        description.setText(toModify.getDescription());
    }

    public void validation(ActionEvent actionEvent) {
        if(IdeaFacade.getInstance().modifyIdea((Idea)toModify)){
            HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
            IdeaBoxUI ideabox = new IdeaBoxUI();
            if(box.getChildren().size() >1 )
                box.getChildren().remove(1);
            box.getChildren().add(ideabox.loadScene().getRoot());
        }else{
            UIError error = new UIError((UIGlobal) new IdeaBoxUI());
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
