package Config;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.Part;

public class ImageUtil {
	

	public static void saveImage(Part part, String type, String filename) {
        String fileName = null;
		try {
			fileName = filename;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//file name
		
		String applicationPath ="C:\\Users\\acer\\Desktop\\cc-shop\\src\\main\\webapp\\assets\\images" ;
		if(type.equals("admin")) {
			applicationPath ="C:\\Users\\acer\\Desktop\\cc-shop\\src\\main\\webapp\\assets\\images\\admin" ;
		}else if(type.equals("user")) {
			applicationPath ="C:\\Users\\acer\\Desktop\\cc-shop\\src\\main\\webapp\\assets\\images\\user" ;
		}else if(type.equals("seller")) {
			applicationPath ="C:\\Users\\acer\\Desktop\\cc-shop\\src\\main\\webapp\\assets\\images\\seller" ;
		}

        
        String uploadPath = applicationPath + File.separator ;
        System.out.println("applicationPath:" + applicationPath);
        File fileUploadDirectory = new File(uploadPath);
        if (!fileUploadDirectory.exists()) {
            fileUploadDirectory.mkdirs();
        }
        String savePath = uploadPath + File.separator + fileName;
        System.out.println("savePath: " + savePath);
        String sRootPath = new File(savePath).getAbsolutePath();
        System.out.println("sRootPath: " + sRootPath);
        
        
        Path directoryPath = Paths.get("C:\\Users\\acer\\Desktop\\cc-shop\\src\\main\\webapp\\assets\\images");
        if(type.equals("admin")) {
        	directoryPath = Paths.get("C:\\Users\\acer\\Desktop\\cc-shop\\src\\main\\webapp\\assets\\images\\admin");
		}else if(type.equals("user")) {
			directoryPath = Paths.get("C:\\Users\\acer\\Desktop\\cc-shop\\src\\main\\webapp\\assets\\images\\user");
		}else if(type.equals("seller")) {
			directoryPath = Paths.get("C:\\Users\\acer\\Desktop\\cc-shop\\src\\main\\webapp\\assets\\images\\seller");
		}
        
        try {
			Files.createDirectories(directoryPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        
        File fileSaveDir1 = new File(savePath);
        String dbFileName =  File.separator + fileName;
        try {
			part.write(savePath + File.separator);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] tokens = contentDisp.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return "";
    }
	
}
