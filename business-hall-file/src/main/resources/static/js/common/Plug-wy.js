/**
 * 制作人： 王月
 * 制作时间：2017/6/26
 * 制作目的：项目使用
 * 功能：公共方法
 * */
$.fn.heInput = function(element, options) {
    var inputs =element;
    var dateStr = options.dateStr;
    inputs.each(function(index,elem){
        var input = $(this);
        input.on("keydown",function(event){
            var that = this;   //当前触发事件的输入框。
            var key = event.keyCode;
            var cursorIndex = WYgetCursor(that);

            //输入数字
            if ((key >= 48 && key <= 57))
            {
                event.returnValue = true;
            } else {
                event.returnValue = false;
            }
            if((key >= 48 && key <= 57)||(key >= 96 && key <= 105)){
                //光标在日期末尾或光标的下一个字符是"-",返回false,阻止字符显示。
                if(cursorIndex == dateStr.length || that.value.charAt(cursorIndex) === "-") {return false;}
                //字符串中无下划线时，返回false
                if(that.value.search(/_/) === -1){return false;}

                var fron = that.value.substring(0,cursorIndex); //光标之前的文本
                var reg = /(\d)_/;
                setTimeout(function(){ //setTimeout后字符已经输入到文本中
                    //光标之后的文本
                    var end = that.value.substring(cursorIndex,that.value.length);
                    //去掉新插入数字后面的下划线_
                    that.value = fron + end.replace(reg,"$1");
                    //寻找合适的位置插入光标。
                    WYresetCursor(that);
                },1);
                return true;
                //"Backspace" 删除键
            }else if( key == 8){

                var chineseReg = /^[\u4e00-\u9fa5]+$/;
                //光标在最前面时不能删除。  但是考虑全部文本被选中下的删除情况
                if(!cursorIndex && !WYgetSelection(that).length){ return false;}

                //删除时遇到中划线的处理
                if(that.value.charAt(cursorIndex -1 ) == "-" ){
                    console.info("==============");
                    var ar = that.value.split("");

                    if( chineseReg.test(that.value.charAt(cursorIndex -2 )) ){
                        console.info("==============");
                        var ar = that.value.split("");
                        var br = that.value.split("");
                        ar.splice(cursorIndex-2,1,br[cursorIndex-2]);
                        that.value = ar.join("");
                        WYresetCursor(that);
                        return false;
                    }else{

                        ar.splice(cursorIndex-2,1,"_");
                        that.value = ar.join("");
                        WYresetCursor(that);
                        return false;
                    }
                }

                if(  chineseReg.test(that.value.charAt(cursorIndex -1 )) ){
                    console.info("==============");
                    var ar = that.value.split("");
                    var br = that.value.split("");
                    ar.splice(cursorIndex-2,1,br[cursorIndex-2]);
                    that.value = ar.join("");
                    WYresetCursor(that);
                    return false;
                }


                setTimeout(function(){
                    //值为空时重置
                    if(that.value === "") {
                        that.value = dateStr;
                        WYresetCursor(that);
                    }
                    //删除的位置加上下划线
                    var cursor = WYgetCursor(that);
                    var ar = that.value.split("");
                    ar.splice(cursor,0,"_");
                    that.value = ar.join("");
                    WYresetCursor(that);
                },1);

                return true;
            }
            return false;

        });
        input.on("focus",function(){
            if(!this.value){
                this.value = dateStr;
            }
            WYresetCursor(this);
            $(this).removeAttr( "first" );

        });
        input.on("blur",function(){
            if(this.value === dateStr){
                this.value = "";
            }
        });
        input.on("click",function(){
            var first =  $(this).attr( "first" );
            if( !first ){
                $(this).attr( "first",true );
                //locateCursor(this, 0)
                WYresetCursor(this);
            }
        });

    });
    //设置光标到正确的位置
    function WYresetCursor(elem){
        var value = elem.value;
        var index = value.length;
        //当用户通过选中部分文字并删除时，此时只能将内容置为初始格式洛。
        var re=/[0-9]/;//设置正则表达式;

        //var temp1 = value.match(/\d/g);
        //console.log(value.match(/\d/g));
        //console.info(temp1);

        if(elem.value.length !== dateStr.length){
            elem.value = dateStr;
        }
        var temp = value.search(/_/);
        index =  temp> -1? temp: index;

        WYsetCursor(elem,index);
        //把光标放到第一个_下划线的前面
        //没找到下划线就放到末尾
    }
    
    
    function WYgetCursor(elem){
	    //IE 9 ，10，其他浏览器
	    if(elem.selectionStart !== undefined){
	        return elem.selectionStart;
	    } else{ //IE 6,7,8
	        var range = document.selection.createRange();
	        range.moveStart("character",-elem.value.length);
	        var len = range.text.length;
	        return len;
	    }
	}
	function WYsetCursor(elem,index){
	    //IE 9 ，10，其他浏览器
	    if(elem.selectionStart !== undefined){
	        elem.selectionStart = index;
	        elem.selectionEnd = index;
	    } else{//IE 6,7,8
	        var range = elem.createTextRange();
	        range.moveStart("character",-elem.value.length); //左边界移动到起点
	        range.move("character",index); //光标放到index位置
	        range.select();
	    }
	}
	function WYgetSelection(elem){
	    //IE 9 ，10，其他浏览器
	    if(elem.selectionStart !== undefined){
	        return elem.value.substring(elem.selectionStart,elem.selectionEnd);
	    } else{ //IE 6,7,8
	        var range = document.selection.createRange();
	        return range.text;
	    }
	}
	function WYsetSelection(elem,leftIndex,rightIndex){
	    if(elem.selectionStart !== undefined){ //IE 9 ，10，其他浏览器
	        elem.selectionStart = leftIndex;
	        elem.selectionEnd = rightIndex;
	    } else{//IE 6,7,8
	        var range = elem.createTextRange();
	        range.move("character",-elem.value.length);  //光标移到0位置。
	        //这里一定是先moveEnd再moveStart
	        //因为如果设置了左边界大于了右边界，那么浏览器会自动让右边界等于左边界。
	        range.moveEnd("character",rightIndex);
	        range.moveStart("character",leftIndex);
	        range.select();
	    }
	}
	
	function locateCursor(elem, index) {
	    if (elem.setSelectionRange) {
	        elem.setSelectionRange(index, index);
	    } else {
	        var range = elem.createTextRange();
	        var len = elem.value.length;
	        range.moveStart('character', -len);
	        range.moveEnd('character', -len);
	        range.moveStart('character', index);
	        range.moveEnd('character', 0);
	        range.select();
	    }
	}
	
	    
    
    
    
    
};










