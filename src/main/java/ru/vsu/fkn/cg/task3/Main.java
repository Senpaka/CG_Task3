package ru.vsu.fkn.cg.task3;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Vector3D> vertices = Arrays.asList(
                new Vector3D(-1, -1, -1), // 0
                new Vector3D( 1, -1, -1), // 1
                new Vector3D( 1,  1, -1), // 2
                new Vector3D(-1,  1, -1), // 3

                new Vector3D(-1, -1,  1), // 4
                new Vector3D( 1, -1,  1), // 5
                new Vector3D( 1,  1,  1), // 6
                new Vector3D(-1,  1,  1)  // 7
        );

        List<Face> faces = Arrays.asList(
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

        NormalsCalculator calculator = new NormalsCalculator();
        Mesh mesh = new Mesh(vertices, faces);

        List<Vector3D> faceNormals = calculator.calculateFaceNormals(mesh);
        List<Vector3D> normals = calculator.calculateVertexNormals(mesh, faceNormals) ;

        for(Vector3D v: normals){
            System.out.println(v.toString());
        }
    }
}