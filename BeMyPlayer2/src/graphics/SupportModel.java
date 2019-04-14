package graphics;

import javax.swing.*;

public class SupportModel {

    private JButton btnSubmit;
    private JButton back;

    private JTextArea description;

    private JComboBox<String> probArea;

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

    public JComboBox<String> getProbArea() {
        return probArea;
    }

    public void setProbArea(JComboBox<String> probArea) {
        this.probArea = probArea;
    }

    public JButton getBack() {
        return back;
    }

    public void setBack(JButton back) {
        this.back = back;
    }
}

