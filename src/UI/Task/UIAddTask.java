package UI.Task;
/**
 *
 * @author Thomas Faure
 */
import BuisnessLogic.Project.AbstractProject;
import Controller.IController;
import Controller.Task.AddTaskController;
import UI.UIGlobal;
import UI.UIGlobalWithController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.util.Callback;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class UIAddTask {
	AbstractProject project;
	public static UIGlobalWithController ui;

	public UIAddTask(AbstractProject project, UIGlobalWithController ui){
		this.project = project;
		this.ui=ui;
	};
	public Scene loadScene(){
		Map<Class, Callable<?>> creators = new HashMap<>();
		creators.put(AddTaskController.class , new Callable<AddTaskController>() {
			@Override
			public AddTaskController call() throws Exception {
				return new AddTaskController(project,ui);
			}
		});
		Parent root = null;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("AddTaskUI.fxml"));
			loader.setControllerFactory(new Callback<Class<?>, Object>() {

				@Override
				public Object call(Class<?> param) {
					Callable<?> callable = creators.get(param);
					if (callable == null) {
						try {
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
