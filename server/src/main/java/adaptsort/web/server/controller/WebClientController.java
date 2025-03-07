package adaptsort.web.server.controller;

import adaptsort.web.server.dto.execution.ExecutionResponseDTO;
import adaptsort.web.server.dto.item.ItemDTO;
import adaptsort.web.server.dto.item.ItemResponseDTO;
import adaptsort.web.server.service.ExecutionService;
import adaptsort.web.server.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class WebClientController {

    private final ItemService itemService;

    private final ExecutionService executionService;

    @PostMapping("/items")
    public ResponseEntity<ItemResponseDTO> createOrUpdateItem(@RequestBody ItemDTO itemDTO) {
        ItemResponseDTO itemResponseDTO = itemService.saveOrUpdateItem(itemDTO);
        executionService.saveOrUpdateExecution(itemDTO);
        return ResponseEntity.ok(itemResponseDTO);
    }

    @GetMapping("/items")
    public List<ItemResponseDTO> getAllItems() {
        return itemService.findAllItems();
    }

    @GetMapping("/executions")
    public List<ExecutionResponseDTO> getAllExecutions() {
        return executionService.findFiveLastExecutions();
    }
}