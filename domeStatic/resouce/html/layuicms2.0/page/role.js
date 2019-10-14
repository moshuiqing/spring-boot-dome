var index;
var layer;
var tableIns ;
var form;
var ejindex;
var jeindex;
var xtree3;
layui.use(['element','form','layer','table','laytpl','tree'],function(){
    var $ = layui.jquery,
    	laytpl = layui.laytpl,
    	element=layui.element,
    	table = layui.table;
		//layer = parent.layer === undefined ? layui.layer : top.layer;
    	layer = layui.layer;
    	form = layui.form;
     	
    //角色列表
     tableIns = table.render({
        elem: '#role',
        url : webname+'/backsystem/system/pageFoundRole',
        cellMinWidth : 95,
        height : "full-125",
        page : true,
        limits : [10,15,20],
        limit : 10,
        cols : [[
            {type: "checkbox", fixed:"left", minWidth:50},
            {type:'numbers', minWidth:50,title:'序号'},
            {field: 'rname', title: '角色名',  minWidth:80, align:"center"},
            {field: 'remake', title: '描述',minWidth:350, align:'center'},
            {field: 'modelid', title: '权限',minWidth:100, align:'center',templet:function(d){
            	var h="";
            	var getTpl = $("#quxian").html();
             	laytpl(getTpl).render(d, function(html){
             		h=html;
             	});
            	return h;
            }},
            {field: 'menuid', title: '菜单',minWidth:100, align:'center',templet:function(d){
            	var h="";
            	var getTpl = $("#menu").html();
             	laytpl(getTpl).render(d, function(html){
             		h=html;
             	});            	
             	return h;
            }},
            {title: '操作', minWidth:250,align:"center",templet:function(d){
            	var h="";
            	var getTpl = $("#dome").html();
             	laytpl(getTpl).render(d, function(html){
             		h=html;
             	});
            	return h;
            }}
        ]]
    });

     ///提交监听
     form.on("submit(submit)",function(data){
    	var param= data.field;
    	console.log(param);
    	if(param.rname==param.copyname){
    		param.rname='';
    	}
    	$.post(webname+"/backsystem/system/updateRoleById",param,function(d){
    		if(qjty(d)){
	    		layer.close(jeindex);
	    		layer.msg(d.msg);
	    		tableIns.reload({});
    		}
    		
    	})
    	
    	
    	return false;
    	 
     })
     
     //查找监听
     form.on("submit(found)",function(data){
    	 
    	 var param = data.field;
    	 tableIns.reload({
             page: {
                 curr: 1 //重新从第 1 页开始
             },
             where: param
         })
    	 
    	 return false;
     })

});


//新增
function addRole(){
	form.val("addOrUp", {
		  "rid": "" 
		  ,"rname": ""
		  ,"copyname":""
		  ,"remake":""
		});
	
	jeindex = layer.open({
		title : '新增角色', //标题
		type : 1,
		area : [ '520px', '300px' ],
		offset : 'auto',
		shadeClose : false, //点击遮罩关闭
		content : $('#tc'),
		skin : 'show'
	});	
}



//查看权限
function lookRole(info){
	$("#tree").html("");

$.post(webname+"/backsystem/system/getModel",{info:info},function(d){
		
		if(d!=null){
			if(qjty(d)){
				layui.tree({
					  elem: '#tree' //传入元素选择器
					  ,nodes: d  //参数
					  ,skin:'yangshi'
					  ,click: function(node){
						  
					  }
					  
				});
			}
		}
});
	
	
	ejindex = layer.open({
		title : '查看权限', //标题
		type : 1,
		area : [ '300px', '500px' ],
		offset : 'auto',
		shadeClose : false, //点击遮罩关闭
		content : $('#ej'),
		skin : 'show'
	});
}

/////
function lookBmenu(info){
	
	$("#tree").html("");
//	$("#type").val("bigMenu");
$.post(webname+"/backsystem/system/getBigMenu",{info:info},function(d){
		
		if(d!=null){
			if(qjty(d)){
				layui.tree({
					  elem: '#tree' //传入元素选择器
					  ,nodes: d  //参数
					  ,skin:'yangshi'
					  ,click: function(node){
						  
					  }
					  
				});
			}
		}
});

	ejindex = layer.open({
		title : '查看横菜单', //标题
		type : 1,
		area : [ '300px', '500px' ],
		offset : 'auto',
		shadeClose : false, //点击遮罩关闭
		content : $('#ej'),
		skin : 'show'
	});
}
////////
function lookmenu(info,binfo){
	$("#tree").html("");
//	$("#type").val("menu");
$.post(webname+"/backsystem/system/getMenus",{info:info,binfo:binfo},function(d){
		
		if(d!=null){
			if(qjty(d)){
				layui.tree({
					  elem: '#tree' //传入元素选择器
					  ,nodes: d  //参数
					  ,skin:'yangshi'
					  ,click: function(node){
						  
					  }
					  
				});
			}
		}
});

	ejindex = layer.open({
		title : '查看竖菜单', //标题
		type : 1,
		area : [ '300px', '500px' ],
		offset : 'auto',
		shadeClose : false, //点击遮罩关闭
		content : $('#ej'),
		skin : 'show'
	});
}
//////////////////////////////////////////////////////////////////启用 禁用 删除/////////////////////////////////////////
function change(rid,isdisable){
	if(isdisable==0){
		isdisable=1;
	}else if(isdisable==1){
		isdisable=0
	}
	
	$.post(webname+"/backsystem/system/updateDisable",{rid:rid,isdisable:isdisable},function(d){
		if(qjty(d)){
			layer.msg(d.msg);
			tableIns.reload({});	
		}
	})
}

