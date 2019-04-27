package support;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextArea;

/**
 * The Class SupportModel.
 */
public class SupportModel {

    /** The btn submit. */
    private JButton btnSubmit;
    
    /** The back. */
    private JButton back;

    /** The description. */
    private JTextArea description;

    /** The prob area. */
    private JComboBox<String> probArea;

    /**
     * Gets the btn submit.
     *
     * @return the btn submit
     */
    public JButton getBtnSubmit() {
        return btnSubmit;
    }

    /**
     * Sets the btn submit.
     *
     * @param btnSubmit the new btn submit
     */
    public void setBtnSubmit(JButton btnSubmit) {
        this.btnSubmit = btnSubmit;
    }

    /**
     * Gets the description.
     *
     * @return the description
     */
    public JTextArea getDescription() {
        return description;
    }

    /**
     * Sets the description.
     *
     * @param description the new description
     */
    public void setDescription(JTextArea description) {

        this.description = description;
    }

    /**
     * Gets the prob area.
     *
     * @return the prob area
     */
    public JComboBox<String> getProbArea() {
        return probArea;
    }

    /**
     * Sets the prob area.
     *
     * @param probArea the new prob area
     */
    public void setProbArea(JComboBox<String> probArea) {
        this.probArea = probArea;
    }

    /**
     * Gets the back.
     *
     * @return the back
     */
    public JButton getBack() {
        return back;
    }

    /**
     * Sets the back.
     *
     * @param back the new back
     */
    public void setBack(JButton back) {
        this.back = back;
    }
}

