function addUsers(){
	var param={
			firstname:$("#firstname").val(),
			lastname:$("#lastname").val(),
			email:$("#email").val(),
			password:$("#password").val(),
			phone:$("#phone").val()
	}

	var ser_data=JSON.stringify(param);
	$.ajax({
		type:"POST",
		contentType:'application/json; charset=UTF-8',
		url:'addUser',
		data:ser_data,
		success:function(data){
			if(data==1){
				alert("parolalar eşleşmiyor")
			}else if(data=='OK'){
				alert("başarıyla üye olundu");
				
				
			}else if(data=='ERROR'){
				alert("Bir hata oluştu");
			}
		},error:function(data){
			alert(data)
		}
		
		
	});
}