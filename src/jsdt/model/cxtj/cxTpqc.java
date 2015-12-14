package jsdt.model.cxtj;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jsdt.model.Fpkj;
import jsdt.tools.DBConnection;
import jsdt.tools.Util;

public class cxTpqc {
	public ArrayList selectTpqc(String sqlStr,String kprqStr,String scsjStr) {
		Connection conn = null;
		ArrayList al = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select a.*,b.NSRMC from SKQ_FPKJ a left outer join SKQ_NSRXX b on a.NSRWJBM=b.NSRWJBM where 1=1 "+sqlStr+" and ((a.KPLX=2 "+kprqStr+") or(a.KPLX=3 "+scsjStr+")) order by a.NSRWJBM asc,a.QRBZ asc";
				System.out.println("sql=="+sql);
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				al = new ArrayList();
				while (rs.next()) {
					Fpkj fpkj = new Fpkj();

					fpkj.setSid(Integer.valueOf(rs.getInt("SID")));
					fpkj.setNsrwjbm(rs.getString("NSRWJBM"));
					fpkj.setJqbh(rs.getString("JQBH"));
					fpkj.setKprq(rs.getString("KPRQ"));
					fpkj.setKplx(Integer.valueOf(rs.getInt("KPLX")));
					fpkj.setFpdm(rs.getString("FPDM"));
					fpkj.setFphm(Integer.valueOf(rs.getInt("FPHM")));
					fpkj.setHjzje(Double.valueOf(rs.getDouble("HJZJE")));
					fpkj.setYfphm(Integer.valueOf(rs.getInt("YFPHM")));
					fpkj.setFkdw(Util.iso8859togbk(rs.getString("FKDW")));
					fpkj.setSky(Util.iso8859togbk(rs.getString("SKY")));
					fpkj.setNsrmc(Util.iso8859togbk(rs.getString("NSRMC")));
					fpkj.setQrbz(rs.getInt("QRBZ"));
					al.add(fpkj);
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
