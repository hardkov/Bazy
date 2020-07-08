import javax.persistence.*;
import java.util.Set;

@Entity
public class Invoice implements Comparable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int dbID;

    private int invoiceNumber;
    private int quantity;

    @ManyToMany(mappedBy = "invoiceSet", cascade = CascadeType.PERSIST)
    private Set<Product> productSet;

    public Invoice(){}

    public Invoice(int invoiceNumber, int quantity){
        this.invoiceNumber = invoiceNumber;
        this.quantity = quantity;
    }

    public int getDbID() {
        return dbID;
    }

    public int getInvoiceNumber() {
        return invoiceNumber;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setInvoiceNumber(int invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Set<Product> getProductSet() {
        return productSet;
    }

    public void setProductSet(Set<Product> productSet) {
        this.productSet = productSet;
    }

    public int compareTo(Object o){
        if(o instanceof Invoice){
            return Integer.compare(((Invoice) o).invoiceNumber, this.invoiceNumber);
        }

        return -1;
    }
}
