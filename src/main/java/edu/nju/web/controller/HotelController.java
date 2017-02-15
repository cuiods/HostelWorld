package edu.nju.web.controller;

import edu.nju.bl.service.HotelService;
import edu.nju.bl.vo.*;
import edu.nju.util.enums.Gender;
import edu.nju.util.enums.HotelStar;
import edu.nju.web.json.HotelCreateJson;
import edu.nju.web.json.HotelEditJson;
import edu.nju.web.json.PageJson;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * Rest controller for hotel module
 * @author cuihao
 */
@RestController
@RequestMapping("/api/v1/hotel")
public class HotelController {

    @Resource
    private HotelService hotelService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Page<HotelVo> hotelList(@Valid @RequestParam PageJson pageJson) {
        return hotelService.getHotelList(pageJson.getPage(),pageJson.getPageSize());
    }

    @RequestMapping(value = "/{hotelId}",method = RequestMethod.GET)
    public HotelVo detail(@PathVariable int hotelId) {
        return hotelService.getHotelDetail(hotelId);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResultVo<HotelVo> create(@Valid @RequestParam HotelCreateJson hotel, BindingResult result) {
        if (result.hasErrors()) return new ResultVo<>(false,result.getAllErrors().toString(),null);
        HotelVo hotelVo = hotelService.createHotel(hotel.getName(),hotel.getPassword(),hotel.getPhone(),hotel.getAvatar(),
                Gender.valueOf(hotel.getGender()),hotel.getFullName(),hotel.getLocation(),hotel.getX(),hotel.getY(),
                hotel.getDescription(),hotel.getSummary(), HotelStar.valueOf(hotel.getHotelStar()),hotel.getPicture());
        return new ResultVo<>(true,"",hotelVo);
    }

    @RequestMapping(value = "/{hotelId}/edit", method = RequestMethod.PUT)
    public ResultVo<HotelTempVo> edit(@PathVariable int hotelId,
                                      @Valid @RequestParam HotelEditJson hotel, BindingResult result) {
        if (result.hasErrors()) return new ResultVo<>(false,result.getAllErrors().toString(),null);
        HotelTempVo hotelTempVo = hotelService.editHotel(hotelId,hotel.getFullName(),hotel.getLocation(),
                hotel.getX(),hotel.getY(),hotel.getDescription(),hotel.getSummary(),HotelStar.valueOf(hotel.getHotelStar()),
                hotel.getPicture());
        return new ResultVo<>(true,"",hotelTempVo);
    }

    @RequestMapping(value = "/{id}/reserve", method = RequestMethod.GET)
    public List<ReserveVo> reserves(@PathVariable int id) {
        return hotelService.getHotelReserve(id);
    }

    @RequestMapping(value = "/{id}/check", method = RequestMethod.GET)
    public List<CheckVo> checks(@PathVariable int id) {
        return hotelService.getHotelCheck(id);
    }

    @RequestMapping(value = "/{id}/room", method = RequestMethod.GET)
    public List<RoomVo> rooms(@PathVariable int id) {
        return hotelService.getAvailableRooms(id);
    }

}
