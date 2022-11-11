package ru.mephi.gpus_api.entity.products;

import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "category")
public class Category {

    @Id
    @Column(name = "category_id")
    @GeneratedValue(generator="system_uuid")
    @GenericGenerator(name="system_uuid", strategy = "uuid")
    private String category_id;

    @Column(name = "name")
    private String name;

    @ManyToMany(cascade =
            {
                    CascadeType.MERGE,
                    CascadeType.REFRESH,
                    CascadeType.PERSIST
            })
    @JoinTable(
            name = "products_categories",
            inverseJoinColumns = @JoinColumn(
                    name = "product_id", referencedColumnName = "product_id"),
            joinColumns  = @JoinColumn(
                    name = "category_id", referencedColumnName = "category_id")

    )
    private List<Product> products;

    public Category() {
        products = new ArrayList<>();
    }

    public Category(String name) {
        this.name = name;
        products = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return name.equals(category.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
