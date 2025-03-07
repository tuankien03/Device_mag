package com.bkav.device_mag_backend.repository.DAO;

import com.bkav.device_mag_backend.Mapper.AssignmentMapper;
import com.bkav.device_mag_backend.exception.BadRequestException;
import com.bkav.device_mag_backend.model.DTO.request.SaveAssignmentRequestDTO;
import com.bkav.device_mag_backend.model.DTO.response.AssignmentResponseDTO;
import com.bkav.device_mag_backend.model.DTO.response.PageResponse;
import com.bkav.device_mag_backend.model.entity.Assignment;
import com.bkav.device_mag_backend.repository.DAO.interfaces.IAssignmentDAO;
import com.bkav.device_mag_backend.repository.JpaRepository.AssignmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AssignmentDaoimpl implements IAssignmentDAO {
    private final AssignmentRepository assignmentRepository;
    private final AssignmentMapper assignmentMapper;

    @Override
    public AssignmentResponseDTO saveAssignment(SaveAssignmentRequestDTO saveAssignmentRequestDTO) {
        Assignment assignment =assignmentMapper.toAssignmentFromSaveAssignmentRequestDTO(saveAssignmentRequestDTO);
        assignment = assignmentRepository.save(assignment);
        return assignmentMapper.toAssignmentResponseDTO(assignment);
    }

    @Override
    public PageResponse<AssignmentResponseDTO> getAllAssignments(Pageable pageable) {
        Page<Assignment> pageData = assignmentRepository.findAll(pageable);
        return PageResponse.<AssignmentResponseDTO>builder()
                .data(pageData.getContent().stream().map(assignmentMapper::toAssignmentResponseDTO).collect(Collectors.toList()))
                .pageSize(pageData.getSize())
                .currentPage(pageData.getNumber())
                .totalPages(pageData.getTotalPages())
                .build();
    }


    @Override
    public AssignmentResponseDTO getAssignmentById(UUID assignmentId) {
        Assignment assignment = assignmentRepository.findById(assignmentId).orElse(null);
        if (assignment == null) {
            throw new BadRequestException("Assignment not found");
        }
        return  assignmentMapper.toAssignmentResponseDTO(assignment);
    }

    @Override
    public void deleteAssignmentById(UUID assignmentId) {
            assignmentRepository.deleteById(assignmentId);
    }
}
