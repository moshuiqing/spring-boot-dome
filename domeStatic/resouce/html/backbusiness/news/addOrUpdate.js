//百度编辑器初始化
var editor = UE.getEditor('detailWord', {
	initialFrameWidth : '99.9%',
	initialFrameHeight : 600,
	scaleEnabled : true
});

//layui初始化
var layer;
var form;
layui.use(['element','form','layer','table','laytpl','tree'],function(){
    var $ = layui.jquery,
    	laytpl = layui.laytpl,
    	element=layui.element,
    	table = layui.table;
    	layer = layui.layer;
    	form = layui.form;
    	
    	form.on("submit(submit)",function(d){
    		var param = d.field;
    		console.log(param);
    		
    		if(param.id==""){
    			//新增
    			$.post(webname+"/backsystem/business/news/addNews",param,function(d){
    				if(qjty){
    					layer.msg(d.msg);  		
    					setTimeout(() => {
    						if(d.code>0){
        						window.location.href = webname + "/backsystem/business/news/toNews";
        					}
						}, 1000);
    								
    					
    				}
    			})
   			
    		}else{
    			//修改
    			$.post(webname+"/backsystem/business/news/updateNews",param,function(d){
    				if(qjty){   					
    					layer.msg(d.msg);  	
    					setTimeout(() => {
    						if(d.code>0){
        						window.location.href = webname + "/backsystem/business/news/toNews";
        					}
						}, 1000);
    					
    				}
    			})
    			
    			
    		}
    		
    		
    		return  false;
    	})
    	
});

//退出
function back() {
	layer.confirm("确定要退出吗？", {
		icon : 3
	}, function() {

		window.location.href = webname + "/backsystem/business/news/toNews";
	})
}
