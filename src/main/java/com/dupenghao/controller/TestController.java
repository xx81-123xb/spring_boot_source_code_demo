package com.dupenghao.controller;

import com.dupenghao.commons.Result;
import com.dupenghao.util.AppInstance;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 杜鹏豪 on 2022/8/25.
 */
@RestController
@RequestMapping("/api/Test")
@Api(tags = {"TestController"})
public class TestController {

    @ApiOperation("测试获取Person")
    @GetMapping("/getPerson")
    public Result getPerson(){
        Object dupenghao = AppInstance.getContext().getBean("dupenghao");
        return Result.getSuccess(dupenghao);
    }

}
