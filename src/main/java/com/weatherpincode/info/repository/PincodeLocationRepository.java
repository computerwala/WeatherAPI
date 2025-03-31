package com.weatherpincode.info.repository;

import com.weatherpincode.info.model.PincodeLocation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PincodeLocationRepository extends JpaRepository<PincodeLocation, Long> {
    Optional<PincodeLocation> findByPincode(String pincode);
}
