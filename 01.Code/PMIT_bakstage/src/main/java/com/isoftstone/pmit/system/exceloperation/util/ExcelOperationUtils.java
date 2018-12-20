package com.isoftstone.pmit.system.exceloperation.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class ExcelOperationUtils {

    /**
     * 导入EXCEL数据
     * @param
     * @return
     */
    public static Map<Integer, Object> importExcel(File file){

        Map<Integer, Object> result = new HashMap<Integer, Object>();

        //解析excel，获取上传的事件单
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        String fileName = file.getName();// 获取文件名
        try {
            if (!validateExcel(fileName)) {// 验证文件名是否合格
                result.put(0, "导入excel文件名称有误" + fileName);
                return result;
            }

            FileInputStream fis = new FileInputStream(file);
            if (fis.available() > (20 * 1024 * 1024)){
                result.put(0, "导入excel文件不能超过20MB");
                return result;
            }

            boolean isExcel2003 = true;// 根据文件名判断文件是2003版本还是2007版本
            if (isExcel2007(fileName)) {
                isExcel2003 = false;
            }

            InputStream inputStream = new FileInputStream(file);
            Workbook wb = null;
            if (isExcel2003) {// 当excel是2003时,创建excel2003
                wb = new HSSFWorkbook(inputStream);
            } else {// 当excel是2007时,创建excel2007
                wb = new XSSFWorkbook(inputStream);
            }

            list = readExcel(wb);// 读取Excel里面客户的信息

        } catch (Exception e) {
            e.printStackTrace();
            result.put(0, "获取excel文件数据出错：" + e.getMessage());
            return result;
        }

        result.put(1, list);
        return result;
    }

    private static List<Map<String, String>> readExcel(Workbook wb) {

        // 总行数
        int totalRows = 0;

        // 总列数
        int totalCols = 0;

        // 表格真实数据的起始行
        int realInfoStartRowIndex = 3;

        // 表头所在行
        int titleRowIndex = 2;

        // 得到第一个shell
        Sheet sheet = wb.getSheetAt(0);

        // 得到Excel的行数
        totalRows = sheet.getPhysicalNumberOfRows();

        // 得到Excel的列数
        Row titleRow = null;
        if (totalRows >realInfoStartRowIndex && sheet.getRow(titleRowIndex) != null) {
            titleRow = sheet.getRow(titleRowIndex);
            totalCols = titleRow.getPhysicalNumberOfCells();
        }

        // 得到表头名称的集合
        List<String> colsTitles = new ArrayList<String>();
        for (int col = 0; col < totalCols; col++) {
            colsTitles.add(titleRow.getCell(col).getStringCellValue());
        }

        List<Map<String, String>> infosList = new ArrayList<Map<String, String>>();

        // 循环Excel行数
        for (int rowIndex = realInfoStartRowIndex; rowIndex < totalRows; rowIndex++) {
            Row row = sheet.getRow(rowIndex);
            if (row == null) {
                continue;
            }

            // 循环Excel的列
            Map<String, String> rowMap = new HashMap<String, String>();
            for (int cellIndex = 0; cellIndex < totalCols; cellIndex++) {
                Cell cell = row.getCell(cellIndex);
                if (null != cell) {
                    DecimalFormat df = new DecimalFormat("0");//格式化number String字符串
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//日期格式化
                    String cellValue = "";
                    // 以下是判断数据的类型
                    switch (cell.getCellTypeEnum()) {
                        case STRING:
                            cellValue = cell.getRichStringCellValue().getString();
                            break;
                        case NUMERIC:
                            if ("m/d/yy".equals(cell.getCellStyle().getDataFormatString())){
                                cellValue = sdf.format(cell.getDateCellValue());
                            }else{
                                cellValue = df.format(cell.getNumericCellValue());
                            }
                            break;
                        case BOOLEAN:
                            cellValue = String.valueOf(cell.getBooleanCellValue());
                            break;
                        case BLANK:
                            cellValue = "";
                            break;
                        default:
                            cellValue = cell.toString();
                            break;
                    }
                    rowMap.put(colsTitles.get(cellIndex), cellValue);
                }
            }
            // 添加到list
            infosList.add(rowMap);
        }
        return infosList;
    }

    private static boolean validateExcel(String filePath) {
        if (StringUtils.isEmpty(filePath) || !(isExcel2003(filePath) || isExcel2007(filePath))) {
            return false;
        }
        return true;
    }

    // 是否是2003的excel，返回true是2003
    private static boolean isExcel2003(String filePath) {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }

    // 是否是2007的excel，返回true是2007
    private static boolean isExcel2007(String filePath) {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }



    public static void exportExcel(String fileName, String tableName, List<String> listTitle,
                                   List<Map<String, String>> list, HttpServletRequest request,
                                   HttpServletResponse response){

        try{
//            String templatePath =request.getServletContext().getRealPath("/") + "template.xlsx";
            String tempPath = "C:/Users/ptqua/Desktop/template.xlsx";
            InputStream inputStream = new FileInputStream(new File(tempPath));
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            writeExcel(tableName, workbook, listTitle, list);
            OutputStream os = response.getOutputStream();
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-disposition",
                    "attachment;filename=" + java.net.URLEncoder.encode(fileName, "UTF-8")
                            + " " + System.currentTimeMillis() + ".xlsx");
            workbook.write(os);
            os.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void writeExcel(String tableName, XSSFWorkbook workbook, List<String> listTitle,
                                   List<Map<String, String>> list){
        Sheet sheet = workbook.getSheetAt(0);
        Row tableTitleRow = sheet.getRow(1);
        Row rowTitle = sheet.getRow(2);
        Row rowTable = sheet.getRow(3);
        CellStyle tableTitleRowStyle = tableTitleRow.getCell(0).getCellStyle();
        CellStyle titleCellStyle = rowTitle.getCell(0).getCellStyle();
        CellStyle tableCellStyle = rowTable.getCell(0).getCellStyle();

        // 填写表头
        Cell tableTitleCell = tableTitleRow.createCell(0);
        tableTitleCell.setCellValue(tableName);
        tableTitleCell.setCellStyle(tableTitleRowStyle);

        // 填写表各列头
        Row titleRow = sheet.createRow(2);
        Cell titleCell = null;
        for (int titleCellIndex = 0; titleCellIndex < listTitle.size(); titleCellIndex++){
            titleCell = titleRow.createCell(titleCellIndex);
            titleCell.setCellValue(listTitle.get(titleCellIndex));
            titleCell.setCellStyle(titleCellStyle);
        }

        // 填写表格信息
        int startIndex = 3;
        Row currentRow = null;
        for (Map<String, String> rowInfo : list){
            currentRow = sheet.createRow(startIndex);
            Cell currentCell = null;
            for (int cellIndex = 0; cellIndex < listTitle.size(); cellIndex++){
                currentCell = currentRow.createCell(cellIndex);
                currentCell.setCellValue(rowInfo.get(listTitle.get(cellIndex)));
                currentCell.setCellStyle(tableCellStyle);
            }
            startIndex++;
        }

    }
}
