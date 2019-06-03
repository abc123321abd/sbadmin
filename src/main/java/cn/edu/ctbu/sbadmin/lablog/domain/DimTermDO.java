package cn.edu.ctbu.sbadmin.lablog.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "dim_term")
public class DimTermDO {

    /**
     * 主键，自增
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "JDBC")
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 年代
     */
    @Column(name = "`year`")
    private int year;
}
