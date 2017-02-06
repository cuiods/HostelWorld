package edu.nju.bl.vo;

import edu.nju.data.entity.HotelEntity;
import edu.nju.data.entity.HotelTempEntity;
import edu.nju.util.enums.Gender;
import edu.nju.util.enums.HotelStar;
import edu.nju.util.enums.HotelState;
import edu.nju.util.enums.UserType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

/**
 * Hotel entity vo
 * @author cuihao
 */
@Data
@NoArgsConstructor
public class HotelVo {
    private int id;
    private String phone;
    private Timestamp createdAt;
    private Timestamp updateAt;
    private String fullname;
    private HotelState state;
    private String location;
    private Double locationX;
    private Double locationY;
    private String description;
    private String summary;
    private HotelStar star;
    private String picture;
    private List<RoomVo> roomVos;

    public HotelVo(HotelEntity hotelEntity) {
        this(hotelEntity,null);
    }

    public HotelVo(HotelTempEntity hotelEntity) {
        id = hotelEntity.getId();
        phone = hotelEntity.getPhone();
        createdAt = hotelEntity.getCreatedAt();
        updateAt = hotelEntity.getUpdateAt();
        fullname = hotelEntity.getFullname();
        state = hotelEntity.getState();
        location = hotelEntity.getLocation();
        locationX = hotelEntity.getLocationX();
        locationY = hotelEntity.getLocationY();
        description = hotelEntity.getDescription();
        summary = hotelEntity.getSummary();
        star = hotelEntity.getStar();
        picture = hotelEntity.getPicture();
    }

    public HotelVo(HotelEntity hotelEntity, List<RoomVo> roomVos) {
        id = hotelEntity.getId();
        phone = hotelEntity.getPhone();
        createdAt = hotelEntity.getCreatedAt();
        updateAt = hotelEntity.getUpdateAt();
        fullname = hotelEntity.getFullname();
        state = hotelEntity.getState();
        location = hotelEntity.getLocation();
        locationX = hotelEntity.getLocationX();
        locationY = hotelEntity.getLocationY();
        description = hotelEntity.getDescription();
        summary = hotelEntity.getSummary();
        star = hotelEntity.getStar();
        picture = hotelEntity.getPicture();
        this.roomVos = roomVos;
    }
}
