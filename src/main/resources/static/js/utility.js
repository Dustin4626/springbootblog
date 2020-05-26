//查詢areaId底下的checkbox
function checkedAll(areaId,isChecked){
	$("#" + areaId).find("input").each(function(){
		if(this.type=="checkbox"){
			this.checked = isChecked;
		}
	});
}
//設定全選或全不選
function setupCheckedBox(areaId,inputId){
	isChecked = $("#" + inputId).get(0).checked;
	checkedAll(areaId,isChecked);
}
//全選與全不選
function clickCheckAll(cid,checkedboxName){
	isChecked = $("#" + cid).get(0).checked;
	$("input[name="+checkedboxName+"]").each(function(){
		if(!this.disabled){
			this.checked = isChecked;
		}
	});
}
//刪除檢查是否勾選
function checkDelete(areaId){
	checkLength = $("#" + areaId + " input:checked").length;
	if(checkLength > 0){
		return confirm('是否確定刪除勾選項目?');
	}else{
		alert('請勾選刪除項目');
		return false;
	}
}
//刪除檢查是否勾選
function checkboxData(checkedboxName){
	var dataArray = [];
	$("input[name="+checkedboxName+"]").each(function(){
		if(this.checked){
			dataArray.push(this.value);
		}
	});
	return dataArray;
}
//清理欄位值
function clearInput(areaId){
	$("#" + areaId).find("input,select,textarea").each(function(){
		if(this.type == "checkbox" || this.type == "radio"){
			this.checked = false;
		}else if(this.type == "select-multiple"){
			//先不處理
		}else if (this.type == "select-one"){
			this.selectedIndex = 0;
		}else if(this.type == "button" || this.type == "submit" || this.type == "reset" || this.type == "image"){
			//不處理
		}else {
			$(this).val("");
		}
	});
}
function clearInputNoRaido(areaId){
	$("#" + areaId).find("input,select,textarea").each(function(){
		if(this.type == "radio"){
			
		}else if(this.type == "checkbox"){
			this.checked = false;
		}else if(this.type == "select-multiple"){
			//先不處理
		}else if (this.type == "select-one"){
			this.selectedIndex = 0;
		}else if(this.type == "button" || this.type == "submit" || this.type == "reset" || this.type == "image"){
			//不處理
		}else {
			$(this).val("");
		}
	});
}
//欄位恢復非錯誤樣式
function resetValid(formId){	
	//$("#"+formId).validate({onfocusout:false});
	$("#" + formId + " label.error").each(function(){
		var fo = $(this).attr("for");		
		$(this).attr("style","display: none;");
		if(fo!=""){
			$("#"+fo).removeClass("error");
			$('label[for="'+fo+'"]').remove();
		}
	});
	$("#" + formId).find("input,select,textarea").each(function(){
		if($(this).attr("class") == "error"){
			$(this).attr("class","valid");
		}
	});
}
//取得已勾選checkbox的值，以division參數做區隔
function getValueByName(checkboxName,division){
	values = "";
	$("input[name=" + checkboxName + "]").each(function(){
		if(this.checked){
			if(values == ""){
				values += this.value;
			}else{
				values += (division + this.value);
			}
		}
	});
	return values;
}

function submit2Method(formId,name,methodId){
	$("#"+methodId).attr("name","method:"+name);
	$("#"+formId).get(0).submit();
}

function submit2Action(formId,method){
	$("#"+formId).attr("action",method);
	$("#"+formId).get(0).submit();
}

function selectionMappingText(selectid,value){
	txt = "";
	$("#"+selectid+" option").each(function(){
		if(this.value == value){
			txt = this.text;
		}	
	});
	return txt;
}

function display(className,isShow){
	  if(isShow){
		$("."+className).show();
	  }else{
	    $("."+className).hide();
	  }	
}

function empty(val){	
	return ((val+"")==null || (val+"")=="" || (val+"")=='undefined' || (val+"")=='null' || (val+"")=='undefine');
}

function emptyStr(val){	
	return empty(val)?"":val;
}

function isEmptyObj(obj){
    for (var name in obj) 
    {
        return false;
    }
    return true;
};

