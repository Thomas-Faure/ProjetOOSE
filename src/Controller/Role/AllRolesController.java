package Controller.Role;

import BuisnessLogic.Role.AbstractRole;
import BuisnessLogic.Role.Role;
import BuisnessLogic.User.Member;
import Facade.Role.IRoleFacade;
import Facade.Role.RoleFacade;
import Main.App;
import UI.Role.AddRoleUI;
import UI.Role.AllRolesUI;
import UI.UIError;
import UI.UIGlobal;
import UI.User.Member.ReadAMemberUI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AllRolesController implements Initializable {

    private Member member;

    /*
    public AllRolesController(){
    }*/
    public AllRolesController(Member newmember){
        this.member = newmember;
    }


    @FXML
    private ListView<Role> rolesList ;

    private IRoleFacade roleFacade = RoleFacade.getInstance();
    //private IMemberFacade memberFacade = MemberFacade.getInstance();

    private static Role toManage;

    //permet de garder la liste de base
    private static ObservableList<Role> listViewTemp;
    /*
    @FXML
    public void searchBar(KeyEvent keyEvent) {

        if(!(inputSearch.getText().length() == 0)) {
            ArrayList<AbstractProject> array = new ArrayList<>(listViewTemp);
            ArrayList<AbstractProject> toDelete = new ArrayList<>();
            for (int i = 0; i < array.size(); ++i) {
                String inputS =inputSearch.getText();
                if(inputS.charAt(0) == '*'){
                    inputS= "\\"+inputS;
                }
                String regex = "(.*)" + inputS + "(.*)";
                if (array.get(i).getName().matches(regex)) {

                } else {
                    toDelete.add(array.get(i));
                }
            }

            for (AbstractProject i : toDelete) {
                array.remove(i);
            }
            ObservableList<AbstractProject> listViewT = FXCollections.observableArrayList(array);
            projectsList.setItems(listViewT);

        }else{
            projectsList.setItems(listViewTemp);
        }

    }
*/


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        if(rolesList != null){
            //si on peut récuperer les tickets
            if(roleFacade.getAllRoles()) {
                ArrayList<Role> listeElement = ((ArrayList) roleFacade.getListRoles());
                System.out.println(listeElement);
                ObservableList<Role> listView = FXCollections.observableArrayList(listeElement);
                listViewTemp = FXCollections.observableArrayList(listeElement);

                rolesList.setItems(listView);
                rolesList.setCellFactory(param -> new AllRolesController.Cell());
            }
        }

    }

    public void validation(ActionEvent actionEvent) {
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(!(roleFacade.addRole(toManage))){
            UIError error = new UIError((UIGlobal) new AllRolesUI(this.member));
            box.getChildren().add(error.loadScene().getRoot());
            if(box.getChildren().size() >1 )
                box.getChildren().remove(2);
        }else {
            AnchorPane toHide = (AnchorPane) App.getInstanceScene().lookup("#confirm");
            toHide.setVisible(false);
            AnchorPane toShow = (AnchorPane) App.getInstanceScene().lookup("#manager");
            toShow.setVisible(true);
            rolesList.getItems().remove(toManage);
            listViewTemp.remove(toManage);
            toManage = null;
        }
    }

    public void refuse(ActionEvent actionEvent) {
        AnchorPane toHide = (AnchorPane) App.getInstanceScene().lookup("#confirm");
        toHide.setVisible(false);
        AnchorPane toShow = (AnchorPane) App.getInstanceScene().lookup("#manager");
        toShow.setVisible(true);
    }

    public void backToPage(ActionEvent actionEvent) {
        ReadAMemberUI user = new ReadAMemberUI(this.member);
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(user.loadScene().getRoot());
    }


    static class Cell extends ListCell<Role> {
        Role role;
        HBox hbox = new HBox();
        Image image = new Image("megaphone.png");
        ImageView img = new ImageView(image);
        Button btnA = new Button("Add");
        Label label = new Label("");
        Pane pane = new Pane();

        public Cell() {
            super();
            hbox.setSpacing(10);
            img.setFitHeight(20);
            img.setFitWidth(20);

            hbox.getChildren().addAll(img, label, pane, btnA);

            btnA.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    cellAddRole (role);
                    AnchorPane toHide = (AnchorPane) App.getInstanceScene().lookup("#manager");
                    toHide.setVisible(false);
                    AnchorPane toShow = (AnchorPane) App.getInstanceScene().lookup("#confirm");
                    toShow.setVisible(true);
                }
            });
        }
        public void cellAddRole (AbstractRole role){
            Role newRole = new Role(role.getId(), role.getName());
            toManage = newRole;
        }
        public void updateItem(Role name, boolean empty){
            super.updateItem(name,empty);
            setText(null);
            setGraphic(null);
            if(name != null && !empty){
                this.role = name;
                label.setText(name.getId()+" "+name.getName());
                setGraphic(hbox);
            }

        }

    }

    public void CreateRole(ActionEvent actionEvent) {
        AddRoleUI role = new AddRoleUI();
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(role.loadScene().getRoot());
    }
}
