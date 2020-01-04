package UI.Task;

import BuisnessLogic.Project.AbstractProject;
import Controller.Sprint.SprintController;
import Controller.Task.TaskManagerController;
import UI.UIGlobal;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.util.Callback;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class UITaskManagement implements UIGlobal {

	AbstractProject project;

	public UITaskManagement(AbstractProject project){
		this.project = project;
	};

	public Scene loadScene(){
		Map<Class, Callable<?>> creators = new HashMap<>();
		creators.put(TaskManagerController.class , new Callable<TaskManagerController>() {
			@Override
			public TaskManagerController call() throws Exception {
				return new TaskManagerController(project);
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
		}catch(Exception e){
			e.printStackTrace();
		}
		Scene scene = new Scene(root, 1000, 600);
		return scene;
	}
}
