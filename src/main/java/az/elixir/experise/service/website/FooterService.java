package az.elixir.experise.service.website;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import az.elixir.experise.dto.website.FooterView;
import az.elixir.experise.dto.website.SeoDto;
import az.elixir.experise.model.FooterEntity;
import az.elixir.experise.model.LanguageEntity;
import az.elixir.experise.repository.FooterRepository;
import az.elixir.experise.repository.LanguageRepository;

@Service
public class FooterService {

  @Autowired private FooterRepository footerRepository;

  @Autowired private LanguageRepository languageRepository;

  public FooterView find(String langCode) {
    LanguageEntity getLanguageByLangCode =
        languageRepository.findByLangCodeAndIsEnable(langCode, true);
    FooterEntity footerEntity = footerRepository.findByLangId(getLanguageByLangCode.getId());
    FooterView footerView = new FooterView();
    footerView.mapper(footerEntity);
    return footerView;
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
