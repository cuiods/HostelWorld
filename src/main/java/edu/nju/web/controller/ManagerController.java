package edu.nju.web.controller;

import edu.nju.bl.service.ManagerService;
import edu.nju.bl.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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

    @ApiOperation(value = "Approve new hotel",notes = "Approve new hotel request.",
            response = ResultVo.class, produces = "application/json;charset=UTF-8")
    @PostMapping(value = "/hotel/new/{id}",produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultVo<Boolean> approveNew(@PathVariable int id) {
        return managerService.approveHotelCreate(id);
    }

    @ApiOperation(value = "Approve edit hotel",notes = "Approve edit hotel request.",
            response = ResultVo.class, produces = "application/json;charset=UTF-8")
    @PostMapping(value = "/hotel/edit/{id}", produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultVo<Boolean> approveEdit(@PathVariable int id) {
        return managerService.approveHotelEdit(id);
    }

    @ApiOperation(value = "Complete check record",notes = "Transfer money to hotel account.",
            response = ResultVo.class, produces = "application/json;charset=UTF-8")
    @PostMapping(value = "/check/complete/{id}",produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultVo<Boolean> completeCheck(@PathVariable int id) {
        return managerService.completeCheckOutRecord(id);
    }

}
