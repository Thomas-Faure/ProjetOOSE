package Test.DAO;


import BusinessLogic.Project.AbstractProject;
import BusinessLogic.Project.Project;
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
        AbstractProject project = new Project(0,null,null,null,true);
        assertEquals(false,ChatFacade.getInstance().getChatByProjectId(project.getId()));
        assertFalse(ChatFacade.getInstance().getChatByProjectId(0));
    }
}
