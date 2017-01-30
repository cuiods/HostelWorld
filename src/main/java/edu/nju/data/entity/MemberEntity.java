package edu.nju.data.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by cuihao on 2017/1/26.
 */
@Entity
@Table(name = "member", schema = "hostel", catalog = "")
public class MemberEntity {
    private int id;
    private Serializable state;
    private Integer level;
    private Integer score;
    private String description;
    private Integer remain;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "state")
    public Serializable getState() {
        return state;
    }

    public void setState(Serializable state) {
        this.state = state;
    }

    @Basic
    @Column(name = "level")
    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Basic
    @Column(name = "score")
    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "remain")
    public Integer getRemain() {
        return remain;
    }

    public void setRemain(Integer remain) {
        this.remain = remain;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MemberEntity that = (MemberEntity) o;

        if (id != that.id) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;
        if (level != null ? !level.equals(that.level) : that.level != null) return false;
        if (score != null ? !score.equals(that.score) : that.score != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (remain != null ? !remain.equals(that.remain) : that.remain != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (level != null ? level.hashCode() : 0);
        result = 31 * result + (score != null ? score.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (remain != null ? remain.hashCode() : 0);
        return result;
    }
}
