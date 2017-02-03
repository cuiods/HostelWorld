package edu.nju.bl.vo;

import edu.nju.data.entity.MemberEntity;
import edu.nju.util.enums.Gender;
import edu.nju.util.enums.MemberState;
import edu.nju.util.enums.UserType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.sql.Timestamp;

/**
 * Simple member vo
 * @author cuihao
 */
@Data
@NoArgsConstructor
public class MemberVo {
    private int id;
    private String name;
    private String phone;
    private String avatar;
    private Gender gender;
    private UserType type;
    private Timestamp createdAt;
    private Timestamp updateAt;
    private MemberState state;
    private Integer level;
    private Integer score;
    private String description;
    private Integer remain;
    private byte valid;

    public MemberVo(MemberEntity memberEntity) {
        BeanUtils.copyProperties(memberEntity,this,"deletedAt","password","authorityEntities",
                "accountEntities","messageEntities","checkEntities","reserveEntities");
    }
}
