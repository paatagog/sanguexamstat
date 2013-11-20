package ge.sangu.utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class GeorgianNamesAdjuster {
	public static Set<String> DEPRECATED_NAMES = new HashSet<String>(
			Arrays.asList(new String[] { "აბესალომი","ანზორი", "აპოლონი", "ალბერტი", "არჩილი","გურამი","გრიგოლი","ვახტანგი", "ვარლამი", "იასონი", "იოსები", "კახაბერი","მალხაზი","მარიამი", "მირიანი", "ზურაბი", "დავითი","დარეჯანი", "ნიაზი", "ნიკოლოზი",
					"ნუგზარი", "ნესტანი", "ნოდარი", "ოლეგი", "ოთარი", "ზაური","თამაზი","თემური", "თეიმურაზი", "თინათინი", "თენგიზი","რამაზი", "მერაბი","მევლუდი", "ბაგრატი", "არტემი", "ლევანი", "ქეთევანი", "ვალერიანი",
					"ეთერი", "ელდარი", "ელეფთერი", "ვლადიმერი", "მიხეილი", "სიმონი","ტარიელი","ტრისტანი","რევაზი", "ჯუმბერი","ჰამლეტი" }));
	
	public static String adjust(String name) {
		name = name.trim();
		return DEPRECATED_NAMES.contains(name) ? name.substring(0, name.length() - 1) : name;		
	}

}
