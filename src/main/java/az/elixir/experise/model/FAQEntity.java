package az.elixir.experise.model;

import java.time.Instant;

import javax.persistence.*;

@Entity
@Table(name = "faq")
public class FAQEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String question;

  private int langId;

  private int referId;

  private int createdBy;
  private int updatedBy;

  private Instant createdAt;

  private Instant updatedAt;

  @Column(columnDefinition = "text")
  private String answer;

  public FAQEntity() {}

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getQuestion() {
    return question;
  }

  public void setQuestion(String question) {
    this.question = question;
  }

  public String getAnswer() {
    return answer;
  }

  public void setAnswer(String answer) {
    this.answer = answer;
  }

  public int getLangId() {
    return langId;
  }

  public void setLangId(int langId) {
    this.langId = langId;
  }

  public int getReferId() {
    return referId;
  }

  public void setReferId(int referId) {
    this.referId = referId;
  }

  public int getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(int createdBy) {
    this.createdBy = createdBy;
  }

  public int getUpdatedBy() {
    return updatedBy;
  }

  public void setUpdatedBy(int updatedBy) {
    this.updatedBy = updatedBy;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Instant createdAt) {
    this.createdAt = createdAt;
  }

  public Instant getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Instant updatedAt) {
    this.updatedAt = updatedAt;
  }
}
