package adaptsort.web.server.service.impl;

import adaptsort.web.server.dto.execution.ExecutionResponseDTO;
import adaptsort.web.server.dto.item.ItemDTO;
import adaptsort.web.server.mapper.ExecutionMapper;
import adaptsort.web.server.persistence.entity.Execution;
import adaptsort.web.server.persistence.repository.ExecutionRepository;
import adaptsort.web.server.persistence.repository.ItemRepository;
import adaptsort.web.server.service.ExecutionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExecutionServiceImpl implements ExecutionService {

    private final ExecutionMapper executionMapper;

    private final ExecutionRepository executionRepository;

    private final ItemRepository itemRepository;

    @Override
    public void saveOrUpdateExecution(ItemDTO itemDTO) {
        Execution execution = Execution.builder()
                .item(itemRepository.findByName(itemDTO.getName()).orElseThrow(
                        () -> new RuntimeException("Item with name " + itemDTO.getName() + " not found"))
                )
                .startTime(LocalDateTime.now())
                .robot("Robot 1")
                .passed(1)
                .failed(0)
                .build();
        executionRepository.save(execution);
    }

    @Override
    public List<ExecutionResponseDTO> findFiveLastExecutions() {
        Page<Execution> page = executionRepository.findAll(
                PageRequest.of(0, 5, Sort.by(Sort.Direction.DESC, "startTime"))
        );
        return executionMapper.mapToDto(page.getContent());
    }
}
