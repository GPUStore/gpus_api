package ru.mephi.gpus_api.entity.products;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@EqualsAndHashCode
@Setter
@Getter
@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@Table(name = "parameter")
public class Parameter {

    @Id
    @GeneratedValue(generator="system_uuid")
    @GenericGenerator(name="system_uuid", strategy = "uuid")
    @Column(name = "parameter_id", length = 32)
    private String id;
    @Column(name = "name")
    private String name;
    @Column(name = "value", length = 512)
    private String value;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "characteristic_id", nullable = false)
    private Characteristic characteristic;

    public Parameter setName(String name) {
        this.name = name;
        return this;
    }

    public Parameter setValue(String value) {
        this.value = value;
        return this;
    }

    public Parameter setProduct(Product product) {
        this.product = product;
        return this;
    }

    public Parameter setCharacteristic(Characteristic characteristic) {
        this.characteristic = characteristic;
        return this;
    }
}
