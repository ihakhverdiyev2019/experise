package az.elixir.experise.service;


import az.elixir.experise.dto.AllDegreesAndCredentialsView;
import az.elixir.experise.dto.AllResearchAndWritingsView;
import az.elixir.experise.dto.DegreesAndCredentialsView;
import az.elixir.experise.dto.ResearchAndWritingsView;
import az.elixir.experise.model.DegreesAndCredentialsEntity;
import az.elixir.experise.model.ResearchAndWritingsEntity;
import az.elixir.experise.repository.DegreesAndCredentialsRepository;
import az.elixir.experise.repository.ResearchAndWritingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResearchAndWritingsService {

    @Autowired
    private ResearchAndWritingsRepository repository;

    public List<AllResearchAndWritingsView> findAll() {
        List<ResearchAndWritingsEntity> getAllDetails = repository.findAll();
        List<AllResearchAndWritingsView> result = new ArrayList<>();
        for (ResearchAndWritingsEntity entity :
                getAllDetails) {
            AllResearchAndWritingsView allResearchAndWritingsView = new AllResearchAndWritingsView();
            allResearchAndWritingsView.mapper(entity);
            result.add(allResearchAndWritingsView);
        }
        return result;
    }

    public ResearchAndWritingsView findById(int id) {
        ResearchAndWritingsEntity getAllDetails = repository.findById(id).get();
        ResearchAndWritingsView researchAndWritingsView = new ResearchAndWritingsView();
        researchAndWritingsView.mapper(getAllDetails);
        return researchAndWritingsView;
    }

}