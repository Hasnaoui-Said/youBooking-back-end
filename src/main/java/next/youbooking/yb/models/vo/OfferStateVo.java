package next.youbooking.yb.models.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfferStateVo {
    private List<UUID> uuidList;
    private String state;
}
