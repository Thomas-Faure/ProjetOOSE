package Controller.Role;

import BuisnessLogic.Role.AbstractRole;
import BuisnessLogic.Role.Role;
import BuisnessLogic.User.Member;
import Facade.Role.RoleFacade;
import Main.App;
import UI.Role.AllRolesUI;
import UI.UIError;
import UI.UIGlobal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class AddRoleController {

    private Member member;

    @FXML
    private TextField roleName;


    public AddRoleController(Member newMember){
        this.member = newMember;
    }

    @FXML
    void addNewRole(ActionEvent actionEvent){
        AbstractRole role = new Role(0, roleName.getText());
        if(RoleFacade.getInstance().addRole(role)){
            AllRolesUI roleui = new AllRolesUI(this.member);
            HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
            if(box.getChildren().size() >1 ) {
                box.getChildren().remove(1);
            }
            box.getChildren().add(roleui.loadScene().getRoot());
        }else{
            System.out.println("Bug ajout idée ");
            UIError error = new UIError((UIGlobal) new AllRolesUI(this.member));
            HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
            box.getChildren().add(error.loadScene().getRoot());
            if(box.getChildren().size() >1 )
                box.getChildren().remove(1);


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

    public void backToRole(ActionEvent actionEvent) {
        AllRolesUI roleui = new AllRolesUI(this.member);
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(roleui.loadScene().getRoot());
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
