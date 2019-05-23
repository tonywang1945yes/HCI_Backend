package backend.parameter.goodParameter;

public class PayParameter {
    String uid;
    String bid;

    public PayParameter(String uid, String bid) {
        this.uid = uid;
        this.bid = bid;
    }

    public PayParameter() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }
}
