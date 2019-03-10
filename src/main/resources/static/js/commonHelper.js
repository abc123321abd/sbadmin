/**
 * Created by tms on 18/10/20.
 */

// 采用正则表达式获取地址栏参数：（ 强烈推荐，既实用又方便！）
//
//
// // 调用方法
// alert(GetQueryString("参数名1"));
// alert(GetQueryString("参数名2"));
// alert(GetQueryString("参数名3"));
// 下面举一个例子:
//
//     若地址栏URL为：abc.html?id=123&url=http://www.maidq.com
//
//     那么，但你用上面的方法去调用：alert(GetQueryString("url"));

function GetQueryString(name)
{
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);//search,查询？后面的参数，并匹配正则,注意：支持中文
    if (r != null) return decodeURI(r[2]); return null;

    //if(r!=null)return  unescape(r[2]); return null;
}

String.prototype.trim = function() {
    var str = this,
        str = str.replace(/^\s\s*/, ''),
        ws = /\s/,
        i = str.length;
    while (ws.test(str.charAt(--i)));
    return str.slice(0, i + 1);
}


// 对Date的扩展，将 Date 转化为指定格式的String 
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符， 
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) 
// 例子： 
// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423 
// (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18 
Date.prototype.Format = function (fmt) { //author: meizz 
    var o = {
        "M+": this.getMonth() + 1,                 //月份 
        "d+": this.getDate(),                    //日 
        "h+": this.getHours(),                   //小时 
        "m+": this.getMinutes(),                 //分 
        "s+": this.getSeconds(),                 //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds()             //毫秒 
    };
    if (/(y+)/.test(fmt))
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}


///////////////////////////////////////////////
//////////////////////////////////////////////
var Tms = Tms || {};

//判断是否是chrome浏览器
//判断是否为google的浏览器 
Tms.isChrome = function (value) {
    var userAgent = navigator.userAgent.toLowerCase();
    var edge = /edge/.test(userAgent);
    var chrome = /chrome/.test(userAgent);

    return chrome && !edge;
};

//添加一个转换性别的函数  by yzp 2015年1.11
Tms.formatSex = function (data)
{
    if (data == 0)
        return "0-不详";
    else if (data == 1)
        return "1-男";
    else
        return "2-女";
}

// Ajax 文件下载 
jQuery.download = function (url, data, method) {
    // 获取url和data 
    if (url && data) {
        // data 是 string 或者 array/object 
        data = typeof data == 'string' ? data : jQuery.param(data);
        // 把参数组装成 form的 input 
        var inputs = '';
        jQuery.each(data.split('&'), function () {
            var pair = this.split('=');
            inputs += '<input type="hidden" name="' + pair[0] + '" value="' + pair[1] + '" />';
        });
        // request发送请求 
        jQuery('<form action="' + url + '" method="' + (method || 'post') + '">' + inputs + '</form>')
        .appendTo('body').submit().remove();
    };
};//详细出处参考：http://www.jb51.net/article/33275.htm

//格式化Date
Tms.formatDate = function (value) {

    if (value == null || value == "") {
        return "";
    }

    var typeStr = typeof (value);

    if (typeStr == "object") {
        //value = value.toLocaleString();
    }
    else if (typeStr == 'string') {
        if (Tms.isChrome()) value = value.replace(/T|t|Z|z/g, ' ');
        value = new Date(Date.parse(value));
    }

    return value.Format("yyyy/MM/dd");
};

Tms.formatDateTime = function (value) {

    if (value == null || value == "") {
        return "";
    }

    var typeStr = typeof (value);

    if (typeStr == "object") {
        //value = value.toLocaleString();
    }
    else if (typeStr == 'string') {
        if (Tms.isChrome()) value = value.replace(/T|t|Z|z/g, ' ');
        value = new Date(Date.parse(value));
    }

    return value.Format("yyyy-MM-dd hh:mm:ss");
};

Tms.formatDateSelf = function (value) {

    if (value == null || value == "") {
        return "";
    }

    var typeStr = typeof (value);

    if (typeStr == "object") {
        //value = value.toLocaleString();
    }
    else if (typeStr == 'string') {
        if (Tms.isChrome()) value = value.replace(/T|t|Z|z/g, ' ');
        value = new Date(Date.parse(value));
    }

    return value.Format("yyyy/MM/dd"); //value.substring(0, 10);
};

//针对a.DCreateTime"/Date(1444888747117)/"这样的.net 格式进行格式化
Tms.formatDateTime4Net = function (value) {

    return Tms.formatDateTime(eval('new ' + eval(value).source));
}

//规范时间显示格式 2015.1.11 by lx
Tms.formatNewDate = function (date) {
    if (date == "" || date == null) {
        return "";
    }

    var dd = date.slice(0, 10);
    var ss = date.slice(11, 19);

    return dd + " " + ss;
}

