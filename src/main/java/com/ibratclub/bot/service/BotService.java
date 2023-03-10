package com.ibratclub.bot.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.ibratclub.bot.constant.ConstantEn;
import com.ibratclub.bot.constant.ConstantRu;
import com.ibratclub.bot.constant.ConstantUz;
import com.ibratclub.model.*;
import com.ibratclub.model.enums.Language;
import com.ibratclub.model.enums.RegisteredType;
import com.ibratclub.repository.*;
import com.ibratclub.service.QRCodeService;
import com.ibratclub.specification.*;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendLocation;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Malikov Azizjon  *  17.01.2023  *  22:01   *  IbratClub
 */

@Service
@RequiredArgsConstructor
public class BotService {
    @Value("${company.department.id}")
    private Long departmentId;
    private final UserHistoryRepository userHistoryRepository;
    private final ButtonService buttonService;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final WordHistoryRepository wordHistoryRepository;
    private final RequestRepository requestRepository;
    private final VacancyRepository vacancyRepository;
    private final BotRepository botRepository;
    private final DepartmentRepository departmentRepository;

    public SendMessage start(String chatId) {
        return SendMessage.builder()
                .text(ConstantRu.START +
                        "        \n" +
                        ConstantUz.START +
                        "        \n" +
                        ConstantEn.START)
                .chatId(chatId)
                .replyMarkup(buttonService.language())
                .build();
    }

    public SendMessage language(String chatId, Language language) {
        if (language.equals(Language.RUS)) {
            return SendMessage.builder()
                    .text(ConstantRu.CONTACT)
                    .chatId(chatId)
                    .replyMarkup(buttonService.contact(language))
                    .build();
        } else if (language.equals(Language.ENG)) {
            return SendMessage.builder()
                    .text(ConstantEn.CONTACT)
                    .chatId(chatId)
                    .replyMarkup(buttonService.contact(language))
                    .build();
        } else return SendMessage.builder()
                .text(ConstantUz.CONTACT)
                .chatId(chatId)
                .replyMarkup(buttonService.contact(language))
                .build();
    }

    public SendMessage menu(String chatId, Language language) {
        if (language.equals(Language.UZB)) {
            return SendMessage.builder()
                    .text(ConstantUz.MENU)
                    .chatId(chatId)
                    .replyMarkup(buttonService.menuButton(language))
                    .build();
        } else if (language.equals(Language.ENG)) {
            return SendMessage.builder()
                    .text(ConstantEn.MENU)
                    .chatId(chatId)
                    .replyMarkup(buttonService.menuButton(language))
                    .build();
        } else {
            return SendMessage.builder()
                    .text(ConstantRu.MENU)
                    .chatId(chatId)
                    .replyMarkup(buttonService.menuButton(language))
                    .build();
        }
    }

    public SendPhoto aboutUs(String chatId, Language language) {
        Optional<Department> departmentOptional = departmentRepository.findById(departmentId);
        Bot bot = departmentOptional.get().getBot();
//        if(bot.getLogo() == null ) {
//            SendMessage sendMessage = new SendMessage();
//            sendMessage.setParseMode("HTML");
//            if (language.equals(Language.UZB))
//                sendMessage.setText(ConstantUz.ABOUT_US);
//            else if (language.equals(Language.RUS))
//                sendMessage.setText(ConstantRu.ABOUT_US);
//            else sendMessage.setText(ConstantEn.ABOUT_US);
//
//            sendMessage.setChatId(chatId);
//
//            sendMessage.setReplyMarkup(buttonService.menuButton(language));
//            return sendMessage;
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setParseMode("HTML");
        if (language.equals(Language.UZB))
            sendPhoto.setCaption(ConstantUz.ABOUT_US);
        else if (language.equals(Language.RUS))
            sendPhoto.setCaption(ConstantRu.ABOUT_US);
        else sendPhoto.setCaption(ConstantEn.ABOUT_US);
        sendPhoto.setPhoto(new InputFile(new ByteArrayInputStream(bot.getLogo().getBytes()), bot.getLogo().getOriginalName()));
        sendPhoto.setChatId(chatId);

        sendPhoto.setReplyMarkup(buttonService.menuButton(language));
        return sendPhoto;

    }

