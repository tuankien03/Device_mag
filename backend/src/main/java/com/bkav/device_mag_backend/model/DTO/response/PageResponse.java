package com.bkav.device_mag_backend.model.DTO.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PageResponse<T> {
    int totalPages;
    int currentPage;
    int pageSize;
    int totalElements;
    List<T> data;
}
