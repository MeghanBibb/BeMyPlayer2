package graphics;

import javax.swing.*;

// TODO: Auto-generated Javadoc
/**
 * The Class MessageModel.
 */
public class MessageModel {
    
    /** The btn send. */
    private JButton btnSend;
    
    /** The back. */
    private JButton back;

    /** The image path. */
    private String imagePath;

    /** The profile image. */
    private JLabel profileImage;
    
    /** The lbl username. */
    private JLabel lblUsername;
    
    /** The lbl age. */
    private JLabel lblAge;
    
    /** The lbl gender. */
    private JLabel lblGender;

    /** The send box. */
    private JTextField sendBox;
    
    /** The thread. */
    private JTextArea thread;

    /**
     * Gets the thread.
     *
     * @return the thread
     */
    public JTextArea getThread() {
        return thread;
    }

    /**
     * Sets the thread.
     *
     * @param thread the new thread
     */
    public void setThread(JTextArea thread) {
        this.thread = thread;
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

    /**
     * Gets the btn send.
     *
     * @return the btn send
     */
    public JButton getBtnSend() {
        return btnSend;
    }

    /**
     * Sets the btn send.
     *
     * @param btnSend the new btn send
     */
    public void setBtnSend(JButton btnSend) {
        this.btnSend = btnSend;
    }

    /**
     * Gets the image path.
     *
     * @return the image path
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * Sets the image path.
     *
     * @param imagePath the new image path
     */
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    /**
     * Gets the profile image.
     *
     * @return the profile image
     */
    public JLabel getProfileImage() {
        return profileImage;
    }

    /**
     * Sets the profile image.
     *
     * @param profileImage the new profile image
     */
    public void setProfileImage(JLabel profileImage) {
        this.profileImage = profileImage;
    }

    /**
     * Gets the lbl username.
     *
     * @return the lbl username
     */
    public JLabel getLblUsername() {
        return lblUsername;
    }

    /**
     * Sets the lbl username.
     *
     * @param lblUsername the new lbl username
     */
    public void setLblUsername(JLabel lblUsername) {
        this.lblUsername = lblUsername;
    }

    /**
     * Gets the lbl age.
     *
     * @return the lbl age
     */
    public JLabel getLblAge() {
        return lblAge;
    }

    /**
     * Sets the lbl age.
     *
     * @param lblAge the new lbl age
     */
    public void setLblAge(JLabel lblAge) {
        this.lblAge = lblAge;
    }

    /**
     * Gets the lbl gender.
     *
     * @return the lbl gender
     */
    public JLabel getLblGender() {
        return lblGender;
    }

    /**
     * Sets the lbl gender.
     *
     * @param lblGender the new lbl gender
     */
    public void setLblGender(JLabel lblGender) {
        this.lblGender = lblGender;
    }

    /**
     * Gets the send box.
     *
     * @return the send box
     */
    public JTextField getSendBox() {
        return sendBox;
    }

    /**
     * Sets the send box.
     *
     * @param sendBox the new send box
     */
    public void setSendBox(JTextField sendBox) {
        this.sendBox = sendBox;
    }
}

