package next.youbooking.yb.security.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class Jeton {
    private String successJeton;
    private String refreshJeton;
}
