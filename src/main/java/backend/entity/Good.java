package backend.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="good")
public class Good {

    @Id
    @Column(name="gid")
    String gid;

    @Column(name="sid")
    String sid;

    @Column(name="name")
    String name;

    @Column(name="description")
    String description;

    @Column(name="number")
    int number;

    @Column(name="price")
    double price;

    public Good() {
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Good(String gid, String sid, String name, String description, int number, double price) {
        this.gid = gid;
        this.sid = sid;
        this.name = name;
        this.description = description;
        this.number = number;
        this.price = price;
    }
}
