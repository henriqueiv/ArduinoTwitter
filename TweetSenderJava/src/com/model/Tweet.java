/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

/**
 *
 * @author henriquevalcanaia
 */
public class Tweet {
    
    private static final int ID_TOKEN   = 1;
    private static final int ID_TWEET   = 2;
    private static final char SEPARATOR = (char) 9679;

    private String tweet;
    private String token;

    public Tweet() {
    }

    public Tweet(String tweet, String token) {
        this.tweet = tweet;
        this.token = token;
    }

    public String getTweet() {
        return this.tweet;
    }

    public void setTweet(String tweet) {
        this.tweet = tweet;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean sendTweet(SerialClass serial) {
        serial.writeData(ID_TOKEN + SEPARATOR + this.getToken());
        serial.writeData(ID_TWEET + SEPARATOR + this.getTweet());
        return true;
    }

    @Override
    public String toString() {
        return "Tweet{" + "tweet=" + tweet + ", token=" + token + '}';
    }
}
