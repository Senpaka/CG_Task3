package ru.vsu.fkn.cg.task3;


import ru.vsu.fkn.cg.task3.math.NormalCalculator;
import ru.vsu.fkn.cg.task3.math.Vector3f;
import ru.vsu.fkn.cg.task3.model.Model;
import ru.vsu.fkn.cg.task3.model.Polygon;
import ru.vsu.fkn.cg.task3.objwriter.ObjWriter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("/Users/senpaka/programms/CG_Task3/src/main/java/ru/vsu/fkn/cg/task3/obj/cube1.obj");
        File file = path.toFile();
        String fileContent = Files.readString(file.toPath());
        Model model = ObjReader.read(fileContent);

        for(Polygon polygon: model.polygons){
            for(int k: polygon.getTextureVertexIndices()){
                System.out.print("" + k + " ");
            }
            System.out.println();
        }

        NormalCalculator.calculateVerticesNormals(model) ;


        for(Polygon polygon: model.polygons){
            for(int k: polygon.getTextureVertexIndices()){
                System.out.print("" + k + " ");
            }
            System.out.println();
        }
        ObjWriter.write("cubeWrited", model);

    }
}