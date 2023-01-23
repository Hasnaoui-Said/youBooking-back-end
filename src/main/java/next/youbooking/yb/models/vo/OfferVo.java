package next.youbooking.yb.models.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfferVo {
    private String title;
    private String description;
    private String hotel;
}
