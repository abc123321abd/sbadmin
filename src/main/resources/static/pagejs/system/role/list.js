var prefix = "/webapi/system/role";
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

//顯示更新對話框按鈕
function searchTable() {
    myGrid.loadTable();
}

//导出Excel按鈕
function outExcel() {
    myGrid.exportExcel();
}

$(function () {
    var options = {
        ctrfix: "/system/role",//当前控制器
        srForm: '#srForm',//查询表单名
        AddDlgTitle: '新增角色',
        updateDlgTitle: '更新角色',

        tableId: '#roleTable',
        recordId:'id',
        //formId:'#formRole',//弹出对话框的表单id
        //dlgID:'#modal-Role',
        sort: 'id',
        direct: 'desc',

        deleteURL: function(){
            return prefix + "/batchRemove";
        },
        listURL: function(){
            return prefix + "/getByPage";
        },
        outExcel: function(){
          return prefix + "/exportExcel";
        },
        columns:[
            {
                checkbox: true
            },
            {
                field: 'id', // 列字段名
                title: '序号' // 列标题
            },
            {
                field: 'roleName',
                title : '角色名'
            },
            {
                field: 'roleSign',
                title : '角色标识'
            },
            {
                field: 'remark',
                title : '备注'
            },
            {
                data: null,
                title: '操作',
                width: '50px',
                formatter: function (data,  row, meta) {
                    var operators = '<a type="button"  href="#" class="btn btn-mint btn-xs btn-icon" onclick=showUpdateModel(' + row.id + ',"' + options.updateDlgTitle + '") title="' + options.updateDlgTitle + '"><i class="demo-psi-pen-5 icon-lg"></i></a>';
                    return operators;
                }
            }
          ]
    }

    if (s_edit_h == ''){
        options.editable = true;
    } else {
        options.editable = false;
    }

    myGrid = new gridViewModel(options);
    myGrid.init();
});



