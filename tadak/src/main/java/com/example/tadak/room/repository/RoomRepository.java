package com.example.tadak.room.repository;

import com.example.tadak.room.domain.Room;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RoomRepository extends MongoRepository<Room, String> {
    List<Room> findAllByManagerId(String managerId);
}
