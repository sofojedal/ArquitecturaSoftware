import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet("/FileController")
public class FileController extends HttpServlet {
    private FileModel fileModel;

    @Override
    public void init() throws ServletException {
        super.init();
        fileModel = new FileModel();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("upload".equals(action)) {
            String uploadDir = getServletContext().getRealPath("/uploads");
            Part filePart = request.getPart("file");
            String fileName = getFileName(filePart);

            try (InputStream fileContent = filePart.getInputStream()) {
                fileModel.uploadFile(uploadDir, fileName, fileContent);
                response.getWriter().write("Archivo subido con éxito.");
            } catch (IOException e) {
                response.getWriter().write("Error al subir el archivo: " + e.getMessage());
            }
        } else if ("download".equals(action)) {
            String fileName = "nombre_del_archivo_a_descargar.txt";
            String filePath = getServletContext().getRealPath("/uploads/" + fileName);

            File file = fileModel.getFile(filePath);
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
            String fileName = "nombre_del_archivo_a_eliminar.txt";
            String filePath = getServletContext().getRealPath("/uploads/" + fileName);

            if (fileModel.deleteFile(filePath)) {
                response.getWriter().write("Archivo eliminado con éxito.");
            } else {
                response.getWriter().write("El archivo no existe.");
            }
        }
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
}
