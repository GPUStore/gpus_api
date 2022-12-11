package ru.mephi.gpus_api.entity.products;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
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
