package Controller.Announcement;

import BuisnessLogic.Announcement.AbstractAnnouncement;
import Facade.AnnouncementFacade;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ShowAnnouncementsController implements Initializable {
    //classe pour permettre aux personens de visualiser les taches en cours

    @FXML
    private VBox vboxList;
    @FXML
    private Button prev;
    @FXML
    private Button next;
    @FXML
    private TextField inputSearch;

    int currentPage = 1;
    int maxPage;
    private List<AbstractAnnouncement> announcementList;


    //quand on clique sur le bouton d'une annonce, l'affiche en gros
    void readAnnouncement(){

    }


    //permet de chercher un annonce par son nom
    void searchAnnouncement(String title){
            List<AbstractAnnouncement> AnnouncementsSearched = AnnouncementFacade.getInstance().getAnnouncementByTitle(title);

    }
    public void ActualiseAnnouncements(){
        System.out.println("actualisation de la page :"+currentPage);
        //on remet à zero la liste
        vboxList.getChildren().clear();
        Parent root = null;



        for(int i = (currentPage*3)-3;i<(currentPage*3) && i<announcementList.size();++i){
            try {
                FXMLLoader loader = new FXMLLoader();
                root = loader.load(getClass().getResourceAsStream("AnnouncementFormat.fxml"));
            }catch(Exception e){
                e.printStackTrace();
            }
            Scene scene = new Scene(root, 500, 150);
            AnchorPane format = (AnchorPane)scene.getRoot();


            Button more = (Button)format.lookup("#more");
            more.setId(announcementList.get(i).getId()+"");
            more.addEventHandler(MouseEvent.MOUSE_CLICKED, new clickMore());
            Text date = (Text)format.lookup("#date");
            date.setText(announcementList.get(i).getDate().toString());
            Text username = (Text)format.lookup("#username");
            username.setText("nom");
            Text announcementTitle = (Text)format.lookup("#AnnouncementTitle");
            announcementTitle.setText(announcementList.get(i).getTitle());

            vboxList.getChildren().add(format);

        }
    }

    private class clickMore implements EventHandler<Event> {
        @Override
        public void handle(Event evt) {
            System.out.println(((Control)evt.getSource()).getId());
        }
    }

    public void testFct(KeyEvent keyEvent) {
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(AnnouncementFacade.getInstance().getAllAnnouncements()){
            announcementList = new ArrayList<>(AnnouncementFacade.getInstance().getListAnnouncements());
            prev.setDisable(true);
            next.setDisable(true);
            maxPage = announcementList.size()/3;
           if(announcementList.size() % 3 == 1){
               maxPage++;
           }
           if(maxPage>1){
               next.setDisable(false);
           }
            ActualiseAnnouncements();

        }

    }

    @FXML
    public void next(javafx.event.ActionEvent actionEvent) {
        if(currentPage == 1){
            prev.setDisable(false);
        }
        System.out.println(currentPage+" "+maxPage);
        if(currentPage <maxPage){
            System.out.println("plus");
            currentPage +=1;
        }
        if(currentPage == maxPage){
            next.setDisable(true);
        }
        ActualiseAnnouncements();
    }

    @FXML
    public void prev(javafx.event.ActionEvent actionEvent) {
        if(currentPage == maxPage){
            next.setDisable(false);
        }
        if(currentPage > 1){
            System.out.println("moins");
            currentPage -=1;
        }
        if(currentPage == 1){
            prev.setDisable(true);
        }
        ActualiseAnnouncements();
    }
}