Tms.formatTimeCurrent = function (value) {

    if (value == null || value == "") {
        return "";
    }

    var typeStr = typeof (value);

    if (typeStr == "object") {
        //value = value.toLocaleString();
    }
    else if (typeStr == 'string') {
        if (Tms.isChrome()) value = value.replace(/T|t|Z|z/g, ' ');
        value = new Date(Date.parse(value));
    }

    //计算时间差
    var curDate = new Date();
    var diffValue = curDate - value;

    if (diffValue < 0) {
        return value.Format("yyyy-MM-dd hh:mm:ss");
    }

    //时间之间的换算关系
    var minute = 1000 * 60;
    var hour = minute * 60;
    var day = hour * 24;
    var halfamonth = day * 15;
    var month = day * 30;

    var monthC = diffValue / month;
    var weekC = diffValue / (7 * day);
    var dayC = diffValue / day;
    var hourC = diffValue / hour;
    var minC = diffValue / minute;

    if (monthC >= 1) {
        return parseInt(monthC) + "个月前";
    }
    else if (weekC >= 1) {
        return parseInt(weekC) + "周前";
    }
    else if (dayC >= 1) {
        return parseInt(dayC) + "天前";
    }
    else if (hourC >= 1) {
        return parseInt(hourC) + "个小时前";
    }
    else if (minC >= 1) {
        return parseInt(minC) + "分钟前";
    }
    else {
        return "刚刚";
    }
}

Tms.optionItem = function (name, population) {
    this.name = name;
    this.value = population;
};//用于select option的类

Tms.getSelectedText = function (jqSelectObj) {
    return jqSelectObj.find("option:selected").text();
}

Tms.getChecked = function (checkboxName) {  //jquery获取复选框值    
    var chk_value = [];
    $('input[name="' + checkboxName + '"]:checked').each(function () {
        var newObj = new Object();
        newObj.id = $(this).val();
        newObj.text = $(this).attr("data-text");//如果有text的话
        chk_value.push(newObj);
    });
    //alert(chk_value.length == 0 ? '你还没有选择任何内容！' : chk_value);
    return chk_value;
}

Tms.setChecked = function (checkboxName, values) {  //jquery设定复选框值    
    var chk_value = [];
    var ids = values.split(",");//按,分割

    $('input[name="' + checkboxName + '"]').each(function () {
        var id = $(this).val();
        for (var m = 0; m < ids.length; m++) {
            if (ids[m] == id) {
                //需要checked
                $(this).attr("checked", true);//打勾
                break;
            }
        }
    });

    //alert(chk_value.length == 0 ? '你还没有选择任何内容！' : chk_value);
    return chk_value;
}

Tms.clearChecked = function (checkboxName) {  //jquery清空复选框值    
    $('input[name="' + checkboxName + '"]:checked').each(function () {

        $(this).attr("checked", false);//打勾
    });
    //alert(chk_value.length == 0 ? '你还没有选择任何内容！' : chk_value);
}

Tms.getScriptArgs = function () {//获取多个参数
    var scripts = document.getElementsByTagName("script"),
    script = scripts[scripts.length - 1],//因为当前dom加载时后面的script标签还未加载，所以最后一个就是当前的script
    src = script.src,
    reg = /(?:\?|&)(.*?)=(.*?)(?=&|$)/g,
    temp, res = {};
    while ((temp = reg.exec(src)) != null) res[temp[1]] = decodeURIComponent(temp[2]);
    return res;
};

//var args = getScriptArgs();
//alert(args.a + " | " + args.b + " | " + args.c);
//假如上面的js是在这个js1.js的脚本中<script type="text/javascript" src="js1.js?a=abc&b=汉字&c=123"></script>
Tms.getScriptArg = function (key) {//获取单个参数
    var scripts = document.getElementsByTagName("script"),
    script = scripts[scripts.length - 1],
    src = script.src;
    return (src.match(new RegExp("(?:\\?|&)" + key + "=(.*?)(?=&|$)")) || ['', null])[1];
};
//alert(getScriptArg("c"));

//只取一个：
Tms.queryStringKey = function (key) {
    return (document.location.search.match(new RegExp("(?:^\\?|&)" + key + "=(.*?)(?=&|$)")) || ['', null])[1];
}

Tms.queryString = function () {
    var url = location.href; //获取url中"?"符后的字串 
    var theRequest = new Object();
    var startI = url.indexOf("?");

    if (startI != -1) {
        var str = url.substr(startI + 1);
        strs = str.split("&");
        for (var i = 0; i < strs.length; i++) {
            theRequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);
        }
    }

    return theRequest;
}

//var args = Tms.queryString();
//alert(args.name + " | " + args.sex + " | " + args.age);
///取指定月的天数,month是1-12
Tms.getMonthDays = function (year, month) {
    var day = new Date(year, month, 0);
    return day.getDate();
}

//添加于12.22日
//去除多余的分割符，例如：",a",结果为"a"
Tms.dropRsplit = function (a, splitC) {
    var tmpA = a.split(splitC);
    var tmpObj = new Array();

    for (var i = 0; i < tmpA.length; i++) {
        if (tmpA[i] != "") {
            tmpObj.push(tmpA[i]);
        }
    }

    return tmpObj.join(splitC);
}

