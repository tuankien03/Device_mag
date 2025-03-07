package com.bkav.device_mag_backend.controller;
import Constant.CodeStatus;
import com.bkav.device_mag_backend.model.DTO.request.SaveAssignmentRequestDTO;
import com.bkav.device_mag_backend.model.DTO.response.ApiResponse;
import com.bkav.device_mag_backend.model.DTO.response.AssignmentResponseDTO;
import com.bkav.device_mag_backend.model.DTO.response.PageResponse;
import com.bkav.device_mag_backend.service.AssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/assignment")
@RequiredArgsConstructor
public class AssignmentController {
    private final AssignmentService assignmentService;

    @GetMapping
    public ApiResponse<PageResponse<AssignmentResponseDTO>> getAllAssignments(
            @RequestParam(value = "page" , required = false, defaultValue = "1") int page,
            @RequestParam(value="size", required = false, defaultValue = "12") int size
    ) {
        Sort sort = Sort.by("assignedAt").descending();
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        return new ApiResponse<>(CodeStatus.SUCCESS, CodeStatus.SUCCESS_TEXT,assignmentService.getAllAssignments(pageable));
    }

    @PostMapping
    public ApiResponse<AssignmentResponseDTO> addAssignment(@RequestBody SaveAssignmentRequestDTO saveAssignmentRequestDTO) {
        return new ApiResponse<>(CodeStatus.CREATED, CodeStatus.CREATED_TEXT,  assignmentService.addAssignment(saveAssignmentRequestDTO));
    }

    @PutMapping("{id}")
    public ApiResponse<AssignmentResponseDTO> updateAssignment(@RequestBody SaveAssignmentRequestDTO saveAssignmentRequestDTO, @PathVariable UUID id) {
        saveAssignmentRequestDTO.setId(id);
        return new ApiResponse<>(CodeStatus.CREATED, CodeStatus.CREATED_TEXT,  assignmentService.addAssignment(saveAssignmentRequestDTO));
    }

    @PutMapping("{id}/return")
    public ApiResponse<String> returnDevice(@PathVariable UUID id) {
        assignmentService.returnDevice(id);
        return new ApiResponse<>(CodeStatus.SUCCESS, CodeStatus.SUCCESS_TEXT,  "Thiết bị đã được trả!!");
    }

    @PutMapping("{id}/confirm")
    public ApiResponse<String> confirmReturnedDevice(@PathVariable UUID id) {
        assignmentService.confirmReturnedDevice(id);
        return new ApiResponse<>(CodeStatus.SUCCESS, CodeStatus.SUCCESS_TEXT,  "Ghi nhận thiết bị đã được trả!!");
    }

    @DeleteMapping("{id}")
    public ApiResponse<String> deleteAssignment(@PathVariable UUID id) {
        assignmentService.deleteAssignmentById(id);
        return new ApiResponse<>(CodeStatus.CREATED, CodeStatus.CREATED_TEXT, "assignment deleted successfully!!");
    }
}
