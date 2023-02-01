package com.ibratclub.service;

import com.ibratclub.dto.ApiResponse;
import com.ibratclub.model.Country;
import com.ibratclub.model.District;
import com.ibratclub.model.Region;
import com.ibratclub.repository.CountryRepository;
import com.ibratclub.repository.DistrictRepository;
import com.ibratclub.repository.RegionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final DistrictRepository districtRepository;
    private final RegionRepository regionRepository;
    private final CountryRepository countryRepository;

    public ApiResponse<List<District>> getAllDistricts(Long regionId) {
        List<District> districts;
        if (regionId != null) {
            districts = districtRepository.findAllByRegion_Id(regionId);
        } else {
            districts = districtRepository.findAll();
        }
        return ApiResponse.<List<District>>builder().
                message("Here").
                status(200).
                success(true).
                data(districts).
                build();
    }

    public ApiResponse<List<Region>> getAllRegions(Long countryId) {
        List<Region> regions;
        if (countryId != null) {
            regions = regionRepository.findAllByCountry_Id(countryId);
        } else {
            regions = regionRepository.findAll();
        }
        return ApiResponse.<List<Region>>builder().
                message("Here").
                status(200).
                success(true).
                data(regions).
                build();
    }

    public ApiResponse<List<Country>> getAllCountry() {
        return ApiResponse.<List<Country>>builder().
                message("Here").
                status(200).
                success(true).
                data(countryRepository.findAll()).
                build();
    }

}
