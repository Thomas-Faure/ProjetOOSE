package UI.Login;

import Main.App;
import UI.UIGlobal;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class UIForgottenPassword implements UIGlobal {


	public UIForgottenPassword(){

	}


	public Scene loadScene(){
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("ForgottenPasswordPage.fxml"));
		}catch(Exception e){
			e.printStackTrace();
		}
		Scene scene = new Scene(root, 1000, 600);

		TextField mailTextField = (TextField) scene.lookup("#inputMail");


		Button btn = (Button) scene.lookup("#getNewPassword");
		Button btnBack = (Button) scene.lookup("#backButton");
		btnBack.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				UILogin login = new UILogin();
				App.setInstanceScene(login.loadScene());



			}
		});



		return scene;
	}


}
