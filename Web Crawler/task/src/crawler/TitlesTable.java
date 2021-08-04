package crawler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

public class TitlesTable extends JTable {
    public TitlesTable(){
        setName("TitlesTable");
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("URL");
        model.addColumn("Title");
        setModel(model);
        setEnabled(false);
    }
}
