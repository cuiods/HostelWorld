package edu.nju.data.entity;

import javax.persistence.*;

/**
 * Created by cuihao on 2017/1/26.
 */
@Entity
@Table(name = "room_picture", schema = "hostel", catalog = "")
@IdClass(RoomPictureEntityPK.class)
public class RoomPictureEntity {
    private int roomid;
    private int picid;

    @Id
    @Column(name = "roomid")
    public int getRoomid() {
        return roomid;
    }

    public void setRoomid(int roomid) {
        this.roomid = roomid;
    }

    @Id
    @Column(name = "picid")
    public int getPicid() {
        return picid;
    }

    public void setPicid(int picid) {
        this.picid = picid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoomPictureEntity that = (RoomPictureEntity) o;

        if (roomid != that.roomid) return false;
        if (picid != that.picid) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = roomid;
        result = 31 * result + picid;
        return result;
    }
}
