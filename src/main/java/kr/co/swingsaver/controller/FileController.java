package kr.co.swingsaver.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.swingsaver.response.AuthResponse;
import kr.co.swingsaver.response.FileUploadResponse;
import kr.co.swingsaver.service.FileService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/file")
@Tag(name="File upload/download Controller", description="File upload/download Controller")
public class FileController {

	@Autowired
	private FileService service;
	
    @Operation(summary = "File 저장", description = "File 저장")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "File 저장", content = @Content(schema = @Schema(implementation = AuthResponse.class)))
    })  
    @PostMapping(value="/upload")
	public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) throws Exception {
		AuthResponse response = AuthResponse.newInstance();
		FileUploadResponse result = service.storeFile(file);
		response.addData("file", result);
		
		return response.build();
	}
	
}
