package com.gxh.demo.service;

import com.alibaba.druid.sql.visitor.functions.If;
import com.gxh.demo.dao.AssetDao;
import com.gxh.demo.entity.Asset;
import com.gxh.demo.utils.QrCodeUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.UUID;
@Service
public class ExcelService {

    @Autowired
    private AssetDao assetDao;

    /**
     * 获取excel文件中的数据
     * @throws FileNotFoundException
     */
    public void getExcelData() throws Exception {
       File file =  ResourceUtils.getFile("classpath:excel/Desktop.xlsx");
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(file);
        Sheet sheet = xssfWorkbook.getSheetAt(0);
        int lastRowNum = sheet.getLastRowNum();
        System.out.println(sheet.getSheetName());
        System.out.println("开始行>>>>>"+sheet.getFirstRowNum());
        System.out.println("有效行>>>>>"+sheet.getLastRowNum());
        System.out.println("开始列>>>>>"+sheet.getRow(0).getFirstCellNum());
        System.out.println("有效列>>>>>"+sheet.getRow(0).getLastCellNum());
        Asset asset = new Asset();
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
            //System.out.println(shortColor+"==========="+color);
            if (shortColor == 0){
                String uuid = UUID.randomUUID().toString().replaceAll("-", "");
                if(sheet.getRow(i).getCell(0) == null){
                    continue;
                }
                if(sheet.getRow(i).getCell(1) == null){
                    continue;
                }
                if(sheet.getRow(i).getCell(2) == null){
                    continue;
                }
                if(sheet.getRow(i).getCell(3) == null){
                    continue;
                }
                if(sheet.getRow(i).getCell(4) == null){
                    continue;
                }
                if(sheet.getRow(i).getCell(5) == null){
                    continue;
                }if(sheet.getRow(i).getCell(6) == null){
                    continue;
                }
                if(sheet.getRow(i).getCell(7) == null){
                    continue;
                }
                if(sheet.getRow(i).getCell(8) == null){
                    continue;
                }
                if(sheet.getRow(i).getCell(9) == null){
                    continue;
                }
                if(sheet.getRow(i).getCell(10) == null){
                    continue;
                }

                sheet.getRow(i).getCell(0).setCellType(CellType.STRING);
                sheet.getRow(i).getCell(1).setCellType(CellType.STRING);
                sheet.getRow(i).getCell(2).setCellType(CellType.STRING);
                sheet.getRow(i).getCell(3).setCellType(CellType.STRING);
                sheet.getRow(i).getCell(4).setCellType(CellType.STRING);
                sheet.getRow(i).getCell(5).setCellType(CellType.STRING);
                sheet.getRow(i).getCell(6).setCellType(CellType.STRING);
                sheet.getRow(i).getCell(7).setCellType(CellType.STRING);
                sheet.getRow(i).getCell(8).setCellType(CellType.STRING);
                sheet.getRow(i).getCell(9).setCellType(CellType.STRING);
                sheet.getRow(i).getCell(10).setCellType(CellType.STRING);
                String beijingCode = sheet.getRow(i).getCell(0).getStringCellValue();
                String financeCode = sheet.getRow(i).getCell(1).getStringCellValue();
                String taggerNumber = sheet.getRow(i).getCell(2).getStringCellValue();
                String serialNumber = sheet.getRow(i).getCell(3).getStringCellValue();
                String purchaseYear = sheet.getRow(i).getCell(4).getStringCellValue();
                String department = sheet.getRow(i).getCell(5).getStringCellValue();
                String companySource = sheet.getRow(i).getCell(6).getStringCellValue();
                String assetOrigin = sheet.getRow(i).getCell(7).getStringCellValue();
                String assetType = sheet.getRow(i).getCell(8).getStringCellValue();
                String comBrand = sheet.getRow(i).getCell(9).getStringCellValue();
                String comModel = sheet.getRow(i).getCell(10).getStringCellValue();
                asset.setAssetUuid(uuid);
                asset.setAssetType("cpu");
                //0表示没有被使用
                asset.setAssetStatus(0);
                asset.setTaggerNumber(taggerNumber);
                asset.setBeijingCode(beijingCode);
                asset.setFinanceCode(financeCode);
                asset.setSerialNumber(serialNumber);
                asset.setComputerModel(comBrand+comModel);
                asset.setAssetPeople("Christian Abades");
                asset.setAssetOrigin(assetOrigin);
                if(beijingCode.equals("")||financeCode.equals("")){
                    asset.setAssetClass(1);
                    System.out.println("没有固定资产编码的");
                }else {
                    asset.setAssetClass(0);
                }
                asset.setAssetYear(purchaseYear);
                Thread.sleep(1000);
                String url = QrCodeUtil.BASE_URL+serialNumber;
                String fileName = QrCodeUtil.createQrCode(url);
                asset.setAssetQrcodeAddress(fileName);
                asset.setTest("cpu");
                assetDao.insert(asset);
                ll++;
                System.out.println(ll+">>>"+uuid+">>>"+beijingCode +">>>"+financeCode +">>>"+taggerNumber +">>>"+serialNumber +">>>"+purchaseYear+">>>"+ department+">>>"+ companySource +">>>"+ assetOrigin+">>>"+ assetType +">>>"+comBrand+">>>"+ comModel);
            }else {

                for (int j = 0; j<=11;j++){
                    Cell cellj = sheet.getRow(i).getCell(j);
                    //System.out.println(cellj);
                }

                kk++;

            }
            //System.out.println(i);
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
