package adaptsort.web.server.service.impl;

import adaptsort.web.server.dto.item.ItemDTO;
import adaptsort.web.server.dto.item.ItemResponseDTO;
import adaptsort.web.server.mapper.ItemMapper;
import adaptsort.web.server.persistence.entity.Item;
import adaptsort.web.server.persistence.repository.ItemRepository;
import adaptsort.web.server.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemMapper itemMapper;

    private final ItemRepository itemRepository;

    @Override
    public ItemResponseDTO saveOrUpdateItem(ItemDTO itemDTO) {
        Optional<Item> existingItem = itemRepository.findByName(itemDTO.getName());
        if (existingItem.isPresent()) {
            existingItem.get().setAmount(existingItem.get().getAmount() + 1);
            itemRepository.save(existingItem.get());
            return itemMapper.mapToDto(existingItem.get());
        } else {
            Item item = Item.builder()
                    .name(itemDTO.getName())
                    .amount(1)
                    .build();
            itemRepository.save(item);
            return itemMapper.mapToDto(item);
        }
    }

    @Override
    public List<ItemResponseDTO> findAllItems() {
        return itemMapper.mapToDto(itemRepository.findAll());
    }
}
