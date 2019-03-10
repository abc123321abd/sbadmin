package cn.edu.ctbu.sbadmin.common.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 完整的树结点，包含了children
 */
public class zTreeNodeExtend extends  ZTreeNode {

    private List<zTreeNodeExtend> _children;
    public zTreeNodeExtend()
    {
        _children = new ArrayList<zTreeNodeExtend>();
    }
    /// </summary>
    private List <zTreeNodeExtend> children;

    /// <summary>
    /// 最深结点的等级
    /// </summary>
    public int MaxLevel ;

    /// <summary>
    /// 所有的子结点

    public List<zTreeNodeExtend> getChildren() {
        return children;
    }

    public void setChildren(List<zTreeNodeExtend> children) {
        this.children = children;
    }

    public int getMaxLevel() {
        return MaxLevel;
    }

    public void setMaxLevel(int maxLevel) {
        MaxLevel = maxLevel;
    }


}
