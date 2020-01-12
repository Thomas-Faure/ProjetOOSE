package Test.DAO;


import Facade.ChatFacade;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 *
 * @author Guillaume Tessier
 */
public class ChatDAOMySQLTest {


    @Test
    void getChatByProjectId(){
        assertEquals(false,ChatFacade.getInstance().getChatByProjectId(0));
        assertFalse(ChatFacade.getInstance().getChatByProjectId(0));
    }
}
