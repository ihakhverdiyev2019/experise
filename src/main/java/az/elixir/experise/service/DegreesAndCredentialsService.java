package az.elixir.experise.service;


import az.elixir.experise.dto.AllDegreesAndCredentialsView;
import az.elixir.experise.dto.DegreesAndCredentialsView;
import az.elixir.experise.model.DegreesAndCredentialsEntity;
import az.elixir.experise.repository.DegreesAndCredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DegreesAndCredentialsService {

    @Autowired
    private DegreesAndCredentialsRepository degreesAndCredentialsRepository;

    public DegreesAndCredentialsView findById(int id) {
        DegreesAndCredentialsEntity getAllDetails = degreesAndCredentialsRepository.findById(id).get();
        DegreesAndCredentialsView degreesAndCredentialsView = new DegreesAndCredentialsView();
        degreesAndCredentialsView.mapper(getAllDetails);
        return degreesAndCredentialsView;
    }

    public List<AllDegreesAndCredentialsView> findAll() {
        List<AllDegreesAndCredentialsView> result = new ArrayList<>();
        List<DegreesAndCredentialsEntity> getAll = degreesAndCredentialsRepository.findAll();
        for (DegreesAndCredentialsEntity degreesAndCredentialsEntity : getAll){
            AllDegreesAndCredentialsView allDegreesAndCredentialsView = new AllDegreesAndCredentialsView();
            allDegreesAndCredentialsView.mapper(degreesAndCredentialsEntity);
            result.add(allDegreesAndCredentialsView);
        }

        return result;
    }

}