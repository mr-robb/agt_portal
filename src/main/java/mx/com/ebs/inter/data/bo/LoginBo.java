package mx.com.ebs.inter.data.bo;

/**
 * Created by robb on 27/04/2015.
 */
public class LoginBo {

    private String username;
    private String passwd;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public LoginBo(String username, String passwd) {
        this.username = username;
        this.passwd = passwd;
    }
}
