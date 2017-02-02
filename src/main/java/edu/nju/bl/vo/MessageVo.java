package edu.nju.bl.vo;

import edu.nju.data.entity.MessageEntity;
import edu.nju.util.enums.MessageState;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.sql.Timestamp;

/**
 * Message entity vo
 * @author cuihao
 */
@Data
@NoArgsConstructor
public class MessageVo {
    private int id;
    private String content;
    private String url;
    private MessageState isRead;
    private Timestamp createdAt;
    public MessageVo(MessageEntity messageEntity) {
        BeanUtils.copyProperties(messageEntity,this);
    }
}
