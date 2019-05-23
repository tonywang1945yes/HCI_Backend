package backend.response.AdminResponse;

public class AdminPayAllResponse {
    double total=0;

    public AdminPayAllResponse() {
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public AdminPayAllResponse(double total) {
        this.total = total;
    }
}
