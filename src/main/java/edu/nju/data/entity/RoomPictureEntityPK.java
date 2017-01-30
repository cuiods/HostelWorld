package edu.nju.data.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by cuihao on 2017/1/26.
 */
public class RoomPictureEntityPK implements Serializable {
    private int roomid;
    private int picid;

    @Column(name = "roomid")
    @Id
    public int getRoomid() {
        return roomid;
    }

    public void setRoomid(int roomid) {
        this.roomid = roomid;
    }

    @Column(name = "picid")
    @Id
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

        RoomPictureEntityPK that = (RoomPictureEntityPK) o;

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
