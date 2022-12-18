package az.elixir.experise.model;


import javax.persistence.*;

@Entity
@Table(name = "degrees_and_credentials_category")
public class DegreesAndCredentialsCategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    public DegreesAndCredentialsCategoryEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
