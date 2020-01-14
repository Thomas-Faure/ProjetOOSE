package Controller.User.GlobalUser;

import BusinessLogic.User.GlobalUser;
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

/**
 * @author Lauren Unquera - Polytech Montpellier IG4
 * @Description Cette Classe correspond au contrôleur qui gère la vue "ModifyUserUI".
 * Il va servir aux utilisateurs (admins) pour modifier l'utilisateur courrant qui a été
 * passé en paramètre.
 */
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

    /**
     * @author Lauren Unquera - Polytech Montpellier IG4
     * @Description Permet de revenir sur la page précédente / sur la page correspondant
     * à la gestion de l'ensemble des utilisateurs (AllUsersUI)
     * @param actionEvent
     */
    public void backToUser(ActionEvent actionEvent) {
        AllUsersUI user = new AllUsersUI();
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(user.loadScene().getRoot());
    }

    /**
     * @author Lauren Unquera - Polytech Montpellier IG4
     * @Description Cette fonction permet d'appliquer les modifications qui avaient été
     * entrées dans le formulaire pour la modification de l'utilisateur courrant,
     * une nouvelle UI apparaitra pour demander si l'utilisateur (admin) veut valider ou non ces modifications
     * Une fois les modifications effectuées, renvoie l'utilisateur (admin) à la page précédente / la page
     * correspondant à la gestion de l'ensemble des utilisateurs
     * (AllUsersUI). Dans le cas contraire elle le signifie avec une UIError.
     * @param actionEvent
     */
    @FXML
    void modifyUser(ActionEvent actionEvent){
        if (! password.getText().isEmpty()){
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
            toModify.setPassword(hashtext);
        }
        toModify.setUsername(username.getText());
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

    /**
     * @author Lauren Unquera - Polytech Montpellier IG4
     * @Description Cette fonction permet d'initialiser la page en entrant
     * dans les champs correspondants les valeurs des attributs de l'utilisateur
     * qu'on veut modifier
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        username.setText(toModify.getUsername());
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

    /**
     * @author Lauren Unquera - Polytech Montpellier IG4
     * @Description Cette fonction permet de valider la modification
     */
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

    /**
     * @author Lauren Unquera - Polytech Montpellier IG4
     * @Description Cette fonction permet d'annuler la modification
     */
    public void refuse(ActionEvent actionEvent) {
        AnchorPane toHide = (AnchorPane) App.getInstanceScene().lookup("#confirm");
        toHide.setVisible(false);
        AnchorPane toShow = (AnchorPane) App.getInstanceScene().lookup("#manager");
        toShow.setVisible(true);
    }
}
