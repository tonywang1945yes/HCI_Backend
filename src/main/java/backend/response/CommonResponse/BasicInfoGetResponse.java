package backend.response.CommonResponse;

public class BasicInfoGetResponse {
    String sid;
    String description;
    double balance;
    String city;
    String district;
    String address;
    String username;

    public BasicInfoGetResponse(String sid, String description, double balance, String city, String district, String address, String username) {
        this.sid = sid;
        this.description = description;
        this.balance = balance;
        this.city = city;
        this.district = district;
        this.address = address;
        this.username = username;
    }

    public BasicInfoGetResponse() {
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
