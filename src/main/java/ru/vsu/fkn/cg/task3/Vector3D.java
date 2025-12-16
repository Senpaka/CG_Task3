package ru.vsu.fkn.cg.task3;

public class Vector3D {

    public double x;
    public double y;
    public double z;

    private static final double Epsilon = 1e-6;

    public Vector3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3D(){
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    public double length(){
        return Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
    }

    public Vector3D normalize(){
        double length = this.length();

        if (Math.abs(length) < Epsilon){
            throw new ArithmeticException("Ошибка деления на 0. Передан нулевой вектор");
        }

        return Vector3D.divide(this, length);
    }

    public static Vector3D cross(Vector3D vector1, Vector3D vector2){
        double x = vector1.getY() * vector2.getZ() - vector1.getZ() * vector2.getY();
        double y = vector1.getZ() * vector2.getX() - vector1.getX() * vector2.getZ();
        double z = vector1.getX() * vector2.getY() - vector1.getY() * vector2.getX();

        return new Vector3D(x, y, z);
    }

    public static double dot(Vector3D vector1, Vector3D vector2){
        return vector1.getX() * vector2.getX() +
                vector1.getY() * vector2.getY() +
                vector1.getZ() * vector2.getZ();
    }

    public static Vector3D divide(Vector3D vector, double scalar){
        if (Math.abs(scalar) < Epsilon){
            throw new ArithmeticException("Деление на 0. Скаляр близок к нулю");
        }

        double invScalar = 1 / scalar;
        double x = vector.getX() * invScalar;
        double y = vector.getY() * invScalar;
        double z = vector.getZ() * invScalar;
        return new Vector3D(x, y, z);
    }

    public static Vector3D negative(Vector3D vector){
        return new Vector3D(-vector.getX(), -vector.getY(), -vector.getZ());
    }

    public static Vector3D multiply(Vector3D vector1, Vector3D vector2){
        double x = vector1.getX() * vector2.getX();
        double y = vector1.getY() * vector2.getY();
        double z = vector1.getZ() * vector2.getZ();
        return new Vector3D(x, y, z);
    }

    public static Vector3D multiply(Vector3D vector, double scalar){
        double x = vector.getX() * scalar;
        double y = vector.getY() * scalar;
        double z = vector.getZ() * scalar;
        return new Vector3D(x, y, z);
    }

    public static Vector3D sum(Vector3D vector1, Vector3D vector2){
        double x = vector1.getX() + vector2.getX();
        double y = vector1.getY() + vector2.getY();
        double z = vector1.getZ() + vector2.getZ();
        return new Vector3D(x, y, z);
    }

    public static Vector3D subtract(Vector3D vector1, Vector3D vector2){
        double x = vector1.getX() - vector2.getX();
        double y = vector1.getY() - vector2.getY();
        double z = vector1.getZ() - vector2.getZ();
        return new Vector3D(x, y, z);
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

    public boolean equals(Vector3D v){
        boolean a = (this.getX() - v.getX()) < Epsilon;
        boolean b = (this.getY() - v.getY()) < Epsilon;
        boolean c = (this.getZ() - v.getZ()) < Epsilon;
        return a && b && c;
    }

    @Override
    public String toString(){
        return x + " " + y + " " + z;
    }
}
