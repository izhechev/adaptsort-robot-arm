package adaptsort.web.server.dto.execution;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExecutionResponseDTO {
    private Long id;
    private String startTime;
    private String itemName;
    private String robot;
    private Integer passed;
    private Integer failed;
}
