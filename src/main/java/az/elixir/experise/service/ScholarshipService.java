package az.elixir.experise.service;

import az.elixir.experise.dto.AllResearchAndWritingsView;
import az.elixir.experise.dto.AllScholarshipView;
import az.elixir.experise.dto.ResearchAndWritingsView;
import az.elixir.experise.dto.ScholarshipView;
import az.elixir.experise.model.ResearchAndWritingsEntity;
import az.elixir.experise.model.ScholarshipEntity;
import az.elixir.experise.repository.ResearchAndWritingsRepository;
import az.elixir.experise.repository.ScholarshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScholarshipService {

    @Autowired
    private ScholarshipRepository repository;

    public List<AllScholarshipView> findAll() {
        List<ScholarshipEntity> getAllDetails = repository.findAll();
        List<AllScholarshipView> result = new ArrayList<>();
        for (ScholarshipEntity entity :
                getAllDetails) {
            AllScholarshipView allScholarshipView = new AllScholarshipView();
            allScholarshipView.mapper(entity);
            result.add(allScholarshipView);
        }
        return result;
    }

    public ScholarshipView findById(int id) {
        ScholarshipEntity getAllDetails = repository.findById(id).get();
        ScholarshipView scholarshipView = new ScholarshipView();
        scholarshipView.mapper(getAllDetails);
        return scholarshipView;
    }
}
