package az.elixir.experise.model;

import javax.persistence.*;

@Entity
@Table(name = "services")
public class ServiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String servicePhoto;

    public ServiceEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getServicePhoto() {
        return servicePhoto;
    }

    public void setServicePhoto(String servicePhoto) {
        this.servicePhoto = servicePhoto;
    }
}
