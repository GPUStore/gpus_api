package ru.mephi.gpus_api.entity.products;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


@EqualsAndHashCode
@Getter
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")

@NamedEntityGraph(
        name = "stores-categories",
        attributeNodes = {
                @NamedAttributeNode(value = "stores"),
                @NamedAttributeNode(value ="categories"),
        }
)
@NamedEntityGraph(
        name = "parameters-with-characteristics",
        attributeNodes = {
                @NamedAttributeNode(value = "parameters", subgraph = "characteristics-subgraph"),
        },
        subgraphs = {
                @NamedSubgraph(
                        name = "characteristics-subgraph",
                        attributeNodes = {
                                @NamedAttributeNode("characteristic")
                        }
                )
        }
)
@Entity
@Indexed
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(generator = "system_uuid")
    @GenericGenerator(name = "system_uuid", strategy = "uuid")
    @Column(name = "product_id", length = 32)
    private String id;
    @Column(name = "name")
    @Field
    private String name;
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Store> stores;
    @Column(name = "country")
    @Field
    private String country;
    @Column(name = "weight")
    private double weight;
    @Column(name = "weight_with_box")
    private double weightWithBox;
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Parameter> parameters;
    @Enumerated(EnumType.STRING)
    private Type type;
    @ManyToMany(cascade =
            {
                    CascadeType.MERGE,
                    CascadeType.REFRESH,
                    CascadeType.PERSIST
            })
    @JoinTable(
            name = "products_categories",
            inverseJoinColumns = @JoinColumn(
                    name = "category_id", referencedColumnName = "category_id"),
            joinColumns = @JoinColumn(
                    name = "product_id", referencedColumnName = "product_id")

    )
    private Set<Category> categories;

    public Product setStores(List<Store> store) {
        this.stores = store;
        return this;
    }

    public Product setCategories(Set<Category> categorySet) {
        this.categories = categorySet;
        return this;
    }

    public Product setName(String name) {
        this.name = name;
        return this;
    }

    public Product setCountry(String country) {
        this.country = country;
        return this;
    }

    public Product setWeight(double weight) {
        this.weight = weight;
        return this;
    }

    public Product setWeightWithBox(double weightWithBox) {
        this.weightWithBox = weightWithBox;
        return this;
    }

    public Product setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
        return this;
    }

    public Product setType(Type type) {
        this.type = type;
        return this;
    }
}
