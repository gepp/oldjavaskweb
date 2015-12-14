package jsdt.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import jsdt.tools.DBConnection;
import jsdt.tools.Query;
import jsdt.tools.Util;

public class Sjyz {
	// 查询税种税目
	public ArrayList selectSzsm() {
		Connection conn = null;
		ArrayList al = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select * from SERVER_TAXITEM";
				// System.out.println("sql=="+sql);
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				al = new ArrayList();
				while (rs.next()) {
					HashMap hm = new HashMap();
					hm.put("smsy", rs.getInt("INDEX"));
					hm.put("smbm", rs.getString("NO"));
					hm.put("sl", rs.getInt("RATE"));
					hm.put("smmc", rs.getString("NAME_CN"));
					hm.put("smjc", rs.getString("NAME_CN"));
					al.add(hm);
				}
				rs.close();
				ps.close();
				conn.close();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return al;
	}

	// 插入税种税目
	public int insertSzsm(ArrayList al) {
		int result1 = 1;
		Connection conn = null;

		if (al != null && !al.isEmpty()) {
			try {
				conn = DBConnection.getConnection();
				if (conn != null) {
					conn.setAutoCommit(false);
					Statement stsm = conn.createStatement();

					Iterator it = al.iterator();
					while (it.hasNext()) {
						HashMap hm = (HashMap) it.next();
						String smbm = (String) hm.get("smbm");
						String szbm = smbm.substring(0, 2);
						String sql = "insert into SKQ_PMSZ(SZBM,SMBM,SMMC,SMJC,SL,SMSY,FPBM,HDKPBL,CZKPXE,CEZS,STATUS) values('"
								+ szbm
								+ "','"
								+ smbm
								+ "','"
								+ (String) hm.get("smmc")
								+ "','"
								+ (String) hm.get("smjc")
								+ "',"
								+ (Integer) hm.get("sl")
								/ 100
								+ ","
								+ (Integer) hm.get("smsy")
								+ ",'001',5,100000,0,1)";
						stsm.addBatch(sql);
					}

					int[] result = stsm.executeBatch();
					conn.commit();
					for (int i = 0; i < result.length; i++) {
						if (result[i] == Statement.EXECUTE_FAILED
								|| result[i] == Statement.SUCCESS_NO_INFO) {
							conn.rollback();
							stsm.close();
							result1 = -1;
						}
						break;
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				result1 = -1;
				// e.getMessage();
				e.printStackTrace();
			} finally {
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return result1;
	}

	// 查询注册类型
	public ArrayList selectZclx() {
		Connection conn = null;
		ArrayList al = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select * from SERVER_NO_REG_TYPE";
				// System.out.println("sql=="+sql);
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				al = new ArrayList();
				while (rs.next()) {
					HashMap hm = new HashMap();
					hm.put("zclxbm", rs.getString("SN"));
					hm.put("zclxmc", rs.getString("NAME"));
					hm.put("zclxjc", rs.getString("NAME"));
					al.add(hm);
				}
				rs.close();
				ps.close();
				conn.close();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return al;
	}

	// 插入注册类型
	public int insertZclx(ArrayList al) {
		int result1 = 1;
		Connection conn = null;

		if (al != null && !al.isEmpty()) {
			try {
				conn = DBConnection.getConnection();
				if (conn != null) {
					conn.setAutoCommit(false);
					Statement stsm = conn.createStatement();

					Iterator it = al.iterator();
					while (it.hasNext()) {
						HashMap hm = (HashMap) it.next();
						String sql = "insert into SKQ_ZCLX(ZCLXBM,ZCLXMC,ZCLXJC,STATUS) values('"
								+ (String) hm.get("zclxbm")
								+ "','"
								+ (String) hm.get("zclxmc")
								+ "','"
								+ (String) hm.get("zclxjc") + "',1)";
						stsm.addBatch(sql);
					}

					int[] result = stsm.executeBatch();
					conn.commit();
					for (int i = 0; i < result.length; i++) {
						if (result[i] == Statement.EXECUTE_FAILED
								|| result[i] == Statement.SUCCESS_NO_INFO) {
							conn.rollback();
							stsm.close();
							result1 = -1;
						}
						break;
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				result1 = -1;
				// e.getMessage();
				e.printStackTrace();
			} finally {
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return result1;
	}

	// 查询纳税人信息及机器信息
	public ArrayList selectNsrxx() {
		Connection conn = null;
		ArrayList al = null;

		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select * from SERVER_TAXPAYER_USER";
				// System.out.println("sql=="+sql);
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				al = new ArrayList();
				while (rs.next()) {
					HashMap hm = new HashMap();
					hm.put("sn", rs.getString("SN"));
					hm.put("nsrwjbm", rs.getString("CODE"));
					hm.put("nsrsbh", rs.getString("NO"));
					hm.put("nsrmc", rs.getString("NAME"));
					hm.put("jydz", rs.getString("ADDR"));
					hm.put("frdb", rs.getString("CORPORATION"));
					hm.put("zclxbm", "1");
					hm.put("hybm", "1");
					hm.put("hymxbm", "1");
					hm.put("zsfs", 0);
					hm.put("yhde", 0);
					hm.put("swjgbm", "00000001");
					al.add(hm);
				}
				rs.close();
				ps.close();
				// conn.close();
				if (al != null && !al.isEmpty()) {
					Iterator it = al.iterator();
					while (it.hasNext()) {
						HashMap hm = (HashMap) it.next();
						String sn = (String) hm.get("sn");
						String nsrwjbm = (String) hm.get("nsrwjbm");

						// 纳税人税种税目
						ArrayList alszsm = this.selectSzsm();
						hm.put("alNsrszsm", alszsm);

						sql = "select a.*,b.NO as smsy1,c.NO as smsy2,d.NO as smsy3,e.NO as smsy4,f.NO as smsy5,g.NO as smsy6 from SERVER_TAXPAYER_FISCAL a left outer join SERVER_TAXITEM b on a.INDEX_1=b.[INDEX] left outer join SERVER_TAXITEM c on a.INDEX_2=c.[INDEX] left outer join SERVER_TAXITEM d on a.INDEX_3=d.[INDEX] left outer join SERVER_TAXITEM e on a.INDEX_4=e.[INDEX] left outer join SERVER_TAXITEM f on a.INDEX_5=f.[INDEX] left outer join SERVER_TAXITEM g on a.INDEX_6=g.[INDEX] where a.USER_SN = '"
								+ sn + "'";
						System.out.println("sql111==" + sql);
						ps = conn.prepareStatement(sql);
						rs = ps.executeQuery();
						ArrayList alJqxx = new ArrayList();
						String smbmStr = "";
						String jqbhStr = ",";
						while (rs.next()) {
							HashMap hm1 = new HashMap();
							
							String kpjzrq = Util.tobzrq(rs
									.getString("MAKE_END_DATE"));
							double dzkpxe = rs.getDouble("MAKE_MAX_SINGLE")/100;
							double yljkpxe = rs.getDouble("MAKE_MAX_SUM")/100;
							if(yljkpxe<0){
								yljkpxe = 42000000;
							}
							if(dzkpxe<0){
								dzkpxe=42000000;
							}
							double yljtpxe = rs.getDouble("BACK_MAX_SUM")/100;
							if(yljtpxe<0){
								yljtpxe = 42000000;
							}
						
							String smsy1 = rs.getString("smsy1");
							String smsy2 = rs.getString("smsy2");
							String smsy3 = rs.getString("smsy3");
							String smsy4 = rs.getString("smsy4");
							String smsy5 = rs.getString("smsy5");
							String smsy6 = rs.getString("smsy6");
							String skkh = rs.getString("FISCAL_CARD_NO");
							String jqbh = rs.getString("MACHINE_NO");
							String yhkh = rs.getString("FISCAL_CARD_NO");
							String jqxhbm = "1";
							String kqyrq = Util.tobzrq(rs
									.getString("START_DATE"));
							String kyxrq = Util
									.tobzrq(rs.getString("END_DATE"));
							String mxsbbz = rs.getString("DECLARE_MAKE_TYPE");
							String sbfs = rs.getString("DECLARE_TYPE");
							
							if(jqbhStr.indexOf(jqbh)>=0){
								
							}
							else{
								jqbhStr = jqbhStr+","+jqbh;
								hm1.put("nsrwjbm", nsrwjbm);
								hm1.put("kpjzrq", kpjzrq);
								hm1.put("dzkpxe", dzkpxe);
								hm1.put("yljkpxe", yljkpxe);
								hm1.put("yljtpxe", yljtpxe);
								hm1.put("smsy1", smsy1);
								hm1.put("smsy2", smsy2);
								hm1.put("smsy3", smsy3);
								hm1.put("smsy4", smsy4);
								hm1.put("smsy5", smsy5);
								hm1.put("smsy6", smsy6);
								hm1.put("skkh", skkh);
								hm1.put("jqbh", jqbh);
								hm1.put("yhkh", yhkh);
								hm1.put("jqxhbm", jqxhbm);
								hm1.put("kqyrq", kqyrq);
								hm1.put("kyxrq", kyxrq);
								hm1.put("mxsbbz", mxsbbz);
								hm1.put("sbfs", sbfs);

								alJqxx.add(hm1);
							}
							
						}

						hm.put("alJqxx", alJqxx);
					}
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return al;
	}

	// 插入纳税人机器信息
	public int insertNsrxx(ArrayList al) {
		int result1 = 1;
		Connection conn = null;

		if (al != null && !al.isEmpty()) {
			try {
				conn = DBConnection.getConnection();
				if (conn != null) {
					conn.setAutoCommit(false);
					Statement stsm = conn.createStatement();

					Iterator it = al.iterator();
					while (it.hasNext()) {
						HashMap hm = (HashMap) it.next();
						String nsrwjbm = (String) hm.get("nsrwjbm");
						// 纳税人基础信息
						String sql = "insert into SKQ_NSRXX(NSRWJBM,NSRSBH,NSRMC,JYDZ,FRDB,ZCLXBM,HYBM,HYMXBM,ZSFS,YHDE,SWJGBM,STATUS) values('"
								+ (String) hm.get("nsrwjbm")
								+ "','"
								+ (String) hm.get("nsrsbh")
								+ "','"
								+ (String) hm.get("nsrmc")
								+ "','"
								+ (String) hm.get("jydz")
								+ "','"
								+ (String) hm.get("frdb")
								+ "','"
								+ (String) hm.get("zclxbm")
								+ "','"
								+ (String) hm.get("hybm")
								+ "','"
								+ (String) hm.get("hymxbm")
								+ "',"
								+ (Integer) hm.get("zsfs")
								+ ","
								+ (Integer) hm.get("yhde")
								+ ",'"
								+ (String) hm.get("swjgbm") + "',1)";
						//System.out.println("sql=="+sql);
						stsm.addBatch(sql);

						// 纳税人税种税目
						ArrayList alNsrszsm = (ArrayList) hm.get("alNsrszsm");
						if (alNsrszsm != null && !alNsrszsm.isEmpty()) {
							Iterator it1 = alNsrszsm.iterator();
							while (it1.hasNext()) {
								HashMap hmszsm = (HashMap) it1.next();

								String sql1 = "insert into SKQ_NSRSZSM(NSRWJBM,SZBM,SMBM,SMMC,SMJC,SL,STATUS) values('"
										+ nsrwjbm
										+ "','"
										+ ((String) hmszsm.get("smbm"))
												.substring(0, 2)
										+ "','"
										+ (String) hmszsm.get("smbm")
										+ "','"
										+ (String) hmszsm.get("smmc")
										+ "','"
										+ (String) hmszsm.get("smjc")
										+ "',"
										+ (Integer) hmszsm.get("sl")
										/ 100
										+ ",1)";
								//System.out.println("sql1=="+sql1);
								stsm.addBatch(sql1);
							}
						}

						// 机器信息
						ArrayList alJqxx = (ArrayList) hm.get("alJqxx");
						if (alJqxx != null && !alJqxx.isEmpty()) {
							Iterator it2 = alJqxx.iterator();
							while (it2.hasNext()) {
								HashMap hmjqxx = (HashMap) it2.next();

								String sql2 = "insert into SKQ_JQXX(NSRWJBM,JQXHBM,JQBH,SKKH,YHKH,KQYRQ,KYXRQ,SBFS,MXSBBZ,KPJZRQ,DZKPXE,YLJKPXE,YLJTPXE,STATUS) values('"
										+ nsrwjbm
										+ "','"
										+ (String) hmjqxx.get("jqxhbm")
										+ "','"
										+ (String) hmjqxx.get("jqbh")
										+ "','"
										+ (String) hmjqxx.get("skkh")
										+ "','"
										+ (String) hmjqxx.get("yhkh")
										+ "','"
										+ (String) hmjqxx.get("kqyrq")
										+ "','"
										+ (String) hmjqxx.get("kyxrq")
										+ "','"
										+ (String) hmjqxx.get("sbfs")
										+ "','"
										+ (String) hmjqxx.get("mxsbbz")
										+ "','"
										+ (String) hmjqxx.get("kpjzrq")
										+ "',"
										+ (Double) hmjqxx.get("dzkpxe")
										+ ","
										+ (Double) hmjqxx.get("yljkpxe")
										+ ","
										+ (Double) hmjqxx.get("yljtpxe")
										+ ",1)";
								//System.out.println("sql2=="+sql2);
								stsm.addBatch(sql2);

								String smsy1 = (String) hmjqxx.get("smsy1");
								String smsy2 = (String) hmjqxx.get("smsy2");
								String smsy3 = (String) hmjqxx.get("smsy3");
								String smsy4 = (String) hmjqxx.get("smsy4");
								String smsy5 = (String) hmjqxx.get("smsy5");
								String smsy6 = (String) hmjqxx.get("smsy6");
								String jqbh = (String) hmjqxx.get("jqbh");
								if (smsy1 != null && !"".equals(smsy1)) {
									String sql3 = "insert into SKQ_JQSZSM(JQBH,SMBM) values('"
											+ jqbh + "','" + smsy1 + "')";
									//System.out.println("sql3=="+sql3);
									stsm.addBatch(sql3);
								}

								if (smsy2 != null && !"".equals(smsy2)) {
									String sql4 = "insert into SKQ_JQSZSM(JQBH,SMBM) values('"
											+ jqbh + "','" + smsy2 + "')";
									stsm.addBatch(sql4);
								}

								if (smsy3 != null && !"".equals(smsy3)) {
									String sql5 = "insert into SKQ_JQSZSM(JQBH,SMBM) values('"
											+ jqbh + "','" + smsy3 + "')";
									stsm.addBatch(sql5);
								}

								if (smsy4 != null && !"".equals(smsy4)) {
									String sql6 = "insert into SKQ_JQSZSM(JQBH,SMBM) values('"
											+ jqbh + "','" + smsy4 + "')";
									stsm.addBatch(sql6);
								}

								if (smsy5 != null && !"".equals(smsy5)) {
									String sql7 = "insert into SKQ_JQSZSM(JQBH,SMBM) values('"
											+ jqbh + "','" + smsy5 + "')";
									stsm.addBatch(sql7);
								}

								if (smsy6 != null && !"".equals(smsy6)) {
									String sql8 = "insert into SKQ_JQSZSM(JQBH,SMBM) values('"
											+ jqbh + "','" + smsy6 + "')";
									stsm.addBatch(sql8);
								}
							}
						}
					}

					int[] result = stsm.executeBatch();
					conn.commit();
					for (int i = 0; i < result.length; i++) {
						if (result[i] == Statement.EXECUTE_FAILED
								|| result[i] == Statement.SUCCESS_NO_INFO) {
							conn.rollback();
							stsm.close();
							result1 = -1;
						}
						break;
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				result1 = -1;
				// e.getMessage();
				e.printStackTrace();
			} finally {
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return result1;
	}
	public int insertOldSbsj(){
		String sql="select c.CODE,c.name,a.start_date,a.end_date,a.NORMAL_CNT,a.BACK_CNT,a.DEPOSE_CNT,b.machine_no,(a.NORMAL_CNT+a.BACK_CNT+a.DEPOSE_CNT) as totalSum,a.normal_sum,a.back_sum,(select count(SN) as num from SERVER_DECLARE_MAKE WHERE substring(DATETIME,0,9)>=a.start_date AND substring(DATETIME,0,9)<=a.end_date AND MACHINE_NO IN(SELECT MACHINE_NO FROM SERVER_TAXPAYER_FISCAL  WHERE USER_SN IN(SELECT SN FROM SERVER_TAXPAYER_USER WHERE CODE=c.CODE))) AS YSBSUM  FROM SERVER_DECLARE a  left join SERVER_TAXPAYER_FISCAL b  on a.FISCAL_CARD_NO=b.FISCAL_CARD_NO left outer join SERVER_TAXPAYER_USER c on b.USER_SN = c.SN WHERE a.START_DATE>='20110101' AND a.END_DATE<='20110228' group by c.CODE,a.start_date,a.end_date,c.name,a.NORMAL_CNT,a.BACK_CNT,a.DEPOSE_CNT,a.normal_sum,a.back_sum,b.machine_no";
	    int result=Query.executeSql(sql);
	    if(result==-1){
	    	result=1;
	    }else
	    	result=-1;
        return result;
	}
}
