package main.model;

import jdk.nashorn.internal.ir.annotations.Reference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "room_category")
public class RoomCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;


    @Reference
    @Enumerated(EnumType.STRING)
    @Column(name = "category",unique = true,nullable = false)
    private RoomCategoryName category;

    @OneToMany(mappedBy = "feature")
    private List<Feature> features = new ArrayList<Feature>();

    public RoomCategory() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RoomCategoryName getCategory() {
        return category;
    }

    public void setCategory(RoomCategoryName category) {
        this.category = category;
    }

    public List<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(List<Feature> features) {
        this.features = features;
    }
}
