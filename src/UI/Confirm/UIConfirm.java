package UI.Confirm;

import Controller.ConfirmController;
import Controller.ErrorController;
import UI.UIGlobal;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.util.Callback;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class UIConfirm implements UIGlobal {


    String object;
    Object obj;
    String action;
    Node node;
    public UIConfirm(String object, String action, Object obj, Node node){
        this.object=object;
        this.obj=obj;
        this.action=action;
        this.node=node;

    }
    public Scene loadScene(){
        Map<Class, Callable<?>> creators = new HashMap<>();
        creators.put(ConfirmController.class , new Callable<ConfirmController>() {
            @Override
            public ConfirmController call() throws Exception {

                return new ConfirmController(object,action,obj,node);
            }
        });
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Confirmation.fxml"));
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
