DROP TABLE IF EXISTS mg_app;
CREATE TABLE mg_app
(
	pid int(20) NOT NULL COMMENT '主键ID',
	app_id VARCHAR(40) NOT NULL COMMENT 'app_id',
	app_secret VARCHAR(40) NOT NULL COMMENT 'app_secret',
    access_token VARCHAR(255) NULL COMMENT 'access_token',
    expires_time int NULL COMMENT 'expires_time',
	PRIMARY KEY (pid)
);

DROP TABLE IF EXISTS mg_video1;
CREATE TABLE mg_video1
(
	id int(20) NOT NULL COMMENT 'id',
	pid int(20) NOT NULL COMMENT 'pid',
	drama_id int(40) NULL COMMENT 'drama_id',
	name VARCHAR(40) NULL COMMENT 'name',
    remark VARCHAR(255) NULL COMMENT 'remark',
	PRIMARY KEY (id)
);

DROP TABLE IF EXISTS mg_video2;
CREATE TABLE mg_video2
(
	id int(20) NOT NULL COMMENT 'id',
	pid int(20) NOT NULL COMMENT 'pid',
	drama_id int(40) NULL COMMENT 'drama_id',
	name VARCHAR(40) NULL COMMENT 'name',
    remark VARCHAR(255) NULL COMMENT 'remark',
	PRIMARY KEY (id)
);

DROP TABLE IF EXISTS mg_video3;
CREATE TABLE mg_video3
(
	id int(20) NOT NULL COMMENT 'id',
	pid int(20) NOT NULL COMMENT 'pid',
	drama_id int(40) NULL COMMENT 'drama_id',
	name VARCHAR(40) NULL COMMENT 'name',
    remark VARCHAR(255) NULL COMMENT 'remark',
	PRIMARY KEY (id)
);

DROP TABLE IF EXISTS mg_video_num1;
CREATE TABLE mg_video_num1
(
	id int(20) NOT NULL COMMENT 'id',
	pid int(20) NOT NULL COMMENT 'pid',
	drama_id int(40) NOT NULL COMMENT 'drama_id',
	video_id int(40) NOT NULL COMMENT 'video_id',
	video_num int(40) NOT NULL COMMENT 'video_num',
	media_id int(40) NOT NULL COMMENT 'media_id',
	mp4_url VARCHAR(40) NOT NULL COMMENT 'mp4_url',
    expires_time int NULL COMMENT 'expires_time',
	PRIMARY KEY (id)
);

DROP TABLE IF EXISTS mg_video_num2;
CREATE TABLE mg_video_num2
(
	id int(20) NOT NULL COMMENT 'id',
	pid int(20) NOT NULL COMMENT 'pid',
	drama_id int(40) NOT NULL COMMENT 'drama_id',
	video_id int(40) NOT NULL COMMENT 'video_id',
	video_num int(40) NOT NULL COMMENT 'video_num',
	media_id int(40) NOT NULL COMMENT 'media_id',
	mp4_url VARCHAR(40) NOT NULL COMMENT 'mp4_url',
    expires_time int NULL COMMENT 'expires_time',
	PRIMARY KEY (id)
);

DROP TABLE IF EXISTS mg_video_num3;
CREATE TABLE mg_video_num3
(
	id int(20) NOT NULL COMMENT 'id',
	pid int(20) NOT NULL COMMENT 'pid',
	drama_id int(40) NOT NULL COMMENT 'drama_id',
	video_id int(40) NOT NULL COMMENT 'video_id',
	video_num int(40) NOT NULL COMMENT 'video_num',
	media_id int(40) NOT NULL COMMENT 'media_id',
	mp4_url VARCHAR(40) NOT NULL COMMENT 'mp4_url',
    expires_time int NULL COMMENT 'expires_time',
	PRIMARY KEY (id)
);