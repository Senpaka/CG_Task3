package ru.vsu.fkn.cg.task3;

import java.util.ArrayList;
import java.util.List;

public class Face {

    private int indexFace;
    private int[] vertexIndices;

    public Face(int indexFace, int[] array) {
        this.indexFace = indexFace;
        this.vertexIndices = array;
    }

    public int[] getVertexIndices(){
        return vertexIndices;
    }

    public int getIndexFace() {
        return indexFace;
    }
}
