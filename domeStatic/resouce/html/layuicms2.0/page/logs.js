layui.use(['table'],function(){
	var table = layui.table;

	//系统日志
    table.render({
        elem: '#logs',
        url : webname+'/backsystem/system/foundLogs',
        cellMinWidth : 95,
        page : true,
        height : "full-20",
        limit : 10,
        limits : [10,15,20,25],
        cols : [[
            {type: "checkbox", fixed:"left", minWidth:50},
            {type:'numbers',minWidth:250,title:'序号',align:'center'},
            {field: 'url', title: '请求地址', minWidth:400},
            {field: 'method', title: '操作方式',minWidth:150, align:'center',templet:function(d){
                if(d.method.toUpperCase() == "GET"){
                    return '<span class="layui-blue">'+d.method+'</span>'
                }else{
                    return '<span class="layui-red">'+d.method+'</span>'
                }
            }},
            {field: 'ip', title: '操作IP',minWidth:150,  align:'center',},
            {field: 'timeConsuming', title: '耗时',minWidth:100, align:'center',templet:function(d){
                return '<span class="layui-btn layui-btn-normal layui-btn-xs">'+d.timeConsuming+'</span>'
            }},
            {field: 'isAbnormal', title: '是否异常',minWidth:100, align:'center',templet:function(d){
                if(d.isAbnormal == "正常"){
                    return '<span class="layui-btn layui-btn-green layui-btn-xs">'+d.isAbnormal+'</span>'
                }else{
                    return '<span class="layui-btn layui-btn-danger layui-btn-xs">'+d.isAbnormal+'</span>'
                }
            }},
            {field: 'operatingTime', title: '操作时间', align:'center', minWidth:200}
        ]]
    });
 	
})
