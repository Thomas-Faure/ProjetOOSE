package UI.Task;

import Controller.Task.ModifyTaskController;
import UI.UIGlobal;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.util.Callback;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class UIModifyTask implements UIGlobal {





	int id;
	public UIModifyTask(int id){
		this.id=id;
	}



	public Scene loadScene(){
		Map<Class, Callable<?>> creators = new HashMap<>();
		creators.put(ModifyTaskController.class , new Callable<ModifyTaskController>() {

			@Override
			public ModifyTaskController call() throws Exception {
				System.out.println(id+"");
				return new ModifyTaskController(id);
			}

		});
		Parent root = null;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifyTaskUI.fxml"));
			loader.setControllerFactory(new Callback<Class<?>, Object>() {
				@Override
				public Object call(Class<?> param) {
					Callable<?> callable = creators.get(param);
					if (callable == null) {
						try {
							// default handling: use no-arg constructor
							return param.newInstance();
						} catch (InstantiationException | IllegalAccessException ex) {
							throw new IllegalStateException(ex);
						}
					} else {
						try {
							return callable.call();
						} catch (Exception ex) {
							throw new IllegalStateException(ex);
						}
					}
				}
			});
			root = loader.load();
		}catch(Exception e){
			e.printStackTrace();
		}
		Scene scene = new Scene(root, 1000, 600);



		return scene;
	}


}
