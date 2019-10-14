var index;
var layer;
var tableIns ;
var form;
layui.use(['form','layer','table','laytpl'],function(){
    var $ = layui.jquery,
    	laytpl = layui.laytpl,
    	table = layui.table;
		//layer = parent.layer === undefined ? layui.layer : top.layer;
    	layer=layui.layer;
    	form = layui.form;
     	
    //用户列表
     tableIns = table.render({
        elem: '#userList',
        url : webname+'/backsystem/system/getSysUsers',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,15,20],
        limit : 10,
        id : "userListTable",
        cols : [[
            {type: "checkbox", fixed:"left", minWidth:20},
            {type:'numbers', minWidth:20,title:'序号'},
            {field: 'username', title: '用户名', minWidth:150, align:"center"},
            {field: 'userEmail', title: '用户邮箱', minWidth:250, align:'center',templet:function(d){
                return '<a class="layui-blue" href="mailto:'+d.userEmail+'">'+d.userEmail+'</a>';
            }},
            {field: 'rname', title: '用户角色',minWidth:100, align:'center'},
            {field: 'isdisable', title: '用户状态', minWidth:100,  align:'center',templet:function(d){
                return d.isdisable == "0" ? "正常使用" : "禁止使用";
            }},
            {field: 'userEndTime', title: '最后登录时间',minWidth:120, align:'center'},
            {title: '操作', minWidth:185, templet:'#userListBar',fixed:"right",align:"center",templet:function(d){

            	var h="";
            	var getTpl = $("#caozuo").html();
             	laytpl(getTpl).render(d, function(html){
             		h=html;
             	});            	
             	return h;
            	
            }}
        ]]
    });
    
    //提交监听
    form.on('submit(submit)',function(d){
    	  var param=d.field;
    	  var copyname =$("#copyname").val();
    	  if(copyname==param.username){
    		  param.username=null;
    	  }
		  $.post(webname+"/backsystem/index/addSystemUser",param,function(d){
			  if(qjty(d)){
				  layer.msg(d.msg);
				 
				  if(d.code>0){
					  layer.close(index); 
					  tableIns.reload({});
				  }
			  }
		  })
    	
    	
    	return false;
    })
    

    
    $(".search_btn").on("click",function(){

        	tableIns.reload({
                page: {
                    curr: 1 //重新从第 1 页开始
                },
                where: {
                    username:$(".searchVal").val()  //搜索的关键字
                }
            })

    });

    //添加用户
    function addUser(edit){
		$("#uid").val("");
		$("#username").val("");
		$("#password").val("");
		$("#roleid").val("");
		$("#copyname").val("");
		form.render();
    	index = layer.open({
    		title : '添加系统用户', //标题
    		type : 1,
    		area : [ '600px', '383px' ],
    		offset : 'auto',
    		shadeClose : false, //点击遮罩关闭
    		content : $('#tc'),
    		skin : 'show'
    	});
    }
    $(".addNews_btn").click(function(){
       addUser ();
    })

    

    //批量删除
    $(".delAll_btn").click(function(){
        var checkStatus = table.checkStatus('userListTable'),
            data = checkStatus.data,
           uids = "";
        if(data.length > 0) {
            for (var i in data) {
            	uids+=data[i].uid+",";
            }
          //  console.log(uids);
  
            layer.confirm('确定删除选中的用户？', {icon: 3, title: '提示信息'}, function (index) {
            	$.post(webname+"/backsystem/system/deletes",{uids:uids},function(d){
            		if(qjty(d)){
            	
        				layer.msg(d.msg);
        				tableIns.reload();
        				layer.close(index);
            		}
            	})
                
               
                // })
            })
        }else{
            layer.msg("请选择需要删除的用户");
        }
    })

   /* //列表操作
    table.on('tool(userList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;

        if(layEvent === 'edit'){ //编辑
            addUser(data);
        }else if(layEvent === 'usable'){ //启用禁用
            var _this = $(this),
                usableText = "是否确定禁用此用户？",
                btnText = "已禁用";
            if(_this.text()=="已禁用"){
                usableText = "是否确定启用此用户？",
                btnText = "已启用";
            }
            layer.confirm(usableText,{
                icon: 3,
                title:'系统提示',
                cancel : function(index){
                    layer.close(index);
                }
            },function(index){
                _this.text(btnText);
                layer.close(index);
            },function(index){
                layer.close(index);
            });
        }else if(layEvent === 'del'){ //删除
            layer.confirm('确定删除此用户？',{icon:3, title:'提示信息'},function(index){
                // $.get("删除文章接口",{
                //     newsId : data.newsId  //将需要删除的newsId作为参数传入
                // },function(data){
                    tableIns.reload();
                    layer.close(index);
                // })
            });
        }
    });
*/
})
//////////////////////////////////////////////////////////////////////
//打开编辑弹窗
    function update(id,name,roleid){
		
			$("#uid").val(id);
			$("#username").val(name);
			$("#password").val("");
			$("#roleid").val(roleid);
			$("#copyname").val(name);
			form.render();
    	index = layer.open({
    		title : '修改系统用户', //标题
    		type : 1,
    		area : [ '600px', '383px' ],
    		offset : 'auto',
    		shadeClose : false, //点击遮罩关闭
    		content : $('#tc'),
    		skin : 'show'
    	});
    }
//启用 禁用
	function change(id,state){
	
		if(state=='0'){
			state="1";
		}else if(state=='1'){
			state='0';
		}
		var param={
			uid:id,
			isdisable:state
		};
		$.post(webname+"/backsystem/system/change",param,function(d){
			if(qjty(d)){
				layer.msg(d.msg);
				if(d.code>0){
					tableIns.reload({});
				}
			}
			
		})
		
		
	}
	
	//删除用户
	function del(id){
		layer.confirm('确定要删除吗？', {icon: 3, title:'提示'}, function(index){
			$.post(webname+"/backsystem/system/delete",{uid:id},function(d){
				if(qjty(d)){
					layer.msg(d.msg,{time:2});
					if(d.code>0){
						tableIns.reload({});
					}
				}
			})
		});
		
	}

