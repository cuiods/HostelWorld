package edu.nju.web.controller;

import edu.nju.bl.service.HotelService;
import edu.nju.bl.vo.*;
import edu.nju.util.constant.ErrorCode;
import edu.nju.util.constant.MessageConstant;
import edu.nju.util.enums.Gender;
import edu.nju.util.enums.HotelStar;
import edu.nju.web.json.HotelCreateJson;
import edu.nju.web.json.HotelEditJson;
import edu.nju.web.json.PageJson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * Rest controller for hotel module
 * @author cuihao
 */
@Api(value = "/hotel", description = "Hotel API")
@RestController
@RequestMapping("/api/v1/hotel")
public class HotelController {

    @Resource
    private HotelService hotelService;

    @ApiOperation(value = "Get hotel list",notes = "Get a page of hotelVo",
            response = Page.class, produces = "application/json;charset=UTF-8")
    @ApiImplicitParam(name = "pageJson", value = "page setting", required = true, dataType = "PageJson")
    @GetMapping(value = "", produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Page<HotelVo> hotelList(@Valid @RequestParam PageJson pageJson) {
        return hotelService.getHotelList(pageJson.getPage(),pageJson.getPageSize());
    }

    @ApiOperation(value = "Get hotel detail",notes = "Get detail info of a hotel, including rooms.",
            response = HotelVo.class, produces = "application/json;charset=UTF-8")
    @GetMapping(value = "/{hotelId}",produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public HotelVo detail(@PathVariable int hotelId) {
        return hotelService.getHotelDetail(hotelId);
    }

    @ApiOperation(value = "Create hotel",notes = "Create a new hotel user, need to be examined.",
            response = ResultVo.class, produces = "application/json;charset=UTF-8")
    @ApiImplicitParam(name = "hotel", value = "hotel data", required = true, dataType = "HotelCreateJson")
    @PostMapping(value = "", produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultVo<HotelVo> create(@Valid @RequestParam HotelCreateJson hotel) {
        HotelVo hotelVo = hotelService.createHotel(hotel.getName(),hotel.getPassword(),hotel.getPhone(),hotel.getAvatar(),
                Gender.valueOf(hotel.getGender()),hotel.getFullName(),hotel.getLocation(),hotel.getX(),hotel.getY(),
                hotel.getDescription(),hotel.getSummary(), HotelStar.valueOf(hotel.getHotelStar()),hotel.getPicture());
        return new ResultVo<>(ErrorCode.SUCCESS, MessageConstant.SUCCESS,hotelVo);
    }

    @ApiOperation(value = "Edit hotel",notes = "Edit hotel info, need to be examined.",
            response = ResultVo.class, produces = "application/json;charset=UTF-8")
    @ApiImplicitParam(name = "hotel", value = "hotel data", required = true, dataType = "HotelEditJson")
    @PutMapping(value = "/{hotelId}/edit", produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultVo<HotelTempVo> edit(@PathVariable int hotelId,
                                      @Valid @RequestParam HotelEditJson hotel) {
        HotelTempVo hotelTempVo = hotelService.editHotel(hotelId,hotel.getFullName(),hotel.getLocation(),
                hotel.getX(),hotel.getY(),hotel.getDescription(),hotel.getSummary(),HotelStar.valueOf(hotel.getHotelStar()),
                hotel.getPicture());
        return new ResultVo<>(ErrorCode.SUCCESS, MessageConstant.SUCCESS,hotelTempVo);
    }

    @ApiOperation(value = "Get hotel reservations",notes = "Get all reservations of a hotel.",
            response = ReserveVo.class, responseContainer = "List", produces = "application/json;charset=UTF-8")
    @GetMapping(value = "/{id}/reserve", produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<ReserveVo> reserves(@PathVariable int id) {
        return hotelService.getHotelReserve(id);
    }

    @ApiOperation(value = "Get hotel check in records",notes = "Get all check in records of a hotel.",
            response = CheckVo.class, responseContainer = "List", produces = "application/json;charset=UTF-8")
    @GetMapping(value = "/{id}/check", produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<CheckVo> checks(@PathVariable int id) {
        return hotelService.getHotelCheck(id);
    }

    @ApiOperation(value = "Get hotel rooms",notes = "Get all rooms of a hotel.",
            response = RoomVo.class, responseContainer = "List", produces = "application/json;charset=UTF-8")
    @GetMapping(value = "/{id}/room", produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<RoomVo> rooms(@PathVariable int id) {
        return hotelService.getAvailableRooms(id);
    }

}
