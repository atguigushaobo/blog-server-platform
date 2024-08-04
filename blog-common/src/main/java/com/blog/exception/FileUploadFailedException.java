package com.blog.exception;

public class FileUploadFailedException extends BaseException{
    public FileUploadFailedException() {
    }
    public FileUploadFailedException(String message) {
        super(message);
    }
}