//使用selectbox.innerHTML=Tms.creatSelectTree(tree);来生成对应的数据
//var tree = [
//{
//    id: 19, pId: 0, name: 'nodejs',
//    children: [
//            { id: 20, pId: 19, name: 'express', children: [{ id: 60, pId: 20, name: 'ejs' }] },
//            { id: 21, pId: 19, name: 'mongodb' }
//    ]
//},
//{
//    id: 59, pId: 0, name: '前端开发',
//    children: [
//          { id: 70, pId: 59, name: 'javascript' },
//          { id: 71, pId: 59, name: 'css' },
//          { id: 72, pId: 59, name: 'html' },
//          { id: 73, pId: 59, name: 'bootstrap' }
//    ]
//},
//{ id: 61, pId: 0, name: '视觉设计', children: [{ id: 63, pId: 61, name: '网页设计' }] },
//];
Tms.treeAfix = "";//树的前缀

//将[
//{ id: 19, pId: 0, name: 'nodejs' },
//{ id: 20, pId: 19, name: 'express' },
//{ id: 21, pId: 19, name: 'mongodb' },
//{ id: 60, pId: 20, name: 'ejs' },
//{ id: 59, pId: 0, name: '前端开发' },
//{ id: 70, pId: 59, name: 'javascript' },
//{ id: 71, pId: 59, name: 'css' },
//{ id: 72, pId: 59, name: 'html' },
//{ id: 73, pId: 59, name: 'bootstrap' },
//{ id: 61, pId: 0, name: '视觉设计' },
//{ id: 63, pId: 61, name: '网页设计' },
//]转为上面的形式
Tms.convert2tree = function (list, pId) {
    var ret = [];//一个存放结果的临时数组

    for (var i in list) {
        if (list[i].pId == pId) {//如果当前项的父id等于要查找的父id，进行递归
            list[i].children = Tms.convert2tree(list, list[i].id);
            ret.push(list[i]);//把当前项保存到临时数组中
        }
    }

    return ret;//递归结束后返回结果
}

Tms.creatSelectTree = function (d, first, usekey) {
    var option = "";

    if (first != null && first != "") {
        option = first;//first用于展示请选择之类的
    }

    for (var i = 0; i < d.length; i++) {
        if (d[i].children) {
            //如果有子集
            option += "<option value='" + ((usekey == null || usekey == "" || parseInt(usekey) <= 0) ? d[i].id : d[i].key) + "'>" + Tms.treeAfix + d[i].name + "</option>";
            Tms.treeAfix += "&nbsp;&nbsp;&nbsp;";//前缀符号加一个符号
            option += Tms.creatSelectTree(d[i].children, "", usekey);//递归调用子集
            Tms.treeAfix = Tms.treeAfix.slice(0, Tms.treeAfix.length - 18);//每次递归结束返回上级时，前缀符号需要减一个符号
        }
        else {
            //没有子集直接显示
            option += "<option value='" + ((usekey == null || usekey == "" || parseInt(usekey) <= 0) ? d[i].id : d[i].key) + "'>" + Tms.treeAfix + d[i].name + "</option>";
        }
    }

    return option;//返回最终html结果
}

