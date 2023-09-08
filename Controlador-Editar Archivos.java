package Controlador;

import Modelo.Editar_Modelo;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;


@WebServlet("/FileController")
public class Editor_Con extends HttpServlet {
    private Editar_Modelo fileModel; // Usa el modelo FileModel para interactuar con los archivos

    @Override
    public void init() throws ServletException {
        super.init();
        fileModel = new Editar_Modelo(); // Inicializa el modelo
    }
    private String getFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        String[] tokens = contentDisposition.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return "";
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("upload".equals(action)) {
            String uploadDir = getServletContext().getRealPath("/uploads");
            Part filePart = request.getPart("file");
            String fileName = getFileName(filePart);

            try (InputStream fileContent = filePart.getInputStream()) {
                fileModel.uploadFile(uploadDir, fileName, fileContent); // Llama al método del modelo
                response.getWriter().write("Archivo subido con éxito.");
            } catch (IOException e) {
                response.getWriter().write("Error al subir el archivo: " + e.getMessage());
            }

        } else if ("uploadCSV".equals(action)) {
            Part filePart = request.getPart("file");
            String fileName = getFileName(filePart);

            try (InputStream fileContent = filePart.getInputStream();
                 InputStreamReader reader = new InputStreamReader(fileContent);
                 BufferedReader bufferedReader = new BufferedReader(reader)) {
            
                 String line;
                 while ((line = bufferedReader.readLine()) != null) {
                    // Dividir la línea del CSV en tokens utilizando una coma como separador
                    StringTokenizer tokenizer = new StringTokenizer(line, ",");

                    while (tokenizer.hasMoreTokens()) {
                        // Acceder a cada token y realizar las acciones necesarias
                        String token = tokenizer.nextToken();
                        System.out.println("Token: " + token);
                    }
                 }
                response.getWriter().write("Archivo CSV procesado con éxito.");
            } catch (IOException e) {
                response.getWriter().write("Error al procesar el archivo CSV: " + e.getMessage());
            }

        } else if ("download".equals(action)) {
            String fileName = "nombre_del_archivo_a_descargar.txt"; // Nombre del archivo a descargar (deberías obtenerlo de la solicitud)
            String filePath = getServletContext().getRealPath("/uploads/" + fileName);

            File file = fileModel.getFile(filePath); // Llama al método del modelo para obtener el archivo
            if (file.exists()) {
                response.setContentType("application/octet-stream");
                response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

                try (InputStream fileContent = new FileInputStream(file);
                     OutputStream os = response.getOutputStream()) {
                    int bytesRead;
                    byte[] buffer = new byte[4096];
                    while ((bytesRead = fileContent.read(buffer)) != -1) {
                        os.write(buffer, 0, bytesRead);
                    }
                }
            } else {
                response.getWriter().write("El archivo no existe.");
            }
        } else if ("delete".equals(action)) {
            String fileName = "nombre_del_archivo_a_eliminar.txt"; // Nombre del archivo a eliminar (deberías obtenerlo de la solicitud)
            String filePath = getServletContext().getRealPath("/uploads/" + fileName);

            if (fileModel.deleteFile(filePath)) { // Llama al método del modelo para eliminar el archivo
                response.getWriter().write("Archivo eliminado con éxito.");
            } else {
                response.getWriter().write("El archivo no existe.");
            }
        }
    }
}
/* <form id="upload-form" action="/tu-contexto-de-servlet/Editor_Con" method="POST" enctype="multipart/form-data">
    <input type="file" name="file" id="file" accept=".csv">
    <input type="hidden" name="action" value="uploadCSV">
    <input type="submit" value="Subir CSV">
</form>
*/
