package edu.nju.bl.vo;

import edu.nju.data.entity.PictureEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

/**
 * Picture entity vo
 * @author cuihao
 */
@Data
@NoArgsConstructor
public class PictureVo {
    private int id;
    private String url;
    private String alt;
    public PictureVo(PictureEntity pictureEntity) {
        BeanUtils.copyProperties(pictureEntity,this);
    }
}
