var prefix = "/webapi/system/dept";
var myGrid;

function batchRemove() {
    myGrid.batchRemove();
}

function removeOne(id) {
    myGrid.remove(id);
}

function showAddModel(id,text) {
    myGrid.showAddModel(id,text);
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
        ctrfix : "/system/dept",//当前控制器
        recordId: 'id',
        srForm: '#srForm',//查询表单名
        formId: '#formdept',//弹出对话框的表单id
        tableId: '#deptTable',
        dlgID: '#modal-dept',
        sort: 'id',
        direct: 'desc',
        recordId:'id',

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
                field: 'name',
                title: '部门名称',
                valign: 'center'
            },
            {
                field: 'orderNum',
                title: '排序'
            },
            {
                field: 'delFlag',
                title: '是否删除  -1：已删除  0：正常'
            },
            {
                title: '操作',
                field: 'roleId',
                align: 'center',
                width:'140px',
                formatter: function (row, value, index) {
                    var e = '<a class="btn btn-success btn-xs btn-icon tableicon " href="#" mce_href="#" title="编辑" onclick="showUpdateModel(\''
                        + row.id+'\',\''+row.name
                        + '\')"><i class="demo-psi-pen-5 icon-lg "></i></a> ';
                    var a = '<a class="btn btn-info btn-xs btn-icon  tableicon' + s_add_h + '" href="#" title="增加下級"  mce_href="#" onclick="showAddModel('
                        + row.id+',\''+row.name
                        + '\')"><i class=" ion-plus-round "></i></a> ';
                    var d = '<a class="btn btn-danger btn-xs btn-icon tableicon ' + s_remove_h + '" href="#" title="删除"  mce_href="#" onclick="removeOne(\''
                        + row.id
                        + '\')"><i class="demo-psi-recycling icon-lg"></i></a> ';
                    return e+a+d;
                }
            }]
    }

    myGrid = new gridViewModel(options);
    myGrid.init();
})



