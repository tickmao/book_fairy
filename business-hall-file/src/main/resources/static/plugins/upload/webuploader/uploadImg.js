
/**
 * 制作人： 何杨洲
 * 制作时间：2017/01/09
 * 制作目的：项目使用
 * 功能：图文管理界面
 * */
var yzFc = $.extend({}, yzFc);/* 定义全局对象，类似于命名空间或包的作用 */

$(function(){
    window.getFileUrl = "";

    yzFc.initUploader();
});

/**************************************************  模块  图片上传 **************************************************/
;(function () {
    //初始化WebUploader 图片上传插件
    yzFc.initUploader=function () {
        yzFc.uploader = WebUploader.create({

            // 选完文件后，是否自动上传。
            auto: true,

            // swf文件路径
            swf: 'lib/Uploader.swf',

            // 文件接收服务端。
            //server: 'http://127.0.0.1:8086/common-file/api/fileUpload/singleImg',
            //server: 'http://file.ivfcn.com/common-file/api/fileUpload/singleImg',
            server: 'http://58.51.90.231:8085/core-file/api/fileUpload/singleImg',
            //server: getFileUrl + 'fileUpload/singleImg',

            pick: '#filePicker',

            // 只允许选择图片文件。
            accept: {
                title: 'Images',
                extensions: 'gif,jpg,jpeg,bmp,png',
                mimeTypes: ".gif,.jpg,.jpeg,.png"
            },
            duplicate :true
        });

        // 当有文件添加进来的时候
        yzFc.uploader.on( 'fileQueued', function( file ) {
            $("#fileList").append( '<div id="' + file.id + '" class="item">' +
                '<h4 class="info">' + file.name + '</h4>' +
                '<p class="state">正在上传...</p>' +
                '</div>' );
            layer.load(1, {
                shade: 0.4,
                offset: ['50%', '50%']
            });
        });

        // 文件上传过程中创建进度条实时显示。
        yzFc.uploader.on( 'uploadProgress', function( file, percentage ) {
            //console.info(percentage);
            var $li = $( '#'+file.id ),
                $percent = $li.find('.progress .progress-bar');

            // 避免重复创建
            if ( !$percent.length ) {
                $percent = $('<div class="progress progress-striped active">' +
                    '<div class="progress-bar progressGreen-bar" role="progressbar" style="width: 0%">' +
                    '</div>' +
                    '</div>').appendTo( $li ).find('.progress-bar');
            }
            $li.find('p.state').text('上传中...');

            $percent.css( 'width', percentage * 100 + '%' );


        });

        // 文件上传成功，给item添加成功class, 用样式标记上传成功。
        yzFc.uploader.on( 'uploadSuccess', function( file,response ) {
            //console.info(getCommonFileUrl + "fileUpload/singleImg");
            var $li = $(
                '<div id="' + file.id + '" class="file-item">' +
                '<img width="150" height="150" src='+response.data.filePath+'>' +
                '</div>'
            );
            $('<div class="del" style="display: none;"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></div>').appendTo( $li );
            $("#fileList").html($li);
            layer.closeAll('loading');

        });

        // 文件上传失败，显示上传出错。
        yzFc.uploader.on( 'uploadError', function( file ) {
            var $li = $( '#'+file.id );
            $li.remove();
            layer.msg("上传图片失败", {icon: 5});
            layer.closeAll('loading');
        });

        // 完成上传完了，成功或者失败，先删除进度条。
        yzFc.uploader.on( 'uploadComplete', function( file ) {
            $( '#'+file.id ).find('.progress').remove();
        });
    };

    //新增窗口时，清理图片; 编辑窗口时，显示图片
    yzFc.resetUploaderImg = function (type, path) {
        if(type == "add"){//新增窗口时，清理图片
            $("#fileList").html("");
        }else{//编辑窗口时，显示图片
            $("#fileList").html("");
            if(path){
                var $li = $(
                    '<div id="fileItem" class="file-item">' +
                    '<img width="150" height="150" src='+path+'>' +
                    '</div>'
                );
                $('<div class="del" style="display: none;"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></div>').appendTo( $li );
                $("#fileList").html($li);
            }
        }
    };

    //获取图片的地址
    yzFc.getUploaderImgPath = function () {
        var path = "";
        if( $("#fileList").find(".file-item").length  ){
            path = $("#fileList").find(".file-item>img").attr("src")
        }
        return path;
    }
})();