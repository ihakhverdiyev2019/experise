package az.elixir.experise.service.website;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import az.elixir.experise.dto.website.SeoDto;
import az.elixir.experise.model.FAQEntity;
import az.elixir.experise.model.LanguageEntity;
import az.elixir.experise.model.seo.FAQSeoEntity;
import az.elixir.experise.repository.FAQRepository;
import az.elixir.experise.repository.LanguageRepository;
import az.elixir.experise.repository.seo.FAQSeoRepository;

@Service
public class FAQService {

  @Autowired private FAQRepository faqRepository;

  @Autowired private FAQSeoRepository faqSeoRepository;

  @Autowired private LanguageRepository languageRepository;

  public List<FAQEntity> findAll(String langCode) {
    LanguageEntity getLanguageByLangCode =
        languageRepository.findByLangCodeAndIsEnable(langCode, true);

    return faqRepository.findAllByLangId(getLanguageByLangCode.getId());
  }

  public SeoDto seoDetails(String langCode) {
    LanguageEntity getLanguageByLangCode =
        languageRepository.findByLangCodeAndIsEnable(langCode, true);
    FAQSeoEntity faqSeoEntity = faqSeoRepository.findByLangId(getLanguageByLangCode.getId());
    SeoDto seoDto = new SeoDto();
    if (faqSeoEntity != null) {
      seoDto.setTitle(faqSeoEntity.getTitle());
      seoDto.setKey(faqSeoEntity.getKey());
      seoDto.setDescription(faqSeoEntity.getDescription());
    } else {
      seoDto.setTitle("Elixir LLC.");
      seoDto.setKey("Elixir LLC.");
      seoDto.setDescription("Elixir LLC.");
    }
    return seoDto;
  }
}
