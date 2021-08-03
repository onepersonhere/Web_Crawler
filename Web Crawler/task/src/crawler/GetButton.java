package crawler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;

public class GetButton extends JButton {
    public GetButton(){
        setText("Get text!");
        setName("RunButton");
        setFocusPainted(false);
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //get html
                String url = WebCrawler.getTextField().getText();
                String html = "";
                try {
                    html = GetHTML.get(url);
                    WebCrawler.getTextArea().setText(html);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
