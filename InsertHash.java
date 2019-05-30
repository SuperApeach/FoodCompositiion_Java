import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Iterator;
import java.util.List;

public class InsertHash {
	
	public void inputHash(HashMap<String, String[]> foodMap, String[] line) {

		String fCode = line[0];
	
		int sLen = line.length - 1;
		String[] fContext = new String[sLen];
	
		for(int i=0;i<sLen;i++) {
			fContext[i] = line[i+1];
		}
	
		foodMap.put(fCode, fContext);
	
	}
	
	public ArrayList<String[]> search(HashMap<String, String[]> foodMap, String name) {  //지금 이거 한줄만 불러냄. 여러줄 불러내야댐. -해결함.
		ArrayList<String[]> resList = new ArrayList<String[]>();
		
		Set foodKeys = foodMap.keySet();
		
		Iterator<String> exIter = foodKeys.iterator();
		
		
		int sLen = foodMap.get(exIter.next()).length;
		
		
		//int sLen = 25 - 1;  //나중에 arrayList로 수정  해야함.
		
		
		//String[] result = new String[sLen];// = new String[sLen];
		
		//Set foodKeys = foodMap.keySet();
		
		Iterator<String> iter = foodKeys.iterator();
		
		while(iter.hasNext()) {
			
			
			
			
			String fKey = iter.next();
			String temp2 = foodMap.get(fKey)[0];
			
			/*
			StringTokenizer st = new StringTokenizer(temp2," ");
			String temp = st.nextToken();
			String splStr = temp.split(",")[0];
			String resStr = splStr.split("\\(")[0];
			
			*/
			
			
			//if(name.equals(resStr)) {
			if(temp2.contains(name)) {
				//int sLen = foodMap.get(fKey).length - 1;
				
				String[] result = new String[sLen];
				
				for(int i=0;i<sLen;i++) {
					//fContext[i] = s1[i+1];
					result[i] = foodMap.get(fKey)[i];
				}
			
				resList.add(result);
			}
			
		}
		
		return resList;  //지금은 이름부터 받아옴.
		
	}
	
	public ArrayList<ArrayList<Double>> convertNum(ArrayList<String[]> inList1, double gram){  // 이름을 여기서 걸러야댐.
		ArrayList<ArrayList<Double>> result = new ArrayList<ArrayList<Double>>();	
		// 맨 뒤에 3개 인덱스 사용 안함   지워야댐
		int sLen = inList1.get(0).length - 1;
		
		
		ArrayList<String[]> inList = new ArrayList<String[]>();
		
		for(int j=0;j<inList1.size();j++) {
			String[] conv = new String[sLen];
			for(int i=0;i<sLen;i++) {
				conv[i] = inList1.get(j)[i+1];
			}
			inList.add(conv);
		}
		
		
		double d;
		
		for(int i=0;i<inList.size();i++) {
			ArrayList<Double> lineRes = new ArrayList<Double>();
			
			for(int j=0;j<inList.get(0).length - 3;j++) {
				if(inList.get(i)[j].equals("-")) {
					lineRes.add((double) 0);
				} else if(inList.get(i)[j].equals("tr")) {
					lineRes.add((double) 0);
				} else if(inList.get(i)[j].contains("-")) {
					String temp = inList.get(i)[j].substring(1, inList.get(i)[j].length());
					lineRes.add(Double.parseDouble(temp) * (gram/100));  //데이터 소스에 원래 중량이 100g 기준으로한것.
					
				} else {
					lineRes.add(Double.parseDouble(inList.get(i)[j]) * (gram/100)); // 마찬가지
					//result.get(i)[j] = Double.parseDouble(inList.get(i)[j]);
				}
			}
			
			result.add(lineRes);
			
			
		}
		return result;
	}
}
