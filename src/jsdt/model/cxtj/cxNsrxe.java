package jsdt.model.cxtj;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jsdt.model.Xebg;
import jsdt.tools.DBConnection;
import jsdt.tools.Util;

public class cxNsrxe {
	// 查询限额变更
	public ArrayList selectAll(String sqlStr) {
		ArrayList al = null;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select a.*,b.NSRMC from SKQ_XEBG a left outer join SKQ_NSRXX b on a.NSRWJBM=b.NSRWJBM where 1=1 "+sqlStr+" order by a.SQSJ desc";
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
}
