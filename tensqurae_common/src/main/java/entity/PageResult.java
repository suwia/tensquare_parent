package entity;

import java.util.List;

/**
 * @Author: sirc_hzr
 * @Date: 2019/3/4 13:56
 * @ClassName: PageResult
 * @Version: 1.0
 * @Description:分页结果类
 */
public class PageResult <T> {
    private Long total;
    private List<T> row;

    public PageResult(Long total, List<T> row) {
        this.total = total;
        this.row = row;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getRow() {
        return row;
    }

    public void setRow(List<T> row) {
        this.row = row;
    }
}
