/**
 * 制作人： 何杨洲
 * 制作时间：2018\2\27 0027
 * 制作目的：项目使用
 * 功能：
 * */





var yzFc = $.extend({}, yzFc);/* 定义全局对象，类似于命名空间或包的作用 */
var getFileUrl = "http://localhost:8083/wind-file/api/";

var attachfilepath = new Array();  //上传的文件路径
var viewer;   //病历图预览变量

$(function () {
    yzFc.initUploader();
})



/**************************   附件开始     开始  *******************************/

yzFc.fileinfoInit = function(){
    yzFc.fileinfo_list(  );   //获取附件内容

};

/**
 * 获取附件内容
 */
yzFc.fileinfo_list = function(){
    var param = {
        data : {
            mode:2
        },
        type : "GET",
        url : serverUrl + 'diagnose/first/'+yzFc.reqParam.p_id+"?token="+yzFc.cookie.get( "token" )
    };
    wyCommon.shadeSH("open");
    wyCommon.ajaxReqCallBack( param,function( param,successData ){
        $(".pro_imgBig").html("");
        $.each(successData, function () {
            var del_li = ['<li class="del_li" style="position: relative">',
                '<img src="' + getFileUrl + this.filepath +'" fileid="'+ this.fileid +'" width="150" height="150" style="margin-right:10px; margin-top:10px;">',
                '<div class="del"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></div>',
                '</li>'].join("");
            $(".pro_imgBig").append(del_li);
            //$(".pro_imgBig").append(" <li><img src='" + getFileUrl + this.filepath + "' fileid='" + this.fileid + "' width='150' height='150' style='margin-right:10px; margin-top:10px;'/></li>")
        });
        if(viewer){
            yzFc.again();
        }else{
            yzFc.initView();
        }
        wyCommon.shadeSH("close");
    },function( param,errorData ){
        wyCommon.shadeSH("close");
        if( JSON.parse(errorData.responseText).code == "404" ){
            $(".pro_imgBig").html("");
            layer.msg(JSON.parse(errorData.responseText).message, {icon: 7});
        }
    } );
};

/**
 * 上传附件
 */
yzFc.initUploader = function(){
    state = 'pending';

    var $list = $('#thelist'),
        $btn = $('#ctlBtn');

    var urlData = {url : getFileUrl + "filerecord/uploadFile?token="+yzFc.cookie.get( "token" ) + "&t="+new Date().getTime()};


    yzFc.uploader = WebUploader.create({
        swf: '../js/plugins/Uploader.swf',
        // 文件接收服务端。
        server: urlData.url,
        // 选择文件的按钮。可选。
        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
        pick: '#picker',

        disableGlobalDnd: true,
        accept: {
            title: 'Images',
            extensions: 'gif,jpg,jpeg,bmp,png',
            //mimeTypes: 'image/*'
            mimeTypes: ".gif,.jpg,.jpeg,.png"
        },
        formData: {
            //token: yzFc.cookie.get( "token" ),
            p_id: yzFc.reqParam.p_id
        },

        // 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
        resize: false
    });

    // 文件上传过程中创建进度条实时显示。
    yzFc.uploader.on('uploadProgress', function (file, percentage) {
        var $li = $('#' + file.id);

        console.info("uploadProgress =============================================================");
        console.info(this);

        if (percentage % 2 == 0)
            $li.find('.state').text('上传中...');
        else
            $li.find('.state').text('上传中......');
    });

    yzFc.uploader.on('uploadSuccess', function (file, response) {
        //attachfilepath.push(response._raw); //上传的图片路径  保存时需要提交
        attachfilepath.push(response); //上传的图片路径  保存时需要提交
        response = response.split("@");

        $('#' + file.id).find('.state').text('已上传');
        var del_li = ['<li class="del_li" style="position: relative">',
            '<img src="' + getFileUrl + response[0] +'" fileid="'+ response[1] +'" width="150" height="150" style="margin-right:10px; margin-top:10px;">',
            '<div class="del"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></div>',
            '</li>'].join("");
        $(".pro_imgBig").append(del_li);
        //$(".pro_imgBig").append(" <li><img src='" + getFileUrl + response[0] + "' fileid='" +response[1] + "' width='150' height='150' style='margin-right:10px; margin-top:10px;' alt='111'/></li>");

        yzFc.again();
    });

    yzFc.uploader.on('beforeFileQueued', function (file) {
        urlData.url = urlData.url + "&t2=" + (new Date().getTime());

        console.info('beforeFileQueued');
    });

    yzFc.uploader.on('uploadBeforeSend', function(obj, data, headers) {
        console.info("uploadBeforeSend ===================");
        this.options.server = this.options.server.split("&")[0] +"&t=" + (new Date().getTime());
    });


    yzFc.uploader.on('uploadError', function (file, reason) {
        $('#' + file.id).find('.state').text('上传出错');
    });

    yzFc.uploader.on('uploadComplete', function (file) {
        $('#' + file.id).find('.state').text('上传完毕');
        //yzFc.uploader.reset();

    });

    yzFc.uploader.on('uploadFinished', function () {
        yzFc.hidechoose_upload();
    });

    yzFc.uploader.on('all', function (type) {
        if (type === 'startUpload') {
            state = 'uploading';
        } else if (type === 'stopUpload') {
            state = 'paused';
        } else if (type === 'uploadFinished') {
            state = 'done';
        }

        if (state === 'uploading') {
            $btn.text('暂停上传');
        } else {
            $btn.text('开始上传');
        }
    });

    // 当有文件添加进来的时候
    yzFc.uploader.on('fileQueued', function (file) {
        $list.append('<li class="ill_left item" id="' + file.id + '" ><label class="info" style="width:300px;">' + file.name
            + '</label> <label class="state" style="float:right;margin-right:10px; color:#1e50a2">等待上传...</label></li>');
    });

    yzFc.uploader.on("filesQueued", function (files) {
        if (files.length> 0)
            yzFc.showchoose_Upload($("#picker").parent().offset());
    });
};

