package com.ibratclub.service;

import com.ibratclub.dto.ApiResponse;
import com.ibratclub.dto.UserDTO;
import com.ibratclub.model.*;
import com.ibratclub.model.enums.RegisteredType;
import com.ibratclub.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    @Value("${page.size}")
    private int size;
    @Value("${company.department.id}")
    private Long departmentId;

    private final RequestRepository requestRepository;
    private final CountryRepository countryRepository;
    private final RegionRepository regionRepository;
    private final DepartmentRepository departmentRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ChangeService changeService;

    public ApiResponse<Page<User>> getAll(int page) {
        Page<User> userPage = userRepository.findAllByDepartment_Id(departmentId, PageRequest.of(page, size));
        return ApiResponse.<Page<User>>builder().
                message("Here").
                status(200).
                success(true).
                data(userPage).
                build();
    }

    public ApiResponse<User> getOne(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty() || !userOptional.get().getDepartment().getId().equals(departmentId)) {
            return ApiResponse.<User>builder().
                    message("User not found!!!").
                    status(400).
                    success(false).
                    build();
        }
        User user = userOptional.get();
        return ApiResponse.<User>builder().
                message("User here!!!").
                status(200).
                success(true).
                data(user).
                build();
    }

    public ApiResponse<User> add(UserDTO dto, Long eventId) {
        String email = dto.getEmail();
        String phone = dto.getPhone();
        boolean isEmail = email != null;
        boolean isPhone = phone != null;
        Request request = new Request();
        if (eventId != null) {
            Optional<Product> productOptional = productRepository.findById(eventId);
            if (productOptional.isEmpty() || !productOptional.get().getCategory().getDepartment().getId().equals(departmentId)) {
                return ApiResponse.<User>builder().
                        message("Event not found").
                        success(false).
                        status(400).
                        build();
            }
            request.setProduct(productOptional.get());
        }
        if (isEmail) {
            isEmail = !email.equals("");
        }
        if (isPhone) {
            isPhone = !phone.equals("");
        }
        if (isEmail && isPhone) {
            Optional<User> userOptionalByEmail1 = userRepository.findByEmailAndDepartment_Id(email, departmentId);
            Optional<User> userOptionalByPhone1 = userRepository.findByPhoneAndDepartment_Id(phone, departmentId);
            if (userOptionalByPhone1.isPresent() && userOptionalByEmail1.isPresent()) {
                User userByEmail = userOptionalByEmail1.get();
                User userByPhone = userOptionalByPhone1.get();
                if (!userByPhone.getId().equals(userByEmail.getId())) {
                    userByPhone.setEmail(userByEmail.getEmail());
                    User save = userRepository.save(userByPhone);
                    userRepository.delete(userByEmail);
                    request.setUser(save);
                    requestRepository.save(request);
                    return ApiResponse.<User>builder().
                            message("User updated!!!").
                            success(true).
                            status(200).
                            data(save).
                            build();
                }
            }

        } else if (isEmail) {
            Optional<User> userOptionalByEmail = userRepository.findByEmailAndDepartment_Id(email, departmentId);
            if (userOptionalByEmail.isPresent()) {
                User userByEmail = userOptionalByEmail.get();
                if (userByEmail.getPhone() == null) {
                    userByEmail.setPhone(phone);
                }
                userByEmail.setCount(userByEmail.getCount() + 1);
                User save = userRepository.save(userByEmail);
                request.setUser(save);
                requestRepository.save(request);
                return ApiResponse.<User>builder().
                        message("User updated!!!").
                        success(true).
                        status(200).
                        data(save).
                        build();
            }
        } else if (isPhone) {
            Optional<User> userOptionalByPhone = userRepository.findByPhoneAndDepartment_Id(phone, departmentId);
            if (userOptionalByPhone.isPresent()) {
                User userByPhone = userOptionalByPhone.get();
                if (userByPhone.getEmail() == null) {
                    userByPhone.setEmail(email);
                }
                userByPhone.setCount(userByPhone.getCount() + 1);
                User save = userRepository.save(userByPhone);
                request.setUser(save);
                requestRepository.save(request);
                return ApiResponse.<User>builder().
                        message("User updated!!!").
                        success(true).
                        status(200).
                        data(save).
                        build();
            }
        }

//        if (!isEmail && !isPhone)
//            return ApiResponse.<User>builder().
//                    message("Parameters are required!!!").
//                    success(false).
//                    status(400).
//                    build();
        if (isEmail && !email.contains("@"))
            return ApiResponse.<User>builder().
                    message("Email type is not supported!!!").
                    success(false).
                    status(400).
                    build();
        if (isPhone) {
            try {
                Long.parseLong(phone.substring(1));
            } catch (NumberFormatException e) {
                return ApiResponse.<User>builder().
                        message("Phone is not numeric!!!").
                        success(false).
                        status(400).
                        build();
            }
        }

        if (isPhone && !phone.startsWith("+"))
            return ApiResponse.<User>builder().
                    message("Phone is not numeric!!!").
                    success(false).
                    status(400).
                    build();


        User user = new User();
        if (isEmail) {
            user.setEmail(email);
        }
        if (isPhone) {
            user.setPhone(phone);
        }
        if (dto.getFullName() != null)
            user.setFullName(dto.getFullName());
        if (dto.getHowKnow() != null)
            user.setKnow(dto.getHowKnow());
        if (dto.getCompany() != null)
            user.setCompany(dto.getCompany());
        if (dto.getWorkType() != null)
            user.setWorkType(dto.getWorkType());
        Address address = new Address();
        Optional<Country> countryOptional = countryRepository.findById(dto.getCountryId());
        if (countryOptional.isEmpty()) {
            return ApiResponse.<User>builder().
                    message("Country not found!!!").
                    success(false).
                    status(400).
                    build();
        }
        address.setCountry(countryOptional.get());
        if (dto.getRegionId() != null) {
            Optional<Region> regionOptional = regionRepository.findById(dto.getRegionId());
            if (regionOptional.isEmpty() || !regionOptional.get().getCountry().getId().equals(countryOptional.get().getId())) {
                return ApiResponse.<User>builder().
                        message("Region not found!!!").
                        success(false).
                        status(400).
                        build();
            }
            address.setRegion(regionOptional.get());
        }
        user.setAddress(address);
        user.setCount(1);
        user.setRegisteredType(RegisteredType.WEBSITE);
//        user.setBot(botRepository.findById(botId).get());
//        user.setCompany(companyRepository.findById(companyId).get());
        user.setDepartment(departmentRepository.findById(departmentId).get());
        User save = userRepository.save(user);
        request.setUser(save);
        requestRepository.save(request);
        return ApiResponse.<User>builder().
                message("User saved!!!").
                success(true).
                status(201).
                data(save).
                build();
    }

    public ApiResponse<User> edit(Long id, UserDTO dto) {
        String email = dto.getEmail();
        String phone = dto.getPhone();
        boolean isEmail = email != null;
        boolean isPhone = phone != null;
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty() || !userOptional.get().getDepartment().getId().equals(departmentId)) {
            return ApiResponse.<User>builder().
                    message("User not found!!!").
                    success(false).
                    status(400).
                    build();
        }
        User user = userOptional.get();
        if (isEmail) {
            user.setEmail(email);
        }
        if (isPhone) {
            user.setPhone(phone);
        }
        user.setFullName(dto.getFullName());
        user.setKnow(dto.getHowKnow());
        user.setCompany(dto.getCompany());
        user.setWorkType(dto.getWorkType());
        if (dto.getCountryId() != null) {
            Address address = new Address();
            if (user.getAddress() != null) {
                address = user.getAddress();
            }
            Optional<Country> countryOptional = countryRepository.findById(dto.getCountryId());
            if (countryOptional.isEmpty()) {
                return ApiResponse.<User>builder().
                        message("Country not found!!!").
                        success(false).
                        status(400).
                        build();
            }
            address.setCountry(countryOptional.get());
            if (dto.getRegionId() != null) {
                Optional<Region> regionOptional = regionRepository.findById(dto.getRegionId());
                if (regionOptional.isEmpty() || !regionOptional.get().getCountry().getId().equals(countryOptional.get().getId())) {
                    return ApiResponse.<User>builder().
                            message("Region not found!!!").
                            success(false).
                            status(400).
                            build();
                }
                address.setRegion(regionOptional.get());
            }
            user.setAddress(address);
        }
        user.setCount(1);
        user.setRegisteredType(RegisteredType.WEBSITE);
//        user.setBot(botRepository.findById(botId).get());
//        user.setCompany(companyRepository.findById(companyId).get());
//        user.setDepartment(departmentRepository.findById(departmentId).get());
        User save = userRepository.save(user);
        return ApiResponse.<User>builder().
                message("User updated!!!").
                success(true).
                status(201).
                data(save).
                build();
    }

    public ApiResponse<User> getByQrCode(String code) {
        Optional<User> userOptional = userRepository.findByDepartment_IdAndQrcode(departmentId, UUID.fromString(code));
        if (userOptional.isEmpty()) {
            return ApiResponse.<User>builder().
                    message("User not found!!!").
                    status(400).
                    success(false).
                    build();
        }
        return ApiResponse.<User>builder().
                message("User here!!!").
                status(200).
                success(true).
                data(userOptional.get()).
                build();
    }
}
