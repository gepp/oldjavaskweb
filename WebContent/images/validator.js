//去除字符串两边的空格
String.prototype.trim = function () {
	return this.replace(/(^\s+)|(\s+$)/g, "");
};
//检测字符串是否为空
String.prototype.isEmpty = function () {
	return !(/.?[^\s　]+/.test(this));
};
//检测值是否介于某两个指定的值之间
String.prototype.isBetween = function (val, min, max) {
	return isNaN(val) == false && val >= min && val <= max;
};
//获取最大值或最小值
String.prototype.getBetweenVal = function (what) {
	var val = this.split(",");
	var min = val[0];
	var max = val[1] == null ? val[0] : val[1];
	if (parseInt(min) > parseInt(max)) {
		min = max;
		max = val[0];
	}
	return what == "min" ? (isNaN(min) ? null : min) : (isNaN(max) ? null : max);
};
var validator = function (formObj) {
	this.allTags = formObj.getElementsByTagName("*");
    //字符串验证正则表达式
	this.reg = new Object();
	this.reg.english = /^[a-zA-Z0-9_\-]+$/;
	this.reg.chinese = /^[\u0391-\uFFE5]+$/;
	this.reg.number = /^[-\+]?\d+(\.\d+)?$/;
	this.reg.integer = /^[-\+]?\d+$/;
	this.reg.float = /^[-\+]?\d+(\.\d+)?$/;
	this.reg.date = /^(\d{4})(-|\/)(\d{1,2})\2(\d{1,2})$/;
	this.reg.email = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
	this.reg.url = /^(((ht|f)tp(s?))\:\/\/)[a-zA-Z0-9]+\.[a-zA-Z0-9]+[\/=\?%\-&_~`@[\]\':+!]*([^<>\"\"])*$/;
	this.reg.phone = /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/;
	this.reg.mobile = /^((\(\d{2,3}\))|(\d{3}\-))?((13\d{9})|(159\d{8}))$/;
	this.reg.ip = /^(0|[1-9]\d?|[0-1]\d{2}|2[0-4]\d|25[0-5]).(0|[1-9]\d?|[0-1]\d{2}|2[0-4]\d|25[0-5]).(0|[1-9]\d?|[0-1]\d{2}|2[0-4]\d|25[0-5]).(0|[1-9]\d?|[0-1]\d{2}|2[0-4]\d|25[0-5])$/;
	this.reg.zipcode = /^[1-9]\d{5}$/;
	this.reg.qq = /^[1-9]\d{4,10}$/;
	this.reg.msn = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
	this.reg.idcard = /(^\d{15}$)|(^\d{17}[0-9Xx]$)/;
    //错误输出信息
	this.tip = new Object();
	this.tip.unknow = "\u672a\u627e\u5230\u7684\u9a8c\u8bc1\u7c7b\u578b\uff0c\u65e0\u6cd5\u6267\u884c\u9a8c\u8bc1\u3002";
	this.tip.paramError = "\u53c2\u6570\u8bbe\u7f6e\u9519\u8bef\uff0c\u65e0\u6cd5\u6267\u884c\u9a8c\u8bc1\u3002";
	this.tip.required = "\u4e0d\u5141\u8bb8\u4e3a\u7a7a\u3002";
	this.tip.english = "\u4ec5\u5141\u8bb8\u82f1\u6587\u5b57\u7b26\u53ca\u4e0b\u5212\u7ebf (a-zA-Z0-9_)\u3002";
	this.tip.chinese = "\u4ec5\u5141\u8bb8\u4e2d\u6587\u5b57\u7b26\u3002";
	this.tip.number = "\u4e0d\u662f\u4e00\u4e2a\u6709\u6548\u7684\u6570\u5b57\u3002";
	this.tip.integer = "\u4e0d\u662f\u4e00\u4e2a\u6709\u6548\u7684\u6574\u6570\u3002";
	this.tip.float = "\u4e0d\u662f\u4e00\u4e2a\u6709\u6548\u7684\u6d6e\u70b9\u6570\u3002";
	this.tip.date = "\u4e0d\u662f\u4e00\u4e2a\u6709\u6548\u7684\u65e5\u671f\u683c\u5f0f\u3002 (\u4f8b\u5982\uff1a2007-06-29)";
	this.tip.email = "\u4e0d\u662f\u4e00\u4e2a\u6709\u6548\u7684\u7535\u5b50\u90ae\u4ef6\u683c\u5f0f\u3002";
	this.tip.url = "\u4e0d\u662f\u4e00\u4e2a\u6709\u6548\u7684\u8d85\u94fe\u63a5\u683c\u5f0f\u3002";
	this.tip.phone = "\u4e0d\u662f\u4e00\u4e2a\u6709\u6548\u7684\u7535\u8bdd\u53f7\u7801\u3002";
	this.tip.mobile = "\u4e0d\u662f\u4e00\u4e2a\u6709\u6548\u7684\u624b\u673a\u53f7\u7801\u3002";
	this.tip.ip = "\u4e0d\u662f\u4e00\u4e2a\u6709\u6548\u7684IP\u5730\u5740\u3002";
	this.tip.zipcode = "\u4e0d\u662f\u4e00\u4e2a\u6709\u6548\u7684\u90ae\u653f\u7f16\u7801\u3002";
	this.tip.qq = "\u4e0d\u662f\u4e00\u4e2a\u6709\u6548\u7684QQ\u53f7\u7801\u3002";
	this.tip.msn = "\u4e0d\u662f\u4e00\u4e2a\u6709\u6548\u7684MSN\u5e10\u6237\u3002";
	this.tip.idcard = "\u4e0d\u662f\u4e00\u4e2a\u6709\u6548\u7684\u8eab\u4efd\u8bc1\u53f7\u7801\u3002";
    //获取控件名称
	this.getControlName = function () {
		return this.element.getAttribute("controlName") == null ? "\u6307\u5b9a\u63a7\u4ef6\u7684\u503c" : this.element.getAttribute("controlName");
	};
    //设定焦点
	this.setFocus = function (ele) {
		try {
			ele.focus();
		}
		catch (e) {
		}
	};
    //设置边框颜色
	this.setBorderColor = function (ele) {
		var borderColor = ele.currentStyle ? ele.currentStyle.borderColor : document.defaultView.getComputedStyle(ele, null)["borderColor"];
		ele.style.borderColor = "#ff9900";
		ele.onkeyup = function () {
			this.style.borderColor = borderColor;
		};
	};
    //输出错误反馈信息
	this.feedback = function (type) {
		try {
			var msg = eval("this.tip." + type) == undefined ? type : this.getControlName() + eval("this.tip." + type);
		}
		catch (e) {
			msg = type;
		}
		this.setBorderColor(this.element);
		alert(msg);
		this.setFocus(this.element);
	};
    //执行字符串验证
	this.validate = function () {
		var v = this.element.value;
        //验证是否允许非空
		var required = this.element.getAttribute("required");
		if (required != null && v.isEmpty()) {
			this.feedback("required");
			return false;
		}
        //验证是否合法格式
		var dataType = this.element.getAttribute("dataType");
		if (!v.isEmpty() && dataType != null && dataType.toLowerCase() != "password") {
			dataType = dataType.toLowerCase();
			try {
				if (!(eval("this.reg." + dataType)).test(v)) {
					this.feedback(dataType);
					return false;
				}
			}
			catch (e) {
				this.feedback("unknow");
				return false;
			}
		}
        //执行数据验证
		var confirm = this.element.getAttribute("confirm");
		if (confirm != null) {
			try {
				var data = eval("formObj." + confirm + ".value");
				if (v != data) {
					alert("\u4e24\u6b21\u8f93\u5165\u7684\u5185\u5bb9\u4e0d\u4e00\u81f4\uff0c\u8bf7\u91cd\u65b0\u8f93\u5165\u3002");
					this.setBorderColor(this.element);
					this.setFocus(this.element);
					return false;
				}
			}
			catch (e) {
				this.feedback("paramError");
				return false;
			}
		}
        //验证数字大小
		var dataBetween = this.element.getAttribute("dataBetween");
		if (!v.isEmpty() && dataBetween != null) {
			var min = dataBetween.getBetweenVal("min");
			var max = dataBetween.getBetweenVal("max");
			if (min == null || max == null) {
				this.feedback("paramError");
				return false;
			}
			if (!v.isBetween(v.trim() * 100, min * 100, max * 100)) {
				this.feedback(this.getControlName() + "\u5fc5\u987b\u662f\u4ecb\u4e8e " + min + "-" + max + " \u4e4b\u95f4\u7684\u6570\u5b57\u3002");
				return false;
			}
		}
        //验证字符长度
		var dataLength = this.element.getAttribute("dataLength");
		if (!v.isEmpty() && dataLength != null) {
			var min = dataLength.getBetweenVal("min");
			var max = dataLength.getBetweenVal("max");
			if (min == null || max == null) {
				this.feedback("paramError");
				return false;
			}
			if (!v.isBetween(v.trim().length, min, max)) {
				this.feedback(this.getControlName() + "\u5fc5\u987b\u662f " + min + "-" + max + " \u4e2a\u5b57\u7b26\u3002");
				return false;
			}
		}
		return true;
	};
    //执行初始化操作
	this.init = function () {
		var initflag = 1;
		for (var i = 0; i < this.allTags.length; i++) {
			if (this.allTags[i].tagName.toUpperCase() == "INPUT" || this.allTags[i].tagName.toUpperCase() == "SELECT" || this.allTags[i].tagName.toUpperCase() == "TEXTAREA") {
				this.element = allTags[i];
				if (!this.validate()) {
					initflag = 0;
					return false;
				}
			}
		}
		if (initflag == 1) {
			return true;
		}
	};
	return this.init();
};

