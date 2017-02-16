package edu.nju.web.controller;

import edu.nju.bl.service.ManagerService;
import edu.nju.bl.vo.CheckVo;
import edu.nju.bl.vo.HotelTempVo;
import edu.nju.bl.vo.HotelVo;
import edu.nju.bl.vo.ResultVo;
import edu.nju.web.json.CompleteJson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Rest controller for manager module
 * @author cuihao
 */
@Api(value = "/manager",description = "Manager API")
@RestController
@RequestMapping("/api/v1/manager")
public class ManagerController {

    @Resource
    private ManagerService managerService;

    @ApiOperation(value = "Get new hotels",notes = "Get new hotels to be approved.",
            response = HotelVo.class, responseContainer = "List",produces = "application/json;charset=UTF-8")
    @GetMapping(value = "/hotel/new",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<HotelVo> approveNewHotels() {
        return managerService.getCreatedHotel();
    }

    @ApiOperation(value = "Approve new hotel",notes = "Approve new hotel request.",
            response = ResultVo.class, produces = "application/json;charset=UTF-8")
    @PostMapping(value = "/hotel/new/{id}",produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultVo<Boolean> approveNew(@PathVariable int id) {
        return managerService.approveHotelCreate(id);
    }

    @ApiOperation(value = "Get edit hotels",notes = "Get edited hotels to be approved.",
            response = HotelTempVo.class, responseContainer = "List", produces = "application/json;charset=UTF-8")
    @GetMapping(value = "/hotel/edit",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<HotelTempVo> approveEditHotels() {
        return managerService.getEditHotel();
    }

    @ApiOperation(value = "Approve edit hotel",notes = "Approve edit hotel request.",
            response = ResultVo.class, produces = "application/json;charset=UTF-8")
    @PostMapping(value = "/hotel/edit/{id}", produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultVo<Boolean> approveEdit(@PathVariable int id) {
        return managerService.approveHotelEdit(id);
    }

    @ApiOperation(value = "Get uncompleted checks",notes = "Get uncompleted check records to transfer money to hotels.",
            response = CheckVo.class, responseContainer = "List", produces = "application/json;charset=UTF-8")
    @GetMapping(value = "/check/complete",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<CheckVo> getUncompletedChecks() {
        return managerService.getUnCompletedCheck();
    }

    @ApiOperation(value = "Complete check record",notes = "Transfer money to hotel account.",
            response = ResultVo.class, produces = "application/json;charset=UTF-8")
    @PostMapping(value = "/check/complete/{id}",produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultVo<Boolean> completeCheck(@PathVariable int id) {
        return managerService.completeCheckOutRecord(id);
    }

    @ApiOperation(value = "Complete check records",notes = "Transfer money to hotel account.",
            response = ResultVo.class, produces = "application/json;charset=UTF-8")
    @ApiImplicitParam(name = "completeJson", value = "check ids", required = true, dataType = "CompleteJson")
    @PostMapping(value = "/check/complete",produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultVo<Boolean> completeCheck(@RequestBody CompleteJson completeJson) {
        return managerService.completeCheckOutRecord(completeJson.getCheckIds());
    }

}
