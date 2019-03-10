/**
 * Created by tms on 18/2/28.
 */
if (typeof(gridViewModel) == 'undefined') {

    var gridViewModel = function (options) {
        var self = this;

        ///////////////////////////初始化参数开始
        var tableId = options.tableId;
        var parentTableId=tableId.substr(1);//作为唯一关键字，放在top 中，去掉#号
        var recordId = 'id';//默认使用id
        var dlgID = options.dlgID;
        var formId = options.formId;
        var pageSize = 15;//默认一页记录数

        if (options.recordId) {
            recordId = options.recordId;
        }

        if (options.pageSize) {
            pageSize = options.pageSize;
        }

        /////////////////////////////////////初始化
        self.init = function () {
            self.loadTable();

            // var validateOpt=genValidateRules(dlgID);

            if(top.tabArr == undefined){
                top.tabArr={};

            }
            top.tabArr[parentTableId]=self;//放入父级中以便调用
        }

        ////////////////////////////////删除一条数据
        self.remove = function (id) {
            parent.layer.confirm("确认要删除选中的数据吗?", {
                btn: ['确定', '取消']
                // 按钮
            }, function () {
                $.ajax({
                    type: 'POST',
                    url: options.deleteURL(id),
                    success: function (r) {
                        if (r.code == 0) {
                            parent.layer.msg(r.msg);
                        } else {
                            parent.layer.msg(r.msg);
                            // toastr.success(r.msg);
                        }
                        self.reLoadTable();
                        return;
                    }
                });
            }, function () {
            });
        }

        //显示新增对话框
        self.showAddModel = function (id,rootText) {
            $("#hidId").val(0);

            if (options.AddDlgTitle == null) {
                options.AddDlgTitle = "新增记录";
            }

            self.showPopLayer(false,id, options.AddDlgTitle,rootText);
        }

        //////////////////显示更新对话框，
        self.showUpdateModel = function (id,text) {
             if (options.updateDlgTitle == null) {
                options.updateDlgTitle = "编辑记录";
            }

            self.showPopLayer(true,id, options.updateDlgTitle,text);
        }

        //真正的显示对话框
        self.showPopLayer = function(isEdit,id,title,rootText){
            isNew = 1;

            if (isEdit){
                isNew = 0;
            }

            var url = options.ctrfix + '/edit?isNew=' + isNew+'&id=' + id + "&tag=" + parentTableId + "&rootText=" + encodeURI(rootText);

            top.layer.open({
                type: 2,
                title: title,
                maxmin: true,
                shadeClose: false, // 点击遮罩关闭层
                area: ['800px', '520px'],
                content: url
            });
        }

        ////////////////////////////
        self.reLoadTable = function () {
            // $table.on('load-success.bs.table', function (e, data) {
            //     if (data.total && !data.rows.length) {
            //         $table.bootstrapTable('prevPage').bootstrapTable('refresh');
            //     }
            // });

            //$("#exampleTable").bootstrapTable('destroy');   //销毁表格

            self.loadTable();
            //$(tableId).bootstrapTreeTable('refresh');
        }

        //////////////////////////
        self.loadTable = function () {
            var inputs = $(':input', options.srForm).not(':button, :submit, :reset, :hidden'); //取所有的参数
            var len = inputs.length;
            var whereObj = new Object(); //找出所有可用的参数

            for (var i = 0; i < len; i++) {
                var item = inputs[i];
                var itemName = item.name;
                if (itemName.indexOf("sr") < 0) {
                    //没有使用sr开头的直接放弃
                    continue;
                }

                itemName = itemName.substring(2);//去掉头部的sr
                var itemValue = $(item).val();
                if (itemValue == null || itemValue == "")
                    continue;//空就什么都不做

                var itemBt = $(item).attr("data-between");
                if (itemBt != null && itemBt != "") {
                    //有between,直接找出前一个来加
                    var oldV = whereObj[itemBt];
                    oldV += "^^" + itemValue;
                    whereObj[itemBt] = oldV;
                    continue;//不再进行下面的处理了
                }

                var itemOption = $(item).attr("data-opera");//操作符
                if (itemOption != null && itemOption != "") {
                    //有操作符
                    itemValue += "^:" + itemOption;
                }
                whereObj[itemName] = itemValue;
            }

            //把对象转为字符串
            var whereStr = "";
            for (var item in whereObj) {
                whereStr += "&" + item + "=" + whereObj[item];
            }

            if (whereStr != "") {
                //去掉第一个&
                whereStr = whereStr.substring(1);
                whereStr = encodeURI(whereStr);//编码特殊字符
            }

            // $(tableId)
            //     .bootstrapTreeTable("destroy");

            $(tableId).bootstrapTreeTable(
                {
                    id: options.recordId,
                    code: options.recordId,
                    parentCode: 'parentId',
                    type: "GET", // 请求数据的ajax类型
                    url: options.listURL(), // 请求数据的ajax的url
                    ajaxParams: {}, // 请求数据的ajax的data属性
                    expandColumn: '1', // 在哪一列上面显示展开按钮
                    striped: true, // 是否各行渐变色
                    bordered: true, // 是否显示边框
                    expandAll: false, // 是否全部展开
                    // toolbar : '#exampleToolbar',
                    columns: options.columns
                });
        }
    }

}
