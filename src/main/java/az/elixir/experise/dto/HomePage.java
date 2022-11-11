package az.elixir.experise.dto;

import java.util.List;

public class HomePage {

    private List<ServiceView> serviceView;

    private List<PlaceView> placeView;

    private LanguageView languageView;

    public HomePage() {
    }

    public List<ServiceView> getServiceView() {
        return serviceView;
    }

    public void setServiceView(List<ServiceView> serviceView) {
        this.serviceView = serviceView;
    }

    public List<PlaceView> getPlaceView() {
        return placeView;
    }

    public void setPlaceView(List<PlaceView> placeView) {
        this.placeView = placeView;
    }

    public LanguageView getLanguageView() {
        return languageView;
    }

    public void setLanguageView(LanguageView languageView) {
        this.languageView = languageView;
    }
}
