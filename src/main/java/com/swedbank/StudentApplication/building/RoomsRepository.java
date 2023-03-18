package com.swedbank.StudentApplication.building;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomsRepository extends JpaRepository<ClassRoom, Long> {
}
