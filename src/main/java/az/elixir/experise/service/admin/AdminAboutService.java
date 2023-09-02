package az.elixir.experise.service.admin;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import az.elixir.experise.dto.admin.Payload;
import az.elixir.experise.dto.admin.about.AboutUpdateRequest;
import az.elixir.experise.dto.admin.about.AboutView;
import az.elixir.experise.dto.admin.seo.SeoRequest;
import az.elixir.experise.dto.admin.seo.SeoView;
import az.elixir.experise.model.AboutEntity;
import az.elixir.experise.model.LanguageEntity;
import az.elixir.experise.model.UserEntity;
import az.elixir.experise.model.seo.AboutSeoEntity;
import az.elixir.experise.repository.AboutRepository;
import az.elixir.experise.repository.LanguageRepository;
import az.elixir.experise.repository.seo.AboutSeoRepository;
import az.elixir.experise.utils.SaveImage;

@Service
public class AdminAboutService {

  @Autowired private LanguageRepository languageRepository;
  @Autowired private AboutRepository aboutRepository;
  @Autowired private AboutSeoRepository aboutSeoRepository;
  @Autowired private SaveImage saveImage;

  public Payload updateViewAbout(String langCode) {
    LanguageEntity getLanguageByLangCode = languageRepository.findByLangCode(langCode);
    AboutEntity aboutEntity = aboutRepository.findByLangId(getLanguageByLangCode.getId());
    Payload result = new Payload();
    AboutView aboutView = new AboutView();
    result.setSuccess(true);
    if (aboutEntity != null) {
      aboutView.setId(aboutEntity.getId());
      aboutView.setPhoto(aboutEntity.getPhoto());
      aboutView.setText(aboutEntity.getText());
      result.setResult(aboutView);
    } else {
      aboutView.setId(0);
      aboutView.setText("");
      aboutView.setPhoto("");
      result.setResult(aboutView);
    }
    return result;
  }

  public Payload updateSeoViewAbout(String langCode) {
    LanguageEntity getLanguageByLangCode = languageRepository.findByLangCode(langCode);
    AboutSeoEntity aboutSeoEntity = aboutSeoRepository.findByLangId(getLanguageByLangCode.getId());
    Payload result = new Payload();
    SeoView seoView = new SeoView();
    result.setSuccess(true);
    if (aboutSeoEntity != null) {
      seoView.setId(aboutSeoEntity.getId());
      seoView.setKey(aboutSeoEntity.getKey());
      seoView.setDescription(aboutSeoEntity.getDescription());
      seoView.setTitle(aboutSeoEntity.getTitle());
      result.setResult(seoView);
    } else {
      seoView.setId(0);
      seoView.setKey("");
      seoView.setDescription("");
      seoView.setTitle("");
      result.setResult(seoView);
    }
    return result;
  }

  public Payload doUpdateAbout(
      AboutUpdateRequest aboutUpdateRequest, MultipartFile file, UserEntity userEntity) {
    Payload payload = new Payload();
    try {
      LanguageEntity getLanguageByLangCode =
          languageRepository.findByLangCode(aboutUpdateRequest.getLangCode());
      if (aboutUpdateRequest.getAboutId() != 0) {
        AboutEntity aboutEntity = aboutRepository.findById(aboutUpdateRequest.getAboutId()).get();
        if (!file.isEmpty()) {
          boolean savedImage = saveImage.savePhoto("/about/" + aboutEntity.getId(), file);
          if (savedImage) {
            aboutEntity.setPhoto(
                "/store/about/" + aboutEntity.getId() + "/" + file.getOriginalFilename());
          } else {
            payload.setSuccess(false);
            return payload;
          }
        }
        aboutEntity.setText(aboutUpdateRequest.getText());
        aboutEntity.setUpdatedBy(userEntity.getId());
        aboutEntity.setUpdatedAt(Instant.now());
        aboutRepository.save(aboutEntity);
      } else {
        AboutEntity aboutEntityNew = new AboutEntity();
        aboutEntityNew.setText(aboutUpdateRequest.getText());
        aboutEntityNew.setCreatedAt(Instant.now());
        aboutEntityNew.setCreatedBy(userEntity.getId());
        aboutEntityNew.setUpdatedBy(0);
        aboutEntityNew.setLangId(getLanguageByLangCode.getId());
        AboutEntity saved = aboutRepository.save(aboutEntityNew);
        if (!file.isEmpty()) {
          boolean savedImage = saveImage.savePhoto("/about/" + saved.getId(), file);
          if (savedImage) {
            saved.setPhoto("/store/about/" + saved.getId() + "/" + file.getOriginalFilename());
          } else {
            payload.setSuccess(false);
            return payload;
          }
        }
        aboutRepository.save(saved);
      }
      payload.setSuccess(true);
      return payload;
    } catch (Exception e) {
      return payload;
    }
  }

  public Payload doSeoUpdateFAQ(SeoRequest seoRequest, UserEntity userEntity) {
    Payload payload = new Payload();
    try {
      LanguageEntity getLanguageByLangCode =
          languageRepository.findByLangCode(seoRequest.getLangCode());
      if (seoRequest.getId() != 0) {
        AboutSeoEntity aboutSeoEntity = aboutSeoRepository.findById(seoRequest.getId()).get();
        aboutSeoEntity.setKey(seoRequest.getKey());
        aboutSeoEntity.setTitle(seoRequest.getTitle());
        aboutSeoEntity.setDescription(seoRequest.getDescription());
        aboutSeoEntity.setUpdatedBy(userEntity.getId());
        aboutSeoEntity.setUpdatedAt(Instant.now());
        aboutSeoRepository.save(aboutSeoEntity);
      } else {
        AboutSeoEntity aboutSeoEntityNew = new AboutSeoEntity();
        aboutSeoEntityNew.setKey(seoRequest.getKey());
        aboutSeoEntityNew.setTitle(seoRequest.getTitle());
        aboutSeoEntityNew.setDescription(seoRequest.getDescription());
        aboutSeoEntityNew.setCreatedAt(Instant.now());
        aboutSeoEntityNew.setCreatedBy(userEntity.getId());
        aboutSeoEntityNew.setUpdatedBy(0);
        aboutSeoEntityNew.setLangId(getLanguageByLangCode.getId());
        aboutSeoRepository.save(aboutSeoEntityNew);
      }
      payload.setSuccess(true);
      return payload;
    } catch (Exception e) {
      return payload;
    }
  }
}
