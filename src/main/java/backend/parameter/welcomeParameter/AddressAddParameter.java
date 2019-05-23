package backend.parameter.welcomeParameter;

public class AddressAddParameter {
    String uid;
    String city; //城市
    String district;//区
    String address;//详细地址

    public AddressAddParameter(String uid, String city, String district, String address) {
        this.uid = uid;
        this.city = city;
        this.district = district;
        this.address = address;
    }

    public AddressAddParameter() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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
