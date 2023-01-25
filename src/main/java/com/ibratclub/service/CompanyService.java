package com.ibratclub.service;

import com.ibratclub.dto.ApiResponse;
import com.ibratclub.model.Attachment;
import com.ibratclub.model.Company;
import com.ibratclub.model.Employee;
import com.ibratclub.repository.AttachmentRepository;
import com.ibratclub.repository.CompanyRepository;
import com.ibratclub.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Malikov Azizjon  *  25.01.2023  *  12:51   *  IbratClub
 */

@Service
@RequiredArgsConstructor
public class CompanyService {

    @Value("${page.size}")
    private int size;

    private final CompanyRepository companyRepository;
    private final AttachmentRepository attachmentRepository;
    private final EmployeeRepository employeeRepository;

    public ApiResponse<Page<Company>> getAll(int page, Boolean active) {

        Page<Company> companies = companyRepository.findAllByActive(active, PageRequest.of(page,size));
        if (companies.isEmpty()) {
            return ApiResponse.<Page<Company>>builder().
                    success(false).
                    status(400).
                    message("Companies not found").
                    build();
        }
        return ApiResponse.<Page<Company>>builder().
                success(true).
                status(200).
                message("Companies here").
                data(companies).
                build();
    }

    public ApiResponse<Company> getOne(Long id) {

        Optional<Company> companyOptional = companyRepository.findById(id);
        if (companyOptional.isPresent()) {
            Company company = companyOptional.get();
            return ApiResponse.<Company>builder().
                    success(true).
                    status(200).
                    message("Company here").
                    data(company).
                    build();
        }
        return ApiResponse.<Company>builder().
                success(false).
                status(400).
                message("Company is not found").
                build();
    }

//    @SneakyThrows
//    public ApiResponse<?> add(CompanyDTO dto) {
//        Company company = new Company();
//        if (dto.getAttachment() != null) {
//            MultipartFile photo = dto.getAttachment();
//            Attachment attachment = new Attachment();
//            attachment.setBytes(photo.getBytes());
//            attachment.setOriginalName(photo.getOriginalFilename());
//            attachment.setContentType(photo.getContentType());
//            attachment.setSize(photo.getSize());
//            company.setAttachment(attachment);
//        }
//        company.setName(dto.getName());
//        company.setActivityType(dto.getActiveType());
//        company.setStirNumber(dto.getStirNumber());
//        Optional<Company> companyOptional = companyRepository.findById(dto.getMemberOrganizationId());
//        if (companyOptional.isEmpty()) {
//            return ApiResponse.builder().
//                    message("Member organization not found!!!").
//                    status(400).
//                    success(false).
//                    build();
//        }
//        company.setMemberOrganization(companyOptional.get());
//        company.setBankInfo(dto.getBankInfo());
//        company.setDirector(userRepository.findById(dto.getDirectorId()).get());
//        Address address = new Address();
//        AddressDTO addressDTO = dto.getAddressDTO();
//        Optional<District> districtOptional = districtRepository.findById(addressDTO.getDistrictId());
//        if (districtOptional.isEmpty()) {
//            return ApiResponse.<Company>builder().
//                    message("District not found!!!").
//                    status(400).
//                    success(false).
//                    build();
//        }
//        address.setDistrict(districtOptional.get());
//        address.setStreetHome(address.getStreetHome());
//        company.setAddress(address);
//
//        companyRepository.save(company);
//        return ApiResponse.builder().
//                message("Company is created").
//                status(201).
//                success(true).
//                data(companyRepository.save(company)).
//                build();
//    }
//
//    @SneakyThrows
//    public ApiResponse<?> edit(Long id, CompanyDTO dto, Employee employee) {
//        Optional<Company> companyOptional = companyRepository.findById(id);
//        if (companyOptional.isEmpty()) {
//            return ApiResponse.builder().
//                    success(false).
//                    status(400).
//                    message("Company is not found").
//                    build();
//        }
//        Company company = companyOptional.get();
//        if (company != employee.getCompany()) {
//            return ApiResponse.builder().
//                    success(false).
//                    status(400).
//                    message("Company is not found").
//                    build();
//        }
//        if (dto.getAttachment() != null) {
//            MultipartFile photo = dto.getAttachment();
//            Attachment attachment = new Attachment();
//            if (company.getAttachment() != null)
//                attachment = company.getAttachment();
//            attachment.setBytes(photo.getBytes());
//            attachment.setOriginalName(photo.getOriginalFilename());
//            attachment.setContentType(photo.getContentType());
//            attachment.setSize(photo.getSize());
//            company.setAttachment(attachment);
//        }
//        company.setName(dto.getName());
//        company.setActivityType(dto.getActiveType());
//        company.setStirNumber(dto.getStirNumber());
//        Optional<Company> memberCompanyOptional = companyRepository.findById(dto.getMemberOrganizationId());
//        if (memberCompanyOptional.isEmpty() || memberCompanyOptional.get().getId().equals(company.getId())) {
//            return ApiResponse.builder().
//                    message("Member organization not found!!!").
//                    status(400).
//                    success(false).
//                    build();
//        }
//        company.setMemberOrganization(memberCompanyOptional.get());
//        company.setBankInfo(BankInfo.builder().build());
//
//        Address address = company.getAddress();
//        AddressDTO addressDTO = dto.getAddressDTO();
//        Optional<District> districtOptional = districtRepository.findById(addressDTO.getDistrictId());
//
//        if (districtOptional.isEmpty()) {
//            return ApiResponse.<Company>builder().
//                    message("District not found!!!").
//                    status(400).
//                    success(false).
//                    build();
//        }
//        address.setDistrict(districtOptional.get());
//        address.setStreetHome(address.getStreetHome());
//        company.setAddress(address);
//        return ApiResponse.builder().
//                message("Company is edited!").
//                status(200).
//                success(true).
//                data(companyRepository.save(company)).
//                build();
//    }
//
//    public ApiResponse<?> delete(Long id, Employee employee) {
//        Optional<Company> companyOptional = companyRepository.findById(id);
//        if (companyOptional.isEmpty()) {
//            return ApiResponse.builder().
//                    message("Company is not found !").
//                    success(false).
//                    status(400).
//                    build();
//        }
//
//        Company company = companyOptional.get();
//        if (company != employee.getCompany()) {
//            return ApiResponse.builder().
//                    success(false).
//                    status(400).
//                    message("Company is not found").
//                    build();
//        }
//        company.setActive(!company.isActive());
//        companyRepository.save(company);
//        return ApiResponse.builder().
//                message("Is deleted !").
//                success(true).
//                status(200).
//                build();
//    }

