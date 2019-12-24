package Controller;

import BuisnessLogic.Announcement.Announcement;
import BuisnessLogic.Task.Task;
import Facade.AnnouncementFacade;
import Facade.TaskFacade;
import Main.App;
import UI.Announcement.UIAnnouncementManagement;
import UI.Task.UITaskManagement;
import UI.UIError;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;

import java.awt.event.ActionEvent;

public class ConfirmController {
    String object;
    String action;
    Object obj;
    Node node;
    public ConfirmController(String object, String action, Object obj, Node node) {
        this.action=action;
        this.object=object;
        this.obj=obj;
        this.node=node;
    }




    public void validation(javafx.event.ActionEvent actionEvent) {
        if(action.equals("Delete")) {

            if (object.equals("Task")) {


               if(!(TaskFacade.getInstance().deleteTask((Task)obj))){
                 UIError error = new UIError(new UITaskManagement());
                 HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
                 box.getChildren().add(error.loadScene().getRoot());
                 if(box.getChildren().size() >1 )
                 box.getChildren().remove(2);
                 }else{
                    HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
                    box.getChildren().add(node);
                    if(box.getChildren().size() >1 )
                        box.getChildren().remove(1);

                }



            } else if (object.equals("Announcement")) {
                if(!(AnnouncementFacade.getInstance().deleteAnnouncement((Announcement) obj))){
                    UIError error = new UIError(new UITaskManagement());
                    HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
                    box.getChildren().add(error.loadScene().getRoot());
                    if(box.getChildren().size() >1 )
                        box.getChildren().remove(2);
                }else{
                    HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
                    box.getChildren().add(node);
                    if(box.getChildren().size() >1 )
                        box.getChildren().remove(1);

                }

            }
        }
        else if(action.equals("Modify")) {


            if (object.equals("Task")) {

                if(TaskFacade.getInstance().modifyTask((Task)obj)){

                    HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
                    if(box.getChildren().size() >1 )
                        box.getChildren().remove(1);
                    box.getChildren().add(node);
                }else{
                    UIError error = new UIError(new UITaskManagement());
                    HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
                    box.getChildren().add(error.loadScene().getRoot());
                    if(box.getChildren().size() >1 )
                        box.getChildren().remove(1);
                }


            } else if (object.equals("Announcement")) {
                if(AnnouncementFacade.getInstance().modifyAnnouncement((Announcement) obj)){

                    HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
                    if(box.getChildren().size() >1 )
                        box.getChildren().remove(1);
                    box.getChildren().add(node);
                }else{
                    UIError error = new UIError(new UIAnnouncementManagement());
                    HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
                    box.getChildren().add(error.loadScene().getRoot());
                    if(box.getChildren().size() >1 )
                        box.getChildren().remove(1);
                }

            }
        }
    }

    public void refuse(javafx.event.ActionEvent actionEvent) {
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(node);

    }
}
