
/**
 * 制作人： 何杨洲
 * 制作时间：2017/01/09
 * 制作目的：项目使用
 * 功能：图文管理界面
 * */
var yzFc = $.extend({}, yzFc);/* 定义全局对象，类似于命名空间或包的作用 */

$(function(){
    window.getFileUrl = "http://localhost:8083/wind-file/api/";

    yzFc.initWangEditor();
});


/**************************************************  模块  富文本框  **************************************************/
;(function () {
    yzFc.initWangEditor = function () {
        var windowWangEditor = window.wangEditor;
        yzFc.wangEditor = new windowWangEditor('#input_content');
        // 配置服务器端地址 /imgUpload/singleImg


        yzFc.wangEditor.customConfig.uploadImgServer = getFileUrl + "ueditor/singleImgRemoteUpload";
        //yzFc.wangEditor.customConfig.uploadImgServer = getFileUrl + "imgUpload/singleImg";
        yzFc.wangEditor.customConfig.uploadImgParamsWithUrl = true;
        yzFc.wangEditor.customConfig.uploadFileName = 'file';
        yzFc.wangEditor.customConfig.withCredentials = true;
        yzFc.wangEditor.customConfig.uploadImgTimeout = 3000;

        yzFc.wangEditor.customConfig.uploadImgParams = {
            businessEnumType: 22, //上传业务
            tableType: 3, //上传保存的表
            richEditType: 3  // 属性值会自动进行 encode ，此处无需 encode
        }

        /*editor.customConfig.customUploadImg = function (files, insert) {
         http://localhost:8086/common-file/api/fileUpload/singleImg?uploadType=6
         insert('https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png')
         }*/
        yzFc.wangEditor.create()
        //editor.txt.html('<p>用 JS 设置的内容</p>');
    };

    //对富文本框赋值
    yzFc.loadDataWangEditor = function (html) {
        yzFc.wangEditor.txt.html(html);
    }

    //对富文本框取值
    yzFc.getDataWangEditor = function () {
        return yzFc.wangEditor.txt.html();
    }

})();