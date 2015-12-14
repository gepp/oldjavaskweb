package jsdt.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jsdt.tools.DBConnection;

public class Fpbm extends baseClass {
	private int sid;
	private String fpbm;
	private String fpdm;

	/**
	 * @roseuid 4CA19C4A005D
	 */
	public Fpbm() {

	}

	/**
	 * @return int
	 * @roseuid 4CA05D8C036B
	 */
	public int add() {
		return 0;
	}

	/**
	 * @param fpbm
	 * @param fpdm
	 * @return int
	 * @roseuid 4CA05D9200FA
	 */
	public int add(String fpbm, String fpdm) {
		int result = -1;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				PreparedStatement ps = null;
				ps = conn.prepareStatement("insert into SKQ_FP (FPBM,FPDM) values(?,?)");
				ps.setString(1, fpbm);
				ps.setString(2, fpdm);
				
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
	 * @return ArrayList
	 * @roseuid 4CA05E7401C5
	 */
	public ArrayList selectAll() {
		ArrayList al = null;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select * from SKQ_FPBM where FPBM='"+fpbm+"'";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					Fpbm fp = new Fpbm();
					fp.setFpbm(fpbm);
					fp.setFpdm(rs.getString("FPDM"));
					
					al.add(fp);
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
}
