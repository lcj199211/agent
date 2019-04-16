$(document).ready(function(){
	jQuery.ajaxSetup({
		beforeSend:function(e){
			var _that=this;
			var _url=_that.url;
			if(_url.indexOf("springraintoken")!=-1)return;
			if(_url.indexOf("?")!=-1){
				_that.url= _url+"&springraintoken="+springraintoken;
			}else{
				_that.url= _url+"?springraintoken="+springraintoken;
			}
		},
		complete:function(data){
			try{
				var _obj=data.responseText;
				_obj=eval("("+_obj+")");
				if(_obj.statusCode=='relogin'){
					this.success=null;
					springrain.info("登录超时，请重新登录.", null);
					setTimeout(function(){
						window.location.href=ctx+"/system/login";
					},1000);
				}
			}catch(e){
				springrain.info(e, null);
			}
		}
	});
	//初始化插件
	configLayui("global");
	//加载菜单
	loadMenu();
	init_sort_btn();
	init_button_action();
	//赋予 元素特殊事件 ，和表单的样式处理。如TAB和菜单 的滑过事件 ,不添加没有动画效果，且必须加到ready后
	setTimeout(function(){
		if(jQuery("form").length>0){
			layui.use('form', function(){
				  form = layui.form();
			});
		}
		layui.use(['element'], function(){
			  var element = layui.element();   
		});
	},200);
});
var form;
/*添加form的监听回调*/
function selectListener(filterId,callback){
	console.log(123);
	form.on('select('+filterId+')', function(data){
		callback(data);
	});
}
function loadMenu(){
	
	//加载菜单
    if(!(!!locache.get("menuData"))){//没有数据
    	ajaxmenu();
    }else{
    	var menuData = locache.get("menuData");
    	buildModule(menuData);
    }
}


function exit(){

		springrain.confirm("确定退出？", function(){
			try{
				locache.flush();
			}catch(e){}
			
			var _url=ctx+"/system/logout";
			    springrain.goTo(_url);
		});
}

function configLayui(par){
	layui.config({
		  base: ctx+"/layui/lay/modules/"
		}).use(par);
}



/**
 * 获取所有导航资源
 */
function ajaxmenu() {
    jQuery.ajax({
        url : ctx + "/system/menu/leftMenu",
        type : "post",
        data:{"springraintoken":springraintoken},
        cache : false,
        async : false,
        scriptCharset : "utf-8",
        dataType : "json",
        success : function(_json) {
            if(_json.status=="success"){
            	locache.set("menuData",_json.data);
            	var menuData = locache.get("menuData");
                buildModule(menuData);
            }
			
        }
    });
}


function buildModule(data) {
    if (data != null && typeof (data) != "undefined") {
        var htmlStr = '';
        /*处理/update时丢了菜单 状态*/
        var _url=window.location.pathname;
        if(_url.indexOf('/update/pre')!=-1){
        	_url=_url.substring(0,_url.indexOf("/update/pre"))+"/list";
        } 
        /*处理/update时丢了菜单 状态*/
        var childrenMenuList = null;
        var aside_menu = ""; //侧栏导航
        var a= 0;
        var ico = ["fa-home","fa-user","fa-calculator","fa-gamepad","fa-laptop","fa-edit","fa-sitemap","fa-gears","fa-circle-thin"];
            /*********************导航栏目修改************************/
            for(i in data){
            	//取出一级导航名称
            	aside_menu += '<li><a  href="javascript:void(0);"><i class="fa '+ico[i]+'"></i> '+data[i].name+' <span class="fa fa-chevron-down"></span></a>';
            	console.log(a+","+data[i].name);
				aside_menu += '<ul class="nav child_menu">';
				//判断是否有二级栏目
            	if(data[i].leaf != ""){
            		//对存在的二级栏目列表进行显示
	            	for(j in data[i].leaf){
//	            		console.log(j+","+data[i].leaf[j].name);
						//判断二级栏目项是否有链接地址
						if(data[i].leaf[j].leaf != ""){
							aside_menu += '<li id="'+data[i].leaf[j].id+'"><a data-pid="'+data[i].leaf[j].pid+'" href="javascript:void(0);">'+data[i].leaf[j].name+'</a>';
							aside_menu += '<ul class="nav child_menu">';
							//对存在的三级栏目进行输出
							for(k in data[i].leaf[j].leaf){
								if(data[i].leaf[j].leaf[k].pageurl){
									aside_menu += '<li id="'+data[i].leaf[j].leaf[k].id+'"><a data-pid="'+data[i].leaf[j].leaf[k].pid+'" data-action="'+ctx+data[i].leaf[j].leaf[k].pageurl+'" href="javascript:void(0);" >"'+data[i].leaf[j].leaf[k].name+'"</a></li>';
								}else{
									console.log('跳转404页面！');
								}
							}
							aside_menu += '</ul></li>';
						}else {
							aside_menu += '<li id="'+data[i].leaf[j].id+'"><a data-pid="'+data[i].leaf[j].pid+'" data-action="'+ctx+data[i].leaf[j].pageurl+'" href="javascript:void(0);">'+data[i].leaf[j].name+'</a></li>';
//							console.log(ctx+data[i].leaf[j].pageurl);
						}
	            	}
	            	aside_menu += '</ul></li>';
            	}else{
            		console.log("没有数据");
            	}
            }
            $('#aside-menu').append(aside_menu); //侧栏放入到 id= aside-menu 的元素中
            /*********************************************/
    }
}

