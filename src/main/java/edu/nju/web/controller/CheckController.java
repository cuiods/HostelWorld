package edu.nju.web.controller;

import edu.nju.bl.service.RoomService;
import edu.nju.bl.vo.CheckVo;
import edu.nju.bl.vo.ResultVo;
import edu.nju.bl.vo.TenantVo;
import edu.nju.data.dao.TenantDao;
import edu.nju.data.entity.TenantEntity;
import edu.nju.exception.HostelException;
import edu.nju.util.constant.ErrorCode;
import edu.nju.util.constant.MessageConstant;
import edu.nju.util.enums.PayWay;
import edu.nju.web.json.CheckJson;
import edu.nju.web.json.CheckOutJson;
import edu.nju.web.json.TenantJson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.sql.Date;

/**
 * REST controller for check module
 * @author cuihao
 */
@Api(value = "/check", description = "Hotel check in API")
@RestController
@RequestMapping("/api/v1/check")
public class CheckController {

    @Resource
    private RoomService roomService;

    @Resource
    private TenantDao tenantDao;

    @ApiOperation(value = "Check in",notes = "Hotel check in operation.",
            response = CheckVo.class,responseContainer = "ResultVo", produces = "application/json;charset=UTF-8")
    @ApiImplicitParam(name = "checkJson", value = "check in data", required = true, dataType = "CheckJson")
    @PostMapping(value = "", produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultVo<CheckVo> checkIn(@Valid @RequestBody CheckJson checkJson) throws HostelException {
        return roomService.checkIn(checkJson.getRoomId(),checkJson.getMemberId(),
                Date.valueOf(checkJson.getStart()), Date.valueOf(checkJson.getEnd()),checkJson.getTenants());
    }

    @ApiOperation(value = "Check out",notes = "Hotel check out operation.",
            response = CheckVo.class,responseContainer = "ResultVo", produces = "application/json;charset=UTF-8")
    @PostMapping(value = "/{id}/checkOut", produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultVo<CheckVo> checkOut(@PathVariable int id, @Valid @RequestBody CheckOutJson checkOutJson) throws HostelException {
        return roomService.checkOut(id,checkOutJson.getMemberId(), PayWay.valueOf(checkOutJson.getPayway()));
    }

    @ApiOperation(value = "Add a tenant",notes = "Before check in, hotel must add at least one tenant.",
            response = TenantVo.class,responseContainer = "ResultVo", produces = "application/json;charset=UTF-8")
    @ApiImplicitParam(name = "tenantJson", value = "tenant data", required = true, dataType = "TenantJson")
    @PostMapping(value = "/tenant", produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultVo<TenantVo> addTenant(@Valid @RequestBody TenantJson tenantJson) {
        TenantEntity tenantEntity = new TenantEntity();
        tenantEntity.setName(tenantJson.getName());
        tenantEntity.setIdCard(tenantJson.getIdCard());
        return new ResultVo<>(ErrorCode.SUCCESS, MessageConstant.SUCCESS,
                new TenantVo(tenantDao.findById(tenantDao.save(tenantEntity).getId())));
    }

    @ApiOperation(value = "Delete a tenant",notes = "Delete directly, cannot undo.",
            response = ResultVo.class,produces = "application/json;charset=UTF-8")
    @DeleteMapping(value = "/tenant/{id}", produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultVo<Boolean> deleteTenant(@PathVariable int id) {
        tenantDao.delete(id);
        return new ResultVo<>(ErrorCode.SUCCESS, MessageConstant.SUCCESS,true);
    }

}
