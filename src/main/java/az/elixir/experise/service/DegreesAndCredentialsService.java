package az.elixir.experise.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import az.elixir.experise.dto.AllDegreesAndCredentialsView;
import az.elixir.experise.dto.DegreesAndCredentialsView;
import az.elixir.experise.model.DegreesAndCredentialsEntity;
import az.elixir.experise.model.LanguageEntity;
import az.elixir.experise.repository.DegreesAndCredentialsRepository;
import az.elixir.experise.repository.LanguageRepository;

@Service
public class DegreesAndCredentialsService {

  @Autowired private DegreesAndCredentialsRepository degreesAndCredentialsRepository;
  @Autowired private LanguageRepository languageRepository;

  public DegreesAndCredentialsView findById(int id, String langCode) {
    LanguageEntity getLanguageByLangCode =
        languageRepository.findByLangCodeAndIsEnable(langCode, true);

    DegreesAndCredentialsEntity getAllDetails =
        degreesAndCredentialsRepository.findByIdAndLangId(id, getLanguageByLangCode.getId());
    DegreesAndCredentialsView degreesAndCredentialsView = new DegreesAndCredentialsView();
    degreesAndCredentialsView.mapper(getAllDetails);
    return degreesAndCredentialsView;
  }

  public List<AllDegreesAndCredentialsView> findAll(String langCode) {
    LanguageEntity getLanguageByLangCode =
        languageRepository.findByLangCodeAndIsEnable(langCode, true);

    List<AllDegreesAndCredentialsView> result = new ArrayList<>();
    List<DegreesAndCredentialsEntity> getAll =
        degreesAndCredentialsRepository.findAllByLangId(getLanguageByLangCode.getId());
    for (DegreesAndCredentialsEntity degreesAndCredentialsEntity : getAll) {
      AllDegreesAndCredentialsView allDegreesAndCredentialsView =
          new AllDegreesAndCredentialsView();
      allDegreesAndCredentialsView.mapper(degreesAndCredentialsEntity);
      result.add(allDegreesAndCredentialsView);
    }

    return result;
  }
}
