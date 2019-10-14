
var index;
var ejindex;
var layer;
//查询菜单
layui.use(['form','tree','layer'],function(){
	var form = layui.form;
	layer = layui.layer;
	$.post(webname+"/backsystem/system/getTrees",function(d){
		
		if(d!=null){
			layui.tree({
				  elem: '#xtree3' //传入元素选择器
				  ,nodes: d  //参数
				  ,click: function(node){
					 
					    console.log(node) //node即为当前点击的节点数据
					    $("#menu").removeClass("hiden");
					    $("#title").val(node.name);
					    $("#icon").val(node.icon);
					    $("#href").val(node.hreftext);
					    $("#sort").val(node.sort);
					    $("#id").val(node.id);
					    $("#jibie").val(node.jibie);
					    $(".menu1").val(node.menu1);
					    if(node.jibie==1){
					    	$("#erji").removeClass("hiden");
					    	$("#sanji").addClass("hiden");
					    	$("#dz").addClass("hiden");
					    	
					    }
					    if(node.jibie==2){
					    	$("#sanji").removeClass("hiden");
					    	$("#erji").addClass("hiden");
					    	$("#dz").removeClass("hiden");
					    }
					    if(node.jibie==3){
					    	$("#sanji").addClass("hiden");
					    	$("#erji").addClass("hiden");
					    	$("#dz").removeClass("hiden");
					    }
					 } 
				});
		}
		
		
	})
	
	
	//新增一级菜单提交
	form.on('submit(submit2)', function(data){		
		  //console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
		  //console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
		  //console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}
		  var param=data.field;
		  $.post(webname+"/backsystem/system/addBigMenu",param,function(d){
			  if(qjty(d)){
				  if(d.code>0){
					  layer.msg(d.msg)
					  setTimeout(function() {
						  layer.close(index); 
						  window.location.reload();
						}, 1000);
				  }else{
					  layer.msg(d.msg)
				  }
			  }
			  
		  });

		  return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
		});
	
	//新增二级和三级菜单提交
	form.on('submit(submit3)', function(data){		
		  //console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
		  //console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
		  //console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}
		  var param=data.field;
		  
		  
		  $.post(webname+"/backsystem/system/addMenu",param,function(d){
			  if(qjty(d)){ 
				 if(d.code>0){
					  layer.msg(d.msg)
					  setTimeout(function() {
						  layer.close(index); 
						  window.location.reload();
						}, 1000);
				  }else{
					  layer.msg(d.msg)
				  }
			  
			  }
		  });
		  
		  return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
		});
	//编辑菜单菜单提交
	form.on('submit(submit1)', function(data){		
		  //console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
		  //console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
		  //console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}
		  var param=data.field;
		  
		  var jibie = $("#jibie").val();
		  
		  if(jibie==1){
			  //修改一级菜单
			  $.post(webname+"/backsystem/system/updateBigMenu",param,function(d){
				  if(qjty(d)){
					   if(d.code>0){
						  layer.msg(d.msg);
						  setTimeout(function() {
								 window.location.reload();
								}, 1000);
						 
					  }else{
						  layer.msg(d.msg);
					  }
				  
				  }
			  });
		  }else{
			  //修改二级菜单
			  
				  $.post(webname+"/backsystem/system/updateMenu",param,function(d){
					  if(qjty(d)){
						  if(d.code>0){
							  layer.msg(d.msg);
							  setTimeout(function() {
									 window.location.reload();
									}, 1000);
							 
						  }else{
							  layer.msg(d.msg);
						  }
					  }
				  });
			
			  
		  }
		  
		 
		  
		  return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
		});

	
	
	

});

$(function(){
	$("#yiji").click(function(){
		open();
	})
	
	$("#shanchu").click(function(){
		layui.layer.confirm('确定要删除吗？', {icon: 3, title:'提示'}, function(index){
			  //do something
		var jibie = $("#jibie").val();
		var id = $("#id").val();
		//alert(jibie);
		var param={
			jibie:jibie,
			id:id
		};
		
		$.post(webname+"/backsystem/system/deleteMenu",param,function(d){
			if(qjty(d)){
				 layer.msg(d.msg)
				setTimeout(function() {
					 window.location.reload();
					}, 1000);
			}
			
		});
		});
		
	})
	
	//编辑
	$("#bianji").click(function(){
		$(".dis").removeAttr("disabled"); 
		$("#title").focus();
		$(".sub1").removeClass("hiden")
	})
	//取消编辑
	$("#quxiao1").click(function(){
		$(".dis").attr("disabled","disabled");
		$(".sub1").addClass("hiden");
	})
	//打开二级菜单新增
	$("#erji").click(function(){
		$("#parent").val("");
		ejopen();
	})
	//打开三级菜单新增
	$("#sanji").click(function(){
		$("#parent").val($("#id").val());
		ejopen();
	})
	

	
})

function open(){
	index = layer.open({
		title : '添加一级菜单', //标题
		type : 1,
		area : [ '600px', '353px' ],
		offset : 'auto',
		shadeClose : false, //点击遮罩关闭
		content : $('#tc'),
		skin : 'show'
	});
}
function ejopen(){
	ejindex = layer.open({
		title : '添加二级菜单', //标题
		type : 1,
		area : [ '600px', '403px' ],
		offset : 'auto',
		shadeClose : false, //点击遮罩关闭
		content : $('#ej'),
		skin : 'show'
	});
}


