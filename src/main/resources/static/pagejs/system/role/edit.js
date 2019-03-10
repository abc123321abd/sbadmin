/**
 * Created by tms on 18/10/20.
 */

var formId = "#formRole";
var prefix = "/webapi/system/role";
var trueParentObj;//真正的父对象的表格
var myEdit;

$(function () {
    var isNew = GetQueryString("isNew");
    var hidId = GetQueryString("id");
    var parentName = GetQueryString("parentname");
    var rootText = decodeURI(GetQueryString("rootText"));

    $("#hidId").val(hidId);

    var options = {
        ctrfix : "/system/role",//当前控制器
        getURL: function(id){
            return prefix + "/get?id=" + id;
        },
        saveURL: function(){
            return prefix + "/save";
        },
        updateURL: function(){
            return prefix + "/update";
        },

        saveBefore: function(){
            //保存前
            if (!validateData()) {
                return false;
            }
            return true;
        },
        updateBefore: function(){
            //更新前
            if (!validateData()) {
                return false;
            }
            return true;
        },
        saveAfter: function (rs) {
            //新增后,保存权限菜单
            saveRoleMenus(rs);
        },
        updateAfter: function (rs) {
            //保存后,保存权限菜单
            saveRoleMenus(rs);
        },

        showAddBefore : function () {
            //新增对话框打开前
        },
        showAddAfter:function () {
            //新增对话框打开后
        },
        showUpdateBefore : function () {
            //更新对话框打开前
        },
        showUpdateAfter: function (rs) {
            //更新对话框打开后,读取菜单信息
            var roleId = rs.data.id;

            $.ajax({
                type: "GET",
                url: "/webapi/system/menu/menuids/" + roleId,
                success:function (data) {
                    //此时需要根据信息来checked
                    if (data.code == 1){
                        var tmpStr = data.data;

                        //首先，清除所有的check元素；
                        myEdit.zTreeObjMenu.checkAllNodes(false);

                        if (tmpStr != null && tmpStr != "") {
                            var tmpStrs = tmpStr.split(',');
                            for (var i = 0; i < tmpStrs.length; i++) {
                                if (tmpStrs[i] != "") {
                                    // ,viewModel.zTreeObj.getNodeByTId(tmpStrs[i]);,,,.zTreeObj.getNodeByParam("id", tmpStrs[i], null);
                                    var selNode = myEdit.zTreeObjMenu.getNodeByParam("id", tmpStrs[i], null); //如果有高度节点，则选中它
                                    myEdit.zTreeObjMenu.checkNode(selNode, true, true);//勾选节点
                                }
                            }
                        }
                    } else{
                        layer.msg(data.msg);
                    }
                }
            });
        }
    };

    //初始化菜单树
    initZtreeMenu();

    myEdit = new editModel(options);
    myEdit.init(isNew,hidId,parentName,rootText);

    var parentTag = GetQueryString("tag");
    trueParentObj = top.tabArr[parentTag];
});

//初始化菜单树
function initZtreeMenu() {
    //获取菜单基础数据并显示
    $.ajax({
        type: "GET",
        url: "/webapi/system/menu/ztreeAll"
    }).done(function (data) {
        var setting = {
            view: {
                showIcon: true
            },
            check: {
                enable: true,
                chkboxType: {"Y": "p", "N": "p"}  //勾选时，父不关联子
            },
            data: {
                simpleData: {
                    enable: true
                }
            }
        };

        //打开Power树
        var treeNodes = data;
        myEdit.zTreeObjMenu = $.fn.zTree.init($("#ztreeMenu"), setting, treeNodes);
    });
}

//增加或更新表單按鈕
function insertOrUpdateForm() {
    // 1)判断是否输入是有效的
    if ($(formId).valid()) {
        //2)将所有的表单输入转为对象或键值对
        var zTree = myEdit.zTreeObjMenu;
        var check = zTree.getCheckedNodes(true);//取得所有被选中节点数据
        var PowerIds="";

        for (var i in check) {
            PowerIds = check[i].id+"," +PowerIds;
        }

        //获取选中的菜单
        PowerIds = Tms.dropRsplit (PowerIds,",");
        $("#menuIds").val(PowerIds);

        myEdit.insertOrUpdateForm();//更新当前结点，真正的方法
    }
}

//保存权限菜单
function  saveRoleMenus(rs) {
    var data = rs.data;
    //第一次保存后的再运行，
    //这次需要读取ztree中的选中结点，并提交到远程去
    var PowerIds = "";
    var zTree = myEdit.zTreeObjMenu;
    var check = zTree.getCheckedNodes(true);//取得所有被选中节点数据
    var ids = new Array();

    // 遍历所有选择的行数据，取每条数据对应的ID
    for (var i in check) {
        //PowerIds = PowerIds + "," + check[i].id;
        ids[i] = check[i].id;
    }

    $.ajax({
        cache: true,
        type: "POST",
        url:  "/webapi/system/menu/rolemenus" ,
        data: {
            "roleId":data.id,
            "ids": ids
        },
        async: false,
        error: function (request) {
            layer.msg("网络连接出错，请稍候再试!");
            //toastr["error"]("网络连接出错，请稍候再试!!");
        },
        success: function (data) {
            if (data.code == 0) {
                //toastr["error"](data.msg);
                layer.msg(data.msg);
            } else {
                //layer.msg("成功写入数据！");
                //toastr["success"]("成功写入数据！");
            }
        }
    });

}


//验证数据
function validateData() {
    var ok = false;
    var id = $("#hidId").val()
    var roleName = $.trim($("#roleName").val());
    var roleSign = $.trim($("#roleSign").val());

    if (roleName == "") {
        layer.msg("请输入角色名!");
        return false;
    }

    if (roleSign == "") {
        layer.msg("角色标识!");
        return false;
    }

    $.ajax({
        url: prefix +  '/validateRole',
        type: 'POST',
        async: false,
        cache: false,
        data: { id: id, roleName: roleName ,roleSign: roleSign },
        success: function (r) {
            ok = (r.code == 1) ? true : false;
            if (!ok) {
                layer.msg(r.msg);
            }
        }
    });

    return ok;
}