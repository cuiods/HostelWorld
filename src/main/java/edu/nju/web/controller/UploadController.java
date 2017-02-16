package edu.nju.web.controller;

import edu.nju.bl.service.UploadService;
import edu.nju.bl.vo.OssPolicyVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Picture upload controller
 * @author cuihao
 */
@Api(value = "/upload", description = "Server signature API")
@RestController
@RequestMapping("/api/v1/upload")
public class UploadController {

    @Resource
    private UploadService uploadService;

    @ApiOperation(value = "Get user policy",notes = "Server oss signature for user upload picture",
            response = OssPolicyVo.class, produces = "application/json;charset=UTF-8")
    @GetMapping(value = "/user",produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public OssPolicyVo getUserPolicy() {
        return uploadService.getUserOssPolicy();
    }

    @ApiOperation(value = "Get room policy",notes = "Server oss signature for room upload picture",
            response = OssPolicyVo.class, produces = "application/json;charset=UTF-8")
    @GetMapping(value = "/room",produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public OssPolicyVo getRoomPolicy() {
        return uploadService.getRoomPolicy();
    }

}
