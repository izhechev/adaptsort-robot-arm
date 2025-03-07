package adaptsort.web.server.mapper;

import adaptsort.web.server.dto.execution.ExecutionResponseDTO;
import adaptsort.web.server.persistence.entity.Execution;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class ExecutionMapper extends AbstractMapper<Execution, ExecutionResponseDTO> {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy, HH:mm:ss");

    @Override
    public ExecutionResponseDTO mapToDto(Execution execution) {
        return ExecutionResponseDTO.builder()
                .id(execution.getId())
                .startTime(execution.getStartTime().format(DATE_TIME_FORMATTER))
                .itemName(execution.getItem().getName())
                .robot(execution.getRobot())
                .passed(execution.getPassed())
                .failed(execution.getFailed())
                .build();
    }
}
