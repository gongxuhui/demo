package com.gxh.demo.service;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.UUID;

public class ExcelService {
    /**
     * 获取excel文件中的数据
     * @throws FileNotFoundException
     */
    public void getExcelData() throws Exception {
       File file =  ResourceUtils.getFile("classpath:excel/(4)Monitor.xlsx");
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(file);
        Sheet sheet = xssfWorkbook.getSheetAt(0);
        int lastRowNum = sheet.getLastRowNum();
        System.out.println(sheet.getSheetName());

        System.out.println("开始行>>>>>"+sheet.getFirstRowNum());
        System.out.println("有效行>>>>>"+sheet.getLastRowNum());
        System.out.println("开始列>>>>>"+sheet.getRow(0).getFirstCellNum());
        System.out.println("有效列>>>>>"+sheet.getRow(0).getLastCellNum());
        int ll = 0;
        int kk = 0;
        for (int i =0;i <= lastRowNum;i++){
            Cell cell = sheet.getRow(i).getCell(3);
            if (cell == null){
                System.out.println("为空值请继续"+i);
                continue;
            }
            short shortColor = cell.getCellStyle().getFillForegroundColor();
            Color color = cell.getCellStyle().getFillForegroundColorColor();
            System.out.println(shortColor+"==========="+color);

            if (shortColor == 0){
                for (int j = 0; j<=11;j++){
                    Cell cellj = sheet.getRow(i).getCell(j);
                    System.out.println(cellj);
                    String uuid = UUID.randomUUID().toString().replaceAll("-", "");
                }
                ll++;
            }else {

                for (int j = 0; j<=11;j++){
                    Cell cellj = sheet.getRow(i).getCell(j);
                    System.out.println(cellj);
                }

                kk++;

            }
            System.out.println(i);
        }
        System.out.println("需要导入数据库的显示器的个数"+ll);
        System.out.println("不需要导入数据库的显示器的个数"+kk);
//        int i = 0;
//        int j = 0;
//        for (Row row : sheet){
//            for (Cell cell : row){
//                short shortColor = cell.getCellStyle().getFillForegroundColor();
//                Color color = cell.getCellStyle().getFillForegroundColorColor();
//                System.out.println(shortColor+"==========="+color);
//                if (shortColor == 0){
//                    cell.setCellType(CellType.STRING);
//                    String values =  cell.getStringCellValue();
//                    System.out.println(values);
//                    i++;
//                }else {
//                    j++;
//                    cell.setCellType(CellType.STRING);
//                    String values =  cell.getStringCellValue();
//                    System.out.println("只做打印不做导入"+values);
//                }
//
//            }
//            System.out.println("======================");
//        }



    }

    public static void main(String[] args) throws Exception {
        ExcelService excelService = new ExcelService();
        excelService.getExcelData();
    }
}