    public SendMessage vacancy(String chatId, Language language) {
        if (language.equals(Language.UZB)) {
            return SendMessage.builder()
                    .text(ConstantUz.VACANCY)
                    .chatId(chatId)
                    .replyMarkup(buttonService.menuButton(language))
                    .build();
        } else if (language.equals(Language.ENG)) {
            return SendMessage.builder()
                    .text(ConstantEn.VACANCY)
                    .chatId(chatId)
                    .replyMarkup(buttonService.menuButton(language))
                    .build();
        } else {
            return SendMessage.builder()
                    .text(ConstantRu.VACANCY)
                    .chatId(chatId)
                    .replyMarkup(buttonService.menuButton(language))
                    .build();
        }
    }


    public SendMessage settings(String chatId, Language language) {
        if (language.equals(Language.UZB)) {
            return SendMessage.builder()
                    .text(ConstantUz.CHOOSE)
                    .chatId(chatId)
                    .replyMarkup(buttonService.settingsMenu(language))
                    .build();
        } else if (language.equals(Language.ENG)) {
            return SendMessage.builder()
                    .text(ConstantEn.CHOOSE)
                    .chatId(chatId)
                    .replyMarkup(buttonService.settingsMenu(language))
                    .build();
        } else {
            return SendMessage.builder()
                    .text(ConstantRu.CHOOSE)
                    .chatId(chatId)
                    .replyMarkup(buttonService.settingsMenu(language))
                    .build();
        }
    }

    public SendMessage edited(String chatId, Language language) {
        if (language.equals(Language.UZB)) {
            return SendMessage.builder()
                    .text(ConstantUz.EDITED)
                    .chatId(chatId)
                    .replyMarkup(buttonService.settingsMenu(language))
                    .build();
        } else if (language.equals(Language.ENG)) {
            return SendMessage.builder()
                    .text(ConstantEn.EDITED)
                    .chatId(chatId)
                    .replyMarkup(buttonService.settingsMenu(language))
                    .build();
        } else return SendMessage.builder()
                .text(ConstantRu.EDITED)
                .chatId(chatId)
                .replyMarkup(buttonService.settingsMenu(language))
                .build();
    }


    public SendMessage editLanguage(String chatId, Language language) {
        if (language.equals(Language.UZB)) {
            return SendMessage.builder()
                    .text(ConstantUz.CHOOSE_LANGUAGE)
                    .chatId(chatId)
                    .replyMarkup(buttonService.editLanguage())
                    .build();

        } else if (language.equals(Language.ENG)) {
            return SendMessage.builder()
                    .text(ConstantEn.CHOOSE_LANGUAGE)
                    .chatId(chatId)
                    .replyMarkup(buttonService.editLanguage())
                    .build();
        } else {
            return SendMessage.builder()
                    .text(ConstantRu.CHOOSE_LANGUAGE)
                    .chatId(chatId)
                    .replyMarkup(buttonService.editLanguage())
                    .build();
        }
    }

    public SendMessage ok(String chatId, Language language) {
        return SendMessage.builder()
                .text(ConstantUz.OK)
                .chatId(chatId)
                .replyMarkup(buttonService.menuButton(language))
                .build();
    }

    public SendMessage services(String chatId, Language language) {
        if (language.equals(Language.UZB)) {
            return SendMessage.builder()
                    .text(ConstantUz.CHOOSE)
                    .chatId(chatId)
                    .replyMarkup(buttonService.categories(language))
                    .build();
        } else if (language.equals(Language.ENG)) {
            return SendMessage.builder()
                    .text(ConstantEn.CHOOSE)
                    .chatId(chatId)
                    .replyMarkup(buttonService.categories(language))
                    .build();
        } else {
            return SendMessage.builder()
                    .text(ConstantRu.CHOOSE)
                    .chatId(chatId)
                    .replyMarkup(buttonService.categories(language))
                    .build();
        }
    }

