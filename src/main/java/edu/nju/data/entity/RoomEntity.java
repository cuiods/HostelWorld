package edu.nju.data.entity;

import edu.nju.util.enums.BedType;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 * Hotel room plan entity
 */
@Entity
@Table(name = "room")
@Where(clause="deleted_at is null")
public class RoomEntity {
    private int id;
    private String roomType;
    private Integer size;
    private int people;
    private BedType bedType;
    private String description;
    private int number;
    private BigDecimal price;
    private Timestamp createdAt;
    private Timestamp updateAt;
    private Date start;
    private Date end;
    private Timestamp deletedAt;
    private HotelEntity hotelEntity;
    private List<PictureEntity> pictureEntities;
    private List<CheckRecordEntity> checkEntities;
    private List<ReserveEntity> reserveEntities;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "hotel_id", referencedColumnName = "id")
    public HotelEntity getHotelEntity() {
        return hotelEntity;
    }

    public void setHotelEntity(HotelEntity hotelEntity) {
        this.hotelEntity = hotelEntity;
    }

    @ManyToMany
    @JoinTable(name = "room_picture", schema = "hostel",
            joinColumns = @JoinColumn(name = "roomid", referencedColumnName = "id",nullable = false),
            inverseJoinColumns = @JoinColumn(name = "picid", referencedColumnName = "id",nullable = false))
    public List<PictureEntity> getPictureEntities() {
        return pictureEntities;
    }

    public void setPictureEntities(List<PictureEntity> pictureEntities) {
        this.pictureEntities = pictureEntities;
    }

    @OneToMany
    @JoinColumn(name = "roomid")
    public List<CheckRecordEntity> getCheckEntities() {
        return checkEntities;
    }

    public void setCheckEntities(List<CheckRecordEntity> checkEntities) {
        this.checkEntities = checkEntities;
    }

    @OneToMany
    @JoinColumn(name = "roomid")
    public List<ReserveEntity> getReserveEntities() {
        return reserveEntities;
    }

    public void setReserveEntities(List<ReserveEntity> reserveEntities) {
        this.reserveEntities = reserveEntities;
    }

    @Basic
    @Column(name = "room_type")
    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    @Basic
    @Column(name = "size")
    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    @Basic
    @Column(name = "people")
    public int getPeople() {
        return people;
    }

    public void setPeople(int people) {
        this.people = people;
    }

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "bed_type")
    public BedType getBedType() {
        return bedType;
    }

    public void setBedType(BedType bedType) {
        this.bedType = bedType;
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
    @Column(name = "number")
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Basic
    @Column(name = "price")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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

    @Basic
    @Column(name = "start")
    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    @Basic
    @Column(name = "end")
    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    @Basic
    @Column(name = "deleted_at")
    public Timestamp getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Timestamp deletedAt) {
        this.deletedAt = deletedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoomEntity that = (RoomEntity) o;

        if (id != that.id) return false;
        if (people != that.people) return false;
        if (number != that.number) return false;
        if (roomType != null ? !roomType.equals(that.roomType) : that.roomType != null) return false;
        if (size != null ? !size.equals(that.size) : that.size != null) return false;
        if (bedType != null ? !bedType.equals(that.bedType) : that.bedType != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (createdAt != null ? !createdAt.equals(that.createdAt) : that.createdAt != null) return false;
        if (updateAt != null ? !updateAt.equals(that.updateAt) : that.updateAt != null) return false;
        if (start != null ? !start.equals(that.start) : that.start != null) return false;
        if (end != null ? !end.equals(that.end) : that.end != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (roomType != null ? roomType.hashCode() : 0);
        result = 31 * result + (size != null ? size.hashCode() : 0);
        result = 31 * result + people;
        result = 31 * result + (bedType != null ? bedType.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + number;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (updateAt != null ? updateAt.hashCode() : 0);
        result = 31 * result + (start != null ? start.hashCode() : 0);
        result = 31 * result + (end != null ? end.hashCode() : 0);
        return result;
    }

}
