package az.elixir.experise.dto.admin.faq;

public class FAQUpdateRequest {

  private String answer;
  private String question;

  private int faqId;

  private String langCode;
  private int referId;

  public FAQUpdateRequest() {}

  public String getAnswer() {
    return answer;
  }

  public void setAnswer(String answer) {
    this.answer = answer;
  }

  public String getQuestion() {
    return question;
  }

  public void setQuestion(String question) {
    this.question = question;
  }

  public int getFaqId() {
    return faqId;
  }

  public void setFaqId(int faqId) {
    this.faqId = faqId;
  }

  public String getLangCode() {
    return langCode;
  }

  public void setLangCode(String langCode) {
    this.langCode = langCode;
  }

  public int getReferId() {
    return referId;
  }

  public void setReferId(int referId) {
    this.referId = referId;
  }
}
