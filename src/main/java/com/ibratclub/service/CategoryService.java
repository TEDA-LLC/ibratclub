package com.ibratclub.service;

import com.ibratclub.dto.ApiResponse;
import com.ibratclub.model.Category;
import com.ibratclub.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Malikov Azizjon  *  16.01.2023  *  17:52   *  IbratClub
 */
@Service
@RequiredArgsConstructor
public class CategoryService {
    @Value("${telegram.bot.id}")
    private Long botId;
    private final CategoryRepository categoryRepository;

    public ApiResponse<List<Category>> getAll() {
        List<Category> categories = categoryRepository.findAllByBot_Id(botId);
        return ApiResponse.<List<Category>>builder().
                message("Here").
                status(200).
                success(true).
                data(categories).
                build();
    }

}
