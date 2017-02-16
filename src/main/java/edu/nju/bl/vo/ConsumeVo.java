package edu.nju.bl.vo;

import edu.nju.data.entity.ConsumeRecordEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.sql.Timestamp;

/**
 * Consume record vo
 * @author cuihao
 */
@Data
@NoArgsConstructor
public class ConsumeVo {
    private int id;
    private Timestamp createdAt;
    private int number;
    private String description;
    public ConsumeVo(ConsumeRecordEntity recordEntity) {
        BeanUtils.copyProperties(recordEntity,this,"memberEntity");
    }
}
