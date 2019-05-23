package backend.response.AdminResponse;

public class AdminStatisticsResponse {
    int userNum=0;
    int sellerNum=0;
    double totalPrice=0.0;

    public AdminStatisticsResponse() {
    }

    public int getUserNum() {
        return userNum;
    }

    public void setUserNum(int userNum) {
        this.userNum = userNum;
    }

    public int getSellerNum() {
        return sellerNum;
    }

    public void setSellerNum(int sellerNum) {
        this.sellerNum = sellerNum;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
