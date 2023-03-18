package com.swedbank.StudentApplication.building;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SchoolService {

    private final BuildingRepository buildingRepository;

    private final RoomsRepository roomRepository;

    public List<Building> allBuildings() {return buildingRepository.findAll();}

    public Building getOneBuilding(int id) {
        var building = buildingRepository.findById(id).orElseThrow(() -> new BuildingNotFoundException(id));

        return building;
    }


    public List<ClassRoom> getBuildingRooms(long id){
        var building = buildingRepository.findById((int) id).orElseThrow(() -> new RoomsNotFound((int) id));

        return building.getRoomSet();
    }

    public ClassRoom addClassRoom(ClassRoom newRoom, int buildingId, long roomId) {
        Building building = buildingRepository.findById((int) buildingId).orElseThrow(() -> new BuildingNotFoundException(buildingId));
        return roomRepository.findById(roomId).map(room -> {
            room.setName(newRoom.getName());
            room.setCapacity(newRoom.getCapacity());
            room.setFloor(newRoom.getFloor());
            room.setBuilding(building);
            return roomRepository.save(room);
        }).orElseGet(() ->{newRoom.setId(roomId);
            newRoom.setBuilding(building);
            return roomRepository.save(newRoom);
        });
    }
}
