package Controller.Announcement;
/**
 *
 * @author Thomas Faure
 */
import BuisnessLogic.Announcement.AbstractAnnouncement;
import Facade.Announcement.AnnouncementFacade;
import Main.App;
import UI.Announcement.UIReadAnnouncement;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Controller of the show announcement page (announcements displayed to users)
 */
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

    /**
     * When a user click on the "next" or "prev" button ,these functions call ActualiseAnnouncements , the goal of that method is to load the announcement according to the actual page number
     */
    public void ActualiseAnnouncements(){
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
            date.setText(announcementList.get(i).getDate().toString());
            Text username = (Text)format.lookup("#username");
            username.setText("User: "+announcementList.get(i).getUser().getFirstName());
            Text announcementTitle = (Text)format.lookup("#AnnouncementTitle");
            announcementTitle.setText("Title: "+announcementList.get(i).getTitle());
            vboxList.getChildren().add(format);
        }
    }

    public void testFct(KeyEvent keyEvent) {
    }

    /**
     * Method called when the user click on the "see more" button, call the read announcement page
     */
    private class clickMore implements EventHandler<Event> {
        @Override
        public void handle(Event evt) {
            UIReadAnnouncement read = new UIReadAnnouncement(Integer.parseInt(((Control)evt.getSource()).getId()),false);
            HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
            box.getChildren().remove(1);
            box.getChildren().add(read.loadScene().getRoot());
        }
    }

    /**
     * Method call when the controller is created, this method initialise the table of announcement and se the variable of number of pages
     * @param location
     * @param resources
     */
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

    /**Method call when the user click on the "next" button, and change the page of announcement's list
     * @param actionEvent
     */
    @FXML
    public void next(javafx.event.ActionEvent actionEvent) {
        if(currentPage == 1){
            prev.setDisable(false);
        }
        if(currentPage <maxPage){
            currentPage +=1;
        }
        if(currentPage == maxPage){
            next.setDisable(true);
        }
        ActualiseAnnouncements();
    }

    /**Method call when the user click on the "prev" button, and change the page of announcement's list
     * @param actionEvent
     */
    @FXML
    public void prev(javafx.event.ActionEvent actionEvent) {
        if(currentPage == maxPage){
            next.setDisable(false);
        }
        if(currentPage > 1){
            currentPage -=1;
        }
        if(currentPage == 1){
            prev.setDisable(true);
        }
        ActualiseAnnouncements();
    }
}
