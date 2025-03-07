package adaptsort.web.server.service;

import adaptsort.web.server.dto.execution.ExecutionResponseDTO;
import adaptsort.web.server.dto.item.ItemDTO;

import java.util.List;

public interface ExecutionService {
    void saveOrUpdateExecution(ItemDTO itemDTO);
    List<ExecutionResponseDTO> findFiveLastExecutions();
}
