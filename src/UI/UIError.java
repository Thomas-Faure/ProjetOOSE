package UI;

import Controller.Announcement.ModifyAnnouncementController;
import Controller.ErrorController;
import UI.UIGlobal;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.util.Callback;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class UIError implements UIGlobal {

	//page where the user will be return after he clicked on the button
	UIGlobal returnPage;

	public UIError(UIGlobal returnPage){
		this.returnPage=returnPage;

	}

	public Scene loadScene(){
		Map<Class, Callable<?>> creators = new HashMap<>();
		creators.put(ErrorController.class , new Callable<ErrorController>() {
			@Override
			public ErrorController call() throws Exception {

				return new ErrorController(returnPage);
			}
		});
		Parent root = null;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Error.fxml"));
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
