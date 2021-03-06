package UI.Task;
/**
 *
 * @author Thomas Faure
 */
import BusinessLogic.Project.AbstractProject;
import Controller.IController;
import Controller.Task.TaskManagerController;
import UI.UIGlobalWithController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.util.Callback;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class TaskManagementUI implements UIGlobalWithController {

	AbstractProject project;


	public static UIGlobalWithController ui;
	public static IController controller;
	public TaskManagementUI(AbstractProject project){
		this.project = project;ui=this;
	};

	public Scene loadScene(){
		Map<Class, Callable<?>> creators = new HashMap<>();
		creators.put(TaskManagerController.class , new Callable<TaskManagerController>() {
			@Override
			public TaskManagerController call() throws Exception {
				return new TaskManagerController(project,ui);
			}
		});
		Parent root = null;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("TaskManagementUI.fxml"));

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
			controller=loader.getController();
		}catch(Exception e){
			e.printStackTrace();
		}
		Scene scene = new Scene(root, 1000, 600);
		return scene;
	}

	@Override
	public IController getController() {
		return controller;
	}
}
