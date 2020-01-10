package UI.Sprint;


import BuisnessLogic.Project.AbstractProject;
import BuisnessLogic.Sprint.AbstractSprint;
import Controller.Sprint.AddTaskSprintController;
import UI.UIGlobal;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.util.Callback;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class AddTaskSprintUI implements UIGlobal {
    AbstractProject project;
    AbstractSprint sprint;

    public AddTaskSprintUI(AbstractProject project, AbstractSprint sprint){
        this.project = project;
        this.sprint = sprint;
    }

    @Override
    public Scene loadScene(){
        Map<Class, Callable<?>> creators = new HashMap<>();
        creators.put(AddTaskSprintController.class , new Callable<AddTaskSprintController>() {
            @Override
            public AddTaskSprintController call() throws Exception {
                return new AddTaskSprintController(project,sprint);
            }
        });
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddTaskSprintUI.fxml"));
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