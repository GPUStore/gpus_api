package ru.mephi.gpus_api.entity.clients;

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

    @JoinColumn(name = "email", nullable = false)
    private String email;

    @JoinColumn(name = "nickname", nullable = false)
    private String nickname;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<ProductLink> productIds;

    public Client setEmail(String email) {
        this.email = email;
        return this;
    }

    public Client setProductIds(List<ProductLink> productIds) {
        this.productIds = productIds;
        return this;
    }
}
