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

public class cxJzaz {
	//查询建筑安装项目信息
	public ArrayList selectJzaz(String sqlStr){
		ArrayList al = new ArrayList();
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select a.*,b.SWJGMC from SKQ_JZAZ a left outer join SKQ_SWJG b on a.ZGSWJG=b.SWJGBM where a.SID in("+sqlStr+") order by a.ZGSWJG asc";
				
				System.out.println("sql=="+sql);
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				al = new ArrayList();
				DecimalFormat dg = new DecimalFormat("0.00");
				while (rs.next()) {
					HashMap hm = new HashMap();
					hm.put("id", String.valueOf(rs.getInt("SID")));
					hm.put("xmdm", rs.getString("XMDM"));
					hm.put("xmmc", Util.iso8859togbk(rs.getString("XMMC")));
					hm.put("jfwjbm", rs.getString("JFWJBM"));
					hm.put("jfmc", Util.iso8859togbk(rs.getString("JFMC")));
					hm.put("yfwjbm", rs.getString("YFWJBM"));
					hm.put("yfmc", Util.iso8859togbk(rs.getString("YFMC")));
					hm.put("nsrxz", String.valueOf(rs.getInt("NSRXZ")));
					hm.put("parentid", String.valueOf(rs.getInt("PARENTID")));
					hm.put("ddkje", dg.format(rs.getDouble("DDKJE")));
					int htxz = rs.getInt("HTXZ");
					String htxzmc = "";
					if(htxz==0){
						htxzmc = "总包";
					}
					else{
						htxzmc = "分包";
					}
					
					hm.put("htxz", htxzmc);
					double htzje = rs.getDouble("GCK")+rs.getDouble("JGC");
					hm.put("htzje", String.valueOf(dg.format(htzje)));
					
					double ykje = rs.getDouble("YKJE");
					int sid = rs.getInt("SID");
					
					String sql1 = "select sum(HJZJE) as zcpzje from SKQ_JZAZKP where KPLX=1 and KXXZ!=2 and PARENTID="+sid;
					String fieldname = "zcpzje";
					double zcpzje = Query.getFieldDouble(sql1, fieldname);
					
					sql1 = "select sum(HJZJE) as tpzje from SKQ_JZAZKP where KPLX=2 and KXXZ!=2 and PARENTID="+sid;
					fieldname = "tpzje";
					double tpzje = Query.getFieldDouble(sql1, fieldname);
					
					double ykpzje = ykje+zcpzje-tpzje;
					double sykpzje = htzje-ykpzje;
					
					
					//ykpzje = Double.parseDouble(dg.format(ykpzje));
					hm.put("ykpzje", dg.format(ykpzje));
					hm.put("sykpzje", dg.format(sykpzje));					
					
					
					int fbflag = 0;
					ArrayList alfb = this.selectJzazFb(rs.getInt("SID"));
					if(alfb!=null&&!alfb.isEmpty()){
						fbflag = 1;
					}
					
					int kpflag = 0;
					ArrayList alkp = this.selectJzazKp(rs.getInt("SID"));
					if(alkp!=null&&!alkp.isEmpty()){
						kpflag = 1;
					}
					
					hm.put("fbflag", String.valueOf(fbflag));
					hm.put("kpflag", String.valueOf(kpflag));
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
	
	//查询建筑安装汇总
	public HashMap selectJzazHz(String sqlStr){
		HashMap hm = new HashMap();
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select sum(a.GCK) as hjgck,sum(a.JGC) as hjjgc,sum(a.YKJE) as hjykje,sum(a.DDKJE) as hjddkje from SKQ_JZAZ a where 1=1 "+sqlStr;
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				DecimalFormat dg = new DecimalFormat("0.00");
				while (rs.next()) {
					hm.put("hjhtje", String.valueOf(rs.getDouble("hjgck")+rs.getDouble("hjjgc")));
					hm.put("hjykje", String.valueOf(rs.getDouble("hjykje")));
					hm.put("hjddkje", String.valueOf(rs.getDouble("hjddkje")));
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
	
	//查询建筑安装开票信息
	public ArrayList selectJzazKp(int parentid){
		ArrayList al = new ArrayList();
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select * from SKQ_JZAZKP where PARENTID="+parentid;
				
				//System.out.println("sql=="+sql);
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				al = new ArrayList();
				DecimalFormat dg = new DecimalFormat("0.00");
				while (rs.next()) {
					HashMap hm = new HashMap();
					hm.put("sid", String.valueOf(rs.getInt("SID")));
					hm.put("parentid", rs.getString("PARENTID"));
					hm.put("kxxz", String.valueOf(rs.getInt("KXXZ")));
					hm.put("fpdm", rs.getString("FPDM"));
					hm.put("fphm", Util.fpIntToString(rs.getInt("FPHM")));
					hm.put("kplx", String.valueOf(rs.getInt("KPLX")));
					hm.put("hjzje", dg.format(rs.getDouble("HJZJE")));
					hm.put("kprq", rs.getString("KPRQ").substring(0,10));
					hm.put("scrq", rs.getString("SCRQ").substring(0,10));		

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
	
	//查询建筑安装分包项目信息
	public ArrayList selectJzazFb(int parentid){
		ArrayList al = new ArrayList();
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select a.*,b.SWJGMC from SKQ_JZAZ a left outer join SKQ_SWJG b on a.ZGSWJG=b.SWJGBM where PARENTID="+parentid;
				
				System.out.println("sql=="+sql);
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				al = new ArrayList();
				DecimalFormat dg = new DecimalFormat("0.00");
				while (rs.next()) {
					HashMap hm = new HashMap();
					hm.put("id", String.valueOf(rs.getInt("SID")));
					hm.put("xmdm", rs.getString("XMDM"));
					hm.put("xmmc", Util.iso8859togbk(rs.getString("XMMC")));
					hm.put("jfwjbm", rs.getString("JFWJBM"));
					hm.put("jfmc", Util.iso8859togbk(rs.getString("JFMC")));
					hm.put("yfwjbm", rs.getString("YFWJBM"));
					hm.put("yfmc", Util.iso8859togbk(rs.getString("YFMC")));
					hm.put("nsrxz", String.valueOf(rs.getInt("NSRXZ")));
					hm.put("parentid", String.valueOf(rs.getInt("PARENTID")));
					hm.put("ddkje", dg.format(rs.getDouble("DDKJE")));
					int htxz = rs.getInt("HTXZ");
					String htxzmc = "";
					if(htxz==0){
						htxzmc = "总包";
					}
					else{
						htxzmc = "分包";
					}
					
					hm.put("htxz", htxzmc);
					double htzje = rs.getDouble("GCK")+rs.getDouble("JGC");
					hm.put("htzje", String.valueOf(dg.format(htzje)));
					
					double ykje = rs.getDouble("YKJE");
					int sid = rs.getInt("SID");
					
					String sql1 = "select sum(HJZJE) as zcpzje from SKQ_JZAZKP where KPLX=1 and KXXZ!=2 and PARENTID="+sid;
					String fieldname = "zcpzje";
					double zcpzje = Query.getFieldDouble(sql1, fieldname);
					
					sql1 = "select sum(HJZJE) as tpzje from SKQ_JZAZKP where KPLX=2 and KXXZ!=2 and PARENTID="+sid;
					fieldname = "tpzje";
					double tpzje = Query.getFieldDouble(sql1, fieldname);
					
					double ykpzje = ykje+zcpzje-tpzje;
					double sykpzje = htzje-ykpzje;
					
					
					//ykpzje = Double.parseDouble(dg.format(ykpzje));
					hm.put("ykpzje", dg.format(ykpzje));
					hm.put("sykpzje", dg.format(sykpzje));					

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
