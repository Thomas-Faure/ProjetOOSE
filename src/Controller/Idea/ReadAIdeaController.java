package Controller.Idea;

import BusinessLogic.Idea.AbstractIdea;
import Facade.Idea.IIdeaFacade;
import Facade.Idea.IdeaFacade;
import Main.App;
import UI.Idea.IdeaBoxUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Lauren Unquera - Polytech Montpellier IG4
 * @Description Cette Classe correspond au contrôleur qui gère la vue "ReadAIdeaUI".
 * Il va servir aux utilisateurs (tous) pour lire l'idée courrante qui a été
 * passée en paramètre.
 */
public class ReadAIdeaController implements Initializable {

    private IIdeaFacade ideaFacade = IdeaFacade.getInstance();
    private static AbstractIdea toRead;

    @FXML
    private TextField name;
    @FXML
    private TextField subject;
    @FXML
    private TextArea description;

    public ReadAIdeaController(){
    }

    public ReadAIdeaController(AbstractIdea idea) {
        toRead = idea;
    }

    /**
     * @author Lauren Unquera - Polytech Montpellier IG4
     * @Description Cette fonction est appelée lorsque l'utilisateur appuie
     * sur le bouton "Back".
     * Permet de revenir sur la page précédente / sur la page correspondant
     * à la boite à idée (IdeaBoxUI)
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
     * @author Lauren Unquera - Polytech Montpellier IG4
     * @Description Cette fonction permet d'initialiser la page en entrant
     * dans les champs correspondants les valeurs des attributs de l'idée
     */
    public void initialize(URL arg0, ResourceBundle arg1) {
        name.setText(toRead.getName());
        subject.setText(toRead.getSubject());
        description.setText(toRead.getDescription());
    }
}
