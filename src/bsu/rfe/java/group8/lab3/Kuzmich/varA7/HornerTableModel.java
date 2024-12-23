package bsu.rfe.java.group8.lab3.Kuzmich.varA7;

import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
class HornerTableModel extends AbstractTableModel {
    private Double[] coefficients;
    private Double from;
    private Double to;
    private Double step;

    public HornerTableModel(Double from, Double to, Double step, Double[] coefficients) {
        this.from = from;
        this.to = to;
        this.step = step;
        this.coefficients = coefficients;
    }

    public Double getFrom() {
        return from;
    }

    public Double getTo() {
        return to;
    }

    public Double getStep() {
        return step;
    }

    @Override
    public int getColumnCount() {
        return 3; // Добавляем третий столбец
    }

    @Override
    public int getRowCount() {
        return new Double(Math.ceil((to - from) / step)).intValue() + 1;
    }

    @Override
    public Object getValueAt(int row, int col) {
        double x = from + step * row;
        return switch (col) {
            case 0 -> x;
            case 1 -> calculateHorner(x);
            case 2 -> isEvenIntegerPart(calculateHorner(x));
            default -> null;
        };
    }

    private boolean isEvenIntegerPart(double value) {
        int integerPart = (int) value;
        return integerPart % 2 == 0;
    }

    @Override
    public String getColumnName(int col) {
        return switch (col) {
            case 0 -> "Значение X";
            case 1 -> "Значение многочлена";
            case 2 -> "Целая часть чѐтная";
            default -> "";
        };
    }

    @Override
    public Class<?> getColumnClass(int col) {
        return switch (col) {
            case 2 -> Boolean.class;
            case 0, 1 -> Double.class;
            default -> Object.class;
        };
    }

    private double calculateHorner(double x) {
        double result = coefficients[coefficients.length - 1];
        for (int i = coefficients.length - 2; i >= 0; i--) {
            result = result * x + coefficients[i];
        }
        return result;
    }
}
