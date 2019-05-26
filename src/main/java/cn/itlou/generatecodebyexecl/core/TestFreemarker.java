package cn.itlou.generatecodebyexecl.core;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class TestFreemarker {

    public static void main(String[] args) throws Exception {

        // 拼装数据
        Map<String, Object> formBeanMap = new HashMap<String, Object>();
        List<ImportExcel> importExcelData = importExeclData();
        formBeanMap.put("className", "TestDTO");
        formBeanMap.put("authorName", "yuanyl");
        formBeanMap.put("createTime", new SimpleDateFormat("yyyy/MM/dd HH:mm").format(new Date()));
        List<Map<String, String>> paramsList = new ArrayList<Map<String, String>>();
        for (int i = 0; i < importExcelData.size(); i++) {
            Map<String, String> tmpParamMap = new HashMap<String, String>();
            tmpParamMap.put("resultType", importExcelData.get(i).getResultType());
            tmpParamMap.put("variableName", importExcelData.get(i).getVariableName());
            tmpParamMap.put("commentDetail", importExcelData.get(i).getCommentDetail());
            paramsList.add(tmpParamMap);
        }
        formBeanMap.put("params", paramsList);

        Configuration config = new Configuration();
        config.setDirectoryForTemplateLoading(new File("D:\\ideaworkspace\\generatecodebyexecl\\src\\main\\resources"));
        Template formBeanTemplate = config.getTemplate("generatedto.ftl", "UTF-8");

        Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("TestBeanForm.java"), "UTF-8"));
        formBeanTemplate.process(formBeanMap, out);
        out.flush();
        out.close();
    }

    public static List<ImportExcel> importExeclData() throws Exception{

        ImportParams params = new ImportParams();
        params.setStartSheetIndex(4);
        params.setSheetNum(1);
        ExcelImportResult<ImportExcel> importExecls = ExcelImportUtil.importExcelMore(new File("D:\\亚太决策引擎字段：三方数据字段_v1.03_20190520.xlsx"), ImportExcel.class, params);
        List<ImportExcel> resultList = importExecls.getList();
        return resultList;

    }

}
