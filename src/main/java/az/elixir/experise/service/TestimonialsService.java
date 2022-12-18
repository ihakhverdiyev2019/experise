package az.elixir.experise.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import az.elixir.experise.dto.TestimonialsView;
import az.elixir.experise.model.LanguageEntity;
import az.elixir.experise.model.TestimonialsEntity;
import az.elixir.experise.repository.LanguageRepository;
import az.elixir.experise.repository.TestimonialsRepository;

@Service
public class TestimonialsService {

  @Autowired private TestimonialsRepository testimonialsRepository;

  @Autowired private LanguageRepository languageRepository;

  public List<TestimonialsView> findAll(String langCode) {
    LanguageEntity getLanguageByLangCode =
        languageRepository.findByLangCodeAndIsEnable(langCode, true);

    List<TestimonialsEntity> getAllDetails =
        testimonialsRepository.findAllByLangId(getLanguageByLangCode.getId());
    List<TestimonialsView> result = new ArrayList<>();
    for (TestimonialsEntity entity : getAllDetails) {
      TestimonialsView testimonialsView = new TestimonialsView();
      testimonialsView.mapper(entity);
      result.add(testimonialsView);
    }
    return result;
  }
}
