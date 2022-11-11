package az.elixir.experise.model;

import javax.persistence.*;

@Entity
@Table(name = "language")
public class LanguageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String langPhoto;
    private String langText;
    private String langCode;
    private boolean isEnable;

    public LanguageEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLangPhoto() {
        return langPhoto;
    }

    public void setLangPhoto(String langPhoto) {
        this.langPhoto = langPhoto;
    }

    public String getLangText() {
        return langText;
    }

    public void setLangText(String langText) {
        this.langText = langText;
    }

    public String getLangCode() {
        return langCode;
    }

    public void setLangCode(String langCode) {
        this.langCode = langCode;
    }

    public boolean isEnable() {
        return isEnable;
    }

    public void setEnable(boolean enable) {
        isEnable = enable;
    }
}
