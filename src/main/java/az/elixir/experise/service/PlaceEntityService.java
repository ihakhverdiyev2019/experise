package az.elixir.experise.service;

import az.elixir.experise.dto.PlaceView;
import az.elixir.experise.dto.ServiceView;
import az.elixir.experise.model.LanguageEntity;
import az.elixir.experise.model.PlaceEntity;
import az.elixir.experise.model.ServiceEntity;
import az.elixir.experise.model.ServiceLanguageEntity;
import az.elixir.experise.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlaceEntityService {

    @Autowired
    private PlaceRepository placeRepository;

    public List<PlaceView> findAllPlaces() {
        List<PlaceEntity> getAllDetails = placeRepository.findAll();
        List<PlaceView> result = new ArrayList<>();
        for (PlaceEntity placeEntity :
                getAllDetails) {
            PlaceView placeView = new PlaceView();
            placeView.setPlacePhoto(placeEntity.getPlacePhoto());
            result.add(placeView);
        }

        return result;
    }
}