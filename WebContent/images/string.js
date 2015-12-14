function isList(str,strModel){
      if (str.length==0)
            return false;
      for (i=0;i<str.length;i++){
            var a = str.substr(i,1);
            if (strModel.indexOf(a,0)==-1)
                  return false;
      }
      return true;
}
function isChar(str){
      return isList(str,"abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
}
function isCharNum(str){
      return isList(str,"abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789");
}

function isCharNumLine(str){
      return isList(str,"abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789_");
}
function isNum(str){
      return      isList(str,"0123456789");
}
function isPosInt(str){
      if (str.length==0)
            return false;
      str = trim(str);
      for (i=0;i<str.length;i++){
            var a = str.substr(i,1);
            strModel = "0123456789";
            if (i==0){
                  strModel=strModel+"+";
            }
            if (strModel.indexOf(a,0)==-1)
                  return false;
      }
      return true;
}
function isInt(str){
      if (str.length==0)
            return false;
      str = trim(str);
      for (i=0;i<str.length;i++){
            var a = str.substr(i,1);
            strModel = "0123456789";
            if (i==0){
                  strModel=strModel+"+-";
            }
            if (strModel.indexOf(a,0)==-1)
                  return false;
      }
      return true;
}
function isPosDecimal(str){
      if (isNaN(str))
            return false;
      if (parseFloat(str)<0)
            return false;
      return true;
}
function isDecimal(str){
      if (isNaN(str))
            return false;
      return true;
}
function hasQuote(str){
      if (str.indexOf("'",0)==-1)
            return false;
      return true;
}
function trim(s){
      if (s == null)
            return null;
      s = s.replace(/ *$/, "");
      s = s.replace(/^ */, "");
      return s;
}
function xtrim(s, nStart, nEnd){
      if (s == null)
            return null;
      if (nStart == 0 && nEnd == 0)
            return s;
      if ( nEnd != 0){
            s = s.replace(/ *$/, "");
      }
      if (nStart != 0){
            s = s.replace(/^ */, "");
      }
      return s;
}

function xreplace(source, sModel, sTarget){
      if (source == null)
            return null;
      var regExp = new RegExp(sModel, "g");
      return source.replace(regExp, sTarget);
}
function getLength(s){
      if ((s==null) || (s.length==0))
            return 0;
      //var legal = ".-?;:<>/\|~!@#$%^&*()_+012345678abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
      var legal ="`1234567890-=~!@#$%^&*()_+qwertyuiop[]\\QWERTYUIOP{}|asdfghjkl;'ASDFGHJKL:\"zxcvbnm,./ZXCVBNM<>? "



      var i,n;
      n = 0;
      for (i=0;i<s.length;i++){
            if (legal.indexOf(s.charAt(i))==-1){
                  n += 2;
            }else{
                  n += 1;
            }
      }
      return n;
}
function isValidMobile(s){
      if (trim(s).length!=11)
            return false;
      if    (!isNum(s))
            return false;
      return true;
}
function isValidMail(s){
      var pattern=/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
      if (s.search(pattern)==-1)
            return false;
      return true;
}
function startWithNumber(s){
      var a = s.substr(0,1);
      return isNum(a);
}

//ÓÊÏä
function fun_email($str){ 
	return (preg_match('/^[_\.0-9a-z-]+@([0-9a-z][0-9a-z-]+\.)+[a-z]{2,4}$/',$str))?true:false; 
}

//½âËø
function jiesuo(){
	parent.frames["leftFrame"].document.getElementById("locked_left").style.display='none';
	parent.frames["topFrame"].document.getElementById("locked_top").style.display='none';
}

function suoding(){
	parent.frames["leftFrame"].document.getElementById("locked_left").style.display='block';
	parent.frames["topFrame"].document.getElementById("locked_top").style.display='block';	
}
