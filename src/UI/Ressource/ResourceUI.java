package UI.Ressource;

import BusinessLogic.Project.AbstractProject;
import Controller.Resource.ResourceController;
import UI.UIGlobal;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.util.Callback;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 *
 * @author Guillaume Tessier
 */
public class ResourceUI implements UIGlobal {
    AbstractProject project;

    public ResourceUI(AbstractProject project){
        this.project = project;
    };

    @Override
    public Scene loadScene(){
        Map<Class, Callable<?>> creators = new HashMap<>();
        creators.put(ResourceController.class , new Callable<ResourceController>() {
            @Override
            public ResourceController call() throws Exception {
                return new ResourceController(project);
            }
        });
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ResourceUI.fxml"));
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
