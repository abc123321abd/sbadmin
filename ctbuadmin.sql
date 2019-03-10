/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50724
Source Host           : localhost:3306
Source Database       : ctbuadmin

Target Server Type    : MYSQL
Target Server Version : 50724
File Encoding         : 65001

Date: 2019-03-10 16:45:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `sys_config`
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL COMMENT '名称',
  `type` int(11) DEFAULT NULL COMMENT '类型',
  `keyword` varchar(255) DEFAULT NULL COMMENT '键名',
  `text` varchar(255) DEFAULT NULL COMMENT '文本',
  `value` int(11) DEFAULT NULL COMMENT '数值',
  `memo` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES ('1', '配置信息测试', '101', 'GsmMsg', '测试', null, '测试1');

-- ----------------------------
-- Table structure for `sys_dept`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Id编号',
  `parent_id` int(11) NOT NULL COMMENT '父节点Id',
  `name` varchar(40) NOT NULL COMMENT '部门名称',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `is_deleted` int(11) unsigned zerofill DEFAULT '00000000000' COMMENT '是否被删除',
  `gmt_delete` datetime DEFAULT NULL COMMENT '删除时间',
  `delete_userid` bigint(11) DEFAULT NULL COMMENT '删除人的id',
  `memo` varchar(200) DEFAULT NULL COMMENT '备注',
  `address` varchar(200) DEFAULT NULL COMMENT '地址',
  `phone` varchar(50) DEFAULT NULL COMMENT '电话',
  `owner_id` bigint(11) DEFAULT '0' COMMENT '所有者id',
  `longtitude` varchar(20) DEFAULT NULL COMMENT '经度',
  `latitude` varchar(20) DEFAULT NULL COMMENT '纬度',
  `level` int(11) DEFAULT NULL COMMENT '等级',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('1', '0', '计信学院', '1', '00000000000', null, null, '', '重庆', '123456789', '0', '', '', null);
INSERT INTO `sys_dept` VALUES ('44', '1', '计算机实验中心', null, '00000000000', null, null, '', '', '', '0', '', '', null);
INSERT INTO `sys_dept` VALUES ('46', '44', 'test1', null, '00000000000', null, null, '', '', '', '0', '', '', null);

