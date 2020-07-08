import javax.persistence.*;
import java.util.Set;

@Entity
public class Product implements Comparable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int dbID;

    private String productName;
    private int unitsOnStock;

    @ManyToOne
    @JoinColumn(name="SUPPLIER_FK")
    private Supplier supplier;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private Set<Invoice> invoiceSet;

    public Product(){}

    public Product(String productName, int unitsOnStock){
        this.productName = productName;
        this.unitsOnStock = unitsOnStock;
    }

    public String getProductName() {
        return this.productName;
    }

    public int getUnitsOnStock() {
        return this.unitsOnStock;
    }

    public void setUnitsOnStock(int unitsOnStock) {
        this.unitsOnStock = unitsOnStock;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getDbID() {
        return dbID;
    }

    @Override
    public int compareTo(Object o) {
        if(o instanceof Product){
            return ((Product) o).getProductName().compareTo(this.productName);
        }

        return -1;
    }


    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public Set<Invoice> getInvoiceSet() {
        return invoiceSet;
    }

    public void setInvoiceSet(Set<Invoice> invoiceSet) {
        this.invoiceSet = invoiceSet;
    }
}
