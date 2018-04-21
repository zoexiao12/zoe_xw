package com.zoe.util;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.*;

/**
 * Created by gpdi on 2017/8/11.
 */
public class CreateWord {

    private POIFSFileSystem fs;
    private HSSFWorkbook wb;
    private HSSFSheet sheet;
    private HSSFRow row;
    private  int []  useTitlesIndex = {2,3,4,7,9,10,11};
    //{0:x,1:ID,2:源规格名称, 3:源表, 4: 源字段,5:源字段字典名称, 6:源字段字典,
    // 7:ITSP规格名称, 8:ITSP表, 9:ITSP表字段, 10: ITSP字段字典值名称, 11: ITSP字段字典ID }
    //F:\GIS属性映射（包含字典值）.xls
    //F:\\workspace\\myself\\GIS属性映射（包含字典值）.xls

    /**
     * 按照要求读取excel中的内容
     * @param is
     * @return
     * @throws Exception
     */
    public Map<String,List<String[]>> readExcelContent(InputStream is) throws Exception{
        Map<String,List<String[]>> map = new HashMap<>();
        if(is == null) {
            is = new FileInputStream("F:\\GIS属性映射（包含字典值）.xls");
        }
        fs = new POIFSFileSystem(is);
        wb = new HSSFWorkbook(fs);
        sheet = wb.getSheetAt(0);

//        //读取列头 ITSP规格名称
//        row = sheet.getRow(0);
//        int colNum = row.getPhysicalNumberOfCells();
//        String [] titles = new String [colNum];
//        for (int i = 0; i <colNum ; i++) {
//            titles[i] = getStringCellValue(row.getCell(i));
//        }


        //解析内容
        int rowNum = sheet.getLastRowNum();
        String mapkey = null;
        for (int i = 1; i < rowNum; i++) {
            row = sheet.getRow(i);
            mapkey =  getStringCellValue(row.getCell(8));
            List<String []>  list = map.get(mapkey);
            if(list == null) {
                list = new ArrayList<>();
                map.put(mapkey,list);
            }
            String [] strArray = getContent(row);
            list.add(strArray);
        }




        return clearMap(map);

//        return map;
    }


