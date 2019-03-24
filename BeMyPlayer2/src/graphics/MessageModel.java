package graphics;

import javax.swing.*;

public class MessageModel {
    private JButton btnSend;
    private JButton back;

    private String imagePath;

    private JLabel profileImage;
    private JLabel lblUsername;
    private JLabel lblAge;
    private JLabel lblGender;

    private JTextField sendBox;
    private JTextArea thread;

    public JTextArea getThread() {
        return thread;
    }

    public void setThread(JTextArea thread) {
        this.thread = thread;
    }

    public JButton getBack() {
        return back;
    }

    public void setBack(JButton back) {
        this.back = back;
    }

    public JButton getBtnSend() {
        return btnSend;
    }

    public void setBtnSend(JButton btnSend) {
        this.btnSend = btnSend;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public JLabel getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(JLabel profileImage) {
        this.profileImage = profileImage;
    }

    public JLabel getLblUsername() {
        return lblUsername;
    }

    public void setLblUsername(JLabel lblUsername) {
        this.lblUsername = lblUsername;
    }

    public JLabel getLblAge() {
        return lblAge;
    }

    public void setLblAge(JLabel lblAge) {
        this.lblAge = lblAge;
    }

    public JLabel getLblGender() {
        return lblGender;
    }

    public void setLblGender(JLabel lblGender) {
        this.lblGender = lblGender;
    }

    public JTextField getSendBox() {
        return sendBox;
    }

    public void setSendBox(JTextField sendBox) {
        this.sendBox = sendBox;
    }
}

