var billObj;
//订单管理页面上点击删除按钮弹出删除框(billlist.jsp)
function deleteBill(billid){
	if(!confirm("确认要删除该订单吗")){
		return;
	}
	$.ajax({
		type:"post",
		url:"/jsp/deletion.action",
		data:{billid:billid},
		dataType:"json",
		success:function(data){
				if(data==1){
					alert("删除成功！！");
					window.location.href="jsp/billlist";
				}
		},
		error:function(data){
			alert("对不起，删除失败");
		}
	});
}

function openYesOrNoDLG(){
	$('.zhezhao').css('display', 'block');
	$('#removeBi').fadeIn();
}

function cancleBtn(){
	$('.zhezhao').css('display', 'none');
	$('#removeBi').fadeOut();
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
function findBill(id) {
    window.location.href="/jsp/bill.do?method=view&id="+id;
}
//修改订单
function UpdateBill(id) {
    window.location.href="/jsp/bill.do?method=update&id="+id;
}
$(function(){
	 //初始化方法 传入params为
	   var  params={"pageNum":1,"pageSize":5};
	getBillPage(params);
	$.post("jsp/getProviderList",function(result){
		$.each(result.rows,function(){
			var $option=$("<option value=\""+this.id+"\">"+this.proname+"</option>");
			$("select[name=queryProviderId]").append($option);
		})
	});
	$("#searchbutton").click(function(){
		 var productname = $("input[name=queryProductName]").val();
	        var providerid = $("select[name=queryProviderId]").val();
	        var ispayment = $("select[name=queryIsPayment]").val();
	        params["productname"] = productname;
	        params["providerid"] = providerid;
	        params["ispayment"] = ispayment;
	        params["pageNum"] = 1;
	        getBillPage(params);
	});
	/*$(".viewProvider").on("click",function(){
		//将被绑定的元素（a）转换成jquery对象，可以使用jquery方法
		var obj = $(this);
		window.location.href=path+"/jsp/bill.do?method=view&billid="+ obj.attr("id");
	});*/

	/*$(".modifyBill").on("click",function(){
		var obj = $(this);
		window.location.href=path+"/jsp/bill.do?method=modify&billid="+ obj.attr("billid");
	});*/
	$('#no').click(function () {
		cancleBtn();
	});
	
	$('#yes').click(function () {
		deleteBill(billObj);
	});

	$(".deleteBill").on("click",function(){
		billObj = $(this);
		changeDLGContent("你确定要删除订单【"+billObj.attr("billcc")+"】吗？");
		openYesOrNoDLG();
	});
	
	/*$(".deleteBill").on("click",function(){
		var obj = $(this);
		if(confirm("你确定要删除订单【"+obj.attr("billcc")+"】吗？")){
			$.ajax({
				type:"GET",
				url:path+"/bill.do",
				data:{method:"delbill",billid:obj.attr("billid")},
				dataType:"json",
				success:function(data){
					if(data.delResult == "true"){//删除成功：移除删除行
						alert("删除成功");
						obj.parents("tr").remove();
					}else if(data.delResult == "false"){//删除失败
						alert("对不起，删除订单【"+obj.attr("billcc")+"】失败");
					}else if(data.delResult == "notexist"){
						alert("对不起，订单【"+obj.attr("billcc")+"】不存在");
					}
				},
				error:function(data){
					alert("对不起，删除失败");
				}
			});
		}
	});*/
});
function getBillPage(params){
    $.post("jsp/getBillList",params,function(result){
        params["pageNum"] = result.pageNum;
        params["pageSize"] = result.pageSize;
        params["productname"] = result.productname;
        params["providerid"] = result.providerid;
        params["ispayment"] = result.ispayment;

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
                getBillPage(params);
            });
            $(".prev").click(function(){
                params["pageNum"]--;
                getBillPage(params);
            });
        }

        if(result.isLast){
            $(".last,.next").css("background","#eee");
        }else{
            $(".last").click(function(){
                params["pageNum"] = result.pages;
                getBillPage(params);
            });
            $(".next").click(function(){
                params["pageNum"]++;
                getBillPage(params);
            });
        }
        $(".other").click(function(){
            params["pageNum"] = $(this).text();
            getBillPage(params);
        });

        $(".providerTable>tbody>tr:gt(0)").remove();
        $.each(result.rows, function(){
            var $tr = $("<tr>"+
                "<td>"+this.billcode+"</td>"+
                "<td>"+this.productname+"</td>"+
                "<td>"+this.provider.proname+"</td>"+
                "<td>"+this.totalprice+"</td>"+
                "<td>"+(this.ispayment==1?"未支付":this.ispayment==2?"已支付":"")+"</td>"+
                "<td>"+parseDate(this.creationdate)+"</td>"+
                " <td><span><a class=\"viewProvider\" href=\"javascript:findBill("+this.id+");\" ><img src=\"static/images/read.png\" alt=\"查看\" title=\"查看\" /></a></span> <span><a class=\"modifyProvider\" href=\"javascript:UpdateBill("+this.id+");\" ><img src=\"static/images/xiugai.png\" alt=\"修改\" title=\"修改\" /></a></span> <span><a class=\"deleteProvider\" href=\"javascript:deleteBill("+this.id+");\" ><img src=\"static/images/schu.png\" alt=\"删除\" title=\"删除\" /></a></span></td>"+
                "</tr>");
            $(".providerTable>tbody").append($tr);
        });
    });
}