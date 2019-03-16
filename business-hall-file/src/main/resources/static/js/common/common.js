/**
 * 制作人： 王月
 * 制作时间：2017/5/15
 * 制作目的：项目使用
 * 功能：公共方法
 * */
var QueryPath = {//相对请求路径的前缀

	//	测试
//	serverUrl : "http://58.51.90.231:8090/cloud-clinic/api/",
//	fileUrl : "http://58.51.90.231:8090/cloud-clinic/api/",	

//	正式
//	serverUrl : "http://120.24.79.125:8090/cloud-clinic/api/",
//	fileUrl : "http://120.24.79.125:8090/cloud-clinic/api/",	

//  serverUrl : "http://localhost:8090/cloud-clinic/api/",
//  fileUrl : "http://localhost:8090/cloud-clinic/api/",

	serverUrl : "http://192.168.0.166:8090/cloud-clinic/api/",
	fileUrl : "http://192.168.0.166:8090/cloud-clinic/api/",	
	commonFileUrl : "http://58.51.90.231:8085/core-file/api/",

    getServerUrl : function(){
        return this.serverUrl;
    },
    getCommonFileUrl : function(){
        return this.commonFileUrl;
    }
};
window.serverUrl = QueryPath.getServerUrl();
window.getCommonFileUrl = QueryPath.getCommonFileUrl();


/**
 * 包含常用的方法
 * @author 王月
 * @version 20170510
 */

var wyCommon = $.extend({}, wyCommon);/* 定义全局对象，类似于命名空间或包的作用 */

String.prototype.Trim = function() { return this.replace(/(^\s*)|(\s*$)/g, "");}
/**
 * @author 王月
 * @requires jQuery
 * 将form表单元素的值序列化成对象
 * @returns object
 */
wyCommon.serializeObject = function(form) {
    var o = {};
    $.each(form.serializeArray(), function(index) {
        if (o[this['name']]) { 
            o[this['name']] = o[this['name']] + "," + this['value'];
        } else {
            o[this['name']] = this['value'];
        }
    });
    return o;
};

/**
 * @author 王月
 * @requires jQuery
 * 将form表单元素的值序列化成对象(来自互联网)
 * @returns object
 */
(function($) {
    $.fn.serializeJson = function() {
        var serializeObj = {};
        var array = this.serializeArray();
        var str = this.serialize();
        $(array).each(
            function() {
                if (serializeObj[this.name]) {
                    if ($.isArray(serializeObj[this.name])) {
                        serializeObj[this.name].push(this.value);
                    } else {
                        serializeObj[this.name] = [
                            serializeObj[this.name], this.value ];
                    }
                } else {
                    serializeObj[this.name] = this.value;
                }
            });
        return serializeObj;
    };
})(jQuery);

/**
 * @author 王月
 * @requires jQuery
 * 获取用户请求url后的参数
 * @returns object
 */
//获取用户请求字符串
wyCommon.getRequest = function(){
    var url = location.search; //获取url中"?"符后的字串
    //url = unescape(url);
    var theRequest = new Object();
    if (url.indexOf("?") != -1) {
        var str = url.substr(1);
        strs = str.split("&");
        for(var i = 0; i < strs.length; i ++) {
            theRequest[strs[i].split("=")[0]]=(strs[i].split("=")[1]);
        }
    }
    return theRequest;
};

/**
 * 通过 ajax 请求并返回数据去执行回调函数
 * @param param
 * @param fn
 */
