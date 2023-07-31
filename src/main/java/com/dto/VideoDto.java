package com.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

@Data
@TableName("mg_video")
public class VideoDto extends Model<VideoDto> {

    @TableId
    private int id;
    private int pid;
    private int dramaId;
    private String name;
    private String remark;

}