$(function () {
    $("#mainMenu li a").click(function () {
        var selText = $(this).text();
        if (this.href.indexOf("#logout") > -1) {
            $.ajax({
                url: "/api/Login/ClearSession", 
                type: "GET", 
                success: function (data, text) {
                       window.location.reload();
                   }
            })
            return;
        }

        if (this.href != "" && this.href != "#") {
            window.location = this.href;
        }
    });

    $._messengerDefaults = {
        extraClasses: 'messenger-fixed messenger-theme-future messenger-on-bottom messenger-on-right'
    }; //默认提示位置;

    // 手机号码验证
    jQuery.validator.addMethod("mobile", function (value, element) {
        var length = value.length;
        //var mobile = /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/
        var mobile = /^0?(13[0-9]|14[5-9]|15[012356789]|166|17[0-8]|18[0-9]|19[89])[0-9]{8}$/;      //验证规则
        return this.optional(element) || (length == 11 && mobile.test(value));
    }, "手机号码格式错误");

    // 电话号码验证   
    jQuery.validator.addMethod("phone", function (value, element) {
        var tel = /^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$/;
        return this.optional(element) || (tel.test(value));
    }, "电话号码格式错误");

    // 邮政编码验证   
    jQuery.validator.addMethod("zipCode", function (value, element) {
        var tel = /^[0-9]{6}$/;
        return this.optional(element) || (tel.test(value));
    }, "邮政编码格式错误");

    // QQ号码验证   
    jQuery.validator.addMethod("qq", function (value, element) {
        var tel = /^[1-9]\d{4,9}$/;
        return this.optional(element) || (tel.test(value));
    }, "qq号码格式错误");

    // IP地址验证
    jQuery.validator.addMethod("ip", function (value, element) {
        var ip = /^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/;
        return this.optional(element) || (ip.test(value) && (RegExp.$1 < 256 && RegExp.$2 < 256 && RegExp.$3 < 256 && RegExp.$4 < 256));
    }, "Ip地址格式错误");

    // 字母和数字的验证
    jQuery.validator.addMethod("chrnum", function (value, element) {
        var chrnum = /^([a-zA-Z0-9]+)$/;
        return this.optional(element) || (chrnum.test(value));
    }, "只能输入数字和字母(字符A-Z, a-z, 0-9)");

    // 中文的验证
    jQuery.validator.addMethod("chinese", function (value, element) {
        var chinese = /^[\u4e00-\u9fa5]+$/;
        return this.optional(element) || (chinese.test(value));
    }, "只能输入中文");

    // 下拉框验证
    $.validator.addMethod("selectNone", function (value, element) {
        return value == "请选择";
    }, "必须选择一项");

    // 字节长度验证
    jQuery.validator.addMethod("byteRangeLength", function (value, element, param) {
        var length = value.length;
        for (var i = 0; i < value.length; i++) {
            if (value.charCodeAt(i) > 127) {
                length++;
            }
        }
        return this.optional(element) || (length >= param[0] && length <= param[1]);
    }, $.validator.format("请确保输入的值在{0}-{1}个字节之间(一个中文字算2个字节)"));

    // 字符验证   
    jQuery.validator.addMethod("stringCheck", function (value, element) {
        return this.optional(element) || /^[\u0391-\uFFE5\w]+$/.test(value);
    }, "只能包括中文字、英文字母、数字和下划线");

    // 中文字两个字节   
    jQuery.validator.addMethod("byteRangeLength", function (value, element, param) {
        var length = value.length;
        for (var i = 0; i < value.length; i++) {
            if (value.charCodeAt(i) > 127) {
                length++;
            }
        }
        return this.optional(element) || (length >= param[0] && length <= param[1]);
    }, "请确保输入的值在3-15个字节之间(一个中文字算2个字节)");

    // 身份证号码验证   
    jQuery.validator.addMethod("isIdCardNo", function (value, element) {
        return this.optional(element) || isIdCardNo(value);
    }, "请正确输入您的身份证号码");

    // 手机号码验证   
    jQuery.validator.addMethod("isMobile", function (value, element) {
        var length = value.length;
        //var mobile = /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/;
        //var mobile = /^1[3|4|5|6|7|8|9][0-9]{9}$/;      //验证规则
        var mobile = /^0?(13[0-9]|14[5-9]|15[012356789]|166|17[0-8]|18[0-9]|19[89])[0-9]{8}$/;      //验证规则
        return this.optional(element) || (length == 11 && mobile.test(value));
    }, "请正确填写您的手机号码,格式1XXXXXXXXXX");

    // 电话号码验证   
    jQuery.validator.addMethod("isTel", function (value, element) {
        var tel = /^\d{3,4}-?\d{7,9}$/; //电话号码格式010-12345678   
        return this.optional(element) || (tel.test(value));
    }, "请正确填写您的电话号码,格式010-12345678");

    // 联系电话(手机/电话皆可)验证   
    jQuery.validator.addMethod("isPhone", function (value, element) {
        var length = value.length;
        //var mobile = /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/;
        var mobile = /^0?(13[0-9]|14[5-9]|15[012356789]|166|17[0-8]|18[0-9]|19[89])[0-9]{8}$/;      //验证规则
        var tel = /^\d{3,4}-?\d{7,9}$/;
        return this.optional(element) || (tel.test(value) || mobile.test(value));

    }, "请正确填写您的联系电话");

    // 邮政编码验证   
    jQuery.validator.addMethod("isZipCode", function (value, element) {
        var tel = /^[0-9]{6}$/;
        return this.optional(element) || (tel.test(value));
    }, "请正确填写您的邮政编码");

    // 身份证号码验证
    jQuery.validator.addMethod("isIdCardNo", function (value, element) {
        return this.optional(element) || isIdCardNo(value);
    }, "请正确输入您的身份证号码");

    //高考报名号验证   
    jQuery.validator.addMethod("isGkbmh", function (value, element) {
        var length = value.length;
        var gkbmh = /^19+\d{12}$/;
        return this.optional(element) || (length == 14 && gkbmh.test(value));
    }, "请正确填写19开头的14位考生号");

    //高考成绩范围验证   
    jQuery.validator.addMethod("isGkcjFw", function (value, element) {
        var cj = parseInt(value);
        var gkcjFw = /^\d+(\.\d+)?$/;
        return this.optional(element) || (cj > 200 && cj < 900 && gkcjFw.test(value));
    }, "请正确填写高考成绩（200~900）");

    //日期格式验证   
    jQuery.validator.addMethod("isDateFormat", function (value, element) {
        var dateFormat = /^((\d{4}-\d{1,2}-\d{1,2})|(\d{4}.\d{1,2}.\d{1,2})|(\d{4}\/\d{1,2}\/\d{1,2}))$/;
        return this.optional(element) || dateFormat.test(value);
    }, "请正确填写日期格式:yyyy-mm-dd或yyyy.mm.dd");

    //增加身份证验证
    function isIdCardNo(num) {
        var factorArr = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1);  /*每位加权因子*/
        var parityBit = new Array("1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2");  /*第18位校检码*/
        var varArray = new Array();
        var intValue;
        var lngProduct = 0;
        var intCheckDigit;
        var intStrLen = num.length;
        var idNumber = num;

        // initialize
        if ((intStrLen != 10) && (intStrLen != 15) && (intStrLen != 18)) {
            return false;
        }

        //验证港澳台身份证合法性
        if (intStrLen == 10) {
            if (isHkIdCardNo(idNumber)) {
                //验证香港身份证合法性
                return true;
            }
            else if (isTwIdCardNo(idNumber)){
                //验证台湾身份证
                return true;
            }
            else {
                //验证澳门身份证
                return isMoIdCardNo(idNumber);
            }
        }

        //验证大陆身份证
        // check and set value
        for (i = 0; i < intStrLen; i++) {
            varArray[i] = idNumber.charAt(i);
            if ((varArray[i] < '0' || varArray[i] > '9') && (i != 17)) {
                return false;
            } else if (i < 17) {
                varArray[i] = varArray[i] * factorArr[i];
            }
        }

       if (intStrLen == 18) {
            //check date
            var date8 = idNumber.substring(6, 14);
            if (isDate8(date8) == false) {
                return false;
            }
            // calculate the sum of the products
            for (i = 0; i < 17; i++) {
                lngProduct = lngProduct + varArray[i];
            }
            // calculate the check digit
            intCheckDigit = parityBit[lngProduct % 11];
            // check last digit
            if (varArray[17] != intCheckDigit) {
                return false;
            }
        }
        else {        //length is 15
            //check date
            var date6 = idNumber.substring(6, 12);
            if (isDate6(date6) == false) {
                return false;
            }
        }
        return true;
    }

    //验证香港身份证合法性
    function isHkIdCardNo(str) {
        var strValidChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        // basic check length
        if (str.length < 8)
            return false;

        // handling bracket
        if (str.charAt(str.length - 3) == '(' && str.charAt(str.length - 1) == ')')
            str = str.substring(0, str.length - 3) + str.charAt(str.length - 2);

        // convert to upper case
        str = str.toUpperCase();

        // regular expression to check pattern and split
        var hkidPat = /^([A-Z]{1,2})([0-9]{6})([A0-9])$/;
        var matchArray = str.match(hkidPat);

        // not match, return false
        if (matchArray == null)
            return false;

        // the character part, numeric part and check digit part
        var charPart = matchArray[1];
        var numPart = matchArray[2];
        var checkDigit = matchArray[3];

        // calculate the checksum for character part
        var checkSum = 0;

        if (charPart.length == 2) {
            checkSum += 9 * (10 + strValidChars.indexOf(charPart.charAt(0)));
            checkSum += 8 * (10 + strValidChars.indexOf(charPart.charAt(1)));
        } else {
            checkSum += 9 * 36;
            checkSum += 8 * (10 + strValidChars.indexOf(charPart));
        }

        // calculate the checksum for numeric part
        for (var i = 0, j = 7; i < numPart.length; i++, j--)
            checkSum += j * numPart.charAt(i);

        // verify the check digit
        var remaining = checkSum % 11;
        var verify = remaining == 0 ? 0 : 11 - remaining;

        return verify == checkDigit || (verify == 10 && checkDigit == 'A');
    }

    //验证澳门身份证合法性
    function isMoIdCardNo(idNo) {
        var reg = /^[1|5|7][0-9]{6}\([0-9Aa]\)/;
        return reg.test(idNo);
    }

    /** 
    * 验证台湾身份证合法性
    * 國民身分證統一編號：共有十位，第一位為英文字母，以後九位為數字 
    * L1   D1  D2  D3  D4  D5  D6  D7  D8  D9 
    * 計算方法： 
    * a.   字母L1由表(二)中，找到其代號兩位，令其為X1、X2。X1為十位，X2為個位。 
    * b.   計算方法： 
    * Y=X1 + 9 * X2 +8 * D1+7 * D2+6 * D3+5 * D4+4 * D5 + 3 * D6 + 2 * D7 + D8 +D9 
    * 如：M120675371   合法真实的身份证号 
    * 表(二) 
    * 序號   1   2   3   4   5   6   7   8   9   10  11  12  13  14  15  16  17  18  19  20  21  22  23  24  25  26 
    * 字母   A   B   C   D   E   F   G   H   J   K   L   M   N   P   Q   R   S   T   U   V   X   Y   W   Z   I   O 
    * 代號   10  11  12  13  14  15  16  17  18  19  20  21  22  23  24  25  26  27  28  29  30  31  32  33  34  35 
    *  
    */ 
    function isTwIdCardNo(idNo){ 
        if (idNo == null || idNo == ""){
            return false;//JSON.parse('{“respCode”:”9”,”respDesc”:”空值”}');
        } 
    
        if (idNo.length != 10){
            return false;//JSON.parse('{“respCode”:”8”,”respDesc”:”國民身分證統一編號長度不正確(10)”}');
        } 
    
        var eng = idNo.substring(0,1); 
        var num = idNo.substring(1,10); 
        var sex = idNo.substring(1,2); 

        eng = eng.toUpperCase(); 
        var regNum = /^[0-9]+.?[0-9]*/;//判断字符串是否为数字//判断正整数/[1−9]+[0−9]∗]∗/;//判断字符串是否为数字//判断正整数/[1−9]+[0−9]∗]∗/ 
        var regEng=/^[A-Za-z]+$/; 
      
        if (!regNum.test(num)){ 
            return false ;//JSON.parse('{“respCode”:”1”,”respDesc”:”國民身分證統一編號不正確”}');
        } 

        if (sex != 1 && sex != 2){
            return false;//JSON.parse('{“respCode”:”1”,”respDesc”:”國民身分證統一編號不正確”}');
        } 

        if (!regEng.test(eng)){
            return false;//JSON.parse('{“respCode”:”1”,”respDesc”:”國民身分證統一編號不正確”}');
        }
        
        //var key=['A','B','C','D','E','F','G','H','J','K','L','M','N','P','Q','R','S','T','U','V','X','Y','W','Z','I','O']; 
        var key = "ABCDEFGHJKLMNPQRSTUVXYWZIO"; 
        var value = ['10','11','12','13','14','15','16','17','18','19','20','21','22','23','24','25','26','27','28','29','30','31','32','33','34','35']; 
        var pos = key.indexOf(eng); 
       
        if (pos == -1) {
            return false;//JSON.parse('{“respCode”:”1”,”respDesc”:”國民身分證統一編號不正確”}');
        }

        var x1 = value[pos].substring(0, 1);
        var x2 = value[pos].substring(1,2); 
        var y = parseInt(x1)+9*parseInt(x2) +8*parseInt(num.substring(0,1))+7*parseInt(num.substring(1,2))+ 
                6*parseInt(num.substring(2,3))+5*parseInt(num.substring(3,4))+ 
                4*parseInt(num.substring(4,5)) + 3*parseInt(num.substring(5,6))+ 
                2*parseInt(num.substring(6,7)) + parseInt(num.substring(7,8)); 
        var checkResult = y % 10;

        if ((10-checkResult) == num.substring(8,9)){ 
            return true;//JSON.parse('{“respCode”:”0”,”respDesc”:”success”}');
        }
        else {
            if (checkResult == 10 && num.substring(8,9) == 0){ 
                //若取余之后的结果为0，那么末尾的数为0 
                return true;//JSON.parse('{“respCode”:”0”,”respDesc”:”success”}');
            }
            else {
                return false;//JSON.parse('{“respCode”:”1”,”respDesc”:”國民身分證統一編號不正確”}');
            } 
        }  
    }

    function isDate6(sDate) {
        if (!/^[0-9]{6}$/.test(sDate)) {
            return false;
        }
        var year, month, day;
        year = sDate.substring(0, 4);
        month = sDate.substring(4, 6);
        if (year < 1700 || year > 2500) return false
        if (month < 1 || month > 12) return false
        return true
    }

    function isDate8(sDate) {
        if (!/^[0-9]{8}$/.test(sDate)) {
            return false;
        }
        var year, month, day;
        year = sDate.substring(0, 4);
        month = sDate.substring(4, 6);
        day = sDate.substring(6, 8);
        var iaMonthDays = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]
        if (year < 1700 || year > 2500) return false
        if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) iaMonthDays[1] = 29;
        if (month < 1 || month > 12) return false
        if (day < 1 || day > iaMonthDays[month - 1]) return false
        return true;
    }

    //输入提示格式验证1：有两个":"号
    $.validator.addMethod("maohao2", function (value, element) {
        var reg = /\w{0,}[\u4e00-\u9fa5]{0,}[:]\w{0,}[\u4e00-\u9fa5]{0,}\w{0,}[:]\w{0,}[\u4e00-\u9fa5]{0,}/;
        return this.optional(element) || (reg.test(value));
    }, "格式不正确,请从下拉列表中选择")

    //输入提示格式验证1：有一个":"号
    $.validator.addMethod("maohao1", function (value, element) {
        var reg = /\w{0,}[\u4e00-\u9fa5]{0,}[:]\w{0,}[\u4e00-\u9fa5]{0,}/;
        return this.optional(element) || (reg.test(value));
    }, "格式不正确,请从下拉列表中选择");
});


