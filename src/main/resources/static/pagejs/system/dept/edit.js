/**
 * Created by tms on 18/10/20.
 */

var formId = "#formdept";
var prefix = "/webapi/system/dept";
var trueParentObj;//真正的父对象的表格
var myEdit;

//增加或更新表單按鈕
function insertOrUpdateForm(){
    myEdit.insertOrUpdateForm();//更新当前结点，真正的方法
}

$(function () {
    var isNew = GetQueryString("isNew");
    var hidId = GetQueryString("id");
    var parentName = GetQueryString("parentname");
    var rootText = decodeURI(GetQueryString("rootText"));

    $("#hidId").val(hidId);

    var options = {
        ctrfix :"/system/dept",//当前控制器

        getURL:function(id){
            return prefix + "/get?id=" + id;
        },
        saveURL:function(){
            return prefix + "/save";
        },
        updateURL:function(){
            return prefix + "/update";
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
        }
    }

    myEdit = new editModel(options);
    myEdit.init(isNew,hidId,parentName,rootText);

    //showUpdateModel(hidId,parentName);
    var parentTag = GetQueryString("tag");
    trueParentObj = top.tabArr[parentTag];
})

