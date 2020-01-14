package Test.Business;

import BusinessLogic.Project.AbstractProject;
import BusinessLogic.Project.Project;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


/**
 *
 * @author Rémi Salmi
 */
public class ProjectTest {

    @Test
    void isAgile(){
        AbstractProject project = new Project(1,"PPM","Ma desc",LocalDate.now(),true);
        AbstractProject project2 = new Project(2,"PPM","Ma desc",LocalDate.now(),false);
        assertTrue(project.isAgile());
        assertFalse(project2.isAgile());
    }
}
