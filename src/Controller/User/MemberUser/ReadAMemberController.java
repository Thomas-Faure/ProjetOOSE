package Controller.User.MemberUser;

import BuisnessLogic.Project.AbstractProject;
import BuisnessLogic.Role.Role;
import BuisnessLogic.User.Member;
import Facade.Role.IRoleFacade;
import Facade.Role.RoleFacade;
import Facade.User.MemberUser.IMemberFacade;
import Facade.User.MemberUser.MemberFacade;
import Main.App;
import UI.Role.AllRolesUI;
import UI.UIError;
import UI.UIGlobal;
import UI.User.Member.AllMembersUI;
import UI.User.Member.ReadAMemberUI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ReadAMemberController implements Initializable {

    @FXML
    private TextField username;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;

    private Member member;
    /*
        private static ObservableList<User> listViewTemp;
        private ListView<User> membersList ;
        private IMemberFacade memberFacade = MemberFacade.getInstance();
        private static Member toManage;
    */
    public ReadAMemberController (Member newmember){

        this.member = newmember;

    }

    public void addNewRole(ActionEvent actionEvent) {
        /*
        AddRoleUI member = new AddMemberUI(this.project);
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(member.loadScene().getRoot());

         */
    }

    @FXML
    private ListView<Role> rolesList ;
    private IRoleFacade roleFacade = RoleFacade.getInstance();

    //private IGlobalUserFacade userFacade = GlobalUserFacade.getInstance();
    private IMemberFacade memberFacade = MemberFacade.getInstance();

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
        username.setText(member.getUsername());
        firstName.setText(member.getFirstName());
        lastName.setText(member.getLastName());
        if(rolesList != null){
            if(roleFacade.getAllRoles()) {
                ArrayList<Role> listeElement = new ArrayList<>();
                if (member.getRole() != null){
                    for (int i = 0; i < roleFacade.getListRoles().size(); i++){
                        if (member.getRole().getId() == roleFacade.getListRoles().get(i).getId()){
                            listeElement.add( (Role) roleFacade.getListRoles().get(i));
                        }
                    }
                }

                ObservableList<Role> listView = FXCollections.observableArrayList(listeElement);
                listViewTemp = FXCollections.observableArrayList(listeElement);
                rolesList.setItems(listView);
                rolesList.setCellFactory(param -> new ReadAMemberController.Cell());
            }
        }


    }

    public void validation(ActionEvent actionEvent) {
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        AbstractProject project = this.member.getProject();
        if(!(memberFacade.deleteMember(this.member))){
            UIError error = new UIError( new AllMembersUI(project));
            box.getChildren().add(error.loadScene().getRoot());
            if(box.getChildren().size() >1 )
                box.getChildren().remove(2);
        }else {
            AllMembersUI allm = new AllMembersUI(this.member.getProject());
            if(box.getChildren().size() >1 )
                box.getChildren().remove(1);
            box.getChildren().add(allm.loadScene().getRoot());
            member = null;
        }
    }

    public void refuse(ActionEvent actionEvent) {
        AnchorPane toHide = (AnchorPane) App.getInstanceScene().lookup("#confirm");
        toHide.setVisible(false);
        AnchorPane toShow = (AnchorPane) App.getInstanceScene().lookup("#manager");
        toShow.setVisible(true);
    }

    public void validationDelRole(ActionEvent actionEvent) {
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        this.member.setRole(null);
        if(!(memberFacade.modifyMember(this.member))){
            UIError error = new UIError((UIGlobal) new ReadAMemberController(this.member));
            box.getChildren().add(error.loadScene().getRoot());
            if(box.getChildren().size() >1 )
                box.getChildren().remove(2);
        }else {
            ReadAMemberUI allm = new ReadAMemberUI(this.member);
            if(box.getChildren().size() >1 )
                box.getChildren().remove(1);
            box.getChildren().add(allm.loadScene().getRoot());
        }
    }

    public void refuseDelRole(ActionEvent actionEvent) {
        AnchorPane toHide = (AnchorPane) App.getInstanceScene().lookup("#confirm2");
        toHide.setVisible(false);
        AnchorPane toShow = (AnchorPane) App.getInstanceScene().lookup("#manager");
        toShow.setVisible(true);
    }

    public void backToPage(ActionEvent actionEvent) {
        AllMembersUI allm = new AllMembersUI(this.member.getProject());
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(allm.loadScene().getRoot());


    }

    public void deleteMember(ActionEvent actionEvent) {
        AnchorPane toHide = (AnchorPane) App.getInstanceScene().lookup("#manager");
        toHide.setVisible(false);
        AnchorPane toShow = (AnchorPane) App.getInstanceScene().lookup("#confirm");
        toShow.setVisible(true);

    }

    public void addRole(ActionEvent actionEvent) {
        AllRolesUI allm = new AllRolesUI(this.member);
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(allm.loadScene().getRoot());
    }


    static class Cell extends ListCell<Role> {
        Role role;
        HBox hbox = new HBox();
        Image image = new Image("megaphone.png");
        ImageView img = new ImageView(image);
        Button btnD = new Button("Delete");
        Label label = new Label("");
        Pane pane = new Pane();

        public Cell() {
            super();
            hbox.setSpacing(10);
            img.setFitHeight(20);
            img.setFitWidth(20);

            hbox.getChildren().addAll(img, label, pane, btnD);

            btnD.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    toManage = role;
                    AnchorPane toHide = (AnchorPane) App.getInstanceScene().lookup("#manager");
                    toHide.setVisible(false);
                    AnchorPane toShow = (AnchorPane) App.getInstanceScene().lookup("#confirm2");
                    toShow.setVisible(true);
                }
            });
        }
        public void updateItem(Role name, boolean empty){
            super.updateItem(name,empty);
            setText(null);
            setGraphic(null);
            if(name != null && !empty){
                role = name;
                label.setText(name.getId()+" "+name.getName());
                setGraphic(hbox);
            }

        }

    }
}