yzFc.hidechoose_upload = function () {
    $('#thelist').empty();
    yzFc.uploader.destroy();
    yzFc.initUploader();
    var divOffset = { left: -1000, top: -1000 };
    $(".chooseform:last").css(divOffset).fadeOut(200);
};

yzFc.showchoose_Upload = function (offset) {
    var left = offset.left + 150;
    //var top = offset.top - 20;

    var top = offset.top;
    var divOffset = { left: left, top: top };
    $(".chooseform:last").css(divOffset).fadeIn(200);
};


/**
 * 上传附件点击事件
 */
yzFc.UploadClick = function(){
    $('#ctlBtn').unbind('click');
    $('.choose_tiptop a').unbind('click');
    $('#ctlBtn').on('click', function () {
        if (state === 'uploading') {
            yzFc.uploader.stop();
        } else {
            yzFc.uploader.upload();
        }
    });

    $(".choose_tiptop a").click(function () {
        yzFc.hidechoose_upload();
    });
};


/**
 * 附件删除 鼠标移入移出事件
 */
yzFc.del_show = function() {
    $(document).on("mouseenter", ".del_li", function () {
        $(this).find(".del").show();

    });
    $(document).on("mouseleave", ".del_li", function () {
        $(this).find(".del").hide();

    });
    yzFc.del();
};

/**
 * 附件删除
 */
yzFc.del = function() {
    $(document).on("click", ".del", function () {
        var fileid = $(this).parent().find("img").attr("fileid");
        $("#fileinfo_del").modal("show");
        if( fileid ){
            $("#fileinfoDel_btn").attr("fileid",fileid);
        }
        return false;
    });
};

/**
 * 附件删除提交
 */
yzFc.delSub = function() {
    var fileid = $("#fileinfoDel_btn").attr("fileid");
    var param = {
        data : {
            "fileid": $("#fileinfoDel_btn").attr("fileid")
        },
        url : getFileUrl + 'filerecord/delete/'+fileid+"?token="+yzFc.cookie.get( "token" ),
        type : "POST"
    };


    wyCommon.ajaxReqCallBack( param,function( param,successData ){

        $("#fileinfo_del").modal("hide");
        $(".del_li").find("img[fileid="+fileid+"]").remove();

    },function( param,errorData ){

        layer.msg(JSON.parse(errorData.responseText).message, {icon: 5});
        $("#fileinfo_del").modal("hide");
    } );
};

/**************************   附件开始    结束  *******************************/




//********************************  病例附件预览 Starts  *********************************************

wyMRD.initView = function(){
    //js版
    viewer = new Viewer(document.getElementById('preViewImg'), {
        title : false,
        tooltip : true,
        viewed: function () {
//          this.viewer.zoomTo(1.0);
            this.viewer.zoomTo(0.7);
        }
    });

};

wyMRD.again = function(){
    //js版本
    viewer.destroy();
    viewer = new Viewer(document.getElementById('preViewImg'), {
        title : false,
        show: function() {
            //$('#preViewImg').zoom(0.5);
        },
        tooltip : true,
        viewed: function () {
            // All methods are available here except "show".
//          this.viewer.zoomTo(1.0);
            this.viewer.zoomTo(0.7);
        }
    });
};
//********************************  病例附件预览 End  *********************************************
