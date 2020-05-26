(function($) {

    $.fn.fieldsToJson = function(prefix) {

        if (!JSON || !JSON.stringify) {

            alert("JSON is required!");

            return "";

        }

        var obj = {};

        $("#" + prefix).find("input,select,textarea")

        .each(function() {
            //alert(this.id);
            if (!this.id) return; //Skip no id attribute
            if(this.type == "button" || this.type == "submit" || this.type == "reset" || this.type == "image"){
            	return ;
            }
            var fn = this.id;

            //Filtered by prefix

            //if (prefix && fn.indexOf(prefix) == -1) return;

            //Remove prefix

            //if (prefix) fn = fn.substr(prefix.length, fn.length - prefix.length);

            var val = this.value + ""; //Avoid IE8 JSON bug

            if (this.type == "checkbox" || this.type == "radio"){
				fn = fn.substring(0,fn.indexOf("."));                
				if(!this.checked){return;}
				if(obj[fn] == null || obj[fn] == "" || obj[fn] == "undefined"){
					val = this.value;
				}else{
					val = ";" + this.value;
				}
            }else if (this.type == "select-one"){

                val = this.value + "";

            }else if (this.type == "select-multiple") {

                var selected = [];

                $(this).children().each(function(i) {

                    if (this.selected) selected.push(i);

                });

                val = selected.join(";");

            }
			if(obj[fn] == null || obj[fn] == "" || obj[fn] == "undefined"){
				obj[fn] = val;
			}else{
				obj[fn] += val;
			}

        });

        //alert(JSON.stringify(obj));

        return obj;

    };
    
    $.fn.f2jWithoutName = function(prefix,replace) {

        if (!JSON || !JSON.stringify) {

            alert("JSON is required!");

            return "";

        }

        var obj = {};

        $("#" + prefix).find("input,select,textarea")

        .each(function() {
            //alert(this.id);
            if (!this.id) return; //Skip no id attribute
            if(this.type == "button" || this.type == "submit" || this.type == "reset" || this.type == "image"){
            	return ;
            }
            var fn = this.id.replace(replace,"");

            //Filtered by prefix

            //if (prefix && fn.indexOf(prefix) == -1) return;

            //Remove prefix

            //if (prefix) fn = fn.substr(prefix.length, fn.length - prefix.length);

            var val = this.value + ""; //Avoid IE8 JSON bug

            if (this.type == "checkbox" || this.type == "radio"){
				fn = fn.substring(0,fn.indexOf("."));                
				if(!this.checked){return;}
				if(obj[fn] == null || obj[fn] == "" || obj[fn] == "undefined"){
					val = this.value;
				}else{
					val = ";" + this.value;
				}
            }else if (this.type == "select-one"){

                val = this.value + "";

            }else if (this.type == "select-multiple") {

                var selected = [];

                $(this).children().each(function(i) {

                    if (this.selected) selected.push(i);

                });

                val = selected.join(";");

            }
			if(obj[fn] == null || obj[fn] == "" || obj[fn] == "undefined"){
				obj[fn] = val;
			}else{
				obj[fn] += val;
			}

        });

        //alert(JSON.stringify(obj));

        return obj;

    };

    $.fn.jsonStrToFields = function(jsonString, prefix) {

        if (!JSON || !JSON.parse) {

            alert("JSON is required!");

            return "";

        }

        var obj = JSON.parse(jsonString);

        $("#" + prefix).find("input,select,textarea")

        .each(function() {

            if (!this.id) return; //Skip no id attribute

            var fn = this.id;

            //Filtered by prefix

            //if (prefix && fn.indexOf(prefix) == -1) return;

            //Remove prefix

            //if (prefix) fn = fn.substr(prefix.length, fn.length - prefix.length);
			
			if (this.type == "checkbox" || this.type == "radio"){
				fn = fn.substring(0,fn.indexOf("."));   
			}
			
            if (obj[fn] == undefined) return;

            var val = obj[fn];

            if (this.type == "checkbox"){
				var values = val.split(";");
				var isFind = false;
				for(var i = 0;i < values.length;i++){
					if(values[i] == this.value){
						isFind = true;
						break;
					}
				}
                this.checked = isFind;

            }else if (this.type == "radio"){
				if(val == this.value){
					this.checked = true;
				}else{
					this.checked = false;
				}
			}else if (this.type == "select-one")

                this.value = val;

            else if (this.type == "select-multiple") {

                var selected = val.split(";");

                var options = $(this).children();

                options.filter(":selected").removeAttr("selected");

                for (var i = 0; i < selected.length; i++) {

                    var opt = options[selected[i]];

                    if (opt) opt.selected = true;

                }

            }

            else this.value = val;

        });

    };
	
	$.fn.jsonToFields = function(obj, prefix) {

        $("#" + prefix).find("input,select,textarea")

        .each(function() {

            if (!this.id) return; //Skip no id attribute
            if(this.type == "button" || this.type == "submit" || this.type == "reset" || this.type == "image"){
            	return ;
            }
            var fn = this.id;

            //Filtered by prefix

            //if (prefix && fn.indexOf(prefix) == -1) return;

            //Remove prefix

            //if (prefix) fn = fn.substr(prefix.length, fn.length - prefix.length);
			
			if (this.type == "checkbox" || this.type == "radio"){
				fn = fn.substring(0,fn.indexOf("."));   
			}
			
            if (obj[fn] == undefined) return;

            var val = obj[fn];

            if (this.type == "checkbox"){
				var values = val.split(";");
				var isFind = false;
				for(var i = 0;i < values.length;i++){
					if(values[i] == this.value){
						isFind = true;
						break;
					}
				}
                this.checked = isFind;

            }else if (this.type == "radio"){
				if(val == this.value){
					this.checked = true;
				}else{
					this.checked = false;
				}
			}else if (this.type == "select-one")

                this.value = val;

            else if (this.type == "select-multiple") {

                var selected = val.split(";");

                var options = $(this).children();

                options.filter(":selected").removeAttr("selected");

                for (var i = 0; i < selected.length; i++) {

                    var opt = options[selected[i]];

                    if (opt) opt.selected = true;

                }

            }

            else this.value = val;

        });

    };
	
	$.fn.j2fWithoutName = function(obj, prefix,replace) {

        $("#" + prefix).find("input,select,textarea")

        .each(function() {

            if (!this.id) return; //Skip no id attribute
            if(this.type == "button" || this.type == "submit" || this.type == "reset" || this.type == "image"){
            	return ;
            }
            var fn = this.id.replace(replace,"");

            //Filtered by prefix

            //if (prefix && fn.indexOf(prefix) == -1) return;

            //Remove prefix

            //if (prefix) fn = fn.substr(prefix.length, fn.length - prefix.length);			
			if (this.type == "checkbox" || this.type == "radio"){
				fn = fn.substring(0,fn.indexOf("."));   
			}
			
            if (obj[fn] == undefined) return;

            var val = obj[fn];
            
            if (this.type == "checkbox"){            	
				var values = (val+"").split(";");
				var isFind = false;
				for(var i = 0;i < values.length;i++){
					if(values[i] == this.value){						
						isFind = true;
						break;
					}
				}
                this.checked = isFind;

            }else if (this.type == "radio"){   
				if(val+"" == this.value+""){
					this.checked = true;
				}else{
					this.checked = false;
				}
			}else if (this.type == "select-one")

                this.value = val;

            else if (this.type == "select-multiple") {

                var selected = val.split(";");

                var options = $(this).children();

                options.filter(":selected").removeAttr("selected");

                for (var i = 0; i < selected.length; i++) {

                    var opt = options[selected[i]];

                    if (opt) opt.selected = true;

                }

            }

            else this.value = val;

        });

    };
	
	$.fn.fieldsToJsonStr = function(prefix,bypass) {

        if (!JSON || !JSON.stringify) {

            alert("JSON is required!");

            return "";

        }

        var obj = {};

        $("#" + prefix).find("input,select,textarea")

        .each(function() {
            //alert(this.id);
            if (!this.id) return; //Skip no id attribute
            if(this.type == "button" || this.type == "submit" || this.type == "reset" || this.type == "image" || this.type == "hidden"){
            	return ;
            }
            if(bypass != null){
            	if(bypass[this.id] != null && bypass[this.id] != "" && bypass[this.id] != "undefined"){
            		return;
            	}
            }
            /*
            if(this.readOnly || this.disabled){            	
            	return ;
            }
            */
            var fn = this.id;

            //Filtered by prefix

            //if (prefix && fn.indexOf(prefix) == -1) return;

            //Remove prefix

            //if (prefix) fn = fn.substr(prefix.length, fn.length - prefix.length);

            var val = this.value + ""; //Avoid IE8 JSON bug

            if (this.type == "checkbox" || this.type == "radio"){
				fn = this.name;//fn.substring(0,fn.indexOf("."));                
				if(!this.checked){return;}
				if(obj[fn] == null || obj[fn] == "" || obj[fn] == "undefined"){
					val = this.value;
				}else{
					val = ";" + this.value;
				}
            }else if (this.type == "select-one"){

                val = this.value + "";

            }else if (this.type == "select-multiple") {

                var selected = [];

                $(this).children().each(function(i) {

                    if (this.selected) selected.push(i);

                });

                val = selected.join(";");

            }
			if(obj[fn] == null || obj[fn] == "" || obj[fn] == "undefined"){
				obj[fn] = val;
			}else{
				obj[fn] += val;
			}

        });

        //alert(JSON.stringify(obj));

        return JSON.stringify(obj);

    };
	
	$.fn.j2f = function(obj, prefix) {

        $("#" + prefix).find("input,select,textarea")

        .each(function() {

            if (!this.id) return; //Skip no id attribute
            if(this.type == "button" || this.type == "submit" || this.type == "reset" || this.type == "image"){
            	return ;
            }
            var fn = this.id;

            //Filtered by prefix

            //if (prefix && fn.indexOf(prefix) == -1) return;

            //Remove prefix

            //if (prefix) fn = fn.substr(prefix.length, fn.length - prefix.length);
			
			if (this.type == "checkbox" || this.type == "radio"){
				fn = this.name;   
			}
			
            if (obj[fn] == undefined) return;

            var val = obj[fn];

            if (this.type == "checkbox"){
				var values = val.split(";");
				var isFind = false;
				for(var i = 0;i < values.length;i++){
					if(values[i] == this.value){
						isFind = true;
						break;
					}
				}
                this.checked = isFind;

            }else if (this.type == "radio"){
				if(val == this.value){
					this.checked = true;
				}else{
					this.checked = false;
				}
			}else if (this.type == "select-one")

                this.value = val;

            else if (this.type == "select-multiple") {

                var selected = val.split(";");

                var options = $(this).children();

                options.filter(":selected").removeAttr("selected");

                for (var i = 0; i < selected.length; i++) {

                    var opt = options[selected[i]];

                    if (opt) opt.selected = true;

                }

            }

            else this.value = val;

        });

    };
	
	$.fn.f2j = function(prefix) {

        if (!JSON || !JSON.stringify) {

            alert("JSON is required!");

            return "";

        }

        var obj = {};

        $("#" + prefix).find("input,select,textarea")

        .each(function() {
            //alert(this.id);
            if (!this.id) return; //Skip no id attribute
            if(this.type == "button" || this.type == "submit" || this.type == "reset" || this.type == "image"){
            	return ;
            }
            var fn = this.id;

            //Filtered by prefix

            //if (prefix && fn.indexOf(prefix) == -1) return;

            //Remove prefix

            //if (prefix) fn = fn.substr(prefix.length, fn.length - prefix.length);

            var val = this.value + ""; //Avoid IE8 JSON bug

            if (this.type == "checkbox" || this.type == "radio"){
				fn = this.name;                
				if(!this.checked){return;}
				if(obj[fn] == null || obj[fn] == "" || obj[fn] == "undefined"){
					val = this.value;
				}else{
					val = ";" + this.value;
				}
            }else if (this.type == "select-one"){

                val = this.value + "";

            }else if (this.type == "select-multiple") {

                var selected = [];

                $(this).children().each(function(i) {

                    if (this.selected) selected.push(i);

                });

                val = selected.join(";");

            }
			if(obj[fn] == null || obj[fn] == "" || obj[fn] == "undefined"){
				obj[fn] = val;
			}else{
				obj[fn] += val;
			}

        });

        //alert(JSON.stringify(obj));

        return obj;

    };
    
    $.fn.f2jStr = function(prefix) {

        if (!JSON || !JSON.stringify) {

            alert("JSON is required!");

            return "";

        }

        var obj = {};

        $("#" + prefix).find("input,select,textarea")

        .each(function() {
            //alert(this.id);
            if (!this.id) return; //Skip no id attribute
            if(this.type == "button" || this.type == "submit" || this.type == "reset" || this.type == "image"){
            	return ;
            }
            var fn = this.id;

            //Filtered by prefix

            //if (prefix && fn.indexOf(prefix) == -1) return;

            //Remove prefix

            //if (prefix) fn = fn.substr(prefix.length, fn.length - prefix.length);

            var val = this.value + ""; //Avoid IE8 JSON bug

            if (this.type == "checkbox" || this.type == "radio"){
				fn = this.name;                
				if(!this.checked){return;}
				if(obj[fn] == null || obj[fn] == "" || obj[fn] == "undefined"){
					val = this.value;
				}else{
					val = ";" + this.value;
				}
            }else if (this.type == "select-one"){

                val = this.value + "";

            }else if (this.type == "select-multiple") {

                var selected = [];

                $(this).children().each(function(i) {

                    if (this.selected) selected.push(i);

                });

                val = selected.join(";");

            }
			if(obj[fn] == null || obj[fn] == "" || obj[fn] == "undefined"){
				obj[fn] = val;
			}else{
				obj[fn] += val;
			}

        });

        //alert(JSON.stringify(obj));

        return JSON.stringify(obj);

    };
	
    $.fn.f2jwn = function(prefix,replace) {

        if (!JSON || !JSON.stringify) {

            alert("JSON is required!");

            return "";

        }

        var obj = {};

        $("#" + prefix).find("input,select,textarea")

        .each(function() {
            //alert(this.id);
            if (!this.id) return; //Skip no id attribute
            if(this.type == "button" || this.type == "submit" || this.type == "reset" || this.type == "image"){
            	return ;
            }
            var fn = this.id.replace(replace,"");

            //Filtered by prefix

            //if (prefix && fn.indexOf(prefix) == -1) return;

            //Remove prefix

            //if (prefix) fn = fn.substr(prefix.length, fn.length - prefix.length);

            var val = this.value + ""; //Avoid IE8 JSON bug

            if (this.type == "checkbox" || this.type == "radio"){
				fn = this.name.replace(replace,"");                
				if(!this.checked){return;}
				if(obj[fn] == null || obj[fn] == "" || obj[fn] == "undefined"){
					val = this.value;
				}else{
					val = ";" + this.value;
				}
            }else if (this.type == "select-one"){

                val = this.value + "";

            }else if (this.type == "select-multiple") {

                var selected = [];

                $(this).children().each(function(i) {

                    if (this.selected) selected.push(i);

                });

                val = selected.join(";");

            }
			if(obj[fn] == null || obj[fn] == "" || obj[fn] == "undefined"){
				obj[fn] = val;
			}else{
				obj[fn] += val;
			}

        });

        //alert(JSON.stringify(obj));

        return obj;

    };
    
    $.fn.f2jwnStr = function(prefix,replace) {

        if (!JSON || !JSON.stringify) {

            alert("JSON is required!");

            return "";

        }

        var obj = {};

        $("#" + prefix).find("input,select,textarea")

        .each(function() {
            //alert(this.id);
            if (!this.id) return; //Skip no id attribute
            if(this.type == "button" || this.type == "submit" || this.type == "reset" || this.type == "image"){
            	return ;
            }
            var fn = this.id.replace(replace,"");

            //Filtered by prefix

            //if (prefix && fn.indexOf(prefix) == -1) return;

            //Remove prefix

            //if (prefix) fn = fn.substr(prefix.length, fn.length - prefix.length);

            var val = this.value + ""; //Avoid IE8 JSON bug

            if (this.type == "checkbox" || this.type == "radio"){
				fn = this.name.replace(replace,"");                
				if(!this.checked){return;}
				if(obj[fn] == null || obj[fn] == "" || obj[fn] == "undefined"){
					val = this.value;
				}else{
					val = ";" + this.value;
				}
            }else if (this.type == "select-one"){

                val = this.value + "";

            }else if (this.type == "select-multiple") {

                var selected = [];

                $(this).children().each(function(i) {

                    if (this.selected) selected.push(i);

                });

                val = selected.join(";");

            }
			if(obj[fn] == null || obj[fn] == "" || obj[fn] == "undefined"){
				obj[fn] = val;
			}else{
				obj[fn] += val;
			}

        });

        //alert(JSON.stringify(obj));

        return JSON.stringify(obj);

    };
    
    $.fn.j2fwn = function(obj, prefix,replace) {

    	//-- 增加  class=valueLabel 的顯示資料(span 或 label div 皆可)  by mingfu at 2014-04-28
        $("#" + prefix).find("input,select,textarea,.valueLabel")

        .each(function() {

            if (!this.id) return; //Skip no id attribute
            if(this.type == "button" || this.type == "submit" || this.type == "reset" || this.type == "image"){
            	return ;
            }
            var fn = this.id.replace(replace,"");

            //Filtered by prefix

            //if (prefix && fn.indexOf(prefix) == -1) return;

            //Remove prefix

            //if (prefix) fn = fn.substr(prefix.length, fn.length - prefix.length);			
			if (this.type == "checkbox" || this.type == "radio"){
				fn = this.name.replace(replace,"");   
			}
			
			//-- 修正資料若是複合 Key 中的欄位，透過  id 物件中取出資料  by mingfu at 2014-04-08
			//-- old
            if (obj[fn] == undefined) return;
            var val = obj[fn];
            //-- fix
			/*
			var val = null;
			if(obj.id && (fn in obj.id)){ //--如果欄位名稱剛好命名為 id , 需要先取 id 物件中的屬性(id) 
				val =obj.id[fn];
			}else if(obj[fn] || obj[fn] == '0'){ //--考慮資料為 0 時
				val =obj[fn];
			}else{
				return;
			}*/			
			
            if (this.type == "checkbox"){            	
				var values = (val+"").split(";");
				var isFind = false;
				for(var i = 0;i < values.length;i++){
					if(values[i] == this.value){						
						isFind = true;
						break;
					}
				}
                this.checked = isFind;

            }else if (this.type == "radio"){   
				if(val+"" == this.value+""){
					this.checked = true;
				}else{
					this.checked = false;
				}
			}else if (this.type == "select-one")

                this.value = val;

            else if (this.type == "select-multiple") {

                var selected = val.split(";");

                var options = $(this).children();

                options.filter(":selected").removeAttr("selected");

                for (var i = 0; i < selected.length; i++) {

                    var opt = options[selected[i]];

                    if (opt) opt.selected = true;

                }

            }else if ($(this).hasClass("valueLabel")){
            	//-- 增加  class=valueLabel 的顯示資料(span 或 label div 皆可) by mingfu at 2014-04-28
            	if ($(this).hasClass("formatMoneyNumber")){ //--額外格式化金額
            		val = formatMoneyNumber(val, 0 , '$' , true);
            	}
            	$(this).text(val);
            }
            else this.value = val;

        });

    };
    
})(jQuery);	