package com.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

@Data
@TableName("mg_app")
public class WxApp extends Model<WxApp> {

    @TableId
    private int pid;
    private String appId;
    private String appSecret;
    private String accessToken;
    private Long expiresTime;

}
