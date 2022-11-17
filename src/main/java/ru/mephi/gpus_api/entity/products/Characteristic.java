package ru.mephi.gpus_api.entity.products;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@Data
@EqualsAndHashCode
@Entity
@Table(name = "characteristic")
public class Characteristic {
    @Id
    @Column(name = "characteristic_id")
    private String name;
    @OneToMany(mappedBy = "characteristic", fetch = FetchType.LAZY)
    private List<Parameter> parameters;

    public Characteristic setName(String name) {
        this.name = name;
        return this;
    }

    public Characteristic setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
        return this;
    }
}