function disabledAll(id){
    var inputs = $("#" + id + "  input");
    for(var i = 0;i < inputs.length;i++){
        var input = inputs.get(i);
        if(input.type == "text" || input.type == "password" || input.type == "file" || input.type == "checkbox" || input.type == "radio"){
            if(input.type == "file" || input.type == "checkbox" || input.type == "radio"){
                input.disabled="disabled";
            }else{
                input.readOnly = "readonly";
                input.style.color = '#0300FA';
                input.style.borderColor = '#ffffff';
                input.style.borderWidth = '0px';
                input.style.borderStyle = 'None';
				input.style.backgroundColor = '#ffffff';
            }
        }       
    }
    var selects = $("#" + id + "  select");
    for(var j = 0;j < selects.length;j++){
        var select = selects.get(j);
        if(select.id!="PageSize" && select.id!="CurrentPage"){
            select.disabled="disabled";                
        }
    }
    var textareas = $("#" + id + "  textarea");
    for(var j = 0;j < textareas.length;j++){
        var textarea = textareas.get(j);            
        textarea.disabled="disabled";
        textarea.style.color = '#0300FA';
        textarea.style.borderColor = '#ffffff';
        textarea.style.borderWidth = '0px';
        textarea.style.borderStyle = 'None';
		textarea.style.backgroundColor = '#ffffff';
    }        
    var spans = $("#" + id + "  span");
    for(var j = 0;j < spans.length;j++){
        var span = spans.get(j);
        if(span.innerText == " "){
            span.innerText = "";
        }                     
    }
}

function unDisabledAll(id){
    var inputs = $("#" + id + "  input");
    for(var i = 0;i < inputs.length;i++){
        var input = inputs.get(i);
        if(input.type == "text" || input.type == "password" || input.type == "file" || input.type == "checkbox" || input.type == "radio"){
            if(input.type == "file" || input.type == "checkbox" || input.type == "radio"){
                input.disabled="";
            }else{
                input.readOnly = "";
                input.style.color = '';
                input.style.borderColor = '';
                input.style.borderWidth = '';
                input.style.borderStyle = '';
				input.style.backgroundColor = '';
            }
        }       
    }
    var selects = $("#" + id + "  select");
    for(var j = 0;j < selects.length;j++){
        var select = selects.get(j);
        if(select.id!="PageSize" && select.id!="CurrentPage"){
            select.disabled="";                
        }
    }
    var textareas = $("#" + id + "  textarea");
    for(var j = 0;j < textareas.length;j++){
        var textarea = textareas.get(j);            
        textarea.disabled="";
        textarea.style.color = '';
        textarea.style.borderColor = '';
        textarea.style.borderWidth = '';
        textarea.style.borderStyle = '';
		textarea.style.backgroundColor = '';
    }        
    var spans = $("#" + id + "  span");
    for(var j = 0;j < spans.length;j++){
        var span = spans.get(j);
        if(span.innerText == " "){
            span.innerText = "";
        }                     
    }
}

function toggleCreate(){
	$( "#add1").toggle( "blind", 400 );
	var btn = $("#toggleCreateBtn");
	if(btn.attr("class")=="opbtn"){
		btn.attr("class","clbtn");
		if($("#queryTable1").size() > 0){
			$("#queryTable1").hide();
		}
	}else{
		btn.attr("class","opbtn");
		if($("#queryTable1").size() > 0){
			$("#queryTable1").show();
		}
	}
}

String.prototype.startsWith = function(str){
	return (this.match("^"+str)==str);
};

//td id 塞值
function setHtml(obj, prefix,replace){
	$("#" + prefix).find("td").each(function() {
        if (!this.id) return; 
        var fn = this.id.replace(replace,"");
        if(fn.startsWith("id_")){
        	if(!empty(obj["id"])){
	        	$(this).html(emptyStr(obj["id"][fn.replace("id_","")]));
	        	return;
        	}
        }
        if (obj[fn] == undefined) return;
        var val = emptyStr(obj[fn]);          
        $(this).html(val);        
	});
}

//span id 塞值
function setSpanHtml(obj, prefix,replace){
	$("#" + prefix).find("span").each(function() {
        if (!this.id) return; 
        var fn = this.id.replace(replace,"");
        if(fn.startsWith("id_")){    
        	if(!empty(obj["id"])){
	        	$(this).html(emptyStr(obj["id"][fn.replace("id_","")]));
	        	return;
        	}
        }
        if (obj[fn] == undefined) return;
        var val = emptyStr(obj[fn]);          
        $(this).html(val);        
	});
}

//td id 清值
function clearHtml(obj, prefix,replace){
	$("#" + prefix).find("td").each(function() {
        if (!this.id) return; 
        var fn = this.id.replace(replace,"");
        if(fn.startsWith("id_")){
        	if(!empty(obj["id"])){
	        	$(this).html(emptyStr(obj["id"][fn.replace("id_","")]));
	        	return;
        	}
        }
        $(this).html("");        
	});
}

//下拉選單轉為span顯示, 隱藏下拉選單
function dropdown2Span(prefix,replace)
{
	$("#" + prefix).find("select").each(function() {
        if (!this.id) return; 
        var txt = $("option:selected", this).text();
        $(this).after("<span>"+txt+"</span>");
        $(this).hide();
	});
}

