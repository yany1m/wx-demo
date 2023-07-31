package com.service;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dto.*;
import com.mapper.VideoNumberDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class WeiXinService extends ServiceImpl<VideoNumberDtoMapper, VideoNumDto> {

    @Autowired
    VideoNumberDtoMapper videoNumberDtoMapper;

    public void sync(int pid, int dramaId) {
        WxMaService wxService = getWxService(pid);
        WxMediaService wxMediaService = new WxMediaService(wxService);

        // 获取所有剧集
        WxMediaInfoResponse wxMediaInfoResponse = wxMediaService.listmedia(dramaId);

        // 循环获取url
        List<VideoNumDto> list = new ArrayList<>();
        for (WxMediaInfoResponse.WxMediaInfoDto wxMediaInfoDto : wxMediaInfoResponse.getMediaInfoDtoList()) {
            WxMediaPlaybackInfo wxMediaPlaybackInfo = wxMediaService.getmedialink(wxMediaInfoDto.getMediaId());
            VideoNumDto videoNumDto = new VideoNumDto();
            videoNumDto.setDramaId(dramaId);
            videoNumDto.setVideoNum(Integer.parseInt(getVideoNumByName(wxMediaPlaybackInfo.getName())));
            videoNumDto.setMediaId(wxMediaInfoDto.getMediaId());
            videoNumDto.setMp4Url(wxMediaPlaybackInfo.getMp4Url());
            videoNumDto.setExpiresTime(wxMediaInfoDto.getExpireTime());
            list.add(videoNumDto);
        }

        // 同步链接，批量插入
        List<WxApp> listApp = new WxApp().selectAll();
        for (WxApp wxApp : listApp) {
            insertOrUpdateBatch(list, wxApp, dramaId);
        }
    }

    private String getVideoNumByName(String name) {
        Pattern pattern = Pattern.compile("第(\\d+)集");
        Matcher matcher = pattern.matcher(name);

        if (matcher.find()) {
            return matcher.group(1);
        }

        throw new RuntimeException("正则匹配失败");
    }

    private void insertOrUpdateBatch(List<VideoNumDto> list, WxApp wxApp, int dramaId) {
        VideoDto videoDto = new VideoDto().selectOne(new QueryWrapper<VideoDto>().lambda().eq(VideoDto::getDramaId, dramaId).eq(VideoDto::getPid, wxApp.getPid()));

        // 批量查
        Map<Integer, VideoNumDto> map = videoNumberDtoMapper.selectList(new QueryWrapper<VideoNumDto>().lambda().eq(VideoNumDto::getDramaId, dramaId).eq(VideoNumDto::getPid, wxApp.getPid()))
                .stream()
                .collect(Collectors.toMap(VideoNumDto::getMediaId, Function.identity()));

        for (VideoNumDto videoNumDto : list) {
            if (map.get(videoNumDto.getMediaId()) != null) {
                videoNumDto.setId(map.get(videoNumDto.getMediaId()).getId());
            }
            videoNumDto.setVideoId(videoDto.getId());
            videoNumDto.setPid(wxApp.getPid());
        }

        this.saveOrUpdateBatch(list);
    }

    public WxMaService getWxService(int pid) {
        // todo 优先从redis中获取
        WxApp wp = new WxApp();
        wp.setPid(pid);
        WxApp wxApp = wp.selectById();

        WxMaService wxService = new cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl();
        WxMaDefaultConfigImpl wxMaConfig = new WxMaDefaultConfigImpl();
        wxMaConfig.setAppid(wxApp.getAppId());
        wxMaConfig.setSecret(wxApp.getAppSecret());
        wxService.addConfig(wxApp.getAppId(), wxMaConfig);

        return wxService;
    }

}
