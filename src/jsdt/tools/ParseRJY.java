package jsdt.tools;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class ParseRJY {

	private static String rjypath = "F:/MyProjects/Sinopec/SinopeMX/23800/RJY00001.BIN";

	// private static int blocksize = 1024;

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		paresRjy(new File("f:/RJY00001.BIN"));
		System.out
				.println("共耗时：" + (System.currentTimeMillis() - start) / 1000);
	}

	private static ArrayList<String> splitRjy(String data) {
		String pduCodeString = data;
		int splitNum = 384;
		int arrayNum = 0; // 初始化arrayNum，计算得到的分组个数
		int yuShu = data.length() % splitNum; // 是否正好除尽
		if (yuShu == 0) // 正好除尽
		{
			arrayNum = pduCodeString.length() / splitNum;
		} else // 未除尽
		{
			arrayNum = pduCodeString.length() / splitNum + 1;
		}
		System.out.println(arrayNum);
		ArrayList<String> rjyList = new ArrayList<String>(arrayNum);
		for (int i = 0; i < arrayNum; i++) {
			rjyList.add(pduCodeString.substring(0, splitNum));
			pduCodeString = pduCodeString.substring(splitNum);
		}
		return rjyList;
	}
	
	// private static ArrayList<HashMap<String,String>>
	// paresRjy(ArrayList<String> rjydata)
	public static ArrayList<HashMap<String, Object>> paresRjy(File rjyfile) {
		DataInputStream fromFile = null;
		ArrayList<HashMap<String, Object>> result = new ArrayList<HashMap<String, Object>>();

		try {
			BufferedInputStream bufferedInputStream = new BufferedInputStream(
					new FileInputStream(rjyfile));
			System.out.println(rjyfile.length());
//			System.out.println(bufferedInputStream.available());
			int bytesRead = 0;
			byte[] bytes = new byte[384];
			StringBuffer sb = new StringBuffer();
			while ((bytesRead = bufferedInputStream.read(bytes)) != -1) {
				sb.append(Util.byte2hex(bytes));
			}
			String data = sb.toString();
			// System.out.println("data length = "+data.length());
			System.out.println("data = " + data);
			ArrayList<String> rjy_data = splitRjy(data);
			System.out.println(rjy_data);
			System.out.println(rjy_data.size());

			Iterator<String> it = rjy_data.iterator();
			while (it.hasNext()) {
				HashMap<String, Object> rjy = new HashMap<String, Object>();
				String tmp = it.next();
				if(!"000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000".equals(tmp))
				{
				String date = tmp.substring(0, 8);
				rjy.put("dqrq", date);
				System.out.println("当前日期：" + date);
				String zcpfs = Integer.valueOf(tmp.substring(8, 12), 16)
						.toString();
				rjy.put("zcpfs", zcpfs);
				System.out.println("当天正常发票份数：" + zcpfs);
				String tpfs = Integer.valueOf(tmp.substring(12, 16), 16)
						.toString();
				rjy.put("tpfs", tpfs);
				System.out.println("当天退票份数：" + tpfs);
				String fpfs = Integer.valueOf(tmp.substring(16, 20), 16)
						.toString();
				rjy.put("fpfs", fpfs);
				System.out.println("当天废票份数：" + fpfs);
				String szsm = tmp.substring(20, 32);
				rjy.put("szsm", szsm);
				System.out.println("税种税目索引号：" + szsm);

				String normal_sum = tmp.substring(32, 80);
				System.out.println("当天正常发票分类累计金额：" + normal_sum);
				rjy.put("index1_zcp",
						Double.valueOf(Integer.valueOf(normal_sum.substring(0, 8), 16)/100));
				System.out.println("税种税目1正常发票金额："
						+ Integer.valueOf(normal_sum.substring(0, 8), 16)
								.toString());
				rjy.put("index2_zcp",
						Double.valueOf(Integer.valueOf(normal_sum.substring(8, 16), 16)/100));
				System.out.println("税种税目2正常发票金额："
						+ Integer.valueOf(normal_sum.substring(8, 16), 16)/100);
				rjy.put("index3_zcp",
						Double.valueOf(Integer.valueOf(normal_sum.substring(16, 24), 16)/100));
				System.out.println("税种税目3正常发票金额："
						+ Integer.valueOf(normal_sum.substring(16, 24), 16)
								.toString());
				rjy.put("index4_zcp",
						Double.valueOf(Integer.valueOf(normal_sum.substring(24, 32), 16)/100));
				System.out.println("税种税目4正常发票金额："
						+ Integer.valueOf(normal_sum.substring(24, 32), 16)
								.toString());
				rjy.put("index5_zcp",
						Double.valueOf(Integer.valueOf(normal_sum.substring(32, 40), 16)/100));
				System.out.println("税种税目5正常发票金额："
						+ Integer.valueOf(normal_sum.substring(32, 40), 16)
								.toString());
				rjy.put("index6_zcp",
						Double.valueOf(Integer.valueOf(normal_sum.substring(40, 48), 16)/100));
				System.out.println("税种税目6正常发票金额："
						+ Integer.valueOf(normal_sum.substring(40, 48), 16)
								.toString());

				String back_sum = tmp.substring(80, 128);
				System.out.println("当天退票分类累计金额：" + back_sum);
				rjy.put("index1_tp",
						Double.valueOf(Integer.valueOf(back_sum.substring(0, 8), 16)/100));
				System.out.println("税种税目1退票金额："
						+ Integer.valueOf(back_sum.substring(0, 8), 16)
								.toString());
				rjy.put("index2_tp",
						Double.valueOf(Integer.valueOf(back_sum.substring(8, 16), 16)/100));
				System.out.println("税种税目2退票金额："
						+ Integer.valueOf(back_sum.substring(8, 16), 16)
								.toString());
				rjy.put("index3_tp",
						Double.valueOf(Integer.valueOf(back_sum.substring(16, 24), 16)/100));
				System.out.println("税种税目3退票金额："
						+ Integer.valueOf(back_sum.substring(16, 24), 16)
								.toString());
				rjy.put("index4_tp",
						Double.valueOf(Integer.valueOf(back_sum.substring(24, 32), 16)/100));
				System.out.println("税种税目4退票金额："
						+ Integer.valueOf(back_sum.substring(24, 32), 16)
								.toString());
				rjy.put("index5_tp",
						Double.valueOf(Integer.valueOf(back_sum.substring(32, 40), 16)/100));
				System.out.println("税种税目5退票金额："
						+ Integer.valueOf(back_sum.substring(32, 40), 16)
								.toString());
				rjy.put("index6_tp",
						Double.valueOf(Integer.valueOf(back_sum.substring(40, 48), 16)/100));
				System.out.println("税种税目6退票金额："
						+ Integer.valueOf(back_sum.substring(40, 48), 16)
								.toString());

				String signer = tmp.substring(128, 384);
				rjy.put("signer", signer);
				System.out.println("电子签名：" + signer);
				result.add(rjy);

				System.out
						.println("=========================================================");
				System.out.println();
				
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Cannot open file " + rjyfile);
			System.exit(0);
		} catch (EOFException e) {
			System.out.println("End of the file " + rjyfile + " reached.");
		} catch (IOException e) {
			System.out.println("Error reading the file " + rjyfile);
			System.exit(0);
		} finally {
			try {
				if (fromFile != null)
					fromFile.close(); // throw IOException
			} catch (IOException e) {
				System.out.println("Error closing the file " + rjyfile);
				System.exit(0);
			}
		}

		return result;
	}
	
	public static int insertRjy(String machineno,String fiscalcard,ArrayList<HashMap<String, Object>> al)
	{
        int result = 1;
        if(al == null||al.isEmpty())
        {
            return result;
        }
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            String sqlrjy = "insert SKQ_RJYMX (JQBH,SKKH,DQRQ,ZCPFS,TPFS,FPFS,SMSY,SMBM,ZCPZJE,TPZJE,QM) values(?,?,?,?,?,?,?,?,?,?,?)";
            conn.setAutoCommit(false);
            PreparedStatement ps1 = conn.prepareStatement(sqlrjy);

            Iterator<HashMap<String, Object>> it = al.iterator();
            HashMap<String, Object> hm = null;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd"); 
            while(it.hasNext())
            {
                hm = (HashMap<String, Object>)it.next();
                String dqrq = (String)hm.get("dqrq");
                String zcpfs = (String)hm.get("zcpfs");
                String tpfs = (String)hm.get("tpfs");
                String fpfs = (String)hm.get("fpfs");
                String szsm_index = (String) hm.get("szsm");
                String[] szsm = new String[6];
                szsm[0] = szsm_index.substring(0,2);
                szsm[1] = szsm_index.substring(2,4);
                szsm[2] = szsm_index.substring(4,6);
                szsm[3] = szsm_index.substring(6,8);
                szsm[4] = szsm_index.substring(8,10);
                szsm[5] = szsm_index.substring(10,12);
                
                Double[] zcp = new Double[6];
                zcp[0] = (Double) hm.get("index1_zcp");
                zcp[1] = (Double) hm.get("index2_zcp");
                zcp[2] = (Double) hm.get("index3_zcp");
                zcp[3] = (Double) hm.get("index4_zcp");
                zcp[4] = (Double) hm.get("index5_zcp");
                zcp[5] = (Double) hm.get("index6_zcp");
                
                Double[] tp = new Double[6];
                tp[0] = (Double) hm.get("index1_tp");
                tp[1] = (Double) hm.get("index2_tp");
                tp[2] = (Double) hm.get("index3_tp");
                tp[3] = (Double) hm.get("index4_tp");
                tp[4] = (Double) hm.get("index5_tp");
                tp[5] = (Double) hm.get("index6_tp");

                String signer = (String) hm.get("signer");
                for(int i=0;i<6;i++)
                {
                	ps1.setString(1, machineno);
                	ps1.setString(2, fiscalcard);
                	ps1.setDate(3, Date.valueOf(dqrq.substring(0,4)+"-"+dqrq.substring(4,6)+"-"+dqrq.substring(6)));
                	ps1.setInt(4,Integer.parseInt(zcpfs));
                	ps1.setInt(5,Integer.parseInt(tpfs));
                	ps1.setInt(6,Integer.parseInt(fpfs));
                	ps1.setInt(7, Integer.parseInt(szsm[i]));
                	String sql = "select SMBM from SKQ_PMSZ where SMSY="+Integer.parseInt(szsm[i]);
                	String fieldname = "SMBM";
                	ps1.setString(8, Query.getField(sql, fieldname));
                	ps1.setDouble(9, zcp[i]);
                	ps1.setDouble(10, tp[i]);
                	ps1.setString(11, signer);
                	
                    ps1.addBatch();
                }

            }
            int[] res1 = ps1.executeBatch();
            conn.commit();
            for (int i1 = 0; i1 < res1.length; i1++) {
                if (res1[i1] == PreparedStatement.EXECUTE_FAILED
                        || res1[i1] == PreparedStatement.SUCCESS_NO_INFO) {
                    conn.rollback();
                    ps1.close();
                    result = -1;
                }
                break;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally
        {
            try {
                if(conn!=null)
                {
                    conn.close();
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return result;
	}
}
