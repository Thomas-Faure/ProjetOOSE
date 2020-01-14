package Controller.Idea;

import BusinessLogic.Idea.AbstractIdea;
import BusinessLogic.Idea.Idea;
import Facade.Idea.IIdeaFacade;
import Facade.Idea.IdeaFacade;
import Main.App;
import UI.Idea.IdeaBoxUI;
import UI.UIError;
import UI.UIGlobal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Cette Classe correspond au contrôleur qui gère la vue "ModifyIdeaUI".
 * Il va servir aux utilisateurs (admins) pour modifier l'idée courrante qui a été
 * passée en paramètre.
 * @author Lauren Unquera - Polytech Montpellier IG4
 */
public class ModifyIdeaController implements Initializable {

    private IIdeaFacade ideaFacade = IdeaFacade.getInstance();
    private static AbstractIdea toModify;

    @FXML
    private Label state;
    @FXML
    private TextField name;
    @FXML
    private TextField subject;
    @FXML
    private TextField description;

    public ModifyIdeaController(){
    }

    public ModifyIdeaController(AbstractIdea idea) {
        toModify = idea;
    }

    /**
     * Permet de revenir sur la page précédente / sur la page correspondant
     * à la boite à idée (IdeaBoxUI)
     * @author Lauren Unquera - Polytech Montpellier IG4
     * @param actionEvent
     */
    @FXML
    public void backToIdeaPage(ActionEvent actionEvent) {
        IdeaBoxUI idea = new IdeaBoxUI();
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(idea.loadScene().getRoot());
    }

    /**
     * Cette fonction permet d'appliquer les modifications que l'utilisateurs
     * avait entré dans le formulaire à l'idée courrante, une nouvelle UI apparaitra pour
     * damander si l'utilisateur veut valider ou non ces modifications
     * Une fois les modifications effectuées, renvoie l'utilisateur à la page précédente / la page
     * correspondant à la boite à idée (IdeaBoxUI). Dans le cas contraire elle le signifie avec
     * une UIError.
     * @author Lauren Unquera - Polytech Montpellier IG4
     * @param actionEvent
     */
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

    /**
     * Cette fonction permet d'initialiser la page en entrant
     * dans les champs correspondants les valeurs des attributs de l'idée
     * @author Lauren Unquera - Polytech Montpellier IG4
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        name.setText(toModify.getName());
        subject.setText(toModify.getSubject());
        description.setText(toModify.getDescription());
        state.setText(toModify.getState());
    }

    /**
     * Cette fonction permet de valider la modification
     * @author Lauren Unquera - Polytech Montpellier IG4
     */
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

    /**
     * Cette fonction permet d'annuler la modification
     * @author Lauren Unquera - Polytech Montpellier IG4
     */
    public void refuse(ActionEvent actionEvent) {
        AnchorPane toHide = (AnchorPane) App.getInstanceScene().lookup("#confirm");
        toHide.setVisible(false);
        AnchorPane toShow = (AnchorPane) App.getInstanceScene().lookup("#manager");
        toShow.setVisible(true);
    }
}