///////////////////////////////////////////////////////////////
//
///////////////////////////////////////////////////////////////
var Tsw = Tsw || {};

//是否有效使用时间
//返回：0-无效；1-有效
Tsw.IsValidUseDate = function (useStartDate, useEndDate) {
    var startDate = Tms.formatDateTime(useStartDate);
    var endDate = Tms.formatDateTime(useEndDate);
    var currentDate = Tms.formatDateTime(new Date());

    return (currentDate >= startDate && currentDate <= endDate) ? 1 : 0;
}

//获取select2的value字符串的拼接
Tsw.getSelect2Value = function (controlId) {
    var str = "";
    var array = $("#" + controlId).val();

    if (array == null || array.length == 0) {
        return "";
    }

    //拼接字符串
    for (var i = 0; i < array.length; i++) {
        str += array[i] + ',';
    }

    //删除多余的逗号
    str = Tms.dropRsplit(str, ",");

    return str;
}

//获取select2的text字符串的拼接
Tsw.getSelect2Text = function (controlId) {
    var str = "";
    var options = $("#" + controlId + " option:selected");//获取当前选中的项  

    if (options == null || options.length == 0) {
        return "";
    }

    //拼接字符串
    for (var i = 0; i < options.length; i++) {
        str += options[i].text + ',';
    }

    //删除多余的逗号
    str = Tms.dropRsplit(str, ",");

    return str;
}

