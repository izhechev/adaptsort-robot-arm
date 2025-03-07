package adaptsort.web.server.mapper;

import adaptsort.web.server.dto.item.ItemDTO;
import adaptsort.web.server.dto.item.ItemResponseDTO;
import adaptsort.web.server.persistence.entity.Item;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper extends AbstractMapper<Item, ItemResponseDTO> {

    @Override
    public ItemResponseDTO mapToDto(Item sourceItem) {
        return ItemResponseDTO.builder()
                .id(sourceItem.getId())
                .name(sourceItem.getName())
                .amount(sourceItem.getAmount())
                .build();
    }
}
