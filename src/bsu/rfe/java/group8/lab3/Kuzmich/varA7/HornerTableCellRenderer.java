package bsu.rfe.java.group8.lab3.Kuzmich.varA7;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;

public class HornerTableCellRenderer implements TableCellRenderer {
    private JPanel panel;
    private JLabel label;
    private JCheckBox checkBox;

    private String needle = null;

    private DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance();

    public HornerTableCellRenderer() {
        formatter.setMaximumFractionDigits(5);
        formatter.setGroupingUsed(false);

        DecimalFormatSymbols dottedDouble = formatter.getDecimalFormatSymbols();
        dottedDouble.setDecimalSeparator('.');
        formatter.setDecimalFormatSymbols(dottedDouble);

        panel = new JPanel();
        label = new JLabel();
        checkBox = new JCheckBox();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.add(label);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
        if (col == 2) {
            // Для третьего столбца отображаем флажок
            panel.remove(label);
            checkBox.setSelected((Boolean) value);
            panel.add(checkBox);
            return panel;
        } else {
            // Для других столбцов отображаем форматированные числа
            String formattedDouble = formatter.format(value);
            label.setText(formattedDouble);

            panel.remove(checkBox);
            panel.add(label);

            panel.setBackground(Color.WHITE);
            label.setForeground(Color.BLACK);

            if (needle != null && needle.equals(formattedDouble)) {
                panel.setBackground(Color.RED);
            }
            return panel;
        }
    }

    public void setNeedle(String needle) {
        this.needle = needle;
    }
}
