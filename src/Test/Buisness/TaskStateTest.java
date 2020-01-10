package Test.Buisness;
/**
 *
 * @author Thomas Faure
 */
import BuisnessLogic.Task.TaskState;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskStateTest {
    @Test
    void getStatetoString() {
        TaskState testState = TaskState.getStateByString("todo");
        String test = testState.getStatetoString();
        assertEquals("todo",test,"");
    }
    @Test
    void getStateByString() {
        assertEquals(TaskState.todo, TaskState.getStateByString("todo"),"");
    }
}