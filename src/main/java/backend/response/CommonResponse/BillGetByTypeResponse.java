package backend.response.CommonResponse;

public class BillGetByTypeResponse {
    String bid;
    String sid;
    double actualPrice;
    double totalPrice;
    String time;

    public BillGetByTypeResponse(String bid, String sid, double actualPrice, double totalPrice, String time) {
        this.bid = bid;
        this.sid = sid;
        this.actualPrice = actualPrice;
        this.totalPrice = totalPrice;
        this.time = time;
    }

    public BillGetByTypeResponse() {
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
