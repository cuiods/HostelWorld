package edu.nju.bl.vo;

import edu.nju.data.entity.AccountEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

/**
 * Show account info
 * @author cuihao
 */
@Data
@NoArgsConstructor
public class AccountVo {
    private int id;
    private Integer balance;

    public AccountVo(AccountEntity accountEntity) {
        BeanUtils.copyProperties(accountEntity,this,"userEntity");
    }
}
