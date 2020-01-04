package Controller.Idea;

import BuisnessLogic.Idea.Idea;
import BuisnessLogic.User.AbstractUser;
import Facade.Idea.IdeaFacade;
import Facade.SessionFacade;
import Main.App;
import UI.Idea.IdeaBoxUI;
import UI.UIError;
import UI.UIGlobal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class AddIdeaController  {

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
    void addNewIdea(ActionEvent actionEvent){
        //AbstractUser test = new User (0, "lauren", "lauren", "unquera","");
        AbstractUser test = SessionFacade.getInstance().getUser();
        Idea idea = new Idea(0, name.getText(), description.getText(), subject.getText(), test);
        if(IdeaFacade.getInstance().addIdea(idea)){
            IdeaBoxUI ideaP = new IdeaBoxUI();
            HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
            if(box.getChildren().size() >1 ) {
                box.getChildren().remove(1);
            }
            box.getChildren().add(ideaP.loadScene().getRoot());
        }else{
            System.out.println("Bug ajout idée ");
            UIError error = new UIError((UIGlobal) new IdeaBoxUI());
            HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
            box.getChildren().add(error.loadScene().getRoot());
            if(box.getChildren().size() >1 )
                box.getChildren().remove(1);


        }
    }

    @FXML
    void backToIdeaPage(ActionEvent actionEvent){
        IdeaBoxUI idea = new IdeaBoxUI();
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(idea.loadScene().getRoot());
    }

}
