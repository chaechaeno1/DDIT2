package List;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapType {

	public static void main(String[] args) {
		List<Map<String,Object>> userList = new ArrayList<>();
		
		Map<String,Object> user1 = new HashMap<>();
		user1.put("이름", "이진기");
		user1.put("행운의숫자", 2);
		user1.put("생년월일", "1996-06-12");
		user1.put("주문횟수", 12);
		user1.put("주문총금액", 500000);
		user1.put("포인트", 0);
		
		Map<String,Object> user2 = new HashMap<>();
		user1.put("이름", "김종현");
		user1.put("행운의숫자", 5);
		user1.put("생년월일", "2000-08-03");
		user1.put("주문횟수", 3);
		user1.put("주문총금액", 150000);
		user1.put("포인트", 0);
		
		Map<String,Object> user3 = new HashMap<>();
		user1.put("이름", "김기범");
		user1.put("행운의숫자", 9);
		user1.put("생년월일", "2006-10-03");
		user1.put("주문횟수", 7);
		user1.put("주문총금액", 100000);
		user1.put("포인트", 0);
		
		Map<String,Object> user4 = new HashMap<>();
		user1.put("이름", "최민호");
		user1.put("행운의숫자", 4);
		user1.put("생년월일", "1999-03-20");
		user1.put("주문횟수", 2);
		user1.put("주문총금액", 150000);
		user1.put("포인트", 0);
		
		Map<String,Object> user5 = new HashMap<>();
		user1.put("이름", "이태민");
		user1.put("행운의숫자", 8);
		user1.put("생년월일", "2008-10-04");
		user1.put("주문횟수", 10);
		user1.put("주문총금액", 40000);
		user1.put("포인트", 0);
		
		userList.add(user1);
		userList.add(user1);
		userList.add(user1);
		userList.add(user1);
		userList.add(user1);
		
	}

}
