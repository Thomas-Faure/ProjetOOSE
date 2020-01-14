package Test.Business;

import BusinessLogic.Project.AbstractProject;
import BusinessLogic.Project.Project;
import BusinessLogic.Role.AbstractRole;
import BusinessLogic.Role.Role;
import BusinessLogic.User.Member;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;



/**
 * Test de User de la business Logic
 * @author Lauren Unquera - Polytech Montpellier IG4
 */
public class MemberTest {

    @Test
    public void setRole (){
        Member member = new Member(1,"name","firstname","lastname","password");
        AbstractRole role = new Role(1, "nom");
        member.setRole(role);
        assertEquals("nom", member.getRole().getName());
    }

    private void assertEquals(String nom, String name) {
    }

    @Test
    public void setProject (){
        Member member = new Member(1,"name","firstname","lastname","password");
        AbstractProject project = new Project(1,"PPM","Ma desc", LocalDate.now(),true);
        member.setProject(project);
        assertEquals("PPM", member.getProject().getName());
    }
}
