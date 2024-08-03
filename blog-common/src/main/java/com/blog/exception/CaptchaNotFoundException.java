package com.blog.exception;

public class CaptchaNotFoundException extends BaseException{
    public CaptchaNotFoundException() {
    }
    public CaptchaNotFoundException(String message) {
        super(message);
    }
}
