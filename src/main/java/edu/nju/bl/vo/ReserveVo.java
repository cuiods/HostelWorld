package edu.nju.bl.vo;

import edu.nju.data.entity.ReserveEntity;
import edu.nju.util.enums.ReserveState;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * Reserve entity vo
 * @author cuihao
 */
@Data
@NoArgsConstructor
public class ReserveVo {
    private int id;
    private Date start;
    private Date end;
    private String name;
    private String contact;
    private ReserveState state;
    private String extra;
    private Timestamp createdAt;
    private Timestamp updateAt;
    private RoomVo roomVo;

    public ReserveVo(ReserveEntity reserveEntity) {
        this(reserveEntity,false);
    }

    public ReserveVo(ReserveEntity reserveEntity, boolean hasSession) {
        BeanUtils.copyProperties(reserveEntity,this,"roomEntity","memberEntity","deletedAt");
        if (hasSession) {
            roomVo = new RoomVo(reserveEntity.getRoomEntity());
        }
    }
}
