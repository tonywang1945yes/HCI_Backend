package backend.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="seller")
public class Seller {
    @Id
    @Column(name="sid")
    String sid="";

    @Column(name="emailaddress")
    String emailAddress="";

    @Column(name="discription")
    String discription="";

    @Column(name="balance")
    double balance=0;

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Seller() {
    }

    public Seller(String emailAddress) {
        this.emailAddress = emailAddress;
        this.discription="这位商家很懒，什么都没有说";
        int id=(int)(Math.random()*900000+100000);
        this.sid="s"+Integer.toString(id);
        this.balance=0;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }
}

