/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tweetsender;

/**
 *
 * @author henriquevalcanaia
 */
public class Tweet {
    private static String tweet;
    private static String token;

    public Tweet() {
    }
    
    public Tweet(String tweet, String token) {
        Tweet.tweet = tweet;
        Tweet.token = token;
    }

    public static String getTweet() {
        return tweet;
    }

    public static void setTweet(String tweet) {
        Tweet.tweet = tweet;
    }

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        Tweet.token = token;
    }
    
    public static boolean sendTweet(){
        return true;
    }
    
}
