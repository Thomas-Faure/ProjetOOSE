package Controller.User.GlobalUser;

import BuisnessLogic.User.AbstractUser;
import BuisnessLogic.User.GlobalUser;
import Facade.User.GlobalUser.GlobalUserFacade;
import Main.App;
import UI.UIError;
import UI.UIGlobal;
import UI.User.Global.AllUsersUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class AddUserController {

    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField email;
    @FXML
    private TextField city;
    @FXML
    private TextField position;
    @FXML
    private TextField phoneNumber;
    @FXML
    private CheckBox isAdmin;
    @FXML
    private Button addUserButton;
    @FXML
    private Button backButton;

    @FXML
    public void backToUser(ActionEvent actionEvent) {
        AllUsersUI user = new AllUsersUI();
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(user.loadScene().getRoot());
    }

    public void addNewUser(ActionEvent actionEvent) {
        AbstractUser user = new GlobalUser(0, username.getText(), password.getText(), firstName.getText(), lastName.getText(),
                city.getText(), phoneNumber.getText(), email.getText(), position.getText(), isAdmin.isSelected());
        if(GlobalUserFacade.getInstance().addUser(user)){
            AllUsersUI userU = new AllUsersUI();
            HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
            if(box.getChildren().size() >1 ) {
                box.getChildren().remove(1);
            }
            box.getChildren().add(userU.loadScene().getRoot());
        }else{
            System.out.println("Bug ajout idée ");
            UIError error = new UIError((UIGlobal) new AllUsersUI());
            HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
            box.getChildren().add(error.loadScene().getRoot());
            if(box.getChildren().size() >1 )
                box.getChildren().remove(1);


        }
    }
}
