package az.elixir.experise.service.website;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import az.elixir.experise.dto.website.AboutView;
import az.elixir.experise.dto.website.SeoDto;
import az.elixir.experise.model.AboutEntity;
import az.elixir.experise.model.LanguageEntity;
import az.elixir.experise.repository.AboutRepository;
import az.elixir.experise.repository.LanguageRepository;

@Service
public class AboutService {

  @Autowired private AboutRepository aboutRepository;

  @Autowired private LanguageRepository languageRepository;

  public AboutView findAbout(String langCode) {
    LanguageEntity getLanguageByLangCode =
        languageRepository.findByLangCodeAndIsEnable(langCode, true);
    AboutEntity getAllDetails = aboutRepository.findByLangId(getLanguageByLangCode.getId());
    AboutView aboutView = new AboutView();
    aboutView.mapper(getAllDetails);
    return aboutView;
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