//span 清值
function clearHtmlSpan(obj, prefix,replace){
	$("#" + prefix).find("td span").each(function() {
        if (!this.id) return; 
        var fn = this.id.replace(replace,"");
        if(fn.startsWith("id_")){
        	if(!empty(obj["id"])){
	        	$(this).html(emptyStr(obj["id"][fn.replace("id_","")]));
	        	return;
        	}
        }
        $(this).html("");        
	});
}

//DivSpan 清值
function clearHtmlDivSpan(prefix){
	$("#" + prefix).find("div span").each(function() {        
        $(this).html("");        
	});
}

function listTrClass(cid){
	$("#"+cid+" tr:odd").addClass("odd");
	$("#"+cid+" tr:even").addClass("even");
}

//數值字串補0 (isLeft:true 代表左方補0)
function paddingZero(id,len,isLeft){
	var str = $("#"+id).val();
	if(isInteger(str)){
		if(str.length < len){
			var zNum = len - str.length;
			if(isLeft){
				for(var i=0;i<zNum;i++){
					str = "0" + str;
				}				
			}else{
				for(var i=0;i<zNum;i++){
					str = str + "0";
				}					
			}
			$("#"+id).val(str);
		}
	}
}

//數值字串補0 
function padding(num, length) {
	for(var len = (num + "").length; len < length; len = num.length) {
		num = "0" + num;            
	}
	return num;
}


/**
 * 初始化日曆元件
 * @param inputId 欄位 ID
 * @param minDateS 日曆最小日期
 * @param maxDateS 日曆最大日期
 */
function datePicker(inputId, minDateS, maxDateS){	
	if(minDateS!=null && minDateS!="" && maxDateS!=null && maxDateS!=""){
		$("#"+inputId).datepicker({
			//showOn : "button",
			//buttonImage : "../images/dete.gif",
			//buttonImageOnly : true,
			changeYear : true,
			changeMonth : true,      
			dateFormat : 'yy/mm/dd',
			minDate : minDateS,
			maxDate : maxDateS
	    });
	}else{
		$("#"+inputId).datepicker({
			//showOn : "button",
			//buttonImage : "../images/dete.gif",
			//buttonImageOnly : true,
			dateFormat : 'yy/mm/dd',
			changeYear : true,
			changeMonth : true
	    });
	}
}

function dateTimePicker(inputId, minDateS, maxDateS){
	var opt={
			showOn: "focus",	  
			changeYear  : true,
			changeMonth : true,
			dateFormat: 'yy/mm/dd',
            showSecond: false,
            timeFormat: 'HH:mm',
            controlType:"select" ,
            minDate: minDateS==(null||"")?null:minDateS,
            maxDate: maxDateS==(null||"")?null:maxDateS
    };
	
	$( "#" + inputId ).datetimepicker(opt);
}

//全形轉半形
function fullToHalf(text){
    var asciiTable = "!\"#$%&\'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~";
    var big5Table  = "%uFF01%u201D%uFF03%uFF04%uFF05%uFF06%u2019%uFF08%uFF09%uFF0A%uFF0B%uFF0C%uFF0D%uFF0E%uFF0F%uFF10%uFF11%uFF12%uFF13%uFF14%uFF15%uFF16%uFF17%uFF18%uFF19%uFF1A%uFF1B%uFF1C%uFF1D%uFF1E%uFF1F%uFF20%uFF21%uFF22%uFF23%uFF24%uFF25%uFF26%uFF27%uFF28%uFF29%uFF2A%uFF2B%uFF2C%uFF2D%uFF2E%uFF2F%uFF30%uFF31%uFF32%uFF33%uFF34%uFF35%uFF36%uFF37%uFF38%uFF39%uFF3A%uFF3B%uFF3C%uFF3D%uFF3E%uFF3F%u2018%uFF41%uFF42%uFF43%uFF44%uFF45%uFF46%uFF47%uFF48%uFF49%uFF4A%uFF4B%uFF4C%uFF4D%uFF4E%uFF4F%uFF50%uFF51%uFF52%uFF53%uFF54%uFF55%uFF56%uFF57%uFF58%uFF59%uFF5A%uFF5B%uFF5C%uFF5D%uFF5E";
    var result = "";
    for (var i = 0; i < text.length; i++) {
        var val = escape(text.charAt(i));
        var j   = big5Table.indexOf(val);
        result += (((j > -1) && (val.length == 6)) ? asciiTable.charAt(j / 6) : text.charAt(i));
    }
    return result;
}

