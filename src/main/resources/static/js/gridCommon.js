/**
 * Created by tms on 18/2/28.
 */
if (typeof(gridViewModel) == 'undefined') {


    var gridViewModel = function (options) {
        var self = this;

        ///////////////////////////初始化参数开始
        var tableId = options.tableId;
        var parentTableId = tableId.substr(1);//作为唯一关键字，放在top 中，去掉#号


        var recordId = options.recordId;//默认使用id
        if (options.recordId) {
            recordId = options.recordId;
        }
        var dlgID = options.dlgID;
        var formId = options.formId;
        var pageSize = 15;//默认一页记录数
        if (options.pageSize) {
            pageSize = options.pageSize;
        }

        ////////////////////////初始参数结束


        //初始化
        self.init = function () {
            self.loadTable();
            if(top.tabArr == undefined){
                top.tabArr = {};
            }
            top.tabArr[parentTableId] = self;//放入父级中以便调用
            // var validateOpt=genValidateRules(dlgID);

            $(formId).validate();
        }

        self.batchRemove = function () {
            var rows = $(tableId).bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
            if (rows.length == 0) {
                layer.msg("请选择要删除的数据");
                return;
            }
            top.layer.confirm("确认要删除选中的'" + rows.length + "'条数据吗?", {
                btn: ['确定', '取消']
                // 按钮
            }, function (index) {
                top.layer.close(index);

                var ids = new Array();
                // 遍历所有选择的行数据，取每条数据对应的ID
                $.each(rows, function (i, row) {
                    ids[i] = row[recordId];
                });
                $.ajax({
                    type: 'POST',
                    data: {
                        "ids": ids
                    },
                    url: options.deleteURL(),
                    success: function (r) {
                        if (r.code == 0) {
                            layer.msg(r.msg);

                        } else {
                            layer.msg(r.msg);
                            // toastr.success(r.msg);

                        }
                        self.reLoadTable();

                        return;
                    }
                });
            }, function () {
            });

        }


        //清除所有的对话框中的内容
        self.clearModel = function () {
            //由于有可能不是input，所以这里可能还需要写入一些准备工作


            var inputs = $(':input', options.formId)
                .not(':button,:reset,:checkbox,:radio');//注意：使用submit就出错

            inputs.val('');
            if ($(options.formId).iCheck)
                $(':input', options.formId).iCheck('uncheck'); //移除 checked 状态
            $(':input', options.formId).prop('checked', false).remove("selected");
            //  inputs.prevObject.each(function (item) {
            //     item.val('').removeAttr('checked')
            //         .removeAttr('selected');
            //
            // });


        }

        //显示新增对话框
        self.showAddModel = function () {
            $("#hidId").val(0);

            self.clearModel();
            if (options.showAddBefore) {

                options.showAddBefore();
            }

            $(dlgID).modal("show");

        }



        ////////////////////////显示新增对话框
        self.showAddModel = function () {
            $("#hidId").val(0);

            if (options.AddDlgTitle == null){
                options.AddDlgTitle = "新增记录";
            }

            self.showPopLayer(0,options.AddDlgTitle);
        }

        ////////////////////////显示更新对话框，此时读取远程数据后填充
        self.showUpdateModel = function (id) {
            if(options.updateDlgTitle == null){
                options.updateDlgTitle = "编辑记录";
            }

            self.showPopLayer(id,options.updateDlgTitle);
        }

        ////////////////////////真正的显示对话框
        self.showPopLayer = function(id,title){
            top.layer.open({
                type: 2,
                title: title,
                maxmin: true,
                shadeClose: false, // 点击遮罩关闭层
                area: ['800px', '520px'],
                content: options.ctrfix + '/edit?id=' + id + " &tag=" + parentTableId
            });
        }



        self.reLoadTable = function () {

            // $table.on('load-success.bs.table', function (e, data) {
            //     if (data.total && !data.rows.length) {
            //         $table.bootstrapTable('prevPage').bootstrapTable('refresh');
            //     }
            // });

            //$("#exampleTable").bootstrapTable('destroy');   //销毁表格

            $(tableId).bootstrapTable('refresh');
        }


        self.loadTable = function () {
            //取所有的参数

            var inputs = $(':input', options.srForm)
                .not(':button, :submit, :reset');
            var len = inputs.length;
            //找出所有可用的参数
            var whereObj = new Object();
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


            $(tableId)
                .bootstrapTable("destroy");


            $(tableId)
                .bootstrapTable(
                    {
                        method: 'GET', // 服务器数据的请求方式 get or post
                        url: options.listURL(), // 服务器数据的加载地址
                        // showRefresh : true,
                        // showToggle : true,
                        // showColumns : true,
                        iconSize: 'outline',
                        toolbar: '#exampleToolbar',
                        striped: true, // 设置为true会有隔行变色效果
                        dataType: "json", // 服务器返回的数据类型
                        pagination: true, // 设置为true会在底部显示分页条
                        //pageList: [5, 10, 15, 20],
                        //smartDisplay:false,如果配合pagelist使用,可以一定显示分页条数
                        // queryParamsType : "limit",
                        // //设置为limit则会发送符合RESTFull格式的参数
                        singleSelect: false, // 设置为true将禁止多选
                        // contentType : "application/x-www-form-urlencoded",
                        // //发送到服务器的数据编码类型
                        pageSize: pageSize, // 如果设置了分页，每页数据条数
                        pageNumber: 1, // 如果设置了分布，首页页码
                        // search : true, // 是否显示搜索框
                        // showColumns : true, // 是否显示内容下拉框（选择显示的列）
                        sidePagination: "server", // 设置在哪里进行分页，可选值为"client" 或者
                        // "server"
                        queryParams: function (params) {

                            var sortId=params.sort;
                            var direct=params.order;
                            if(sortId==null){
                                sortId=options.sort;
                                direct=options.direct;
                            }

                            var paraObj = {
                                pageSize: params.limit,
                                pageIndex: params.offset,
                                sort:sortId ,
                                direct:direct ,
                                where: whereStr
                            };

                            if (options.listParamsBefore) {
                                options.listParamsBefore(paraObj);
                            }
                            return paraObj;
                        },
                        // //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
                        // queryParamsType = 'limit' ,返回参数必须包含
                        // limit, offset, search, sort, order 否则, 需要包含:
                        // pageSize, pageNumber, searchText, sortName,
                        // sortOrder.
                        // 返回false将会终止请求
                        columns: options.columns
                    });


        }


        ////////////////////////导出Excel type导出的类型
        //type 1 导出当页数据 2 导出全部数据 3 导出符合条件全部数据
        self.exportExcel = function(type) {
            // //获取table的分页参数值
            // var offset =options.pageSize;  //$('#exampleTable').bootstrapTable('getOptions').pageSize;
            // var limit = options.pageIndex; //$('#exampleTable').bootstrapTable('getOptions').pageNumber * offset;
            // data = 'limit='+limit+'&offset='+offset;
            //后端导出的方法
            document.location.href =  options.outExcel();
            //?type="+type+"&"+ data;
        }



    }


}