    public Long getCategoryIdByName(String message) {
        SearchRequest searchRequest = new SearchRequest();
        List<FilterRequest> filterRequests = new ArrayList<>();
        filterRequests.add(FilterRequest.builder().
                fieldType(FieldType.STRING).
                operator(Operator.EQUAL).
                value(message).
                key("nameUz").
                build());
        filterRequests.add(FilterRequest.builder().
                fieldType(FieldType.STRING).
                operator(Operator.EQUAL).
                value(message).
                or(true).
                key("nameRu").
                build());
        filterRequests.add(FilterRequest.builder().
                fieldType(FieldType.STRING).
                operator(Operator.EQUAL).
                value(message).
                or(true).
                key("nameEn").
                build());
        searchRequest.setFilters(filterRequests);
        Page<Category> category = categoryRepository.findAll(new EntitySpecification<Category>(
                SearchRequest.builder()
                        .filters(List.of(FilterRequest.builder().
                                fieldType(FieldType.LONG).
                                operator(Operator.EQUAL).
                                value(departmentId).
                                key("department.id")
                                .build())
                        ).build()).and(new EntitySpecification<>(searchRequest)), PageRequest.of(0, 1));
        if (category.isEmpty()) return null;

        else return category.getContent().get(0).getId();
    }

    public SendMessage products(Long categoryId, Language language, String chatId) {
        InlineKeyboardMarkup inlineKeyboardMarkup = buttonService.products(productRepository.findAllByCategoryId(categoryId), language);

        if (language.equals(Language.UZB)) {
            return SendMessage.builder()
                    .text(ConstantUz.CHOOSE)
                    .chatId(chatId)
                    .replyMarkup(inlineKeyboardMarkup)
                    .build();
        } else if (language.equals(Language.ENG)) {
            return SendMessage.builder()
                    .text(ConstantEn.CHOOSE)
                    .chatId(chatId)
                    .replyMarkup(inlineKeyboardMarkup)
                    .build();
        } else {
            return SendMessage.builder()
                    .text(ConstantRu.CHOOSE)
                    .chatId(chatId)
                    .replyMarkup(inlineKeyboardMarkup)
                    .build();
        }
    }

    public SendPhoto getProduct(Update update, User currentUser) {
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(String.valueOf(update.getCallbackQuery().getMessage().getChatId()));

        Optional<Product> productOptional = productRepository.findById(Long.valueOf(update.getCallbackQuery().getData()));
        Product product = productOptional.get();

        StringBuilder builder = new StringBuilder();
        if (currentUser.getLanguage().equals(Language.UZB)) {
            builder.append(product.getNameUz())
                    .append("\n")
                    .append(product.getDescriptionUz())
                    .append("\n")
                    .append(product.getPrice())
                    .append("+")
                    .append("\n");
        } else if (currentUser.getLanguage().equals(Language.ENG)) {
            builder.append(product.getNameEn())
                    .append("\n")
                    .append(product.getDescriptionEn())
                    .append("\n")
                    .append(product.getPrice())
                    .append("+")
                    .append("\n");
        } else {
            builder.append(product.getNameRu())
                    .append("\n")
                    .append(product.getDescriptionRu())
                    .append("\n")
                    .append(product.getPrice())
                    .append("+")
                    .append("\n");
        }
        UserHistory userHistory = new UserHistory();
        userHistory.setUser(currentUser);
        userHistory.setProduct(product);
        userHistoryRepository.save(userHistory);
        if (product.getAddress() != null) {
            if (product.getFromDate() != null && product.getToDate() != null) {
                builder.append(product.getFromDate().toLocalDate().toString());
                builder.append(" - ");
                builder.append(product.getToDate().toLocalDate().toString());
                builder.append("\n");
            }
            builder.append("Address: ");
            builder.append(product.getAddress().getDistrict().getName());
            builder.append(" ");
            builder.append(product.getAddress().getStreetHome());
        }
        sendPhoto.setCaption(String.valueOf(builder));

        InputFile inputFile = new InputFile(new ByteArrayInputStream(product.getAttachment().getBytes()), product.getAttachment().getOriginalName());
        sendPhoto.setPhoto(inputFile);
        sendPhoto.setReplyMarkup(buttonService.aboutProduct(currentUser.getLanguage(), product));
        return sendPhoto;
    }

    public DeleteMessage deleteMessage(String chatId, Integer messageId) {
        return DeleteMessage.builder()
                .chatId(chatId)
                .messageId(messageId)
                .build();
    }

