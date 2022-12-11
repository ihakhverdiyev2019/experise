package az.elixir.experise.model;

import javax.persistence.*;

@Entity
@Table(name = "services")
public class ServiceEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String servicePhoto;

  private String serviceName;

  private String serviceDescription;

  private int langId;

  public ServiceEntity() {}

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

  public String getServiceName() {
    return serviceName;
  }

  public void setServiceName(String serviceName) {
    this.serviceName = serviceName;
  }

  public String getServiceDescription() {
    return serviceDescription;
  }

  public void setServiceDescription(String serviceDescription) {
    this.serviceDescription = serviceDescription;
  }

  public int getLangId() {
    return langId;
  }

  public void setLangId(int langId) {
    this.langId = langId;
  }
}
