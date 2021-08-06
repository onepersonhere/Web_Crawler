package crawler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;

public class WebCrawler extends JFrame {
    static JTextField textField = new URLField();
    static JPanel mainPanel = new MainPanel();
    static JPanel export = new ExportPanel();

    public WebCrawler() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setSize(500, 270);
        setTitle("Simple Window");

        iniComp();
        setVisible(true);
    }

    private void iniComp(){
        JPanel UpperUpperPanel = new JPanel(new BorderLayout());
        JPanel upperPanel = new JPanel();

        JLabel URL = new JLabel("Start URL:             ");
        upperPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        upperPanel.add(URL);
        upperPanel.add(textField);
        upperPanel.add(GetButton());
        UpperUpperPanel.add(upperPanel, BorderLayout.NORTH);

        add(UpperUpperPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
        add(export, BorderLayout.SOUTH);
    }

    public static JTextField getTextField() {
        return textField;
    }

    Timer timer = null;
    int UPDATE_SPEED = 100;
    private static int clicks = 0;

    private JToggleButton GetButton(){
        JToggleButton button = new JToggleButton();
        button.setText("Parse");
        button.setName("RunButton");
        button.setFocusPainted(false);
        ActionListener refresh = e -> {
            try {
                new GetHTML();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            MainPanel.getPages().repaint();
            MainPanel.getTimeElapsed().setText(); //set timeElasped here
            repaint();
            revalidate();
            setVisible(true);
        };
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //workers specify here
                if(clicks % 2 == 0) {
                    timer = new Timer(UPDATE_SPEED, refresh);
                    timer.start();
                }else{
                    timer.stop();
                }
                clicks++;
            }
        });

        return button;
    }
}
//TODO: Configure max depth to breath first!!
//TODO: Implement elaspedTime with Swing Timer
//TODO: Compared ElaspedTime vs Time Limit
//TODO: Setup multithreading for workers at main