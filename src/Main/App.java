package Main;

import UI.Menu;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App {
    private App(){

    }

    public static App instance;
    private Stage stage;
    private Scene scene;
    private Menu menu;
    public static App getInstance(){
        if (instance == null){
            instance = new App();
        }
        return instance;
    }
    public Scene getMenuScene(){
        return this.menu.loadScene();
    }
    public static void setMenuScene(){
        if(getInstance().menu == null){
            getInstance().menu=new Menu();
        }
        getInstance().setInstanceScene(getInstance().getMenuScene());
    }
    public Stage getStage(){
        return this.stage;
    }
    public Scene getScene(){
        return this.scene;
    }
    public void setStage(Stage stage){
        this.stage=stage;
    }
    public void setSceneApp(Scene scene){
        this.scene=scene;
    }
    public static Stage getInstanceStage(){
        return instance.getStage();
    }
    public static Scene getInstanceScene(){
        return instance.getScene();
    }

    public static void setInstanceScene(Scene scene){
        getInstanceStage().setScene(scene);
        getInstance().setSceneApp(scene);
    }
    public static void setInstanceStage(Stage stage){
        instance.setStage(stage);
    }
}
