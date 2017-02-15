package edu.nju.web.controller;

import edu.nju.bl.service.RoomService;
import edu.nju.bl.vo.ReserveVo;
import edu.nju.bl.vo.ResultVo;
import edu.nju.web.json.ReserveJson;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.sql.Date;

/**
 * REST controller for reserve module
 * @author cuihao
 */
@RestController
@RequestMapping("/api/v1/reserve")
public class ReserveController {

    @Resource
    private RoomService roomService;

    @RequestMapping(value = "",method = RequestMethod.POST)
    public ResultVo<ReserveVo> reserve(@Valid @RequestBody ReserveJson reserveJson, BindingResult result) {
        if (result.hasErrors()) return new ResultVo<>(false,result.getAllErrors().toString(),null);
        return roomService.reserve(reserveJson.getRoomId(), reserveJson.getMemberId(),
                Date.valueOf(reserveJson.getStart()), Date.valueOf(reserveJson.getEnd()),reserveJson.getName(),
                reserveJson.getContact(), reserveJson.getExtra());
    }

    @RequestMapping(value = "/{reserveId}/cancel", method = RequestMethod.POST)
    public ResultVo<Boolean> cancel(@PathVariable int reserveId) {
        return roomService.cancelReserve(reserveId);
    }

}
