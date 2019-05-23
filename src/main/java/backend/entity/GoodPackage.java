package backend.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;

@Entity
@Table(name="goodpackage")
public class GoodPackage {
    @Id
    @Column(name="id")
    String id;
    @Column(name="name")
    String name;
    @Column(name="price")
    double price;
    @Column(name="gids")
    ArrayList<String>gids =new ArrayList<>();
    @Column(name="gnums")
    ArrayList<Integer>gnums =new ArrayList<>();
    @Column(name="emailaddress")
    String emailAddress;

    public ArrayList<Integer> getGnums() {
        return gnums;
    }

    public void setGnums(ArrayList<Integer> gnums) {
        this.gnums = gnums;
    }



    public ArrayList<String> getGids() {
        return gids;
    }

    public void setGids(ArrayList<String> gids) {
        this.gids = gids;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public GoodPackage() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
