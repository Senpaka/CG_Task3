package ru.vsu.fkn.cg.task3;

import java.util.ArrayList;
import java.util.List;

public class NormalsCalculator {
    private Vector3D calculateTriangleNormal(Vector3D vector1, Vector3D vector2, Vector3D vector3, Vector3D centerModel){

        Vector3D edge1 = Vector3D.subtract(vector2, vector1);
        Vector3D edge2 = Vector3D.subtract(vector3, vector1);
        Vector3D normal = Vector3D.cross(edge1, edge2);

        Vector3D centerFace = Vector3D.divide(
                Vector3D.sum(
                        Vector3D.sum(
                                vector1, vector2), vector3), 3);

        Vector3D centerModelToFace = Vector3D.subtract(centerModel, centerFace);

        if (Vector3D.dot(normal, centerModelToFace) < 0){
            normal = Vector3D.negative(normal);
        }

        return normal.normalize();
    }

    public List<Vector3D> calculateFaceNormals(Mesh mesh){
        List<Vector3D> vertices = mesh.getVertices();
        List<Face> faces = mesh.getFaces();

        List<Vector3D> faceNormals = new ArrayList<>();
        Vector3D centerModel = calculateCenterModel(mesh.getVertices());

        for (Face face: faces){

            int[] indices = face.getVertexIndices();

            Vector3D vector1 = vertices.get(indices[0]);
            Vector3D vector2 = vertices.get(indices[1]);
            Vector3D vector3 = vertices.get(indices[2]);

            Vector3D normal = calculateTriangleNormal(vector1, vector2, vector3, centerModel);
            faceNormals.add(normal);
        }

        return faceNormals;
    }

    public List<Vector3D> calculateVertexNormals(Mesh mesh, List<Vector3D> faceNormals){
        List<Vector3D> vertices = mesh.getVertices();
        List<Face> faces = mesh.getFaces();

        List<Vector3D> verticesNormals = new ArrayList<>(vertices.size());
        int[] faceCount = new int[vertices.size()];

        for (int i = 0; i < vertices.size(); i++){
            verticesNormals.add(new Vector3D());
            faceCount[i] = 0;
        }

        for (int i = 0; i < faces.size(); i++) {
            Face face = faces.get(i);
            Vector3D faceNormal = faceNormals.get(i);

            for (int verticesIndex: face.getVertexIndices()) {
                Vector3D currentSum = verticesNormals.get(verticesIndex);
                Vector3D newSum = Vector3D.sum(currentSum, faceNormal);
                verticesNormals.set(verticesIndex, newSum);
                faceCount[verticesIndex] += 1;
            }
        }

        for (int i = 0; i < verticesNormals.size(); i++) {
            Vector3D normal = verticesNormals.get(i);
            int count = faceCount[i];

            if (count > 0){
                normal = Vector3D.divide(normal, faceCount[i]);
                verticesNormals.set(i, normal.normalize());
            }else{
                verticesNormals.set(i, new Vector3D(0, 1, 0));
            }

        }

        return verticesNormals;
    }

    private Vector3D[] getFaceVertices(Face face, List<Vector3D> vertices){
        int[] indices = face.getVertexIndices();
        Vector3D[] verticesArray = new Vector3D[indices.length];

        for (int i = 0; i < indices.length; i++) {
            verticesArray[i] = vertices.get(indices[i]);
        }

        return verticesArray;
    }

    private Vector3D calculateCenterModel(List<Vector3D> vertices){
        Vector3D vector = new Vector3D();

        for (int i = 0; i < vertices.size(); i++) {
            vector = Vector3D.sum(vector, vertices.get(i));
        }

        return Vector3D.divide(vector, vertices.size());
    }
}
