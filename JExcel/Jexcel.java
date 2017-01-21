import java.io.FileOutputStream;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFCell;

public class JExcel {

    public static void main(String[] args) {

        try {
            String filename = "NewExcelFile.xls";
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("FirstSheet");

            HSSFRow rowhead = sheet.createRow((short) 0);
            rowhead.createCell(0).setCellValue("رقم الطالب");
            rowhead.createCell(1).setCellValue("الاسم");
            rowhead.createCell(2).setCellValue("العنوان");
            rowhead.createCell(3).setCellValue("الايميل");

            HSSFRow row = sheet.createRow((short) 1);
            row.createCell(0).setCellValue("1");
            row.createCell(1).setCellValue("خالد");
            row.createCell(2).setCellValue("الرياض");
            row.createCell(3).setCellValue("test@gmail.com");

            FileOutputStream fileOut = new FileOutputStream(filename);
            workbook.write(fileOut);
            fileOut.close();
            System.out.println("Your excel file has been generated!");

        } catch (Exception ex) {
            System.out.println(ex);

        }
    }
}
