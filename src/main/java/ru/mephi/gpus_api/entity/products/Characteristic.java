package ru.mephi.gpus_api.entity.products;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode
@JsonIdentityInfo(
  generator = ObjectIdGenerators.PropertyGenerator.class,
  property = "name")
@Getter
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
