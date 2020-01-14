package Controller.User.MemberUser;


import BusinessLogic.Project.AbstractProject;
import BusinessLogic.User.AbstractUser;
import BusinessLogic.User.Member;
import Facade.User.GlobalUser.GlobalUserFacade;
import Facade.User.GlobalUser.IGlobalUserFacade;
import Facade.User.MemberUser.IMemberFacade;
import Facade.User.MemberUser.MemberFacade;
import Main.App;
import UI.Project.ReadProjectUI;
import UI.UIError;
import UI.User.Member.AddMemberUI;
import UI.User.Member.AllMembersUI;
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

/**
 * Cette Classe correspond au contrôleur qui gère la vue "AllMembersUI".
 * Il va servir pour gerer l'ensemble des membres associées au projet passé en
 * paramètre qui ont été  créées sur l'application.
 * On va pouvoir ajouter, créer, voir/modifier un membre grâce à cette interface.
 * @author Lauren Unquera - Polytech Montpellier IG4
 */
public class AllMembersController implements Initializable {

    private AbstractProject project;

    @FXML
    private ListView<Member> usersList ;
    private IGlobalUserFacade userFacade = GlobalUserFacade.getInstance();
    private IMemberFacade memberFacade;
    private static Member mtoManage;
    private static ObservableList<Member> listViewTemp;

    public AllMembersController (AbstractProject project){

        this.project = project;
        memberFacade = MemberFacade.getInstance();
    }

    /**
     * Redirige l'utilisateur sur la page de création d'un
     * membre.
     * @author Lauren Unquera - Polytech Montpellier IG4
     */
    public void addNewMember(ActionEvent actionEvent) {
        AddMemberUI member = new AddMemberUI(this.project);
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(member.loadScene().getRoot());
    }


    /**
     * Sous fonction utilisée pour regarder si un utilisateur
     * passé en paramètre de la fonction est un membre du projet (qui a été
     * passé en paramètre lors de la création du controller)
     * @author Lauren Unquera - Polytech Montpellier IG4
     */
    public boolean estMembre(AbstractUser user){

        for (int i = 0; i < memberFacade.getListMembers().size(); i++){
            if ( (memberFacade.getListMembers().get(i).getId() == user.getId()) &&
                    (memberFacade.getListMembers().get(i).getProject().getId() == this.project.getId())) {
                return true;
            }

        }
        return false;

    }

    /**
     * Permet d'initialiser la page avec
     * tous les membres du projet associé existants dans l'application.
     * Utilise la classe Cell propre à AllMembersController
     * @author Lauren Unquera - Polytech Montpellier IG4
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        if(usersList != null){
            //si on peut récuperer les tickets
            if(memberFacade.getAllMembers()) {
                ArrayList<Member> listeElement = new ArrayList<>();
                for (int i = 0; i < memberFacade.getListMembers().size(); i++){
                    if (this.estMembre(memberFacade.getListMembers().get(i))){
                        listeElement.add(memberFacade.getListMembers().get(i));
                    }
                }
                ObservableList<Member> listView = FXCollections.observableArrayList(listeElement);
                listViewTemp = FXCollections.observableArrayList(listeElement);
                usersList.setItems(listView);
                usersList.setCellFactory(param -> new AllMembersController.Cell(this.project));
            }
        }

    }

    /**
     * Permet de valider la suppression courrante d'un membre du projet.
     * En cas d'erreur, le signifie avec un UIError.
     * @author Lauren Unquera - Polytech Montpellier IG4
     */
    public void validation(ActionEvent actionEvent) {
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(!(memberFacade.deleteMember(mtoManage))){
            UIError error = new UIError( new AllMembersUI(this.project));
            box.getChildren().add(error.loadScene().getRoot());
            if(box.getChildren().size() >1 )
                box.getChildren().remove(2);
        }else {
            AnchorPane toHide = (AnchorPane) App.getInstanceScene().lookup("#confirm");
            toHide.setVisible(false);
            AnchorPane toShow = (AnchorPane) App.getInstanceScene().lookup("#manager");
            toShow.setVisible(true);
            usersList.getItems().remove(mtoManage);
            listViewTemp.remove(mtoManage);
            mtoManage = null;
            AllMembersUI user = new AllMembersUI(this.project);
            if(box.getChildren().size() >1 )
                box.getChildren().remove(1);
            box.getChildren().add(user.loadScene().getRoot());
        }
    }

    /**
     * Permet d'annuler la suppression courrante d'un membre du projet.
     * En cas d'erreur, le signifie avec un UIError.
     * @author Lauren Unquera - Polytech Montpellier IG4
     */
    public void refuse(ActionEvent actionEvent) {
        AnchorPane toHide = (AnchorPane) App.getInstanceScene().lookup("#confirm");
        toHide.setVisible(false);
        AnchorPane toShow = (AnchorPane) App.getInstanceScene().lookup("#manager");
        toShow.setVisible(true);
    }

    /**
     * Cette fonction est appelée lorsque l'utilisateur appuie
     * sur le bouton "Back".
     * Elle permet de rediriger l'utilisateur sur la page de la "ReadProjectUI" qui
     * était la page précédente avant qu'il arrive sur celle-ci ("AllMembersUI").
     * @author Lauren Unquera - Polytech Montpellier IG4
     */
    public void backToPage(ActionEvent actionEvent) {
        ReadProjectUI user = new ReadProjectUI(this.project);
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(user.loadScene().getRoot());
    }


    /**
     * Classe propre à AddMemberController qui servira à donner
     * la liste des membres associés au projet. Chaque membre
     * correspondra à une cellule dans laquelle on pourra trouver
     * les boutons nécessaires à la gestion.
     * @author Lauren Unquera - Polytech Montpellier IG4
     */
    static class Cell extends ListCell<Member> {
        Member user;
        AbstractProject cellProject;
        HBox hbox = new HBox();
        Image image = new Image("megaphone.png");
        ImageView img = new ImageView(image);
        Button btnR = new Button("Read");
        Button btnD = new Button("Delete");
        Label label = new Label("");
        Pane pane = new Pane();

        public Cell(AbstractProject project) {
            super();
            //user.setProject(project);
            this.cellProject = project;
            hbox.setSpacing(10);
            img.setFitHeight(20);
            img.setFitWidth(20);


            hbox.getChildren().addAll(img, label, pane, btnR, btnD);

            btnD.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    mtoManage = (Member) user;
                    AnchorPane toHide = (AnchorPane) App.getInstanceScene().lookup("#manager");
                    toHide.setVisible(false);
                    AnchorPane toShow = (AnchorPane) App.getInstanceScene().lookup("#confirm");
                    toShow.setVisible(true);
                }
            });
            btnR.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    user.setProject(project);
                    ReadAMemberUI read = new ReadAMemberUI(user);
                    HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");

                    box.getChildren().remove(1);
                    box.getChildren().add(read.loadScene().getRoot());

                }
            });
        }

        /**
         * Permet de donner des information sur les membres
         * des cellules, ici le nom et prénom des utilisateurs.
         * @author Lauren Unquera - Polytech Montpellier IG4
         */
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
