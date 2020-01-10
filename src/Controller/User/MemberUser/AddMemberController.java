package Controller.User.MemberUser;

import BuisnessLogic.Project.AbstractProject;
import BuisnessLogic.User.AbstractUser;
import BuisnessLogic.User.Member;
import BuisnessLogic.User.User;
import Facade.User.GlobalUser.GlobalUserFacade;
import Facade.User.GlobalUser.IGlobalUserFacade;
import Facade.User.MemberUser.IMemberFacade;
import Facade.User.MemberUser.MemberFacade;
import Main.App;
import UI.UIError;
import UI.User.Global.AllUsersUI;
import UI.User.Member.AddMemberUI;
import UI.User.Member.AllMembersUI;
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

public class AddMemberController implements Initializable {

    private AbstractProject project;

    public AddMemberController(){
    }
    public AddMemberController(AbstractProject project){
        this.project = project;
    }


    @FXML
    private ListView<User> usersList ;

    private IGlobalUserFacade userFacade = GlobalUserFacade.getInstance();
    private IMemberFacade memberFacade = MemberFacade.getInstance();

    private static Member toManage;

    //permet de garder la liste de base
    private static ObservableList<User> listViewTemp;
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
    public boolean estMembre(AbstractUser user){

        for (int i = 0; i < memberFacade.getListMembers().size(); i++){
            if ( memberFacade.getListMembers().get(i).getId() == user.getId()){
                return true;
            }
        }
        return false;

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        if(usersList != null){
            if(userFacade.getAllUsers()) {
                ArrayList<User> listeElement = new ArrayList<>();
                for (int i = 0; i < userFacade.getListUsers().size(); i++){
                    if (! estMembre(userFacade.getListUsers().get(i))){
                        listeElement.add((User) userFacade.getListUsers().get(i));
                    }
                }

                ObservableList<User> listView = FXCollections.observableArrayList(listeElement);
                listViewTemp = FXCollections.observableArrayList(listeElement);

                usersList.setItems(listView);
                usersList.setCellFactory(param -> new AddMemberController.Cell(this.project));
            }
        }

    }

    public void validation(ActionEvent actionEvent) {
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(!(memberFacade.addMember(toManage))){
            UIError error = new UIError( new AllUsersUI());
            box.getChildren().add(error.loadScene().getRoot());
            if(box.getChildren().size() >1 )
                box.getChildren().remove(2);
        }else {
            AnchorPane toHide = (AnchorPane) App.getInstanceScene().lookup("#confirm");
            toHide.setVisible(false);
            AnchorPane toShow = (AnchorPane) App.getInstanceScene().lookup("#manager");
            toShow.setVisible(true);
            usersList.getItems().remove(toManage);
            listViewTemp.remove(toManage);
            toManage = null;
            AddMemberUI user = new AddMemberUI(this.project);
            if(box.getChildren().size() >1 )
                box.getChildren().remove(1);
            box.getChildren().add(user.loadScene().getRoot());
        }
    }

    public void refuse(ActionEvent actionEvent) {
        AnchorPane toHide = (AnchorPane) App.getInstanceScene().lookup("#confirm");
        toHide.setVisible(false);
        AnchorPane toShow = (AnchorPane) App.getInstanceScene().lookup("#manager");
        toShow.setVisible(true);
    }

    public void backToPage(ActionEvent actionEvent) {
        AllMembersUI user = new AllMembersUI(this.project);
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(user.loadScene().getRoot());
    }


    public static class Cell extends ListCell<User> {
        AbstractProject cellProject;
        User user;
        HBox hbox = new HBox();
        Image image = new Image("megaphone.png");
        ImageView img = new ImageView(image);
        Button btnA = new Button("Add");
        Label label = new Label("");
        Pane pane = new Pane();

        public Cell(AbstractProject project) {
            super();
            hbox.setSpacing(10);
            img.setFitHeight(20);
            img.setFitWidth(20);
            this.cellProject = project;

            hbox.getChildren().addAll(img, label, pane, btnA);

            btnA.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    cellAddMember (user);
                    AnchorPane toHide = (AnchorPane) App.getInstanceScene().lookup("#manager");
                    toHide.setVisible(false);
                    AnchorPane toShow = (AnchorPane) App.getInstanceScene().lookup("#confirm");
                    toShow.setVisible(true);
                }
            });
        }
        public void cellAddMember (User user){
            Member newMember = new Member(user.getId(), user.getUsername(), user.getPassword(), user.getFirstName(), user.getLastName(),
                    user.getCity(), user.getPhoneNumber(), user.getEmail(), user.getPosition(), user.isAdmin());
            newMember.setProject(this.cellProject);
            toManage = newMember;
        }
        public void updateItem(User name, boolean empty){
            super.updateItem(name,empty);
            setText(null);
            setGraphic(null);
            if(name != null && !empty){
                user = name;
                label.setText(name.getId()+" "+name.getFirstName()+" "+ name.getLastName());
                setGraphic(hbox);
            }

        }

    }
}