//设置select2的value字符串的拼接
Tsw.setSelect2Value = function (controlId, values) {
    if (values != null && values.length > 0) {
        //更新被选中的组
        var array = values.split(',');
        $("#" + controlId).val(array).trigger("change");
    }
    else {
        $("#" + controlId).val(null).trigger("change");
    }
}

//支付方式
Tsw.changePayWay = function (data) {
    var str = "未知";

    switch (parseInt(data)) {
        case 1:
            str = "线下";
            break;
        case 2:
            str = "微信";
            break;
        case 3:
            str = "支付宝";
            break;
        case 4:
            str = "银联";
            break;
        default:
            str = "未知";
            break;
    }

    return str;
}

//支付状态
Tsw.changePayState = function (data) {
    var str = "未知";

    switch (parseInt(data)) {
        case 0:
            str = "未支付";
            break;
        case 1:
            str = "正在支付";
            break;
        case 2:
            str = "已经支付";
            break;
        case -1:
            str = "取消支付";
            break;
        case -2:
            str = "退款";
            break;
        default:
            str = "未知";
            break;
    }

    return str;
}

//编辑表单内容
Tsw.editFormContent = function (formId, edit) {
    var form = $("#" + formId);

    if (form == null) {
        return;
    }

    for (var i = 0; i < form[0].length; i++) {
        var element = form[0].elements[i];
        element.disabled = !edit;
    }
}

