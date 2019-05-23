package backend.parameter.goodParameter;

public class GoodParameter {
    String emailAddress;
    String name;
    String description;
    int number;
    double price;

    public GoodParameter() {
    }

    public GoodParameter(String emailAddress, String name, String description, int number, double price) {
        this.emailAddress = emailAddress;
        this.name = name;
        this.description = description;
        this.number = number;
        this.price = price;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
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
}
