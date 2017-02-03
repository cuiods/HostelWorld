package edu.nju.bl.vo;

import edu.nju.data.entity.UserEntity;
import edu.nju.util.enums.Gender;
import edu.nju.util.enums.UserType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

/**
 * User entity vo
 * @author cuihao
 */
@Data
@NoArgsConstructor
public class UserVo {
    private int id;
    private String name;
    private String phone;
    private String avatar;
    private Gender gender;
    private UserType type;
    private Timestamp createdAt;
    private Timestamp updateAt;
    private byte valid;
    private List<AuthorityVo> authorityVos;
    public UserVo(UserEntity userEntity) {
        BeanUtils.copyProperties(userEntity,this,"deletedAt","password",
                "authorityEntities","accountEntities","messageEntities");
        this.authorityVos = userEntity.getAuthorityEntities().stream()
                .map(AuthorityVo::new).collect(Collectors.toList());
    }
}
