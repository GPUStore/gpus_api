package ru.mephi.gpus_api.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Data
@EqualsAndHashCode
@Entity
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(generator = "system_uuid")
    @GenericGenerator(name = "system_uuid", strategy = "uuid")
    @Column(name = "client_id", length = 32)
    private String id;

    @JoinColumn(name = "product_id", nullable = false)
    private String email;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<ProductLink> productIds;

    public Client setEmail(String email) {
        this.email = email;
        return this;
    }

    public Client setProductIds(List<ProductLink> product_ids) {
        this.productIds = product_ids;
        return this;
    }
}
