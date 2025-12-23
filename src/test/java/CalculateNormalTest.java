import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.vsu.fkn.cg.task3.math.NormalCalculator;
import ru.vsu.fkn.cg.task3.math.Vector3f;
import ru.vsu.fkn.cg.task3.model.Model;
import ru.vsu.fkn.cg.task3.objreader.ObjReader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class CalculateNormalTest {
    private ArrayList<Vector3f> trueNormals;
    private Model model;

    @BeforeEach
    void init() throws IOException {
        Path path = Paths.get("/Users/senpaka/programms/CG_Task3/src/main/java/ru/vsu/fkn/cg/task3/obj/Cube.obj");
        File file = path.toFile();
        String fileContent = Files.readString(file.toPath());
        model = ObjReader.read(fileContent);
    }

    @Test
    void normalTest(){

        ArrayList<Vector3f> trueNormal = model.normals;
        ArrayList<Vector3f> myNormal = NormalCalculator.calculateVerticesNormals(model.vertices, model.polygons);

        for(int i = 0; i < myNormal.size(); i++){
            System.out.println(trueNormal.get(i));
            System.out.println(myNormal.get(i));
            System.out.println("===========");
            assertTrue(myNormal.get(i).equals(trueNormal.get(i)));
        }
    }

}
