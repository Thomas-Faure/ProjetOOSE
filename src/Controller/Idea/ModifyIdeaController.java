package Controller.Idea;

import BuisnessLogic.Idea.Idea;
import BuisnessLogic.User.AbstractUser;
import BuisnessLogic.User.User;
import Facade.Idea.IdeaFacade;
import Main.App;
import UI.Idea.IdeaBoxUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class ModifyIdeaController {

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
        if(IdeaFacade.getInstance().modifyIdea(idea)){
            IdeaBoxUI ideaP = new IdeaBoxUI();
            /*HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
            if(box.getChildren().size() >1 ) {
                box.getChildren().remove(1);
            }
            box.getChildren().add(ideaP.loadScene().getRoot());*/
            System.out.println("Idée modifiée");
        }else{
            System.out.println("Bug Modification idée ");
            //pas ok
        }
    }
}
