package mx.com.ebs.inter.data.bo;

/**
 * Created by robb on 11/06/2015.
 */
public class ChangePasswordBo {

    private String currentPassword;
    private String newPassword;
    private String confirmationNewPassword;

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmationNewPassword() {
        return confirmationNewPassword;
    }

    public void setConfirmationNewPassword(String confirmationNewPassword) {
        this.confirmationNewPassword = confirmationNewPassword;
    }
}
