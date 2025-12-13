package ru.vsu.fkn.cg.task3;

import java.util.ArrayList;
import java.util.List;

public class Face {

    private int indexFace;
    private int[] vertexIndices;

    public Face(int indexFace, int v1, int v2, int v3) {
        this.indexFace = indexFace;
        this.vertexIndices = new int[]{v1, v2, v3};
    }

    public int[] getVertexIndices(){
        return vertexIndices;
    }

    public int getIndexFace() {
        return indexFace;
    }
}