//导航下滑
//var menu_id =  $('#aside-menu');
//menu_id.on("click","a",function(){
//	alert("1");
//})


function getChindModule(_leaf,parentMenu) {
    var t = '<dl class="layui-nav-child">';
    for ( var menuObj in _leaf) {
        if((ctx+_leaf[menuObj].pageurl)==window.location.pathname){
        	 t = t+'<dd class="layui-this" pageUrl="'+_leaf[menuObj].pageurl+'" id="'+_leaf[menuObj].id+'"><a href="javascript:void(0);" data-action="'+ctx+_leaf[menuObj].pageurl+'"><i class="layui-icon">'+_leaf[menuObj].menuIcon+'</i><cite>'+_leaf[menuObj].name+'</cite></a></dd>';
        	 $("#neckNavi").append('<a><cite>'+parentMenu.name+'</cite></a><span class="layui-box">&gt;</span>');
        	 $("#neckNavi").append('<a><cite>'+_leaf[menuObj].name+'</cite></a>');
        }else{
        	t = t+'<dd pageUrl="'+_leaf[menuObj].pageurl+'" id="'+_leaf[menuObj].id+'"><a href="javascript:void(0);" data-action="'+ctx+_leaf[menuObj].pageurl+'"><i class="layui-icon">'+_leaf[menuObj].menuIcon+'</i><cite>'+_leaf[menuObj].name+'</cite></a></dd>';
        }
    }
    t = t+'</dl>';
    return t;
}


/**
 * 获取url中的hash值，例如http://www.baidu.com#keyword,返回['keyword']
 * @returns 数组
 */
function getLocationHashArr(){
    var urlHash = window.location.hash;
    if(!!urlHash){
        var urlHashArr = urlHash.split("#");
        urlHashArr.splice(0,1);
        return urlHashArr;
    }
    return new Array();
}
function gentimestampstr(){
	return new Date().getTime();
}
function getcurrentMenuId(){
	var currentPageUrl=window.location.href;
	var urlElementArr=currentPageUrl.split("?");
	var menuId='';
	if(urlElementArr.length>1){//非首页
		var paramElementArr=urlElementArr[1].split("#");
		menuId= paramElementArr[0].split("=")[1];
		menuId=menuId.replace("&t","");
	}
	return menuId;
}
 

/**
 * 批量删除
 * @param _url
 * @param formId
 * @param listage
 * @returns {Boolean}
 */
function mydeletemore(_url, formId,listage) {
	var arr = new Array();
	var checks = jQuery(":checkbox[name='check_li']");
	checks.each(function(i, _obj) {
		if (_obj.checked) {
			arr.push(_obj.value);
		}

	});
	if (arr.length < 1) {
		myalert("请选择要删除的记录!");
		return false;
	}

	myconfirm("确定删除选中数据?", function() {
		myhref2page(_url,listage,"records=" + arr.join(","));
	});
}




