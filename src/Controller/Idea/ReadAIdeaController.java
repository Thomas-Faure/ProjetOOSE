package Controller.Idea;

import BuisnessLogic.Idea.AbstractIdea;
import Facade.Idea.IIdeaFacade;
import Facade.Idea.IdeaFacade;
import Main.App;
import UI.Idea.IdeaBoxUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ReadAIdeaController implements Initializable {

    private IIdeaFacade ideaFacade = IdeaFacade.getInstance();
    private static AbstractIdea toRead;

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

    public ReadAIdeaController(){
    }

    public ReadAIdeaController(AbstractIdea idea) {
        toRead = idea;
    }

    @FXML
    public void backToIdeaPage(ActionEvent actionEvent) {
        IdeaBoxUI idea = new IdeaBoxUI();
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(idea.loadScene().getRoot());
    }

    public void initialize(URL arg0, ResourceBundle arg1) {
        name.setText(toRead.getName());
        subject.setText(toRead.getSubject());
        description.setText(toRead.getDescription());
    }
}
