package Test.Buisness;

import BusinessLogic.Ressource.AbstractResource;
import BusinessLogic.Ressource.Resource;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Guillaume Tessier
 */
public class ResourceTest {

    @Test
    void getFilename() {
        String path = "/path/to/the/file";
        String filename = "filenameTest";
        AbstractResource ressourceTest = new Resource(path,filename,0);
        assertEquals(filename,ressourceTest.getFilename());
    }
}