wyCommon.ajaxReqCallBack = function(param,fnSuccess,fnError){
    $.ajax({
        type : param.type || 'POST',
        url : param.url,
        data : param.data || {},
        async : true,  //默认异步
        cache : false,  //清除缓存
        dataType :'json',
        contentType:"application/json; charset=utf-8",
        timeout : 60000, //超时时间设置，单位毫秒
        headers: {
            sssdfd: "dfgfgfgfg"
        },
        success : function(data){
            fnSuccess(param,data);
        },
        //error: function (XMLHttpRequest, textStatus, errorThrown) {
        //    param.XMLHttpRequest = XMLHttpRequest;
        //    param.textStatus = textStatus;
        //    param.errorThrown = errorThrown;
        //    fnError(param,data);
        //}
        error: function (XMLHttpRequest, textStatus) {
//      	console.info(XMLHttpRequest);
        
            if(XMLHttpRequest && XMLHttpRequest.statusText && XMLHttpRequest.statusText == "timeout"){
                //统一处理请求延时的问题
                layer.alert('哎呀，您的网络好像有问题，请检查网络连接。', {icon: 5,skin: 'wyGreen-layout'});
            }else if( XMLHttpRequest && XMLHttpRequest.status == "0" ){
            	 layer.alert('哎呀，您的网络好像有问题，请检查网络连接。', {icon: 5,skin: 'wyGreen-layout'});
            }

            fnError(param,XMLHttpRequest);
        },
        complete : function(XMLHttpRequest,status){ //请求完成后最终执行参数
            if(status=='timeout'){//超时,status还有success,error等值的情况
                //alert("超时");
            }
        }
    })
};

wyCommon.ajaxReqCallBackCT = function(param,fnSuccess,fnError){
    $.ajax({
        type : param.type || 'POST',
        url : param.url,
        data : param.data || {},
        async : true,  //默认异步
        cache : false,  //清除缓存
        dataType :'json',
        contentType:"application/json; charset=utf-8",
        timeout : 60000, //超时时间设置，单位毫秒
        /*headers: {
            "login-token": (function () {
                return function () {
                    wyCommon.cookies()["login-token"];
                };
            })()
        },*/
        success : function(data){
            fnSuccess(param,data);
        },
        //error: function (XMLHttpRequest, textStatus, errorThrown) {
        //    param.XMLHttpRequest = XMLHttpRequest;
        //    param.textStatus = textStatus;
        //    param.errorThrown = errorThrown;
        //    fnError(param,data);
        //}
        error: function (errorData) {
            fnError(param,errorData);
        }
    })
};
/**
 * 返回顶部
 */
wyCommon.backTop = function(){
    $("#backTop").unbind("click");
    $("#backTop").click(function(){

        var t =$(".nano-content").scrollTop();
        if (t) {
            $('.nano-content').animate({ scrollTop: 0 }, 300);
            return false;
        }
        $('.nano-content').animate({ scrollTop: 0 }, 300);
        return false;
    });
};

/**
 * 设定自定义过期时间来设置 Cookies
 * 如果需要设定自定义过期时间 ,那么把上面的setCookie　函数换成下面两个函数就ok;
 * 这是有设定过期时间的使用示例：setCookie("name","hayden","s20");
 */
wyCommon.setCookie = function(name,value,time) {
    var strsec = he.getsec(time);
    var exp = new Date();
    exp.setTime(exp.getTime() + strsec*1);
    document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
};

/**
 * 读取cookies
 * 使用示例 setCookie("name","hayden");
 */
wyCommon.getCookie = function(name) {
    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
    if(arr=document.cookie.match(reg))
        return unescape(arr[2]);
    else
        return null;
};

/**
 * 删除cookies
 * 使用示例 alert(getCookie("name"));
 * @param name
 */
wyCommon.delCookie = function(name) {
    var exp = new Date();
    exp.setTime(exp.getTime() - 1);
    var cval=he.getCookie(name);
    if(cval!=null)
        document.cookie= name + "="+cval+";expires="+exp.toGMTString();
};

/**
 * cookies操作封装
 * 使用示例 alert(getCookie("name"));
 * @param name
 */


