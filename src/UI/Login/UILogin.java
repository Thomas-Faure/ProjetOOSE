package UI.Login;

import Controller.LoginController;
import Controller.UILoginController;
import UI.Menu;
import UI.Task.UIAddTask;
import UI.UIGlobal;
import Main.App;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class UILogin implements UIGlobal {



	public UILogin() {

	}

	public Scene loadScene(){

		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
		}catch(Exception e){
			e.printStackTrace();
		}
		Scene scene = new Scene(root, 1000, 600);


		TextField userTextField = (TextField) scene.lookup("#inputUsername");
		PasswordField pwBox = (PasswordField) scene.lookup("#inputPassword");

		Button btn = (Button) scene.lookup("#loginButton");
		Button btnPasswordForgotten = (Button) scene.lookup("#passwordForgotten");
		btnPasswordForgotten.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				UIForgottenPassword fg = new UIForgottenPassword();
				App.setInstanceScene(fg.loadScene());

			}
		});

		/////pour le test
		Button btnTest = (Button) scene.lookup("#addTaskPage");
		btnTest.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				UIAddTask fg = new UIAddTask();
				App.setInstanceScene(fg.loadScene());

			}
		});




		final Text actiontarget = (Text) scene.lookup("#info");
		btn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				System.out.println(userTextField.getText());
				System.out.println(pwBox.getText());

				if (login(userTextField.getText(), pwBox.getText())) {
					actiontarget.setFill(Color.CHARTREUSE);
					actiontarget.setText("Connected");

					App.setMenuScene();
				



				} else {
					actiontarget.setFill(Color.FIREBRICK);
					actiontarget.setText("Error");
				}

			}
		});
		return scene;
	}

	public Stage getStage(){
		return null;
	}



	public boolean login(String username, String password) {

		if (UILoginController.getInstance().login(username, password)) {
			return true;
		} else {
			return false;
		}
	}


}
