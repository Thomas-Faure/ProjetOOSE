package UI.Task;

import BuisnessLogic.Task.Task;
import BuisnessLogic.User.User;
import Controller.TaskController;
import Controller.UILoginController;
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

import java.time.LocalDate;
import java.time.LocalTime;

public class UIModifyTask implements UIGlobal {



	public UIModifyTask(){

	}

	public boolean modifyTask(int id,String name,String description, int priority, LocalDate deadline){
		return false;
	}


	public Scene loadScene(){
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("ModifyTaskUI.fxml"));
		}catch(Exception e){
			e.printStackTrace();
		}
		Scene scene = new Scene(root, 1000, 600);

		TextField id = (TextField) scene.lookup("#id");
		TextField name = (TextField) scene.lookup("#subject");
		TextField description = (TextField) scene.lookup("#description");
		TextField deadline = (TextField) scene.lookup("#deadline");
		TextField priority = (TextField) scene.lookup("#priority");


		Button btn = (Button) scene.lookup("#modifyTask");
		btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				modifyTask(Integer.parseInt(id.getText()),name.getText(),description.getText(),Integer.parseInt(priority.getText()),LocalDate.now());


			}
		});
		Button btnBack = (Button) scene.lookup("#backButton");
		btnBack.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {





			}
		});



		return scene;
	}


}
