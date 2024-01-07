package sec.common.core.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @description 
 * @author xiaobo
 * @date 2024/1/3 17:01
 */
@Data
public class QuoteDataVO  {
    private BigDecimal last;
    private BigDecimal preClose;
    private BigDecimal change;
    private BigDecimal changeRate;
    private BigDecimal close;
    private Long time;
    private BigDecimal open;
    private BigDecimal high;
    private BigDecimal low;
    private Long volume;
    private BigDecimal amount;

}
