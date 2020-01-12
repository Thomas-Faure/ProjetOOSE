package Controller.User.GlobalUser;

import BuisnessLogic.User.GlobalUser;
import Facade.User.GlobalUser.GlobalUserFacade;
import Facade.User.GlobalUser.IGlobalUserFacade;
import Main.App;
import UI.User.Global.AllUsersUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Lauren Unquera - Polytech Montpellier IG4
 * @Description Cette Classe correspond au contrôleur qui gère la vue "ReadAUserUI".
 * Il va servir aux utilisateurs (tous) pour lire l'utilisateur courrant qui a été
 * passée en paramètre.
 */
public class ReadAUserController implements Initializable {

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

    private IGlobalUserFacade userFacade = GlobalUserFacade.getInstance();
    private static GlobalUser toRead;

    public ReadAUserController(){
    }

    public ReadAUserController(GlobalUser user) {
        toRead = user;
    }

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
     * @Description Cette fonction permet d'initialiser la page en entrant
     * dans les champs correspondants les valeurs des attributs de l'utilisateur
     */
    public void initialize(URL arg0, ResourceBundle arg1) {
        username.setText(toRead.getUsername());
        password.setText(toRead.getPassword());
        firstName.setText(toRead.getFirstName());
        lastName.setText(toRead.getLastName());
        email.setText(toRead.getEmail());
        city.setText(toRead.getCity());
        position.setText(toRead.getPosition());
        phoneNumber.setText(toRead.getPhoneNumber());
        if (toRead.isAdmin()){
            isAdmin.setSelected(true);
        }

    }



}
