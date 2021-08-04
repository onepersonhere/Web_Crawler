package crawler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExportPanel extends JPanel {
    public ExportPanel(){
        setLayout(new FlowLayout(FlowLayout.LEADING));
        add(new JLabel("Export: "));

        JTextField textField = new TextField();
        textField.setPreferredSize(new Dimension(350,25));
        textField.setName("ExportUrlTextField");
        add(textField);

        JButton saveButton = new JButton("Save");
        saveButton.setName("ExportButton");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String filePath = textField.getText();
                new Export(filePath);
            }
        });
        add(saveButton);
    }
}
