package backend.response.AdminResponse;

public class SellerGetResponse {
    String name;
    String emailAddress;
    String city;
    String district;
    String address;

    public SellerGetResponse() {
    }

    public SellerGetResponse(String name, String emailAddress, String city, String district, String address) {
        this.name = name;
        this.emailAddress = emailAddress;
        this.city = city;
        this.district = district;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
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
