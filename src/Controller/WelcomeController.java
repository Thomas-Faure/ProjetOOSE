package Controller;

import Facade.SessionFacade;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class WelcomeController implements Initializable {
    @FXML
    Text username;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        username.setText(SessionFacade.getInstance().getUser().getFirstName()+" "+SessionFacade.getInstance().getUser().getLastName());

    }
}