    public SendMessage backToProducts(Update update, String chatId, Language language) {
        String data = update.getCallbackQuery().getData();

        Long categoryId = Long.valueOf(data.substring(5));

        List<Product> productList = productRepository.findAllByCategoryId(categoryId);

        InlineKeyboardMarkup inlineKeyboardMarkup = buttonService.products(productList, language);

        if (language.equals(Language.UZB)) {
            return SendMessage.builder()
                    .text(ConstantUz.CHOOSE)
                    .chatId(chatId)
                    .replyMarkup(inlineKeyboardMarkup)
                    .build();
        } else if (language.equals(Language.ENG)) {
            return SendMessage.builder()
                    .text(ConstantEn.CHOOSE)
                    .chatId(chatId)
                    .replyMarkup(inlineKeyboardMarkup)
                    .build();
        } else {
            return SendMessage.builder()
                    .text(ConstantRu.CHOOSE)
                    .chatId(chatId)
                    .replyMarkup(inlineKeyboardMarkup)
                    .build();
        }
    }

    public void storyWriter(User user, Message message) {
        WordHistory wordsHistory = WordHistory.builder().
                user(user).
                word(message.getText()).
                department(departmentRepository.findById(departmentId).get()).
                build();
        wordHistoryRepository.save(wordsHistory);
    }

    @SneakyThrows
    public SendPhoto saveRequest(Update update, User currentUser) {
        String data = update.getCallbackQuery().getData();

        Long productId = Long.valueOf(data.substring(8));
        Optional<Product> productOptional = productRepository.findById(productId);
        Product product = productOptional.get();
        List<Request> requestList = requestRepository.findAllByProductAndUser_Phone(product, currentUser.getPhone());
        Request request;
        if (requestList.isEmpty()) {
            request = Request.builder().
                    aboutProduct(product.getNameEn()).
                    registeredType(RegisteredType.BOT).
                    dateTime(LocalDateTime.now()).
                    user(currentUser).
                    product(product).
                    build();
        } else {
            request = requestList.get(0);
        }
        requestRepository.save(request);
        SendPhoto sendPhoto = new SendPhoto();
//        ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
//        bb.putLong(request.getUser().getQrcode().getMostSignificantBits());
//        bb.putLong(request.getUser().getQrcode().getLeastSignificantBits());
//        InputFile inputFile = new InputFile(new ByteArrayInputStream(bb.array()).toString());
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(request.getUser().getQrcode().toString(), BarcodeFormat.QR_CODE, 500, 500);

        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
        byte[] pngData = pngOutputStream.toByteArray();
        InputFile inputFile = new InputFile(new ByteArrayInputStream(pngData), "QrCode.png");
        sendPhoto.setPhoto(inputFile);
        sendPhoto.setChatId(update.getCallbackQuery().getMessage().getChatId());
        if (currentUser.getLanguage().equals(Language.UZB)) {
//            return SendMessage.builder()
//                    .text(ConstantUz.RESPONSE_FOR_REQUEST + "\n" +
//                            "Sizning murojaat raqamingiz: " + savedRequest.getId())
//                    .chatId(update.getCallbackQuery().getMessage().getChatId())
//                    .build();
            sendPhoto.setCaption(ConstantUz.RESPONSE_FOR_REQUEST);
        } else if (currentUser.getLanguage().equals(Language.ENG)) {
//            return SendMessage.builder()
//                    .text(ConstantEn.RESPONSE_FOR_REQUEST + "\n" +
//                            "Your reference number: " + savedRequest.getId())
//                    .chatId(update.getCallbackQuery().getMessage().getChatId())
//                    .build();
            sendPhoto.setCaption(ConstantEn.RESPONSE_FOR_REQUEST);
        } else {
//            return SendMessage.builder()
//                    .text(ConstantRu.RESPONSE_FOR_REQUEST + "\n" +
//                            "?????? ?????????? ????????????: " + savedRequest.getId())
//                    .chatId(update.getCallbackQuery().getMessage().getChatId())
//                    .build();
            sendPhoto.setCaption(ConstantRu.RESPONSE_FOR_REQUEST);
        }
        return sendPhoto;
    }

