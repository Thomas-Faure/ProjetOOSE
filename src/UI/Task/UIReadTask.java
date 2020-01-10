package UI.Task;
/**
 *
 * @author Thomas Faure
 */
import BuisnessLogic.Project.AbstractProject;
import Controller.Task.ReadTaskController;
import UI.UIGlobal;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.util.Callback;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class UIReadTask implements UIGlobal {
	int id;
	AbstractProject project;
	public UIReadTask(int id, AbstractProject project){
		this.id=id;this.project=project;
	}

	public Scene loadScene(){
		Map<Class, Callable<?>> creators = new HashMap<>();
		creators.put(ReadTaskController.class , new Callable<ReadTaskController>() {
			@Override
			public ReadTaskController call() throws Exception {
				return new ReadTaskController(id,project);
			}
		});
		Parent root = null;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ReadTaskUI.fxml"));
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
