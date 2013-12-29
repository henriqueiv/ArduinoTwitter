/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import com.model.exceptions.TweetException;

/**
 *
 * @author henriquevalcanaia
 */
public class Tweet {

    private static final int ID_TOKEN = 1;
    private static final int ID_TWEET = 2;
    private static final char SEPARATOR = (char) 9679;
    private String tweet;
    private String token;

    public Tweet() {
    }

    public Tweet(String token, String tweet) {
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

    public void sendTweet(SerialClass serial) throws TweetException {
        if (this.validateTweet(this)) {
            serial.writeData(ID_TOKEN + SEPARATOR + this.getToken());
            serial.writeData(ID_TWEET + SEPARATOR + this.getTweet());
        }
    }

    @Override
    public String toString() {
        return "Tweet{" + "tweet=" + tweet + ", token=" + token + '}';
    }

    public boolean validateTweet(Tweet tweet) throws TweetException {
        if (this.isTokenValid(tweet)) {
            if (this.isTweetValid(tweet)) {
                return true;
            }
        }
        return false;
    }

    private boolean isTokenValid(Tweet tweet) throws TweetException {
        if (tweet.getToken() == null) {
            throw new TweetException("Token is null!");
        } else {
            if (tweet.getToken().isEmpty()) {
                throw new TweetException("Token is empty!");
            }
        }
        return true;
    }

    private boolean isTweetValid(Tweet tweet) throws TweetException {
        if (tweet.getTweet() == null) {
            throw new TweetException("Tweet is null!");
        } else {
            if (tweet.getTweet().isEmpty()) {
                throw new TweetException("Tweet is empty!");
            }
        }
        return true;
    }
}
