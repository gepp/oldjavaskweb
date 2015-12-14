package jsdt.model.cxtj;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jsdt.model.Jqxx;
import jsdt.tools.DBConnection;
import jsdt.tools.SYSTEM;
import jsdt.tools.Util;

public class cxJqxx {
	public ArrayList selectJqxx(String sqlStr) {
		Connection conn = null;
		ArrayList al = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select a.*,b.NSRMC,c.JQXHMC from SKQ_JQXX a left outer join SKQ_NSRXX b on a.NSRWJBM=b.NSRWJBM left outer join SKQ_JQXH c on a.JQXHBM=c.JQXHBM  where a.SID in("+sqlStr+") order by a.NSRWJBM asc,a.KQYRQ desc";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				al = new ArrayList();
				while (rs.next()) {
					Jqxx jqxx = new Jqxx();
					jqxx.setNsrwjbm(rs.getString("NSRWJBM"));
					jqxx.setNsrmc(Util.iso8859togbk(rs.getString("NSRMC")));
					jqxx.setJqxhmc(Util.iso8859togbk(rs.getString("JQXHMC")));
					jqxx.setJqbh(rs.getString("JQBH"));
					jqxx.setSkkh(rs.getString("SKKH"));
					jqxx.setKqyrq(rs.getString("KQYRQ").substring(0,10));
					jqxx.setYljkpxe(rs.getDouble("YLJKPXE"));
					jqxx.setYljtpxe(rs.getDouble("YLJTPXE"));
					jqxx.setKpjzrq(rs.getString("KPJZRQ").substring(0,10));
					jqxx.setStatus(rs.getInt("STATUS"));
					
					al.add(jqxx);
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
	
	
	public ArrayList selectWcshJqxx(String sqlStr) {
		Connection conn = null;
		ArrayList al = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select a.*,b.NSRMC,c.JQXHMC from SKQ_JQXX a left outer join SKQ_NSRXX b on a.NSRWJBM=b.NSRWJBM left outer join SKQ_JQXH c on a.JQXHBM=c.JQXHBM  where a.STATUS=1 "+sqlStr+" and (substring(a.JQBH,5,16)='"+SYSTEM.CSHJQBH+"' or a.SKKH='"+SYSTEM.CSHSKKH+"')  order by a.NSRWJBM asc,a.KQYRQ desc";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				al = new ArrayList();
				while (rs.next()) {
					Jqxx jqxx = new Jqxx();
					jqxx.setNsrwjbm(rs.getString("NSRWJBM"));
					jqxx.setNsrmc(Util.iso8859togbk(rs.getString("NSRMC")));
					jqxx.setJqxhmc(Util.iso8859togbk(rs.getString("JQXHMC")));
					jqxx.setJqbh(rs.getString("JQBH"));
					jqxx.setSkkh(rs.getString("SKKH"));
					jqxx.setKqyrq(rs.getString("KQYRQ").substring(0,10));
					jqxx.setYljkpxe(rs.getDouble("YLJKPXE"));
					jqxx.setYljtpxe(rs.getDouble("YLJTPXE"));
					jqxx.setKpjzrq(rs.getString("KPJZRQ").substring(0,10));
					jqxx.setStatus(rs.getInt("STATUS"));
					
					al.add(jqxx);
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
}
