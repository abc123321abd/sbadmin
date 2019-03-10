package cn.edu.ctbu.sbadmin.common.utils;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * https://blog.csdn.net/SmileorSilence/article/details/80535853
 *
 */
@Slf4j
public class EasyPoiUtil {

    /**
     * 创建Excel
     */
    public static void exportExcel(String title, String sheetName, boolean isCreateHeader,
                                   Class<?> pojoClass, List<?> data,
                                   HttpServletResponse response, String fileName) throws Exception {

        ExportParams exportParams = new ExportParams(title, sheetName);
        exportParams.setCreateHeadRows(isCreateHeader);

        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, pojoClass, data);
        if (workbook == null) throw new Exception("Excel文件是空的，无法生成");

        try {
            downloadExcel(response, fileName, workbook);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 下载Excel
     */
    private static void downloadExcel(HttpServletResponse response, String fileName, Workbook workbook) throws Exception {
        // 设置响应实体的编码格式
        response.setCharacterEncoding("UTF-8");
        // 通知浏览器使用什么软件可以打开此文件
        response.setHeader("content-Type", "application/vnd.ms-excel");
        // 下载文件的默认名称
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xls");

        try {
            workbook.write(response.getOutputStream());
        } catch (Exception e) {
            throw e ;
        }
    }



}
