package ru.vsu.fkn.cg.task3.exeptions;

public class ObjWriterException extends RuntimeException {
    public ObjWriterException(String errorMessage) {
        super(errorMessage);
    }
}