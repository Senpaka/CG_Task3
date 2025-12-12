package ru.vsu.fkn.cg.task3;

public class Point3D {

    private double x;
    private double y;
    private double z;

    public Point3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double length(Point3D to){
        double dx = to.getX() - this.x;
        double dy = to.getY() - this.y;
        double dz = to.getZ() - this.z;

        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }

    public static double length(Point3D from, Point3D to){
        double dx = to.getX() - from.getX();
        double dy = to.getY() - from.getY();
        double dz = to.getZ() - from.getZ();

        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }
}