/*
	src：需格式化的数据源，可以是数字或数字字符串
　　num：小数部分长度
　　sep：千分位标记
　　per：小数点标记
　　mode：小数部分截取操作，R：四舍五入，C：Ceil，其他相当于Floor
*/
function formatNbr(src, num, sep, per, mode){
	if(src==null)return "0";
    var nbr;
    var cnt;
    var tmp;
    var int, dec;
  //---
    if(mode=='R' && num<=0){
    	src = Math.round(src);    		
	}
    //---
    switch (typeof(src)){
        case "string":
            nbr=src;
            break;
        case "number": 
            nbr=src.toString();
            break;
        default:
            break;
    }    
    int=nbr.split(".")[0];
    dec=typeof(nbr.split(".")[1])=="undefined"?0:nbr.split(".")[1];
    cnt=Math.floor((int.length-1)/3);
    for (var i=cnt;i>0;i--){
        int=int.substring(int.length-i*3,0)+sep+int.substring(int.length-i*3,int.length);
    }
    if (num>0) {
        if (dec.length>=num) {
            switch (mode) {
                case "R":
                    dec=(Math.round(parseInt(dec)/Math.pow(10, (dec.length-num)))).toString().split(".")[0];
                    break;
                case "C":
                    dec=(Math.ceil(parseInt(dec)/Math.pow(10, (dec.length-num)))).toString().split(".")[0];
                    break;
                default:
                    dec=dec.substring(0,num);
                    break;
            }
        } else {
            tmp=num-dec.length;
            for (var i=0;i<tmp;i++){
                dec=dec+"0";
            }
        }
        if(dec=="0"){
            return int;
        }
        return int+per+dec;
    } else {    	
        return int;
    }
}

//將 select 加入 01~12 月的項目, v=true 則加入請選擇項目
function produceMonthSelect(id, v,selectedCurrentMonth){
	if(v){
		$("#"+id).append("<option value=''>請選擇</option>");
	}
	var month = getTodayDate().substring(5,7);
	for(var h=1; h<=12; h++){
		var hv=h;
		if(h<10){
			hv="0"+h;
		}
		if(selectedCurrentMonth && hv==month){
			$("#"+id).append("<option value='"+hv+"' selected>"+hv+"</option>");
		}else{
			$("#"+id).append("<option value='"+hv+"'>"+hv+"</option>");
		}
		
	}
}

//將 select 加入 00~23 時的項目, v=true 則加入請選擇項目
function produceHourSelect(id, v){
	if(v){
		$("#"+id).append("<option value=''>請選擇</option>");
	}
	for(var h=0; h<24; h++){
		var hv=h;
		if(h<10){
			hv="0"+h;
		}
		$("#"+id).append("<option value='"+hv+"'>"+hv+"</option>");
	}
}

//將 select 加入 00~59 分或秒的項目, v=true 則加入請選擇項目
function produceMinuteSelect(id, v){
	if(v){
		$("#"+id).append("<option value=''>請選擇</option>");
	}
	for(var m=0; m<60; m++){
		var mv=m;
		if(m<10){
			mv="0"+m;
		}
		$("#"+id).append("<option value='"+mv+"'>"+mv+"</option>");	
	}
}

//將 select 加入 96年~目前年度的項目, v=true 則加入請選擇項目
function produceYearSelect(id, v){
	if(v){
		$("#"+id).append("<option value=''>請選擇</option>");
	}	
	var yyy = getNowYear();	
	for(var m=96; m<=yyy; m++){
		var mv=m;		
		$("#"+id).append("<option value='"+mv+"'>"+mv+"</option>");	
	}
}

//將 select 加入+-目前年度的項目, v=true 則加入請選擇項目
function produceYearSelect2(id, minusYear, addYear, v, selectedCurrentYear){
	if(v){
		$("#"+id).append("<option value=''>請選擇</option>");
	}	
	var yyyy = new Date().getFullYear();
	for(var m=yyyy-minusYear; m<=yyyy+addYear; m++){
		var mv=m;
		if(selectedCurrentYear && mv==yyyy){
			$("#"+id).append("<option value='"+mv+"' selected>"+mv+"</option>");
		}else{
			$("#"+id).append("<option value='"+mv+"'>"+mv+"</option>");
		}
	}
}

//取得當年度民國年
function getNowYear(){
	var now = new Date();
	function fourdigits(number) {		
	    return (number < 1000) ? number + 1900 : number;
	}	
	return (fourdigits(now.getYear())-1911);	
}

function defaultZero(val){
	if(empty(val)) return "0";
	var rtn = $.formatNumber(val,{format:"#,###.##", locale:"tw"});
	if(rtn == ""){
		return "0";
	}else{
		return rtn;
	}
}

