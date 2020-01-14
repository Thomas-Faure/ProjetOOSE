package UI.Task;
/**
 *
 * @author Thomas Faure
 */
import BusinessLogic.Project.AbstractProject;
import BusinessLogic.Task.AbstractTask;
import Controller.Task.ModifyTaskController;
import UI.UIGlobal;
import UI.UIGlobalWithController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.util.Callback;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class ModifyTaskUI implements UIGlobal{
	AbstractTask task;
	private AbstractProject project;
	UIGlobalWithController ui;
	public ModifyTaskUI(AbstractTask task, AbstractProject project, UIGlobalWithController ui){
		this.ui=ui;
		this.task=task;this.project=project;

	}

	public Scene loadScene(){
		Map<Class, Callable<?>> creators = new HashMap<>();
		creators.put(ModifyTaskController.class , new Callable<ModifyTaskController>() {
			@Override
			public ModifyTaskController call() throws Exception {

				return new ModifyTaskController(task,project,ui);
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
