package Controller.Login;
/**
 *
 * @author Thomas Faure
 */
import Facade.SessionFacade;
import Main.App;
import UI.User.Global.CreateAccountUI;
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


/**
 * A controller to manage login
 */
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

    /**
     * method call when push "login" button on the UI, this method try to log the user with
     * the login and password provided by UI's inputs
     * @param actionEvent
     */
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



    /**
     * Method to try to log the user
     * @param username
     * @param password
     * @return
     */
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

    /**
     * @author Lauren Unquera - Polytech Montpellier IG4
     * @Description Cette fonction permet de rediriger l'utilisateur
     * sur la page de création de compte
     * @param actionEvent
     */
    public void addUser(ActionEvent actionEvent) {
        CreateAccountUI user = new CreateAccountUI();
        App.setInstanceScene(user.loadScene());

    }
}
