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

public class Szsm extends baseClass {
	private int sid;
	private String szbm;
	private String smbm;
	private String smmc;
	private String smjc;
	private double sl;
	private int smsy;
	private String fpbm;
	private int hdkpbl;
	private double czkpxe;
	private int cezs;
	private int status;

	/**
	 * @roseuid 4CA19C440128
	 */
	public Szsm() {

	}

	/**
	 * @param smbm
	 * @roseuid 4CA02D7E02E6
	 */
	public Szsm(String smbm) {
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select * from SKQ_PMSZ where SMBM='" + smbm + "'";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					this.sid = rs.getInt("SID");
					this.szbm = rs.getString("SZBM");
					this.smbm = rs.getString("SMBM");
					this.smmc = Util.iso8859togbk(rs.getString("SMMC"));
					this.smjc = Util.iso8859togbk(rs.getString("SMJC"));
					this.sl = rs.getDouble("SL");
					this.smsy = rs.getInt("SMSY");
					this.fpbm = rs.getString("FPBM");
					this.hdkpbl = rs.getInt("HDKPBL");
					this.czkpxe = rs.getDouble("CZKPXE");
					this.cezs = rs.getInt("CEZS");
					this.status = rs.getInt("STATUS");
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
	 * @return int
	 * @roseuid 4CA02DDD01AD
	 */
	public ArrayList getFromZG(String smbmStr) {
		// int result = -1;
		Connection conn = null;
		ArrayList al = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select SMBM,SZBM,SMMC,SMJC,FDSL from BM_SM where SZBM='03' and QYBJ_SM='1' and SMBM not in("+smbmStr+") order by SMBM";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				al = new ArrayList();
				while (rs.next()) {
					HashMap hm = new HashMap();
					hm.put("SMBM", rs.getString("SMBM"));
					hm.put("SZBM", rs.getString("SZBM"));
					hm.put("SMMC", Util.iso8859togbk(rs.getString("SMMC")));
					hm.put("SMJC", Util.iso8859togbk(rs.getString("SMJC")));
					hm.put("SL", Double.valueOf(rs.getDouble("FDSL")));

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

	/**
	 * @return int
	 * @roseuid 4CA02F370075
	 */
	public int update() {
		int result = -1;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "update SKQ_PMSZ set FPBM='" + this.fpbm
						+ "',HDKPBL=" + this.hdkpbl + ",CZKPXE=" + this.czkpxe
						+ ",CEZS=" + this.cezs + ",STATUS=" + this.status
						+ " where SID=" + this.sid;
				PreparedStatement ps = conn.prepareStatement(sql);
				result = ps.executeUpdate();
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
		return result;
	}

	/**
	 * @param fpbm
	 * @param hdkpbl
	 * @param czkpxe
	 * @param cezs
	 * @param status
	 * @return int
	 * @roseuid 4CA02F4A02F6
	 */
	public int update(String fpbm, int hdkpbl, double czkpxe, int cezs,
			int status, int sid) {
		int result = -1;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "update SKQ_PMSZ set FPBM='" + fpbm + "',HDKPBL="
						+ hdkpbl + ",CZKPXE=" + czkpxe + ",CEZS=" + cezs
						+ ",STATUS=" + status + " where SID=" + sid;
				PreparedStatement ps = conn.prepareStatement(sql);
				result = ps.executeUpdate();
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
		return result;
	}
	
	public int updateAll(ArrayList al) {
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
						Szsm szsm1 = (Szsm)it.next();
	
						String sql = "update SKQ_PMSZ set FPBM='" + szsm1.getFpbm() + "',HDKPBL="
						+ szsm1.getHdkpbl() + ",CZKPXE=" + szsm1.getCzkpxe() + ",CEZS=" + szsm1.getCezs()
						+ ",STATUS=" + szsm1.getStatus() + " where SID=" + szsm1.getSid();
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
				//e.getMessage();
				e.printStackTrace();
			}
			finally {
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

	/**
	 * @return int
	 * @roseuid 4CA02FCC020B
	 */
	public int add() {
		int result = -1;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				PreparedStatement ps = null;
//				int MAXSMSY = 0;
//				String sql = "select max(SMSY) as MAXSMSY from SKQ_PMSZ";
//				ps = conn.prepareStatement(sql);
//				ResultSet rs = ps.executeQuery();
//				while (rs.next()) {
//					MAXSMSY = rs.getInt("MAXSMSY");
//				}
//				rs.close();
//				ps.close();
//
//				MAXSMSY = MAXSMSY+1;
				ps = conn
						.prepareStatement("insert into SKQ_PMSZ (SMBM,SZBM,SMMC,SMJC,SL,SMSY,CEZS,STATUS,FPBM,HDKPBL,CZKPXE) values(?,?,?,?,?,?,?,?,?,?,?)");
				ps.setString(1, this.smbm);
				ps.setString(2, this.szbm);
				ps.setString(3, Util.gbkto8859(this.smmc));
				ps.setString(4, Util.gbkto8859(this.smjc));
				ps.setDouble(5, this.sl);
				ps.setInt(6, this.smsy);
				ps.setInt(7, this.cezs);
				ps.setInt(8, this.status);
				ps.setString(9, this.fpbm);
				ps.setInt(10, this.hdkpbl);
				ps.setDouble(11, this.czkpxe);
				result = ps.executeUpdate();
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
		return result;
	}

	/**
	 * @param szbm
	 * @param smbm
	 * @param smmc
	 * @param sl
	 * @param smsy
	 * @return int
	 * @roseuid 4CA02FD500E2
	 */
	public int add(String szbm, String smbm, String smmc, String smjc, double sl) {
		int result = -1;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				PreparedStatement ps = null;
				int MAXSMSY = 0;
				String sql = "select max(SMSY) as MAXSMSY from SKQ_PMSZ";
				ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					MAXSMSY = rs.getInt("MAXSMSY");
				}
				rs.close();
				ps.close();

				MAXSMSY += MAXSMSY;
				ps = conn
						.prepareStatement("insert into SKQ_PMSZ (SMBM,SZBM,SMMC,SMJC,SL,SMSY,CEZS,STATUS) values(?,?,?,?,?,?,?,?)");
				ps.setString(1, smbm);
				ps.setString(2, szbm);
				ps.setString(3, Util.gbkto8859(smmc));
				ps.setString(4, Util.gbkto8859(smjc));
				ps.setDouble(5, sl);
				ps.setInt(6, MAXSMSY);
				ps.setInt(7, 0);
				ps.setInt(8, 0);
				result = ps.executeUpdate();
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
		return result;
	}
	
	public int addZg(ArrayList al) {
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
	
						String sql1 = "insert into SKQ_PMSZ (SMBM,SZBM,SMMC,SMJC,SL,SMSY,CEZS,STATUS) values('"+(String)hm.get("smbm")+"','"+(String)hm.get("szbm")+"','"+Util.gbkto8859((String)hm.get("smmc"))+"','"+Util.gbkto8859((String)hm.get("smjc"))+"',"+(Double)hm.get("sl")+","+(Integer)hm.get("smsy")+",0,0)";
						stsm.addBatch(sql1);
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
				e.printStackTrace();
			}
		}
		return result1;
	}

	/**
	 * @return ArrayList
	 * @roseuid 4CA0304E0008
	 */
	public ArrayList selectAll() {
		ArrayList al = null;
		Connection conn = null;
		try {
//			String propfile_path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
//			System.out.println("Propertiese File path : "+propfile_path);
//			SYSCOMMON.dbpool = new DBConnection("DT",propfile_path);
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select * from SKQ_PMSZ";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				al = new ArrayList();
				while (rs.next()) {
					Szsm szsm = new Szsm();
					szsm.setSid(rs.getInt("SID"));
					szsm.setSmbm(rs.getString("SMBM"));
					szsm.setSzbm(rs.getString("SZBM"));
					szsm.setSmmc(Util.iso8859togbk(rs.getString("SMMC")));
					szsm.setSmjc(Util.iso8859togbk(rs.getString("SMJC")));
					szsm.setSl(rs.getDouble("SL"));
					szsm.setSmsy(rs.getInt("SMSY"));
					szsm.setFpbm(rs.getString("FPBM"));
					szsm.setHdkpbl(rs.getInt("HDKPBL"));
					szsm.setCzkpxe(rs.getDouble("CZKPXE"));
					szsm.setCezs(rs.getInt("CEZS"));
					szsm.setStatus(rs.getInt("STATUS"));

					al.add(szsm);
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

	/**
	 * @return ArrayList
	 * @roseuid 4CA068A90242
	 */
	public String selectQy() {
		String str = null;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select SMBM from SKQ_PMSZ where STATUS = 1";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					str = str + rs.getString("SMBM") + ",";
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
		return str;
	}
	
	public String selectAllStr() {
		String str = null;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select SMBM from SKQ_PMSZ";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					if(str==null){
						str = "'"+rs.getString("SMBM")+"'";
					}
					else{
						str = str+",'"+rs.getString("SMBM")+"'";
					}
					//str = str + rs.getString("SMBM") + ",";
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
		return str;
	}
	
	public String selectAllQyStr() {
		String str = null;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select SMBM from SKQ_PMSZ where STATUS=1";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					if(str==null){
						str = "'"+rs.getString("SMBM")+"'";
					}
					else{
						str = str+",'"+rs.getString("SMBM")+"'";
					}
					//str = str + rs.getString("SMBM") + ",";
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
		return str;
	}
	
	public int getMaxSmsy() {
		int smsy = 0;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select max(SMSY) as SMSY from SKQ_PMSZ";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					smsy = rs.getInt("SMSY");
				}
				smsy+=1;
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
		return smsy;
	}
	
	public int getMaxHdkpbl(String smbmStr) {
		int hdkpbl = 0;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select max(HDKPBL) as HDKPBL from SKQ_PMSZ where SMBM in("+smbmStr+")";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					hdkpbl = rs.getInt("HDKPBL");
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
		return hdkpbl;
	}
	
	public double getMaxCzkpxe(String smbmStr) {
		double czkpxe = 0;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select max(CZKPXE) as CZKPXE from SKQ_PMSZ where SMBM in("+smbmStr+")";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					czkpxe = rs.getDouble("CZKPXE");
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
		return czkpxe;
	}
	
	public ArrayList selectAllQy() {
		ArrayList al = null;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select * from SKQ_PMSZ where STATUS=1";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				al = new ArrayList();
				while (rs.next()) {
					Szsm szsm = new Szsm();
					szsm.setSid(rs.getInt("SID"));
					szsm.setSmbm(rs.getString("SMBM"));
					szsm.setSzbm(rs.getString("SZBM"));
					szsm.setSmmc(Util.iso8859togbk(rs.getString("SMMC")));
					szsm.setSmjc(Util.iso8859togbk(rs.getString("SMJC")));
					szsm.setSl(rs.getDouble("SL"));
					szsm.setSmsy(rs.getInt("SMSY"));
					szsm.setFpbm(rs.getString("FPBM"));
					szsm.setHdkpbl(rs.getInt("HDKPBL"));
					szsm.setCzkpxe(rs.getDouble("CZKPXE"));
					szsm.setCezs(rs.getInt("CEZS"));
					szsm.setStatus(rs.getInt("STATUS"));

					al.add(szsm);
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
	
	public ArrayList selectBySzsmStr(String szsmStr) {
		ArrayList al = null;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select * from SKQ_PMSZ where STATUS=1 and SMBM in("+szsmStr+")";
				//System.out.println("sql="+sql);
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				al = new ArrayList();
				while (rs.next()) {
					HashMap hm = new HashMap();
					hm.put("smbm", rs.getString("SMBM"));
					hm.put("szbm", rs.getString("SZBM"));
					hm.put("smmc", Util.iso8859togbk(rs.getString("SMMC")));
					hm.put("smjc", Util.iso8859togbk(rs.getString("SMJC")));
					hm.put("sl", rs.getDouble("SL"));

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

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getSzbm() {
		return szbm;
	}

	public void setSzbm(String szbm) {
		this.szbm = szbm;
	}

	public String getSmbm() {
		return smbm;
	}

	public void setSmbm(String smbm) {
		this.smbm = smbm;
	}

	public String getSmmc() {
		return smmc;
	}

	public void setSmmc(String smmc) {
		this.smmc = smmc;
	}

	public String getSmjc() {
		return smjc;
	}

	public void setSmjc(String smjc) {
		this.smjc = smjc;
	}

	public double getSl() {
		return sl;
	}

	public void setSl(double sl) {
		this.sl = sl;
	}

	public int getSmsy() {
		return smsy;
	}

	public void setSmsy(int smsy) {
		this.smsy = smsy;
	}

	public String getFpbm() {
		return fpbm;
	}

	public void setFpbm(String fpbm) {
		this.fpbm = fpbm;
	}

	public int getHdkpbl() {
		return hdkpbl;
	}

	public void setHdkpbl(int hdkpbl) {
		this.hdkpbl = hdkpbl;
	}

	public double getCzkpxe() {
		return czkpxe;
	}

	public void setCzkpxe(double czkpxe) {
		this.czkpxe = czkpxe;
	}

	public int getCezs() {
		return cezs;
	}

	public void setCezs(int cezs) {
		this.cezs = cezs;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
