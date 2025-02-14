package com.bkav.device_mag_backend.Mapper;

import com.bkav.device_mag_backend.model.DTO.request.SaveAssignmentRequestDTO;
import com.bkav.device_mag_backend.model.DTO.response.AssignmentResponseDTO;
import com.bkav.device_mag_backend.model.entity.Assignment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AssignmentMapper {
    Assignment toAssignmentFromSaveAssignmentRequestDTO(SaveAssignmentRequestDTO saveAssignmentRequestDTO);
    AssignmentResponseDTO toAssignmentResponseDTO(Assignment assignment);
}
