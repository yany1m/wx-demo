package com.service;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaMediaServiceImpl;
import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.dto.WxMediaInfoResponse;
import com.dto.WxMediaPlaybackInfo;
import com.google.gson.JsonObject;
import lombok.SneakyThrows;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.json.GsonHelper;
import me.chanjar.weixin.common.util.json.GsonParser;
import org.apache.commons.lang.time.DateUtils;

import java.util.Date;

import static cn.binarywang.wx.miniapp.api.impl.WxMaImmediateDeliveryServiceImpl.ERR_CODE;

public class WxMediaService extends WxMaMediaServiceImpl {

    private WxMaService wxMaService;

    public WxMediaService(WxMaService wxMaService) {
        super(wxMaService);
        this.wxMaService = wxMaService;
    }

    @SneakyThrows
    public WxMediaInfoResponse listmedia(int drama_id) {
        JsonObject jsonObject = GsonHelper.buildJsonObject("drama_id", drama_id);
        String response = this.wxMaService.post("https://api.weixin.qq.com/wxa/sec/vod/listmedia", jsonObject);
        JsonObject respObj = GsonParser.parse(response);

        if (respObj.get(ERR_CODE).getAsInt() != 0) {
            throw new WxErrorException(WxError.fromJson(response, WxType.MiniApp));
        }

        return WxMaGsonBuilder.create().fromJson(response, WxMediaInfoResponse.class);
    }


    @SneakyThrows
    public WxMediaPlaybackInfo getmedialink(int media_id) {
        JsonObject jsonObject = GsonHelper.buildJsonObject("media_id", media_id, "t", DateUtils.addHours(new Date(), 2).getTime());
        String response = this.wxMaService.post("https://api.weixin.qq.com/wxa/sec/vod/getmedialink", jsonObject);
        JsonObject respObj = GsonParser.parse(response);

        if (respObj.get(ERR_CODE).getAsInt() != 0) {
            throw new WxErrorException(WxError.fromJson(response, WxType.MiniApp));
        }

        return WxMaGsonBuilder.create().fromJson(response, WxMediaPlaybackInfo.class);
    }
}
