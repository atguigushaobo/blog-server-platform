package com.blog.exception;

import com.fasterxml.jackson.databind.ser.Serializers;

public class CaptchaIncorrectException extends BaseException {
    public CaptchaIncorrectException(){

    }
    public CaptchaIncorrectException(String message){
        super(message);
    }
}
