package cn.edu.ctbu.sbadmin.common.utils;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;

/**
 * Ztree数的结点
 */
@Data
@JsonAutoDetect(fieldVisibility=JsonAutoDetect.Visibility.ANY, getterVisibility=JsonAutoDetect.Visibility.NONE)
public class ZTreeNode {
    //{ id:1, pId:0, name:"展开、折叠 自定义图标不同", open:true,
    //iconOpen:"../../../css/zTreeStyle/img/diy/1_open.png", iconClose:"../../../css/zTreeStyle/img/diy/1_close.png"},
    private Long id;

    private Long pId;
    private String name;
    private boolean open=false;
    /// <summary>
    /// 例如： iconOpen:"../../../css/zTreeStyle/img/diy/1_open.png",
    /// </summary>
    private String iconOpen;
    /// <summary>
    /// 例如：iconClose:"../../../css/zTreeStyle/img/diy/1_close.png"
    /// </summary>
    private String iconClose;

    /// <summary>
    /// icon:"../../../css/zTreeStyle/img/diy/2.png
    /// </summary>
    private String icon;

    /// <summary>
    /// 节点是否勾选,ztree 为checked,此处需要转换名称
    /// </summary>
    private boolean  nchecked=false;

    /// <summary>
    /// url 示例: url:"http://code.google.com/p/jquerytree/", target:"_blank"
    /// </summary>
    private String url;

    /// <summary>
    /// 点击打开，与url配合，示例 ： url:"http://code.google.com/p/jquerytree/", target:"_blank"
    /// </summary>
    private String target;

    //自定义属性
    private String tip;
    private String DiyStr1;
    private String DiyStr2;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPId() {
        return pId;
    }

    public void setPId(Long pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public String getIconOpen() {
        return iconOpen;
    }

    public void setIconOpen(String iconOpen) {
        this.iconOpen = iconOpen;
    }

    public String getIconClose() {
        return iconClose;
    }

    public void setIconClose(String iconClose) {
        this.iconClose = iconClose;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public boolean isNchecked() {
        return nchecked;
    }

    public void setNchecked(boolean nchecked) {
        this.nchecked = nchecked;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getDiyStr1() {
        return DiyStr1;
    }

    public void setDiyStr1(String diyStr1) {
        DiyStr1 = diyStr1;
    }

    public String getDiyStr2() {
        return DiyStr2;
    }

    public void setDiyStr2(String diyStr2) {
        DiyStr2 = diyStr2;
    }
}
