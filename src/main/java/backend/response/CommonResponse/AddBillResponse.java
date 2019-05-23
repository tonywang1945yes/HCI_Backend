package backend.response.CommonResponse;

public class AddBillResponse {
    int status;
    String bid;

    public AddBillResponse(int status, String bid) {
        this.status = status;
        this.bid = bid;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }
}
