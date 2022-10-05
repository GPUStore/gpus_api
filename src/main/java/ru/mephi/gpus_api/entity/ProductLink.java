package ru.mephi.gpus_api.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Data
@Entity
@Table(name = "product_link")
public class ProductLink {

    @Id
    @GeneratedValue(generator = "system_uuid")
    @GenericGenerator(name = "system_uuid", strategy = "uuid")
    @Column(name = "product_link_id", length = 32)
    private String id;

    @JoinColumn(name = "product_id", nullable = false)
    private String productId;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    public ProductLink setProductId(String productId) {
        this.productId = productId;
        return this;
    }

    public ProductLink setClient(Client client) {
        this.client = client;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductLink that = (ProductLink) o;
        return productId.equals(that.productId)
                && client.equals(that.client);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, client);
    }
}
