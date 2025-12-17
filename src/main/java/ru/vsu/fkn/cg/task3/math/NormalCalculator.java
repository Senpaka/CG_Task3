package ru.vsu.fkn.cg.task3.math;

import ru.vsu.fkn.cg.task3.model.Polygon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NormalCalculator {

    private static void putToDict(Map<Integer, Pair> dict, ArrayList<Vector3f> vertices, ArrayList<Integer> indices, int prevIndex, int currIndex, int nextIndex) {
        Vector3f prevVertex = vertices.get(indices.get(prevIndex));
        Vector3f currVertex = vertices.get(indices.get(currIndex));
        Vector3f nextVertex = vertices.get(indices.get(nextIndex));

        Vector3f a = Vector3f.subtract(prevVertex, currVertex);
        Vector3f b = Vector3f.subtract(nextVertex, currVertex);
        Vector3f normal = Vector3f.cross(b, a);
        normal.normalize();

        if (!dict.containsKey(indices.get(currIndex))) {
            Pair pair = new Pair();
            dict.put(indices.get(currIndex), pair);
        }
        dict.get(indices.get(currIndex)).v.sum(normal);
        dict.get(indices.get(currIndex)).c++;
    }

    public static ArrayList<Vector3f> calculateVerticesNormals(ArrayList<Vector3f> vertices, ArrayList<Polygon> polygons) {
        ArrayList<Vector3f> normals = new ArrayList<>();
        Map<Integer, Pair> dict = new HashMap<>();
        for (Polygon polygon : polygons) {
            ArrayList<Integer> indices = polygon.getVertexIndices();
            int prevIndex = indices.size() - 1;
            int currIndex = 0;
            int nextIndex = 1;
            while (nextIndex < indices.size()) {
                putToDict(dict, vertices, indices, prevIndex, currIndex, nextIndex);

                prevIndex = currIndex;
                currIndex = nextIndex;
                nextIndex++;
            }
            putToDict(dict, vertices, indices, prevIndex, currIndex, 0);
        }

        for (int i = 0; i < vertices.size(); i++) {
            if (dict.get(i) == null) {
                normals.add(new Vector3f());
                continue;
            }

            Pair pair = dict.get(i);
            Vector3f normal = pair.v;
            int amount = pair.c;

            normal.divide(amount);
            normal.normalize();

            normals.add(normal);
        }

        return normals;
    }

    public Vector3f calculatePolygonNormal(Vector3f vector1, Vector3f vector2, Vector3f vector3){

        Vector3f edge1 = Vector3f.subtract(vector2, vector1);
        Vector3f edge2 = Vector3f.subtract(vector3, vector1);

        return Vector3f.cross(edge1, edge2);
    }

    private static class Pair {
        Vector3f v = new Vector3f();
        int c = 0;
    }
}

