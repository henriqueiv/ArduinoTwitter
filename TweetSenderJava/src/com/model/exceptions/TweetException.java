/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model.exceptions;

/**
 *
 * @author henriquevalcanaia
 */
public class TweetException extends Exception {

    public TweetException() {
        super();
    }

    public TweetException(String message) {
        super(message);
    }

    public TweetException(String message, Throwable cause) {
        super(message, cause);
    }

    public TweetException(Throwable cause) {
        super(cause);
    }
}
