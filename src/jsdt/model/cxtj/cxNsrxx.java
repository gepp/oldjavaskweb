package jsdt.model.cxtj;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jsdt.model.Fpjmx;
import jsdt.model.Nsrxx;
import jsdt.tools.DBConnection;
import jsdt.tools.Util;

public class cxNsrxx {
	//查询纳税户信息
	public ArrayList selectNsrxx(String sqlStr){
		ArrayList al = new ArrayList();
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select a.*,b.ZCLXMC,c.HYMC,d.HYMXMC,e.SWJGMC from SKQ_NSRXX a "
						+ "left outer join SKQ_ZCLX b on a.ZCLXBM=b.ZCLXBM "
						+ "left outer join SKQ_HY c on a.HYBM=c.HYBM "
						+ "left outer join SKQ_HYMX d on a.HYMXBM=d.HYMXBM "
						+ "left outer join SKQ_SWJG e on a.SWJGBM=e.SWJGBM where a.STATUS=1 and a.SID in("+sqlStr+") order by a.SWJGBM ASC";
				
				System.out.println("cxNsrxx_selectNsrxx_sql=="+sql);
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				al = new ArrayList();
				while (rs.next()) {
					Nsrxx nsrxx = new Nsrxx();
					nsrxx.setSid(rs.getInt("SID"));
					nsrxx.setNsrwjbm(rs.getString("NSRWJBM"));
					nsrxx.setNsrsbh(rs.getString("NSRSBH"));
					nsrxx.setNsrmc(Util.iso8859togbk(rs.getString("NSRMC")));
					nsrxx.setJydz(Util.iso8859togbk(rs.getString("JYDZ")));
					nsrxx.setFrdb(Util.iso8859togbk(rs.getString("FRDB")));
					nsrxx.setZclxbm(rs.getString("ZCLXBM"));
					nsrxx.setZclxmc(Util.iso8859togbk(rs.getString("ZCLXMC")));
					nsrxx.setHybm(rs.getString("HYBM"));
					nsrxx.setHymc(Util.iso8859togbk(rs.getString("HYMC")));
					nsrxx.setHymxbm(rs.getString("HYMXBM"));
					nsrxx.setHymxmc(Util.iso8859togbk(rs.getString("HYMXMC")));
					nsrxx.setZsfs(rs.getInt("ZSFS"));
					nsrxx.setYhde(rs.getDouble("YHDE"));
					nsrxx.setSwjgbm(rs.getString("SWJGBM"));
					nsrxx.setSwjgmc(Util.iso8859togbk(rs.getString("SWJGMC")));
					nsrxx.setBsy(Util.iso8859togbk(rs.getString("BSY")));
					nsrxx.setSsgly(Util.iso8859togbk(rs.getString("SSGLY")));
					nsrxx.setStatus(rs.getInt("STATUS"));					

					al.add(nsrxx);
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
	
	public ArrayList selectNsrxx1(String sqlStr){
		ArrayList al = new ArrayList();
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql ="select * from skq_nsrxx";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				al = new ArrayList();
				while (rs.next()) {
					Nsrxx nsrxx = new Nsrxx();
					nsrxx.setSid(rs.getInt("SID"));
					nsrxx.setNsrwjbm(rs.getString("NSRWJBM"));
					nsrxx.setNsrsbh(rs.getString("NSRSBH"));
					nsrxx.setNsrmc(Util.iso8859togbk(rs.getString("NSRMC")));
					 
					al.add(nsrxx);
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
	
	//查询纳税户领购发票信息
	public ArrayList selectFpjmxByNsrwjbm(String nsrwjbm) {
		ArrayList al = null;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select a.*,b.FPMC from SKQ_FPJMX a left outer join SKQ_FP b on a.FPBM=b.FPBM where a.NSRWJBM='"
						+ nsrwjbm + "' order by FPXFZT ASC,FPLGRQ DESC";
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
					fpjmx.setFplgrq(rs.getString("FPLGRQ").substring(0,10));

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
}
