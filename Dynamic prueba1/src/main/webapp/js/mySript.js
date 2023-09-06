/**
 * 
 */
function sendData(){
	
	let datoA=$("#a").val();
	let datoB=$("#b").val();
	let d={
		a:datoA,
		b:datoB
	}
	// jQuery está asumido como ya incluido en el proyecto

	// Realizar un llamado AJAX utilizando jQuery
	$.ajax({
	  url: 'MyServlet',  // URL del recurso
	  type: 'GET',  // Tipo de petición: GET, POST, PUT, DELETE, etc.
	 // dataType: 'json',  // Tipo de datos esperados del servidor
	   data:d,	 

	 success: function(data) {  // Función a ejecutar si la petición es exitosa
	    $("#resultado").html("<h2>"+data+"</h2>");
	  },
	  error: function(xhr, status, error) {  // Función a ejecutar si la petición falla
	     $("#resultado").html("<h2>Paila!!!</h2>");
		console.log('Error:', error);
	  }
	});

	
	
	
}