wyCommon.cookies = function(){
    this.get = function (key) {
        var cookie = document.cookie;
        var cookieArray = cookie.split(';');
        var val = "";
        for (var i = 0; i < cookieArray.length; i++) {
            if (cookieArray[i].Trim().substr(0, key.length) == key) {
                val = cookieArray[i].Trim().substr(key.length + 1);
                break;
            }
        }
        return unescape(val);
    };
    this.getChild = function(key, childKey) {
        var child = this.get(key);
        var childs = child.split('&');
        var val = "";

        for (var i = 0; i < childs.length; i++) {
            if (childs[i].Trim().substr(0, childKey.length) == childKey) {
                val = childs[i].Trim().substr(childKey.length + 1);
                break;
            }
        }
        return val;
    };
    this.set = function(key, value) {
        var cookie = "";
        if (!!key && !!value)
            cookie += key + "=" + escape(value) + ";";
        if (!!arguments[2])
            cookie += "expires=" + arguments[2].toGMTString() + ";";
        if (!!arguments[3])
            cookie += "domain=" + arguments[3] + ";";
        if (!!arguments[4])
            cookie += "path=" + arguments[4] + ";";
        document.cookie = cookie;
    };
    this.setchild = function (key, childkey, value) {
        var cookie = document.cookie;
        var cookieArray = cookie.split(';');
        var val = "";
        for (var i = 0; i < cookieArray.length; i++) {
            if (cookieArray[i].Trim().substr(0, key.length) == key) {
                val = cookieArray[i].Trim().substr(key.length + 1);
                cookieArray = val.split("&");
                var cookiecopy = "";
                for (var j = 0; j < cookieArray.length; j++) {
                    if (cookieArray[j].Trim().substr(0, childkey.length) == childkey) {
                        cookiecopy += childkey + "=" + escape(value) + "&";
                    }
                    else
                        cookiecopy += cookieArray[j] + "&";
                }
                cookiecopy = cookiecopy.substr(0, cookiecopy.length - 1);
                document.cookie = key + "=" + cookiecopy;
                break;
            }
        }
    };
    this.remove = function(key) {
        var date = new Date();
        date.setFullYear(date.getFullYear() - 1);
        var cookie = " " + key + "=;expires=" + date + ";"
        document.cookie = cookie;
    }
};


window.Cookie = new wyCommon.cookies();


/**
 * layer弹出提示框
 */
wyCommon.layerOpenTip = function(title,content){
    layer.open({
        type: 1,
        skin: 'gf-layout', //样式类名
        closeBtn: 0, //不显示关闭按钮
        anim: 2,
        offset:'rb',
        title:title,
        shadeClose: true, //开启遮罩关闭
        content: '<div class="contentValue">'+content+'</div>',
        time:2000,
        shade:0
    });
};

/**
 * 通过出生年月日  计算年龄
 * @param datetime '1986-10-07';
 */
wyCommon.calcAgeByYearToDate = function(datetime){
    //var datetime = '1986-10-07';
    var age = '';
    var d = new Date();
    var year = d.getFullYear();
    var month = d.getMonth() + 1;
    var day = d.getDate();
    if (month < 10) {
        month = '0'+month;
    }
    if(day < 10){
        day = '0'+day;
    }
    var now = year+'-'+month+'-'+day;
    if (now.substring(0,4) >= datetime.substring(0,4) && now.substring(5,7) >=datetime.substring(5,7)
        && now.substring(8,10)>=datetime.substring(8,10)) {
        age = year - parseInt(datetime.substring(0,4));
    }else{
        age = year - parseInt(datetime.substring(0,4)) - 1;
    }

    return age + 1;
};

/**
 *
 * @requires jQuery
 *
 * 改变jQuery的AJAX默认属性和方法
 */
$.ajaxSetup({
    type : 'POST',
    /*xhrFields: {
        withCredentials: true  //携带证书
    },*/
    /*headers: {
        "login-token": "dddddddddddddddd"
    },*/
    beforeSend: function (request) {
        //HttpUtility.UrlEncode
        request.setRequestHeader("login-token", "87a6727a-d5ed-4a60-9d5c-160f4bf41fc2");
    },
    crossDomain: true, //是否跨域访问
    statusCode:{
        0:function(){//网络异常
//          console.info("============================  statusCode" + "网络连接异常");
        }
    },
    error : function(XMLHttpRequest, textStatus, errorThrown) {
//      console.log(this.url + " : " + this.data);
    },
    complete:function(xhr,status){
//      console.log(this.url + " ? " + this.data);
    }
});

