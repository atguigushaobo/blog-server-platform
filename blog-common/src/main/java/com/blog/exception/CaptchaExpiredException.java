package com.blog.exception;

public class CaptchaExpiredException extends BaseException{
    public CaptchaExpiredException() {
    }
    public CaptchaExpiredException(String message) {
        super(message);
    }
}
