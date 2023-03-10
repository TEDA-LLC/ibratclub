package com.ibratclub.bot.service;

import com.ibratclub.bot.constant.ConstantEn;
import com.ibratclub.bot.constant.ConstantRu;
import com.ibratclub.bot.constant.ConstantUz;
import com.ibratclub.model.Category;
import com.ibratclub.model.Product;
import com.ibratclub.model.enums.Language;
import com.ibratclub.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Malikov Azizjon  *  17.01.2023  *  22:02   *  IbratClub
 */

@Service
@RequiredArgsConstructor
public class ButtonService {

    private final CategoryRepository categoryRepository;

    public ReplyKeyboardMarkup menuButton(Language language) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        replyKeyboardMarkup.setResizeKeyboard(true);

        List<KeyboardRow> rowList = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        KeyboardRow row1 = new KeyboardRow();
        KeyboardButton button = new KeyboardButton();
        KeyboardButton button1 = new KeyboardButton();
        KeyboardButton button2 = new KeyboardButton();
        KeyboardButton button3 = new KeyboardButton();
        KeyboardButton button4 = new KeyboardButton();
        KeyboardButton button5 = new KeyboardButton();

        if (language.equals(Language.UZB)) {
            button.setText(ConstantUz.SERVICES_BUTTON);
            button1.setText(ConstantUz.EVENTS_BUTTON);
            button5.setText(ConstantUz.VACANCY_BUTTON);

            button3.setText(ConstantUz.SETTINGS_BUTTON);
            button4.setText(ConstantUz.ABOUT_US_BUTTON);
            button2.setText(ConstantUz.MY_REQUESTS);
        } else if (language.equals(Language.ENG)) {
            button.setText(ConstantEn.SERVICES_BUTTON);
            button1.setText(ConstantEn.EVENTS_BUTTON);
            button5.setText(ConstantEn.VACANCY_BUTTON);

            button3.setText(ConstantEn.SETTINGS_BUTTON);
            button4.setText(ConstantEn.ABOUT_US_BUTTON);
            button2.setText(ConstantEn.MY_REQUESTS);
        } else {
            button.setText(ConstantRu.SERVICES_BUTTON);
            button1.setText(ConstantRu.EVENTS_BUTTON);
            button5.setText(ConstantRu.VACANCY_BUTTON);

            button3.setText(ConstantRu.SETTINGS_BUTTON);
            button4.setText(ConstantRu.ABOUT_US_BUTTON);
            button2.setText(ConstantRu.MY_REQUESTS);
        }
        row.add(button);
        row.add(button1);
        row.add(button5);
        row1.add(button3);
        row1.add(button4);
        row1.add(button2);
        rowList.add(row);
        rowList.add(row1);
        replyKeyboardMarkup.setKeyboard(rowList);

