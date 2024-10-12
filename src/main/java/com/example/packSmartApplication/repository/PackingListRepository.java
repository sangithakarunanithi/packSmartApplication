package com.example.packSmartApplication.repository;

import com.example.packSmartApplication.entity.PackingList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PackingListRepository extends JpaRepository<PackingList, Long> {
}