function del(rid){
	
	layer.confirm('确定要删除吗？', {icon: 3, title:'提示'}, function(index){
		$.post(webname+"/backsystem/system/deleteRole",{rid:rid},function(d){
			if(qjty(d)){
				layer.msg(d.msg);
				tableIns.reload({});	
			}
		})
	});
	
}





////////////////////////////////////////////////////////////////////////编辑/////////////////////////////////////////

function edtRole(rid,info){
	$("#xtree").html("");
	var href=webname+"/backsystem/system/updateFoundRole?rid="+rid+"&modelid="+info;
	openTree(href);
	
	jeindex = layer.open({
		title : '权限设置', //标题
		type : 1,
		btn: ['确定','取消'],
		area : [ '300px', '500px' ],
		offset : 'auto',
		shadeClose : false, //点击遮罩关闭
		content : $('#je'),
		skin : 'show'
		,yes: function(index, layero){
			//权限提交
			var oCks = xtree3.GetChecked(); //这是方法
			var ids="";
			for (var i = 0; i < oCks.length; i++) {
				//console.log(oCks[i].value);
				ids+=oCks[i].value+",";
			
			}
			$.post(webname+"/backsystem/system/updateRole",{rid:rid,modelid:ids},function(d){
				if(qjty(d)){
					layer.close(jeindex);
					layer.msg(d.msg);
					tableIns.reload({});
				}
			})
			
			
		}
	});
}

/*function edtBigmenu(rid,bigmenuid,menuid){
	$("#xtree").html("");
	var href=webname+"/system/updateFoundBigMenu?bigmenuid="+bigmenuid+"&menuid="+menuid;
	openTree(href);
	
	jeindex = layer.open({
		title : '横菜单设置', //标题
		type : 1,
		btn: ['确定','取消'],
		area : [ '300px', '500px' ],
		offset : 'auto',
		shadeClose : false, //点击遮罩关闭
		content : $('#je'),
		skin : 'show'
		,yes: function(index, layero){
			//权限提交
			var oCks = xtree3.GetChecked(); //这是方法
			var ids="";
			for (var i = 0; i < oCks.length; i++) {
				//console.log(oCks[i].value);
				ids+=oCks[i].value+",";
			}
			$.post(webname+"/system/updateRole",{rid:rid,bigmenuid:ids},function(d){
				layer.close(jeindex);
				layer.msg(d.msg);
				tableIns.reload({});
			})
			
			
		}
	});

}
*/
function edtMenu(rid,bigmenuid,menuid){
	$("#xtree").html("");
	var href=webname+"/backsystem/system/updateFoundMenu?bigmenuid="+bigmenuid+"&menuid="+menuid;
	openTree(href);
	
	jeindex = layer.open({
		title : '竖菜单设置', //标题
		type : 1,
		btn: ['确定','取消'],
		area : [ '300px', '500px' ],
		offset : 'auto',
		shadeClose : false, //点击遮罩关闭
		content : $('#je'),
		skin : 'show'
		,yes: function(index, layero){
			//权限提交
			var oCks = xtree3.GetChecked(); //这是方法
			var ids="";
			var bids=""
			for (var i = 0; i < oCks.length; i++) {
				//console.log(oCks[i].value);
				jibie= oCks[i].attributes.data.value;
				if(jibie==1){
					bids+=oCks[i].value+",";
				}else{
					ids+=oCks[i].value+",";
				}
				
				
				
			}
			$.post(webname+"/backsystem/system/updateRole",{rid:rid,menuid:ids,bigmenuid:bids},function(d){
				if(qjty(d)){
					layer.close(jeindex);
					layer.msg(d.msg);
					tableIns.reload({});
				}
			})
			
			
		}
	});	
}

function update(rid,rname,remake){
	
	form.val("addOrUp", {
		  "rid": rid 
		  ,"rname": rname
		  ,"copyname":rname
		  ,"remake":remake
		});
	
	jeindex = layer.open({
		title : '新增角色', //标题
		type : 1,
		area : [ '520px', '300px' ],
		offset : 'auto',
		shadeClose : false, //点击遮罩关闭
		content : $('#tc'),
		skin : 'show'
	});	
}










//3、最完整的参数用法
function openTree(href) {
	 xtree3 = new layuiXtree({
		elem : 'xtree' //必填三兄弟之老大
		,
		form : form //必填三兄弟之这才是真老大
		,
		data : href //必填三兄弟之这也算是老大
		,
		isopen : true //加载完毕后的展开状态，默认值：true
		,
		ckall : true //启用全选功能，默认值：false
		,
		ckallback : function() {
		} //全选框状态改变后执行的回调函数
		,
		icon : { //三种图标样式，更改几个都可以，用的是layui的图标
			open : "&#xe7a0;" //节点打开的图标
			,
			close : "&#xe622;" //节点关闭的图标
			,
			end : "&#xe621;" //末尾节点的图标
		},
		color : { //三种图标颜色，独立配色，更改几个都可以
			open : "#EE9A00" //节点图标打开的颜色
			,
			close : "#EEC591" //节点图标关闭的颜色
			,
			end : "#828282" //末级节点图标的颜色
		},
		click : function(data) { //节点选中状态改变事件监听，全选框有自己的监听事件
			console.log(data.elem); //得到checkbox原始DOM对象
			console.log(data.elem.checked); //开关是否开启，true或者false
			console.log(data.value); //开关value值，也可以通过data.elem.value得到
			console.log(data.othis); //得到美化后的DOM对象
			
		}
	});
}



