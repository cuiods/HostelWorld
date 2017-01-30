package edu.nju.data.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by cuihao on 2017/1/26.
 */
@Entity
@Table(name = "check", schema = "hostel", catalog = "")
public class CheckEntity {
    private int id;
    private Serializable state;
    private Date date;
    private String nameOne;
    private String nameTwo;
    private Serializable payway;
    private Timestamp createdAt;
    private Timestamp updateAt;

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
    @Column(name = "date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Basic
    @Column(name = "nameOne")
    public String getNameOne() {
        return nameOne;
    }

    public void setNameOne(String nameOne) {
        this.nameOne = nameOne;
    }

    @Basic
    @Column(name = "nameTwo")
    public String getNameTwo() {
        return nameTwo;
    }

    public void setNameTwo(String nameTwo) {
        this.nameTwo = nameTwo;
    }

    @Basic
    @Column(name = "payway")
    public Serializable getPayway() {
        return payway;
    }

    public void setPayway(Serializable payway) {
        this.payway = payway;
    }

    @Basic
    @Column(name = "created_at")
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Basic
    @Column(name = "update_at")
    public Timestamp getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Timestamp updateAt) {
        this.updateAt = updateAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CheckEntity that = (CheckEntity) o;

        if (id != that.id) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (nameOne != null ? !nameOne.equals(that.nameOne) : that.nameOne != null) return false;
        if (nameTwo != null ? !nameTwo.equals(that.nameTwo) : that.nameTwo != null) return false;
        if (payway != null ? !payway.equals(that.payway) : that.payway != null) return false;
        if (createdAt != null ? !createdAt.equals(that.createdAt) : that.createdAt != null) return false;
        if (updateAt != null ? !updateAt.equals(that.updateAt) : that.updateAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (nameOne != null ? nameOne.hashCode() : 0);
        result = 31 * result + (nameTwo != null ? nameTwo.hashCode() : 0);
        result = 31 * result + (payway != null ? payway.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (updateAt != null ? updateAt.hashCode() : 0);
        return result;
    }
}