function formatDate(d){
	if(d == null) return "";
	var date = new Date(d);
	if(typeof(d)=="string"){
		return date.getFullYear()+"/"+(date.getMonth() + 1)+"/"+date.getDate() ;
	}else{
		return d.getFullYear()+"/"+(d.getMonth() + 1)+"/"+d.getDate() ;
	}
}

function formatYearMonth(ym){
	if(!empty(ym) && (ym+"").length == 6){
		return (ym+"").substring(0,4)+"/"+(ym+"").substring(4,6);
	}
	return ym;
}

function toChinYearMonth(ym){
	if(!empty(ym) && (ym+"").length == 6){
		return (parseInt((ym+"").substring(0,4))-1911)+"/"+(ym+"").substring(4,6);
	}
	return ym;
}

//西元日期轉民國日期, 格式: 2016/03/04 轉 105/03/04
function toChinDate(d) {
	var charSplit =  d.split("/");
	return (charSplit[0]-1911+"/"+charSplit[1]+"/"+charSplit[2]);
}

//民國日期轉西元日期, d格式:105/03/04 轉 2016/03/04
function chinDate2WestDate(d){
	var DELIMETER="/";
	if(!empty(d)){
		var ymd=d.split(DELIMETER);
		var value="";
		if(ymd.length==3){
			value=(parseInt(ymd[0])+1911)+DELIMETER+ymd[1]+DELIMETER+ymd[2];
		}else if(ymd.length==2){
			value=(parseInt(ymd[0])+1911)+DELIMETER+ymd[1];
		} else {
			value=(parseInt(ymd[0])+1911)+"";
		}
		return value;
	}
	return d;
}

//取得當天日期(系統日期)
function getTodayDate() {
	var fullDate = new Date();
	var yyyy = fullDate.getFullYear();
	var MM = (fullDate.getMonth() + 1) >= 10 ? (fullDate.getMonth() + 1) : ("0" + (fullDate.getMonth() + 1));
	var dd = fullDate.getDate() < 10 ? ("0"+fullDate.getDate()) : fullDate.getDate();
	var today = yyyy + "/" + MM + "/" + dd;
	return today;
}

function checkAllSubs(obj,className){
	var isChecked = obj.checked;
	$("." + className).each(function(){
		this.checked = isChecked;
	});
}

function addDateDay(odate,d){
	var add_time = d*60*60*24*1000; //設定欲加的天數毫秒，f是欲相加的天數
	var org_day = new Date(odate); //設定一個原始時間
	var org_time = Date.parse(org_day); //將原始時間分解成毫秒
	var SumDay = org_time + add_time; //將兩個時間相加
	var NewDay = new Date(); //設定一個新的時間變數
    var reday = NewDay.setTime(SumDay); //將相加後的時間還原成日期
    var origDay = new Date(reday); //再設定一個時間變數，值為還原後的時間
    //以下則為取出年、月、日
    var d = origDay.getDate();
    var Y = origDay.getFullYear();
    var M = origDay.getMonth();
    if(d<10){d = "0" + d;} //設定日期若為個數則加0
    if((parseInt(M)+1)<10){
    	M = "0" + (parseInt(M)+1);
    }else{
    	M= (parseInt(M)+1);
    }
    //設定為 2016/03/18的日期格式
    var Ok_Day = Y + "/" + M + "/" + d;
    return Ok_Day;
}

/**
 * 日期相差
 * s:秒差
 * n:分差
 * h:時差
 * d:日差
 * w:週差
 * m:月差
 * y:年差
 */
function dateDiff(interval,objSDate,objEDate){
	var dtStart = new Date(objSDate);
	var dtEnd = new Date(objEDate);
	if(isNaN(dtEnd)) return undefined;
	switch (interval) {
		case "s":return parseInt((dtEnd - dtStart) / 1000);
		case "n":return parseInt((dtEnd - dtStart) / 60000);
		case "h":return parseInt((dtEnd - dtStart) / 3600000);
		case "d":return parseInt((dtEnd - dtStart) / 86400000);
		case "w":return parseInt((dtEnd - dtStart) / (86400000 * 7));
		case "m":return (dtEnd.getMonth()+1)+((dtEnd.getFullYear()-dtStart.getFullYear())*12) - (dtStart.getMonth()+1);
		case "y":return dtEnd.getFullYear() - dtStart.getFullYear();
	}
}
	

function pageFlow(showId,hideId){
	$("#"+hideId).hide();
	$("#"+showId).show();
}

function setupDatePicker(inputId,btnId,format){
    df = "%Y/%m/%d";
	if(format!=null && format!=""){
		df = format;
	}
	return Calendar.setup({
		trigger : btnId,
		inputField : inputId,
		dateFormat : df,
		onSelect : function(){this.hide();}
	});
}

