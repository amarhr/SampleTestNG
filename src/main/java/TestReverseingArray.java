import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestReverseingArray {

	public static void main(String[] args) {
		String str = "I love my country love";
		
		String[] listOfWords = str.split(" ");
		Map<String, Integer> wordCountMap = new HashMap<String, Integer>();
		
		for(String word : listOfWords) {
			if(wordCountMap.containsKey(word)) {
				int count = wordCountMap.get(word); 
				wordCountMap.replace(word, ++count);
			}
			else
				wordCountMap.put(word, 1);
		}
		
		for(String key : wordCountMap.keySet()) {
			System.out.println("Word : Count " + key + " : " + wordCountMap.get(key));
		}
	}
}
