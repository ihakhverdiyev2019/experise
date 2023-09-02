package az.elixir.experise.service.admin;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import az.elixir.experise.dto.admin.Payload;
import az.elixir.experise.dto.admin.faq.FAQUpdateRequest;
import az.elixir.experise.dto.admin.faq.FAQView;
import az.elixir.experise.dto.admin.faq.ListOfFAQ;
import az.elixir.experise.dto.admin.seo.SeoRequest;
import az.elixir.experise.dto.admin.seo.SeoView;
import az.elixir.experise.model.FAQEntity;
import az.elixir.experise.model.LanguageEntity;
import az.elixir.experise.model.UserEntity;
import az.elixir.experise.model.seo.FAQSeoEntity;
import az.elixir.experise.repository.FAQRepository;
import az.elixir.experise.repository.LanguageRepository;
import az.elixir.experise.repository.seo.FAQSeoRepository;

@Service
public class AdminFAQService {
  @Autowired private FAQRepository faqRepository;
  @Autowired private LanguageRepository languageRepository;
  @Autowired private FAQSeoRepository faqSeoRepository;

  public Payload faqList() {

    Payload payload = new Payload();
    List<ListOfFAQ> result = new ArrayList<>();
    List<FAQEntity> getAll = faqRepository.findAll();
    for (FAQEntity faqEntity : getAll) {
      if (faqEntity.getId() == faqEntity.getReferId()) {
        ListOfFAQ listOfFAQ = new ListOfFAQ();
        listOfFAQ.setId(faqEntity.getId());
        listOfFAQ.setTitle(faqEntity.getQuestion());
        result.add(listOfFAQ);
      }
    }
    payload.setSuccess(true);
    payload.setResult(result);
    return payload;
  }

  public Payload removeFAQ(String id) {

    Payload payload = new Payload();
    List<FAQEntity> getAll = faqRepository.findAllByReferId(Integer.parseInt(id));
    if (getAll.size() > 0) {
      faqRepository.deleteAll(getAll);
    }
    payload.setSuccess(true);
    return payload;
  }

  public Payload updateViewFAQ(String id, String langCode) {
    LanguageEntity getLanguageByLangCode = languageRepository.findByLangCode(langCode);
    FAQEntity faqEntity =
        faqRepository.findByLangIdAndReferId(getLanguageByLangCode.getId(), Integer.parseInt(id));
    Payload result = new Payload();
    FAQView faqView = new FAQView();
    result.setSuccess(true);
    if (faqEntity != null) {
      faqView.setId(faqEntity.getId());
      faqView.setReferId(faqEntity.getReferId());
      faqView.setQuestion(faqEntity.getQuestion());
      faqView.setAnswer(faqEntity.getAnswer());
      result.setResult(faqView);
    } else {
      faqView.setId(0);
      faqView.setReferId(0);
      faqView.setQuestion("");
      faqView.setAnswer("");
      result.setResult(faqView);
    }
    return result;
  }

  public Payload updateSeoViewFAQ(String langCode) {
    LanguageEntity getLanguageByLangCode = languageRepository.findByLangCode(langCode);
    FAQSeoEntity faqSeoEntity = faqSeoRepository.findByLangId(getLanguageByLangCode.getId());
    Payload result = new Payload();
    SeoView seoView = new SeoView();
    result.setSuccess(true);
    if (faqSeoEntity != null) {
      seoView.setId(faqSeoEntity.getId());
      seoView.setKey(faqSeoEntity.getKey());
      seoView.setDescription(faqSeoEntity.getDescription());
      seoView.setTitle(faqSeoEntity.getTitle());
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

  public Payload doUpdateFAQ(FAQUpdateRequest faqUpdateRequest, UserEntity userEntity) {
    Payload payload = new Payload();
    try {
      LanguageEntity getLanguageByLangCode =
          languageRepository.findByLangCode(faqUpdateRequest.getLangCode());
      if (faqUpdateRequest.getFaqId() != 0) {
        FAQEntity faqEntity = faqRepository.findById(faqUpdateRequest.getFaqId()).get();
        faqEntity.setQuestion(faqUpdateRequest.getQuestion());
        faqEntity.setAnswer(faqUpdateRequest.getAnswer());
        faqEntity.setUpdatedBy(userEntity.getId());
        faqEntity.setUpdatedAt(Instant.now());
        faqRepository.save(faqEntity);
      } else {
        FAQEntity faqEntity1 = new FAQEntity();
        faqEntity1.setAnswer(faqUpdateRequest.getAnswer());
        faqEntity1.setQuestion(faqUpdateRequest.getQuestion());
        faqEntity1.setCreatedAt(Instant.now());
        faqEntity1.setCreatedBy(userEntity.getId());
        faqEntity1.setUpdatedBy(0);
        faqEntity1.setLangId(getLanguageByLangCode.getId());
        faqEntity1.setReferId(faqUpdateRequest.getReferId());
        faqRepository.save(faqEntity1);
      }
      payload.setSuccess(true);
      return payload;
    } catch (Exception e) {
      return payload;
    }
  }

  public Payload doAddFAQ(FAQUpdateRequest faqUpdateRequest, UserEntity userEntity) {
    Payload payload = new Payload();
    try {
      LanguageEntity getLanguageByLangCode =
          languageRepository.findByLangCode(faqUpdateRequest.getLangCode());
      FAQEntity faqEntity = new FAQEntity();
      faqEntity.setAnswer(faqUpdateRequest.getAnswer());
      faqEntity.setQuestion(faqUpdateRequest.getQuestion());
      faqEntity.setCreatedAt(Instant.now());
      faqEntity.setCreatedBy(userEntity.getId());
      faqEntity.setUpdatedBy(0);
      faqEntity.setLangId(getLanguageByLangCode.getId());
      FAQEntity saved = faqRepository.save(faqEntity);
      saved.setReferId(saved.getId());
      faqRepository.save(saved);
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
        FAQSeoEntity faqSeoEntity = faqSeoRepository.findById(seoRequest.getId()).get();
        faqSeoEntity.setKey(seoRequest.getKey());
        faqSeoEntity.setTitle(seoRequest.getTitle());
        faqSeoEntity.setDescription(seoRequest.getDescription());
        faqSeoEntity.setUpdatedBy(userEntity.getId());
        faqSeoEntity.setUpdatedAt(Instant.now());
        faqSeoRepository.save(faqSeoEntity);
      } else {
        FAQSeoEntity faqSeoEntity1 = new FAQSeoEntity();
        faqSeoEntity1.setKey(seoRequest.getKey());
        faqSeoEntity1.setTitle(seoRequest.getTitle());
        faqSeoEntity1.setDescription(seoRequest.getDescription());
        faqSeoEntity1.setCreatedAt(Instant.now());
        faqSeoEntity1.setCreatedBy(userEntity.getId());
        faqSeoEntity1.setUpdatedBy(0);
        faqSeoEntity1.setLangId(getLanguageByLangCode.getId());
        faqSeoRepository.save(faqSeoEntity1);
      }
      payload.setSuccess(true);
      return payload;
    } catch (Exception e) {
      return payload;
    }
  }
}
