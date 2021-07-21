package kr.co.swingsaver.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import kr.co.swingsaver.response.FileUploadResponse;
import kr.co.swingsaver.utils.CommonUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FileService {
	@Value("${user.data.uploadpath}")
	private String location;
	private Path fileLocation;
	
	@PostConstruct
	private void init() {
		this.fileLocation = Paths.get(location);
		try {
			Files.createDirectories(this.fileLocation);
		}catch(IOException ex) {
			log.error(ex.getMessage());
		}
	}
	/**
	 * 업로드 파일을 저장한다. 
	 * 
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public FileUploadResponse storeFile(MultipartFile file) throws Exception {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		
		try {
			// 파일명에 부적합 문자가 있는지 확인한다.
            if(fileName.contains(".."))
                throw new Exception("파일명에 부적합 문자가 포함되어 있습니다. " + fileName);

            int pos = fileName.lastIndexOf( "." );
            String ext = fileName.substring( pos + 1 );
            String newFileName = CommonUtil.generateUniqueFileName();
            newFileName += "." + ext;
            
            Path targetLocation = this.fileLocation.resolve(newFileName);

            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/download/")
                    .path(newFileName)
                    .toUriString();
            FileUploadResponse fileUploadResponse = new FileUploadResponse(fileName,
            		newFileName,
                    fileDownloadUri,
                    file.getContentType(),
                    file.getSize());
            return fileUploadResponse;
		}catch(Exception ex) {
			throw new Exception("["+fileName+"] 파일 업로드에 실패하였습니다. 다시 시도하십시오.", ex);
		}
	}
}
