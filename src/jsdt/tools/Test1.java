package jsdt.tools;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

public class Test1 {
	public static void main(String[] args) throws Exception {
//		try {
//			int blocksize = 2048;
//			File mxfile = new File("c:/MX000001.BIN");
//			BufferedInputStream bufferedInputStream = new BufferedInputStream(
//					new FileInputStream(mxfile));
//			int bytesRead = 0;
//			// int block = new
//			// BigDecimal(bufferedInputStream.available()/blocksize).setScale(0,
//			// BigDecimal.ROUND_HALF_UP).intValue();
//			// System.out.println("block = "+block);
//			byte[] bytes = new byte[blocksize];
//			StringBuffer sb = new StringBuffer();
//			while ((bytesRead = bufferedInputStream.read(bytes)) != -1) {
//				System.out.println(Util.byte2hex(bytes));
//				sb.append(Util.byte2hex(bytes));
//			}
//			bufferedInputStream.close();
//			String data = sb.toString();
//			// System.out.println("data length = "+data.length());
////			System.out.println("data = " + data);
//		} catch (Exception e) {
//
//		}
//		System.out.println(Long.valueOf("FA56EA00",16));
		HashMap<String,String> map = new HashMap<String,String>();
		HashSet<String> keyset = (HashSet<String>) map.keySet();
		int size = map.size();
		int count = 0;
		Iterator<String> it = keyset.iterator();
		StringBuffer sb = new StringBuffer();
		while(it.hasNext())
		{
			String key=it.next();
			String value = map.get(key);
			sb.append(key).append(":").append(value);
			if((count++) != size-1 )
			{
				sb.append(";");
			}
			
		}
	}
}
