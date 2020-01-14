package UI.Announcement;
/**
 *
 * @author Thomas Faure
 */
import Controller.Announcement.ModifyAnnouncementController;

import UI.UIGlobal;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.util.Callback;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class ModifyAnnouncementUI implements UIGlobal {
	int id;

	public ModifyAnnouncementUI(int id){
		this.id=id;
	}

	public Scene loadScene(){
		Map<Class, Callable<?>> creators = new HashMap<>();
		creators.put(ModifyAnnouncementController.class , new Callable<ModifyAnnouncementController>() {
			@Override
			public ModifyAnnouncementController call() throws Exception {
				return new ModifyAnnouncementController(id);
			}
		});
		Parent root = null;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifyAnnouncementUI.fxml"));
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
