package adaptsort.web.server.service;

import adaptsort.web.server.dto.item.ItemDTO;
import adaptsort.web.server.dto.item.ItemResponseDTO;

import java.util.List;

public interface ItemService {
    ItemResponseDTO saveOrUpdateItem(ItemDTO itemDTO);
    List<ItemResponseDTO> findAllItems();
}
