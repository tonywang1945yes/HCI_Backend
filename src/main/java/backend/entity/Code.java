package backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="code")
public class Code {
    @Id
    @Column(name="emailaddress")
    String emailAddress;
    @Column(name="code")
    String code;
    @Column(name="apple")
    int apple;

    public int getApple() {
        return apple;
    }

    public void setApple(int apple) {
        this.apple = apple;
    }

    public Code(String emailAddress, String code) {
        this.emailAddress = emailAddress;
        this.code = code;
        this.apple=0;

    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }



    public Code(){

    }
}
