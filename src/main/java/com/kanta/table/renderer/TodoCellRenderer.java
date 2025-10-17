package com.kanta.table.renderer;

import com.kanta.rowdata.RowData;
import com.kanta.rowdata.RowDataManager;
import com.kanta.setting.SettingManager;
import com.kanta.table.components.DaysLabel;
import com.kanta.table.components.folderbutton.CellIconLabel;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class TodoCellRenderer implements TableCellRenderer {

    private JPanel jp = null;


    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        CellIconLabel cell;
        RowData data = RowDataManager.getModel().getRowData(row);
        if (column == 0) {
            cell = new CellIconLabel(data.getSvgIcon(),new Color(data.getIconColor()[0],data.getIconColor()[1],data
                    .getIconColor()[2],data.getIconColor()[3]));

            cell.setText(data.getName());
            cell.setVisible(true);

            jp = new JPanel();
            jp.setLayout(new MigLayout("alignx left,aligny center"));
            jp.add(cell);

            if (isSelected) {
                jp.setBackground(table.getSelectionBackground());
            } else {
                jp.setBackground(table.getBackground());
            }


            return jp;
        }else if (column == 1){
            DaysLabel daysLabel = new DaysLabel();
            int interval = RowDataManager.getModel().getRowData(row).getInterval();
            daysLabel.setDays(interval);
            if ( interval <= SettingManager.getInstance().getBeforeDays()){
                daysLabel.getDays().setForeground(Color.red);
            }
            if (isSelected){
                daysLabel.setBackground(table.getSelectionBackground());
            }else {
                daysLabel.setBackground(table.getBackground());
            }


            return daysLabel;
        }

        return  jp;
    }
}
