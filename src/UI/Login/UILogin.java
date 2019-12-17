package UI.Login;

import javax.swing.*;

import Controller.UILoginController;
import UI.UIGlobal;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class UILogin extends Application implements UIGlobal {

	UILoginController loginController;

	public UILogin() {
		loginController = new UILoginController();
	}

	public boolean login(String username, String password) {

		if (loginController.login(username, password)) {
			return true;
		} else {
			return false;
		}
	}
	@Override
	public void start(Stage stage) throws Exception {
		
		Parent root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
		Scene scene = new Scene(root, 1000, 600);
		   
        stage.setResizable(false);
		TextField userTextField = (TextField) scene.lookup("#inputUsername");
		PasswordField pwBox = (PasswordField) scene.lookup("#inputPassword");

		Button btn = (Button) scene.lookup("#loginButton");

		final Text actiontarget = (Text) scene.lookup("#info");
		btn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				System.out.println(userTextField.getText());
				System.out.println(pwBox.getText());

				if (login(userTextField.getText(), pwBox.getText())) {
					actiontarget.setFill(Color.CHARTREUSE);
					actiontarget.setText("Connected");
				} else {
					actiontarget.setFill(Color.FIREBRICK);
					actiontarget.setText("Error");
				}

			}
		});
		
		stage.setScene(scene);
		
		stage.show();

	}

}
