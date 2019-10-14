/* 生成顶部 菜单 */
$(function(){
	
	$.post(webname+"/backsystem/back/getTopMenu.action",function(data){
		
		var html="";
		for (var i = 0; i < data.length; i++) {
			
			if(i==0){
				html+="<li class='layui-nav-item layui-this' data-menu='"+data[i].menu1+"' pc=''><a href='javascript:;'>";
				if((data[i].icon).indexOf(';')>0){
					html+="<i class='layui-icon' data-icon='"+data[i].icon+"'>"+data[i].icon+"</i>";
				}else{
					html+="<i class='seraph "+data[i].icon+"' data-icon='"+data[i].icon+"'></i>";
				}
				html+="<cite>"+data[i].title+"</cite></a></li>";
			}else{
				html+="<li class='layui-nav-item' data-menu='"+data[i].menu1+"' pc=''><a href='javascript:;'>";
				if((data[i].icon).indexOf(';')>0){
					html+="<i class='layui-icon' data-icon='"+data[i].icon+"'>"+data[i].icon+"</i>";
				}else{
					html+="<i class='seraph "+data[i].icon+"' data-icon='"+data[i].icon+"'></i>";
				}
				html+="<cite>"+data[i].title+"</cite></a></li>";
			}
			
		}
		//alert(html);
		
		$(".topLevelMenus").html(html);
	})
	
	
	
})