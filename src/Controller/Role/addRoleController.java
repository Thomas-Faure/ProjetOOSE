package Controller.Role;

import BuisnessLogic.Role.AbstractRole;
import BuisnessLogic.Role.Role;
import Facade.Role.RoleFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AddRoleController {

    @FXML
    private TextField roleName;


    public AddRoleController(){
    }

    @FXML
    void addNewRole(ActionEvent actionEvent){
        AbstractRole role = new Role(0, roleName.getText());
        if(RoleFacade.getInstance().addRole(role)){
            /*RoleMangementUI taskP = new RoleMangementUI();
            HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
            if(box.getChildren().size() >1 )
                box.getChildren().remove(1);
            box.getChildren().add(role.loadScene().getRoot());
            */
        }else{
            /*UIError error = new UIError(new RoleMangementUI());
            HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
            box.getChildren().add(error.loadScene().getRoot());
            if(box.getChildren().size() >1 )
                box.getChildren().remove(1);

             */
        }
    }
    @FXML
    void backToProjetPage(ActionEvent actionEvent){
        /*UITaskManagement task = new UITaskManagement();
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(task.loadScene().getRoot());

         */
    }
/*
    @FXML
    void addTaskPage(ActionEvent actionEvent) {
        UIAddTask addTask = new UIAddTask();
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");

        box.getChildren().remove(1);
        box.getChildren().add(addTask.loadScene().getRoot());
    }
 */
}
