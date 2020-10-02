package com.marqeton.marqetonapi.utility;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component("File Utility Service")
public class FileStorage {
	
	
	@Value("${app.brandDir}")
	public String BRAND_DIR;
	
	@Value("${app.categoryDir}")
	public String CATEGORY_DIR;
	
	@Value("${app.subCategoryDir}")
	public String SUB_CATEGORY_DIR;
	
	@Value("${app.productDir}")
	public String PRODUCT_DIR;
	
	@Value("${app.bannerDir}")
	public String BANNER_DIR;
	
	private Path rootLocation = Paths.get("src/main/resources/");
	 
	
	
	public void store(MultipartFile file,String fileName, String relativePath) {
		try {
			
			/*
			 * Set Relative path
			 */
			Path location = Paths.get(relativePath);
			
			/*
			 * Get file extension
			 */
			String fileExt = FilenameUtils.getExtension(file.getOriginalFilename());
			
			/*=====================================================================================
			 * Place the file with Path + filename + extension
			 * Ex: path=src/main/resources/static/images/users/ filename="profile" extension="png"
			 * result: src/main/resources/static/images/users/profile.png
			 ======================================================================================*/
			if(!Files.exists(location.resolve(fileName+"."+fileExt))) {
				Files.copy(file.getInputStream(), location.resolve(fileName+"."+fileExt));
			}
		} catch (Exception e) {
			throw new RuntimeException("FAIL!");
		}
	}
 
	public Resource loadFile(String filename) {
		try {
			Path file = rootLocation.resolve(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new RuntimeException("FAIL!");
			}
		} catch (MalformedURLException e) {
			throw new RuntimeException("FAIL!");
		}
	}
	
	public void delete(String relativePath) {
		Path location = Paths.get(relativePath);
		try {
			if(location.toFile().exists()) {
				FileUtils.forceDelete(location.toFile());
			}
			else {
				System.out.println("File doesn't exist: "+ location.toFile().getName());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
 
	public void deleteAll(String relativePath) {
		Path location = Paths.get(relativePath);
		try {
			FileUtils.cleanDirectory(location.toFile());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
 
	public void deleteAllExcept(String directoryRelativePath, List<String> fileRelativePaths) {
		Path directoryLocation = Paths.get(directoryRelativePath);
		boolean fileExists = false;
			try {
				Iterator filesIterator = FileUtils.iterateFiles(directoryLocation.toFile(), null, false);
				while (filesIterator.hasNext()) {
					File file = (File)filesIterator.next();
					fileExists = false;
					for (String fileRelativePath : fileRelativePaths) {
						if(FileUtils.contentEquals(file, Paths.get(fileRelativePath).toFile())) {
							fileExists = true;
							break;
						}
					}
					if(!fileExists) {
						FileUtils.forceDelete(file);
					}
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
	public void createDirectory(String relativePath) {
		try {
			Path location = Paths.get(relativePath);
			if (!Files.exists(location)) {
				Files.createDirectories(location);
			}
			else {
				/*
				 * Replace with Log4J
				 */
				System.out.println("Info: Directory already exists");
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Could not initialize storage!");
		}
	}
	public void init() {
		try {
			Files.createDirectory(rootLocation);
		} catch (IOException e) {
			throw new RuntimeException("Could not initialize storage!");
		}
	}
	
	public String getFilePattern() {
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		Calendar calendar = Calendar.getInstance(); 
		Date now = calendar.getTime();
		String filePattern = dateFormat.format(now);
		
		return filePattern;
	}
	
	public void renameDirectory(String renameFrom, String renameTo) throws IOException {
		Path renameFromLocation = Paths.get(renameFrom);
		Path renameToLocation = Paths.get(renameTo);
		
		Files.move(renameFromLocation, renameToLocation);
	}
}