/*
 * 只供 日期元件(Calendar) 使用
 * datePicker 日期元件(Calendar)物件
 * min 可選最小日期, 格式 2016/01/01
 * max 可選最大日期, 格式 2016/01/01
 */
function changeDatePickerMinMax(datePicker, min, max){
	if(!empty(min)){
		datePicker.args.min=westStrDate2Date(min);
	}
	
	if(!empty(max)){
		datePicker.args.max=westStrDate2Date(max);
	}

	datePicker.moveTo(datePicker.args.min); //將日期移至指定時間
}

//只能輸入數字
function ValidateNumber(e, pnumber){
    if (!/^\d+$/.test(pnumber))
    {
    	var newValue = /^\d+/.exec(e.value);        
        if (newValue != null){            
            e.value = newValue;       
        }else{         
            e.value = "";   
        } 
    }
    return false;
}

//可以輸入小數，限制只有小數點後一位
function ValidateFloat(e, pnumber){
    if (!/^\d+[.]?[1-9]?$/.test(pnumber))
    {
        e.value = /\d+[.]?[1-9]?/.exec(e.value);
    }
    return false;
}

function confirmPageFlow(showId, hideId, isChangePath, methodName){
	alertify.confirm("資料未儲存，是否確定取消？", function(e){
		if(e){
			pageFlow(showId,hideId);
			if(isChangePath!=null && isChangePath){
				changePathMethod(methodName);
			}
		}
	});
}

//更改功能路徑的作業名稱
function changePathMethod(methodName){
	if(methodName==null || methodName==""){
		$("#pathMethod").html("查詢");
	}else{
		$("#pathMethod").html(methodName);
	}
}

//開啟 Bootstrap's Modal 視窗
function openModalWin(selector) {
	$(selector).modal({
		backdrop : 'static',
		keyboard : false
	}).on("shown.bs.modal", function(e) {
		$("body").addClass("modal-open");
	});
}

//判斷 IE 版本
function GetIEVersion(){
	var sAgent=window.navigator.userAgent;
	var Idx=sAgent.indexOf("MSIE");

	//If IE, return version number.
	if(Idx>0){
		return parseInt(sAgent.substring(Idx+ 5, sAgent.indexOf(".", Idx)));
	}else if(!!navigator.userAgent.match(/Trident\/7\./)){
		//If IE 11 then look for Updated user agent string.
		return 11;
	}else{
		return 0; //It is not IE
	}
}

//重設倒數登出時間
function resetTimeout(){
	t=60*30; //三十分鐘	
}

//設定 radio 預設值選取
function setRadioCheckedValueByName(radioName, value){
	$("input[name="+radioName+"]").each(function(){
		if(this.value==value){
			this.checked=true;
		}
	});
}
//浮點數相乘
function accMul(arg1,arg2) { 
	var m=0,s1=arg1.toString(),s2=arg2.toString(); 
	try{m+=s1.split(".")[1].length}catch(e){} 
	try{m+=s2.split(".")[1].length}catch(e){} 
	return Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m) ;
} 

//更改功能名稱標題
function changeActionTitle(s){
	$("#actionTitle").html(s);
}

//隱藏功能路徑的作業名稱
function hidePathMethod(){
	$("#pathMethod").hide();
}

//顯示功能路徑的作業名稱
function showPathMethod(){
	$("#pathMethod").show();
}

//顯示功能路徑的作業名稱
function showPathMethod(){
	$("#pathMethod").show();
}

//數值字串補0 (isLeft:true 代表左方補0)
function paddingZeroForVal(val,len,isLeft){
	var str = val;
	if(str.length < len){
		var zNum = len - str.length;
		if(isLeft){
			for(var i=0;i<zNum;i++){
				str = "0" + str;
			}				
		}else{
			for(var i=0;i<zNum;i++){
				str = str + "0";
			}					
		}
		return str;
	}
	return val;
}

/**
 * 將物件陣列的對應欄位轉成選單
 * @param selectId 需操作選單 ID(不用 # 字號)
 * @param objList 需加入選單的物件陣列
 * @param value 選單的值, 給對應的物件欄位名稱
 * @param text 選單的顯示, 給對應的物件欄位名稱
 * @param isNullOption 是否要有"請選擇"選項
 */
function objList2Select(selectId, objList, value, text, isNullOption){
	$("#"+selectId+" option").remove();
	if(isNullOption){
		$("#"+selectId).append($("<option></option>").attr("value", "").text("請選擇"));
	}
	for(var i=0; i<objList.length; i++){
		var o=objList[i];
    	$("#"+selectId).append($("<option></option>").attr("value", o[value]).text(o[text]));
  	}
}

