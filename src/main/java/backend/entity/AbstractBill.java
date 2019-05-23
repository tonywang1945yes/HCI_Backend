package backend.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="abstractbill")
public class AbstractBill {
    @Id
    @Column(name="bid")
    String bid="";
    @Column(name="sid")
    String sid="";
    @Column(name="uid")
    String uid=""; //用户邮箱
    @Column(name="actual_price")
    double actualPrice=0;
    @Column(name="total_price")
    double totalPrice=0;
    @Column(name="time")
    String time="";
    @Column(name="status")
    int status=0; //0,未付款；1，已付款【待配送】；2，已退款; 3,已取消；4，已配送【待结算】；5，已结算

    @Column(name="address")
    String address="";

    @Column(name="counter")
    int counter=0;

    public AbstractBill(String bid, String sid, String uid, double actualPrice, double totalPrice, String time, int status,String address) {
        this.bid = bid;
        this.sid = sid;
        this.uid = uid;
        this.actualPrice = actualPrice;
        this.totalPrice = totalPrice;
        this.time = time;
        this.status = status;
        this.address=address;
        this.counter=0;
    }

    public AbstractBill() {
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public double getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(double actualPrice) {
        this.actualPrice = actualPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}
