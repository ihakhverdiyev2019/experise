package az.elixir.experise.dto;

public class ServiceView {

    private String servicePhoto;
    private String serviceName;
    private String serviceDescription;

    public ServiceView() {
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
}