    public Map<String,List<String[]>> clearMap(Map<String,List<String[]>> map) {
        //清理后，ITSP表所对应的字段解析，没有重复字段了。
        Map<String,List<String[]>> newmap = new HashMap<>();
        List<String []> newlist =  null;
        List<String []> allList = null;

        //ITSP表字段作为key，将所有字段进行分组。
        Map<String,List<String[]>> cmap = null;
        List<String []> cList = null;


        Set<String> s0 = null;
        Set<String> s1 = null;
        Set<String> s2 = null;
        Set<String> s3 = null;
        Set<String> s5 = null;
        Set<String> s6 = null;
        String [] strArray = null;
        int i = 0;
        StringBuilder sb = null;

        //所有内容的大Map，需要清洗
        for (String key :map.keySet()){
            newlist =  new ArrayList<>();
            cmap = new HashMap<>();
            allList = map.get(key);

            if(key.equals("CM_DEVICE")) {
                System.out.println();
            }

            //1 根据ITSP表字段将内容分组，得到一个cmap集合； 因为ITSP表字段有很多重复的
            for (String [] c : allList) {
                String itspField = c[4];   //新资源表字段
                cList = cmap.get(itspField);
                if(cList == null) {
                    cList =  new ArrayList<>();
                    cmap.put(itspField,cList);
                }
                cList.add(c);
            }



//           private  int []  useTitlesIndex = {2,3,4,7,9,10,11};
//           //{0:x,1:ID,2:源规格名称, 3:源表, 4: 源字段,5:源字段字典名称, 6:源字段字典,
//           // 7:ITSP规格名称, 8:ITSP表, 9:ITSP表字段, 10: ITSP字段字典值名称, 11: ITSP字段字典ID }
            //根据ITSP表字段再组装，将重复内容合并一条。每个ckey代表一条
            for (String ckey :cmap.keySet()){
                //合并组装后的内容


                //需要合并的内容
                cList = cmap.get(ckey);

                //用Set来去掉重复的内容。
                s0 = new HashSet<>();
                s1 = new HashSet<>();
                s2 = new HashSet<>();
                s3 = new HashSet<>();
                s5 = new HashSet<>();
                s6 = new HashSet<>();

                for (String [] c : cList) {
                    s0.add(c[0]);s1.add(c[1]);s2.add(c[2]);s3.add(c[3]);s5.add(c[5]);s6.add(c[6]);
                }

                //重新组装
                strArray = new String[useTitlesIndex.length-1];
                //源规格名称
                sb = new StringBuilder();
                i = 0;
                for (String fkey:s0) {
                    if(i == 0) {
                        sb.append(fkey);
                    }else {
                        sb.append("\n" + fkey);
                    }
                    i++;
                }
                strArray[0] = sb.toString();

                //源表
                sb = new StringBuilder();
                i = 0;
                for (String fkey:s1) {
                    if(i == 0) {
                        sb.append(fkey);
                    }else {
                        sb.append("\n" + fkey);
                    }
                    i++;
                }
                strArray[1] = sb.toString();


                //源字段
                sb = new StringBuilder();
                i = 0;
                for (String fkey:s2) {
                    if(i == 0) {
                        sb.append(fkey);
                    }else {
                        sb.append("\n" + fkey);
                    }
                    i++;
                }
                strArray[2] = sb.toString();


                //ITSP规格名称
                sb = new StringBuilder();
                i = 0;
                for (String fkey:s3) {
                    if(i == 0) {
                        sb.append(fkey);
                    }else {
                        sb.append("\n" + fkey);
                    }
                    i++;
                }
                strArray[3] = sb.toString();




                //ITSP字段字典值名称,
                sb = new StringBuilder();
                i = 0;
                for (String fkey:s5) {
                    if(i == 0) {
                        sb.append(fkey);
                    }
                    sb.append("\n"+fkey);
                    i++;
                }
                String zd = sb.toString();


                // ITSP字段字典ID
                sb = new StringBuilder();
                i = 0;
                for (String fkey:s6) {
                    if(i ==0) {
                        sb.append(fkey);
                    }
                    sb.append("\n"+fkey);
                    i++;
                }
                //字段字典值和ID重合
                strArray[5] = zd + "\n" + sb.toString();

                strArray[4] = cList.get(0)[4];


                //合并后的新内容
                newlist.add(strArray);
            }
            newmap.put(key,newlist);
        }

        return  newmap;
    }


