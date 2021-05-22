package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity

public class Tom extends Model {
    public String name;
    public float temperature;

    public Tom(String name, float temperature) {
        this.name = name;
        this.temperature = temperature;
    }
}
