package edu.nju.web.controller;

import edu.nju.bl.service.RoomService;
import edu.nju.bl.vo.CheckVo;
import edu.nju.bl.vo.ResultVo;
import edu.nju.bl.vo.RoomVo;
import edu.nju.util.enums.BedType;
import edu.nju.web.json.DateRangeJson;
import edu.nju.web.json.RoomJson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
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
@Api(value = "/room",description = "Room API")
@RestController
@RequestMapping("/api/v1/room")
public class RoomController {

    @Resource
    private RoomService roomService;

    @ApiOperation(value = "Create new room",notes = "Hotel publish a new room plan.",
            response = RoomVo.class, produces = "application/json;charset=UTF-8")
    @ApiImplicitParam(name = "roomJson", value = "room data", required = true, dataType = "RoomJson")
    @PostMapping(value = "", produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultVo<RoomVo> create(@Valid @RequestParam RoomJson roomJson, BindingResult result) {
        if (result.hasErrors()) return new ResultVo<>(false,result.getAllErrors().toString(),null);
        RoomVo roomVo =  roomService.createRoom(roomJson.getHotelId(),roomJson.getRoomType(),roomJson.getSize(),
                roomJson.getPeople(), BedType.valueOf(roomJson.getBedType()),roomJson.getDescription(),
                roomJson.getNumber(),new BigDecimal(roomJson.getPrice()), Date.valueOf(roomJson.getStart()),Date.valueOf(roomJson.getEnd()));
        return new ResultVo<>(true,"",roomVo);
    }

    @ApiOperation(value = "Get room detail",notes = "Get room detail info with left room number.",
            response = RoomVo.class,responseContainer = "ResultVo", produces = "application/json;charset=UTF-8")
    @ApiImplicitParam(name = "dateRangeJson", value = "date query range", required = true, dataType = "DateRangeJson")
    @GetMapping(value = "/{id}",produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultVo<RoomVo> roomDetail(@PathVariable int id, @Valid @RequestParam DateRangeJson dateRangeJson,
                                       BindingResult result) {
        if (result.hasErrors()) return new ResultVo<>(false,result.getAllErrors().toString(),null);
        RoomVo roomVo =  roomService.getRoomDetail(id,Date.valueOf(dateRangeJson.getStart()),Date.valueOf(dateRangeJson.getEnd()));
        return new ResultVo<>(true,"",roomVo);
    }

    @ApiOperation(value = "Get unfinished check records",notes = "Get unfinished room check in records.",
            response = CheckVo.class,responseContainer = "List", produces = "application/json;charset=UTF-8")
    @GetMapping(value = "/{id}/unfinished", produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<CheckVo> unfinishedChecks(@PathVariable int id) {
        return roomService.getUnfinishedChecks(id);
    }

}
