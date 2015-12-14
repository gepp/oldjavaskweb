package jsdt.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import jsdt.tools.DBConnection;
import jsdt.tools.Util;

public class Fpjmx extends baseClass {
	private int sid;
	private String nsrwjbm;
	private String nsrmc;
	private String fpbm;
	private String fpmc;
	private String fpdm;
	private int fpqsh;
	private int fpjzh;
	private int fpdw;
	private String jqbh;
	private int fpxfzt;
	private int fplgzt;
	private int mxsczt;
	private String fplgrq;
	private int hasUsed;
    private int fpzs;
	public int getFpzs() {
		return fpzs;
	}

	public void setFpzs(int fpzs) {
		this.fpzs = fpzs;
	}

	public int getHasUsed() {
		return hasUsed;
	}

	public void setHasUsed(int hasUsed) {
		this.hasUsed = hasUsed;
	}
	private String fpsyzt;
	public String getFpsyzt() {
		return fpsyzt;
	}

	public void setFpsyzt(String fpsyzt) {
		this.fpsyzt = fpsyzt;
	}

	/**
	 * @roseuid 4CA19C4A03D8
	 */
	public Fpjmx() {

	}

	/**
	 * @param fpdm
	 * @param fpqsh
	 * @roseuid 4CA06A7D0177
	 */
	public Fpjmx(String fpdm, int fpqsh) {

	}
	public ArrayList selectFpxfWLKP(String nsrwjbm) {
		ArrayList al = null;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select * from SKQ_FPJMX where NSRWJBM='"
						+ nsrwjbm + "' and FPSYZT='wsy'";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				al = new ArrayList();
				while (rs.next()) {
					Fpjmx fpjmx = new Fpjmx();
					fpjmx.setSid(rs.getInt("SID"));
					fpjmx.setNsrwjbm(rs.getString("NSRWJBM"));
					fpjmx.setFpbm(rs.getString("FPBM"));
					fpjmx.setFpdm(rs.getString("FPDM"));
					fpjmx.setFpqsh(rs.getInt("FPQSH"));
					fpjmx.setFpjzh(rs.getInt("FPJZH"));
					fpjmx.setFpdw(rs.getInt("FPDW"));
					fpjmx.setJqbh(rs.getString("JQBH"));
					fpjmx.setFpxfzt(rs.getInt("FPXFZT"));
					fpjmx.setFplgzt(rs.getInt("FPLGZT"));
					fpjmx.setMxsczt(rs.getInt("MXSCZT"));
					fpjmx.setFplgrq(rs.getString("FPLGRQ").substring(0, 10));
					fpjmx.setFpsyzt(rs.getString("FPSYZT"));
					al.add(fpjmx);
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
	public int add() {
		int result1 = 1;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				conn.setAutoCommit(false);
				Statement stsm = conn.createStatement();

				String sql = "insert into SKQ_FPJMX(FPBM,FPDM,FPQSH,FPJZH,FPDW,NSRWJBM,FPLGRQ,FPXFZT,FPLGZT,MXSCZT) values('"
						+ this.fpbm
						+ "','"
						+ this.fpdm
						+ "',"
						+ this.fpqsh
						+ ","
						+ this.fpjzh
						+ ","
						+ this.fpdw
						+ ",'"
						+ this.nsrwjbm + "','" + this.fplgrq + "',0,2,0)";

				stsm.addBatch(sql);

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

	public int updateXkbz(ArrayList alFpj, int sid) {
		int result1 = 1;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				conn.setAutoCommit(false);
				Statement stsm = conn.createStatement();
				Iterator it = alFpj.iterator();
				while (it.hasNext()) {
					Fpjmx fpjmx = (Fpjmx) it.next();
					String sql = "update SKQ_FPJMX set FPXFZT=1 where FPDM='"
							+ fpjmx.getFpdm() + "' and FPQSH="
							+ fpjmx.getFpqsh();
					stsm.addBatch(sql);

					// int fpqsh = fpjmx.getFpqsh();
					// int fpjzh = fpjmx.getFpjzh();
					// if(sid==0){
					// for (int i = fpqsh; i <= fpjzh; i++) {
					// String sql1 =
					// "insert into SKQ_FPKJ(NSRWJBM,JQBH,FPDM,FPHM,QRBZ,STATUS) values('"
					// + fpjmx.getNsrwjbm()
					// + "','"
					// + fpjmx.getJqbh()
					// + "','" + "00000000"+fpjmx.getFpdm() + "'," + i +
					// ",0,0)";
					// stsm.addBatch(sql1);
					// }
					// }
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
		return result1;
	}

	/**
	 * @param nsrwjbm
	 * @param fpbm
	 * @return ArrayList
	 * @roseuid 4CA06ACD032C
	 */
	public ArrayList selectFpxf(String nsrwjbm, String fpbm) {
		ArrayList al = null;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select * from SKQ_FPJMX where NSRWJBM='"
						+ nsrwjbm + "' and FPBM='" + fpbm + "' and FPXFZT=0";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				al = new ArrayList();
				System.out.println("sql:="+sql);
				while (rs.next()) {
					Fpjmx fpjmx = new Fpjmx();
					fpjmx.setSid(rs.getInt("SID"));
					fpjmx.setNsrwjbm(rs.getString("NSRWJBM"));
					fpjmx.setFpbm(rs.getString("FPBM"));
					fpjmx.setFpdm(rs.getString("FPDM"));
					fpjmx.setFpqsh(rs.getInt("FPQSH"));
					fpjmx.setFpjzh(rs.getInt("FPJZH"));
					fpjmx.setFpdw(rs.getInt("FPDW"));
					fpjmx.setJqbh(rs.getString("JQBH"));
					fpjmx.setFpxfzt(rs.getInt("FPXFZT"));
					fpjmx.setFplgzt(rs.getInt("FPLGZT"));
					fpjmx.setMxsczt(rs.getInt("MXSCZT"));
					fpjmx.setFplgrq(rs.getString("FPLGRQ").substring(0, 10));
					System.out.println(fpjmx.getNsrwjbm()+"纳税人微机编码：");
					al.add(fpjmx);
				}
				System.out.println("al.size()"+al.size());
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

	public ArrayList selectCheckedFpjmx(String strSql) {
		ArrayList al = null;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select * from SKQ_FPJMX where FPXFZT = 0 "
						+ strSql;
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				al = new ArrayList();
				while (rs.next()) {
					Fpjmx fpjmx = new Fpjmx();
					fpjmx.setSid(rs.getInt("SID"));
					fpjmx.setNsrwjbm(rs.getString("NSRWJBM"));
					fpjmx.setFpbm(rs.getString("FPBM"));
					fpjmx.setFpdm(rs.getString("FPDM"));
					fpjmx.setFpqsh(rs.getInt("FPQSH"));
					fpjmx.setFpjzh(rs.getInt("FPJZH"));
					fpjmx.setFpdw(rs.getInt("FPDW"));
					fpjmx.setJqbh(rs.getString("JQBH"));
					fpjmx.setFpxfzt(rs.getInt("FPXFZT"));
					fpjmx.setFplgzt(rs.getInt("FPLGZT"));
					fpjmx.setMxsczt(rs.getInt("MXSCZT"));
					fpjmx.setFplgrq(rs.getString("FPLGRQ").substring(0, 10));

					al.add(fpjmx);
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

	public ArrayList selectBlFpjmx(String nsrwjbm) {
		ArrayList al = null;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select a.*,b.FPMC from SKQ_FPJMX a left outer join SKQ_FP b on a.FPBM=b.FPBM where a.NSRWJBM='"
						+ nsrwjbm + "' and a.FPLGZT=2";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				al = new ArrayList();
				while (rs.next()) {
					Fpjmx fpjmx = new Fpjmx();
					fpjmx.setSid(rs.getInt("SID"));
					fpjmx.setNsrwjbm(rs.getString("NSRWJBM"));
					fpjmx.setFpbm(rs.getString("FPBM"));
					fpjmx.setFpmc(Util.iso8859togbk(rs.getString("FPMC")));
					fpjmx.setFpdm(rs.getString("FPDM"));
					fpjmx.setFpqsh(rs.getInt("FPQSH"));
					fpjmx.setFpjzh(rs.getInt("FPJZH"));
					fpjmx.setFpdw(rs.getInt("FPDW"));
					fpjmx.setJqbh(rs.getString("JQBH"));
					fpjmx.setFpxfzt(rs.getInt("FPXFZT"));
					fpjmx.setFplgzt(rs.getInt("FPLGZT"));
					fpjmx.setMxsczt(rs.getInt("MXSCZT"));
					fpjmx.setFplgrq(rs.getString("FPLGRQ").substring(0, 10));

					al.add(fpjmx);
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

	public String getFpbm() {
		return fpbm;
	}

	public void setFpbm(String fpbm) {
		this.fpbm = fpbm;
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

	public String getJqbh() {
		return jqbh;
	}

	public void setJqbh(String jqbh) {
		this.jqbh = jqbh;
	}

	public int getFpxfzt() {
		return fpxfzt;
	}

	public void setFpxfzt(int fpxfzt) {
		this.fpxfzt = fpxfzt;
	}

	public int getFplgzt() {
		return fplgzt;
	}

	public void setFplgzt(int fplgzt) {
		this.fplgzt = fplgzt;
	}

	public int getMxsczt() {
		return mxsczt;
	}

	public void setMxsczt(int mxsczt) {
		this.mxsczt = mxsczt;
	}

	public String getFplgrq() {
		return fplgrq;
	}

	public void setFplgrq(String fplgrq) {
		this.fplgrq = fplgrq;
	}

	public String getFpmc() {
		return fpmc;
	}

	public void setFpmc(String fpmc) {
		this.fpmc = fpmc;
	}

	public String getNsrmc() {
		return nsrmc;
	}

	public void setNsrmc(String nsrmc) {
		this.nsrmc = nsrmc;
	}

	/**
	 * 2011-3-8发票购买获取最后一次发票代码
	 * 
	 */
	public String getFpjdm() {
		String fpdm = "";
		Fpjmx fpjmx = new Fpjmx();
		String fieldname = "FPDM";
		fpjmx.setTable("SKQ_FPJMX");
		String where = " order by fplgrq asc";
		fpdm = fpjmx.getField(fieldname, where);
		System.out.println(fpdm);
		return fpdm;
	}
    public String toString(Fpjmx fpjmx){
    	String fpjmxStr="";
    	fpjmxStr="发票代码："+fpjmx.getFpdm()+",发票编码："+fpjmx.getFpbm()+",发票单位："+fpjmx.getFpdw()
    	+",发票起始号："+fpjmx.getFpqsh()+"发票截止号："+fpjmx.getFpjzh()+",发票领购日起："+fpjmx.getFplgrq()
    	+",机器编号："
    	+fpjmx.getJqbh()+",纳税人微机编码："+fpjmx.getNsrwjbm();
    	return  fpjmxStr;
    }
	
	public static void main(String[] args) {
		Fpjmx fpjmx = new Fpjmx();
		String fieldname = "FPDM";
		fpjmx.setTable("SKQ_FPJMX");
		String where = " order by fplgrq asc";
		String fpdm = fpjmx.getField(fieldname, where);
		System.out.println(fpdm);
	}
}
