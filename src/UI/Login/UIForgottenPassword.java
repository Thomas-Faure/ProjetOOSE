package UI.Login;

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

public class UIForgottenPassword extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		
		//à remplacer avec la bonne page
		Parent root = FXMLLoader.load(getClass().getResource("ForgottenPasswordPage.fxml"));
		Scene scene = new Scene(root, 1000, 600);
		   
        stage.setResizable(false);
		TextField emailField = (TextField) scene.lookup("#inputEmail");
		Button btn = (Button) scene.lookup("#forgottenPasswordButton");

		final Text actiontarget = (Text) scene.lookup("#info");
		btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				System.out.println("Button pushed !");
			}
		});
		
		stage.setScene(scene);
		
		stage.show();
		
	}

}
