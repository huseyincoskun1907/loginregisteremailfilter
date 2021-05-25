function loginUsers(){
	var param={
			
			email:$("#email").val(),
			password:$("#password").val()
			
	}

	var ser_data=JSON.stringify(param);
	$.ajax({
		type:"GET",
		contentType:'application/json; charset=UTF-8',
		url:'loginUser',
		data:ser_data,
		success:function(data){
			if(data==1){
				alert("email yok")
			}else if(data=='OK'){
				windows.location.href="index";
				
				
			}else if(data=='ERROR'){
				alert("Bir hata oluştu");
			}
		},error:function(data){
			alert(data)
		}
		
		
	});
}