import javax.persistence.Entity;

@Entity
public class Customer extends Company{
    private double discount;

    public Customer(){}

    public Customer(double discount, String companyName, String street, String city, String zipCode){
        super(companyName, street, city, zipCode);
        this.discount = discount;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