    /**
     * 生成world文档
     * @param map         生成word文档的内容
     * @throws Exception
     */
    public void  createWord(Map<String,List<String[]>> map) throws Exception{
        if(map == null) {
            return ;
        }
        XWPFDocument xdoc = new XWPFDocument();

        //标题
        XWPFParagraph titleMes = xdoc.createParagraph();
        titleMes.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun r1 = titleMes.createRun();
        r1.setText("新资源系统字典表");
        r1.setFontFamily("微软雅黑");
        r1.setFontSize(20);
        r1.setBold(true);

        //正文
        Set<String> keys = map.keySet();
        List<String []> list = null;
        XWPFParagraph label = null;
        XWPFRun labelRun = null;
        XWPFTable xTable = null;
        int num = 1;
        for (String key :keys) {
            //表名
            label = xdoc.createParagraph();
            labelRun = label.createRun();
            labelRun.setText(num+".  " +key);
            labelRun.setFontFamily("微软雅黑");
            labelRun.setFontSize(13);

            //表格内容
            list = map.get(key);

            //word中生成表格
            xTable = xdoc.createTable(list.size()+1,8);
            String bgColor="FFFFFF";
            CTTbl ttbl = xTable.getCTTbl();
            CTTblPr tblPr = ttbl.getTblPr() == null ? ttbl.addNewTblPr() : ttbl.getTblPr();
            CTTblWidth tblWidth = tblPr.isSetTblW() ? tblPr.getTblW() : tblPr.addNewTblW();
            tblWidth.setW(new BigInteger("8600"));
            tblWidth.setType(STTblWidth.DXA);

            //设置表头 {2,3,4,5,6,7,9,10,11};
            setCellText(xdoc, getCellHight(xTable, 0, 0), "序号",bgColor,500,true);
            setCellText(xdoc, getCellHight(xTable, 0, 1), "字段名",bgColor,500,true);
            setCellText(xdoc, getCellHight(xTable, 0, 2), "字段说明",bgColor,500,true);
            setCellText(xdoc, getCellHight(xTable, 0, 3), "规格",bgColor,500,true);
            setCellText(xdoc, getCellHight(xTable, 0, 4), "备注",bgColor,500,true);

            setCellText(xdoc, getCellHight(xTable, 0, 5), "old_字段",bgColor,500,true);
            setCellText(xdoc, getCellHight(xTable, 0, 6), "old_表",bgColor,500,true);
            setCellText(xdoc, getCellHight(xTable, 0, 7), "old_规格",bgColor,500,true);





            int i = 1;
            for (String [] array : list ) {
                setCellText(xdoc, getCellHight(xTable, i, 0),i+"",bgColor,500,false);
                setCellText(xdoc, getCellHight(xTable, i, 1), array[4],bgColor,500,false);
                setCellText(xdoc, getCellHight(xTable, i, 2), "",bgColor,500,false);
                setCellText(xdoc, getCellHight(xTable, i, 3), array[3],bgColor,500,false);
                setCellText(xdoc, getCellHight(xTable, i, 4), array[5],bgColor,500,false);
                setCellText(xdoc, getCellHight(xTable, i, 5), array[2],bgColor,500,false);
                setCellText(xdoc, getCellHight(xTable, i, 6), array[1],bgColor,500,false);
                setCellText(xdoc, getCellHight(xTable, i, 7), array[0],bgColor,500,false);
                i++;
            }

            num++;
        }

        FileOutputStream fos = new FileOutputStream("F:\\GIS属性映射（包含字典值）.xls");
        xdoc.write(fos);
        fos.close();

    }

    //设置表格高度
    private static XWPFTableCell getCellHight(XWPFTable xTable,int rowNomber,int cellNumber){
        XWPFTableRow row = null;
        row = xTable.getRow(rowNomber);
        row.setHeight(100);
        XWPFTableCell cell = null;
        cell = row.getCell(cellNumber);
        return cell;
    }

    private static void setCellText(XWPFDocument xDocument, XWPFTableCell cell,
                                    String text, String bgcolor, int width,boolean flag) {
        CTTc cttc = cell.getCTTc();
        CTTcPr cellPr = cttc.addNewTcPr();
        cellPr.addNewTcW().setW(BigInteger.valueOf(width));
        XWPFParagraph pIO =cell.addParagraph();
        cell.removeParagraph(0);
        XWPFRun rIO = pIO.createRun();
        rIO.setFontFamily("微软雅黑");
        rIO.setColor("000000");
        rIO.setFontSize(10);
        rIO.setText(text.toLowerCase());
        if(flag) {
            rIO.setBold(true);

        }
    }

    private void createXWTable(XWPFDocument xdoc, XWPFTable xTable) {

    }

    private String [] getContent(HSSFRow row) {
        String [] strArray = new String[useTitlesIndex.length];
        for (int i = 0; i < useTitlesIndex.length; i++) {
            strArray[i] =  getStringCellValue(row.getCell(useTitlesIndex[i]));
        }
        return strArray;
    }


    private String getStringCellValue(HSSFCell cell) {
        String strCell = "";
        if(cell == null) {
            return strCell;
        }
        switch (cell.getCellType()){
            case HSSFCell.CELL_TYPE_STRING :
                strCell = cell.getStringCellValue();
                break;
            case HSSFCell.CELL_TYPE_NUMERIC :
                strCell = String.valueOf(cell.getNumericCellValue());
                break;
            case HSSFCell.CELL_TYPE_BOOLEAN :
                strCell = String.valueOf(cell.getBooleanCellValue());
                break;
            case HSSFCell.CELL_TYPE_BLANK :
                strCell = "";
                break;
            default:
                strCell = "";
                break;
        }
        return strCell;
    }

    public static void main(String [] args) throws Exception{
        CreateWord cw = new CreateWord();

        Map<String,List<String[]>> map  = cw.readExcelContent(null);
        cw.createWord(map);
    }
}
