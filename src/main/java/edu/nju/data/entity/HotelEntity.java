package edu.nju.data.entity;

import edu.nju.util.enums.HotelStar;
import edu.nju.util.enums.HotelState;

import javax.persistence.*;
import java.util.List;

/**
 * Hotel entity
 */
@Entity
@Table(name = "hotel", schema = "hostel")
@PrimaryKeyJoinColumn(name = "id")
public class HotelEntity extends UserEntity{
    private int id;
    private String fullname;
    private HotelState state;
    private String location;
    private Double locationX;
    private Double locationY;
    private String description;
    private String summary;
    private HotelStar star;
    private String picture;
    private List<RoomEntity> roomEntities;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @OneToMany
    @JoinColumn(name = "hotel_id")
    public List<RoomEntity> getRoomEntities() {
        return roomEntities;
    }

    public void setRoomEntities(List<RoomEntity> roomEntities) {
        this.roomEntities = roomEntities;
    }

    @Basic
    @Column(name = "fullname")
    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    public HotelState getState() {
        return state;
    }

    public void setState(HotelState state) {
        this.state = state;
    }

    @Basic
    @Column(name = "location")
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Basic
    @Column(name = "location_x")
    public Double getLocationX() {
        return locationX;
    }

    public void setLocationX(Double locationX) {
        this.locationX = locationX;
    }

    @Basic
    @Column(name = "location_y")
    public Double getLocationY() {
        return locationY;
    }

    public void setLocationY(Double locationY) {
        this.locationY = locationY;
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
    @Column(name = "summary")
    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "star")
    public HotelStar getStar() {
        return star;
    }

    public void setStar(HotelStar star) {
        this.star = star;
    }

    @Basic
    @Column(name = "picture")
    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HotelEntity that = (HotelEntity) o;

        if (id != that.id) return false;
        if (fullname != null ? !fullname.equals(that.fullname) : that.fullname != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;
        if (location != null ? !location.equals(that.location) : that.location != null) return false;
        if (locationX != null ? !locationX.equals(that.locationX) : that.locationX != null) return false;
        if (locationY != null ? !locationY.equals(that.locationY) : that.locationY != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (summary != null ? !summary.equals(that.summary) : that.summary != null) return false;
        if (star != null ? !star.equals(that.star) : that.star != null) return false;
        if (picture != null ? !picture.equals(that.picture) : that.picture != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (fullname != null ? fullname.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (locationX != null ? locationX.hashCode() : 0);
        result = 31 * result + (locationY != null ? locationY.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (summary != null ? summary.hashCode() : 0);
        result = 31 * result + (star != null ? star.hashCode() : 0);
        result = 31 * result + (picture != null ? picture.hashCode() : 0);
        return result;
    }


}
