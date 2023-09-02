package az.elixir.experise.service.website;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import az.elixir.experise.dto.website.AllDegreesAndCredentialsView;
import az.elixir.experise.dto.website.DegreesAndCredentialsView;
import az.elixir.experise.dto.website.SeoDto;
import az.elixir.experise.model.DegreesAndCredentialsEntity;
import az.elixir.experise.model.LanguageEntity;
import az.elixir.experise.repository.DegreesAndCredentialsRepository;
import az.elixir.experise.repository.LanguageRepository;

@Service
public class DegreesAndCredentialsService {

  @Autowired private DegreesAndCredentialsRepository degreesAndCredentialsRepository;
  @Autowired private LanguageRepository languageRepository;

  public List<AllDegreesAndCredentialsView> findAllByCategory(String category, String langCode) {
    LanguageEntity getLanguageByLangCode =
        languageRepository.findByLangCodeAndIsEnable(langCode, true);
    List<DegreesAndCredentialsEntity> getAllDetails =
        degreesAndCredentialsRepository.findAllByCategoryAndLangId(
            category, getLanguageByLangCode.getId());
    List<AllDegreesAndCredentialsView> result = new ArrayList<>();
    for (DegreesAndCredentialsEntity entity : getAllDetails) {
      AllDegreesAndCredentialsView allDegreesAndCredentialsView =
          new AllDegreesAndCredentialsView();
      allDegreesAndCredentialsView.mapper(entity);
      result.add(allDegreesAndCredentialsView);
    }
    return result;
  }

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

  public List<AllDegreesAndCredentialsView> isFooter(String langCode) {
    LanguageEntity getLanguageByLangCode =
        languageRepository.findByLangCodeAndIsEnable(langCode, true);

    List<AllDegreesAndCredentialsView> result = new ArrayList<>();
    List<DegreesAndCredentialsEntity> getAll =
        degreesAndCredentialsRepository.findAllByLangIdAndIsFooter(
            getLanguageByLangCode.getId(), true);
    for (DegreesAndCredentialsEntity degreesAndCredentialsEntity : getAll) {
      AllDegreesAndCredentialsView allDegreesAndCredentialsView =
          new AllDegreesAndCredentialsView();
      allDegreesAndCredentialsView.mapper(degreesAndCredentialsEntity);
      result.add(allDegreesAndCredentialsView);
    }

    return result;
  }

  public SeoDto seoDetails(String langCode) {
    LanguageEntity getLanguageByLangCode =
        languageRepository.findByLangCodeAndIsEnable(langCode, true);
    //    FAQSeoEntity faqSeoEntity = faqSeoRepository.findByLangId(getLanguageByLangCode.getId());
    SeoDto seoDto = new SeoDto();
    if (false) {
      //      seoDto.setTitle(faqSeoEntity.getTitle());
      //      seoDto.setKey(faqSeoEntity.getKey());
      //      seoDto.setDescription(faqSeoEntity.getDescription());
    } else {
      seoDto.setTitle("Elixir LLC.");
      seoDto.setKey("Elixir LLC.");
      seoDto.setDescription("Elixir LLC.");
    }
    return seoDto;
  }
}
