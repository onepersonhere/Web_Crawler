package crawler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GetButton extends JButton {
    public GetButton(){
        setText("Parse");
        setName("RunButton");
        setFocusPainted(false);
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //get html
                DefaultTableModel dtm = (DefaultTableModel) WebCrawler.getTable().getModel();
                dtm.setRowCount(0);

                String url = WebCrawler.getTextField().getText();
                try {
                    GetHTML.get(url, 0);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
