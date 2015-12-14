package jsdt.model.cxtj;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

import jsdt.tools.DBConnection;
import jsdt.tools.Query;
import jsdt.tools.Util;

public class Fpxs {
	//单张通用票查询
	public HashMap selectTyfp(String fpdm, int fphm) {
		HashMap hm = new HashMap();
		ArrayList al = new ArrayList();
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select a.*,b.NSRMC,b.NSRSBH,b.SWJGBM,c.DJ,c.SL as zsl,(select HYMC from SKQ_HY where HYBM in(select HYBM from SKQ_NSRXX where NSRWJBM=a.NSRWJBM)) as HYMC from SKQ_FPKJ a left outer join SKQ_NSRXX b on a.NSRWJBM=b.NSRWJBM left outer join SKQ_FPKJXM c on a.FPHM=c.FPHM and a.fpdm=c.FPDM where a.FPDM='"+fpdm+"' and a.FPHM="+fphm;
				System.out.println("sql=="+sql);
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				DecimalFormat dg = new DecimalFormat("0.00");
				int sid = 0;
				String nsrwjbm = "";
				String cxfpdm = "";
				int cxfphm = 0;
				while (rs.next()) {
					nsrwjbm = rs.getString("NSRWJBM");
					hm.put("nsrwjbm", nsrwjbm);
					hm.put("dj", rs.getString("DJ"));
					hm.put("zsl", rs.getDouble("zsl"));
					System.out.println("rs:zsl"+rs.getDouble("zsl"));
					hm.put("nsrmc", Util.iso8859togbk(rs.getString("NSRMC")));
					hm.put("hymc", Util.iso8859togbk(rs.getString("HYMC")));
					hm.put("swjgbm", rs.getString("SWJGBM"));
					hm.put("nsrsbh", rs.getString("NSRSBH"));
					hm.put("jqbh", rs.getString("JQBH"));
					hm.put("kprq", rs.getString("KPRQ").substring(0,10));
					hm.put("kplx", String.valueOf(rs.getInt("KPLX")));
					hm.put("fpdm", rs.getString("FPDM"));
					cxfpdm = rs.getString("FPDM");
					cxfphm = rs.getInt("FPHM");
					hm.put("fphm", Util.fpIntToString(rs.getInt("FPHM")));
					hm.put("hjzje", dg.format(rs.getDouble("HJZJE")));
					int yfphm = rs.getInt("YFPHM");
					if(yfphm>0){
						hm.put("yfphm", Util.fpIntToString(yfphm));
					}
					else{
						hm.put("yfphm", "");
					}
					hm.put("fkdw", Util.iso8859togbk(rs.getString("FKDW")));
					hm.put("sky", Util.iso8859togbk(rs.getString("SKY")));
					hm.put("skm", rs.getString("SKM"));
					hm.put("bz", Util.iso8859togbk(rs.getString("BZ")));
					hm.put("zkzje", rs.getDouble("ZKZJE"));
					sid = rs.getInt("SID");
				}
				rs.close();
				ps.close();
				
				if(sid>0){
					sql = "select * from SKQ_FPKJXM where FPDM='"+cxfpdm+"' and FPHM="+cxfphm;
					ps = conn.prepareStatement(sql);
					rs = ps.executeQuery();
					while(rs.next()){
						HashMap hmXm = new HashMap();
						hmXm.put("xmmc", Util.iso8859togbk(rs.getString("XMMC")));
						hmXm.put("sl", dg.format(rs.getDouble("SL")));
						hmXm.put("dj", dg.format(rs.getDouble("DJ")));
						hmXm.put("je", dg.format(rs.getDouble("JE")));
						
						al.add(hmXm);
					}
				}
				hm.put("alXm", al);
				
				conn.close();
				
//				sql = "select DJZH_SWDJZB from DJ_SWDJZB where QY_ID='"+nsrwjbm+"'";
//				String fieldname = "DJZH_SWDJZB";
//				String nsrsbh = Query.getFieldZg(sql, fieldname);
				
				hm.put("nsrsbh", "");
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
		return hm;
	}
	
	
	//单张不动产发票查询
	public HashMap selectBdcfp(String fpdm, int fphm) {
		HashMap hm = new HashMap();
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select a.*,b.XMBM,b.ZXMMC,c.SWJGBM from SKQ_BDCKP a left outer join SKQ_BDCXMMX b on a.BDCXMMXID=b.SID left outer join SKQ_NSRXX c on a.NSRWJBM=c.NSRWJBM and c.STATUS=1 where a.FPDM='"+fpdm+"' and a.FPHM="+fphm;
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				DecimalFormat dg = new DecimalFormat("0.00");
				int sid = 0;
				while (rs.next()) {
					hm.put("zgswjg", rs.getString("SWJGBM"));
					hm.put("fkfdm", rs.getString("FKFDM"));
					hm.put("fkfmc", Util.iso8859togbk(rs.getString("FKFMC")));
					hm.put("lpmc", Util.iso8859togbk(rs.getString("LPMC")));
					hm.put("kxxz", String.valueOf(rs.getInt("KXXZ")));
					hm.put("xmbm", rs.getString("XMBM"));
					hm.put("zxmmc", Util.iso8859togbk(rs.getString("ZXMMC")));
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
		return hm;
	}
	
	
	//单张建筑安装发票查询
	public HashMap selectJzazfp(String fpdm, int fphm) {
		HashMap hm = new HashMap();
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select a.*,b.XMDM,b.XMMC,b.ZGSWJG,b.JFWJBM,b.JFMC,b.YFWJBM,b.YFMC,b.HTXZ from SKQ_JZAZKP a left outer join SKQ_JZAZ b on a.PARENTID=b.SID where a.FPDM='"+fpdm+"' and a.FPHM="+fphm;
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				DecimalFormat dg = new DecimalFormat("0.00");
				int sid = 0;
				String jfwjbm="";
				String yfwjbm="";
				while (rs.next()) {
					jfwjbm = rs.getString("JFWJBM");
					yfwjbm = rs.getString("YFWJBM");
					hm.put("jfwjbm", jfwjbm);
					hm.put("yfwjbm", yfwjbm);
					hm.put("kxxz", String.valueOf(rs.getInt("KXXZ")));
					hm.put("xmbm", rs.getString("XMDM"));
					hm.put("zgswjg", rs.getString("ZGSWJG"));
					hm.put("xmmc", Util.iso8859togbk(rs.getString("XMMC")));
					hm.put("jfmc", Util.iso8859togbk(rs.getString("JFMC")));
					hm.put("yfmc", Util.iso8859togbk(rs.getString("YFMC")));
					hm.put("htxz", String.valueOf(rs.getInt("HTXZ")));
				}
				rs.close();
				ps.close();
				conn.close();
				
				sql = "select DJZH_SWDJZB from DJ_SWDJZB where QY_ID='"+jfwjbm+"'";
				String fieldname = "DJZH_SWDJZB";
				String jfsbh = Query.getFieldZg(sql, fieldname);
				if(jfsbh==null||"".equals(jfsbh)){
					jfsbh = jfwjbm;
				}
				hm.put("jfsbh", jfsbh);
				
				sql = "select DJZH_SWDJZB from DJ_SWDJZB where QY_ID='"+yfwjbm+"'";
				fieldname = "DJZH_SWDJZB";
				String yfsbh = Query.getFieldZg(sql, fieldname);
				if(jfsbh==null||"".equals(jfsbh)){
					jfsbh = jfwjbm;
				}
				hm.put("yfsbh", yfsbh);
				System.out.println("HM=="+hm);
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
		return hm;
	}
}
