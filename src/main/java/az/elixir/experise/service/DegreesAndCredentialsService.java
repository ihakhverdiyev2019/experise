package az.elixir.experise.service;


import az.elixir.experise.dto.AllDegreesAndCredentialsView;
import az.elixir.experise.dto.DegreesAndCredentialsView;
import az.elixir.experise.dto.PlaceView;
import az.elixir.experise.model.DegreesAndCredentialsEntity;
import az.elixir.experise.model.PlaceEntity;
import az.elixir.experise.repository.DegreesAndCredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DegreesAndCredentialsService {

    @Autowired
    private DegreesAndCredentialsRepository degreesAndCredentialsRepository;

    public List<AllDegreesAndCredentialsView> findAllByCategory(String category) {
        List<DegreesAndCredentialsEntity> getAllDetails = degreesAndCredentialsRepository.findAllByCategory(category);
        List<AllDegreesAndCredentialsView> result = new ArrayList<>();
        for (DegreesAndCredentialsEntity entity :
                getAllDetails) {
            AllDegreesAndCredentialsView allDegreesAndCredentialsView = new AllDegreesAndCredentialsView();
            allDegreesAndCredentialsView.mapper(entity);
            result.add(allDegreesAndCredentialsView);
        }
        return result;
    }

    public DegreesAndCredentialsView findById(int id) {
        DegreesAndCredentialsEntity getAllDetails = degreesAndCredentialsRepository.findById(id).get();
        DegreesAndCredentialsView degreesAndCredentialsView = new DegreesAndCredentialsView();
        degreesAndCredentialsView.mapper(getAllDetails);
        return degreesAndCredentialsView;
    }

}