        return replyKeyboardMarkup;
    }

    public ReplyKeyboardMarkup settingsMenu(Language language) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        replyKeyboardMarkup.setResizeKeyboard(true);

        List<KeyboardRow> rowList = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        KeyboardRow row1 = new KeyboardRow();
        KeyboardButton languageButton = new KeyboardButton();
        KeyboardButton number = new KeyboardButton();
        KeyboardButton back = new KeyboardButton();

        number.setRequestContact(true);


        if (language.equals(Language.UZB)) {
            languageButton.setText(ConstantUz.LANGUAGE);
            number.setText(ConstantUz.PHONE);
            back.setText(ConstantUz.BACK);
        } else if (language.equals(Language.ENG)) {
            languageButton.setText(ConstantEn.LANGUAGE);
            number.setText(ConstantEn.PHONE);
            back.setText(ConstantEn.BACK);
        } else {
            languageButton.setText(ConstantRu.LANGUAGE);
            number.setText(ConstantRu.PHONE);
            back.setText(ConstantRu.BACK);
        }

        row.add(languageButton);
        row.add(number);
        row1.add(back);
        rowList.add(row);
        rowList.add(row1);
        replyKeyboardMarkup.setKeyboard(rowList);

        return replyKeyboardMarkup;
    }

    public ReplyKeyboardMarkup contact(Language language) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        replyKeyboardMarkup.setResizeKeyboard(true);

        List<KeyboardRow> rowList = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        KeyboardButton button = new KeyboardButton();
        button.setRequestContact(true);

        if (language.equals(Language.RUS)) {
            button.setText(ConstantRu.CONTACT_BUTTON);
        } else if (language.equals(Language.ENG)) {
            button.setText(ConstantEn.CONTACT_BUTTON);
        } else button.setText(ConstantUz.CONTACT_BUTTON);

        row.add(button);
        rowList.add(row);
        replyKeyboardMarkup.setKeyboard(rowList);

        return replyKeyboardMarkup;
    }

    public ReplyKeyboardMarkup language() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        replyKeyboardMarkup.setResizeKeyboard(true);

        List<KeyboardRow> rowList = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        KeyboardButton rus = new KeyboardButton();
        KeyboardButton eng = new KeyboardButton();
        KeyboardButton uzb = new KeyboardButton();
        rus.setText(ConstantRu.BUTTON);
        eng.setText(ConstantEn.BUTTON);
        uzb.setText(ConstantUz.BUTTON);
        row.add(rus);
        row.add(eng);
        row.add(uzb);
        rowList.add(row);
        replyKeyboardMarkup.setKeyboard(rowList);

        return replyKeyboardMarkup;
    }

    public ReplyKeyboardMarkup editLanguage() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        replyKeyboardMarkup.setResizeKeyboard(true);

        List<KeyboardRow> rowList = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        KeyboardButton rus = new KeyboardButton();
        KeyboardButton eng = new KeyboardButton();
        KeyboardButton uzb = new KeyboardButton();
        rus.setText(ConstantRu.LANGUAGE_ICON);
        eng.setText(ConstantEn.LANGUAGE_ICON);
        uzb.setText(ConstantUz.LANGUAGE_ICON);
        row.add(rus);
        row.add(eng);
        row.add(uzb);
        rowList.add(row);
        replyKeyboardMarkup.setKeyboard(rowList);

        return replyKeyboardMarkup;
    }

    public ReplyKeyboardMarkup categories(Language language) {

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        replyKeyboardMarkup.setResizeKeyboard(true);

        List<KeyboardRow> rowList = new ArrayList<>();
        KeyboardRow row1 = new KeyboardRow();
        KeyboardRow row2 = new KeyboardRow();

        KeyboardButton backButton = new KeyboardButton();

        for (Category category : categoryRepository.findAll()) {
            KeyboardButton button = new KeyboardButton();
            if (language.equals(Language.UZB)) {
                button.setText(category.getNameUz());
                backButton.setText(ConstantUz.BACK);
            } else if (language.equals(Language.ENG)) {
                button.setText(category.getNameEn());
                backButton.setText(ConstantEn.BACK);
            } else {
                button.setText(category.getNameRu());
                backButton.setText(ConstantRu.BACK);
            }
            row1.add(button);
        }
        row2.add(backButton);

        rowList.add(row1);
        rowList.add(row2);
        replyKeyboardMarkup.setKeyboard(rowList);

        return replyKeyboardMarkup;

    }

    public InlineKeyboardMarkup products(List<Product> products, Language language) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();

        for (Product product : products) {
            if (language.equals(Language.UZB)) {
                buttons.add(Collections.singletonList(
                        InlineKeyboardButton.builder().text(product.getNameUz()).callbackData(String.valueOf(product.getId())).build()));
            } else if (language.equals(Language.ENG)) {
                buttons.add(Collections.singletonList(
                        InlineKeyboardButton.builder().text(product.getNameEn()).callbackData(String.valueOf(product.getId())).build()));
            } else {
                buttons.add(Collections.singletonList(
                        InlineKeyboardButton.builder().text(product.getNameRu()).callbackData(String.valueOf(product.getId())).build()));
            }
        }
        if (language.equals(Language.UZB)) {
            buttons.add(Collections.singletonList(InlineKeyboardButton.builder().text(ConstantUz.BACK).callbackData("$back").build()));
        } else if (language.equals(Language.ENG)) {
            buttons.add(Collections.singletonList(InlineKeyboardButton.builder().text(ConstantEn.BACK).callbackData("$back").build()));
        } else {
            buttons.add(Collections.singletonList(InlineKeyboardButton.builder().text(ConstantRu.BACK).callbackData("$back").build()));
        }
        inlineKeyboardMarkup.setKeyboard(buttons);

        return inlineKeyboardMarkup;
    }

    public InlineKeyboardMarkup aboutProduct(Language language, Product product) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();

        if (language.equals(Language.UZB)) {
            buttons.add(Arrays.asList(
                    InlineKeyboardButton.builder().text(ConstantUz.IN_DETAIL).url("https://ibrat.teda.uz/").build(),
                    InlineKeyboardButton.builder().text(ConstantUz.REQUEST).callbackData("$request" + product.getId()).build()));
            buttons.add(Collections.singletonList(InlineKeyboardButton.builder().text(ConstantUz.BACK).callbackData("$back" + product.getCategory().getId()).build()));
        } else if (language.equals(Language.ENG)) {
            buttons.add(Arrays.asList(
                    InlineKeyboardButton.builder().text(ConstantEn.IN_DETAIL).url("https://ibrat.teda.uz/").build(),
                    InlineKeyboardButton.builder().text(ConstantEn.REQUEST).callbackData("$request" + product.getId()).build()));
            buttons.add(Collections.singletonList(InlineKeyboardButton.builder().text(ConstantEn.BACK).callbackData("$back" + product.getCategory().getId()).build()));
        } else {
            buttons.add(Arrays.asList(
                    InlineKeyboardButton.builder().text(ConstantRu.IN_DETAIL).url("https://ibrat.teda.uz/").build(),
                    InlineKeyboardButton.builder().text(ConstantRu.REQUEST).callbackData("$request" + product.getId()).build()));
            buttons.add(Collections.singletonList(InlineKeyboardButton.builder().text(ConstantRu.BACK).callbackData("$back" + product.getCategory().getId()).build()));
        }

        inlineKeyboardMarkup.setKeyboard(buttons);

        return inlineKeyboardMarkup;
    }

}
