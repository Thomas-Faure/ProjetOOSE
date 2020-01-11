package Controller;

import BuisnessLogic.User.AbstractUser;
import Facade.SessionFacade;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Thomas Faure
 */
public class WelcomeController implements Initializable {
    @FXML
    Text username;

    /**Mehtod called on the controller creation, this method is to set the name of the user on the Text field (javafx)
     * @param location
     * @param resources
     */
    @Override

    public void initialize(URL location, ResourceBundle resources) {
        AbstractUser user = SessionFacade.getInstance().getUser();
        String firstname = user.getFirstName();
        String lastname = user.getLastName();
        username.setText(firstname+" "+lastname);

    }
}
