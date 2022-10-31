package ru.mephi.gpus_api.entity.products;

import lombok.Data;
import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Data
@Getter
@Entity
@Table(name = "store")
public class Store {
    @Id
    @GeneratedValue(generator = "system_uuid")
    @GenericGenerator(name = "system_uuid", strategy = "uuid")
    @Column(name = "store_id", length = 32)
    private String id;
    @Column(name = "name")
    private String name;
    @Column(name = "url")
    private String url;
    @Column(name = "cost")
    private Double cost;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Store setId(String id) {
        this.id = id;
        return this;
    }

    public Store setName(String name) {
        this.name = name;
        return this;
    }

    public Store setUrl(String url) {
        this.url = url;
        return this;
    }

    public Store setCost(Double cost) {
        this.cost = cost;
        return this;
    }

    public Store setProduct(Product product) {
        this.product = product;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Store store = (Store) o;
        return name.equals(store.name)
                && url.equals(store.url)
                && cost.equals(store.cost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, url, cost);
    }
}
