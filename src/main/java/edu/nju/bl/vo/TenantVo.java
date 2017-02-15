package edu.nju.bl.vo;

import edu.nju.data.entity.TenantEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

/**
 * tenant vo
 */
@Data
@NoArgsConstructor
public class TenantVo {
    private int id;
    private String name;
    private String phone;
    private String idCard;
    public TenantVo(TenantEntity tenantEntity) {
        BeanUtils.copyProperties(tenantEntity,this);
    }
}
