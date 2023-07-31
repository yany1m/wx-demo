package com.dto;

import cn.binarywang.wx.miniapp.bean.shop.response.WxMaShopBaseResponse;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class WxMediaInfoResponse extends WxMaShopBaseResponse {

    @SerializedName("media_info_list")
    private List<WxMediaInfoResponse.WxMediaInfoDto> mediaInfoDtoList;

    @Data
    public static class WxMediaInfoDto {

        @SerializedName("media_id")
        private int mediaId;

        @SerializedName("mp4_url")
        private String mp4Url;

        @SerializedName("name")
        private String name;

        @SerializedName("expire_time")
        private Long expireTime;
    }
}
