var prefix = "/webapi/system/menu";
var myGrid;

function batchRemove() {
    myGrid.batchRemove();
}

function remove(id) {
    myGrid.remove(id);
}

function showAddModel(id, text) {
    myGrid.showAddModel(id, text);
}

/**
 * 显示更新对话框
 */
function showUpdateModel(id) {
    myGrid.showUpdateModel(id);
}

function searchTable() {
    myGrid.loadTable();
}

$(function () {
    var options = {
        ctrfix: "/system/menu",//当前控制器
        recordId: 'id',
        tableId: '#menuTable',
        AddDlgTitle: '新增菜单',
        updateDlgTitle: '更新菜单',

        sort: 'id',
        direct: 'desc',

        deleteURL: function (id) {
            return prefix + "/delete?id=" + id;
        },
        listURL: function () {
            return prefix + "/getAll";
        },
        columns: [
            {
                title: '编号',
                field: 'id',
                visible: false,
                align: 'center',
                valign: 'center',
                width: '50px',
                checkbox: true
            },
            {
                title: '名称',
                valign: 'center',
                field: 'name',
                width: '20%'
            },
            {
                title: '图标',
                field: 'icon',
                align: 'center',
                valign: 'center',
                width : '5%',
                formatter: function (row, value, index) {
                    return row.icon == null ? '' : '<i class="' + row.icon + ' fa-lg"></i>';
                }
            },
            {
                title: '类型',
                field: 'type',
                align: 'center',
                valign: 'center',
                width : '10%',
                formatter: function (row, value, index) {
                    if (row.type === 0) {
                        return '<span class="label label-primary">目录</span>';
                    }
                    if (row.type === 1) {
                        return '<span class="label label-success">菜单</span>';
                    }
                    if (row.type === 2) {
                        return '<span class="label label-warning">按钮</span>';
                    }
                }
            },
            {
                title: '地址',
                valign: 'center',
                width : '20%',
                field: 'url'
            },
            {
                title: '权限标识',
                valign: 'center',
                width : '20%',
                field: 'perms'
            },
            {
                title: '操作',
                field: 'id',
                align: 'center',
                width: '140px',
                formatter: function (row, value, index) {
                    var e = '<a class="btn btn-success btn-xs btn-icon tableicon " href="#" mce_href="#" title="编辑" onclick="showUpdateModel(\''
                        + row.id + '\',\'' + row.name
                        + '\')"><i class="demo-psi-pen-5 icon-lg "></i></a> ';
                    var a = '<a class="btn btn-info btn-xs btn-icon  tableicon' + s_add_h + '" href="#" title="增加下級"  mce_href="#" onclick="showAddModel('
                        + row.id + ',\'' + row.name
                        + '\')"><i class=" ion-plus-round "></i></a> ';
                    var d = '<a class="btn btn-danger btn-xs btn-icon tableicon ' + s_remove_h + '" href="#" title="删除"  mce_href="#" onclick="remove(\''
                        + row.id
                        + '\')"><i class="demo-psi-recycling icon-lg"></i></a> ';
                    return e + a + d;
                }
            }
        ]
    }

    myGrid = new gridViewModel(options);
    myGrid.init();
})