$.validator.addMethod("requiredSelect", function(value) {
	return (value!="" && value!="-");
}, '必填');
$.validator.addMethod("LetterOrInt", function(value) {
	return isLetterOrInteger(value);
}, '請輸入英數字');
$.validator.addMethod("PID", function(value) {
	if(value=="")return true;
	return checkID(value.toUpperCase());
}, '身份證號格式錯誤');
$.validator.addMethod("CID", function(value) {
	if(value=="")return true;
	return checkCID(value);
}, '統一編號格式錯誤');
$.validator.addMethod("Telephone", function(value) {
	if(value=="")return true;
	return isTelephone(value);
}, '格式錯誤');
$.validator.addMethod("Fax", function(value) {
	if(value=="")return true;
	return isTelephone(value);
}, '格式錯誤');
$.validator.addMethod("selectReq", function(value) {
	if(value=="" || value=="-")return false;
	else return true;
}, '必填');
$.validator.addMethod("checkPwd", function(value) {
	if(value=="")return true;
	return isLetterOrInteger(value);
}, '密碼格式錯誤');
$.validator.addMethod("integer", function(value) {
	if(value=="")return true;
	return isInteger(value);
}, '格式錯誤');
$.validator.addMethod("Email", function(value) {
	if(value=="")return true;
	return isEmail(value);
}, 'e-mail格式錯誤');
$.validator.addMethod("pcid", function(value) {
	if(value=="")return true;
	if(value.lenght>8){
		return checkID(value);
	}else{
		return checkCID(value.toUpperCase());
	}
}, '身份證號或統一編號格式錯誤');
$.validator.addMethod("ip", function(value) {
	return check_IP(value);
}, 'IP格式錯誤');

//--日期起迄檢查
$.validator.messages["StartEndDateChk"] ="迄日不得早於起日";
$.validator.methods.StartEndDateChk = function(value, element, param) {	
	if (!/Invalid|NaN/.test(new Date(value)) && !/Invalid|NaN/.test(new Date($(param).val()))) {
		var valE = chinDate2WestDate(value);		
		var valS = chinDate2WestDate($(param).val());		
    	return new Date(valE) >= new Date(valS);
    }
    return true;
};

//--數字起迄檢查
$.validator.messages["rangeChk"]="迄金額不得大於起金額";
$.validator.methods.rangeChk = function(value, element, param){
	if(!/Invalid|NaN/.test(Number(value)) && !/Invalid|NaN/.test(Number($(param).val()))){
		return Number(value) >= Number($(param).val());
    }
    return true;
};

//--密碼檢查
$.validator.messages["confirmPw"] ="確認密碼與密碼不符";
$.validator.methods.confirmPw = function(value, element, param) {	
    return (value == $(param).val());
};

//--年月起迄檢查
$.validator.messages["YearMonthSEChk"] ="年月迄不得早於年月起";
$.validator.methods.YearMonthSEChk = function(value, element, param) {			
	if (!/Invalid|NaN/.test(value) && !/Invalid|NaN/.test($(param).val()) && value!="" && $(param).val()!="") {
    	return Number(value.replace("/","")) >= Number($(param).val().replace("/",""));
    }
    return true;
};

//--年起迄檢查
$.validator.messages["YearSEChk"] ="年迄不得早於年起";
$.validator.methods.YearSEChk = function(value, element, param) {			
	if (!/Invalid|NaN/.test(value) && !/Invalid|NaN/.test($(param).val()) && value!="" && $(param).val()!="") {
    	return Number(value) >= Number($(param).val());
    }
    return true;
};
 
$.validator.addMethod("checkAcc", function(value) {
	if(value=="")return true;
	return isAccount(value);
}, '帳號格式錯誤');

$.validator.addMethod("isPositiveInteger", function(value) {
	if(value=="")return true;
	return isPositiveInteger(value);
}, '請輸入正整數');

$.validator.addMethod("isPositiveIntegerZero", function(value) {
	if(value=="")return true;
	return isPositiveIntegerZero(value);
}, '請輸入正整數');

$.validator.addMethod("checkTimeHMS", function(value) {
	if(value=="")return true;
	return isTimeHMS(value);
},'時間格式錯誤');

//字串陣列轉陣列
function strArray2Array(strArray){
	var array=[];
	if($.isArray(strArray)){
		array=strArray;
	}else if(typeof strArray==="string"){
		var parts=strArray.replace(/[\[\]]/g, "").split(/[\s,]+/);
		for(var i=0; i<parts.length; i++){
			array[i]=parts[i];
		}
	}
	return array;
}

