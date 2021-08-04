package crawler;

import javax.swing.*;
import java.awt.*;

public class WebCrawler extends JFrame {
    static JTextField textField = new TextField();
    static JButton button = new GetButton();
    static JLabel titleLable = new TitleLabel();
    static JTable table = new TitlesTable();
    static JPanel export = new ExportPanel();

    public WebCrawler() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setSize(500, 500);
        setTitle("Simple Window");

        iniComp();
        setVisible(true);
    }

    private void iniComp(){
        JPanel UpperUpperPanel = new JPanel(new BorderLayout());
        JPanel upperPanel = new JPanel();
        JPanel lowerpanel = new JPanel();

        JLabel URL = new JLabel("URL: ");
        upperPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        upperPanel.add(URL);
        upperPanel.add(textField);
        upperPanel.add(button);
        UpperUpperPanel.add(upperPanel, BorderLayout.NORTH);

        JLabel Title = new JLabel("Title: ");
        lowerpanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        lowerpanel.add(Title);
        lowerpanel.add(titleLable);
        UpperUpperPanel.add(lowerpanel, BorderLayout.SOUTH);

        JScrollPane sPane = new JScrollPane(table);

        add(UpperUpperPanel, BorderLayout.NORTH);
        add(sPane, BorderLayout.CENTER);
        add(export, BorderLayout.SOUTH);
    }

    public static JTextField getTextField() {
        return textField;
    }

    public static JTable getTable() {
        return table;
    }

    public static JLabel getTitleLable(){
        return titleLable;
    }

}