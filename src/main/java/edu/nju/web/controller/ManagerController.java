package edu.nju.web.controller;

import edu.nju.bl.service.ManagerService;
import edu.nju.bl.vo.ResultVo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Rest controller for manager module
 * @author cuihao
 */
@RestController
@RequestMapping("/api/v1/manager")
public class ManagerController {

    @Resource
    private ManagerService managerService;

    @RequestMapping(value = "/hotel/new/{id}", method = RequestMethod.POST)
    public ResultVo<Boolean> approveNew(@PathVariable int id) {
        return managerService.approveHotelCreate(id);
    }

    @RequestMapping(value = "/hotel/edit/{id}", method = RequestMethod.POST)
    public ResultVo<Boolean> approveEdit(@PathVariable int id) {
        return managerService.approveHotelEdit(id);
    }

    @RequestMapping(value = "/check/complete/{id}",method = RequestMethod.POST)
    public ResultVo<Boolean> completeCheck(@PathVariable int id) {
        return managerService.completeCheckOutRecord(id);
    }

}
