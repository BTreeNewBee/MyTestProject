package iguigui.javaBean.easyExcelTest;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

public class Demo01 {

    public static void main(String[] args) {



    }

}

@Data
class ExcelData{

    @ExcelProperty("标题1")
    private String string;
    @ExcelProperty("标题2")
    private String doubleData;

}
