package ru.vsu.fkn.cg.task3;

import java.util.List;

public class Mesh {
    List<Vector3D> vertices;
    List<Face> faces;

    public Mesh(List<Vector3D> vertices, List<Face> faces) {
        this.vertices = vertices;
        this.faces = faces;
    }

    public List<Vector3D> getVertices() {
        return vertices;
    }

    public List<Face> getFaces() {
        return faces;
    }
}
