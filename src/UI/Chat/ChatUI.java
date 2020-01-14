package UI.Chat;

import BusinessLogic.Project.AbstractProject;
import Controller.Chat.ChatController;
import Controller.IController;
import UI.UIGlobalWithController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.util.Callback;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class ChatUI implements UIGlobalWithController {

    AbstractProject project;
    public static UIGlobalWithController ui;
    public static IController controller;

    public ChatUI(AbstractProject project){
        this.project = project;
        this.ui=this;
    }

    @Override
    public Scene loadScene(){
        Map<Class, Callable<?>> creators = new HashMap<>();
        creators.put(ChatController.class , new Callable<ChatController>() {
            @Override
            public ChatController call() throws Exception {
                return new ChatController(project,ui);
            }
        });
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ChatUI.fxml"));
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
