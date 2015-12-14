package jsdt.model.cxtj;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

import jsdt.tools.DBConnection;
import jsdt.tools.Query;
import jsdt.tools.Util;

public class cxBdc {
	public ArrayList getBdcxm(String sqlStr) {
		ArrayList alResult = new ArrayList();
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				
				String sql = "select * from SKQ_BDCXMMX where SID in("+sqlStr+") order by PARENTID desc,CJSJ desc";
				//System.out.println("获取不动产项目sql=="+sql);
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					HashMap hm = new HashMap();
					hm.put("id", String.valueOf(rs.getInt("SID")));
					hm.put("xmmc", Util.iso8859togbk(rs.getString("ZXMMC")));
					hm.put("xmbm", rs.getString("XMBM"));
					hm.put("bzxptzzts", String.valueOf(rs.getInt("BZXPTZZTS")));
					hm.put("fbzxptzzts", String.valueOf(rs.getInt("FBZXPTZZTS")));
					hm.put("fptzzts", String.valueOf(rs.getInt("FPTZZTS")));
					hm.put("bsts", String.valueOf(rs.getInt("BSTS")));
					hm.put("zzts", String.valueOf(rs.getInt("BSTS")+rs.getInt("FPTZZTS")+rs.getInt("FBZXPTZZTS")+rs.getInt("BZXPTZZTS")));
					hm.put("spts", String.valueOf(rs.getInt("SPTS")));
					hm.put("xzlts", String.valueOf(rs.getInt("XZLTS")));
					hm.put("qtts", String.valueOf(rs.getInt("QTTS")));
					hm.put("zzmj", String.valueOf(rs.getDouble("BZXPTZZMJ")+rs.getDouble("FBZXPTZZMJ")+rs.getDouble("FPTZZMJ")+rs.getDouble("BSMJ")));
					hm.put("bzxptzzmj", String.valueOf(rs.getDouble("BZXPTZZMJ")));
					hm.put("fbzxptzzmj", String.valueOf(rs.getDouble("FBZXPTZZMJ")));
					hm.put("fptzzmj", String.valueOf(rs.getDouble("FPTZZMJ")));
					hm.put("bsmj", String.valueOf(rs.getDouble("BSMJ")));
					hm.put("spmj", String.valueOf(rs.getDouble("SPMJ")));
					hm.put("xzlmj", String.valueOf(rs.getDouble("XZLMJ")));
					hm.put("qtmj", String.valueOf(rs.getDouble("QTMJ")));
					
					String sql1 = "select count(SID) as lrts from SKQ_BDCLP where PARENTID="+rs.getInt("SID");
					String fieldname = "lrts";
					int lrts = Query.getFieldInt(sql1, fieldname);
					//System.out.println("lrts=="+lrts);
					hm.put("lrts", String.valueOf(lrts));
					alResult.add(hm);
				}
				rs.close();
				ps.close();
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
		return alResult;
	}
	
	
	public HashMap getBdcxmHz(String sqlStr) {
		HashMap hm = new HashMap();
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				
				String sql = "select sum(BZXPTZZTS) as hzbzxptzzts,sum(FBZXPTZZTS) as hzfbzxptzzts,sum(FPTZZTS) as hzfptzzts,sum(BSTS) as hzbsts,sum(SPTS) as hzspts,sum(XZLTS) as hzxzlts,sum(QTTS) as hzqtts from SKQ_BDCXMMX where 1=1 "+sqlStr;
				//System.out.println("获取不动产项目sql=="+sql);
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					hm.put("hzbzxptzzts", String.valueOf(rs.getDouble("hzbzxptzzts")));
					hm.put("hzfbzxptzzts", String.valueOf(rs.getDouble("hzfbzxptzzts")));
					hm.put("hzfptzzts", String.valueOf(rs.getDouble("hzfptzzts")));
					hm.put("hzbsts", String.valueOf(rs.getDouble("hzbsts")));
					hm.put("hzspts", String.valueOf(rs.getDouble("hzspts")));
					hm.put("hzxzlts", String.valueOf(rs.getDouble("hzxzlts")));
					hm.put("hzqtts", String.valueOf(rs.getDouble("hzqtts")));
				}
				rs.close();
				ps.close();
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
		return hm;
	}

	// 获取不动产楼牌信息
	public ArrayList getBdclp(int xmid, String nsrwjbm) {
		ArrayList alResult = new ArrayList();
		nsrwjbm = Util.substringFront(nsrwjbm, 15);
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sqlStr = "";
				if(nsrwjbm!=null&&!"".equals(nsrwjbm)){
					sqlStr = " and a.NSRWJBM='"+nsrwjbm+"'";
				}
				String sql = "select a.*,b.ZXMMC,b.XMBM from SKQ_BDCLP a left outer join SKQ_BDCXMMX b on a.PARENTID=b.SID where a.PARENTID="
						+ xmid
						+ sqlStr +" order by a.LC desc,a.LPXH asc";
				System.out.println("sql==" + sql);
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					HashMap hm = new HashMap();
					hm.put("id", rs.getString("SID"));
					hm.put("lph", rs.getString("LPMC"));
					hm.put("lc", String.valueOf(rs.getInt("LC")));
					hm.put("mj", String.valueOf(rs.getDouble("MJ")));
					hm.put("dj", String.valueOf(rs.getDouble("DJ")));
					hm.put("lx", String.valueOf(rs.getInt("LX")));
					hm.put("xmmc", Util.iso8859togbk(rs.getString("ZXMMC")));
					hm.put("xmbm", rs.getString("XMBM"));

					int sid = rs.getInt("SID");
					String sql1 = "select SID from SKQ_BDCKP where PARENTID="
							+ sid + " and KXXZ=3";
					String fieldname = "SID";
					int sfkid = Query.getFieldInt(sql1, fieldname);
					int flag = 0;
					if (sfkid > 0) {
						sql1 = "select sum(HJZJE) as ysfkzcp from SKQ_BDCKP where PARENTID="
								+ sid + " and KXXZ=3 and KPLX=1";
						fieldname = "ysfkzcp";
						double ysfkzcp = Query.getFieldDouble(sql1, fieldname);

						sql1 = "select sum(HJZJE) as ysfktp from SKQ_BDCKP where PARENTID="
								+ sid + " and KXXZ=3 and KPLX=2";
						fieldname = "ysfktp";
						double ysfktp = Query.getFieldDouble(sql1, fieldname);
						if ((ysfkzcp - ysfktp) > 0) {
							flag = 1;
							HashMap hmKp = this.selectBdcKp(sfkid);
							hm.put("fkfmc", (String) hmKp.get("fkfmc"));
							hm.put("fkfdm", (String) hmKp.get("fkfdm"));
							hm.put("kxxz", String.valueOf("售房款"));
						}
					}
					if (flag == 0) {
						sql1 = "select SID from SKQ_BDCKP where PARENTID="
								+ sid + " and KXXZ=2";
						fieldname = "SID";
						int ysfkid = Query.getFieldInt(sql1, fieldname);

						if (ysfkid > 0) {
							sql1 = "select sum(HJZJE) as ysfkzcp from SKQ_BDCKP where PARENTID="
									+ sid + " and KXXZ=2 and KPLX=1";
							fieldname = "ysfkzcp";
							double ysfkzcp = Query.getFieldDouble(sql1,
									fieldname);

							sql1 = "select sum(HJZJE) as ysfktp from SKQ_BDCKP where PARENTID="
									+ sid + " and KXXZ=2 and KPLX=2";
							fieldname = "ysfktp";
							double ysfktp = Query.getFieldDouble(sql1,
									fieldname);

							if ((ysfkzcp - ysfktp) > 0) {
								flag = 1;
								HashMap hmKp = this.selectBdcKp(ysfkid);
								hm.put("fkfmc", (String) hmKp.get("fkfmc"));
								hm.put("fkfdm", (String) hmKp.get("fkfdm"));
								hm.put("kxxz", String.valueOf("预收购房款"));
							}
						}
						if (flag == 0) {
							sql1 = "select SID from SKQ_BDCKP where PARENTID="
									+ sid + " and KXXZ=1";
							fieldname = "SID";
							int djid = Query.getFieldInt(sql1, fieldname);

							if (djid > 0) {
								sql1 = "select sum(HJZJE) as djzcp from SKQ_BDCKP where PARENTID="
										+ sid + " and KXXZ=1 and KPLX=1";
								fieldname = "djzcp";
								double djzcp = Query.getFieldDouble(sql1,
										fieldname);

								sql1 = "select sum(HJZJE) as djtp from SKQ_BDCKP where PARENTID="
										+ sid + " and KXXZ=1 and KPLX=2";
								fieldname = "djtp";
								double djtp = Query.getFieldDouble(sql1,
										fieldname);

								if ((djzcp - djtp) > 0) {
									flag = 1;
									HashMap hmKp = this.selectBdcKp(djid);
									hm.put("fkfmc", (String) hmKp.get("fkfmc"));
									hm.put("fkfdm", (String) hmKp.get("fkfdm"));
									hm.put("kxxz", String.valueOf("预售定金"));
								}
							}
						}
						if (flag == 0) {
							hm.put("fkfmc", "");
							hm.put("fkfdm", "");
							hm.put("kxxz", String.valueOf("无开票"));
						}
						
						//
						sql = "select count(SID) as num from SKQ_BDCLP where PARENTID="
							+ xmid + " and LX=1";
						fieldname = "num";
						int lx1 = Query.getFieldInt(sql, fieldname);
						
						sql = "select count(SID) as num from SKQ_BDCLP where PARENTID="
							+ xmid + " and LX=2";
						fieldname = "num";
						int lx2 = Query.getFieldInt(sql, fieldname);
						
						sql = "select count(SID) as num from SKQ_BDCLP where PARENTID="
							+ xmid + " and LX=3";
						fieldname = "num";
						int lx3 = Query.getFieldInt(sql, fieldname);
						
						sql = "select count(SID) as num from SKQ_BDCLP where PARENTID="
							+ xmid + " and LX=4";
						fieldname = "num";
						int lx4 = Query.getFieldInt(sql, fieldname);
						
						sql = "select count(SID) as num from SKQ_BDCLP where PARENTID="
							+ xmid + " and LX=5";
						fieldname = "num";
						int lx5 = Query.getFieldInt(sql, fieldname);
						
						sql = "select count(SID) as num from SKQ_BDCLP where PARENTID="
							+ xmid + " and LX=6";
						fieldname = "num";
						int lx6 = Query.getFieldInt(sql, fieldname);
						
						sql = "select count(SID) as num from SKQ_BDCLP where PARENTID="
							+ xmid + " and LX=7";
						fieldname = "num";
						int lx7 = Query.getFieldInt(sql, fieldname);
						
						int maxts = lx1;
						if(maxts<lx1){
							maxts = lx1;
						}
						if(maxts<lx2){
							maxts = lx2;
						}
						if(maxts<lx3){
							maxts = lx3;
						}
						if(maxts<lx4){
							maxts = lx4;
						}
						if(maxts<lx5){
							maxts = lx5;
						}
						if(maxts<lx6){
							maxts = lx6;
						}
						if(maxts<lx7){
							maxts = lx7;
						}
						
						hm.put("maxts", String.valueOf(maxts));
					}
					alResult.add(hm);
				}
				rs.close();
				ps.close();
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
		//System.out.println("alResult==" + alResult);
		return alResult;
	}
	
	
	public HashMap getBdclpt(int xmid, String nsrwjbm) {
		HashMap hmResult = new HashMap();
		ArrayList alResult = new ArrayList();
		nsrwjbm = Util.substringFront(nsrwjbm, 15);
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sqlStr = "";
				if(nsrwjbm!=null&&!"".equals(nsrwjbm)){
					sqlStr = " and a.NSRWJBM='"+nsrwjbm+"'";
				}
				String sql = "select a.*,b.ZXMMC,b.XMBM from SKQ_BDCLP a left outer join SKQ_BDCXMMX b on a.PARENTID=b.SID where a.PARENTID="
						+ xmid
						+ sqlStr +" order by a.LC desc,a.LPXH asc";
				System.out.println("sql==" + sql);
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				int i = 0;
				HashMap hmLp = new HashMap();
				while (rs.next()) {
					HashMap hm = new HashMap();
					hm.put("id", rs.getString("SID"));
					hm.put("lph", rs.getString("LPMC"));
					hm.put("lc", String.valueOf(rs.getInt("LC")));
					hm.put("mj", String.valueOf(rs.getDouble("MJ")));
					hm.put("dj", String.valueOf(rs.getDouble("DJ")));
					hm.put("lx", String.valueOf(rs.getInt("LX")));
					hm.put("xmmc", Util.iso8859togbk(rs.getString("ZXMMC")));
					hm.put("xmbm", rs.getString("XMBM"));
					
					int lc = rs.getInt("LC");
					int lpxh = rs.getInt("LPXH");
					
					String arrxb = lc+","+lpxh;

					int sid = rs.getInt("SID");
					String sql1 = "select SID from SKQ_BDCKP where PARENTID="
							+ sid + " and KXXZ=3";
					String fieldname = "SID";
					int sfkid = Query.getFieldInt(sql1, fieldname);
					int flag = 0;
					if (sfkid > 0) {
						sql1 = "select sum(HJZJE) as ysfkzcp from SKQ_BDCKP where PARENTID="
								+ sid + " and KXXZ=3 and KPLX=1";
						fieldname = "ysfkzcp";
						double ysfkzcp = Query.getFieldDouble(sql1, fieldname);

						sql1 = "select sum(HJZJE) as ysfktp from SKQ_BDCKP where PARENTID="
								+ sid + " and KXXZ=3 and KPLX=2";
						fieldname = "ysfktp";
						double ysfktp = Query.getFieldDouble(sql1, fieldname);
						if ((ysfkzcp - ysfktp) > 0) {
							flag = 1;
							HashMap hmKp = this.selectBdcKp(sfkid);
							hm.put("fkfmc", (String) hmKp.get("fkfmc"));
							hm.put("fkfdm", (String) hmKp.get("fkfdm"));
							hm.put("kxxz", String.valueOf("售房款"));
						}
					}
					if (flag == 0) {
						sql1 = "select SID from SKQ_BDCKP where PARENTID="
								+ sid + " and KXXZ=2";
						fieldname = "SID";
						int ysfkid = Query.getFieldInt(sql1, fieldname);

						if (ysfkid > 0) {
							sql1 = "select sum(HJZJE) as ysfkzcp from SKQ_BDCKP where PARENTID="
									+ sid + " and KXXZ=2 and KPLX=1";
							fieldname = "ysfkzcp";
							double ysfkzcp = Query.getFieldDouble(sql1,
									fieldname);

							sql1 = "select sum(HJZJE) as ysfktp from SKQ_BDCKP where PARENTID="
									+ sid + " and KXXZ=2 and KPLX=2";
							fieldname = "ysfktp";
							double ysfktp = Query.getFieldDouble(sql1,
									fieldname);

							if ((ysfkzcp - ysfktp) > 0) {
								flag = 1;
								HashMap hmKp = this.selectBdcKp(ysfkid);
								hm.put("fkfmc", (String) hmKp.get("fkfmc"));
								hm.put("fkfdm", (String) hmKp.get("fkfdm"));
								hm.put("kxxz", String.valueOf("预收购房款"));
							}
						}
						if (flag == 0) {
							sql1 = "select SID from SKQ_BDCKP where PARENTID="
									+ sid + " and KXXZ=1";
							fieldname = "SID";
							int djid = Query.getFieldInt(sql1, fieldname);

							if (djid > 0) {
								sql1 = "select sum(HJZJE) as djzcp from SKQ_BDCKP where PARENTID="
										+ sid + " and KXXZ=1 and KPLX=1";
								fieldname = "djzcp";
								double djzcp = Query.getFieldDouble(sql1,
										fieldname);

								sql1 = "select sum(HJZJE) as djtp from SKQ_BDCKP where PARENTID="
										+ sid + " and KXXZ=1 and KPLX=2";
								fieldname = "djtp";
								double djtp = Query.getFieldDouble(sql1,
										fieldname);

								if ((djzcp - djtp) > 0) {
									flag = 1;
									HashMap hmKp = this.selectBdcKp(djid);
									hm.put("fkfmc", (String) hmKp.get("fkfmc"));
									hm.put("fkfdm", (String) hmKp.get("fkfdm"));
									hm.put("kxxz", String.valueOf("预售定金"));
								}
							}
						}
						if (flag == 0) {
							hm.put("fkfmc", "");
							hm.put("fkfdm", "");
							hm.put("kxxz", String.valueOf("无开票"));
						}
						
						
					}
					
					
					
					hmLp.put(arrxb, hm);
					
					
					i++;
				}
				rs.close();
				ps.close();
				
				
				//
				sql = "select count(SID) as num from SKQ_BDCLP where PARENTID="
					+ xmid + " and LX=1";
				String fieldname = "num";
				int lx1 = Query.getFieldInt(sql, fieldname);
				
				sql = "select count(SID) as num from SKQ_BDCLP where PARENTID="
					+ xmid + " and LX=2";
				fieldname = "num";
				int lx2 = Query.getFieldInt(sql, fieldname);
				
				sql = "select count(SID) as num from SKQ_BDCLP where PARENTID="
					+ xmid + " and LX=3";
				fieldname = "num";
				int lx3 = Query.getFieldInt(sql, fieldname);
				
				sql = "select count(SID) as num from SKQ_BDCLP where PARENTID="
					+ xmid + " and LX=4";
				fieldname = "num";
				int lx4 = Query.getFieldInt(sql, fieldname);
				
				sql = "select count(SID) as num from SKQ_BDCLP where PARENTID="
					+ xmid + " and LX=5";
				fieldname = "num";
				int lx5 = Query.getFieldInt(sql, fieldname);
				
				sql = "select count(SID) as num from SKQ_BDCLP where PARENTID="
					+ xmid + " and LX=6";
				fieldname = "num";
				int lx6 = Query.getFieldInt(sql, fieldname);
				
				sql = "select count(SID) as num from SKQ_BDCLP where PARENTID="
					+ xmid + " and LX=7";
				fieldname = "num";
				int lx7 = Query.getFieldInt(sql, fieldname);
				
				int maxts = lx1;
				if(maxts<lx1){
					maxts = lx1;
				}
				if(maxts<lx2){
					maxts = lx2;
				}
				if(maxts<lx3){
					maxts = lx3;
				}
				if(maxts<lx4){
					maxts = lx4;
				}
				if(maxts<lx5){
					maxts = lx5;
				}
				if(maxts<lx6){
					maxts = lx6;
				}
				if(maxts<lx7){
					maxts = lx7;
				}
				
				hmResult.put("maxts", String.valueOf(maxts));
				hmResult.put("hmLpt", hmLp);
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
		//System.out.println("alResult==" + alResult);
		return hmResult;
	}
	
	
	public ArrayList getBdclptlc(int xmid, String nsrwjbm) {
		ArrayList alResult = new ArrayList();
		nsrwjbm = Util.substringFront(nsrwjbm, 15);
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sqlStr = "";
				if(nsrwjbm!=null&&!"".equals(nsrwjbm)){
					sqlStr = " and NSRWJBM='"+nsrwjbm+"'";
				}
				String sql = "select LC from SKQ_BDCLP where PARENTID="
						+ xmid
						+ sqlStr +" order by LC desc,LPXH asc";
				System.out.println("sql==" + sql);
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				int i = 0;
				
				while (rs.next()) {
					HashMap hmLc = new HashMap();
					int lc = rs.getInt("LC");
					hmLc.put("lc", String.valueOf(lc));
					alResult.add(hmLc);
				}
				
				rs.close();
				ps.close();
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
		//System.out.println("alResult==" + alResult);
		return alResult;
	}
	
	public HashMap selectBdcKp(int sid) {
		HashMap hm = new HashMap();
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select * from SKQ_BDCKP where SID =" + sid;
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					hm.put("fkfmc", Util.iso8859togbk(rs.getString("FKFMC")));
					hm.put("fkfdm", rs.getString("FKFDM"));
				}
				rs.close();
				ps.close();
				conn.close();
			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}
		}
		return hm;
	}
	
	//查询不动产开票信息
	public ArrayList selectBdcKpxx(int parentid){
		ArrayList al = new ArrayList();
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select * from SKQ_BDCKP where PARENTID="+parentid;
				
				//System.out.println("sql=="+sql);
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				al = new ArrayList();
				DecimalFormat dg = new DecimalFormat("0.00");
				while (rs.next()) {
					HashMap hm = new HashMap();
					hm.put("sid", String.valueOf(rs.getInt("SID")));
					hm.put("parentid", rs.getString("PARENTID"));
					hm.put("kxxz", String.valueOf(rs.getInt("KXXZ")));
					hm.put("fpdm", rs.getString("FPDM"));
					hm.put("fphm", Util.fpIntToString(rs.getInt("FPHM")));
					hm.put("kplx", String.valueOf(rs.getInt("KPLX")));
					hm.put("hjzje", dg.format(rs.getDouble("HJZJE")));
					hm.put("kprq", rs.getString("KPRQ").substring(0,10));
					hm.put("scrq", rs.getString("SCRQ").substring(0,10));		

					al.add(hm);
				}
				rs.close();
				ps.close();
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
}
