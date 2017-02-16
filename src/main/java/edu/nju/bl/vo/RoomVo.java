package edu.nju.bl.vo;

import edu.nju.data.entity.RoomEntity;
import edu.nju.util.enums.BedType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Room entity vo
 * @author cuihao
 */
@Data
@NoArgsConstructor
public class RoomVo {
    private int id;
    private String roomType;
    private Integer size;
    private int people;
    private BedType bedType;
    private String description;
    private int number;
    private BigDecimal price;
    private int special = -1;
    private Timestamp createdAt;
    private Timestamp updateAt;
    private Date start;
    private Date end;
    private int leftRoom;

    public RoomVo(RoomEntity roomEntity) {
        this(roomEntity,0);
    }

    public RoomVo(RoomEntity roomEntity, int leftRoom) {
        this(roomEntity,leftRoom,-1);
    }

    public RoomVo(RoomEntity roomEntity, int leftRoom, int special) {
        BeanUtils.copyProperties(roomEntity,this,"deletedAt","hotelEntity","pictureEntities",
                "checkEntities","reserveEntities");
        this.leftRoom = leftRoom;
        this.special = special;
    }
}
