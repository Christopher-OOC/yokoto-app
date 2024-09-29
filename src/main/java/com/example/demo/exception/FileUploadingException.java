package com.example.demo.exception;

public class FileUploadingException extends RuntimeException {

    public FileUploadingException() {
        super("We have encounter a problem while uploading your file, try again!");
    }
}
