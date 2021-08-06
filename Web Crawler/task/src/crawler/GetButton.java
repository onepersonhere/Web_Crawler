package crawler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GetButton extends JToggleButton {
    public GetButton(){
        setText("Parse");
        setName("RunButton");
        setFocusPainted(false);

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = WebCrawler.getTextField().getText();
                try {
                    //workers specify here
                    GetHTML.get(url, 0);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
