import java.util.*;

public class Utils{
	/**
	 * To make user input easier.
	 * 
	 * @param words The words appear before input
	 * @return String
	 */
	public static String input(String words) {
		Scanner scanner = new Scanner(System.in);

		System.out.print(words);

		return scanner.nextLine();
	}

	/**
	 * To check the existence of element in an array
	 * 
	 * @param array The array to search
	 * @param v The element to check
	 * @return boolean
	 */
	public static <T> boolean arrContains(final T[] array, final T v) {
		if (v == null) {
			for (final T e : array)
				if (e == null)
					return true;
		} else {
			for (final T e : array)
				if (e == v || v.equals(e))
					return true;
		}

		return false;
	}
	
	public static int generateRandom(int start, int end, ArrayList<Integer> excludeRows) {
	    Random rand = new Random();
	    int range = end - start + 1;
	    int random = rand.nextInt(range) + 1;
	    
	    while(excludeRows.contains(random)) {
	        random = rand.nextInt(range) + 1;
	    }

	    return random;
	}
	
	public static <T> T[] arrayConcat(T[] first, T[] second) {
		T[] result = Arrays.copyOf(first, first.length + second.length);
		System.arraycopy(second, 0, result, first.length, second.length);
		
		return result;
	}
	
	public static <T, E> T getKeyByValue(Map<T, E> map, E value) {
	    for (Map.Entry<T, E> entry : map.entrySet()) {
	        if (Objects.equals(value, entry.getValue())) {
	            return entry.getKey();
	        }
	    }
	    return null;
	}
}