/**
 * 判断登陆者身份
*/
wyCommon.ident = function(){
	 //判断是上级医院还是下级医院，管理员
	var cookie = new  wyCommon.cookies;
    var diagnoselevel = cookie.get("diagnoselevel");
    var usertype = cookie.get("usertype");
    var isadmin = cookie.get("isamdin");
    if( usertype == 0  ){  //超级管理员 
    	return "admin";
    }else if( usertype == 1 ){   //普通用户

        if( diagnoselevel == 0 ){  //上级医院
        	return "supH";	
	    }else{   //下级医院
	       return "baseH";   
	    }
    }
}
/**
 * 获取某日期几天前的日期
*/
wyCommon.getBeforeDay = function( date,times ){
	var d = date;
 	var n=new Date(d.getTime()-times*24*3600*1000);
 	var beforeDate = n.getFullYear()+"-"+ (n.getMonth()+1)+"-"+ n.getDate();
   
    return beforeDate;
}

/**
 * 计算天数差
 * param :sDate1和sDate2是2002-12-18格式
*/
wyCommon.DateDiff = function( sDate1,  sDate2 ){
  var aDate,  oDate1,  oDate2,  iDays  
   aDate  =  sDate1.split("-")  
   oDate1  =  new  Date(aDate[1]  +  '-'  +  aDate[2]  +  '-'  +  aDate[0])    //转换为12-18-2002格式
   aDate  =  sDate2.split("-")  
   oDate2  =  new  Date(aDate[1]  +  '-'  +  aDate[2]  +  '-'  +  aDate[0])  
   iDays  =  parseInt(Math.abs(oDate1  -  oDate2)  /  1000  /  60  /  60  /24)    //把相差的毫秒数转换为天数
   return  iDays  
}

/**
 * 启用、关闭遮罩
 * param :
*/
wyCommon.shadeSH = function( type){
  if( type == "open" ){
  	layer.load(1, {
        shade: 0.4,
        offset: ['50%', '50%']
    });
  }else{
  	layer.closeAll('loading');
  }
}

/**
 * 查询id对应的字段
 * data : 对应的数据
 * mField : 已知字段名
 * value : 已知字段值
 * cField : 需要值的字段名
 */
wyCommon.findSpeciValue = function(data,mField,value,cField){
	var currentValue = "";
	 $.each(data, function ( index, item ) {
        if( value == item[mField] ){
          currentValue = item[cField];
        }
    });
	
    return currentValue;

};

/**
 * 比较日期大小
 * param :sDate1和sDate2是2002-12-18格式
 * 
 * 
 * Compare
*/
wyCommon.DateCompare = function( sDate1,  sDate2 ){
	var DsDate1 = new Date(sDate1.replace(/\-/g, "\/"));  
 	var DsDate2 = new Date(sDate2.replace(/\-/g, "\/")); 
 	if( DsDate1 > DsDate2){
        return true
    }else{
    	return false;
    }
 
}

/**
 *计算时间小时差
 * 自己注意传递要么都带日期，要么都不带，否则计算出来的不对
 * 2017-8-23 12:05:05    或 12:05:05
*/
wyCommon.getHour = function( s1, s2 ){
   var reDate = /\d{4}-\d{1,2}-\d{1,2} /;
    s1 = new Date((reDate.test(s1) ? s1 : '2017-1-1 ' + s1).replace(/-/g, '/'));
    s2 = new Date((reDate.test(s2) ? s2 : '2017-1-1 ' + s2).replace(/-/g, '/'));
    var ms = s2.getTime() - s1.getTime();
    return Math.floor(ms / 1000 / 60 / 60);
 
}

/**
 *获取当前日期
*/
wyCommon.getNowFormatDate = function(){
   var date = new Date();
    var seperator1 = "-";
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = year + seperator1 + month + seperator1 + strDate;
    return currentdate;
 
}
 