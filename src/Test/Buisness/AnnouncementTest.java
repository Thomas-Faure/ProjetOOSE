package Test.Buisness;
/**
 *
 * @author Thomas Faure
 */
import BusinessLogic.Announcement.AbstractAnnouncement;
import BusinessLogic.Announcement.Announcement;
import BusinessLogic.User.AbstractUser;
import BusinessLogic.User.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test Class to test announcement
 */
class AnnouncementTest {

    /**
     * Test the id of an announcement
     */
    @Test
    void getId() {
        AbstractUser user = new User(1,"name","firstname","lastname","password");

        AbstractAnnouncement announcement = new Announcement(1,"title","message", LocalDate.now(),user);
        assertEquals(1,announcement.getId());
    }

    /**
     * Test the id's setter of an announcement
     */
    @Test
    void setId() {
        AbstractUser user = new User(1,"name","firstname","lastname","password");
        AbstractAnnouncement announcement = new Announcement(2,"title","message", LocalDate.now(),user);
        announcement.setId(1);
        assertEquals(1,announcement.getId());
    }
    /**
     * Test the title of an announcement
     */
    @Test
    void getTitle() {
        AbstractUser user = new User(1,"name","firstname","lastname","password");

        AbstractAnnouncement announcement = new Announcement(1,"title","message", LocalDate.now(),user);
        assertEquals("title",announcement.getTitle());
    }
    /**
     * Test the title's setter of an announcement
     */
    @Test
    void setTitle() {
        AbstractUser user = new User(1,"name","firstname","lastname","password");
        AbstractAnnouncement announcement = new Announcement(1,"title2","message", LocalDate.now(),user);
        announcement.setTitle("title");
        assertEquals("title",announcement.getTitle());
    }

    /**
     * Test the message of an announcement
     */
    @Test
    void getMessage() {
        AbstractUser user = new User(1,"name","firstname","lastname","password");
        AbstractAnnouncement announcement = new Announcement(1,"title2","message", LocalDate.now(),user);
        assertEquals("message",announcement.getMessage());
    }
    /**
     * Test the message's setter of an announcement
     */
    @Test
    void setMessage() {
        AbstractUser user = new User(1,"name","firstname","lastname","password");
        AbstractAnnouncement announcement = new Announcement(1,"title2","message", LocalDate.now(),user);
        announcement.setMessage("msg");
        assertEquals("msg",announcement.getMessage());
    }
}