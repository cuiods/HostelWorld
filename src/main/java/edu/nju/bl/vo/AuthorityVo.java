package edu.nju.bl.vo;

import edu.nju.data.entity.AuthorityEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

/**
 * Authority entity vo
 * @author cuihao
 */
@Data
@NoArgsConstructor
public class AuthorityVo {
    private int id;
    private String name;
    private String description;
    public AuthorityVo(AuthorityEntity authorityEntity) {
        BeanUtils.copyProperties(authorityEntity,this);
    }
}
