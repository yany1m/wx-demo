package com.controller;

import com.dto.R;
import com.dto.VideoDto;
import com.dto.WxApp;
import com.service.WeiXinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private WeiXinService weiXinService;

    @PostMapping("/wxapp/add")
    public R wxAppAdd(@RequestBody @Valid WxApp wxApp) {
        wxApp.insert();
        return R.ok();
    }

    @PostMapping("/drama/add")
    public R dramaAdd(@RequestBody @Valid VideoDto videoDto) {
        videoDto.insert();
        return R.ok();
    }

    @PostMapping("/video/sync")
    public R videoSync(@RequestParam("dramaId") int dramaId, @RequestParam("pid") int pid) {
        weiXinService.sync(pid, dramaId);
        return R.ok();
    }

}
