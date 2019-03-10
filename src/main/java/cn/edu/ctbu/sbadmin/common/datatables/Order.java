package cn.edu.ctbu.sbadmin.common.datatables;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    /**
     * Column to which ordering should be applied. This is an index reference to the columns array of
     * information that is also submitted to the server.
     */
    @NotNull
    @Min(0)
    private Integer column;

    /**
     * Ordering direction for this column. It will be asc or desc to indicate ascending ordering or
     * descending ordering, respectively.
     */
    @NotNull
    @Pattern(regexp = "(desc|asc)")
    private String dir;

    /**
    public Order() {

    }

    public Order(Integer column,String dir) {
        this.column = column;
        this.dir = dir;
    }*/

    public Integer getColumn() {
        return column;
    }

    public void setColumn(Integer column) {
        this.column = column;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }
}