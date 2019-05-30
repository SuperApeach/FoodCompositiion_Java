import java.awt.Button;
import java.awt.Frame;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FCMain {

	public static void main(String[] args) throws IOException, EncryptedDocumentException, InvalidFormatException {
		// TODO Auto-generated method stub
		
		//GraphicUI gui = new GraphicUI();
		
		//gui.createFrame();
		
		
		
		
		
		
		HashMap<String,String[]> foodMap = new HashMap<String,String[]>();
		InsertHash foodHash = new InsertHash();
		
		
		//long midt=0;
		FileInputStream fis = new FileInputStream("food.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		int rowindex=0;
		int columnindex=0;
		
		XSSFSheet sheet = workbook.getSheetAt(0);
		
		//long startt = System.currentTimeMillis();
		
		int rows = sheet.getPhysicalNumberOfRows();
		//System.out.println(rows); 5785
		for(rowindex=5;rowindex<5515;rowindex=rowindex+2) {  //5515줄임.
			XSSFRow row = sheet.getRow(rowindex);
			if(row != null) {
				int cells = row.getPhysicalNumberOfCells();
				String[] lineValue = new String[cells]; //한줄 씩 넣을 String배열
				
				for(columnindex=0;columnindex<=cells;columnindex++) {
					XSSFCell cell = row.getCell(columnindex);
					String value = "";  // 셀 한칸 저장 (한줄 저장 X)
					if(cell == null) {
						continue;
					} else {
						switch(cell.getCellType()) {
						case XSSFCell.CELL_TYPE_FORMULA:
							value = cell.getCellFormula();
							break;
						case XSSFCell.CELL_TYPE_NUMERIC:
							value = cell.getNumericCellValue()+"";
							break;
						case XSSFCell.CELL_TYPE_STRING:
							value = cell.getStringCellValue()+"";
							break;
						case XSSFCell.CELL_TYPE_BLANK:
							value = cell.getBooleanCellValue()+"";
							break;
						case XSSFCell.CELL_TYPE_ERROR:
							value = cell.getErrorCellValue()+"";
							break;
						}
					}
					// 이 위치에서 한줄을 담는 스트링 배열에 한셀씩 넣어줌
					//if(rowindex == 15) {
					//	System.out.println("cell : "+value);
						//midt = System.currentTimeMillis();
					//}
					lineValue[columnindex] = value;
					
				}
				//이 위치에서 해쉬맵(foodMap)에 put해줌.   (해쉬 추가 함수 여기다가)
				foodHash.inputHash(foodMap, lineValue);
				
			}
			
		}
		/*
		ArrayList<String[]> searchRes = foodHash.search(foodMap, "귀리");
		ArrayList<ArrayList<Double>> numRes = foodHash.convertNum(searchRes,100);
		
		
		// String[][] data
		int rowL = searchRes.size();
		int colL = numRes.get(0).size() + 1;
		
		
		String[][] data = new String[rowL][colL];
		
		String[][] result = null;
		
		for(int i=0;i<rowL;i++) {
			data[i][0] = searchRes.get(i)[0];
			for(int j=0;j<colL-1;j++) {
				data[i][j+1] = numRes.get(i).get(j).toString();
			}
		}
		
		
		for(int i=0;i<data.length;i++) {
			for(int j=0;j<data[0].length;j++) {
				System.out.print(data[i][j]);
			}
			System.out.println();
		}
		
		
		System.out.println(rowL + "," + colL);
		*/
		/*
		
		Scanner s = new Scanner(System.in);
		
		String name;
		double gram;
		
		
			
			System.out.print("input name : ");
			name = s.nextLine();
			
			System.out.print("input gram : ");
			gram = s.nextDouble();
			
			
			ArrayList<String[]> searchRes = foodHash.search(foodMap, name);
			
			ArrayList<ArrayList<Double>> numRes = foodHash.convertNum(searchRes, gram);
			
			for(int j=0;j<numRes.size();j++) {
				for(int i=0;i<numRes.get(j).size();i++) {
					System.out.print(numRes.get(j).get(i) + ", ");
				}
				System.out.println();
			}
		*/
			
		//long endt = System.currentTimeMillis();
		//System.out.println("end");
		
		//System.out.println("middle : " + (midt - startt)/1000.0
		//		+ "end : " + (endt - startt)/1000.0);
		
		
		
		GraphicUI fgui = new GraphicUI(foodMap, foodHash);
		
		fgui.setVisible(true);
		
		
	}
	
	
	

}
