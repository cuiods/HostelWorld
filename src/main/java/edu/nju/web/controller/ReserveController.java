package edu.nju.web.controller;

import edu.nju.bl.service.RoomService;
import edu.nju.bl.vo.ReserveVo;
import edu.nju.bl.vo.ResultVo;
import edu.nju.web.json.ReserveJson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.sql.Date;

/**
 * REST controller for reserve module
 * @author cuihao
 */
@Api(value = "/reserve", description = "Reservation API")
@RestController
@RequestMapping("/api/v1/reserve")
public class ReserveController {

    @Resource
    private RoomService roomService;

    @ApiOperation(value = "Reserve",notes = "Create a new reservation.",
            response = ReserveVo.class,responseContainer = "ResultVo", produces = "application/json;charset=UTF-8")
    @ApiImplicitParam(name = "reserveJson", value = "reservation data", required = true, dataType = "ReserveJson")
    @PostMapping(value = "",produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultVo<ReserveVo> reserve(@Valid @RequestBody ReserveJson reserveJson, BindingResult result) {
        if (result.hasErrors()) return new ResultVo<>(false,result.getAllErrors().toString(),null);
        return roomService.reserve(reserveJson.getRoomId(), reserveJson.getMemberId(),
                Date.valueOf(reserveJson.getStart()), Date.valueOf(reserveJson.getEnd()),reserveJson.getName(),
                reserveJson.getContact(), reserveJson.getExtra());
    }

    @ApiOperation(value = "Cancel reservation",notes = "Cancel reservation and get money back.",
            response = ResultVo.class, produces = "application/json;charset=UTF-8")
    @PostMapping(value = "/{reserveId}/cancel", produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultVo<Boolean> cancel(@PathVariable int reserveId) {
        return roomService.cancelReserve(reserveId);
    }

}
