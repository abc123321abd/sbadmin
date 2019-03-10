/**
 * Created by tms on 18/10/20.
 */

var formId = "#formUser";
var prefix = "/webapi/system/user";
var trueParentObj;//真正的父对象的表格
var myEdit;


$(function () {
    var isNew = GetQueryString("isNew");
    var hidId = GetQueryString("id");
    var parentName = GetQueryString("parentname");
    var rootText = decodeURI(GetQueryString("rootText"));

    $("#hidId").val(hidId);

    var options = {
        ctrfix : "/system/user",//当前控制器
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
            var password1  = $("#password").val();
            var password2  = $("#confirmPassword").val();
            var roleIds  = $("#roleIds").val();

            //验证数据有效性
            if (!validateData()) {
                return false;
            }

            if (password1 == "" || password2 == "") {
                layer.msg("请填写密码");
                return false;
            }

            if (password1.length < 6 || password1.length > 20) {
                layer.msg("密码长度为6—20位");
                return false;
            }

            if (password1 != password2) {
                layer.msg("两次密码输入不一致");
                return false;
            }

            if (roleIds == "") {
                layer.msg("请选择角色！");
                return false;
            }

            return true;
        },
        updateBefore: function(){
            //更新前
            var password1  = $("#password").val();
            var password2  = $("#confirmPassword").val();
            var roleIds  = $("#roleIds").val();

            //验证数据有效性
            if (!validateData()) {
                return false;
            }

            //两个密码不同时为空时
            if (!(password1 == "" && password2 == "")) {
                if (password1.length < 6 || password1.length > 20) {
                    layer.msg("密码长度为6—20位!");
                    return false;
                }

                if (password1 != password2) {
                    layer.msg("两次密码输入不一致!");
                    $("#txtbSPassword").val("");
                    $("#confirm_password").val("");
                    return false;
                }
            }

            if (roleIds == "") {
                layer.msg("请选择角色！");
                return false;
            }

            return true;
        },
        saveAfter: function (rs) {
            //保存后
        },
        updateAfter: function (rs) {
            //更新后
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
            //更新对话框打开后
            //chosen选择控件可以根据传过来的值动态选中
            $("#roleIds").trigger("chosen:updated");
            $("#managerId").trigger("chosen:updated");
        }
    };

    //初始化控件
    initControl();

    myEdit = new editModel(options);
    myEdit.init(isNew,hidId,parentName,rootText);

     var parentTag = GetQueryString("tag");
    trueParentObj = top.tabArr[parentTag];
})

//初始化控件
function initControl() {
    //性别控件
    $("#sex").append("<option value=''>--请选择--</option>");
    Tsw.getSexTypeOptionItems().forEach(function (e) {
        $("#sex").append("<option value='" + e.value + "'>" + e.name + "</option>");
    });

    //用户状态控件
    $("#status").append("<option value=''>--请选择--</option>");
    Tsw.getUserStatusOptionItems().forEach(function (e) {
        $("#status").append("<option value='" + e.value + "'>" + e.name + "</option>");
    });

    ///////////
    //所属部门
    $.ajax({
        url: "/webapi/system/dept/ztreeAll",
        cache: false,
        type: "GET",
        async: false,
        success: function (data) {
            $("#deptId option").remove();
             if (data != null && data.length > 0) {
                 var newd = Tms.convert2tree(data, 0);
                 var html = Tms.creatSelectTree(newd, "<option value=''>--请选择--</option>");

                 $("#deptId").html(html);
            }
        }
    });

    ////////////
    //所属角色
    $.ajax({
        url: "/webapi/system/role/getAll",
        cache: false,
        type: "GET",
        async: false,
        success: function (data) {
            $("#roleIds option").remove();
            if (data != null && data.length > 0) {
                //$("#roleIds").append("<option value=''>--请选择--</option>");
                data.forEach(function (e) {
                    $("#roleIds").append("<option value='" + e.id + "'>" + e.roleName + "</option>");
                });
            }
        }
     });
    $("#roleIds").chosen({
        width:'100%',
        no_results_text: "没有找到结果!",//搜索无结果时显示的提示
        search_contains:true, //关键字模糊搜索,设置为false,则只从开头开始匹配
        allow_single_deselect:true
    });

    /////////
    //更改角色
    $("#roleIds").change(function (a, b, c, d) {
        $("#roleName").val(Tsw.getSelect2Text("roleIds"));
    });

    //上级管理员
    $.ajax({
        url: "/webapi/system/user/getAll",
        cache: false,
        type: "GET",
        async: false,
        success: function (data) {
            $("#managerId option").remove();
             if (data != null && data.length > 0) {
                $("#managerId").append("<option value=''>--请选择--</option>");
                data.forEach(function (e) {
                    $("#managerId").append("<option value='" + e.id + "'>" + e.username + "</option>");
                });
            }
        }
    });
    $("#managerId").chosen({
        width:'100%',
        no_results_text: "没有找到结果!",//搜索无结果时显示的提示
        search_contains:true, //关键字模糊搜索,设置为false,则只从开头开始匹配
        allow_single_deselect:true
    });

    /////////
    //更改管理员
    $("#managerId").change(function (a, b, c, d) {
        $("#managerName").val(Tsw.getSelect2Text("managerId"));
    });
}

//增加或更新表單按鈕
function insertOrUpdateForm(){
    myEdit.insertOrUpdateForm();//更新当前结点，真正的方法
}

//验证数据
function validateData() {
    var ok = false;
    var id = $("#hidId").val()
    var userName = $.trim($("#username").val());  //输入的用户名

    if (userName == "") {
        layer.msg("请输入用户名!");
        return false;
    }

    $.ajax({
        url: prefix +  '/validateUserName',
        type: 'POST',
        async: false,
        cache: false,
        data: { id: id, userName: userName },
        success: function (r) {
            ok = (r.code == 1) ? true : false;
            if (!ok) {
                layer.msg(r.msg);
            }
        }
    });

    return ok;
}