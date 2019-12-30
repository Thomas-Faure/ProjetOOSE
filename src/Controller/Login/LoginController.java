package Controller.Login;

import Controller.UILoginController;
import Main.App;
import UI.Login.UIForgottenPassword;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.awt.*;

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

        if (UILoginController.getInstance().login(username, password)) {
            return true;
        } else {
            return false;
        }
    }
}
