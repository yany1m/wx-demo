package com.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.util.Date;

@Data
@TableName("mg_video_num")
public class VideoNumDto extends Model<VideoNumDto>  {

    @TableId
    private int id;
    private int pid;
    private int dramaId;
    private int videoId;
    private int videoNum;
    private int mediaId;
    private String mp4Url;
    private Long expiresTime;

}
