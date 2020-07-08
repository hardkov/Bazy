import javax.persistence.*;
import java.util.Set;

@Entity
public class Supplier extends Company{
    private String bankAccountNumber;

    @OneToMany(mappedBy = "supplier")
    private Set<Product> productSet;

    public Supplier(){}

    public Supplier(String bankAccountNumber, String companyName, String street, String city, String zipCode){
        super(companyName, street, city, zipCode);
        this.bankAccountNumber = bankAccountNumber;
    }

    public Set<Product> getProductSet() {
        return productSet;
    }

    public void setProductSet(Set<Product> productSet) {
        this.productSet = productSet;
    }
}
