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
    //�Զ���ȫ���ʼӦ����������   
    $("#auto").hide().css("border","1px black solid").css("position","absolute")   
            .css("top",wordInputOffset.top + wordInput.height() + 5 + "px")   
            .css("left",wordInputOffset.left + "px").width(wordInput.width() + 2).css("background-color","#00B2EE").css("font-size","12"); 
          
    //���ı�����Ӽ��̰��²�������¼�   
    wordInput.keyup(function(event) {   
        //�����ı����еļ����¼�   
        var myEvent = event || window.event;   
        var keyCode = myEvent.keyCode;   
        //�����������˸����ɾ������ҲӦ�ý��ı����е�������Ϣ���͸�������   
        if((keyCode >= 65 && keyCode<=90) || (keyCode >= 48 && keyCode <= 57) ||(keyCode>=96 && keyCode<=105) || keyCode == 46 || keyCode == 8 || keyCode == 32)   
        {   
            //1.���Ȼ�ȡ�ı����е�����   
            var wordText = (($("#nsrwjbm").val()));   
             
            var autoNode = $("#auto");   
            if (wordText != "") {            
                //2.���ı����е����ݷ��͸�������   
                $.post("/javaskweb/InitAjaxForXMLServlet",{nsrwjbm:wordText},function(data){   
                    //��dom����dataת����JQuery�Ķ��� 
                    var jqueryObj = $(data);             
                    //�ҵ����е�nsrwjbm�ڵ�   
                    var wordNodes = jqueryObj.find("nsrwjbm");   
                    //�������е�nsrwjbm�ڵ㣬ȡ�����ݣ�Ȼ�� ������ӵ���������   
                    //��Ҫ���ԭ��������   
                    autoNode.html("");   
                    wordNodes.each(function(i){   
                        //��ȡ����   
                          var wordNode = $(this);        
                          var newDivNode = $("<div id='gpp' style='background-color:#B0E2FF'>").attr("id",i);
                          newDivNode.html(wordNode.text()).appendTo(autoNode);     
                        //�����������¼��������ڵ�;   
                          newDivNode.mouseover(function(){   
                              if(highlightindex != -1){   
                                  $("#auto").children("div").eq(highlightindex).css("background-color","#00B2EE");   
                              }   
                              highlightindex = $(this).attr("id");   
                              $(this).css("background-color","#00B2EE");   
                          });   
                         //��������Ƴ��¼���ȡ����ǰ�����ڵ�   
                          newDivNode.mouseout(function(){   
                        	  
                              $(this).css("background-color","#B0E2FF");   
                              highlightindex = -1;   
                          });   
                        //����������¼������Խ��в�ȫ   
                          newDivNode.click(clickshow);      
                      });   
                       
                    //����������������ݷ��أ�����ʾ������   
                    if (wordNodes.length > 0) {   
                        autoNode.show();   
                    } else {   
                        autoNode.hide();   
                        //���������ص�ͬʱ�������ڵ�����ֵҲ�Ƴ�-1   
                        highlightindex = -1;   
                    }   
                },"xml");   
            } else {   
                autoNode.hide();   
                highlightindex = -1;   
            }   
        } else if (keyCode == 38 || keyCode == 40) {   
            //��������������38����40����   
            if (keyCode == 38) {   
                //����   
                var autoNodes = $("#auto").children("div");   
                if (highlightindex != -1) {   
                    //���ԭ�����ڸ����ڵ㣬�򽫱���ɫ�ĳư�ɫ   
                    autoNodes.eq(highlightindex).css("background-color","#B0E2FF");   
                    highlightindex--;   
                } else {   
                    highlightindex = autoNodes.length - 1;       
                }   
                if (highlightindex == -1) {   
                    //����޸�����ֵ�Ժ�index���-1��������ֵָ�����һ��Ԫ��   
                    highlightindex = autoNodes.length - 1;   
                }   
                //�����ڸ��������ݱ�ɺ�ɫ   
                autoNodes.eq(highlightindex).css("background-color","#00B2EE");   
            }   
            if (keyCode == 40) {   
                //����   
                var autoNodes = $("#auto").children("div");   
                if (highlightindex != -1) {   
                    //���ԭ�����ڸ����ڵ㣬�򽫱���ɫ�ĳư�ɫ   
                    autoNodes.eq(highlightindex).css("background-color","#B0E2FF");   
                }   
                highlightindex++;  
                if (highlightindex === autoNodes.length) {   
                    //����޸�����ֵ�Ժ�index���-1��������ֵָ�����һ��Ԫ��   
                 
                	
                	highlightindex = 0;  
                    }   
                
                
                autoNodes.eq(highlightindex).css("background-color","#00B2EE");   
            }   
        } else if (keyCode == 13) {   
            //���������ǻس�   
            //�������и�������   
            if (highlightindex != -1) {   
                //ȡ�������ڵ���ı�����   
                var comText = $("#auto").hide().children("div").eq(highlightindex).text();  
               var comtext1=comText.substring(comText.lastIndexOf(" ")+1,comText.length);
//               alert(comtext1);
//                var username=(comText.substring(comText.lastIndexOf(" ")+1,comText.length));
                highlightindex = -1;   
                //�ı����е����ݱ�ɸ����ڵ������   
                $("#nsrwjbm").val(comtext1);  
//                $("#NSRMC").val(username);
            } else {   
                //������û�и�������   
              $("#auto").hide();   
                 $("#nsrwjbm").get(0).blur();   
            }   
        }   
    });    
});   
  
//�ı���ʧȥ�������ص�����   
function Onblur(){   
 if(highlightindex == -1){   
  $("#auto").hide();   
 }   
 }  
