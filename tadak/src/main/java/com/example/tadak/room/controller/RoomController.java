package com.example.tadak.room.controller;

import com.example.tadak.auth.CustomUserDetails;
import com.example.tadak.room.data.RoomRequestDto;
import com.example.tadak.room.data.RoomResponseDto;
import com.example.tadak.room.service.RoomService;
import com.example.tadak.util.GetUserDetails;
import com.example.tadak.util.ResponseDataTemplate;
import com.example.tadak.util.ResponseTemplate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.tadak.util.ResponseCode.*;

@RestController
@RequestMapping("/rooms")
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;


    @Operation(summary = "채팅방 생성 API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = RoomResponseDto.class))))})
    @PostMapping
    public ResponseEntity<ResponseDataTemplate> createRoom(@RequestBody RoomRequestDto requestDto, @GetUserDetails CustomUserDetails userDetails) {
        return ResponseDataTemplate.toResponseEntity(
                OK_SUCCESS,
                roomService.create(requestDto, userDetails.getUser()));
    }


    @Operation(summary = "내가 만든 채팅방 리스트 조회 API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = RoomResponseDto.class))))})
    @GetMapping
    public ResponseEntity<ResponseDataTemplate> readMyRooms(@GetUserDetails CustomUserDetails userDetails) {
        return ResponseDataTemplate.toResponseEntity(
                OK_SUCCESS,
                roomService.readMyRooms(userDetails.getUser()));
    }


    @Operation(summary = "채팅방 삭제 API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ResponseTemplate.class))))})
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseTemplate> deleteRoom(@PathVariable String id) {
        roomService.delete(id);
        return ResponseTemplate.toResponseEntity(OK_SUCCESS);
    }
}
