import javax.persistence.*;
import java.util.List;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int categoryID;

    private String name;

    @OneToMany
    @JoinColumn(name = "CATEGORY_FK")
    private List<Product> productList;

    public Category(){}

    public Category(String name){
        this.name = name;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public String getName() {
        return name;
    }
}
