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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Lauren Unquera - Polytech Montpellier IG4
 * @Description Cette Classe correspond au contrôleur qui gère la vue "AddUserUI".
 * Il va servir aux utilisateurs (admins) pour créer/ajouter un utilisateur.
 */
public class AddUserController {

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
    private Button addUserButton;
    @FXML
    private Button backButton;

    /**
     * @author Lauren Unquera - Polytech Montpellier IG4
     * @Description Cette fonction est appelée lorsque l'utilisateur appuie
     * sur le bouton "Back".
     * Elle permet de rediriger l'utilisateur sur la page de la "AllUsersUI" qui
     * était la page précédente avant qu'il arrive sur celle-ci ("AddUserUI").
     */
    @FXML
    public void backToUser(ActionEvent actionEvent) {
        AllUsersUI user = new AllUsersUI();
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(user.loadScene().getRoot());
    }

    /**
     * @author Lauren Unquera - Polytech Montpellier IG4
     * @Description Cette fonction est appelée lorsque l'utilisateur appuie
     * sur le bouton "Add User".
     * Elle permet donc de créer un compte d'utilisateur.
     * Une fois executée, elle redirigera l'utilisateur sur la page de la "AllUsersUI".
     */
    public void addNewUser(ActionEvent actionEvent) {
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
        AbstractUser user = new GlobalUser(0, username.getText(), hashtext, firstName.getText(), lastName.getText(),
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
