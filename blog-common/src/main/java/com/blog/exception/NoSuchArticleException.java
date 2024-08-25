package com.blog.exception;

public class NoSuchArticleException extends BaseException{
    public NoSuchArticleException() {
    }
    public NoSuchArticleException(String message) {
        super(message);
    }
}
