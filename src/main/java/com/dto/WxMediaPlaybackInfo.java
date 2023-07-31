package com.dto;

import cn.binarywang.wx.miniapp.bean.shop.response.WxMaShopBaseResponse;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class WxMediaPlaybackInfo extends WxMaShopBaseResponse {

    @SerializedName("media_id")
    private int mediaId;

    @SerializedName("mp4_url")
    private String mp4Url;

    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private String description;

    @SerializedName("cover_url")
    private String coverUrl;

    @SerializedName("hls_url")
    private String hlsUrl;

    @SerializedName("duration")
    private int duration;

}
