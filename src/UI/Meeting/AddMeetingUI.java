package UI.Meeting;

import BusinessLogic.Project.AbstractProject;
import Controller.Meeting.AddMeetingController;
import UI.UIGlobal;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.util.Callback;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * @author Rémi Salmi
 */
public class AddMeetingUI implements UIGlobal {

    private AbstractProject project;

    public AddMeetingUI(AbstractProject project){
        this.project=project;
    }

    @Override
    public Scene loadScene() {
        Map<Class, Callable<?>> creators = new HashMap<>();
        creators.put(AddMeetingController.class , new Callable<AddMeetingController>() {
            @Override
            public AddMeetingController call() throws Exception {
                return new AddMeetingController(project);
            }
        });
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddMeetingUI.fxml"));
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
