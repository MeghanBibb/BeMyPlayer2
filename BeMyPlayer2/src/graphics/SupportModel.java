package graphics;

import javax.swing.*;

public class SupportModel {

    private JButton btnSubmit;
    private JButton back;

    private JTextArea description;
    private JComboBox probArea;

    public JButton getBtnSubmit() {
        return btnSubmit;
    }

    public void setBtnSubmit(JButton btnSubmit) {
        this.btnSubmit = btnSubmit;
    }

    public JTextArea getDescription() {
        return description;
    }

    public void setDescription(JTextArea description) {
        this.description = description;
    }

    public JComboBox getProbArea() {
        return probArea;
    }

    public void setProbArea(JComboBox probArea) {
        this.probArea = probArea;
    }

    public JButton getBack() {
        return back;
    }

    public void setBack(JButton back) {
        this.back = back;
    }
}