-- ----------------------------
-- Table structure for `sys_dict`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '标签名',
  `value` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '数据值',
  `type` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '类型',
  `description` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '描述',
  `sort` decimal(10,0) DEFAULT NULL COMMENT '排序（升序）',
  `parent_id` bigint(64) DEFAULT '0' COMMENT '父级编号',
  `create_by` int(64) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(64) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) COLLATE utf8_bin DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `sys_dict_value` (`value`),
  KEY `sys_dict_label` (`name`),
  KEY `sys_dict_del_flag` (`del_flag`)
) ENGINE=InnoDB AUTO_INCREMENT=123 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='字典表';

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES ('1', '正常', '0', 'del_flag', '删除标记', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('3', '显示', '1', 'show_hide', '显示/隐藏', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('4', '隐藏', '0', 'show_hide', '显示/隐藏', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('5', '是', '1', 'yes_no', '是/否', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('6', '否', '0', 'yes_no', '是/否', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('7', '红色', 'red', 'color', '颜色值', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('8', '绿色', 'green', 'color', '颜色值', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('9', '蓝色', 'blue', 'color', '颜色值', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('10', '黄色', 'yellow', 'color', '颜色值', '40', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('11', '橙色', 'orange', 'color', '颜色值', '50', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('12', '默认主题', 'default', 'theme', '主题方案', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('13', '天蓝主题', 'cerulean', 'theme', '主题方案', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('14', '橙色主题', 'readable', 'theme', '主题方案', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('15', '红色主题', 'united', 'theme', '主题方案', '40', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('16', 'Flat主题', 'flat', 'theme', '主题方案', '60', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('17', '国家', '1', 'sys_area_type', '区域类型', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('18', '省份、直辖市', '2', 'sys_area_type', '区域类型', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('19', '地市', '3', 'sys_area_type', '区域类型', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('20', '区县', '4', 'sys_area_type', '区域类型', '40', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('21', '公司', '1', 'sys_office_type', '机构类型', '60', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('22', '部门', '2', 'sys_office_type', '机构类型', '70', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('23', '小组', '3', 'sys_office_type', '机构类型', '80', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('24', '其它', '4', 'sys_office_type', '机构类型', '90', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('25', '综合部', '1', 'sys_office_common', '快捷通用部门', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('26', '开发部', '2', 'sys_office_common', '快捷通用部门', '40', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('27', '人力部', '3', 'sys_office_common', '快捷通用部门', '50', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('28', '一级', '1', 'sys_office_grade', '机构等级', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('29', '二级', '2', 'sys_office_grade', '机构等级', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('30', '三级', '3', 'sys_office_grade', '机构等级', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('31', '四级', '4', 'sys_office_grade', '机构等级', '40', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('32', '所有数据', '1', 'sys_data_scope', '数据范围', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('33', '所在公司及以下数据', '2', 'sys_data_scope', '数据范围', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('34', '所在公司数据', '3', 'sys_data_scope', '数据范围', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('35', '所在部门及以下数据', '4', 'sys_data_scope', '数据范围', '40', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('36', '所在部门数据', '5', 'sys_data_scope', '数据范围', '50', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('37', '仅本人数据', '8', 'sys_data_scope', '数据范围', '90', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('38', '按明细设置', '9', 'sys_data_scope', '数据范围', '100', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('39', '系统管理', '1', 'sys_user_type', '用户类型', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('40', '部门经理', '2', 'sys_user_type', '用户类型', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('41', '普通用户', '3', 'sys_user_type', '用户类型', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('42', '基础主题', 'basic', 'cms_theme', '站点主题', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('43', '蓝色主题', 'blue', 'cms_theme', '站点主题', '20', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('44', '红色主题', 'red', 'cms_theme', '站点主题', '30', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('45', '文章模型', 'article', 'cms_module', '栏目模型', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('46', '图片模型', 'picture', 'cms_module', '栏目模型', '20', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('47', '下载模型', 'download', 'cms_module', '栏目模型', '30', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('48', '链接模型', 'link', 'cms_module', '栏目模型', '40', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('49', '专题模型', 'special', 'cms_module', '栏目模型', '50', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('50', '默认展现方式', '0', 'cms_show_modes', '展现方式', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('51', '首栏目内容列表', '1', 'cms_show_modes', '展现方式', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('52', '栏目第一条内容', '2', 'cms_show_modes', '展现方式', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('53', '发布', '0', 'cms_del_flag', '内容状态', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('54', '删除', '1', 'cms_del_flag', '内容状态', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('55', '审核', '2', 'cms_del_flag', '内容状态', '15', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('56', '首页焦点图', '1', 'cms_posid', '推荐位', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('57', '栏目页文章推荐', '2', 'cms_posid', '推荐位', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('58', '咨询', '1', 'cms_guestbook', '留言板分类', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('59', '建议', '2', 'cms_guestbook', '留言板分类', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('60', '投诉', '3', 'cms_guestbook', '留言板分类', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('61', '其它', '4', 'cms_guestbook', '留言板分类', '40', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('62', '公休', '1', 'oa_leave_type', '请假类型', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('63', '病假', '2', 'oa_leave_type', '请假类型', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('64', '事假', '3', 'oa_leave_type', '请假类型', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('65', '调休', '4', 'oa_leave_type', '请假类型', '40', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('66', '婚假', '5', 'oa_leave_type', '请假类型', '60', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('67', '接入日志', '1', 'sys_log_type', '日志类型', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('68', '异常日志', '2', 'sys_log_type', '日志类型', '40', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('69', '请假流程', 'leave', 'act_type', '流程类型', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('70', '审批测试流程', 'test_audit', 'act_type', '流程类型', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('71', '分类1', '1', 'act_category', '流程分类', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('72', '分类2', '2', 'act_category', '流程分类', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('73', '增删改查', 'crud', 'gen_category', '代码生成分类', '10', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('74', '增删改查（包含从表）', 'crud_many', 'gen_category', '代码生成分类', '20', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('75', '树结构', 'tree', 'gen_category', '代码生成分类', '30', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('76', '=', '=', 'gen_query_type', '查询方式', '10', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('77', '!=', '!=', 'gen_query_type', '查询方式', '20', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('78', '&gt;', '&gt;', 'gen_query_type', '查询方式', '30', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('79', '&lt;', '&lt;', 'gen_query_type', '查询方式', '40', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('80', 'Between', 'between', 'gen_query_type', '查询方式', '50', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('81', 'Like', 'like', 'gen_query_type', '查询方式', '60', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('82', 'Left Like', 'left_like', 'gen_query_type', '查询方式', '70', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('83', 'Right Like', 'right_like', 'gen_query_type', '查询方式', '80', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('84', '文本框', 'input', 'gen_show_type', '字段生成方案', '10', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('85', '文本域', 'textarea', 'gen_show_type', '字段生成方案', '20', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('86', '下拉框', 'select', 'gen_show_type', '字段生成方案', '30', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('87', '复选框', 'checkbox', 'gen_show_type', '字段生成方案', '40', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('88', '单选框', 'radiobox', 'gen_show_type', '字段生成方案', '50', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('89', '日期选择', 'dateselect', 'gen_show_type', '字段生成方案', '60', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('90', '人员选择', 'userselect', 'gen_show_type', '字段生成方案', '70', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('91', '部门选择', 'officeselect', 'gen_show_type', '字段生成方案', '80', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('92', '区域选择', 'areaselect', 'gen_show_type', '字段生成方案', '90', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('93', 'String', 'String', 'gen_java_type', 'Java类型', '10', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('94', 'Long', 'Long', 'gen_java_type', 'Java类型', '20', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('95', '仅持久层', 'dao', 'gen_category', '代码生成分类', '40', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('96', '男', '1', 'sex', '性别', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('97', '女', '2', 'sex', '性别', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('98', 'Integer', 'Integer', 'gen_java_type', 'Java类型', '30', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('99', 'Double', 'Double', 'gen_java_type', 'Java类型', '40', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('100', 'Date', 'java.util.Date', 'gen_java_type', 'Java类型', '50', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('104', 'Custom', 'Custom', 'gen_java_type', 'Java类型', '90', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('105', '会议通告', '1', 'oa_notify_type', '通知通告类型', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('106', '奖惩通告', '2', 'oa_notify_type', '通知通告类型', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('107', '活动通告', '3', 'oa_notify_type', '通知通告类型', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('108', '草稿', '0', 'oa_notify_status', '通知通告状态', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('109', '发布', '1', 'oa_notify_status', '通知通告状态', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('110', '未读', '0', 'oa_notify_read', '通知通告状态', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('111', '已读', '1', 'oa_notify_read', '通知通告状态', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('112', '草稿', '0', 'oa_notify_status', '通知通告状态', '10', '0', '1', null, '1', null, '', '0');
INSERT INTO `sys_dict` VALUES ('113', '删除', '0', 'del_flag', '删除标记', null, null, null, null, null, null, '', '');
INSERT INTO `sys_dict` VALUES ('118', '关于', 'about', 'blog_type', '博客类型', null, null, null, null, null, null, '全url是:/blog/open/page/about', '');
INSERT INTO `sys_dict` VALUES ('119', '交流', 'communication', 'blog_type', '博客类型', null, null, null, null, null, null, '', '');
INSERT INTO `sys_dict` VALUES ('120', '文章', 'article', 'blog_type', '博客类型', null, null, null, null, null, null, '', '');
INSERT INTO `sys_dict` VALUES ('121', '编码', 'code', 'hobby', '爱好', null, null, null, null, null, null, '', '');
INSERT INTO `sys_dict` VALUES ('122', '绘画', 'painting', 'hobby', '爱好', null, null, null, null, null, null, '', '');

-- ----------------------------
-- Table structure for `sys_file`
-- ----------------------------
DROP TABLE IF EXISTS `sys_file`;
CREATE TABLE `sys_file` (
  `id` bigint(20) NOT NULL,
  `type` int(11) DEFAULT NULL,
  `url` varchar(500) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_file
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_log`
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `operation` varchar(50) DEFAULT NULL COMMENT '用户操作',
  `time` int(11) DEFAULT NULL COMMENT '响应时间',
  `method` varchar(200) DEFAULT NULL COMMENT '请求方法',
  `params` varchar(5000) DEFAULT NULL COMMENT '请求参数',
  `ip` varchar(64) DEFAULT NULL COMMENT 'IP地址',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8 COMMENT='系统日志';

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES ('26', '1', 'admin', 'error', null, 'http://localhost/webapi/system/config/save', 'org.springframework.validation.BindException: org.springframework.validation.BeanPropertyBindingResult: 1 errors\nField error in object \'configDO\' on field \'type\': rejected value [割发代首]; codes [typeMismatch.configDO.type,typeMismatch.type,typeMismatch.java.lang.Integer,typeMismatch]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [configDO.type,type]; arguments []; default message [type]]; default message [Failed to convert property value of type \'java.lang.String\' to required type \'java.lang.Integer\' for property \'type\'; nested exception is java.lang.NumberFormatException: For input string: \"割发代首\"]', null, '2019-03-10 11:52:53');
INSERT INTO `sys_log` VALUES ('27', '1', 'admin', 'error', null, 'http://localhost/webapi/system/role/update', 'org.apache.ibatis.binding.BindingException: Invalid bound statement (not found): cn.edu.ctbu.sbadmin.system.dao.RoleMenuDao.batchSave', null, '2019-03-10 16:12:47');
INSERT INTO `sys_log` VALUES ('28', '1', 'admin', 'error', null, 'http://localhost/webapi/system/role/update', 'org.apache.ibatis.binding.BindingException: Invalid bound statement (not found): cn.edu.ctbu.sbadmin.system.dao.RoleMenuDao.batchSave', null, '2019-03-10 16:16:45');
INSERT INTO `sys_log` VALUES ('29', '1', 'admin', 'error', null, 'http://localhost/webapi/system/role/update', 'org.apache.ibatis.binding.BindingException: Invalid bound statement (not found): cn.edu.ctbu.sbadmin.system.dao.RoleMenuDao.batchSave', null, '2019-03-10 16:17:07');
INSERT INTO `sys_log` VALUES ('30', '1', 'admin', 'error', null, 'http://localhost/webapi/system/menu/rolemenus', 'org.springframework.web.bind.MissingServletRequestParameterException: Required Integer[] parameter \'ids[]\' is not present', null, '2019-03-10 16:20:30');
INSERT INTO `sys_log` VALUES ('31', '1', 'admin', 'error', null, 'http://localhost/webapi/system/menu/rolemenus', 'java.lang.ArrayStoreException', null, '2019-03-10 16:20:45');
INSERT INTO `sys_log` VALUES ('32', '1', 'admin', 'error', null, 'http://localhost/webapi/system/menu/rolemenus', 'java.lang.ArrayStoreException', null, '2019-03-10 16:20:54');

-- ----------------------------
-- Table structure for `sys_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `parent_id` int(20) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(500) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` int(11) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `is_deleted` int(11) unsigned zerofill DEFAULT '00000000000' COMMENT '是否被删除',
  `gmt_delete` datetime DEFAULT NULL COMMENT '删除时间',
  `delete_userid` bigint(11) DEFAULT NULL COMMENT '删除人的id',
  `level` int(11) DEFAULT NULL COMMENT '等级',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=130 DEFAULT CHARSET=utf8 COMMENT='菜单管理';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '0', '基础管理', 'test', 'a:b:c', '0', 'fa fa-bars', '0', '2017-08-09 22:49:47', '2018-11-19 14:04:16', '00000000000', null, null, '1');
INSERT INTO `sys_menu` VALUES ('2', '3', '系统菜单', '/system/menu/list', 'system:menu:menu', '1', 'fa fa-th-list', '2', '2017-08-09 22:55:15', null, '00000000000', null, null, '2');
INSERT INTO `sys_menu` VALUES ('3', '0', '系统管理', '', '', '0', 'fa fa-desktop', '1', '2017-08-09 23:06:55', '2018-11-16 21:56:20', '00000000000', null, null, '1');
INSERT INTO `sys_menu` VALUES ('6', '3', '用户管理', '/system/user/list', 'system:user:user', '1', 'fa fa-user', '0', '2017-08-10 14:12:11', null, '00000000000', null, null, '2');
INSERT INTO `sys_menu` VALUES ('7', '3', '角色管理', '/system/role/list', 'system:role:role', '1', 'fa fa-paw', '1', '2017-08-10 14:13:19', null, '00000000000', null, null, '2');
INSERT INTO `sys_menu` VALUES ('12', '6', '新增', '', 'system:user:add', '2', '', '0', '2017-08-14 10:51:35', null, '00000000000', null, null, '3');
INSERT INTO `sys_menu` VALUES ('13', '6', '编辑', '', 'system:user:edit', '2', '', '0', '2017-08-14 10:52:06', null, '00000000000', null, null, null);
INSERT INTO `sys_menu` VALUES ('14', '6', '删除', null, 'system:user:remove', '2', null, '0', '2017-08-14 10:52:24', null, '00000000000', null, null, null);
INSERT INTO `sys_menu` VALUES ('15', '7', '新增', '', 'system:role:add', '2', '', '0', '2017-08-14 10:56:37', null, '00000000000', null, null, null);
INSERT INTO `sys_menu` VALUES ('20', '2', '新增', '', 'system:menu:add', '2', '', '0', '2017-08-14 10:59:32', null, '00000000000', null, null, null);
INSERT INTO `sys_menu` VALUES ('21', '2', '编辑', '', 'system:menu:edit', '2', '', '0', '2017-08-14 10:59:56', null, '00000000000', null, null, null);
INSERT INTO `sys_menu` VALUES ('22', '2', '删除', '', 'system:menu:remove', '2', '', '0', '2017-08-14 11:00:26', null, '00000000000', null, null, null);
INSERT INTO `sys_menu` VALUES ('24', '6', '批量删除', '', 'system:user:batchRemove', '2', '', '0', '2017-08-14 17:27:18', null, '00000000000', null, null, null);
INSERT INTO `sys_menu` VALUES ('25', '6', '停用', null, 'system:user:disable', '2', null, '0', '2017-08-14 17:27:43', null, '00000000000', null, null, null);
INSERT INTO `sys_menu` VALUES ('26', '6', '重置密码', '', 'system:user:resetPwd', '2', '', '0', '2017-08-14 17:28:34', null, '00000000000', null, null, null);
INSERT INTO `sys_menu` VALUES ('27', '91', '系统日志', '/system/log/list', 'system:log:log', '1', 'fa fa-warning', '0', '2017-08-14 22:11:53', '2018-11-17 20:00:33', '00000000000', null, null, '2');
INSERT INTO `sys_menu` VALUES ('28', '27', '刷新', '', 'system:log:list', '2', '', '0', '2017-08-14 22:30:22', '2018-11-17 20:01:00', '00000000000', null, null, '3');
INSERT INTO `sys_menu` VALUES ('29', '27', '删除', '', 'system:log:remove', '2', '', '0', '2017-08-14 22:30:43', '2018-11-17 20:01:19', '00000000000', null, null, '3');
INSERT INTO `sys_menu` VALUES ('30', '27', '清空', '', 'system:log:clear', '2', '', '0', '2017-08-14 22:31:02', '2018-11-17 20:01:32', '00000000000', null, null, '3');
INSERT INTO `sys_menu` VALUES ('49', '0', '博客管理', '', '', '0', 'fa fa-rss', '6', null, null, '00000000000', null, null, '1');
INSERT INTO `sys_menu` VALUES ('50', '49', '文章列表', 'blog/bContent', 'blog:bContent:bContent', '1', 'fa fa-file-image-o', '1', null, null, '00000000000', null, null, null);
INSERT INTO `sys_menu` VALUES ('51', '50', '新增', '', 'blog:bContent:add', '2', '', null, null, null, '00000000000', null, null, null);
INSERT INTO `sys_menu` VALUES ('55', '7', '编辑', '', 'system:role:edit', '2', '', null, null, null, '00000000000', null, null, null);
INSERT INTO `sys_menu` VALUES ('56', '7', '删除', '', 'system:role:remove', '2', null, null, null, null, '00000000000', null, null, null);
INSERT INTO `sys_menu` VALUES ('57', '91', '运行监控', '/druid/index.html', '', '1', 'fa fa-caret-square-o-right', '1', null, null, '00000000000', null, null, null);
INSERT INTO `sys_menu` VALUES ('58', '50', '编辑', '', 'blog:bContent:edit', '2', null, null, null, null, '00000000000', null, null, null);
INSERT INTO `sys_menu` VALUES ('59', '50', '删除', '', 'blog:bContent:remove', '2', null, null, null, null, '00000000000', null, null, null);
INSERT INTO `sys_menu` VALUES ('60', '50', '批量删除', '', 'blog:bContent:batchRemove', '2', null, null, null, null, '00000000000', null, null, null);
INSERT INTO `sys_menu` VALUES ('61', '2', '批量删除', '', 'system:menu:batchRemove', '2', '', null, null, null, '00000000000', null, null, '3');
INSERT INTO `sys_menu` VALUES ('62', '7', '批量删除', '', 'system:role:batchRemove', '2', '', null, null, null, '00000000000', null, null, '3');
INSERT INTO `sys_menu` VALUES ('68', '49', '发布文章', '/blog/bContent/add', 'blog:bContent:add', '1', 'fa fa-edit', '0', null, null, '00000000000', null, null, null);
INSERT INTO `sys_menu` VALUES ('71', '1', '文件管理', '/common/sysFile', 'common:sysFile:sysFile', '1', 'fa fa-folder-open', '2', null, '2018-11-17 14:27:44', '00000000000', null, null, '2');
INSERT INTO `sys_menu` VALUES ('72', '77', '计划任务', 'common/job', 'common:taskScheduleJob', '1', 'fa fa-hourglass-1', '4', null, null, '00000000000', null, null, null);
INSERT INTO `sys_menu` VALUES ('73', '3', '部门管理', '/system/dept/list', 'system:dept:dept', '1', 'fa fa-users', '3', null, null, '00000000000', null, null, '2');
INSERT INTO `sys_menu` VALUES ('74', '73', '增加', '', 'system:dept:add', '2', 'fa', '1', null, null, '00000000000', null, null, '3');
INSERT INTO `sys_menu` VALUES ('75', '73', '刪除', '', 'system:dept:remove', '2', '', '2', null, null, '00000000000', null, null, '3');
INSERT INTO `sys_menu` VALUES ('76', '73', '编辑', '', 'system:dept:edit', '2', '', '3', null, null, '00000000000', null, null, '3');
INSERT INTO `sys_menu` VALUES ('77', '0', '系统工具', '', '', '0', 'fa fa-gear', '4', null, null, '00000000000', null, null, '1');
INSERT INTO `sys_menu` VALUES ('78', '1', '数据字典', '/common/sysDict', 'common:sysDict:sysDict', '1', 'fa fa-book', '1', null, null, '00000000000', null, null, '2');
INSERT INTO `sys_menu` VALUES ('79', '78', '增加', '/common/sysDict/add', 'common:sysDict:add', '2', '', '2', null, null, '00000000000', null, null, '3');
INSERT INTO `sys_menu` VALUES ('80', '78', '编辑', '/common/sysDict/edit', 'common:sysDict:edit', '2', '', '2', null, null, '00000000000', null, null, '3');
INSERT INTO `sys_menu` VALUES ('81', '78', '删除', '/common/sysDict/remove', 'common:sysDict:remove', '2', '', '3', null, null, '00000000000', null, null, '3');
INSERT INTO `sys_menu` VALUES ('83', '78', '批量删除', '/common/sysDict/batchRemove', 'common:sysDict:batchRemove', '2', '', '4', null, null, '00000000000', null, null, '3');
INSERT INTO `sys_menu` VALUES ('84', '0', '办公管理', '', '', '0', 'fa fa-laptop', '5', null, null, '00000000000', null, null, '1');
INSERT INTO `sys_menu` VALUES ('85', '84', '通知公告', 'oa/notify', 'oa:notify:notify', '1', 'fa fa-pencil-square', null, null, null, '00000000000', null, null, null);
INSERT INTO `sys_menu` VALUES ('86', '85', '新增', 'oa/notify/add', 'oa:notify:add', '2', 'fa fa-plus', '1', null, null, '00000000000', null, null, null);
INSERT INTO `sys_menu` VALUES ('87', '85', '编辑', 'oa/notify/edit', 'oa:notify:edit', '2', 'fa fa-pencil-square-o', '2', null, null, '00000000000', null, null, null);
INSERT INTO `sys_menu` VALUES ('88', '85', '删除', 'oa/notify/remove', 'oa:notify:remove', '2', 'fa fa-minus', null, null, null, '00000000000', null, null, null);
INSERT INTO `sys_menu` VALUES ('89', '85', '批量删除', 'oa/notify/batchRemove', 'oa:notify:batchRemove', '2', '', null, null, null, '00000000000', null, null, null);
INSERT INTO `sys_menu` VALUES ('90', '84', '我的通知', 'oa/notify/selfNotify', '', '1', 'fa fa-envelope-square', null, null, null, '00000000000', null, null, null);
INSERT INTO `sys_menu` VALUES ('91', '0', '系统监控', '', '', '0', 'fa fa-video-camera', '5', null, null, '00000000000', null, null, '1');
INSERT INTO `sys_menu` VALUES ('92', '91', '在线用户', 'sys/online', '', '1', 'fa fa-user', null, null, null, '00000000000', null, null, null);
INSERT INTO `sys_menu` VALUES ('104', '77', 'swagger', '/swagger-ui.html', '', '1', '', null, null, null, '00000000000', null, null, null);
INSERT INTO `sys_menu` VALUES ('108', '0', 'test', '', '', '0', '', '100', null, null, '00000000001', null, null, '1');
INSERT INTO `sys_menu` VALUES ('109', '108', 'test1', '', '', '1', '', null, null, null, '00000000001', null, null, null);
INSERT INTO `sys_menu` VALUES ('110', '109', 'test2', '', '', '1', '', null, null, null, '00000000001', null, null, null);
INSERT INTO `sys_menu` VALUES ('112', '73', '批量删除', '', 'system:dept:batchRemove', '2', '', null, null, null, '00000000000', null, null, '3');
INSERT INTO `sys_menu` VALUES ('115', '1', '测试', '', '', null, '', null, null, null, '00000000001', null, null, '2');
INSERT INTO `sys_menu` VALUES ('116', '27', '批量删除', '', 'system:log:batchRemove', '2', '', null, '2018-11-17 20:02:36', null, '00000000000', null, null, '3');
INSERT INTO `sys_menu` VALUES ('117', '27', '新增', '', 'system:log:add', '2', '', null, '2018-11-17 20:06:33', null, '00000000000', null, null, '3');
INSERT INTO `sys_menu` VALUES ('118', '27', '编辑', '', 'system:log:edit', '2', '', null, '2018-11-17 20:07:43', null, '00000000000', null, null, '3');
INSERT INTO `sys_menu` VALUES ('119', '3', '配置管理', '/system/config/list', 'system:config:config', '1', 'fa fa-wrench', '4', '2018-11-19 15:31:07', '2018-11-19 15:33:59', '00000000000', null, null, '2');
INSERT INTO `sys_menu` VALUES ('120', '119', '新增', '', 'system:config:add', '2', '', null, '2018-11-19 15:34:30', null, '00000000000', null, null, '3');
INSERT INTO `sys_menu` VALUES ('121', '119', '编辑', '', 'system:config:edit', '2', '', null, '2018-11-19 15:34:59', null, '00000000000', null, null, '3');
INSERT INTO `sys_menu` VALUES ('122', '119', '刪除', '', 'system:config:remove', '2', '', null, '2018-11-19 15:35:22', '2018-11-19 15:35:42', '00000000000', null, null, '3');
INSERT INTO `sys_menu` VALUES ('123', '119', '批量删除', '', 'system:config:batchRemove', '2', '', null, '2018-11-19 15:35:58', null, '00000000000', null, null, '3');
INSERT INTO `sys_menu` VALUES ('124', '119', '导出Excel', '', 'system:config:excel', '2', '', null, '2018-11-19 17:55:07', null, '00000000000', null, null, '3');
INSERT INTO `sys_menu` VALUES ('125', '6', '导出Excel', '', 'system:user:excel', '2', '', null, '2018-11-19 17:55:56', null, '00000000000', null, null, '3');
INSERT INTO `sys_menu` VALUES ('126', '7', '导出Excel', '', 'system:role:excel', '2', '', null, '2018-11-19 17:56:32', null, '00000000000', null, null, '3');
INSERT INTO `sys_menu` VALUES ('127', '2', '导出Excel', '', 'system:menu:excel', '2', '', null, '2018-11-19 17:57:08', null, '00000000000', null, null, '3');
INSERT INTO `sys_menu` VALUES ('128', '73', ' 导出Excel', '', 'system:dept:excel', '2', '', null, '2018-11-19 17:57:46', null, '00000000000', null, null, '3');
INSERT INTO `sys_menu` VALUES ('129', '27', '导出Excel', '', 'system:log:excel', '2', '', null, '2018-11-19 17:58:43', null, '00000000000', null, null, '3');

-- ----------------------------
-- Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `role_sign` varchar(100) DEFAULT NULL COMMENT '角色标识',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `user_id_create` bigint(255) DEFAULT NULL COMMENT '创建用户id',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '创建时间',
  `is_deleted` int(11) DEFAULT '0' COMMENT '是否被删除',
  `gmt_delete` datetime DEFAULT NULL COMMENT '删除时间',
  `delete_userid` bigint(11) DEFAULT NULL COMMENT '删除人的id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=88 DEFAULT CHARSET=utf8 COMMENT='角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'admin', 'admin', '超级管理员', '1', '2018-02-01 00:00:00', '2019-03-10 16:20:54', '0', null, null);
INSERT INTO `sys_role` VALUES ('62', 'test', 'test', '测试', null, null, '2018-11-19 17:08:34', '0', null, null);
INSERT INTO `sys_role` VALUES ('87', 'sell', 'sell', '销售', '1', '2018-11-16 09:32:09', '2019-03-10 16:20:30', '0', null, null);

-- ----------------------------
-- Table structure for `sys_role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL DEFAULT '0' COMMENT '角色编号',
  `menu_id` int(11) NOT NULL DEFAULT '0' COMMENT '菜单编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4794 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('3610', '61', '26');
INSERT INTO `sys_role_menu` VALUES ('3611', '61', '25');
INSERT INTO `sys_role_menu` VALUES ('3612', '61', '24');
INSERT INTO `sys_role_menu` VALUES ('3613', '61', '14');
INSERT INTO `sys_role_menu` VALUES ('3614', '61', '13');
INSERT INTO `sys_role_menu` VALUES ('3615', '61', '12');
INSERT INTO `sys_role_menu` VALUES ('3616', '61', '61');
INSERT INTO `sys_role_menu` VALUES ('3617', '61', '22');
INSERT INTO `sys_role_menu` VALUES ('3618', '61', '21');
INSERT INTO `sys_role_menu` VALUES ('3619', '61', '20');
INSERT INTO `sys_role_menu` VALUES ('3620', '61', '6');
INSERT INTO `sys_role_menu` VALUES ('3621', '61', '2');
INSERT INTO `sys_role_menu` VALUES ('3675', '69', '49');
INSERT INTO `sys_role_menu` VALUES ('3676', '69', '60');
INSERT INTO `sys_role_menu` VALUES ('3677', '69', '59');
INSERT INTO `sys_role_menu` VALUES ('3678', '69', '58');
INSERT INTO `sys_role_menu` VALUES ('3679', '69', '51');
INSERT INTO `sys_role_menu` VALUES ('3680', '69', '68');
INSERT INTO `sys_role_menu` VALUES ('3681', '69', '50');
INSERT INTO `sys_role_menu` VALUES ('3682', '69', '3');
INSERT INTO `sys_role_menu` VALUES ('3683', '69', '76');
INSERT INTO `sys_role_menu` VALUES ('3684', '69', '75');
INSERT INTO `sys_role_menu` VALUES ('3685', '69', '74');
INSERT INTO `sys_role_menu` VALUES ('3686', '69', '62');
INSERT INTO `sys_role_menu` VALUES ('3687', '69', '56');
INSERT INTO `sys_role_menu` VALUES ('3688', '69', '55');
INSERT INTO `sys_role_menu` VALUES ('3689', '69', '15');
INSERT INTO `sys_role_menu` VALUES ('3690', '69', '26');
INSERT INTO `sys_role_menu` VALUES ('3691', '69', '25');
INSERT INTO `sys_role_menu` VALUES ('3692', '69', '24');
INSERT INTO `sys_role_menu` VALUES ('3693', '69', '14');
INSERT INTO `sys_role_menu` VALUES ('3694', '69', '13');
INSERT INTO `sys_role_menu` VALUES ('3695', '69', '12');
INSERT INTO `sys_role_menu` VALUES ('3696', '69', '61');
INSERT INTO `sys_role_menu` VALUES ('3697', '69', '22');
INSERT INTO `sys_role_menu` VALUES ('3698', '69', '21');
INSERT INTO `sys_role_menu` VALUES ('3699', '69', '20');
INSERT INTO `sys_role_menu` VALUES ('3700', '69', '73');
INSERT INTO `sys_role_menu` VALUES ('3701', '69', '7');
INSERT INTO `sys_role_menu` VALUES ('3702', '69', '6');
INSERT INTO `sys_role_menu` VALUES ('3703', '69', '2');
INSERT INTO `sys_role_menu` VALUES ('3726', '85', '76');
INSERT INTO `sys_role_menu` VALUES ('3727', '85', '75');
INSERT INTO `sys_role_menu` VALUES ('3728', '85', '74');
INSERT INTO `sys_role_menu` VALUES ('3729', '85', '73');
INSERT INTO `sys_role_menu` VALUES ('3747', '63', '3');
INSERT INTO `sys_role_menu` VALUES ('3748', '63', '76');
INSERT INTO `sys_role_menu` VALUES ('3749', '63', '75');
INSERT INTO `sys_role_menu` VALUES ('3750', '63', '74');
INSERT INTO `sys_role_menu` VALUES ('3751', '63', '62');
INSERT INTO `sys_role_menu` VALUES ('3752', '63', '56');
INSERT INTO `sys_role_menu` VALUES ('3753', '63', '55');
INSERT INTO `sys_role_menu` VALUES ('3754', '63', '15');
INSERT INTO `sys_role_menu` VALUES ('3755', '63', '26');
INSERT INTO `sys_role_menu` VALUES ('3756', '63', '25');
INSERT INTO `sys_role_menu` VALUES ('3757', '63', '24');
INSERT INTO `sys_role_menu` VALUES ('3758', '63', '14');
INSERT INTO `sys_role_menu` VALUES ('3759', '63', '13');
INSERT INTO `sys_role_menu` VALUES ('3760', '63', '12');
INSERT INTO `sys_role_menu` VALUES ('3761', '63', '61');
INSERT INTO `sys_role_menu` VALUES ('3762', '63', '22');
INSERT INTO `sys_role_menu` VALUES ('3763', '63', '21');
INSERT INTO `sys_role_menu` VALUES ('3764', '63', '20');
INSERT INTO `sys_role_menu` VALUES ('3765', '63', '73');
INSERT INTO `sys_role_menu` VALUES ('3766', '63', '7');
INSERT INTO `sys_role_menu` VALUES ('3767', '63', '6');
INSERT INTO `sys_role_menu` VALUES ('3768', '63', '2');
INSERT INTO `sys_role_menu` VALUES ('3769', '64', '3');
INSERT INTO `sys_role_menu` VALUES ('3770', '64', '76');
INSERT INTO `sys_role_menu` VALUES ('3771', '64', '75');
INSERT INTO `sys_role_menu` VALUES ('3772', '64', '74');
INSERT INTO `sys_role_menu` VALUES ('3773', '64', '62');
INSERT INTO `sys_role_menu` VALUES ('3774', '64', '56');
INSERT INTO `sys_role_menu` VALUES ('3775', '64', '55');
INSERT INTO `sys_role_menu` VALUES ('3776', '64', '15');
INSERT INTO `sys_role_menu` VALUES ('3777', '64', '26');
INSERT INTO `sys_role_menu` VALUES ('3778', '64', '25');
INSERT INTO `sys_role_menu` VALUES ('3779', '64', '24');
INSERT INTO `sys_role_menu` VALUES ('3780', '64', '14');
INSERT INTO `sys_role_menu` VALUES ('3781', '64', '13');
INSERT INTO `sys_role_menu` VALUES ('3782', '64', '12');
INSERT INTO `sys_role_menu` VALUES ('3783', '64', '61');
INSERT INTO `sys_role_menu` VALUES ('3784', '64', '22');
INSERT INTO `sys_role_menu` VALUES ('3785', '64', '21');
INSERT INTO `sys_role_menu` VALUES ('3786', '64', '20');
INSERT INTO `sys_role_menu` VALUES ('3787', '64', '73');
INSERT INTO `sys_role_menu` VALUES ('3788', '64', '7');
INSERT INTO `sys_role_menu` VALUES ('3789', '64', '6');
INSERT INTO `sys_role_menu` VALUES ('3790', '64', '2');
INSERT INTO `sys_role_menu` VALUES ('3791', '86', '60');
INSERT INTO `sys_role_menu` VALUES ('3792', '86', '59');
INSERT INTO `sys_role_menu` VALUES ('3793', '86', '58');
INSERT INTO `sys_role_menu` VALUES ('3794', '86', '51');
INSERT INTO `sys_role_menu` VALUES ('3795', '86', '50');
INSERT INTO `sys_role_menu` VALUES ('3798', '66', '0');
INSERT INTO `sys_role_menu` VALUES ('3799', '66', '1');
INSERT INTO `sys_role_menu` VALUES ('3800', '66', '78');
INSERT INTO `sys_role_menu` VALUES ('3801', '66', '79');
INSERT INTO `sys_role_menu` VALUES ('3802', '66', '80');
INSERT INTO `sys_role_menu` VALUES ('3803', '66', '81');
INSERT INTO `sys_role_menu` VALUES ('3804', '66', '83');
INSERT INTO `sys_role_menu` VALUES ('3805', '66', '71');
INSERT INTO `sys_role_menu` VALUES ('3896', '65', '60');
INSERT INTO `sys_role_menu` VALUES ('3897', '65', '59');
INSERT INTO `sys_role_menu` VALUES ('3898', '65', '58');
INSERT INTO `sys_role_menu` VALUES ('3899', '65', '50');
INSERT INTO `sys_role_menu` VALUES ('3900', '65', '68');
INSERT INTO `sys_role_menu` VALUES ('3901', '65', '49');
INSERT INTO `sys_role_menu` VALUES ('3902', '65', '71');
INSERT INTO `sys_role_menu` VALUES ('3903', '65', '78');
INSERT INTO `sys_role_menu` VALUES ('3904', '65', '1');
INSERT INTO `sys_role_menu` VALUES ('3905', '65', '0');
INSERT INTO `sys_role_menu` VALUES ('4571', '87', '98');
INSERT INTO `sys_role_menu` VALUES ('4572', '87', '97');
INSERT INTO `sys_role_menu` VALUES ('4573', '87', '0');
INSERT INTO `sys_role_menu` VALUES ('4653', '62', '26');
INSERT INTO `sys_role_menu` VALUES ('4654', '62', '25');
INSERT INTO `sys_role_menu` VALUES ('4655', '62', '24');
INSERT INTO `sys_role_menu` VALUES ('4656', '62', '14');
INSERT INTO `sys_role_menu` VALUES ('4657', '62', '13');
INSERT INTO `sys_role_menu` VALUES ('4658', '62', '12');
INSERT INTO `sys_role_menu` VALUES ('4659', '62', '6');
INSERT INTO `sys_role_menu` VALUES ('4660', '62', '3');
INSERT INTO `sys_role_menu` VALUES ('4661', '62', '0');
INSERT INTO `sys_role_menu` VALUES ('4746', '1', '57');
INSERT INTO `sys_role_menu` VALUES ('4747', '1', '30');
INSERT INTO `sys_role_menu` VALUES ('4748', '1', '29');
INSERT INTO `sys_role_menu` VALUES ('4749', '1', '28');
INSERT INTO `sys_role_menu` VALUES ('4750', '1', '129');
INSERT INTO `sys_role_menu` VALUES ('4751', '1', '118');
INSERT INTO `sys_role_menu` VALUES ('4752', '1', '117');
INSERT INTO `sys_role_menu` VALUES ('4753', '1', '116');
INSERT INTO `sys_role_menu` VALUES ('4754', '1', '27');
INSERT INTO `sys_role_menu` VALUES ('4755', '1', '92');
INSERT INTO `sys_role_menu` VALUES ('4756', '1', '91');
INSERT INTO `sys_role_menu` VALUES ('4757', '1', '48');
INSERT INTO `sys_role_menu` VALUES ('4758', '1', '104');
INSERT INTO `sys_role_menu` VALUES ('4759', '1', '77');
INSERT INTO `sys_role_menu` VALUES ('4760', '1', '124');
INSERT INTO `sys_role_menu` VALUES ('4761', '1', '123');
INSERT INTO `sys_role_menu` VALUES ('4762', '1', '122');
INSERT INTO `sys_role_menu` VALUES ('4763', '1', '121');
INSERT INTO `sys_role_menu` VALUES ('4764', '1', '120');
INSERT INTO `sys_role_menu` VALUES ('4765', '1', '119');
INSERT INTO `sys_role_menu` VALUES ('4766', '1', '76');
INSERT INTO `sys_role_menu` VALUES ('4767', '1', '75');
INSERT INTO `sys_role_menu` VALUES ('4768', '1', '74');
INSERT INTO `sys_role_menu` VALUES ('4769', '1', '128');
INSERT INTO `sys_role_menu` VALUES ('4770', '1', '112');
INSERT INTO `sys_role_menu` VALUES ('4771', '1', '73');
INSERT INTO `sys_role_menu` VALUES ('4772', '1', '22');
INSERT INTO `sys_role_menu` VALUES ('4773', '1', '21');
INSERT INTO `sys_role_menu` VALUES ('4774', '1', '20');
INSERT INTO `sys_role_menu` VALUES ('4775', '1', '127');
INSERT INTO `sys_role_menu` VALUES ('4776', '1', '61');
INSERT INTO `sys_role_menu` VALUES ('4777', '1', '2');
INSERT INTO `sys_role_menu` VALUES ('4778', '1', '15');
INSERT INTO `sys_role_menu` VALUES ('4779', '1', '126');
INSERT INTO `sys_role_menu` VALUES ('4780', '1', '62');
INSERT INTO `sys_role_menu` VALUES ('4781', '1', '56');
INSERT INTO `sys_role_menu` VALUES ('4782', '1', '55');
INSERT INTO `sys_role_menu` VALUES ('4783', '1', '7');
INSERT INTO `sys_role_menu` VALUES ('4784', '1', '26');
INSERT INTO `sys_role_menu` VALUES ('4785', '1', '25');
INSERT INTO `sys_role_menu` VALUES ('4786', '1', '24');
INSERT INTO `sys_role_menu` VALUES ('4787', '1', '14');
INSERT INTO `sys_role_menu` VALUES ('4788', '1', '13');
INSERT INTO `sys_role_menu` VALUES ('4789', '1', '12');
INSERT INTO `sys_role_menu` VALUES ('4790', '1', '125');
INSERT INTO `sys_role_menu` VALUES ('4791', '1', '6');
INSERT INTO `sys_role_menu` VALUES ('4792', '1', '3');
INSERT INTO `sys_role_menu` VALUES ('4793', '1', '0');

-- ----------------------------
-- Table structure for `sys_task`
-- ----------------------------
DROP TABLE IF EXISTS `sys_task`;
CREATE TABLE `sys_task` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cron_expression` varchar(255) DEFAULT NULL COMMENT 'cron表达式',
  `method_name` varchar(255) DEFAULT NULL COMMENT '任务调用的方法名',
  `is_concurrent` varchar(255) DEFAULT NULL COMMENT '任务是否有状态',
  `description` varchar(255) DEFAULT NULL COMMENT '任务描述',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `bean_class` varchar(255) DEFAULT NULL COMMENT '任务执行时调用哪个类的方法 包名+类名',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `job_status` varchar(255) DEFAULT NULL COMMENT '任务状态',
  `job_group` varchar(255) DEFAULT NULL COMMENT '任务分组',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `spring_bean` varchar(255) DEFAULT NULL COMMENT 'Spring bean',
  `job_name` varchar(255) DEFAULT NULL COMMENT '任务名',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_task
-- ----------------------------
INSERT INTO `sys_task` VALUES ('1', '0/10 * * * * ?', 'run1', '1', '', '4028ea815a3d2a8c015a3d2f8d2a0002', 'cn.ctbu.edu.sbadmin.common.task.WelcomeJob', '2017-05-19 18:30:56', '0', 'group1', '2017-05-19 18:31:07', null, '', 'welcomJob');

-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `truename` varchar(100) DEFAULT NULL COMMENT '真名',
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `dept_id` int(20) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) DEFAULT NULL COMMENT '手机号',
  `status` tinyint(255) DEFAULT NULL COMMENT '状态 0:禁用，1:正常',
  `user_id_create` bigint(255) DEFAULT NULL COMMENT '创建用户id',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `sex` int(4) DEFAULT NULL COMMENT '性别',
  `birth` datetime DEFAULT NULL COMMENT '出身日期',
  `img_url` varchar(255) DEFAULT NULL COMMENT '用户头像',
  `live_address` varchar(500) DEFAULT NULL COMMENT '现居住地',
  `hobby` varchar(255) DEFAULT NULL COMMENT '爱好',
  `province` varchar(255) DEFAULT NULL COMMENT '省份',
  `city` varchar(255) DEFAULT NULL COMMENT '所在城市',
  `district` varchar(255) DEFAULT NULL COMMENT '所在地区',
  `is_deleted` int(11) unsigned zerofill DEFAULT '00000000000' COMMENT '是否被删除',
  `gmt_delete` datetime DEFAULT NULL COMMENT '删除时间',
  `delete_userid` bigint(11) DEFAULT NULL COMMENT '删除人的id',
  `introduce` varchar(1000) DEFAULT NULL COMMENT '用户简介',
  `img` varchar(250) DEFAULT NULL COMMENT '签名url',
  `memo` varchar(2000) DEFAULT NULL COMMENT '备注',
  `role_name` varchar(2000) DEFAULT NULL COMMENT '角色名',
  `role_ids` varchar(2000) DEFAULT NULL COMMENT '角色ids',
  `home_url` varchar(255) DEFAULT NULL COMMENT '默认主页',
  `manager_id` int(11) DEFAULT NULL COMMENT '主管id',
  `manager_name` varchar(255) DEFAULT NULL COMMENT '主管名',
  `other_password` varchar(255) DEFAULT NULL COMMENT '第二密码',
  `wxids` varchar(255) DEFAULT NULL COMMENT '微信id',
  `wxname` varchar(255) DEFAULT NULL COMMENT '微信名',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=158 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '超级管理员', '27bd386e70f280e24c2f4f2a549b82cf', '1', 'a@b.com', '15902360004', '1', '1', '2017-08-15 21:40:39', '2019-03-10 11:16:31', '1', '2017-12-14 00:00:00', '', '', '', '重庆市', '重庆市市辖区', '沙坪坝区', '00000000000', null, null, '', '', '', 'admin', '1', 'http://www.ctbu.edu.cn', '1', 'admin', null, '', 'test', '2018-11-16 21:31:09');
