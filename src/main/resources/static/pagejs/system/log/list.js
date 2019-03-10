var prefix = "/webapi/system/log";
var myGrid;

//批量删除按鈕
function batchRemove() {
    myGrid.batchRemove();
}

//顯示更新對話框按鈕
function showUpdateModel(id) {
    myGrid.showUpdateModel(id);
}

//顯示更新對話框按鈕
function searchTable() {
    myGrid.loadTable();
}

$(function () {
    var options = {
        ctrfix: "/system/log",//当前控制器
        srForm: '#srForm',//查询表单名
        AddDlgTitle: '新增日志',
        updateDlgTitle: '查看日志',
        recordId:'id',

        tableId: '#logTable',
        //formId:'#formRole',//弹出对话框的表单id
        //dlgID:'#modal-Role',
        sort: 'id',
        direct: 'desc',

        deleteURL: function(){
            return prefix + "/delete";
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
                field: 'id',
                title: '序号'
            },
            {
                field: 'userId',
                title: '用户ID'
            },
            {
                field: 'username',
                title: '用户名'
            },
            {
                field: 'operation',
                title: '事件'
            },
            {
                field: 'gmtCreate',
                title: '创建时间'
            },
            {
                field: 'time',
                title: '响应时间'
            },
            {
                field: 'ip',
                title: 'IP地址'
            },
            {
                data: null,
                title: '操作',
                width: '50px',
                formatter: function (data,  row, meta) {
                    var operators = '<a type="button"  href="#" class="btn btn-mint btn-xs btn-icon" onclick=showUpdateModel(' + row.id + ',"' + options.updateDlgTitle + '") title="'
                        + options.updateDlgTitle + '"><i class="demo-psi-pen-5 icon-lg"></i></a>';
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
