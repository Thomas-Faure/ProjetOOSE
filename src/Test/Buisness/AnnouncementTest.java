package Test.Buisness;
/**
 *
 * @author Thomas Faure
 */
import BuisnessLogic.Announcement.AbstractAnnouncement;
import BuisnessLogic.Announcement.Announcement;
import BuisnessLogic.User.AbstractUser;
import BuisnessLogic.User.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AnnouncementTest {

    @Test
    void getId() {
        AbstractUser user = new User(1,"name","firstname","lastname","password");

        AbstractAnnouncement announcement = new Announcement(1,"title","message", LocalDate.now(),user);
        assertEquals(1,announcement.getId());
    }

    @Test
    void setId() {
        AbstractUser user = new User(1,"name","firstname","lastname","password");
        AbstractAnnouncement announcement = new Announcement(2,"title","message", LocalDate.now(),user);
        announcement.setId(1);
        assertEquals(1,announcement.getId());
    }

    @Test
    void getTitle() {
        AbstractUser user = new User(1,"name","firstname","lastname","password");

        AbstractAnnouncement announcement = new Announcement(1,"title","message", LocalDate.now(),user);
        assertEquals("title",announcement.getTitle());
    }

    @Test
    void setTitle() {
        AbstractUser user = new User(1,"name","firstname","lastname","password");
        AbstractAnnouncement announcement = new Announcement(1,"title2","message", LocalDate.now(),user);
        announcement.setTitle("title");
        assertEquals("title",announcement.getTitle());
    }

    @Test
    void getMessage() {
        AbstractUser user = new User(1,"name","firstname","lastname","password");
        AbstractAnnouncement announcement = new Announcement(1,"title2","message", LocalDate.now(),user);
        assertEquals("message",announcement.getMessage());
    }

    @Test
    void setMessage() {
        AbstractUser user = new User(1,"name","firstname","lastname","password");
        AbstractAnnouncement announcement = new Announcement(1,"title2","message", LocalDate.now(),user);
        announcement.setMessage("msg");
        assertEquals("msg",announcement.getMessage());
    }
}