package edu.nju.web.controller;

import edu.nju.bl.service.RoomService;
import edu.nju.bl.vo.CheckVo;
import edu.nju.bl.vo.ResultVo;
import edu.nju.bl.vo.TenantVo;
import edu.nju.data.dao.TenantDao;
import edu.nju.data.entity.TenantEntity;
import edu.nju.web.json.CheckJson;
import edu.nju.web.json.TenantJson;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.sql.Date;

/**
 * REST controller for check module
 * @author cuihao
 */
@RestController
@RequestMapping("/api/v1/check")
public class CheckController {

    @Resource
    private RoomService roomService;

    @Resource
    private TenantDao tenantDao;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResultVo<CheckVo> checkIn(@Valid @RequestBody CheckJson checkJson, BindingResult result) {
        if (result.hasErrors()) return new ResultVo<>(false,result.getAllErrors().toString(),null);
        return roomService.checkIn(checkJson.getRoomId(),checkJson.getReserveId(),
                Date.valueOf(checkJson.getStart()), Date.valueOf(checkJson.getEnd()),checkJson.getTenants());
    }

    @RequestMapping(value = "/{id}/checkOut", method = RequestMethod.POST)
    public ResultVo<CheckVo> checkOut(@PathVariable int id) {
        return roomService.checkOut(id);
    }

    @RequestMapping(value = "/tenant", method = RequestMethod.POST)
    public ResultVo<TenantVo> addTenant(@Valid @RequestBody TenantJson tenantJson, BindingResult result) {
        if (result.hasErrors()) return new ResultVo<>(false,result.getAllErrors().toString(),null);
        TenantEntity tenantEntity = new TenantEntity();
        tenantEntity.setName(tenantJson.getName());
        tenantEntity.setPhone(tenantJson.getPhone());
        tenantEntity.setIdCard(tenantJson.getIdCard());
        return new ResultVo<>(true,null,new TenantVo(tenantDao.save(tenantEntity)));
    }

    @RequestMapping(value = "/tenant/{id}", method = RequestMethod.DELETE)
    public ResultVo<Boolean> deleteTenant(@PathVariable int id) {
        tenantDao.delete(id);
        return new ResultVo<>(true,"deleted",true);
    }

}
