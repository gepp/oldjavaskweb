//Cai SHao Fa       2009-01-11 PM
//检测浏览器信息  
chkBrowser();
function chkBrowser(){
        window["csfBrowser"]={};(function()
        {
          if(csfBrowser.platform) return;
          var ua = window.navigator.userAgent;
          csfBrowser.platform = window.navigator.platform;
          csfBrowser.firefox = ua.indexOf("Firefox")>-1;
          csfBrowser.opera = typeof(window.opera)=="object";
          csfBrowser.ie = !csfBrowser.opera && ua.indexOf("MSIE")>-1;
          csfBrowser.mozilla = window.navigator.product == "Gecko";
          csfBrowser.netscape= window.navigator.vendor=="Netscape";
          csfBrowser.safari = ua.indexOf("Safari")>-1;
          csfBrowser.width = window.screen.width;
          if(csfBrowser.firefox) var re = /Firefox(\s|\/)(\d+(\.\d+)?)/;
          else if(csfBrowser.ie) var re = /MSIE( )(\d+(\.\d+)?)/;
          else if(csfBrowser.opera) var re = /Opera(\s|\/)(\d+(\.\d+)?)/;
          else if(csfBrowser.netscape) var re = /Netscape(\s|\/)(\d+(\.\d+)?)/;
          else if(csfBrowser.safari) var re = /Version(\/)(\d+(\.\d+)?)/;
          else if(csfBrowser.mozilla) var re = /rv(\:)(\d+(\.\d+)?)/;
          if("undefined"!=typeof(re)&&re.test(ua))
            csfBrowser.version = parseFloat(RegExp.$2);
        })();
	    var app = navigator.appName;
	    var verStr = navigator.appVersion;
	    if(app.indexOf("Netscape") != -1){
	    }else if(app.indexOf("Microsoft") != -1){
	    if(verStr.indexOf('MSIE 3.0') != -1 || verStr.indexOf('MSIE 4.0') != -1 || verStr.indexOf('MSIE 5.0') != -1 || verStr.indexOf('MSIE 5.1') != -1){window.parent.location.replace("/*.htm");}else{
		        if(csfBrowser.version >= 6 && csfBrowser.version <= 8){}else{window.parent.location.replace("/*.htm");}
		    }
	    }
}