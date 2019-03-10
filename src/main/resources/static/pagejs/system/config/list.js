
var prefix = "/webapi/system/config";
var myGrid;

//批量删除按钮
function batchRemove() {
    myGrid.batchRemove();
}

//显示增加对话框按钮
function showAddModel() {
    myGrid.showAddModel();
}

//显示更新对话框按钮
function showUpdateModel(id) {
    myGrid.showUpdateModel(id);
}

//导出Excel按钮
function outExcel() {
    myGrid.exportExcel();
}

//查詢按钮
function searchTable() {
    myGrid.loadTable();
}

$(function () {
    var options = {
        ctrfix: "/system/config",//当前控制器
        recordId: 'id',
        tableId: '#configTable',
        AddDlgTitle: '新增',
        updateDlgTitle: '更新',
        recordId:'id',
        sort: 'id',
        direct: 'desc',
        deleteURL: function (id) {
            return prefix + "/batchRemove";
        },
        listURL: function () {
            return prefix + "/getByPage";
        },
        outExcel:function(){
            return prefix + "/exportExcel";
        },

        columns: [
            {
                checkbox: true
            },
            {
                field: 'id',
                title: '序号',
            },
            {
                field: 'title',
                title: ' 名称',
            },
            {
                field: 'type',
                title: '类型',
            },
            {
                field: 'keyword',
                title: '键名',
            },
            {
                field: 'text',
                title: '文本',
            },
            {
                field: 'value',
                title: '数值',
            },
            {
                field: 'memo',
                title: '备注',
            },
            {
                data : null,
                title: '操作',
                width: '50px',
                formatter: function (data,  row, meta) {
                    var operators = '<a type="button"  href="#" class="btn btn-mint btn-xs btn-icon" onclick=showUpdateModel(' + row.id + ',"' + options.updateDlgTitle + '") title="' + options.updateDlgTitle + '"><i class="demo-psi-pen-5 icon-lg"></i></a>';
                    return operators;
                }
            }
        ]
    }

    if (s_edit_h == '') {
        options.editable = true;
    } else {
        options.editable = false;
    }

    myGrid = new gridViewModel(options);
    myGrid.init();

})
