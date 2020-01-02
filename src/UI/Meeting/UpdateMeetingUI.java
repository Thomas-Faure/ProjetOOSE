package UI.Meeting;

import BuisnessLogic.Meeting.AbstractMeeting;
import BuisnessLogic.Project.AbstractProject;
import Controller.Meeting.AddMeetingController;
import Controller.Meeting.UpdateMeetingController;
import UI.UIGlobal;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.util.Callback;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class UpdateMeetingUI implements UIGlobal {

    private AbstractMeeting meeting;
    private AbstractProject project;

    public UpdateMeetingUI(AbstractMeeting meeting, AbstractProject project){
        this.meeting= meeting;
        this.project = project;
    }

    @Override
    public Scene loadScene() {
        Map<Class, Callable<?>> creators = new HashMap<>();
        creators.put(UpdateMeetingController.class , new Callable<UpdateMeetingController>() {
            @Override
            public UpdateMeetingController call() throws Exception {
                return new UpdateMeetingController(meeting, project);
            }
        });
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateMeetingUI.fxml"));
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
