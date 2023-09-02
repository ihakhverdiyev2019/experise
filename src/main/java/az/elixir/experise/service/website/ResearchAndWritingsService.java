package az.elixir.experise.service.website;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import az.elixir.experise.dto.website.AllResearchAndWritingsView;
import az.elixir.experise.dto.website.ResearchAndWritingsView;
import az.elixir.experise.dto.website.SeoDto;
import az.elixir.experise.model.LanguageEntity;
import az.elixir.experise.model.ResearchAndWritingsEntity;
import az.elixir.experise.repository.LanguageRepository;
import az.elixir.experise.repository.ResearchAndWritingsRepository;

@Service
public class ResearchAndWritingsService {

  @Autowired private ResearchAndWritingsRepository repository;
  @Autowired private LanguageRepository languageRepository;

  public List<AllResearchAndWritingsView> findAll(String langCode) {
    LanguageEntity getLanguageByLangCode =
        languageRepository.findByLangCodeAndIsEnable(langCode, true);

    List<ResearchAndWritingsEntity> getAllDetails =
        repository.findAllByLangId(getLanguageByLangCode.getId());
    List<AllResearchAndWritingsView> result = new ArrayList<>();
    for (ResearchAndWritingsEntity entity : getAllDetails) {
      AllResearchAndWritingsView allResearchAndWritingsView = new AllResearchAndWritingsView();
      allResearchAndWritingsView.mapper(entity);
      result.add(allResearchAndWritingsView);
    }
    return result;
  }

  public ResearchAndWritingsView findById(int id, String langCode) {
    LanguageEntity getLanguageByLangCode =
        languageRepository.findByLangCodeAndIsEnable(langCode, true);

    ResearchAndWritingsEntity getAllDetails =
        repository.findByIdAndLangId(id, getLanguageByLangCode.getId());
    ResearchAndWritingsView researchAndWritingsView = new ResearchAndWritingsView();
    researchAndWritingsView.mapper(getAllDetails);
    return researchAndWritingsView;
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