//元素改变查询
Tsw.changeElementSearch = function (formId, func) {
    var form = $("#" + formId);

    if (form == null) {
        return;
    }

    for (var i = 0; i < form[0].length; i++) {
        var element = form[0].elements[i];
        //元素改变查询
        element.onchange = function () {
            func();
        };
    }
}


////////////////////////////////////////////
//用到的一些常数
////////////////////////////////////////////
//获取性别类型选项
Tsw.getSexTypeOptionItems = function ()
{
    return [new Tms.optionItem("0-不详", 0),
            new Tms.optionItem("1-男", 1),
            new Tms.optionItem("2-女", 2)];
}

//获取是否删除选项
Tsw.getIsDeleteOptionItems = function ()
{
    return [new Tms.optionItem("0-不可删除", 0),
            new Tms.optionItem("1-可删除", 1)];
}

//获取是否显示选项
Tsw.getIsShowOptionItems = function ()
{
    return [new Tms.optionItem("0-不显示", 0),
            new Tms.optionItem("1-显示", 1)];
}

//获取是否允许选项
Tsw.getIsEnableOptionItems = function ()
{
    return [new Tms.optionItem("0-不允许", 0),
            new Tms.optionItem("1-允许", 1)];
}

//获取用户类型选项
Tsw.getUserTypeOptionItems = function ()
{
    return [new Tms.optionItem("1-系统用户", 1),
            new Tms.optionItem("2-院系工作人员", 2),
            new Tms.optionItem("3-基地工作人员", 3),
            new Tms.optionItem("4-微信用户", 4)];
}

//获取新闻位置选项
Tsw.getNewsPositionOptionItems = function ()
{
    return [new Tms.optionItem("0-左边", 0),
            new Tms.optionItem("1-中间",1),
            new Tms.optionItem("2-右边", 2)];
}

//获取新闻状态选项
Tsw.getNewsStateOptionItems = function ()
{
    return [new Tms.optionItem("0-预览", 0),
            new Tms.optionItem("1-发布", 1)];
}

//获取新闻状态选项
Tsw.getAlbumTypeOptionItems = function ()
{
    return [new Tms.optionItem("0-图片相册", 0),
            new Tms.optionItem("1-视频相册", 1)];
}

//获取表单状态选项
Tsw.getFormStateOptionItems = function ()
{
    return [new Tms.optionItem("1-未提交", 1),
            new Tms.optionItem("2-已经提交", 2),
            new Tms.optionItem("3-审核中", 3),
            new Tms.optionItem("4-已经驳回", 4),
            new Tms.optionItem("5-审核通过", 5)];
}

//获取表单设计状态选项
Tsw.getFormDesignStateOptionItems = function ()
{
    return [new Tms.optionItem("0-生成", 0),
            new Tms.optionItem("1-编辑", 1),
            new Tms.optionItem("11-发布", 11)];
}

//获取表单填写状态选项
Tsw.getFormFillStateOptionItems = function ()
{
    return [new Tms.optionItem("1-新建", 1),
            new Tms.optionItem("2-填写中", 2),
            new Tms.optionItem("3-提交", 3),
            new Tms.optionItem("4-删除", 4)];
}

//获取填写设备类型选项
Tsw.getFillDeviceTypeOptionItems = function ()
{
    return [new Tms.optionItem("0-不详", 0),
            new Tms.optionItem("1-Web", 1),
            new Tms.optionItem("2-微信", 2),
            new Tms.optionItem("3-手机", 3)];
}

//获取微信二维码是否临时选项
Tsw.getWxQrCodeIsTempOptionItems = function (data) {
    return [new Tms.optionItem("0-永久", 0),
            new Tms.optionItem("1-临时", 1)];
}

//获取微信问题状态选项
Tsw.getWxQuestionStateOptionItems = function () {
    return [new Tms.optionItem("0-已提问", 0),
            new Tms.optionItem("1-已经回答", 1),
            new Tms.optionItem("2-已解答", 2),
            new Tms.optionItem("3-已经删除", 3)];
}