    public ApiResponse<List<Company>> getAllList() {
        List<Company> companies = companyRepository.findAll();

        if (companies.isEmpty()) {
            return ApiResponse.<List<Company>>builder().
                    message("Users are not found!!!").
                    status(400).
                    success(false).
                    build();
        }
        return ApiResponse.<List<Company>>builder().
                message("Users here!!!").
                status(200).
                success(true).
                data(companies).
                build();
    }

    public ResponseEntity<?> getPhoto(Long id) {
        Optional<Attachment> attachmentOptional = attachmentRepository.findById(id);
        if (attachmentOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("Photo not found!!!");
        }
        Attachment attachment = attachmentOptional.get();
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(attachment.getContentType()))
                .contentLength(attachment.getSize())
                .body(attachment.getBytes());
    }

    public ApiResponse<List<Company>> getByUser(String phone) {
        Optional<Employee> employeeOptional = employeeRepository.findByPhoneFirst(phone);
        if (employeeOptional.isEmpty()){
            return ApiResponse.<List<Company>>builder().
                    message("User not found!!!").
                    status(400).
                    success(false).
                    build();
        }
        Employee employee = employeeOptional.get();
        List<Company> companyList = companyRepository.findAllByDirector(employee);
        return ApiResponse.<List<Company>>builder().
                message("Here!!!").
                status(200).
                success(true).
                data(companyList).
                build();
    }

}
