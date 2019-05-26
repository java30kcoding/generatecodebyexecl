package cn.itlou.generatecodebyexecl.core;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

@Data
public class ImportExcel {

    @Excel(name = "变量类型")
    private String resultType;

    @Excel(name = "变量名称")
    private String variableName;

    @Excel(name = "字段")
    private String commentDetail;

}
