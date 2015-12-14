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
import jsdt.tools.Util;

public class Fpj extends baseClass {
	private int sid;
	private String fpdm;
	private int fpqsh;
	private int fpjzh;
	private int fpdw;
	private String fpbm;
	private String nsrwjbm;
	private String fplgrq;

	/**
	 * @roseuid 4CA19C4A0222
	 */
	public Fpj() {

	}

	/**
	 * @param fpdm
	 * @param fpqsh
	 * @roseuid 4CA0642E0157
	 */
	public Fpj(String fpdm, int fpqsh) {
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select * from SKQ_FPJ where FPDM='" + fpdm
						+ "' and FPQSH=" + fpqsh;
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					this.sid = rs.getInt("SID");
					this.fpbm = rs.getString("FPBM");
					this.fpdm = fpdm;
					this.fpqsh = fpqsh;
					this.fpjzh = rs.getInt("FPJZH");
					this.fpdw = rs.getInt("FPDW");
					this.nsrwjbm = rs.getString("NSRWJBM");
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
	}

	/**
	 * @param nsrwjbm
	 * @return int
	 * @roseuid 4CA06460007D
	 */
	public int getFromZG(String nsrwjbm) {
		return 0;
	}

	/**
	 * @return int
	 * @roseuid 4CA064FF02CE
	 */
	public int add(ArrayList alFpj) {
		int result1 = 1;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				conn.setAutoCommit(false);
				Statement stsm = conn.createStatement();
				Iterator it = alFpj.iterator();
				while (it.hasNext()) {
					HashMap hmFpj = (HashMap) it.next();
					String sql = "insert into SKQ_FPJ(FPBM,FPDM,FPQSH,FPJZH,FPDW,NSRWJBM,FPLGRQ) values('"
							+ (String) hmFpj.get("fpbm")
							+ "','"
							+ (String) hmFpj.get("fpdm")
							+ "',"
							+ (Integer) hmFpj.get("fpqsh")
							+ ","
							+ (Integer) hmFpj.get("fpjzh")
							+ ","
							+ (Integer) hmFpj.get("fpdw")
							+ ",'"
							+ (String) hmFpj.get("nsrwjbm")
							+ "','"
							+ (String) hmFpj.get("fplgrq") + "')";
					stsm.addBatch(sql);

					/*
					 * int fpqsh = (Integer) hmFpj.get("fpqsh"); int fpjzh =
					 * (Integer) hmFpj.get("fpjzh");
					 * 
					 * for (int i = fpqsh; i <= fpjzh; i++) { String sql1 =
					 * "insert into SKQ_FPKJ(NSRWJBM,JQBH,FPDM,FPHM,QRBZ,STATUS) values('"
					 * + (String) hmFpj.get("nsrwjbm") + "','" + (String)
					 * hmFpj.get("jqbh") + "','" + (String) hmFpj.get("fpdm") +
					 * "'," + i + ",0,0)"; stsm.addBatch(sql1); }
					 */
					
					String sql2 = "insert into SKQ_FPJMX(FPBM,FPDM,FPQSH,FPJZH,FPDW,NSRWJBM,FPLGRQ,JQBH,FPXFZT,FPLGZT,MXSCZT,FPSYZT) values('"
						+ (String) hmFpj.get("fpbm")
						+ "','"
						+ (String) hmFpj.get("fpdm")
						+ "',"
						+ (Integer) hmFpj.get("fpqsh")
						+ ","
						+ (Integer) hmFpj.get("fpjzh")
						+ ","
						+ (Integer) hmFpj.get("fpdw")
						+ ",'"
						+ (String) hmFpj.get("nsrwjbm")
						+ "','"
						+ (String) hmFpj.get("fplgrq")
						+ "','"
						+ (String) hmFpj.get("jqbh") + "',0,1,0,'wsy')";
					stsm.addBatch(sql2);
				}
				
				
				
				
//				ArrayList alFpjmx = this.splitFp(alFpj);
//				Iterator it1 = alFpjmx.iterator();
//				while (it1.hasNext()) {
//					HashMap hmFpjmx = (HashMap) it1.next();
//					String sql2 = "insert into SKQ_FPJMX(FPBM,FPDM,FPQSH,FPJZH,FPDW,NSRWJBM,FPLGRQ,JQBH,FPXFZT,FPLGZT,MXSCZT) values('"
//							+ (String) hmFpjmx.get("fpbm")
//							+ "','"
//							+ (String) hmFpjmx.get("fpdm")
//							+ "',"
//							+ (Integer) hmFpjmx.get("fpqsh")
//							+ ","
//							+ (Integer) hmFpjmx.get("fpjzh")
//							+ ","
//							+ (Integer) hmFpjmx.get("fpdw")
//							+ ",'"
//							+ (String) hmFpjmx.get("nsrwjbm")
//							+ "','"
//							+ (String) hmFpjmx.get("fplgrq")
//							+ "','"
//							+ (String) hmFpjmx.get("jqbh") + "',0,1,0)";
//					stsm.addBatch(sql2);
//				}
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
			e.printStackTrace();
		}
		return result1;
	}

	/**
	 * @param fpbm
	 * @param fpdm
	 * @param fpqsh
	 * @param fpjzh
	 * @param fpdw
	 * @param nsrwjbm
	 * @return int
	 * @roseuid 4CA065030128
	 */
	public int add(String fpbm, String fpdm, int fpqsh, int fpjzh, int fpdw,
			String nsrwjbm) {
		return 0;
	}
	//addÍøÂç¿ªÆ±
	
	public int addWlkp(ArrayList alFpj) {
		int result1 = 1;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				conn.setAutoCommit(false);
				Statement stsm = conn.createStatement();
				Iterator it = alFpj.iterator();
				while (it.hasNext()) {
					HashMap hmFpj = (HashMap) it.next();
					String sql = "insert into SKQ_FPJ(FPBM,FPDM,FPQSH,FPJZH,FPDW,NSRWJBM,FPLGRQ) values('"
							+ (String) hmFpj.get("fpbm")
							+ "','"
							+ (String) hmFpj.get("fpdm")
							+ "',"
							+ (Integer) hmFpj.get("fpqsh")
							+ ","
							+ (Integer) hmFpj.get("fpjzh")
							+ ","
							+ (Integer) hmFpj.get("fpdw")
							+ ",'"
							+ (String) hmFpj.get("nsrwjbm")
							+ "','"
							+ (String) hmFpj.get("fplgrq") + "')";
					stsm.addBatch(sql);
					Jcxx jcxx=Jcxx.getJcxx();
					int fpzs=jcxx.getFpzs();
                ArrayList arrayList=Util.spliteFplg((String) hmFpj.get("fpdm"), (Integer) hmFpj.get("fpqsh"), (Integer) hmFpj.get("fpjzh"),fpzs);
					for(int i=0;i<arrayList.size();i++){
						HashMap map=(HashMap)arrayList.get(i);
                String sql2 = "insert into SKQ_FPJMX(FPBM,FPDM,FPQSH,FPJZH,FPDW,NSRWJBM,FPLGRQ,JQBH,FPXFZT,FPLGZT,MXSCZT,FPSYZT) values('"
						+ (String) hmFpj.get("fpbm")
						+ "','"
						+ (String) hmFpj.get("fpdm")
						+ "',"
						+ (String)map.get("FPQSH")
						+ ","
						+ (String) map.get("FPJZH")
						+ ","
						+ 1
						+ ",'"
						+ (String) hmFpj.get("nsrwjbm")
						+ "','"
						+ (String) hmFpj.get("fplgrq")
						+ "','"
						+ (String) hmFpj.get("jqbh") + "',0,1,0,'wsy')";
					stsm.addBatch(sql2);
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
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			result1 = -1;
			e.printStackTrace();
		}
		return result1;
	}
	/**
	 * @param nsrwjbm
	 * @param fpbm
	 * @param fpdm
	 * @param fpqsh
	 * @param fpjzh
	 * @return ArrayList
	 * @roseuid 4CA065AC030D
	 */
	public ArrayList selectAll(String nsrwjbm, String fpbm, String fpdm,
			int fpqsh, int fpjzh) {
		return null;
	}

	/**
	 * @param fpdm
	 * @param fpqsh
	 * @param fpjzh
	 * @return ArrayList
	 * @roseuid 4CA067140119
	 */
	public ArrayList selectFpjmx(String fpdm, int fpqsh, int fpjzh) {
		return null;
	}

	private ArrayList splitFp(ArrayList hqfp) {
		ArrayList split = new ArrayList();
		Iterator it = hqfp.iterator();
		while (it.hasNext()) {
			HashMap old = (HashMap) it.next();
			int sl = (Integer) old.get("fpdw");
			HashMap newhm = new HashMap();
			if (sl == 1) {
				split.add(old);
			} else {
				int start = (Integer) old.get("fpqsh");
				int end = (Integer) old.get("fpjzh");
				int fpsl = end - start + 1;
				int fpzs = fpsl / sl;

				for (int i = 0; i < sl; i++) {
					newhm = new HashMap();
					newhm.put("nsrwjbm", (String) old.get("nsrwjbm"));
					newhm.put("jqbh", (String) old.get("jqbh"));
					newhm.put("fpbm", (String) old.get("fpbm"));
					newhm.put("fpdm", (String) old.get("fpdm"));
					newhm.put("fpdw", 1);
					newhm.put("fpqsh", start);
					newhm.put("fpjzh", start + fpzs - 1);
					newhm.put("fplgrq", (String) old.get("fplgrq"));

					split.add(newhm);
					start = start + fpzs;
				}
			}

		}
		return split;
	}
	
	
	public String getFpbmStr(String nsrwjbm) {
		String fpbmStr = "";
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select a.*,b.SZBM,b.SMMC,b.SMJC,b.SL,b.SMSY,b.FPBM from SKQ_JQSZSM a left outer join SKQ_PMSZ b on a.SMBM=b.SMBM where a.JQBH in(select JQBH from SKQ_JQXX where NSRWJBM='"+nsrwjbm+"' and STATUS=1)";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					if("".equals(fpbmStr)){
						fpbmStr = "'"+rs.getString("FPBM")+"'";
					}
					else{
						fpbmStr = fpbmStr+",'"+rs.getString("FPBM")+"'";
					}
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
		return fpbmStr;
	}
	
	
	public ArrayList selectFpFromZg(String sqlStr, String nsrwjbm,
			String fpbm) {
		ArrayList al = null;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select FPBM,ZG_FHDTJC,QH_FHDTJC,ZH_FHDTJC,YH_ID,FS_FHDTJC from FP_FHDTJC where YH_ID='"
						+ nsrwjbm
						+ "' and FPBM='"
						+ fpbm + "'" + sqlStr;
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				al = new ArrayList();
				while (rs.next()) {
					HashMap hm = new HashMap();
					hm.put("fpbm", rs.getString("FPBM").trim());
					hm.put("fpdm", rs.getString("ZG_FHDTJC").trim());
					hm.put("fpqsh", rs.getString("QH_FHDTJC"));
					hm.put("fpjzh", rs.getString("ZH_FHDTJC"));
					hm.put("nsrwjbm", rs.getString("YH_ID"));
					hm.put("fpdw", Integer.valueOf(rs.getInt("FS_FHDTJC")));

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

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getFpdm() {
		return fpdm;
	}

	public void setFpdm(String fpdm) {
		this.fpdm = fpdm;
	}

	public int getFpqsh() {
		return fpqsh;
	}

	public void setFpqsh(int fpqsh) {
		this.fpqsh = fpqsh;
	}

	public int getFpjzh() {
		return fpjzh;
	}

	public void setFpjzh(int fpjzh) {
		this.fpjzh = fpjzh;
	}

	public int getFpdw() {
		return fpdw;
	}

	public void setFpdw(int fpdw) {
		this.fpdw = fpdw;
	}

	public String getFpbm() {
		return fpbm;
	}

	public void setFpbm(String fpbm) {
		this.fpbm = fpbm;
	}

	public String getNsrwjbm() {
		return nsrwjbm;
	}

	public void setNsrwjbm(String nsrwjbm) {
		this.nsrwjbm = nsrwjbm;
	}

	public String getFplgrq() {
		return fplgrq;
	}

	public void setFplgrq(String fplgrq) {
		this.fplgrq = fplgrq;
	}

}
