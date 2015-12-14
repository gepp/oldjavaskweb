package jsdt.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import jsdt.tools.DBConnection;
import jsdt.tools.Util;

public class Pmbg extends baseClass {
	private int sid;
	private String nsrwjbm;
	private String nsrmc;
	private String jqbh;
	private String ysmbm;
	private String bgsmbm;
	private String bgsm;
	private String sqsj;
	private int clbz;
	private String clz;
	private String clyj;
	private String clsj;
	private int xzbs;
	private ArrayList alYszsm;
	private ArrayList alBgszsm;

	/**
	 * @roseuid 4CA19C4D002E
	 */
	public Pmbg() {

	}

	/**
	 * @param sid
	 * @roseuid 4CA0759D0261
	 */
	public Pmbg(int sid) {
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select a.*,b.NSRMC from SKQ_PMBG a left outer join SKQ_NSRXX b on a.NSRWJBM=b.NSRWJBM where a.SID="
						+ sid;
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					this.sid = rs.getInt("SID");
					this.nsrwjbm = rs.getString("NSRWJBM");
					this.nsrmc = Util.iso8859togbk(rs.getString("NSRMC"));
					this.jqbh = rs.getString("JQBH");
					this.ysmbm = rs.getString("YSMBM");
					this.bgsmbm = rs.getString("BGSMBM");
					this.bgsm = Util.iso8859togbk(rs.getString("BGSM"));
					this.sqsj = rs.getString("SQSJ");
					this.clbz = rs.getInt("CLBZ");
					this.clz = rs.getString("CLZ");
					this.clyj = Util.iso8859togbk(rs.getString("CLYJ"));
					this.clsj = rs.getString("CLSJ");
				}

				rs.close();
				ps.close();

				ArrayList alYszsm = new ArrayList();
				ArrayList alBgszsm = new ArrayList();
				Szsm szsm = new Szsm();
				if (this.ysmbm != null || !"".equals(this.ysmbm)) {
					String[] yszsmArr = this.ysmbm.split(",");
					String yszsmStr = "";
					for (int i = 0; i < yszsmArr.length; i++) {
						if ("".equals(yszsmStr)) {
							yszsmStr = "'" + yszsmArr[i] + "'";
						} else {
							yszsmStr = yszsmStr + ",'" + yszsmArr[i] + "'";
						}
					}

					alYszsm = szsm.selectBySzsmStr(yszsmStr);
				}
				if (this.bgsmbm != null || !"".equals(this.bgsmbm)) {
					String[] bgszsmArr = this.bgsmbm.split(",");
					String bgszsmStr = "";
					for (int i = 0; i < bgszsmArr.length; i++) {
						if ("".equals(bgszsmStr)) {
							bgszsmStr = "'" + bgszsmArr[i] + "'";
						} else {
							bgszsmStr = bgszsmStr + ",'" + bgszsmArr[i] + "'";
						}
					}

					alBgszsm = szsm.selectBySzsmStr(bgszsmStr);
				}

