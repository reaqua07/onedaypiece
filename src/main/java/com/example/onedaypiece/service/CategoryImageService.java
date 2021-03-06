package com.example.onedaypiece.service;

import com.example.onedaypiece.exception.ApiRequestException;
import com.example.onedaypiece.web.domain.categoryImage.CategoryImage;
import com.example.onedaypiece.web.domain.categoryImage.CategoryImageRepository;
import com.example.onedaypiece.web.domain.challenge.CategoryName;
import com.example.onedaypiece.web.dto.request.categoryImage.CategoryImageRequestDto;
import com.example.onedaypiece.web.dto.response.category.CategoryImageResponseDto;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CategoryImageService {

    private final CategoryImageRepository categoryImageRepository;

    @Transactional
    public void postCategoryImage(CategoryImageRequestDto requestDto) {
        if (!categoryImageRepository.existsByCategoryImageUrl(requestDto.getCategoryImageUrl())) {
            categoryImageRepository.save(new CategoryImage(requestDto));
        } else throw new ApiRequestException("이미 존재하는 이미지입니다.");
    }

    public CategoryImageResponseDto getCategoryImage(CategoryName categoryName) {
        CategoryImageResponseDto categoryImageResponseDto = new CategoryImageResponseDto();
        categoryImageRepository.findAllByCategoryName(categoryName).forEach(
                value -> categoryImageResponseDto.addCategoryImageUrl(value.getCategoryImageUrl()));
        return categoryImageResponseDto;
    }

    @Transactional
    public void deleteCategoryImage(String imgUrl) {
        categoryImageRepository.deleteByCategoryImageUrl(imgUrl);
    }
}
