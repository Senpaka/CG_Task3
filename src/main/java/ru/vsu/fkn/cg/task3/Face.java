package ru.vsu.fkn.cg.task3;

import java.util.ArrayList;
import java.util.List;

public class Face {

    int indexVector1;
    int indexVector2;
    int indexVector3;

    public Face(int indexVector1, int indexVector2, int indexVector3) {
        this.indexVector1 = indexVector1;
        this.indexVector2 = indexVector2;
        this.indexVector3 = indexVector3;
    }

    public List<Integer> getFace(){
        ArrayList<Integer> indexes = new ArrayList<Integer>();
        indexes.add(indexVector1);
        indexes.add(indexVector2);
        indexes.add(indexVector3);

        return indexes;
    }

    public int getIndexVector1() {
        return indexVector1;
    }

    public int getIndexVector2() {
        return indexVector2;
    }

    public int getIndexVector3() {
        return indexVector3;
    }
}
