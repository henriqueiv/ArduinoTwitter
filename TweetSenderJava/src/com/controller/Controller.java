/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.model.SerialCom;
import com.model.Tweet;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;

/**
 *
 * @author henriquevalcanaia
 */
public class Controller {

    private static final char CHAR_PONTO = (char) 9679;
    private static final char CHAR_ASTERISCO = (char) 42;
    private static final char CHAR_NENHUM = (char) 0;
    private static final String WEBSITE_TOKEN = "http://arduino-tweet.appspot.com/oauth/twitter/login";
    
    private JTextArea taTweet;
    private JLabel lbCountChar;
    private JPasswordField pfToken;
    private JCheckBox cbShowToken;
    private Tweet tweet;
    private SerialCom serial;

    public Controller() {
    }

    public JTextArea getTaTweet() {
        return this.taTweet;
    }

    public void setTaTweet(JTextArea taTweet) {
        this.taTweet = taTweet;
        this.taTweet.setLineWrap(true);
        this.taTweet.setWrapStyleWord(true);
    }

    public JLabel getLbCountChar() {
        return this.lbCountChar;
    }

    public void setLbCountChar(JLabel lbCountChar) {
        this.lbCountChar = lbCountChar;
    }

    public JPasswordField getPfToken() {
        return pfToken;
    }

    public void setPfToken(JPasswordField pfToken) {
        this.pfToken = pfToken;
    }

    public JCheckBox getCbShowToken() {
        return cbShowToken;
    }

    public void setCbShowToken(JCheckBox cbShowToken) {
        this.cbShowToken = cbShowToken;
    }

    public void showToken(boolean show) {
        if (show) {
            this.pfToken.setEchoChar(CHAR_NENHUM);
        } else {
            this.pfToken.setEchoChar(CHAR_ASTERISCO);
        }
    }

    public void calcTweetLength(JTextArea taTweet, JLabel lbCountChar) {
        String lsLength;
        Integer liLength;
        liLength = taTweet.getDocument().getLength();
        lsLength = liLength.toString();
        lbCountChar.setText(lsLength + "/160");
        if (liLength <= 160) {
            lbCountChar.setForeground(new Color(51, 153, 0));
        } else {
            if (liLength > 160) {
                lbCountChar.setForeground(Color.RED);
            }
        }
    }

    /* private void goWebsite(JLabel website) {
     website.addMouseListener(new MouseAdapter() {
     @Override
     public void mouseClicked(MouseEvent e) {
     try {
     Desktop.getDesktop().browse(new URI("website"));
     } catch (URISyntaxException | IOException ex) {
     //It looks like there's a problem
     }
     }
     });
     }*/
    public void getToken() {
        try {
            Desktop.getDesktop().browse(new URI(WEBSITE_TOKEN));
        } catch (URISyntaxException | IOException ex) {
            JOptionPane.showMessageDialog(null, "Ops, it seems that something went wrong when trying to get your token!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void showHelp() {
        JOptionPane.showMessageDialog(null, "This aplication was created by Henrique Valcanaia to test a Arduino Twitter API.", "Help", JOptionPane.QUESTION_MESSAGE);
    }
}
