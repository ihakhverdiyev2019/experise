package az.elixir.experise.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import az.elixir.experise.dto.PlaceView;
import az.elixir.experise.model.PlaceEntity;
import az.elixir.experise.repository.LanguageRepository;
import az.elixir.experise.repository.PlaceRepository;

@Service
public class PlaceEntityService {

  @Autowired private PlaceRepository placeRepository;
  @Autowired private LanguageRepository languageRepository;

  public List<PlaceView> findAllPlaces() {
    List<PlaceEntity> getAllDetails = placeRepository.findAll();
    List<PlaceView> result = new ArrayList<>();
    for (PlaceEntity placeEntity : getAllDetails) {
      PlaceView placeView = new PlaceView();
      placeView.setPlacePhoto(placeEntity.getPlacePhoto());
      result.add(placeView);
    }

    return result;
  }
}
