package az.elixir.experise.service.website;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import az.elixir.experise.dto.website.AllScholarshipView;
import az.elixir.experise.dto.website.ScholarshipView;
import az.elixir.experise.model.LanguageEntity;
import az.elixir.experise.model.ScholarshipEntity;
import az.elixir.experise.repository.LanguageRepository;
import az.elixir.experise.repository.ScholarshipRepository;

@Service
public class ScholarshipService {

  @Autowired private ScholarshipRepository repository;
  @Autowired private LanguageRepository languageRepository;

  public List<AllScholarshipView> findAll(String langCode) {
    LanguageEntity getLanguageByLangCode =
        languageRepository.findByLangCodeAndIsEnable(langCode, true);

    List<ScholarshipEntity> getAllDetails =
        repository.findAllByLangId(getLanguageByLangCode.getId());
    List<AllScholarshipView> result = new ArrayList<>();
    for (ScholarshipEntity entity : getAllDetails) {
      AllScholarshipView allScholarshipView = new AllScholarshipView();
      allScholarshipView.mapper(entity);
      result.add(allScholarshipView);
    }
    return result;
  }

  public ScholarshipView findById(int id, String langCode) {
    LanguageEntity getLanguageByLangCode =
        languageRepository.findByLangCodeAndIsEnable(langCode, true);

    ScholarshipEntity getAllDetails =
        repository.findByIdAndLangId(id, getLanguageByLangCode.getId());
    ScholarshipView scholarshipView = new ScholarshipView();
    scholarshipView.mapper(getAllDetails);
    return scholarshipView;
  }
}
