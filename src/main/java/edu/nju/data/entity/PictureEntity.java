package edu.nju.data.entity;

import javax.persistence.*;

/**
 * Created by cuihao on 2017/1/26.
 */
@Entity
@Table(name = "picture", schema = "hostel", catalog = "")
public class PictureEntity {
    private int id;
    private String url;
    private String alt;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Basic
    @Column(name = "alt")
    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PictureEntity that = (PictureEntity) o;

        if (id != that.id) return false;
        if (url != null ? !url.equals(that.url) : that.url != null) return false;
        if (alt != null ? !alt.equals(that.alt) : that.alt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (alt != null ? alt.hashCode() : 0);
        return result;
    }
}
