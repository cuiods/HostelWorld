package edu.nju.bl.vo;

import edu.nju.data.entity.CheckRecordEntity;
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

    public CheckVo(CheckRecordEntity checkRecordEntity) {
        this(checkRecordEntity,false);
    }

    public CheckVo(CheckRecordEntity checkRecordEntity, boolean hasSession) {
        BeanUtils.copyProperties(checkRecordEntity,this,"roomEntity","memberEntity");
        if (hasSession) {
            roomVo = new RoomVo(checkRecordEntity.getRoomEntity());
        }
    }
}
