package jsdt.model.cxtj;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import jsdt.tools.DBConnection;

public class cxRjymx {
	// 查询日交易信息
	public ArrayList selectRjymx(String sqlStr) {
		Connection conn = null;
		ArrayList al = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				//start modify by yinkun 20101221
//				
				String sql = "select a.*,b.NSRWJBM,c.SMMC,d.NSRMC from SKQ_RJYMX a left outer join SKQ_JQXX b on a.JQBH=b.JQBH left outer join SKQ_PMSZ c on a.SMSY=c.SMSY left outer join SKQ_NSRXX d on b.NSRWJBM=d.NSRWJBM and d.STATUS=1 where 1=1 "+sqlStr+" and (a.ZCPZJE>0 or a.TPZJE>0) order by a.DQRQ desc";
				//end modify by yinkun 20101221
				System.out.println("sql=="+sql);
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				al = new ArrayList();
				while (rs.next()) {
					HashMap hm = new HashMap();
					hm.put("jqbh", rs.getString("JQBH"));
					hm.put("skkh", rs.getString("SKKH"));
					hm.put("dqrq", rs.getString("DQRQ"));
					hm.put("smbm", rs.getString("SMBM"));
					hm.put("smmc", rs.getString("SMMC"));
					hm.put("nsrwjbm", rs.getString("NSRWJBM"));
					hm.put("nsrmc", rs.getString("NSRMC"));
					hm.put("zcpfs", String.valueOf(rs.getInt("ZCPFS")));
					hm.put("tpfs", String.valueOf(rs.getInt("TPFS")));
					hm.put("fpfs", String.valueOf(rs.getInt("FPFS")));
					hm.put("zcpzje", String.valueOf(rs.getDouble("ZCPZJE")));
					hm.put("tpzje", String.valueOf(rs.getDouble("TPZJE")));
					
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
