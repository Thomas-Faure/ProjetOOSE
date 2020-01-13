package Controller.Project;

import BuisnessLogic.Project.AbstractProject;
import BuisnessLogic.Project.Project;
import Facade.Project.IProjectFacade;
import Facade.Project.ProjectFacade;
import Main.App;
import UI.Project.ProjectUI;
import UI.UIError;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import java.time.LocalDate;

/**
 * Ce controller permet de gérer l'UI pour ajouter un projet
 * @author Rémi Salmi
 */
public class AddProjectController {

    private IProjectFacade projectFacade = ProjectFacade.getInstance();

    @FXML
    private TextField name;

    @FXML
    private CheckBox agile;

    @FXML
    private TextArea description;

    @FXML

    /**
     * Permet d'annuler la création d'un projet
     * @author Rémi Salmi
     */
    void cancel(ActionEvent actionEvent){
        ProjectUI projectPage = new ProjectUI();
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(projectPage.loadScene().getRoot());
    }

    /**
     * Permet de créer le nouveau projet
     * @author Rémi Salmi
     */
    @FXML
    void addNewProject(ActionEvent actionEvent){
        AbstractProject project = new Project(1,name.getText(),description.getText(), LocalDate.now(),agile.isSelected());
        if(projectFacade.addProject(project)){
            ProjectUI projectPage = new ProjectUI();
            HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
            if(box.getChildren().size() >1 )
                box.getChildren().remove(1);
            box.getChildren().add(projectPage.loadScene().getRoot());
        }else{
            UIError error = new UIError(new ProjectUI());
            HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
            box.getChildren().add(error.loadScene().getRoot());
            if(box.getChildren().size() >1 )
                box.getChildren().remove(1);
        }
    }
}