    public SendMessage myRequests(String chatId, User currentUser) {
        List<Request> requests = requestRepository.findAllByUser(currentUser);

        StringBuilder stringBuilder = new StringBuilder();

        if (requests.isEmpty()) {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(chatId);
            if (currentUser.getLanguage().equals(Language.ENG))
                sendMessage.setText(ConstantEn.MY_REQUESTS_EMPTY);
            else if (currentUser.getLanguage().equals(Language.RUS))
                sendMessage.setText(ConstantRu.MY_REQUESTS_EMPTY);
            else
                sendMessage.setText(ConstantUz.MY_REQUESTS_EMPTY);

            return sendMessage;
        }
        for (Request request : requests) {
            if (currentUser.getLanguage().equals(Language.UZB)) {
                stringBuilder.append("Id: ")
                        .append(request.getId())
                        .append("\n")
                        .append("Sana: ")
                        .append(request.getDateTime() == null ? "null" : request.getDateTime().toLocalDate().toString())
                        .append("\n")
                        .append("Xizmat haqida: ")
                        .append(request.getAboutProduct())
                        .append("\n")
                        .append("\n");
            } else if (currentUser.getLanguage().equals(Language.ENG)) {
                stringBuilder.append("Id: ")
                        .append(request.getId())
                        .append("\n")
                        .append("Date: ")
                        .append(request.getDateTime() == null ? "null" : request.getDateTime().toLocalDate().toString())
                        .append("\n")
                        .append("About Service: ")
                        .append(request.getAboutProduct())
                        .append("\n")
                        .append("\n");
            } else {
                stringBuilder.append("Id: ")
                        .append(request.getId())
                        .append("\n")
                        .append("????????: ")
                        .append(request.getDateTime() == null ? "null" : request.getDateTime().toLocalDate().toString())
                        .append("\n")
                        .append("?? ??????????????: ")
                        .append(request.getAboutProduct())
                        .append("\n")
                        .append("\n");
            }
        }
        return SendMessage.builder()
                .text(String.valueOf(stringBuilder))
                .chatId(chatId)
                .build();
    }

    public SendMessage vacancies(String chatId, User currentUser) {

        List<Vacancy> vacancies = vacancyRepository.findAllByActiveTrueAndDepartment_Id(departmentId);

        if (vacancies.isEmpty()) {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(chatId);
            if (currentUser.getLanguage().equals(Language.ENG))
                sendMessage.setText(ConstantEn.VACANCY);
            else if (currentUser.getLanguage().equals(Language.RUS))
                sendMessage.setText(ConstantRu.VACANCY);
            else
                sendMessage.setText(ConstantUz.VACANCY);

            return sendMessage;
        }

        StringBuilder stringBuilder = new StringBuilder();

        for (Vacancy vacancy : vacancies) {
            if (currentUser.getLanguage().equals(Language.UZB)) {
                stringBuilder.append("Id: ")
                        .append(vacancy.getId())
                        .append("\n")
                        .append("Vakansiya nomi: ")
                        .append(vacancy.getName())
                        .append("\n")
                        .append("Haqida: ")
                        .append(vacancy.getDescription())
                        .append("\n")
                        .append("\n");
            } else if (currentUser.getLanguage().equals(Language.ENG)) {
                stringBuilder.append("Id: ")
                        .append(vacancy.getId())
                        .append("\n")
                        .append("Vacancy name: ")
                        .append(vacancy.getName())
                        .append("\n")
                        .append("About: ")
                        .append(vacancy.getDescription())
                        .append("\n")
                        .append("\n");
            } else {
                stringBuilder.append("????: ")
                        .append(vacancy.getId())
                        .append("\n")
                        .append("?????? ????????????????: ")
                        .append(vacancy.getName())
                        .append("\n")
                        .append("?? ????????????????: ")
                        .append(vacancy.getDescription())
                        .append("\n")
                        .append("\n");
            }
        }
        return SendMessage.builder()
                .text(String.valueOf(stringBuilder))
                .chatId(chatId)
                .build();
    }

    public SendLocation getLocation(Update update, User currentUser) {
        Long productId = Long.valueOf(update.getCallbackQuery().getData().substring(8));
        Optional<Product> productOptional = productRepository.findById(Long.valueOf(productId));
        Product product = productOptional.get();
        SendLocation sendLocation = new SendLocation();
        sendLocation.setChatId(String.valueOf(update.getCallbackQuery().getMessage().getChatId()));
        sendLocation.setLatitude(product.getAddress().getLatitude());
        sendLocation.setLongitude(product.getAddress().getLongitude());
        return sendLocation;
    }
}
