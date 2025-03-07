package adaptsort.web.server.dto.item;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemResponseDTO {
    private Long id;
    private String name;
    private Integer amount;
}