				this.alBgszsm = alBgszsm;
				this.alYszsm = alYszsm;
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
	 * @roseuid 4CA075BB0109
	 */
	public int add() {
		int result = -1;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				PreparedStatement ps = null;
				ps = conn
						.prepareStatement("insert into SKQ_PMBG (NSRWJBM,JQBH,YSMBM,BGSMBM,BGSM,SQSJ,CLBZ) values(?,?,?,?,?,?,?)");
				ps.setString(1, this.nsrwjbm);
				ps.setString(2, this.jqbh);
				ps.setString(3, this.ysmbm);
				ps.setString(4, this.bgsmbm);
				ps.setString(5, Util.gbkto8859(this.bgsm));
				ps.setString(6, this.sqsj);
				ps.setInt(7, this.clbz);
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
	 * @return int
	 * @roseuid 4CA09EB0038A
	 */
	public int bgsh() {
		int result1 = 1;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				conn.setAutoCommit(false);
				Statement stsm = conn.createStatement();

				String sql = "update SKQ_PMBG set  CLBZ=" + this.clbz
						+ ",CLZ='" + this.clz + "',CLYJ='"
						+ Util.gbkto8859(this.clyj) + "',CLSJ='" + this.clsj
						+ "',XZBS=" + this.xzbs + " where SID=" + this.sid;
				stsm.addBatch(sql);

				if (this.clbz == 1) {
					String smbmStr = this.bgsmbm;
					if (smbmStr != null && !"".equals(smbmStr)) {
						String sql1 = "delete from SKQ_JQSZSM where JQBH='"
								+ this.jqbh + "'";
						stsm.addBatch(sql1);
						String[] smbmArr = smbmStr.split(",");
						for (int i = 0; i < smbmArr.length; i++) {
							String smbm = smbmArr[i];

							String sql2 = "insert into SKQ_JQSZSM(JQBH,SMBM) values('"
									+ this.jqbh + "','" + smbm + "')";
							System.out.println("sql2=="+sql2);
							stsm.addBatch(sql2);
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

			result1 = -1;
			e.printStackTrace();
		}
		return result1;
	}

	// 查询限额变更
	public ArrayList selectAll() {
		ArrayList al = null;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select a.*,b.NSRMC from SKQ_PMBG a left outer join SKQ_NSRXX b on a.NSRWJBM=b.NSRWJBM order by a.CLBZ asc,a.SQSJ desc";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				al = new ArrayList();
				while (rs.next()) {
					Pmbg pmbg = new Pmbg();
					pmbg.setSid(rs.getInt("SID"));
					pmbg.setNsrwjbm(rs.getString("NSRWJBM"));
					pmbg.setNsrmc(Util.iso8859togbk(rs.getString("NSRMC")));
					pmbg.setJqbh(rs.getString("JQBH"));
					pmbg.setYsmbm(rs.getString("YSMBM"));
					pmbg.setBgsmbm(rs.getString("BGSMBM"));
					pmbg.setBgsm(rs.getString("BGSM"));
					pmbg.setSqsj(rs.getString("SQSJ").substring(0, 10));
					pmbg.setClbz(rs.getInt("CLBZ"));
					pmbg.setClz(rs.getString("CLZ"));
					pmbg.setClyj(Util.iso8859togbk(rs.getString("CLYJ")));
					al.add(pmbg);
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

	public String getNsrwjbm() {
		return nsrwjbm;
	}

	public void setNsrwjbm(String nsrwjbm) {
		this.nsrwjbm = nsrwjbm;
	}

	public String getJqbh() {
		return jqbh;
	}

	public void setJqbh(String jqbh) {
		this.jqbh = jqbh;
	}

	public String getYsmbm() {
		return ysmbm;
	}

	public void setYsmbm(String ysmbm) {
		this.ysmbm = ysmbm;
	}

	public String getBgsmbm() {
		return bgsmbm;
	}

	public void setBgsmbm(String bgsmbm) {
		this.bgsmbm = bgsmbm;
	}

	public String getBgsm() {
		return bgsm;
	}

	public void setBgsm(String bgsm) {
		this.bgsm = bgsm;
	}

	public int getClbz() {
		return clbz;
	}

	public void setClbz(int clbz) {
		this.clbz = clbz;
	}

	public String getClz() {
		return clz;
	}

	public void setClz(String clz) {
		this.clz = clz;
	}

	public String getClyj() {
		return clyj;
	}

	public void setClyj(String clyj) {
		this.clyj = clyj;
	}

	public String getNsrmc() {
		return nsrmc;
	}

	public void setNsrmc(String nsrmc) {
		this.nsrmc = nsrmc;
	}

	public String getSqsj() {
		return sqsj;
	}

	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}

	public String getClsj() {
		return clsj;
	}

	public void setClsj(String clsj) {
		this.clsj = clsj;
	}

	public int getXzbs() {
		return xzbs;
	}

	public void setXzbs(int xzbs) {
		this.xzbs = xzbs;
	}

	public ArrayList getAlYszsm() {
		return alYszsm;
	}

	public void setAlYszsm(ArrayList alYszsm) {
		this.alYszsm = alYszsm;
	}

	public ArrayList getAlBgszsm() {
		return alBgszsm;
	}

	public void setAlBgszsm(ArrayList alBgszsm) {
		this.alBgszsm = alBgszsm;
	}

}
