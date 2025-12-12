package ru.vsu.fkn.cg.task3;

public class Vector3D {

    private int x;
    private int y;
    private int z;
    private int w;

    public Vector3D(int x, int y, int z, int w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public Vector3D(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static Vector3D cross(Vector3D vector1, Vector3D vector2){
        int x = vector1.getY() * vector2.getZ() - vector1.getZ() * vector2.getY();
        int y = vector1.getZ() * vector2.getX() - vector1.getX() * vector2.getZ();
        int z = vector1.getX() * vector2.getY() - vector1.getY() * vector2.getX();
        return new Vector3D(x, y, z);
    }

    public static int dot(Vector3D vector1, Vector3D vector2){
        return vector1.getX() * vector2.getX() +
                vector1.getY() * vector2.getY() +
                vector1.getZ() * vector2.getZ();
    }

    public static Vector3D divide(Vector3D vector1, Vector3D vector2){

        if (vector2.hasZero()){
            throw new ArithmeticException("У вектора присутствует нулевая координата");
        }

        int x = vector1.getX() / vector2.getX();
        int y = vector1.getY() / vector2.getY();
        int z = vector1.getZ() / vector2.getZ();
        return new Vector3D(x, y, z);
    }

    public static Vector3D multiply(Vector3D vector1, Vector3D vector2){
        int x = vector1.getX() * vector2.getX();
        int y = vector1.getY() * vector2.getY();
        int z = vector1.getZ() * vector2.getZ();
        return new Vector3D(x, y, z);
    }

    public static Vector3D sum(Vector3D vector1, Vector3D vector2){
        int x = vector1.getX() + vector2.getX();
        int y = vector1.getY() + vector2.getY();
        int z = vector1.getZ() + vector2.getZ();
        return new Vector3D(x, y, z);
    }

    public static Vector3D minus(Vector3D vector1, Vector3D vector2){
        int x = vector1.getX() - vector2.getX();
        int y = vector1.getY() - vector2.getY();
        int z = vector1.getZ() - vector2.getZ();
        return new Vector3D(x, y, z);
    }

    private boolean hasZero(){
        return this.x == 0 || this.y == 0 || this.z == 0;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public int getW() {
        return w;
    }
}
