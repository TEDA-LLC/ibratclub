package com.ibratclub.service;

import com.ibratclub.bot.TelegramBot;
import com.ibratclub.dto.ApiResponse;
import com.ibratclub.dto.RequestDTO;
import com.ibratclub.dto.ReviewDTO;
import com.ibratclub.model.*;
import com.ibratclub.model.enums.RegisteredType;
import com.ibratclub.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Malikov Azizjon  *  15.01.2023  *  18:04   *  IbratClub
 */
@Service
@RequiredArgsConstructor
public class SiteService {
//    @Value("${telegram.bot.id}")
//    private Long botId;
    @Value("${company.department.id}")
    private Long departmentId;
    private final RequestRepository requestRepository;
    private final SiteHistoryRepository siteHistoryRepository;
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;
    private final TelegramBot telegramBot;
    private final ProductRepository productRepository;
    private final QRCodeService qrCodeService;
    private final BotRepository botRepository;
    private final CompanyRepository companyRepository;
    private final DepartmentRepository departmentRepository;
    private final AttachmentRepository attachmentRepository;
    @SneakyThrows
    public ResponseEntity<?> getQrCodeByUser(Long userid, HttpServletResponse response) {
        Optional<User> userOptional = userRepository.findById(userid);
        if (userOptional.isEmpty() || !userOptional.get().getDepartment().getId().equals(departmentId)) {
            return ResponseEntity.badRequest().body("Request not found!!!");
        }
        response.setContentType("image/png");
        byte[] qrCodeBytes = qrCodeService.generateQRCode(userOptional.get().getQrcode().toString(), 500, 500);
        OutputStream outputStream = response.getOutputStream();
        outputStream.write(qrCodeBytes);
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf("image/png"))
                .contentLength(qrCodeBytes.length)
                .body(qrCodeBytes);
    }

    @SneakyThrows
    public ApiResponse<?> editPhoto(MultipartFile photo) {
        Department department = departmentRepository.findById(departmentId).get();
        Bot bot = department.getBot();
        Attachment attachment = new Attachment();
        attachment.setBytes(photo.getBytes());
        attachment.setOriginalName(photo.getOriginalFilename());
        attachment.setContentType(photo.getContentType());
        attachment.setSize(photo.getSize());
        bot.setLogo(attachmentRepository.save(attachment));
        botRepository.save(bot);
        return ApiResponse.builder().
                message("Success!!!").
                status(200).
                success(true).
                build();
    }

    public ApiResponse<?> add(RequestDTO dto) {
        boolean isEmail = dto.getEmail() == null || !dto.getEmail().equals("");
        boolean isPhone = dto.getPhone() == null || !dto.getPhone().equals("");
        Request request = new Request();
        request.setRegisteredType(RegisteredType.WEBSITE);
        request.setAboutProduct(dto.getAboutProduct());
        request.setDateTime(LocalDateTime.now());
        if(dto.getProductId() != null) {
            Optional<Product> productOptional = productRepository.findById(dto.getProductId());
            if (productOptional.isEmpty() || !productOptional.get().getCategory().getDepartment().getId().equals(departmentId)) {
                return ApiResponse.builder().
                        message("Not found").
                        success(false).
                        status(400).
                        build();
            }
            Product product = productOptional.get();
            request.setProduct(product);
        }
        if (dto.getCategory() != null) {
            request.setCategory(dto.getCategory());
        }
        else {
            request.setCategory("");
        }
        ApiResponse<User> response = login(dto.getEmail(), dto.getPhone());
        if (!response.isSuccess()){
            return response;
        }
        User user = response.getData();
//        List<Request> requestList = requestRepository.findAllByProductAndUser(product, user);
//        if (!requestList.isEmpty()){
//            return ApiResponse.builder().
//                            message("Request was added !").
//                            status(200).
//                            success(true).
//                            data(requestList.get(0)).
//                            build();
//        }

//        if (isPhone) {
//            Optional<User> userOptionalByPhone = userRepository.findByPhoneAndCompany_Id(dto.getPhone(), companyId);
//            if (userOptionalByPhone.isPresent()) {
//                User user = userOptionalByPhone.get();
//                if (dto.getName() != null && !dto.getName().equals(""))
//                    user.setFullName(dto.getName());
//                user.setLastOperationTime(LocalDateTime.now());
//                user.setCount(user.getCount() + 1);
//                user.setEmail(dto.getEmail());
//                User userSave = userRepository.save(user);
//                request.setUser(userSave);
//                List<Request> requestList = requestRepository.findAllByProductAndUser_Phone(product, user.getPhone());
//                if (!requestList.isEmpty()) {
//                    return ApiResponse.builder().
//                            message("Request was added !").
//                            status(200).
//                            success(true).
//                            data(requestList.get(0)).
//                            build();
//                }
//                Request save = requestRepository.save(request);
////                QrCode qrCode = new QrCode();
////                qrCode.setRequest(save);
////                qrCode.setUser(user);
////                qrCode.setProduct(productOptional.get());
////                qrCodeRepository.save(qrCode);
//                return ApiResponse.builder().
//                        message("Request saved!").
//                        status(201).
//                        success(true).
//                        data(save).
//                        build();
//            }
//        }
//
//        if (isEmail) {
//            Optional<User> userOptionalByEmail = userRepository.findByEmailAndCompany_Id(dto.getEmail(), companyId);
//            if (userOptionalByEmail.isPresent()) {
//                User user = userOptionalByEmail.get();
//                user.setLastOperationTime(LocalDateTime.now());
//                user.setCount(user.getCount() + 1);
//                user.setPhone(dto.getPhone());
//                User userSave = userRepository.save(user);
//                request.setUser(userSave);
//                List<Request> requestList = requestRepository.findAllByProductAndUser_Email(product, user.getEmail());
//                if (!requestList.isEmpty()) {
//                    return ApiResponse.builder().
//                            message("Request was added !").
//                            status(200).
//                            success(true).
//                            data(requestList.get(0)).
//                            build();
//                }
//                Request save = requestRepository.save(request);
//                return ApiResponse.builder().
//                        message("Request saved!").
//                        status(201).
//                        success(true).
//                        data(save).
//                        build();
//            }
//        }
//
//
//        User user = new User();
//        user.setFullName(dto.getName());
//        user.setPhone(dto.getPhone());
//        user.setCount(1);
//        user.setEmail(dto.getEmail());
//        user.setRegisteredTime(LocalDateTime.now());
//        user.setRegisteredType(RegisteredType.WEBSITE);
//        user.setBot(botRepository.findById(botId).get());
//        user.setCompany(companyRepository.findById(companyId).get());
//        User userSave = userRepository.save(user);
        request.setUser(user);
        Request save = requestRepository.save(request);
        return ApiResponse.builder().
                message("Request saved!").
                status(201).
                success(true).
                data(save).
                build();
    }

    public ApiResponse<?> historyWriter(SiteHistory history, String phone, String email) {
        boolean isEmail = email != null;
        boolean isPhone = phone != null;
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
                    history.setUser(save);
                    siteHistoryRepository.save(history);
                    return ApiResponse.builder().
                            message("History created !").
                            status(201).
                            success(true).
                            build();
                }
            }

        }
        if (isEmail) {
            Optional<User> userOptionalByEmail = userRepository.findByEmailAndDepartment_Id(email, departmentId);
            if (userOptionalByEmail.isPresent()) {
                User userByEmail = userOptionalByEmail.get();
                if (userByEmail.getPhone() == null) {
                    userByEmail.setPhone(phone);
                }
                userByEmail.setCount(userByEmail.getCount() + 1);
                User save = userRepository.save(userByEmail);
                history.setUser(save);
                siteHistoryRepository.save(history);
                return ApiResponse.builder().
                        message("History created !").
                        status(201).
                        success(true).
                        build();
            }
        }
        if (isPhone) {
            Optional<User> userOptionalByPhone = userRepository.findByPhoneAndDepartment_Id(phone, departmentId);
            if (userOptionalByPhone.isPresent()) {
                User userByPhone = userOptionalByPhone.get();
                if (userByPhone.getEmail() == null) {
                    userByPhone.setEmail(email);
                }
                userByPhone.setCount(userByPhone.getCount() + 1);
                User save = userRepository.save(userByPhone);
                history.setUser(save);
                siteHistoryRepository.save(history);
                return ApiResponse.builder().
                        message("History created !").
                        status(201).
                        success(true).
                        build();
            }
        }

        if (!isEmail && !isPhone)
            return ApiResponse.<User>builder().
                    message("Parameters are required!!!").
                    success(false).
                    status(400).
                    build();
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
        user.setCount(1);
        user.setRegisteredType(RegisteredType.WEBSITE);
