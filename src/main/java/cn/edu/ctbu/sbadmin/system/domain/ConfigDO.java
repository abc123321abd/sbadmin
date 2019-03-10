package cn.edu.ctbu.sbadmin.system.domain;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;


/**
 *
 *
 * @author Fromtech
 * @email tms@fromtech.net
 * @date 2018-11-19 15:45:15
 */
@Data
@Table(name = "sys_config")
public class ConfigDO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "JDBC")
    @Excel(name = "序号")

    private Integer id;
    //名称
    @Excel(name = "名称")
    private String title;
    //类型
    @Excel(name = "类型")
    private Integer type;
    //键名
    @Excel(name = "键名")
    private String keyword;
    //文本
    @Excel(name = "文本")
    private String text;
    //数值
    @Column(name = "`value`")
    @Excel(name = "数值")
    private Integer value;
    //备注
    @Excel(name = "备注")
    private String memo;

}