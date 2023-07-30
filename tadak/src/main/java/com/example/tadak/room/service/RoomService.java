package com.example.tadak.room.service;

import com.example.tadak.room.data.RoomRequestDto;
import com.example.tadak.room.data.RoomResponseDto;
import com.example.tadak.room.domain.Room;
import com.example.tadak.room.repository.RoomRepository;
import com.example.tadak.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;

    public List<RoomResponseDto> readMyRooms(User user) {
        return roomRepository.findAllByManagerId(user.getId()).stream()
                .map(RoomResponseDto::new)
                .collect(Collectors.toList());
    }

    public RoomResponseDto create(RoomRequestDto requestDto, User user) {
        Room room = new Room(requestDto, user);
        roomRepository.save(room);
        return new RoomResponseDto(room);
    }

    public void delete(String roomId) {
        roomRepository.deleteById(roomId);
    }
}
