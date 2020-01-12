package Test.DAO;
/**
 *
 * @author Thomas Faure
 */
import BuisnessLogic.Announcement.AbstractAnnouncement;
import BuisnessLogic.Announcement.Announcement;
import BuisnessLogic.User.AbstractUser;
import BuisnessLogic.User.User;
import DAO.Announcement.AnnouncementDAO;
import DAO.Announcement.AnnouncementDAOMySQL;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test Class to test AnnouncementDAO
 */
class AnnouncementDAOMySQLTest {

    /**
     * Method to test the creation of a database row
     */
    @Test
    void save() {
        AnnouncementDAO dao = new AnnouncementDAOMySQL();
        AbstractUser user = new User(1,"name","firstname","lastname","password");
        AbstractAnnouncement announcement = new Announcement(1,"title","message",LocalDate.now(),user);
        boolean valid = dao.save(announcement);
        assertEquals(true, valid, "to save a new announcement");
    }
    /**
     * Method to test the update of a database row
     */
    @Test
    void update() {
        AnnouncementDAO dao = new AnnouncementDAOMySQL();
        List<AbstractAnnouncement> list = dao.getAllAnnouncements();
        AbstractAnnouncement announcement = null;
        if(list.size() != 0) {
            announcement = list.get(0);
            String msgToVerify ="";
            if(!announcement.getTitle().equals("I'm a test")){
                msgToVerify= "I'm a test 2";
            }else {
               msgToVerify ="I'm a test";
            }
            announcement.setTitle(msgToVerify);

            dao.update(announcement);
            AbstractAnnouncement testAnnouncement = dao.getAnnouncementById(announcement.getId());
            assertEquals(msgToVerify, testAnnouncement.getTitle(), "");
        }else{
            assertEquals(null, announcement, "");
        }
    }

}