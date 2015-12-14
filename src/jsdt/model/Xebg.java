package jsdt.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jsdt.tools.DBConnection;
import jsdt.tools.Util;

public class Xebg extends baseClass {
	private int sid;
	private String nsrwjbm;
	private String nsrmc;
	private String jqbh;
	private double syljkpxe;
	private double syljtpxe;
	private double sqljkpxe;
	private double sqljtpxe;
	private double pzljkpxe;
	private double pzljtpxe;
	private String bgsm;
	private String sqsj;
	private int clbz;
	private String clz;
	private String clyj;
	private String clsj;
	private int xzbs;

	/**
	 * @roseuid 4CA19C4D034B
	 */
	public Xebg() {

	}

	/**
	 * @param sid
	 * @roseuid 4CA0A31B0203
	 */
	public Xebg(int sid) {
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select a.*,b.NSRMC from SKQ_XEBG a left outer join SKQ_NSRXX b on a.NSRWJBM=b.NSRWJBM where a.SID="
						+ sid;
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					this.sid = rs.getInt("SID");
					this.nsrwjbm = rs.getString("NSRWJBM");
					this.nsrmc = Util.iso8859togbk(rs.getString("NSRMC"));
					this.jqbh = rs.getString("JQBH");
					this.syljkpxe = rs.getDouble("SYLJKPXE");
					this.syljtpxe = rs.getDouble("SYLJTPXE");
					this.sqljkpxe = rs.getDouble("SQLJKPXE");
					this.sqljtpxe = rs.getDouble("SQLJTPXE");
					this.pzljkpxe = rs.getDouble("PZLJKPXE");
					this.pzljtpxe = rs.getDouble("PZLJTPXE");
					this.bgsm = Util.iso8859togbk(rs.getString("BGSM"));
					this.sqsj = rs.getString("SQSJ").substring(0, 10);
					this.clbz = rs.getInt("CLBZ");
					this.clz = rs.getString("CLZ");
					this.clyj = Util.iso8859togbk(rs.getString("CLYJ"));
					if (rs.getString("CLSJ") == null) {
						this.clsj = "";
					} else {
						this.clsj = rs.getString("CLSJ").substring(0, 10);
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
	}

	/**
	 * @return int
	 * @roseuid 4CA0A33302FD
	 */
	public int add() {
		int result = -1;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				PreparedStatement ps = null;
				ps = conn
						.prepareStatement("insert into SKQ_XEBG (NSRWJBM,JQBH,SYLJKPXE,SYLJTPXE,SQLJKPXE,SQLJTPXE,BGSM,SQSJ,CLBZ) values(?,?,?,?,?,?,?,?,?)");
				ps.setString(1, this.nsrwjbm);
				ps.setString(2, this.jqbh);
				ps.setDouble(3, this.syljkpxe);
				ps.setDouble(4, this.syljtpxe);
				ps.setDouble(5, this.sqljkpxe);
				ps.setDouble(6, this.sqljtpxe);
				ps.setString(7, Util.gbkto8859(this.bgsm));
				ps.setString(8, this.sqsj);
				ps.setInt(9, this.clbz);
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
	 * @roseuid 4CA0A409008C
	 */
	public int bgsh() {
		int result = -1;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "update SKQ_XEBG set  CLBZ=" + this.clbz
						+ ",PZLJKPXE=" + this.pzljkpxe + ",PZLJTPXE="
						+ this.pzljtpxe + ",CLZ='" + this.clz + "',CLYJ='"
						+ Util.gbkto8859(this.clyj) + "',CLSJ='" + this.clsj
						+ "',XZBS="+this.xzbs+" where SID=" + this.sid;
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

	// 查询限额变更
	public ArrayList selectAll() {
		ArrayList al = null;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select a.*,b.NSRMC from SKQ_XEBG a left outer join SKQ_NSRXX b on a.NSRWJBM=b.NSRWJBM order by a.CLBZ asc,a.SQSJ desc";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				al = new ArrayList();
				while (rs.next()) {
					Xebg xebg = new Xebg();
					xebg.setSid(rs.getInt("SID"));
					xebg.setNsrwjbm(rs.getString("NSRWJBM"));
					xebg.setNsrmc(Util.iso8859togbk(rs.getString("NSRMC")));
					xebg.setJqbh(rs.getString("JQBH"));
					xebg.setSyljkpxe(rs.getDouble("SYLJKPXE"));
					xebg.setSyljtpxe(rs.getDouble("SYLJTPXE"));
					xebg.setSqljkpxe(rs.getDouble("SQLJKPXE"));
					xebg.setSqljtpxe(rs.getDouble("SQLJTPXE"));
					xebg.setPzljkpxe(rs.getDouble("PZLJKPXE"));
					xebg.setPzljtpxe(rs.getDouble("PZLJTPXE"));
					xebg.setBgsm(Util.iso8859togbk(rs.getString("BGSM")));
					xebg.setSqsj(rs.getString("SQSJ").substring(0, 10));
					xebg.setClbz(rs.getInt("CLBZ"));
					xebg.setClz(rs.getString("CLZ"));
					xebg.setClyj(Util.iso8859togbk(rs.getString("CLYJ")));
					xebg.setClsj(rs.getString("CLSJ") == null ? "" : rs
							.getString("CLSJ").substring(0, 10));
					al.add(xebg);
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

	public double getSqljkpxe() {
		return sqljkpxe;
	}

	public void setSqljkpxe(double sqljkpxe) {
		this.sqljkpxe = sqljkpxe;
	}

	public double getSqljtpxe() {
		return sqljtpxe;
	}

	public void setSqljtpxe(double sqljtpxe) {
		this.sqljtpxe = sqljtpxe;
	}

	public double getPzljkpxe() {
		return pzljkpxe;
	}

	public void setPzljkpxe(double pzljkpxe) {
		this.pzljkpxe = pzljkpxe;
	}

	public double getPzljtpxe() {
		return pzljtpxe;
	}

	public void setPzljtpxe(double pzljtpxe) {
		this.pzljtpxe = pzljtpxe;
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

	public String getNsrmc() {
		return nsrmc;
	}

	public void setNsrmc(String nsrmc) {
		this.nsrmc = nsrmc;
	}

	public double getSyljkpxe() {
		return syljkpxe;
	}

	public void setSyljkpxe(double syljkpxe) {
		this.syljkpxe = syljkpxe;
	}

	public double getSyljtpxe() {
		return syljtpxe;
	}

	public void setSyljtpxe(double syljtpxe) {
		this.syljtpxe = syljtpxe;
	}

	public int getXzbs() {
		return xzbs;
	}

	public void setXzbs(int xzbs) {
		this.xzbs = xzbs;
	}

}
