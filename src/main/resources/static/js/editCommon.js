if (typeof(editModel) == 'undefined') {

    var editModel = function (options) {
        var self = this;

        //////////////////////////////根据parentid 取对应的结点信息
        self.getParentName = function (parentId) {
            $.ajax({
                cache: false,
                type: "POST",
                url: options.getURL(parentId),

                error: function (request) {
                    layer.alert("网络连接出错，请稍候再试!!");
                },
                success: function (data) {
                    if (data.code <= 0) {
                        layer.alert(data.msg);
                    } else {
                        $("#parentIdName").val(data.data.name);
                    }
                }
            });
        };

        //////////////////////////////end of clearModel
        self.clearModel = function() {
            //由于有可能不是input，所以这里可能还需要写入一些准备工作
            $(':input', formId)
                .not(':button, :submit, :reset, :hidden, :radio')
                .val('')
                .removeAttr('checked')
                .removeAttr('selected');
        };

        /////////////////////////初始化
        self.init = function (isNew,id,text,rootText) {
            $(formId).validate();

            if (isNew == 1){
                //新增
                self.showAddModel(id,text,rootText);
            }else{
                //编辑
                self.showUpdateModel(id,text);
            }
        }; //end of init

        /////////////////////////显示新增对话框
        self.showAddModel = function (id,text,rootText) {
            self.clearModel();

            $("#hidId").val(0);

            //新增对话框打开前
            if (options.showAddBefore) {
                options.showAddBefore();
            }

            if (id == 0) {
                //根结点
                $("#parentId").val(0);
                $("#parentIdName").val("根结点");

            } else {
                $("#parentId").val(id);
                $("#parentIdName").val(rootText);
            }

            //新增对话框打开后
            if (options.showAddAfter) {
                options.showAddAfter();
            }
        };

        /////////////////////////显示更新对话框
         self.showUpdateModel = function(id) {
            //先清除对话框中的所有内容,
            self.clearModel();

             //更新对话框打开前
             if (options.showUpdateBefore) {
                 options.showUpdateBefore();
             }

            //根据id，读取对应的数据,再显示对话框
            if (id > 0) {
                $.ajax({
                    cache: false,
                    type: "GET",
                    url: prefix + "/get?id=" + id,

                    error: function (request) {
                        parent.layer.alert("网络连接出错，请稍候再试!!");
                    },
                    success: function (data) {
                        if (data.code <= 0) {
                            parent.layer.alert(data.msg);
                        } else {
                            //读取数据成功，数据放在data中
                            if(self.showUpdateBefore){
                                // 读取之前的处理,如果有的话
                                self.showUpdateAfter(data);
                            }

                            $("#hidId").val(id);

                            var myDTO = data.data;

                            //根据input的类型自动赋值
                            var allInputs = $(':input', formId);
                            var len = allInputs.length;

                            for (var i = 0; i < len; i++) {
                                var item = $(allInputs[i]);
                                var itemName = allInputs[i].name;
                                if (item.Name == 'hidId') {
                                    continue;//hidId 不处理，因为上面已经处理了
                                }

                                var nodeName = allInputs[i].nodeName;
                                if (nodeName == 'TEXTAREA') {
                                    item.val(myDTO[itemName]);//对textarea单独处理，因为它没有type
                                    continue;
                                }
                                else if (nodeName == 'SELECT') {
                                    var values = myDTO[itemName];
                                    if (values != null && values != '' && values != undefined) {
                                        //更新被选中的组
                                        if(values.length > 1 && values.indexOf(",") > -1) {
                                            var array = values.split(',');
                                            item.val(array).trigger("change");
                                        } else {
                                            item.val(values);
                                        }
                                    }
                                    else {
                                        item.val(null).trigger("change");
                                    }
                                    continue;
                                }
                                else if (nodeName == 'IMG') {
                                    item.attr("src", myDTO[itemName]);//对img单独处理，因为它没有type
                                    continue;
                                }

                                var itemType = item.attr("type");
                                switch (itemType) {
                                    case 'datetime':
                                    case 'datetime-local':
                                        var newV = myDTO[itemName];
                                        if (newV != null && newV != '') {
                                            newV = newV.replace('/', '-');
                                            newV = newV.replace(' ', 'T');
                                            item.val(newV);
                                        }
                                        break;
                                    case 'date':
                                        var newVa = myDTO[itemName];
                                        if (newVa != null && newVa != '') {
                                            newVa = newVa.replace('/', '-');
                                            newVa = newVa.substring(0, 10);//由于格式的问题，只取前10字符
                                            item.val(newVa);
                                        }
                                        break;
                                    case 'text':
                                    case 'textarea':
                                    case 'email':
                                    case 'hidden':
                                    case 'password':
                                        item.val(myDTO[itemName]);
                                        break;
                                    case 'checkbox':
                                    case 'radio':
                                        var value = myDTO[itemName];
                                        $( " input[name='" + itemName + "'][value=" + value + "]").attr('checked', 'checked');
                                        break;
                                     default:
                                        item.val(myDTO[itemName]);
                                        break;
                                }
                            }//end for

                            //如果还有其它的非标准控件，此处应当有代码

                            //更新对话框打开后
                            if (options.showUpdateAfter){
                                // 更新完后的处理,如果有的话
                                options.showUpdateAfter(data);
                            }

                            if (data.data.parentId > 0) {
                                //有父节点,则找到父节点的名称
                                self.getParentName(data.data.parentId);
                            } else {
                                //无父节点
                                $("#parentIdName").val("根节点");
                            }
                        }
                    }
                });
            }
        };

        self.insertOrUpdateForm = function() {
            // 1)判断是否输入是有效的
            if ($(formId).valid()) {
                //2)将所有的表单输入转为对象或键值对
                var formData = $(formId).serialize();

                //3)根据hidId来判断是插入或者更新
                var hidId = $("#hidId").val();
                var newUrl = "";

                if (hidId == '' || hidId == 0) {
                    //是插入,保存前
                    if (options.saveBefore) {
                         if (!options.saveBefore()) {
                            return;//不能继续执行
                        }
                    }
                    newUrl = prefix + "/save";
                } else {
                    //是更新,更新前
                    if (options.updateBefore) {
                        if (!options.updateBefore()) {
                            return;//不能继续执行
                        }
                    }
                    newUrl = prefix + "/update";
                }

                $.ajax({
                    cache: true,
                    type: "POST",
                    url: newUrl,
                    data: formData,// 你的formid
                    async: false,
                    error: function (data) {
                        parent.layer.alert("网络连接出错，请稍候再试!!");
                        //toastr["error"]("网络连接出错，请稍候再试!!");
                    },
                    success: function (data) {
                        if (data.code == 0) {
                            parent.layer.alert(data.msg);
                        } else {
                            parent.layer.msg(data.msg);
                            var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                            parent.layer.close(index);
                            trueParentObj.reLoadTable();
                        }

                        if (hidId == '' || hidId == 0) {
                            //是插入,保存后
                            if (options.saveAfter) {
                                options.saveAfter(data);
                            }
                        } else {
                            //是更新,更新后
                            if (options.updateAfter) {
                                options.updateAfter(data);
                            }
                        }
                    },
                    complete: function (data) {
                         //完成
                        }
                });
            }
        };

    };//end of  editModel

}//end of if typof(editmodel)