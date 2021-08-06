package crawler;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    JPanel workersPanel = new JPanel(new BorderLayout());
    JPanel statsPanel = new JPanel(new BorderLayout());

    static JTextField numOfWorkers = new JTextField();

    static JTextField maxDepth = new JTextField();
    static JCheckBox limitDepthBox = new JCheckBox("Enabled");

    static JTextField timeLimit = new JTextField();
    static JCheckBox limitTimeBox = new JCheckBox("Enabled");

    static JLabel timeElapsed = new JLabel("0:00");
    static JLabel pages = new JLabel("0");

    public MainPanel(){
        setLayout(new BorderLayout());
        workersPanel();
        statsPanel();
        add(workersPanel, BorderLayout.NORTH);
        add(statsPanel, BorderLayout.SOUTH);
    }

    private void workersPanel(){
        JPanel workers = new JPanel(new FlowLayout(FlowLayout.LEADING));
        workers.add(new JLabel("Workers:              "));

        numOfWorkers.setPreferredSize(new Dimension(375, 25));
        workers.add(numOfWorkers);

        workersPanel.add(workers, BorderLayout.NORTH);



        JPanel depth = new JPanel(new FlowLayout(FlowLayout.LEADING));
        depth.add(new JLabel("Maximum depth: "));

        maxDepth.setName("DepthTextField");
        maxDepth.setPreferredSize(new Dimension(300, 25));
        depth.add(maxDepth);

        limitDepthBox.setFocusPainted(false);
        limitDepthBox.setName("DepthCheckBox");
        depth.add(limitDepthBox);

        workersPanel.add(depth, BorderLayout.CENTER);



        JPanel time = new JPanel(new FlowLayout(FlowLayout.LEADING));
        time.add(new JLabel("Time limit:             "));

        timeLimit.setPreferredSize(new Dimension(245, 25));
        time.add(timeLimit);
        time.add(new JLabel("seconds"));

        limitTimeBox.setFocusPainted(false);
        time.add(limitTimeBox);

        workersPanel.add(time, BorderLayout.SOUTH);
    }

    private void statsPanel(){
        JPanel elaspedTime = new JPanel(new FlowLayout(FlowLayout.LEADING));
        elaspedTime.add(new JLabel("Elasped time: "));

        elaspedTime.add(timeElapsed);

        JPanel parsedPages = new JPanel(new FlowLayout(FlowLayout.LEADING));
        parsedPages.add(new JLabel("Parsed pages: "));

        pages.setName("ParsedLabel");
        parsedPages.add(pages);

        statsPanel.add(elaspedTime, BorderLayout.NORTH);
        statsPanel.add(parsedPages, BorderLayout.SOUTH);
    }

    public static JTextField getNumOfWorkers() {
        return numOfWorkers;
    }

    public static JTextField getMaxDepth() {
        return maxDepth;
    }

    public static JCheckBox getLimitDepthBox() {
        return limitDepthBox;
    }

    public static JTextField getTimeLimit() {
        return timeLimit;
    }

    public static JCheckBox getLimitTimeBox() {
        return limitTimeBox;
    }

    public static JLabel getTimeElapsed() {
        return timeElapsed;
    }

    public static JLabel getPages() {
        return pages;
    }
}
