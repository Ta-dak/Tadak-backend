package com.example.tadak.room.controller;

import com.example.tadak.room.data.RoomRequestDto;
import com.example.tadak.room.service.RoomService;
import com.example.tadak.user.domain.User;
import com.example.tadak.util.GetUserDetails;
import com.example.tadak.util.ResponseDataTemplate;
import com.example.tadak.util.ResponseTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.tadak.util.ResponseCode.*;

@RestController
@RequestMapping("/rooms")
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;

    @PostMapping
    public ResponseEntity<?> createRoom(@RequestBody RoomRequestDto requestDto, @GetUserDetails User user) {
        return ResponseDataTemplate.toResponseEntity(
                OK_SUCCESS,
                roomService.create(requestDto, user));
    }

    @GetMapping
    public ResponseEntity<?> readMyRooms(@GetUserDetails User user) {
        return ResponseDataTemplate.toResponseEntity(
                OK_SUCCESS,
                roomService.readMyRooms(user));
    }

    @DeleteMapping("/{$id}")
    public ResponseEntity<?> deleteRoom(@PathVariable String id) {
        roomService.delete(id);
        return ResponseTemplate.toResponseEntity(OK_SUCCESS);
    }
}