//西元字串日期轉日期物件, strDate 格式: 2016/01/01
function westStrDate2Date(strDate){
	var strArray=strDate.split("/");
	if(strArray.length==3){
		//月需要 -1, 因為月份是 0-11
		return new Date(strArray[0], (parseInt(strArray[1])-1), strArray[2]);
	}else if(strArray.length==2){
		//月需要 -1, 因為月份是 0-11
		return new Date(strArray[0], (parseInt(strArray[1])-1));
	}else if(strArray.length==1){
		return new Date(strArray[0]);
	}else{
		return null;
	}
}

//檢查起訖日間隔, param 格式:[#起始日ID(#inputID), 間隔數值(1), 間隔單位(y or Y)]
$.validator.addMethod("dateRange", 
	function(value, element, param){
		var array=strArray2Array(param);
		var startSD=chinDate2WestDate($(array[0]).val());
		var endSD=chinDate2WestDate(value);
		var startD=westStrDate2Date(startSD);
		var endD=westStrDate2Date(endSD);
		var d=array[2];
		var dateUnit=1000*60*60*24; //一天
		if(d.toUpperCase()=="Y"){
			dateUnit=dateUnit*30*12; //一年
		}else if(d.toUpperCase()=="M"){
			dateUnit=dateUnit*30; //一個月
		}
		var between=Math.floor((endD.getTime()-startD.getTime())/dateUnit);//相差日期
		return (between<array[1]);
	},
	function(param, element){
		var array=strArray2Array(param);
		var d=array[2];
		if(d.toUpperCase()=="Y"){
			array[2]="年";
		}else if(d.toUpperCase()=="M"){
			array[2]="月";
		}else if(d.toUpperCase()=="D"){
			array[2]="日";
		}
		return $.validator.format("年月迄與年月起相隔不得大於 {1} {2}", array);
	}
);

/**
 * 取得檔案副檔名
 * @param filename
 * @returns {String}
 */
function getFilenameExt(filename){	
	var extIndex = filename.lastIndexOf('.');	
	var ext="";
	if (extIndex != -1) {
	   //var name = filename.substr(0, extIndex);                     //檔名不含副檔名	 
	   ext= filename.substr(extIndex+1, filename.length);  //副檔名
	}
	return ext;
}

function roundX(val, precision) {
	return Math.round(Math.round(val * Math.pow(10, (precision || 0) + 1)) / 10) / Math.pow(10, (precision || 0));
}


function formatDate(date,joinChar) {
    var d = new Date(date);
    month = '' + (d.getMonth() + 1);
    day = '' + d.getDate();
    year = d.getFullYear();

    if (month.length < 2) month = '0' + month;
    if (day.length < 2) day = '0' + day;

    return [year, month, day].join(joinChar);
}

/**
 * 根據參數內容，新增一列資料(一筆記錄)
 * @param cellFuncs
 * @param returnObj 
 * @returns {String}
 */
function renderRow(cellFuncs, returnObj){	
	var tr = "<tr>";
	for (var cellNum = 0; cellNum < cellFuncs.length; cellNum++) {
		var func = cellFuncs[cellNum];
		if (typeof func == 'function') {
			retStr = func(returnObj) ;
			if(retStr == null || retStr == "null"){
				retStr = "&nbsp;";
			}
			tr += "<td>" + retStr + "</td>";
		} else {
			tr += "<td>&nbsp;</td>";
		}
	}
	return tr += "</tr>";
}

/**
 * 根據「清單內容」及「函數陣列」，渲染至指定的表格<tbody>之中
 * @param selector
 * @param cellFuncs 
 * @param list 
 * @returns {String}
 */
function renderRowTable(selector, cellFuncs, list) {
	$(selector).html("");
	if(list instanceof Array) {
		for(var i in list) {
			$(selector).append(renderRow(cellFuncs, list[i]));
		}
	} else {
		$(selector).append(renderRow(cellFuncs, list));
	}
}

/**
 * Escape HTML special chars
 * @param str
 * @returns {String}
 */
function escapeHtml(str) {
	var _str = '';
    if ( str.length == 0 ) return '';
    return str.replace(/&/g, "&amp;")
			  .replace(/</g, "&lt;")
			  .replace(/>/g, "&gt;")
			  .replace(/"/g, "&quot;")
			  .replace(/'/g, "&#039;"); 
}

function chekNeedDecryptData(dataColumn ,encryptDataColumn ,key, dataAlign , needNumberFormat){
	var data;
	if(key!='' && null != encryptDataColumn && '' != encryptDataColumn){
		data = decrypt(encryptDataColumn,key,needNumberFormat);
	} else {
		data = dataColumn;
	}
	if(needNumberFormat == true){
		data = defaultZero(data);
	}
	if(null == dataAlign){
		return data;
	}
	return "<div align='"+dataAlign+"'>" + data + "</div>";
}
