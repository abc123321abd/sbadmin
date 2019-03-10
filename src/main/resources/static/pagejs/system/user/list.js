//datatable layout
var prefix = "/webapi/system/user";
var myGrid;


//批量删除按鈕
function batchRemove() {
    myGrid.batchRemove();
}

//顯示增加對話框按鈕
function showAddModel() {
    myGrid.showAddModel();
}

//顯示更新對話框按鈕
function showUpdateModel(id) {
    myGrid.showUpdateModel(id);
}

//导出Excel按鈕
function outExcel() {
    myGrid.exportExcel();
}

//查詢按鈕
function searchTable() {
    myGrid.loadTable();
}

//初始化控件
function initControl() {
    //所属部门
    $.ajax({
        url: "/webapi/system/dept/ztreeAll",
        cache: false,
        type: "GET",
        async: false,
        success: function (data) {
            $("#srdeptId option").remove();
            if (data != null && data.length > 0) {
                var newd = Tms.convert2tree(data, 0);
                var html = Tms.creatSelectTree(newd, "<option value=''>--请选择--</option>");

                $("#srdeptId").html(html);
            }
        }
    });

    //所属角色
    $.ajax({
        url: "/webapi/system/role/getAll",
        cache: false,
        type: "GET",
        async: false,
        success: function (data) {
            $("#srroleIds option").remove();
            if (data != null && data.length > 0) {
                $("#srroleIds").append("<option value=''>--请选择--</option>");
                data.forEach(function (e) {
                    $("#srroleIds").append("<option value='" + e.id + "'>" + e.id + "-" + e.roleName + "</option>");
                });
            }
        }
    });

    //用户状态
    $("#srstatus").append("<option value=''>--请选择--</option>");
    Tsw.getUserStatusOptionItems().forEach(function (e) {
        $("#srstatus").append("<option value='" + e.value + "'>" + e.name + "</option>");
    });
}

$(function () {
    var options = {
        ctrfix : "/system/user",//当前控制器
        srForm: '#srForm',//查询表单名
        AddDlgTitle : '新增用戶',
        updateDlgTitle : '更新用戶',
        recordId:'id',

        tableId : '#userTable',
        //formId : '#formUser',//弹出对话框的表单id
        //dlgID : '#modal-User',
        sort : 'id',
        direct : 'desc',

        deleteURL: function(){
            return prefix + "/batchRemove";
        },

        listURL : function(){
            return prefix + "/getByPage";
        },
        outExcel : function(){
            return prefix + "/exportExcel"  ;
        },
        columns : [
            {
                checkbox: true
            },
            {
                field: 'id',
                title : '用户ID'
            },
            {
                field: 'username',
                title : '用户名',
                formatter: function (data,  row, meta) {
                    //这里是主题  把url变成超链接、把图片路径显示为图片
                    return "<img src='" + row.imgUrl + "' style='width:20px;height:20px;' />" + "<span>" + data + "</span>";
                }
            },
            {
                field: 'truename',
                title : '真是姓名'
            },
            {
                field: 'sex',
                title : '性別',
                render: function (data, type, row, meta) {
                    return "<span>" + Tms.formatSex(data) + "</span>";
                }
            },
            {
                field: 'deptId',
                title : '部门ID'
            },
            {
                field: 'email',
                title : '电子邮箱'
            },
            {
                field: 'mobile',
                title : '手机号码'
            },
            {
                field: 'status',
                title : '用户状态',
                formatter: function (data, type, row, meta) {
                    if (data > 0){
                        return "<span style='color:green'>" + Tsw.changeUserStatus(data) + "</span>";
                    } else {
                        return "<span style='color:red'>" + Tsw.changeUserStatus(data) + "</span>";
                    }
                 }
            },
            {
                field: 'roleName',
                title : '角色'
            },
            {
                field: 'managerName',
                title : '上級管理員'
            },
            {
                data : null,
                title : '操作',
                width: '80px',
                formatter: function (data, row, meta) {
                    var operators = '<a type="button"  href="#" class="btn btn-mint btn-xs btn-icon" onclick=showUpdateModel(' + row.id + ',"' + options.updateDlgTitle + '") title="' + options.updateDlgTitle + '"><i class="demo-psi-pen-5 icon-lg"></i></a>';
                    if (row.status > 0) {
                        //禁用
                        operators += '&nbsp;&nbsp;<a type="button"  href="#"  class="btn btn-danger btn-xs btn-icon" onclick="changeUserStatus(' + row.id  + ')" title="禁用"><i class="demo-psi-tag icon-lg"></i></a>';
                    } else {
                        //恢复
                        operators += '&nbsp;&nbsp;<a type="button"  href="#"  class="btn btn-success btn-xs btn-icon" onclick="changeUserStatus(' + row.id  + ')" title="恢复"><i class="demo-psi-repeat-2 icon-lg"></i></a>';
                    }

                    return operators;
                }
            }
         ]
    }

    if(s_edit_h == ''){
        options.editable = true;
    }else{
        options.editable = false;
    }

    //初始化控件
    initControl();

    //选择所属部门
    $("#srdeptId").change(function (a, b, c, d) {
        searchTable();
    });

    //选择角色
    $("#srroleIds").change(function (a, b, c, d) {
        searchTable();
    });

    //选择用户状态
    $("#srstatus").change(function (a, b, c, d) {
        searchTable();
    });

    myGrid = new gridViewModel(options);
    myGrid.init();
})

//变换用户状态
changeUserStatus = function(id)
{
    $.ajax({
        type: 'POST',
        data: {"id": id},
        url: prefix + "/changeUserStatus",
        success: function (r) {
            myGrid.reLoadTable();
            layer.msg(r.msg);
        }
    });
}








