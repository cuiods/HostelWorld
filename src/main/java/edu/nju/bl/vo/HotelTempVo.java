package edu.nju.bl.vo;

import edu.nju.data.entity.HotelTempEntity;
import edu.nju.util.enums.HotelStar;
import edu.nju.util.enums.HotelState;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

/**
 * Show hotel edit vo
 * @author cuihao
 */
@Data
@NoArgsConstructor
public class HotelTempVo {
    private int id;
    private String fullname;
    private HotelState state;
    private String location;
    private Double locationX;
    private Double locationY;
    private String description;
    private String summary;
    private HotelStar star;
    private String picture;
    public HotelTempVo(HotelTempEntity hotelTempEntity) {
        BeanUtils.copyProperties(hotelTempEntity,this);
    }
}