//        user.setBot(botRepository.findById(botId).get());
//        user.setCompany(companyRepository.findById(companyId).get());
        user.setDepartment(departmentRepository.findById(departmentId).get());
        User save = userRepository.save(user);
        history.setUser(save);
        siteHistoryRepository.save(history);
        return ApiResponse.builder().
                message("History created !").
                status(201).
                success(true).
                build();
    }

    public ApiResponse<List<Request>> getRequest() {
        List<Request> requests = requestRepository.findAllByProduct_Category_Department_Id(departmentId);
        if (requests.isEmpty()) {
            return ApiResponse.<List<Request>>builder().
                    message("Requests are not found !").
                    status(400).
                    success(false).
                    build();
        }
        return ApiResponse.<List<Request>>builder().
                message("Requests here !").
                status(200).
                success(true).
                data(requests).
                build();
    }

    public ApiResponse<List<SiteHistory>> getSiteHistory() {
        List<SiteHistory> siteHistories = siteHistoryRepository.findAllByUser_Department_Id(departmentId);
        if (siteHistories.isEmpty()) {
            return ApiResponse.<List<SiteHistory>>builder().
                    message("History are not found !").
                    status(400).
                    success(false).
                    build();
        }
        return ApiResponse.<List<SiteHistory>>builder().
                message("History here !").
                status(200).
                success(true).
                data(siteHistories).
                build();
    }

    public ApiResponse<?> addReview(ReviewDTO dto) {
        Optional<User> userOptionalByPhone = userRepository.findByPhoneAndDepartment_Id(dto.getPhone(), departmentId);
        if (userOptionalByPhone.isEmpty()) {
            return ApiResponse.builder().
                    message("User not found !").
                    status(400).
                    success(false).
                    build();
        }

        User user = userOptionalByPhone.get();

        if (user.getEmail() == null)
            user.setEmail(dto.getEmail());

        Review review = new Review();
        review.setText(dto.getText());
        review.setUser(user);
        reviewRepository.save(review);

        return ApiResponse.builder().
                message("Review saved !").
                status(201).
                success(true).
                build();
    }

    public ApiResponse<?> editStatusReview(Long id) {
        Optional<Review> reviewOptional = reviewRepository.findById(id);
        if (reviewOptional.isEmpty()) {
            return ApiResponse.builder().
                    message("Review not found !").
                    status(400).
                    success(false).
                    build();
        }

        Review review = reviewOptional.get();

        review.setConfirmation(!review.isConfirmation());
        Review save = reviewRepository.save(review);
        return ApiResponse.builder().
                message("Review edited!").
                status(200).
                success(true).
                data(save).
                build();
    }

    public ApiResponse<Review> getReview(Long id) {
        Optional<Review> reviewOptional = reviewRepository.findById(id);
        if (reviewOptional.isPresent()) {
            return ApiResponse.<Review>builder().
                    message("Review saved !").
                    status(200).
                    success(true).
                    data(reviewOptional.get()).
                    build();
        }
        return ApiResponse.<Review>builder().
                message("Review not found !").
                status(400).
                success(false).
                build();
    }

    public ApiResponse<List<Review>> getReviews(Boolean active) {
        List<Review> reviewList = null;

        if (active == null)
            reviewList = reviewRepository.findAll();

        if (Boolean.FALSE.equals(active))
            reviewList = reviewRepository.findAllByConfirmationFalseAndUser_Department_Id(departmentId);

        if (Boolean.TRUE.equals(active))
            reviewList = reviewRepository.findAllByConfirmationTrueAndUser_Department_Id(departmentId);

        assert reviewList != null;
        reviewList.sort(Comparator.comparing(Review::getDateTime));

        if (reviewList.isEmpty())
            return ApiResponse.<List<Review>>builder().
                    message("Reviews aren't found !").
                    status(400).
                    success(false).
                    build();

        return ApiResponse.<List<Review>>builder().
                message("Reviews here!").
                status(200).
                success(true).
                data(reviewList).
                build();
    }

    public ApiResponse<List<Review>> getReviewForUsers() {

        List<Review> reviewList = reviewRepository.findAllByConfirmationTrueForUsers(departmentId);

        if (reviewList.isEmpty()) {
            return ApiResponse.<List<Review>>builder().
                    message("Reviews aren't found !").
                    status(400).
                    success(false).
                    build();
        }
        return ApiResponse.<List<Review>>builder().
                message("Reviews here!").
                status(200).
                success(true).
                data(reviewList).
                build();
    }

    public ApiResponse<Map<String, List<Request>>> getRequestsByUser(Long id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isEmpty()) {
            return ApiResponse.<Map<String, List<Request>>>builder().
                    message("User not found!!!").
                    success(false).
                    status(400).
                    build();
        }
        List<Request> requestList = requestRepository.findAllByUserAndProduct_Category_Department_Id(userOptional.get(), departmentId, Sort.by(Sort.Direction.ASC, "dateTime"));
        Map<String, List<Request>> collect = requestList.stream().collect(Collectors.groupingBy(Request::getCategory));
        return ApiResponse.<Map<String, List<Request>>>builder().
                message("Here!!!").
                success(true).
                status(200).
                data(collect).
                build();
    }

    @SneakyThrows
    public ApiResponse<?> sendMessage(Long id) {
        Optional<Request> requestOptional = requestRepository.findById(id);
        if (requestOptional.isEmpty()) {
            return ApiResponse.builder().
                    message("Request not found!!!").
                    success(false).
                    status(400).
                    build();
        }
        Request request = requestOptional.get();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("Sizning " + request.getId() + " raqamli murojatingizni " + request.getEmployee().getFullName() + " qabul qildi.");
        sendMessage.setChatId(request.getUser().getChatId());
        telegramBot.execute(sendMessage);

        return ApiResponse.builder().
                message("Sent!!!").
                success(true).
                status(200).
                build();
    }

    public ApiResponse<User> login(String email, String phone) {
        boolean isEmail = email != null;
        boolean isPhone = phone != null;
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
                    return ApiResponse.<User>builder().
                            message("User updated!!!").
                            success(true).
                            status(200).
                            data(save).
                            build();
                }
            }

        }
        if (isEmail) {
            Optional<User> userOptionalByEmail = userRepository.findByEmailAndDepartment_Id(email, departmentId);
            if (userOptionalByEmail.isPresent()) {
                User userByEmail = userOptionalByEmail.get();
                if (userByEmail.getPhone() == null) {
                    userByEmail.setPhone(phone);
                }
                userByEmail.setCount(userByEmail.getCount() + 1);
                User save = userRepository.save(userByEmail);
                return ApiResponse.<User>builder().
                        message("User updated!!!").
                        success(true).
                        status(200).
                        data(save).
                        build();
            }
        }
        if (isPhone) {
            Optional<User> userOptionalByPhone = userRepository.findByPhoneAndDepartment_Id(phone, departmentId);
            if (userOptionalByPhone.isPresent()) {
                User userByPhone = userOptionalByPhone.get();
                if (userByPhone.getEmail() == null) {
                    userByPhone.setEmail(email);
                }
                userByPhone.setCount(userByPhone.getCount() + 1);
                User save = userRepository.save(userByPhone);
                return ApiResponse.<User>builder().
                        message("User updated!!!").
                        success(true).
                        status(200).
                        data(save).
                        build();
            }
        }

        if (!isEmail && !isPhone)
            return ApiResponse.<User>builder().
                    message("Parameters are required!!!").
                    success(false).
                    status(400).
                    build();
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
        user.setCount(1);
        user.setRegisteredType(RegisteredType.WEBSITE);