//获取消息读状态选项
Tsw.getMsgReadStateOptionItems = function () {
    return [new Tms.optionItem("0-未读", 0),
            new Tms.optionItem("1-不需要读", 1),
            new Tms.optionItem("2-已读", 2)];
}

//获取中学类型选项
Tsw.getSchoolTypeOptionItems = function () {
    return [new Tms.optionItem("0-普通中学", 0),
            new Tms.optionItem("1-重点中学", 1)];
}

//获取表单类型选项
Tsw.getFormTypeOptionItems = function (data) {
    return [new Tms.optionItem("1-普通表单", 1),
            new Tms.optionItem("2-工作流表单", 2)];
}

//获取新闻置顶选项
Tsw.getGoTopOptionItems = function (data) {
    return [new Tms.optionItem("0-不置顶", 0),
            new Tms.optionItem("1-置顶", 1)];
}

//获取用户状态选项
Tsw.getUserStatusOptionItems = function ()
{
    return [new Tms.optionItem("0-禁用", 0),
        new Tms.optionItem("1-正常", 1)];
}

////////////////////
//转换用户类型
Tsw.changeUserType = function (data) {
    var result = "未知用户";

    switch (parseInt(data)) {
        case 1:
            result = "系统用户";
            break;
        case 2:
            result = "院系工作人员";
            break;
        case 3:
            result = "基地工作人员";
            break;
        case 4:
            result = "微信用户";
            break;
    }

    return result;
}

//转换新闻状态
Tsw.changeNewsState = function (data) {
    return (data != null && parseInt(data) > 0) ? "发布" : "预览";
}

//转换相册类型
Tsw.changetAlbumType = function (data) {
    if (data == 0) {
        return "图片相册";
    }
    else {
        return "视频相册";
    }
}

//转换是否显示
Tsw.changeIsShow = function (data) {
    if (data  > 0) {
        return "显示";
    }
    else {
        return "不显示";
    }
}

//转换表单状态
Tsw.changeFormState = function(data) {
    switch (parseInt(data)) {
        case 1:
            return "未提交";
        case 2:
            return "已经提交";
        case 3:
            return "审核中";
        case 4:
            return "已经驳回";
        case 5:
            return "审核通过";
    }

    return "未提交";
}

//转换填表设备类型
Tsw.changeFillDeviceType = function(data) {
    var info = ["不详", "Web", "微信", "手机"];

    return info[data];
}

//转换表单类型
Tsw.changeFormType = function (data) {
    if (data == 1) {
        return "普通表单";
    }
    else if (data == 2) {
        return "工作流表单";
    }
    else
    {
        return "";
    }
}

//转换表单设计状态
Tsw.changeFormDesignState = function (data) {
    if (data == 11) return "发布";
    if (data == 0) return "生成";
    if (data == 1) return "编辑";
};

//转换微信用户类型
Tsw.changeWxUserType = function (data) {
    if (data == 1) {
        return "学生";
    }
    else if (data == 2) {
        return "家长";
    }

    return "不详";
}

//转换微信用户来源类型
Tsw.changeWxFromType = function (data) {
    if (data == 1) {
        return "本站";
    }
    else if (data == 2) {
        return "微信";
    }

    return "其它";
}

//转换微信分组上限标志
Tsw.changeWxGroupUpperLimitFlag = function(data) {
    if (data > 0) {
        return "人数已达上限";
    }
    else {
        return "人数未达上限";
    }
}

//转换微信分组同步状态
Tsw.changeWxGroupSyncState = function(data) {
    if (data > 0) {
        return "已经同步";
    }
    else {
        return "未同步";
    }
}

//转换微信分组当前状态
Tsw.changeWxGroupCurrentState = function(data) {
    if (data > 0) {
        return "有效";
    }
    else {
        return "无效";
    }
}

//转换微信二维码是否临时
Tsw.changeWxQrCodeIsTemp = function (data) {
    if (data > 0) {
        return "临时";
    }
    else {
        return "永久";
    }
}

//转换微信问题状态
Tsw.changeWxQuestionState = function(data) {
    if (data == 0) {
        return "已经提问";
    }

    if (data == 1) {
        return "已经回答";
    }

    if (data == 2) {
        return "已经解答";
    }

    if (data) {
        return "已经删除";
    }
}

//转换消息读状态
Tsw.changeMsgReadState = function(data) {
    if (data == 0)
        return "未读";
    else if (data == 1)
        return "不需要读";
    else
        return "已读";
}

//转换中学类型
Tsw.changeSchoolType = function (data) {
    if (data > 0) {
        return "重点中学";
    }
    else {
        return "普通中学";
    }
}

//转换是否允许他人访问权限
Tsw.changeIsEnable = function (data) {
    return (parseInt(data) > 0) ? "允许" : "不允许"
}

//////////////////////
//转换是否允许他人访问权限
Tsw.changeIsEnable1 = function (data) {
    return (parseInt(data) > 0) ? "共享" : "私有"
}

//////////////////////
//转换新闻置顶
Tsw.changeGoTop = function (data) {
    return (parseInt(data) > 0) ? "置顶" : "不置顶"
}

//转换用户状态
Tsw.changeUserStatus = function (data)
{
    return (parseInt(data) > 0) ? "1-正常" : "0-禁用"
}