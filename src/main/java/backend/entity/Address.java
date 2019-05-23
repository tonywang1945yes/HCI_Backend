package backend.entity;

import javax.persistence.*;

@Entity
@Table(name="address")
public class Address {
    @Id
    @Column(name="id")
    String id;

    @Column(name="uid")
    String uid;

    @Column(name="city")
    String city; //城市
    @Column(name="district")
    String district;//区

    @Column(name="address")
    String address;//详细地址

    public Address(String uid, String city, String district, String address) {
        this.uid = uid;
        this.city = city;
        this.district = district;
        this.address = address;
    }

    public Address() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
