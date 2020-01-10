package Controller.User.GlobalUser;

import BuisnessLogic.User.GlobalUser;
import Facade.SessionFacade;
import Facade.User.GlobalUser.GlobalUserFacade;
import Facade.User.GlobalUser.IGlobalUserFacade;
import Main.App;
import UI.UIError;
import UI.UIGlobal;
import UI.User.Global.AllUsersUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;

public class ModifyUserController implements Initializable {

    private IGlobalUserFacade userFacade = GlobalUserFacade.getInstance();
    private GlobalUser toModify;

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
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
    private Button modifyUserButton;
    @FXML
    private Button backButton;

    public ModifyUserController(){
    }

    public ModifyUserController(GlobalUser user) {
        toModify = user;
    }

    public void backToUser(ActionEvent actionEvent) {
        AllUsersUI user = new AllUsersUI();
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(user.loadScene().getRoot());
    }

    @FXML
    void modifyUser(ActionEvent actionEvent){
        String hashtext="";
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] messageDigest = md.digest(password.getText().getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        toModify.setUsername(username.getText());
        toModify.setPassword(hashtext);
        toModify.setFirstName(firstName.getText());
        toModify.setLastName(lastName.getText());
        toModify.setCity(city.getText());
        toModify.setPhoneNumber(phoneNumber.getText());
        toModify.setEmail(email.getText());
        toModify.setPosition(position.getText());
        toModify.setAdmin(isAdmin.isSelected());
        if(toModify.getId() == SessionFacade.getInstance().getUser().getId()){
            SessionFacade.getInstance().setUser(toModify);
        }
        if(userFacade.modifyUser(toModify)){
            AllUsersUI user = new AllUsersUI();
            HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
            if(box.getChildren().size() >1 )
                box.getChildren().remove(1);
            box.getChildren().add(user.loadScene().getRoot());
        }else{
            UIError error = new UIError( new AllUsersUI());
            HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
            box.getChildren().add(error.loadScene().getRoot());
            if(box.getChildren().size() >1 )
                box.getChildren().remove(1);
        }
    }
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        username.setText(toModify.getUsername());
        password.setText(toModify.getPassword());
        firstName.setText(toModify.getFirstName());
        lastName.setText(toModify.getLastName());
        city.setText(toModify.getCity());
        phoneNumber.setText(toModify.getPhoneNumber());
        email.setText(toModify.getEmail());
        position.setText(toModify.getPosition());
        if(toModify.isAdmin()){
            isAdmin.setSelected(true);
        }
    }

    public void validation(ActionEvent actionEvent) {
        if(GlobalUserFacade.getInstance().modifyUser(toModify)){
            AllUsersUI user = new AllUsersUI();
            HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
            if(box.getChildren().size() >1 )
                box.getChildren().remove(1);
            box.getChildren().add(user.loadScene().getRoot());
        }else{
            UIError error = new UIError((UIGlobal) new AllUsersUI());
            HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
            box.getChildren().add(error.loadScene().getRoot());
            if(box.getChildren().size() >1 )
                box.getChildren().remove(1);
        }
    }

    public void refuse(ActionEvent actionEvent) {
        AnchorPane toHide = (AnchorPane) App.getInstanceScene().lookup("#confirm");
        toHide.setVisible(false);
        AnchorPane toShow = (AnchorPane) App.getInstanceScene().lookup("#manager");
        toShow.setVisible(true);
    }
}
