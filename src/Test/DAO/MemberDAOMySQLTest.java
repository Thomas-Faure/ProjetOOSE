package Test.DAO;

import BusinessLogic.Project.AbstractProject;
import BusinessLogic.Project.Project;
import BusinessLogic.User.Member;
import DAO.User.Member.MemberDAO;
import DAO.User.Member.MemberDAOMySQL;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test de Role du DAO
 * @author Lauren Unquera - Polytech Montpellier IG4
 */
public class MemberDAOMySQLTest {
    @Test
    void save() {
        MemberDAO dao = new MemberDAOMySQL();
        Member member = new Member(1,"name","firstname","lastname","password");
        AbstractProject project = new Project(2,"PPM","Ma desc", LocalDate.now(),true);
        member.setProject(project);
        boolean valid = dao.save(member);
        assertEquals(true, valid, "to save a new user");
    }
}
