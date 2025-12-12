package ru.vsu.fkn.cg.task3;

import java.util.ArrayList;
import java.util.List;

public class Normals {

    List<Vector3D> vertices;
    List<Vector3D> faceNormals;
    List<Vector3D> verticesNormals;
    List<Face> faces;

    public Normals(List<Vector3D> vertices, List<Face> faces) {
        this.vertices = vertices;
        this.faces = faces;
        this.faceNormals = new ArrayList<>();
    }

    public Vector3D calculateTriangleNormal(Vector3D vector1, Vector3D vector2, Vector3D vector3){

        Vector3D edge1 = Vector3D.subtract(vector2, vector1);
        Vector3D edge2 = Vector3D.subtract(vector3, vector1);
        Vector3D normal = Vector3D.cross(edge1, edge2);

        return normal.normalize();
    }

    public void calculateFaceNormals(){
        faceNormals.clear();

        for (Face face: faces){

            int index1 = face.getIndexVector1();
            int index2 = face.getIndexVector2();
            int index3 = face.getIndexVector3();

            Vector3D vector1 = vertices.get(index1);
            Vector3D vector2 = vertices.get(index2);
            Vector3D vector3 = vertices.get(index3);

            Vector3D normal = calculateTriangleNormal(vector1, vector2, vector3);
            faceNormals.add(normal);
        }
    }

    public List<Vector3D> calculateVertexNormals(){
        verticesNormals = new ArrayList<>(vertices.size());
        int[] faceCount = new int[vertices.size()];

        for (int i = 0; i < vertices.size(); i++){
            verticesNormals.add(new Vector3D());
            faceCount[i] = 0;
        }

        if (faceNormals.isEmpty()){
            calculateFaceNormals();
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

    private Vector3D[] getFaceVertices(Face face){
        int[] indices = face.getVertexIndices();
        Vector3D[] verticesArray = new Vector3D[indices.length];

        for (int i = 0; i < indices.length; i++) {
            verticesArray[i] = vertices.get(indices[i]);
        }

        return verticesArray;
    }
}
