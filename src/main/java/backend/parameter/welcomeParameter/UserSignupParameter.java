package backend.parameter.welcomeParameter;

public class UserSignupParameter {
    String username;
    String password;
    String emailAddress;
    String code;//验证码
    String city; //城市
    String district;//区
    String address;//详细地址



    public int getType() {
        return type;
    }

    public UserSignupParameter() {
    }

    public void setType(int type) {
        this.type = type;
    }

    int type;//类型

    public UserSignupParameter(String username, String password, String emailAddress, String code, String city, String district, String address, String paycode, int type) {
        this.username = username;
        this.password = password;
        this.emailAddress = emailAddress;
        this.code = code;
        this.city = city;
        this.district = district;
        this.address = address;
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
}
