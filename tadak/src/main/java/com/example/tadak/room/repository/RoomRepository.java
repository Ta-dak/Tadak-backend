package com.example.tadak.room.repository;

import com.example.tadak.room.domain.Room;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoomRepository extends MongoRepository<Room, String> {
}
