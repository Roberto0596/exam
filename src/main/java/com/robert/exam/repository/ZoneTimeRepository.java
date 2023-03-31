package com.robert.exam.repository;

import com.robert.exam.entity.ZoneTime;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZoneTimeRepository extends CrudRepository<ZoneTime, Long> {
}
