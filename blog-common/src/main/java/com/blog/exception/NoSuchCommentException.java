package com.blog.exception;

public class NoSuchCommentException extends BaseException{
    public NoSuchCommentException() {
    }
    public NoSuchCommentException(String message) {
        super(message);
    }
}
