import java.io.*;

public class FileModel {
    public void uploadFile(String uploadDir, String fileName, InputStream fileContent) throws IOException {
        String filePath = uploadDir + File.separator + fileName;
        File uploadedFile = new File(filePath);
        
        try (OutputStream os = new FileOutputStream(uploadedFile)) {
            int bytesRead;
            byte[] buffer = new byte[4096];
            while ((bytesRead = fileContent.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
        }
    }

    public File getFile(String filePath) {
        return new File(filePath);
    }

    public boolean deleteFile(String filePath) {
        File file = new File(filePath);
        return file.exists() && file.delete();
    }
}
