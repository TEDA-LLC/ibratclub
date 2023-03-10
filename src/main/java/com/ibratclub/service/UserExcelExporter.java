package com.ibratclub.service;

import com.ibratclub.model.Request;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class UserExcelExporter {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Request> requestList;

    public UserExcelExporter(List<Request> requestList) {
        this.requestList = requestList;
        workbook = new XSSFWorkbook();
    }


    private void writeHeaderLine() {
        sheet = workbook.createSheet("Requests");

        Row row = sheet.createRow(1);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
        createCell(row, 0, " NO ", style);
        createCell(row, 1, "     USER NAME     ", style);
        createCell(row, 2, " USER PHONE NUMBER ", style);
        createCell(row, 3, "     EMAIL     ", style);
        createCell(row, 4, "     REGISTERED TIME     ", style);

    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value == null) {
            cell.setCellValue(" ");
        } else if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else if (value instanceof Date) {
            cell.setCellValue((Date) value);
        } else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    private void writeDataLines() {
        int rowCount = 2;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
        int count = 1;
        Row row1 = sheet.createRow(0);
        if (!requestList.isEmpty())
            createCell(row1, 0, "  EVENT NAME : " + requestList.get(0).getProduct().getNameEn(), style);
        for (Request request : requestList) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            createCell(row, columnCount++, count++, style);
            createCell(row, columnCount++, request.getUser().getFullName(), style);
            createCell(row, columnCount++, request.getUser().getPhone(), style);
            createCell(row, columnCount++, request.getUser().getEmail(), style);
            createCell(row, columnCount++, getTime(request.getDateTime()), style);

        }
    }

    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();

    }

    private String getTime(LocalDateTime date) {
        String year = String.valueOf(date.getYear()) + "-";
        year += checkingLength(date.getMonthValue()) + "-";
        year += checkingLength(date.getDayOfMonth()) + "   ";
        year += checkingLength(date.getHour()) + ":";
        year += checkingLength(date.getMinute());
        return year;
    }

    private String checkingLength(int num) {
        if (num < 10)
            return "0" + num;
        else
            return String.valueOf(num);
    }
}