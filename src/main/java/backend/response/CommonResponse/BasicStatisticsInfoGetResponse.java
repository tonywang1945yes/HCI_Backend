package backend.response.CommonResponse;

public class BasicStatisticsInfoGetResponse {
    int bookNum;
    int unBookNum;
    double totalPrice;

    public BasicStatisticsInfoGetResponse(int bookNum, int unBookNum, double totalPrice) {
        this.bookNum = bookNum;
        this.unBookNum = unBookNum;
        this.totalPrice = totalPrice;
    }

    public BasicStatisticsInfoGetResponse() {
    }

    public int getBookNum() {
        return bookNum;
    }

    public void setBookNum(int bookNum) {
        this.bookNum = bookNum;
    }

    public int getUnBookNum() {
        return unBookNum;
    }

    public void setUnBookNum(int unBookNum) {
        this.unBookNum = unBookNum;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
