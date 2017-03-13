package edu.nju.web.controller;

import edu.nju.bl.service.StatisticService;
import edu.nju.bl.vo.StatisticVo;
import edu.nju.bl.vo.WrapStatVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * stat info of hostel world
 * @author cuihao
 */
@Api(value = "/stat", description = "Statistic API")
@RestController
@RequestMapping("/api/v1/stat")
public class StatisticController {

    @Resource
    private StatisticService statisticService;

    @ApiOperation(value = "Get hotel statistic",notes = "Get hotel statistic",
            response = WrapStatVo.class, produces = "application/json;charset=UTF-8")
    @GetMapping(value = "/hotel/{id}",produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public WrapStatVo statHotel(@PathVariable int id) {
        return new WrapStatVo(statisticService.getHotelStatistic(id));
    }

    @ApiOperation(value = "Get all statistic",notes = "Get all statistic",
            response = WrapStatVo.class, produces = "application/json;charset=UTF-8")
    @GetMapping(value = "/all",produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public WrapStatVo statAll() {
        return new WrapStatVo(statisticService.getAllStatistic());
    }

}
