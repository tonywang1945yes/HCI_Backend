package backend.response.CommonResponse;

public class UserInfoGetResponse {
    double balance;
    String city;
    String district;
    String address;
    String username;
    String level;
    public UserInfoGetResponse(double balance, String city, String district, String address, String username) {
        this.balance = balance;
        this.city = city;
        this.district = district;
        this.address = address;
        this.username = username;
    }

    public UserInfoGetResponse() {
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

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
