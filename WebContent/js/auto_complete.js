var highlightindex = -1;  
function clickshow(){
	 var comText = $(this).text(); 
     var comtext1=comText.substring(comText.lastIndexOf(" ")+1,comText.length);
     $("#nsrwjbm").val(comtext1);  
     $("#auto").hide();   
     highlightindex=-1;   
};
$(document).ready(function() { 
    var wordInput = $("#nsrwjbm");   
    var wordInputOffset = wordInput.offset();
    //自动补全框最开始应该隐藏起来   
    $("#auto").hide().css("border","1px black solid").css("position","absolute")   
            .css("top",wordInputOffset.top + wordInput.height() + 5 + "px")   
            .css("left",wordInputOffset.left + "px").width(wordInput.width() + 2).css("background-color","#00B2EE").css("font-size","12"); 
          
    //给文本框添加键盘按下并弹起的事件   
    wordInput.keyup(function(event) {   
        //处理文本框中的键盘事件   
        var myEvent = event || window.event;   
        var keyCode = myEvent.keyCode;   
        //如果输入的是退格键或删除键，也应该将文本框中的最新信息发送给服务器   
        if((keyCode >= 65 && keyCode<=90) || (keyCode >= 48 && keyCode <= 57) ||(keyCode>=96 && keyCode<=105) || keyCode == 46 || keyCode == 8 || keyCode == 32)   
        {   
            //1.首先获取文本框中的内容   
            var wordText = (($("#nsrwjbm").val()));   
             
            var autoNode = $("#auto");   
            if (wordText != "") {            
                //2.将文本框中的内容发送给服务器   
                $.post("/javaskweb/InitAjaxForXMLServlet",{nsrwjbm:wordText},function(data){   
                    //将dom对象data转换成JQuery的对象 
                    var jqueryObj = $(data);             
                    //找到所有的nsrwjbm节点   
                    var wordNodes = jqueryObj.find("nsrwjbm");   
                    //遍历所有的nsrwjbm节点，取出内容，然后将 内容添加到弹出框中   
                    //需要清空原来的内容   
                    autoNode.html("");   
                    wordNodes.each(function(i){   
                        //获取内容   
                          var wordNode = $(this);        
                          var newDivNode = $("<div id='gpp' style='background-color:#B0E2FF'>").attr("id",i);
                          newDivNode.html(wordNode.text()).appendTo(autoNode);     
                        //增加鼠标进入事件，高亮节点;   
                          newDivNode.mouseover(function(){   
                              if(highlightindex != -1){   
                                  $("#auto").children("div").eq(highlightindex).css("background-color","#00B2EE");   
                              }   
                              highlightindex = $(this).attr("id");   
                              $(this).css("background-color","#00B2EE");   
                          });   
                         //增加鼠标移出事件，取消当前高亮节点   
                          newDivNode.mouseout(function(){   
                        	  
                              $(this).css("background-color","#B0E2FF");   
                              highlightindex = -1;   
                          });   
                        //增加鼠标点击事件，可以进行补全   
                          newDivNode.click(clickshow);      
                      });   
                       
                    //如果服务器段有数据返回，则显示弹出框   
                    if (wordNodes.length > 0) {   
                        autoNode.show();   
                    } else {   
                        autoNode.hide();   
                        //弹出框隐藏的同时，高亮节点索引值也制成-1   
                        highlightindex = -1;   
                    }   
                },"xml");   
            } else {   
                autoNode.hide();   
                highlightindex = -1;   
            }   
        } else if (keyCode == 38 || keyCode == 40) {   
            //如果输入的是向上38向下40按键   
            if (keyCode == 38) {   
                //向上   
                var autoNodes = $("#auto").children("div");   
                if (highlightindex != -1) {   
                    //如果原来存在高亮节点，则将背景色改称白色   
                    autoNodes.eq(highlightindex).css("background-color","#B0E2FF");   
                    highlightindex--;   
                } else {   
                    highlightindex = autoNodes.length - 1;       
                }   
                if (highlightindex == -1) {   
                    //如果修改索引值以后index变成-1，则将索引值指向最后一个元素   
                    highlightindex = autoNodes.length - 1;   
                }   
                //让现在高亮的内容变成红色   
                autoNodes.eq(highlightindex).css("background-color","#00B2EE");   
            }   
            if (keyCode == 40) {   
                //向下   
                var autoNodes = $("#auto").children("div");   
                if (highlightindex != -1) {   
                    //如果原来存在高亮节点，则将背景色改称白色   
                    autoNodes.eq(highlightindex).css("background-color","#B0E2FF");   
                }   
                highlightindex++;  
                if (highlightindex === autoNodes.length) {   
                    //如果修改索引值以后index变成-1，则将索引值指向最后一个元素   
                 
                	
                	highlightindex = 0;  
                    }   
                
                
                autoNodes.eq(highlightindex).css("background-color","#00B2EE");   
            }   
        } else if (keyCode == 13) {   
            //如果输入的是回车   
            //下拉框有高亮内容   
            if (highlightindex != -1) {   
                //取出高亮节点的文本内容   
                var comText = $("#auto").hide().children("div").eq(highlightindex).text();  
               var comtext1=comText.substring(comText.lastIndexOf(" ")+1,comText.length);
//               alert(comtext1);
//                var username=(comText.substring(comText.lastIndexOf(" ")+1,comText.length));
                highlightindex = -1;   
                //文本框中的内容变成高亮节点的内容   
                $("#nsrwjbm").val(comtext1);  
//                $("#NSRMC").val(username);
            } else {   
                //下拉框没有高亮内容   
              $("#auto").hide();   
                 $("#nsrwjbm").get(0).blur();   
            }   
        }   
    });    
});   
  
//文本框失去焦点隐藏弹出框   
function Onblur(){   
 if(highlightindex == -1){   
  $("#auto").hide();   
 }   
 }  