function myexport(formId, _url) {
	var _form = document.getElementById(formId);
	var _action = _form.action;
	_form.action = _url;
	_form.submit();
	_form.action = _action;
}
/* 赋值 */
function set_val(name, val) {
	if ($("#" + name + " option").length > 0) {
		//按老的UI不动是这个
		$("#" + name).val(val);
		//按新的layerui只能，模拟点击
//		jQuery("#"+name).siblings("div").filter(".layui-form-select").eq(0).find("dd[lay-value='"+val+"']").trigger("click");
		return;
	}

	if (($("#" + name).attr("type")) === "checkbox") {
		if (val == 1) {
			$("#" + name).attr("checked", true);
			return;
		}
	}
	if ($("." + name).length > 0) {
		if (($("." + name).first().attr("type")) === "checkbox") {
			var arr_val = val.split(",");
			for ( var s in arr_val) {
				$("input." + name + "[value=" + arr_val[s] + "]").attr(
						"checked", true);
			}
		}
	}
	if (($("#" + name).attr("type")) === "text") {
		if(typeof val==="number"&&val.length>11){
			try{
				val=getSmpFormatDateByLong(val);
				$("#" + name).val(val);
				return;
			}catch(e){
				$("#" + name).val(val);
				return;
			}
		}
		$("#" + name).val(val);
		return;
	}
	if (($("#" + name).attr("type")) === "hidden") {
		$("#" + name).val(val);
		return;
	}
	if (($("#" + name).attr("rows")) > 0) {
		$("#" + name).val(val);
		return;
	}
}
function  init_sort_btn(){
	jQuery("th[id^='th_']").each(function(_i,_o){
		jQuery(_o).append('<i class="layui-icon sort-icon-up sort-icon">&#xe619;</i><i class="layui-icon sort-icon-down sort-icon">&#xe61a;</i>');
	});
	
	//加载颜色
	var _sort=jQuery("#page_sort").val();
	var _order=jQuery("#page_order").val();
	if(_order){
		if("asc"==_sort){
			jQuery("#th_"+_order).find(".sort-icon-up").css("color","#333333");
		}else{
			jQuery("#th_"+_order).find(".sort-icon-down").css("color","#333333");
		}
	}
	
	jQuery(".sort-icon").bind("mouseenter",function(){
		jQuery(this).addClass("sort-icon-on");
	}).bind("mouseout",function(){
		jQuery(this).removeClass("sort-icon-on");
	});
	//单击事件
	jQuery(".sort-icon").bind("click",function(){
		if(jQuery(this).hasClass("sort-icon-down")){
			jQuery("#page_sort").val("desc");
			jQuery("#page_order").val(jQuery(this).parent("th").attr("id").split("_")[1]);
//			jQuery("#page_order").val(jQuery(this).parent("th").attr("id").slice(jQuery("#page_order").val(jQuery(this).parent("th").attr("id").indexOf("_"));
			
		}else{
			jQuery("#page_sort").val("asc");
			jQuery("#page_order").val(jQuery(this).parent("th").attr("id").split("_")[1]);
//			jQuery("#page_order").val(jQuery(this).parent("th").attr("id").slice(jQuery("#page_order").val(jQuery(this).parent("th").attr("id").indexOf("_"));
		}
		//提交表单
		springrain.commonSubmit("searchForm");
		//$("#searchForm").submit();
	});
}
function init_button_action(){
	jQuery("button[data-action]").bind("click",function(){
		if(!!$(this).attr("data-pid"))
			locache.set("currentPagePid",$(this).attr("data-pid"));
		window.location.href=springrain.appendToken(jQuery(this).attr("data-action"));
	});
	jQuery("a[data-action]").bind("click",function(){
		if(!!$(this).attr("data-pid"))
			locache.set("currentPagePid",$(this).attr("data-pid"));
		window.location.href=springrain.appendToken(jQuery(this).attr("data-action"));
	});
}