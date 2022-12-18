package az.elixir.experise.model;

import javax.persistence.*;

@Entity
@Table(name = "footer")
public class FooterEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String facebook;
  private String twitter;
  private String linkedin;
  private String instagram;
  private String youtube;
  private String whatsapp;

  private String footerLogo;

  private String address;
  private String phoneNumber;

  private String email;

  @Column(columnDefinition = "text")
  private String briefInfo;

  private int langId;

  public FooterEntity() {}

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getFacebook() {
    return facebook;
  }

  public void setFacebook(String facebook) {
    this.facebook = facebook;
  }

  public String getTwitter() {
    return twitter;
  }

  public void setTwitter(String twitter) {
    this.twitter = twitter;
  }

  public String getLinkedin() {
    return linkedin;
  }

  public void setLinkedin(String linkedin) {
    this.linkedin = linkedin;
  }

  public String getInstagram() {
    return instagram;
  }

  public void setInstagram(String instagram) {
    this.instagram = instagram;
  }

  public String getYoutube() {
    return youtube;
  }

  public void setYoutube(String youtube) {
    this.youtube = youtube;
  }

  public String getWhatsapp() {
    return whatsapp;
  }

  public void setWhatsapp(String whatsapp) {
    this.whatsapp = whatsapp;
  }

  public String getFooterLogo() {
    return footerLogo;
  }

  public void setFooterLogo(String footerLogo) {
    this.footerLogo = footerLogo;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getBriefInfo() {
    return briefInfo;
  }

  public void setBriefInfo(String briefInfo) {
    this.briefInfo = briefInfo;
  }

  public int getLangId() {
    return langId;
  }

  public void setLangId(int langId) {
    this.langId = langId;
  }
}
