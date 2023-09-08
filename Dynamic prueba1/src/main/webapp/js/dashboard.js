

$(document).ready(function () {
  $('#upload-form').on('submit', function (e) {
    e.preventDefault();

    var formData = new FormData(this);

    $.ajax({
      url: 'DashboardServlet', // Reemplaza con la URL de tu servidor para manejar la carga de archivos
      type: 'POST', // Tipo de la petición
      data: formData,
      processData: false,
      contentType: false,
      xhr: function () {
        var xhr = new window.XMLHttpRequest();

        // Progreso de carga
        xhr.upload.addEventListener('progress', function (event) {
          if (event.lengthComputable) {
            var percent = (event.loaded / event.total) * 100;
            $('#progress-bar').css('width', percent + '%').attr('aria-valuenow', percent);
          }
        });

        return xhr;
      },
      success: function (data) {
       
        console.log('Carga de archivo exitosa', data);
      },
      error: function () {
        console.error('Error al cargar el archivo');
      }
    });
  });
});



$(document).ready(function () {
  
  $.ajax({
    url: '/DashboardServlet', 
    method: 'GET',
    dataType: 'json',
    success: function (data) {
      // Compila la plantilla Handlebars
      var source = $("#filaTemplate").html();
      var template = Handlebars.compile(source);

      // Cuerpo de la tabla
      var tbody = $("#miGrilla tbody");

      // Itero a través de los datos obtenidos
      $.each(data, function (index, objeto) {
        // Utilizo la plantilla Handlebars para generar la fila segura
        var filaHTML = template(objeto);

        // Agrego la fila generada al cuerpo de la tabla
        tbody.append(filaHTML);
      });
    },
    error: function () {
      console.error('Error al obtener datos del servidor');
    }
  });
});

