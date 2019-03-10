package cn.edu.ctbu.sbadmin.system.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author bootdo 1992lcg@163.com
 * @version V1.0
 */
@Getter
@Setter
@ToString
public class UserToken implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long userId;
    private String username;
    private String name;
    private String password;
    private Long deptId;
}
