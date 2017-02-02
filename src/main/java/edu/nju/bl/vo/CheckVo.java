package edu.nju.bl.vo;

import edu.nju.data.entity.CheckEntity;
import edu.nju.util.enums.CheckState;
import edu.nju.util.enums.PayWay;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * Check entity vo
 * @author cuihao
 */
@Data
@NoArgsConstructor
public class CheckVo {
    private int id;
    private CheckState state;
    private Date start;
    private Date end;
    private String nameOne;
    private String nameTwo;
    private PayWay payway;
    private Timestamp createdAt;
    private Timestamp updateAt;
    private RoomVo roomVo;

    public CheckVo(CheckEntity checkEntity) {
        this(checkEntity,false);
    }

    public CheckVo(CheckEntity checkEntity, boolean hasSession) {
        BeanUtils.copyProperties(checkEntity,this,"roomEntity","memberEntity");
        if (hasSession) {
            roomVo = new RoomVo(checkEntity.getRoomEntity());
        }
    }
}
