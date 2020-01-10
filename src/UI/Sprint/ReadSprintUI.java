package UI.Sprint;


import BuisnessLogic.Project.AbstractProject;
import BuisnessLogic.Sprint.AbstractSprint;
import Controller.IController;
import Controller.Sprint.ReadSprintController;
import UI.UIGlobal;
import UI.UIGlobalWithController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.util.Callback;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class ReadSprintUI implements UIGlobalWithController {
    AbstractProject project;
    AbstractSprint sprint;
    public static IController controller;
    public static UIGlobalWithController ui;

    public ReadSprintUI(AbstractProject project, AbstractSprint sprint){
        this.project = project;
        this.sprint = sprint;
        ui=this;
    }

    @Override
    public Scene loadScene(){
        Map<Class, Callable<?>> creators = new HashMap<>();
        creators.put(ReadSprintController.class , new Callable<ReadSprintController>() {
            @Override
            public ReadSprintController call() throws Exception {
                return new ReadSprintController(project,sprint,ui);
            }
        });
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ReadSprintUI.fxml"));
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
