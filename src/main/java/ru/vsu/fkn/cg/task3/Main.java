package ru.vsu.fkn.cg.task3;


import ru.vsu.fkn.cg.task3.old.NormalCalculator;
import ru.vsu.fkn.cg.task3.math.Vector3f;
import ru.vsu.fkn.cg.task3.model.Model;
import ru.vsu.fkn.cg.task3.objreader.ObjReader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("/Users/senpaka/programms/CG_Task3/src/main/java/ru/vsu/fkn/cg/task3/obj/Cube.obj");
        File file = path.toFile();
        String fileContent = Files.readString(file.toPath());
        Model model = ObjReader.read(fileContent);

        List<Vector3f> normals = NormalCalculator.calculateVerticesNormals(model.vertices, model.polygons) ;

        for(Vector3f v: model.vertices){
            System.out.println(v.toString());
        }

        for(Vector3f v: normals){
            System.out.println(v.toString());
        }
    }
}