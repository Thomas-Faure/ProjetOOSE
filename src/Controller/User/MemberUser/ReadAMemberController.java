package Controller.User.MemberUser;

import BuisnessLogic.Project.AbstractProject;
import BuisnessLogic.User.Member;
import Facade.User.GlobalUser.GlobalUserFacade;
import Facade.User.GlobalUser.IGlobalUserFacade;
import Facade.User.MemberUser.IMemberFacade;
import Facade.User.MemberUser.MemberFacade;
import Main.App;
import UI.UIError;
import UI.User.Member.AllMembersUI;
import UI.User.Member.ReadAMemberUI;
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
    private ListView<Member> usersList ;

    private IGlobalUserFacade userFacade = GlobalUserFacade.getInstance();
    private IMemberFacade memberFacade = MemberFacade.getInstance();

    //private static User toManage;

    //permet de garder la liste de base
    private static ObservableList<Member> listViewTemp;
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
        /*
        if(usersList != null){
            //si on peut récuperer les tickets
            if(memberFacade.getAllMembersProject(this.project)) {
                ArrayList<Member> listeElement = ((ArrayList) memberFacade.getListMembers());
                ObservableList<Member> listView = FXCollections.observableArrayList(listeElement);
                listViewTemp = FXCollections.observableArrayList(listeElement);
                usersList.setItems(listView);
                usersList.setCellFactory(param -> new AllMembersController.Cell());
                System.out.println(listView);
            }
        }

         */

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
    }


    static class Cell extends ListCell<Member> {
        Member user;
        HBox hbox = new HBox();
        Image image = new Image("megaphone.png");
        ImageView img = new ImageView(image);
        Button btnR = new Button("Read");
        Button btnD = new Button("Delete");
        Label label = new Label("");
        Pane pane = new Pane();

        public Cell() {
            super();
            hbox.setSpacing(10);
            img.setFitHeight(20);
            img.setFitWidth(20);

            hbox.getChildren().addAll(img, label, pane, btnR, btnD);

            btnD.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    /*mtoManage = (Member) user;
                    AnchorPane toHide = (AnchorPane) App.getInstanceScene().lookup("#manager");
                    toHide.setVisible(false);
                    AnchorPane toShow = (AnchorPane) App.getInstanceScene().lookup("#confirm");
                    toShow.setVisible(true);

                     */
                }
            });
            btnR.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {

                    ReadAMemberUI read = new ReadAMemberUI(user);
                    HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");

                    box.getChildren().remove(1);
                    box.getChildren().add(read.loadScene().getRoot());

                }
            });


        }
        public void updateItem(Member name, boolean empty){
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
