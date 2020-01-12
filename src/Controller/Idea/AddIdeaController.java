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
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

/**
 * @author Lauren Unquera - Polytech Montpellier IG4
 * @Description Cette Classe correspond au contrôleur qui gère la vue "AddIdeaUI".
 * Il va servir aux utilisateurs pour créer/ajouter une idée.
 */
public class AddIdeaController  {

    @FXML
    private TextField name;
    @FXML
    private TextField subject;
    @FXML
    private TextField description;

    /**
     * @author Lauren Unquera - Polytech Montpellier IG4
     * @Description Cette fonction est appelée lorsque l'utilisateur appuie
     * sur le bouton "Add Idea".
     * Elle permet donc de créer une idée. L'état de cet idée sera null et le
     * créateur sera l'utilisateur de la session
     * Une fois executée, elle redirigera l'utilisateur sur la page de la "Idea Box".
     */
    @FXML
    void addNewIdea(ActionEvent actionEvent){
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

    /**
     * @author Lauren Unquera - Polytech Montpellier IG4
     * @Description Cette fonction est appelée lorsque l'utilisateur appuie
     * sur le bouton "Back".
     * Elle permet de rediriger l'utilisateur sur la page de la "Idea Box" qui
     * était la page précédente avant qu'il arrive sur celle-ci ("AddIdeaUI").
     */
    @FXML
    void backToIdeaPage(ActionEvent actionEvent){
        IdeaBoxUI idea = new IdeaBoxUI();
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(idea.loadScene().getRoot());
    }

}
