/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.model.Port;
import com.model.SerialClass;
import com.model.Tweet;
import gnu.io.CommPortIdentifier;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButtonMenuItem;
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
    private SerialClass serial;

    public Controller() {
        serial = new SerialClass();
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
        final JPanel panel = new JPanel();
        JOptionPane.showMessageDialog(panel, "This aplication was created by Henrique Valcanaia to test a Arduino Twitter API.", "Help", JOptionPane.QUESTION_MESSAGE);
    }

    public void createPortsMenu(JMenu menu) {
        ArrayList<Port> ports;
        this.clearMenuItems(menu);
        ports = this.getSerialPorts();
        ButtonGroup groupMenu = new ButtonGroup();
        for (Port port : ports) {
            //System.out.println(port.toString());
            //if (port.getPortType() == CommPortIdentifier.PORT_PARALLEL) {
            JRadioButtonMenuItem portMenu = new JRadioButtonMenuItem();
            portMenu.setText(port.getPortName());
            ActionListener al = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Controller.selectPortMenu(e.getSource());
                }
            };
            portMenu.addActionListener(al);
            groupMenu.add(portMenu);
            menu.add(portMenu);
            //}
        }
    }

    private void clearMenuItems(JMenu menu) {
        while (menu.getItemCount() > 0) {
            menu.remove(menu.getItemCount() - 1);
        }
    }

    private ArrayList<Port> getSerialPorts() {
        ArrayList<Port> ports = new ArrayList<>();
        Enumeration portList = CommPortIdentifier.getPortIdentifiers();
        while (portList.hasMoreElements()) {
            CommPortIdentifier portId = (CommPortIdentifier) portList.nextElement();
            Port port = new Port();
            port.setPortName(portId.getName());
            port.setPortType(portId.getPortType());
            ports.add(port);
        }
        return ports;
    }

    public static void selectPortMenu(Object source) {
        System.out.println(source.getClass().getCanonicalName());
        if (source instanceof JMenuItem) {
            JMenuItem m = (JMenuItem) source;
            System.out.println("Setar porta " + m.getText());
        } else {
            JOptionPane.showMessageDialog(null, "Invalid class used on "
                    + Controller.class + ".selectPortMenu()\n"
                    + "Class: " + source.getClass().getName(),
                    "Dev Mode", JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean sendTweet(Tweet tweet) {
        boolean tweetSent;

        if (!serial.isInitialized()) {
            serial.initialize();
        }

        tweetSent = tweet.sendTweet(serial);

        return tweetSent;
    }
}
