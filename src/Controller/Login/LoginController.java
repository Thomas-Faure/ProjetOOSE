package Controller.Login;
/**
 *
 * @author Thomas Faure
 */
import Facade.SessionFacade;
import Main.App;
import UI.Login.UIForgottenPassword;
import UI.Role.AllRolesUI;
import UI.WelcomeUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginController {

    @FXML
    public Button loginButton;
    @FXML
    public TextField inputUsername;
    @FXML
    public PasswordField inputPassword;
    @FXML
    public Button passwordForgotten;
    @FXML
    public Text info;

    @FXML
    public void signIn(ActionEvent actionEvent) {
        if (login(inputUsername.getText(), inputPassword.getText())) {
            info.setFill(javafx.scene.paint.Color.CHARTREUSE);
            info.setText("Connected");
            App.setMenuScene();
            WelcomeUI welcomePage = new WelcomeUI();
            HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
            if(box.getChildren().size() >1 ) {
                box.getChildren().remove(1);
            }
            box.getChildren().add(welcomePage.loadScene().getRoot());

        } else {
            info.setFill(Color.FIREBRICK);
            info.setText("Error");
        }

    }

    @FXML
    public void goToPasswordForgottenPage(ActionEvent actionEvent) {
        UIForgottenPassword fg = new UIForgottenPassword();
        App.setInstanceScene(fg.loadScene());
    }

    public boolean login(String username, String password) {


        String hashtext="";

        //encrypt password to sha-1
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] messageDigest = md.digest(password.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        if (SessionFacade.getInstance().login(username, hashtext)) {
            return true;
        } else {
            return false;
        }
    }
}
