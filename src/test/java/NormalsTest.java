import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import ru.vsu.fkn.cg.task3.Face;
import ru.vsu.fkn.cg.task3.Mesh;
import ru.vsu.fkn.cg.task3.NormalsCalculator;
import ru.vsu.fkn.cg.task3.Vector3D;

import java.util.Arrays;
import java.util.List;

public class NormalsTest {

    private List<Vector3D> vertices;
    private List<Face> faces;
    private List<Vector3D> vertexNormals;
    List<Vector3D> trueFaceNormals;
    List<Vector3D> faceNormals;
    List<Vector3D> normals;

    @BeforeEach
    void setUp(){
        this.vertices = Arrays.asList(
                new Vector3D(-1, -1, -1),
                new Vector3D( 1, -1, -1),
                new Vector3D( 1,  1, -1),
                new Vector3D(-1,  1, -1),

                new Vector3D(-1, -1,  1),
                new Vector3D( 1, -1,  1),
                new Vector3D( 1,  1,  1),
                new Vector3D(-1,  1,  1)
        );

        this.faces = Arrays.asList(
                // Нижняя грань (z = 0)
                new Face(0, new int[]{0, 1, 2, 3}),

                // Верхняя грань (z = 1)
                new Face(1, new int[]{4, 5, 6, 7}),

                // Передняя грань (y = 0)
                new Face(2, new int[]{0, 1, 5, 4}),

                // Задняя грань (y = 1)
                new Face(3, new int[]{3, 2, 6, 7}),

                // Левая грань (x = 0)
                new Face(4, new int[]{0, 3, 7, 4}),

                // Правая грань (x = 1)
                new Face(5, new int[]{1, 2, 6, 5})
        );

        this.vertexNormals = Arrays.asList(
                new Vector3D(-0.57735, -0.57735, -0.57735),

                new Vector3D(0.57735, -0.57735, -0.57735),

                new Vector3D(0.57735, 0.57735, -0.57735),

                new Vector3D(-0.57735, 0.57735, -0.57735),

                new Vector3D(-0.57735, -0.57735, 0.57735),

                new Vector3D(0.57735, -0.57735, 0.57735),

                new Vector3D(0.57735, 0.57735, 0.57735),

                new Vector3D(-0.57735, 0.57735, 0.57735)
        );

        this.trueFaceNormals = Arrays.asList(
                // Нижняя грань (z = -1) - индексы вершин: 0, 1, 2, 3
                new Vector3D(0, 0, -1),  // Смотрит вниз

                // Верхняя грань (z = 1) - индексы вершин: 4, 5, 6, 7
                new Vector3D(0, 0, 1),   // Смотрит вверх

                // Передняя грань (y = -1) - индексы вершин: 0, 1, 5, 4
                new Vector3D(0, -1, 0),  // Смотрит вперёд (в отрицательном направлении Y)

                // Задняя грань (y = 1) - индексы вершин: 3, 2, 6, 7
                new Vector3D(0, 1, 0),   // Смотрит назад (в положительном направлении Y)

                // Левая грань (x = -1) - индексы вершин: 0, 3, 7, 4
                new Vector3D(-1, 0, 0),  // Смотрит влево

                // Правая грань (x = 1) - индексы вершин: 1, 2, 6, 5
                new Vector3D(1, 0, 0)    // Смотрит вправо
        );

        NormalsCalculator calculator = new NormalsCalculator();
        Mesh mesh = new Mesh(vertices, faces);

        faceNormals = calculator.calculateFaceNormals(mesh);
        normals = calculator.calculateVertexNormals(mesh, faceNormals);
    }

    @Test
    public void testCubeFaceNormals(){
        for (int i = 0; i < faceNormals.size(); i++) {
            assertTrue(faceNormals.get(i).equals(trueFaceNormals.get(i)));
        }
    }

    @Test
    public void testCubeNormals(){
        for (int i = 0; i < normals.size(); i++) {
            assertTrue(normals.get(i).equals(vertexNormals.get(i)));
        }
    }
}
