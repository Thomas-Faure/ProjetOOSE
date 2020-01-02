package UI.Announcement;

import Controller.Announcement.ReadAnnouncementController;

import UI.UIGlobal;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.util.Callback;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class UIReadAnnouncement implements UIGlobal {
	boolean adminPanel = false;
	int id;
	public UIReadAnnouncement(int id,boolean adminPanel){
		this.adminPanel=adminPanel;
		this.id=id;
	}

	public Scene loadScene(){
		Map<Class, Callable<?>> creators = new HashMap<>();
		creators.put(ReadAnnouncementController.class , new Callable<ReadAnnouncementController>() {
			@Override
			public ReadAnnouncementController call() throws Exception {
				return new ReadAnnouncementController(id,adminPanel);
			}
		});
		Parent root = null;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ReadAnnouncementUI.fxml"));
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
