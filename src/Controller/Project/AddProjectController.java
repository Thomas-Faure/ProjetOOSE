package Controller.Project;

import BuisnessLogic.Project.AbstractProject;
import BuisnessLogic.Project.Project;
import BuisnessLogic.Ticket.AbstractTicket;
import BuisnessLogic.Ticket.Ticket;
import Facade.Project.IProjectFacade;
import Facade.Project.ProjectFacade;
import Main.App;
import UI.Project.ProjectUI;
import UI.Ticket.MyTicketUI;
import UI.UIError;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.time.LocalDate;

public class AddProjectController {

    private IProjectFacade projectFacade = ProjectFacade.getInstance();

    @FXML
    private TextField name;

    @FXML
    private CheckBox agile;

    @FXML
    private TextArea description;

    @FXML
    void cancel(ActionEvent actionEvent){
        ProjectUI projectPage = new ProjectUI();
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(projectPage.loadScene().getRoot());
    }

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
