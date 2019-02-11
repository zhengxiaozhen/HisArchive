
function StringBuilder(){
	this.arr=[]
}
StringBuilder.prototype.append=function(a){
	this.arr.push(a)
};
StringBuilder.prototype.appendFormat=function(){
	for(var a=arguments[0],c=0;c<arguments.length-1;c++)
		a=a.replace(new RegExp("\\{"+c+"\\}"),arguments[c+1]);
	this.arr.push(a)
};
StringBuilder.prototype.toString=function(){
	return this.arr.join("")
};

//兼容的onclick
function Text_OnClick(id)
{ 
   var ie=navigator.appName=="Microsoft Internet Explorer" ? true : false;
   if(ie)
   {
       document.getElementById(id).click();
   }
   else
   {
       var a=document.createEvent('MouseEvents');
       a.initEvent('click', true, true);
       document.getElementById(id).dispatchEvent(a);
   }
}
var item_parentbject;
window.item_city_suggest = function(){
	this.item_Remoreurl = ''; // 远程URL地址
	this.item_object = '';
	this.item_zdmc = '';
	this.show_zdmc = '';
	this.item_id2 = '';
	this.item_taskid = 0;
	this.item_delaySec = 100; // 默认延迟多少毫秒出现提示框
	this.item_lastkeys_val = -1;
	this.item_lastinputstr = '';
	this.item_citys = new Array();
	/**
	*赋值城市数组
	*/
	this.item_setArr_Citys = function(citys){
	    this.item_citys = citys;
	}
	/**
	* 初始化类库
	*/
	this.item_init_zhaobussuggest=  function(){
		var objBody = document.getElementsByTagName("body").item(0);
		var objiFrame = document.createElement("iframe");
		var objplatform = document.createElement("div");
		objiFrame.setAttribute('id','top_getiframe');
		objiFrame.style.zindex='100';
		objiFrame.style.border='0';
		objiFrame.style.position = 'absolute';
		objplatform.setAttribute('id','top_getplatform');
		objplatform.setAttribute('align','left');
		objplatform.style.position = 'absolute';
		objplatform.style.border = 'solid 1px #7f9db9';
		objplatform.style.background = '#ffffff';
		objplatform.style.padding = '0px 3px 3px 3px';
		objBody.appendChild(objiFrame);
		objiFrame.ownerDocument.body.appendChild(objplatform);
		if(!document.all) {
			window.document.addEventListener("click",this.item_hidden_suggest,false);
		}else{
			window.document.attachEvent("onclick",this.item_hidden_suggest);
		}
	}
    /**********************************************getPinYinByCity()*******************************************/
    //
    this.item_getPinYinByCity = function(cityName){
	    var pinYin = "";
	    for(var i = 0,len = this.item_citys.length;i<len;i++){
		    if(cityName == this.item_citys[i][0]){
			    pinYin = this.item_citys[i][1];
			    break;
		    }
	    }
	    return pinYin;
    }
	/***************************************************fill_div()*********************************************/
	//函数功能：动态填充div的内容，该div显示所有的提示内容
	//函数参数：allplat 一个字符串数组，包含了所有可能的提示内容
	this.item_fill_div = function(){
		var zdmc = this.show_zdmc;
		var allplat;
		var msgplat = '';
		var platkeys = this.item_object.value;
        platkeys=this.ltrim(platkeys);		
		if(!platkeys){
			var code = encodeURI(platkeys,"utf-8");
			var msg = "";
			msgplat += '<div id="cityhead" style="overflow-x:scroll;" >';
			msgplat += '<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:4px;color:#999999;font-size:12px;line-height:22px;" id="data_tab">';
			var pageSize  = 0;
			var totalSize = 0;
			$.ajax({
				url:"/js/plugins/dictionary/action/getData.jsp",
				data:{"zdmc":this.item_zdmc,"code":code},
				type:"post",
				async:false,
				dataType:"json",
				success: function(datas){
					if(datas.res!='success'){
						msg = '<div class="ds_input_tips ds_input_tips_no">对不起，找不到字典:'+zdmc+'！</div>';
						pageSize = 0;
					}else{
						pageSize = Math.ceil(datas.Total/10);
						totalSize = datas.Total;
						if(datas.data.length<1){
							msg = '<div class="ds_input_tips ds_input_tips_no">对不起，找不到：'+platkeys+'</div>';
						}
						allplat = datas.data;
						item_parentbject.item_setArr_Citys(allplat);
						$(datas.data).each(function(index, element) {
							var index_ = index+1;
							var chinese = element.codecn;
							var code = element.code;
							var szm = element.szm;
							var all_py = element.all_py;						
							msgplat += '<tr class="tr_poi" onclick="item_parentbject.item_add_input_text(\'' 
									+ chinese + '\',\'' + code + '\')"><td class="numg_01">'
									+ index_ +'</td><td class="numg_02">' + code + '</td><td class="trksd_03">' + chinese + '</td></tr>';
						});
					}
					
				}
			});
			msgplat += '</table>';
			msgplat += '<div class="fenye_div">';
			msgplat += '<input type="hidden" value="'+totalSize+'" id="totalSize" name="totalSize" />';
			msgplat += '<ul class="fenye_ul">';
			msgplat += '<li title="第一页" class="fenye_01" onclick="item_parentbject.firstPage(\''+code+'\')"></li>';
			msgplat += '<li title="上一页" class="fenye_02" onclick="item_parentbject.prevPage(\''+code+'\')"></li>';
			msgplat += '<li class="fenye_05"><span id="npage">1</span>/<span id="spage">'+pageSize+'</span></li>';
			msgplat += '<li title="下一页" class="fenye_03" onclick="item_parentbject.nextPage(\''+code+'\')"></li>';
			msgplat += '<li title="最后一页" class="fenye_04" onclick="item_parentbject.finalPage(\''+code+'\')"></li>';
			msgplat += '</ul>';
			msgplat += '</div>';
			msgplat += '</div>';
			msgplat = msg+msgplat;
        }else {
			var msg = "";
			var code = encodeURI(platkeys,"utf-8");
			msgplat += '<div id="cityhead" style="overflow-x:scroll;" >';
			msgplat += '<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:4px;color:#999999;font-size:12px;line-height:22px;" id="data_tab">';
			var pageSize  = 0;
			var totalSize = 0;
			$.ajax({
				url:"/js/plugins/dictionary/action/getData.jsp",
				data:{"zdmc":this.item_zdmc,"code":code},
				type:"post",
				async:false,
				dataType:"json",
				success: function(datas){
					if(datas.res!='success'){
						msg = '<div class="ds_input_tips ds_input_tips_no">对不起，找不到字典:'+zdmc+'！</div>';
						pageSize = 0;
					}else{
						pageSize = Math.ceil(datas.Total/10);
						totalSize = datas.Total;
						if(datas.data.length<1){
							msg = '<div class="ds_input_tips ds_input_tips_no">对不起，找不到：'+platkeys+'</div>';
						}
						allplat = datas.data;
						item_parentbject.item_setArr_Citys(allplat);
						$(datas.data).each(function(index, element) {
							var index_ = index+1;
							var chinese = element.codecn;
							var code = element.code;
							var szm = element.szm;
							var all_py = element.all_py;						
							msgplat += '<tr class="tr_poi" onclick="item_parentbject.item_add_input_text(\'' 
									+ chinese + '\',\'' + code + '\')"><td class="numg_01">'
									+ index_ +'</td><td class="numg_02">' + code + '</td><td class="trksd_03">' + chinese + '</td></tr>';
						});
					}
				}
			});
			msgplat += '</table>';
			msgplat += '<div class="fenye_div">';
			msgplat += '<input type="hidden" value="'+totalSize+'" id="totalSize" name="totalSize" />';
			msgplat += '<ul class="fenye_ul">';
			msgplat += '<li title="第一页" class="fenye_01" onclick="item_parentbject.firstPage(\''+code+'\')"></li>';
			msgplat += '<li title="上一页" class="fenye_02" onclick="item_parentbject.prevPage(\''+code+'\')"></li>';
			msgplat += '<li class="fenye_05"><span id="npage">1</span>/<span id="spage">'+pageSize+'</span></li>';
			msgplat += '<li title="下一页" class="fenye_03" onclick="item_parentbject.nextPage(\''+code+'\')"></li>';
			msgplat += '<li title="最后一页" class="fenye_04" onclick="item_parentbject.finalPage(\''+code+'\')"></li>';
			msgplat += '</ul>';
			msgplat += '</div>';
			msgplat += '</div>';
			msgplat = msg+msgplat;
		}
		document.getElementById("top_getplatform").innerHTML =  '<div class="suggest-container">'+msgplat+'</div>';//城市结果列表呈现
		document.getElementById("top_getiframe").style.width = document.getElementById("top_getplatform").clientWidth+2;
        document.getElementById("top_getiframe").style.height = document.getElementById("top_getplatform").clientHeight+2;
	}
	
	/***************************************************fix_div_coordinate*********************************************/
	//函数功能：控制提示div的位置，使之刚好出现在文本输入框的下面
	this.item_fix_div_coordinate = function(){
		var leftpos=0;
		var toppos=0;
		var aTag = this.item_object;
		do {
			aTag = aTag.offsetParent;
			leftpos	+= aTag.offsetLeft;
			toppos += aTag.offsetTop;
		}while(aTag.tagName!="BODY"&&aTag.tagName!="HTML");
		document.getElementById("top_getiframe").style.width = '372px';
		if(document.layers){
			document.getElementById("top_getiframe").style.left = this.item_object.offsetLeft	+ leftpos + "px";
			document.getElementById("top_getiframe").style.top = this.item_object.offsetTop +	toppos + this.item_object.offsetHeight + 2 + "px";
		}else{
			document.getElementById("top_getiframe").style.left =this.item_object.offsetLeft-1	+ leftpos  +"px";
			document.getElementById("top_getiframe").style.top = this.item_object.offsetTop +4+	toppos + this.item_object.offsetHeight + 'px';
		}
		if(document.layers){
			document.getElementById("top_getplatform").style.left = this.item_object.offsetLeft	+ leftpos + "px";
			document.getElementById("top_getplatform").style.top = this.item_object.offsetTop +	toppos + this.item_object.offsetHeight + 2 + "px";
		}else{
			document.getElementById("top_getplatform").style.left =this.item_object.offsetLeft-1	+ leftpos  +"px";
			document.getElementById("top_getplatform").style.top = this.item_object.offsetTop +4+	toppos + this.item_object.offsetHeight + 'px';
		}
	}
    /***************************************************hidden_suggest*********************************************/
	//函数功能：隐藏提示框
	this.item_hidden_suggest = function (){
		if (event.target) targ = event.target;  else if (event.srcElement) targ = event.srcElement;
		if(targ.tagName!='LI' && targ.tagName!='UL'){
			var choose = false;
			var val = item_parentbject.item_object.value;
			$(item_parentbject.item_citys).each(function(index, element) {
				if(element.codecn==val){
					choose = true;
					return false;
				}
                
            });
			
			if(!choose){
				document.getElementById(item_parentbject.item_object.id).value="";
			}
		    document.getElementById("top_getiframe").style.visibility = "hidden";
		    document.getElementById("top_getplatform").style.visibility = "hidden";
		}
		//this.item_lastkeys_val = 0;
		//当this.item_lastkeys_val有值时,不清零
		/*if(this.item_lastkeys_val == null || this.item_lastkeys_val < 0){
		    this.item_lastkeys_val = 0;
		}
		document.getElementById("top_getiframe").style.visibility = "hidden";
		document.getElementById("top_getplatform").style.visibility = "hidden";*/
	}

    this.item_onblur=function(object){
		var nodes = document.getElementById("top_getplatform").getElementsByTagName("li");
		if(nodes!=null && typeof(nodes)!='undefined'){
		    for(var i=0;i<nodes.length;i++){
			    if(nodes[i].className == "ds_selected"){
			        if(nodes[i].childNodes.length>1){
			            if(object)
			            {
			                object.value=nodes[i].childNodes[1].innerHTML;
			            }
				    }
			    }
		    }
		}
		else{
		    object.value='';
		}
    }
	/***************************************************show_suggest*********************************************/
	//函数功能：显示提示框
	this.item_show_suggest = function (){
		document.getElementById("top_getiframe").style.visibility = "visible";
		document.getElementById("top_getplatform").style.visibility = "visible";
	}

	this.is_showsuggest= function (){
		if(document.getElementById("top_getplatform").style.visibility == "visible") return true;else return false;
	}

	this.sleep = function(n){
		var start=new Date().getTime(); //for opera only
		while(true) if(new Date().getTime()-start>n) break;
	}

	this.ltrim = function (strtext){
		return strtext.replace(/[\$&\|\^*%#@! ]+/, '');
	}
	
	/***************************************************fix_div_coordinate*********************************************/
	//函数功能：翻页，下一页
	this.nextPage=function(code){
		var spage = parseInt(document.getElementById("spage").innerHTML);
		var npage = parseInt(document.getElementById("npage").innerHTML);
		var totalSize = document.getElementById("totalSize").value;
		var _napge = npage+1;
		if(_napge<=spage){
			var msg = "";
			$.ajax({
				url:"/js/plugins/dictionary/action/getData.jsp",
				data:{"zdmc":this.item_zdmc,"code":code,"totalSize":totalSize,"page":_napge},
				type:"post",
				async:false,
				dataType:"json",
				success: function(datas){
					item_parentbject.item_setArr_Citys(datas.data);
					$(datas.data).each(function(index, element) {
						var index_ = npage*10+index+1;
						var chinese = element.codecn;
						var code = element.code;
						var szm = element.szm;
						var all_py = element.all_py;						
                        msg += '<tr class="tr_poi" onclick="item_parentbject.item_add_input_text(\'' + chinese + '\',\'' + code + '\')"><td class="numg_01">'
								+ index_ +'</td><td class="numg_02">' + code + '</td><td class="trksd_03">' + chinese + '</td></tr>';
                    });
				}
			});
			$("#data_tab").html(msg);
			$("#npage").html(_napge);
			//document.getElementById("data_tab").innerHTML = msg;
			//document.getElementById("npage").innerHTML = _napge;
		}
	}
	//函数功能：翻页，上一页
	this.prevPage=function(code){
		var spage = parseInt(document.getElementById("spage").innerHTML);
		var npage = parseInt(document.getElementById("npage").innerHTML);
		var totalSize = document.getElementById("totalSize").value;
		var _napge = npage-1;
		if(_napge>0){
			var msg = "";
			$.ajax({
				url:"/js/plugins/dictionary/action/getData.jsp",
				data:{"zdmc":this.item_zdmc,"code":code,"totalSize":totalSize,"page":_napge},
				type:"post",
				async:false,
				dataType:"json",
				success: function(datas){
					item_parentbject.item_setArr_Citys(datas.data);
					$(datas.data).each(function(index, element) {
						var index_ = (_napge-1)*10+index+1;
						var chinese = element.codecn;
						var code = element.code;
						var szm = element.szm;
						var all_py = element.all_py;						
                        msg += '<tr class="tr_poi" onclick="item_parentbject.item_add_input_text(\'' + chinese + '\',\'' + code + '\')"><td class="numg_01">'
								+ index_ +'</td><td class="numg_02">' + code + '</td><td class="trksd_03">' + chinese + '</td></tr>';
                    });
				}
			});
			$("#data_tab").html(msg);
			$("#npage").html(_napge);
			
		}
	}
	//函数功能：翻页，最后一页
	this.finalPage=function(code){
		var spage = parseInt(document.getElementById("spage").innerHTML);
		var totalSize = document.getElementById("totalSize").value;
		var _napge = spage-1;
		if(_napge<=spage){
			var msg = "";
			$.ajax({
				url:"/js/plugins/dictionary/action/getData.jsp",
				data:{"zdmc":this.item_zdmc,"code":code,"totalSize":totalSize,"page":spage},
				type:"post",
				async:false,
				dataType:"json",
				success: function(datas){
					item_parentbject.item_setArr_Citys(datas.data);
					$(datas.data).each(function(index, element) {
						var index_ = (_napge)*10+index+1;
						var chinese = element.codecn;
						var code = element.code;
						var szm = element.szm;
						var all_py = element.all_py;						
                        msg += '<tr class="tr_poi" onclick="item_parentbject.item_add_input_text(\'' + chinese + '\',\'' + code + '\')"><td class="numg_01">'
								+ index_ +'</td><td class="numg_02">' + code + '</td><td class="trksd_03">' + chinese + '</td></tr>';
                    });
				}
			});
			$("#data_tab").html(msg);
			$("#npage").html(_napge);
			//document.getElementById("data_tab").innerHTML = msg;
			//document.getElementById("npage").innerHTML = spage;
		}
	}
	//函数功能：翻页，第一页
	this.firstPage=function(code){
		var totalSize = document.getElementById("totalSize").value;
		var _napge = 1;
		if(_napge>0){
			var msg = "";
			$.ajax({
				url:"/js/plugins/dictionary/action/getData.jsp",
				data:{"zdmc":this.item_zdmc,"code":code,"totalSize":totalSize,"page":_napge},
				type:"post",
				async:false,
				dataType:"json",
				success: function(datas){
					item_parentbject.item_setArr_Citys(datas.data);
					$(datas.data).each(function(index, element) {
						var index_ = (_napge-1)*10+index+1;
						var chinese = element.codecn;
						var code = element.code;
						var szm = element.szm;
						var all_py = element.all_py;						
                        msg += '<tr class="tr_poi" onclick="item_parentbject.item_add_input_text(\'' + chinese + '\',\'' + code + '\')"><td class="numg_01">'
								+ index_ +'</td><td class="numg_02">' + code + '</td><td class="trksd_03">' + chinese + '</td></tr>';
                    });
				}
			});
			$("#data_tab").html(msg);
			$("#npage").html(_napge);
			//document.getElementById("data_tab").innerHTML = msg;
			//document.getElementById("npage").innerHTML = _napge;
		}
	}
    /***************************************************add_input_text*********************************************/
	//函数功能：当用户选中时填充相应的城市名字
	this.item_add_input_text = function (keys,szm){
		keys=this.ltrim(keys)
		this.item_object.value = keys;
		var id=this.item_object.id;
		var id2 = this.item_id2;
		
		if(document.getElementById(this.item_id2)!=null){
			document.getElementById(this.item_id2).value = szm;
		}
		//document.getElementById(id).style.color="#000000";
		document.getElementById(id).value=keys;
		
     }

	/***************************************************keys_handleup*********************************************/
	//函数功能：用于处理当用户用向上的方向键选择内容时的事件
	this.item_keys_handleup = function (){
		if(this.item_lastkeys_val > 0) this.item_lastkeys_val--;
		var nodes = document.getElementById("top_getplatform").getElementsByTagName("tr");
		if(this.item_lastkeys_val < 0) this.item_lastkeys_val = nodes.length-1;
		var b = 0;
		for(var i=0;i<nodes.length;i++){
			if(b == this.item_lastkeys_val){
				nodes[i].className = "ds_selected";
				/*if(nodes[i].childNodes.length>2){
				    this.item_add_input_text(nodes[i].childNodes[2].innerHTML);
				}*/
			}else{
				nodes[i].className = "tr_poi";
			}
			b++;
		}
	}

	/***************************************************keys_handledown*********************************************/
	//函数功能：用于处理当用户用向下的方向键选择内容时的事件
	this.item_keys_handledown = function (){
		this.item_lastkeys_val++;
		
		var nodes = document.getElementById("top_getplatform").getElementsByTagName("tr");
		if(this.item_lastkeys_val >= nodes.length) {
			this.item_lastkeys_val--;
			return;
		}
		var b = 0;
		for(var i=0;i<nodes.length;i++){
			if(b == this.item_lastkeys_val){
				nodes[i].className = "ds_selected";
				
				/*if(nodes[i].childNodes.length>2){
				    this.item_add_input_text(nodes[i].childNodes[2].innerHTML);
				}*/
			}else{
				nodes[i].className = "tr_poi";
			}
			b++;
		}
	}

	this.item_ajaxac_getkeycode = function (e){
		var code;
		if (!e) var e = window.event;
		if (e.keyCode) code = e.keyCode;
		else if (e.which) code = e.which;
		return code;
	}

	/***************************************************keys_enter*********************************************/
	//函数功能：用于处理当用户回车键选择内容时的事件
	this.item_keys_enter = function (){
		var nodes = document.getElementById("top_getplatform").getElementsByTagName("li");
		for(var i=0;i<nodes.length;i++){
			if(nodes[i].className == "ds_selected"){
			    if(nodes[i].childNodes.length>1){
				    this.item_add_input_text(nodes[i].childNodes[2].innerHTML);
				}
			}
		}
		this.item_hidden_suggest();
	}

    /***************************************************display*********************************************/
	//函数功能：入口函数，将提示层div显示出来
	//输入参数：object 当前输入所在的对象，如文本框
	//输入参数：e IE事件对象
	this.item_display = function (object,id2,e,zdmc){
	    //this.item_setArr_Citys(citys);
	    /*第二次触发城市控件，默认全选。*/
		//if(document.getElementById("top_getplatform")) this.item_agian = true;
		var content = object.value;
		global_id=object.id;
		var flag = this.item_getPinYinByCity(content)
		if(flag!="") 
		{
		    object.focus();
		    object.select();
		}
		this.item_id2 = id2;
		this.show_zdmc = zdmc;
		this.item_zdmc = encodeURI(zdmc,"utf-8");;
		if(!document.getElementById("top_getplatform")) this.item_init_zhaobussuggest();
		if (!e) e = window.event;
		e.stopPropagation;
		e.cancelBubble = true;
		if (e.target) targ = e.target;  else if (e.srcElement) targ = e.srcElement;
		if (targ.nodeType == 3)  targ = targ.parentNode;

		var inputkeys = this.item_ajaxac_getkeycode(e);
		
		switch(inputkeys){
			/*case 38: //向上方向键
				this.item_keys_handleup(object.id);
			    return;break;
			case 40: //向下方向键
				if(this.is_showsuggest()) this.item_keys_handledown(object.id); else this.item_show_suggest();
			    return;break;*/
			case 39: //向右方向键
				return;break;
			case 37: //向左方向键
				return;break;
			case 13: //对应回车键
			    this.item_keys_enter();
			    return;break;
			case 18: //对应Alt键
				this.item_hidden_suggest();
			    return;break;
			case 27: //对应Esc键
				this.item_hidden_suggest();
			    return;break;
		}

		this.item_object = object;
		if(window.opera) this.sleep(100);//延迟0.1秒
		item_parentbject = this;
		if(this.item_taskid) window.clearTimeout(this.item_taskid);
        this.item_taskid=setTimeout("item_parentbject.item_localtext();" , this.item_delaySec)
	}

	//函数功能：从本地js数组中获取要填充到提示层div中的文本内容
	this.item_localtext = function(){
		var id=this.item_object.id;
        var suggestions="";
        suggestions=this.item_getSuggestionByName();
        if(suggestions==""){
	        item_parentbject.item_show_suggest();
	        item_parentbject.item_fill_div();
	        item_parentbject.item_fix_div_coordinate();	
        }
        else{
	        suggestions=suggestions.substring(0,suggestions.length-1);
	        item_parentbject.item_show_suggest();
	        item_parentbject.item_fill_div();
	        item_parentbject.item_fix_div_coordinate();	
	    }
	}

	/***************************************************getSuggestionByName*********************************************/
	//函数功能：从本地js数组中获取要填充到提示层div中的城市名字
	this.item_getSuggestionByName = function(){
		platkeys = this.item_object.value;
		var str="";
		return str;
        /*platkeys=this.ltrim(platkeys);
		if(!platkeys){
			return str;
        }
		else{
		   platkeys=platkeys.toUpperCase();
			for(i=0;i<this.item_citys.length;i++){
			    if((this.item_citys[i][0].toUpperCase().indexOf(platkeys)!=-1)||
				   this.item_getLeftStr(this.item_citys[i][1],platkeys.length).toUpperCase()==platkeys||
				   this.item_getLeftStr(this.item_citys[i][2],platkeys.length).toUpperCase()==platkeys)
					str+=this.item_citys[i][1]+","+this.item_citys[i][0]+","+this.item_citys[i][2]+";";
			}
			return str;
		}*/
	}

	/***************************************************getLeftStr************* *************************************/
    //函数功能：得到左边的字符串
    this.item_getLeftStr = function(str,len){

        if(isNaN(len)||len==null){
            len = str.length;
        }
        else{
            if(parseInt(len)<0||parseInt(len)>str.length){
                len = str.length;
             }
        }
        return str.substr(0,len);
    }

	/***************************************************parentIndexOf************* *************************************/
    //函数功能：得到子结点在父结点的位置
	function item_parentIndexOf(node){
	  for (var i=0; i<node.parentNode.childNodes.length; i++){
			if(node==node.parentNode.childNodes[i]){return i;}
	  }
   }
   
}
//首先自动加载城市
var item_suggest = new item_city_suggest();
//item_suggest.item_citys = flightcitys;