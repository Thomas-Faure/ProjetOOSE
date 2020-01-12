package Test.Buisness;

import BuisnessLogic.Sprint.AbstractSprint;
import BuisnessLogic.Sprint.Sprint;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


/**
 *
 * @author Guillaume Tessier
 */
public class SprintTest {

    @Test
    void getBeginDate(){
        String sprintName = "SprintTest";
        LocalDate beginDate = LocalDate.now();
        LocalDate endDate = LocalDate.now().plusDays(20);
        AbstractSprint sprintTest = new Sprint(sprintName,beginDate,endDate,0);
        assertEquals(beginDate,sprintTest.getBeginDate());
    }
}
