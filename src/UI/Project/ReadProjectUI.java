package UI.Project;

import BuisnessLogic.Project.AbstractProject;
import Controller.Project.ReadProjectController;
import UI.UIGlobal;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.util.Callback;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class ReadProjectUI implements UIGlobal {

    private AbstractProject project;

    public ReadProjectUI(AbstractProject project){
        this.project= project;
    }

    @Override
    public Scene loadScene() {
        Map<Class, Callable<?>> creators = new HashMap<>();
        creators.put(ReadProjectController.class , new Callable<ReadProjectController>() {
            @Override
            public ReadProjectController call() throws Exception {
                return new ReadProjectController(project);
            }
        });
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ReadProjectUI.fxml"));
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
