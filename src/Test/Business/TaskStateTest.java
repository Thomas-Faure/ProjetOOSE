package Test.Business;
/**
 *
 * @author Thomas Faure
 */
import BusinessLogic.Task.TaskState;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test Class to test the enumeration TaskState
 */
class TaskStateTest {
    /**
     * Method to test the state transformation to String
     */
    @Test
    void getStatetoString() {
        TaskState testState = TaskState.getStateByString("todo");
        String test = testState.getStatetoString();
        assertEquals("todo",test,"");
    }

    /**
     * Method to test the similarity between a state and his string
     */
    @Test
    void getStateByString() {
        assertEquals(TaskState.todo, TaskState.getStateByString("todo"),"");
    }
}