package edu.nju.web.controller;

import edu.nju.bl.service.RoomService;
import edu.nju.bl.vo.CheckVo;
import edu.nju.bl.vo.ResultVo;
import edu.nju.bl.vo.RoomVo;
import edu.nju.util.enums.BedType;
import edu.nju.web.json.DateRangeJson;
import edu.nju.web.json.RoomJson;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

/**
 * REST controller for room module
 * @author cuihao
 */
@RestController
@RequestMapping("/api/v1/room")
public class RoomController {

    @Resource
    private RoomService roomService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResultVo<RoomVo> create(@Valid @RequestParam RoomJson roomJson, BindingResult result) {
        if (result.hasErrors()) return new ResultVo<>(false,result.getAllErrors().toString(),null);
        RoomVo roomVo =  roomService.createRoom(roomJson.getHotelId(),roomJson.getRoomType(),roomJson.getSize(),
                roomJson.getPeople(), BedType.valueOf(roomJson.getBedType()),roomJson.getDescription(),
                roomJson.getNumber(),new BigDecimal(roomJson.getPrice()), Date.valueOf(roomJson.getStart()),Date.valueOf(roomJson.getEnd()));
        return new ResultVo<>(true,"",roomVo);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResultVo<RoomVo> roomDetail(@PathVariable int id, @Valid @RequestParam DateRangeJson dateRangeJson,
                                       BindingResult result) {
        if (result.hasErrors()) return new ResultVo<>(false,result.getAllErrors().toString(),null);
        RoomVo roomVo =  roomService.getRoomDetail(id,Date.valueOf(dateRangeJson.getStart()),Date.valueOf(dateRangeJson.getEnd()));
        return new ResultVo<>(true,"",roomVo);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public List<CheckVo> unfinishedChecks(@PathVariable int id) {
        return roomService.getUnfinishedChecks(id);
    }

}