//        user.setBot(botRepository.findById(botId).get());
//        user.setCompany(companyRepository.findById(companyId).get());
        user.setDepartment(departmentRepository.findById(departmentId).get());
        User save = userRepository.save(user);
        return ApiResponse.<User>builder().
                message("User saved!!!").
                success(true).
                status(201).
                data(save).
                build();

    }

    @SneakyThrows
    public ResponseEntity<?> getQrCode(Long requestId, HttpServletResponse response) {
        Optional<Request> requestOptional = requestRepository.findById(requestId);
        if (requestOptional.isEmpty() || !requestOptional.get().getProduct().getCategory().getDepartment().getId().equals(departmentId)) {
            return ResponseEntity.badRequest().body("Request not found!!!");
        }
        Request request = requestOptional.get();
        if (request.getUser() == null) {
            return ResponseEntity.badRequest().body("User not found!!!");
        }
        response.setContentType("image/png");
        byte[] qrCodeBytes = qrCodeService.generateQRCode(request.getUser().getQrcode().toString(), 500, 500);
        OutputStream outputStream = response.getOutputStream();
        outputStream.write(qrCodeBytes);
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf("image/png"))
                .contentLength(qrCodeBytes.length)
                .body(qrCodeBytes);
    }

    public ApiResponse<?> editRequest(Long id) {
        Optional<Request> requestOptional = requestRepository.findById(id);
        if (requestOptional.isEmpty() || !requestOptional.get().getUser().getDepartment().getId().equals(departmentId)) {
            return ApiResponse.builder().
                    message("Not found!!!").
                    status(400).
                    success(false).
                    build();
        }
        Request request = requestOptional.get();
        request.setArrivalTime(LocalDateTime.now());
        requestRepository.save(request);
        return ApiResponse.builder().
                message("Success!!!").
                status(200).
                success(true).
                build();
    }
}
