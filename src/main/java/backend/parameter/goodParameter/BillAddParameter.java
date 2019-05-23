package backend.parameter.goodParameter;

public class BillAddParameter {
    String userEmail="";
    String sid="";
    String address="";
    BillDto[] goods;

    public BillAddParameter() {
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public BillDto[] getGoods() {
        return goods;
    }

    public void setGoods(BillDto[] goods) {
        this.goods = goods;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
