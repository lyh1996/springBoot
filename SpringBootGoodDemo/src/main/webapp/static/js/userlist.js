var userObj;

//用户管理页面上点击删除按钮弹出删除框(userlist.jsp)
function deleteUser(id){
    if(!confirm("确认要删除该订单吗")){
        return;
    }
	$.ajax({
		type:"POST",
		url:"/jsp/deleteUser.action",
		data:{id:id},
		dataType:"json",
        success:function(data){
            if(data==1){
                alert("删除成功！！");
                window.location.href="jsp/userlist";
            }
        },
        error:function(data){
            alert("对不起，删除失败");
        }
	});
}

function openYesOrNoDLG(){
	$('.zhezhao').css('display', 'block');
	$('#removeUse').fadeIn();
}

function cancleBtn(){
	$('.zhezhao').css('display', 'none');
	$('#removeUse').fadeOut();
}
function changeDLGContent(contentStr){
	var p = $(".removeMain").find("p");
	p.html(contentStr);
}
function parseDate(milliseconds){
    var d = new Date(milliseconds);
    return d.getFullYear()+"-"+zore(d.getMonth()+1)+"-"+zore(d.getDate())+" "+zore(d.getHours())+":"+zore(d.getMinutes())+":"+zore(d.getSeconds());
}
function zore(str){
    return str<10?"0"+str:str;
}

//查看订单
function findUser(id) {
    window.location.href="/jsp/user.do?method=view&id="+id;
}
//修改订单
function UpdateUser(id) {
    window.location.href="/jsp/user.do?method=update&id="+id;
}

function getUserList(params){
	$.post("jsp/getUser",params,function(result){
		 params["pageNum"] = result.pageNum;
	     params["pageSize"] = result.pageSize;
	     params["usercode"] = result.usercode;
	     params["username"] = result.username;
	        
	        //加载分页图标
		 var spans = "<span class=\"first\">首页</span><span class=\"prev\">上一页</span>";
	        $.each(result.navigatepageNums, function(){
	            if(isNaN(this)){
	                return;
	            }
	            if(result.pageNum==this){
	                spans+="<span class=\"pageNum\">"+this+"</span>";
	            }else{
	                spans+="<span class=\"other\">"+this+"</span>";
	            }
	        });
	        spans+="<span class=\"next\">下一页</span><span class=\"last\">尾页</span>";
	        $(".page>span").remove();
	        $(".page").html(spans);
	        
	        if(result.isFirst){
	            $(".first,.prev").css("background","#eee");
	        }else{
	            $(".first").click(function(){
	                params["pageNum"] = 1;
	                getUserList(params);
	            });
	            $(".prev").click(function(){
	                params["pageNum"]--;
	                getUserList(params);
	            });
	        }
	        
	        if(result.isLast){
	            $(".last,.next").css("background","#eee");
	        }else{
	            $(".last").click(function(){
	                params["pageNum"] = result.pages;
	                getUserList(params);
	            });
	            $(".next").click(function(){
	                params["pageNum"]++;
	                getUserList(params);
	            });
	        }
	        $(".other").click(function(){
	            params["pageNum"] = $(this).text();
	            getUserList(params);
	        });
	        
	        $(".providerTable>tbody>tr:gt(0)").remove();
	        $.each(result.rows, function(){
	        	var sex = this.gender ==1 ? '女' : '男';
				var rolename;
				if (this.userrole == 1) {
					rolename = "系统管理员";
				} else if (this.userrole == 2) {
					rolename = "经理";
				} else {
					rolename = "普通员工";
				}
				var a1=this.birthday;
                var date = new Date(Date.parse(a1));
                var date_value=date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate();
	            var $tr = $("<tr>"+
	                          "<td>"+this.id+"</td>"+
	                          "<td>"+this.username+"</td>"+
	                          "<td>"+sex+"</td>"+
	                          "<td>"+date_value+"</td>"+
	                          "<td>"+this.phone+"</td>"+
	                          "<td>"+rolename+"</td>"+
                    " <td><span><a class=\"viewUser\" href=\"javascript:findUser("+this.id+");\" ><img src=\"static/images/read.png\" alt=\"查看\" title=\"查看\" /></a></span> <span><a class=\"modifyUser\" href=\"javascript:UpdateUser("+this.id+");\" ><img src=\"static/images/xiugai.png\" alt=\"修改\" title=\"修改\" /></a></span> <span><a class=\"deleteUser\" href=\"javascript:deleteUser("+this.id+");\" ><img src=\"static/images/schu.png\" alt=\"删除\" title=\"删除\" /></a></span></td>"+
                    "</tr>");
	            $(".providerTable>tbody").append($tr);
	        });
	    });
}
$(function(){
	//查找所有的角色信息
    $.ajax({
        type:"post",
        url:"/jsp/getAllRoleInfo.action",
        dataType:"json",
        success:function(data){
             for (var i = 0,len = data.RoleInfo.length; i < len; i ++) {
				$("#queryUserRole").append('<option value="' + data.RoleInfo[i].id + '">'+ data.RoleInfo[i].rolename +'</option>')
			 }
        },
        error:function(data){
            alert("对不起，查询角色信息失败");
        }
    });

	 var  params={"pageNum":1,"pageSize":5};
	getUserList(params);
	$("#searchbutton").click(function(){
		 var username = $("input[name=queryname]").val();
         var userrole = $("select[name=queryUserRole]").val();
	        params["username"] = username;
	        params["userrole"] = userrole;
	        params["pageNum"] = 1;
        	getUserList(params);
	});
	//通过jquery的class选择器（数组）
	//对每个class为viewUser的元素进行动作绑定（click）
	/**
	 * bind、live、delegate
	 * on
	/* *!/
	$(".viewUser").on("click",function(){
		//将被绑定的元素（a）转换成jquery对象，可以使用jquery方法
		var obj = $(this);
		window.location.href=path+"/jsp/user.do?method=view&uid="+ obj.attr("userid");
	});
	
	$(".modifyUser").on("click",function(){
		var obj = $(this);
		window.location.href=path+"/jsp/user.do?method=modify&uid="+ obj.attr("userid");
	});*/

	$('#no').click(function () {
		cancleBtn();
	});
	
	$('#yes').click(function () {
		deleteUser(userObj);
	});

	$(".deleteUser").on("click",function(){
		userObj = $(this);
		changeDLGContent("你确定要删除用户【"+userObj.attr("username")+"】吗？");
		openYesOrNoDLG();
	});
	
	/*$(".deleteUser").on("click",function(){
		var obj = $(this);
		if(confirm("你确定要删除用户【"+obj.attr("username")+"】吗？")){
			$.ajax({
				type:"GET",
				url:path+"/jsp/user.do",
				data:{method:"deluser",uid:obj.attr("userid")},
				dataType:"json",
				success:function(data){
					if(data.delResult == "true"){//删除成功：移除删除行
						alert("删除成功");
						obj.parents("tr").remove();
					}else if(data.delResult == "false"){//删除失败
						alert("对不起，删除用户【"+obj.attr("username")+"】失败");
					}else if(data.delResult == "notexist"){
						alert("对不起，用户【"+obj.attr("username")+"】不存在");
					}
				},
				error:function(data){
					alert("对不起，删除失败");
				}
			});
		}
	});*/
});