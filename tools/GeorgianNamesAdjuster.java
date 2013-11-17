package tools;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class GeorgianNamesAdjuster {
	public static Set<String> DEPRECATED_NAMES = new HashSet<String>(Arrays.asList(new String[] {"იოსები","მარიამი","ზურაბი","დავითი","ნიკოლოზი","თინათინი","რამაზი","მერაბი","ბაგრატი","არტემი","ლევანი","ქეთევანი","ვალერიანი","ელდარი","ვლადიმერი","მიხეილი"}));
	
	public static String adjust(String name) {
		return DEPRECATED_NAMES.contains(name) ? name.substring(0, name.length() - 1) : name;		
	}

}
