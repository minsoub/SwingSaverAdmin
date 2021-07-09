package kr.co.swingsaver.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.swingsaver.request.GroupRequest;
import kr.co.swingsaver.response.GroupResponse;
import kr.co.swingsaver.service.GroupService;
import kr.co.swingsaver.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/group")
@Tag(name="그룹관리", description="그룹 관리")
public class GroupController {

	final GroupService groupService;
	final UserService userService;
	
    @Operation(summary = "그룹 조회", description = "그룹 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "그룹 리스트 조회", content = @Content(schema = @Schema(implementation = GroupResponse.class)))
    })    
    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> list()
	{
    	return ResponseEntity.ok(groupService.search());
	}
    
    @Operation(summary = "리스트 그룹 조회", description = "리스트 그룹 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "그룹 리스트 조회", content = @Content(schema = @Schema(implementation = GroupResponse.class)))
    })    
    @GetMapping(value = "/grouplist", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> grouplist()
	{
    	return ResponseEntity.ok(groupService.list());
	}    
    
    
    @Operation(description = "그룹 저장/수정")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "그룹 정보를 저장/수정한다.", content = @Content(schema = @Schema(implementation = GroupResponse.class)))
    })
    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@Parameter(description = "그룹정보", required = true, schema = @Schema(implementation = GroupRequest.class))
                                  @RequestBody GroupRequest request) {
        return ResponseEntity.ok(groupService.save(request));
    }
    
    
    @GetMapping(value = "/userlist", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> userlist() {
        return ResponseEntity.ok(userService.findAllUser());   
    }
}
