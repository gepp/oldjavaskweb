package jsdt.model.cxtj;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import jsdt.tools.DBConnection;
import jsdt.tools.Query;
import jsdt.tools.Util;

public class cxJzazjg {
	public ArrayList selectJzazxmmc(String keyword,String swjgStr) {
		ArrayList al = null;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sqlStr = "";

				if (keyword != null && !"".equals(keyword)) {
					sqlStr = " and XMMC like'%" + keyword + "%'";
				}
				String sql = "select SID, XMMC from SKQ_JZAZ where YFWJBM in(select NSRWJBM from SKQ_NSRXX where STATUS=1 and SWJGBM in("+swjgStr+")) " + sqlStr;
				System.out.println("sql=="+sql);
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				al = new ArrayList();
				while (rs.next()) {
					HashMap hm = new HashMap();
					hm.put("sid", String.valueOf(rs.getInt("SID")));
					hm.put("xmmc", Util.iso8859togbk(rs
							.getString("XMMC")));

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
		System.out.println("al==" + al);
		return al;
	}
	
	public int selectXmid(int xmid,int gjdid){
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select a.PARENTID,b.PARENTID as sjid from SKQ_JZAZ a left outer join SKQ_JZAZ b on a.PARENTID=b.SID where a.SID="+xmid;
				System.out.println("sql=="+sql);
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				int dqid = 0;
				int sjid = 0;
				while (rs.next()) {
					dqid = rs.getInt("PARENTID");
					sjid = rs.getInt("sjid");
				}
				rs.close();
				ps.close();
				
				if(dqid==0){
					gjdid = xmid;
				}
				else{
					if(sjid!=0){
						gjdid = this.selectXmid(dqid, gjdid);
					}
					else{
						gjdid = dqid;
					}
				}
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
		return gjdid;
	}
	
	public ArrayList selectJzaz(int gjdid,ArrayList al,int isgjd){
		Connection conn = null;
		if(isgjd==0){
			ArrayList alJzaz = this.getJzaz(gjdid, isgjd);
			Iterator it = alJzaz.iterator();
			while(it.hasNext()){
				HashMap hm = (HashMap)it.next();
				int id = Integer.parseInt((String)hm.get("sid"));
				al.add(hm);
				this.selectJzaz(id, al, isgjd+1);
			}
		}
		else{
			ArrayList alJzaz = this.getJzaz(gjdid, isgjd);
			Iterator it = alJzaz.iterator();
			while(it.hasNext()){
				HashMap hm = (HashMap)it.next();
				int id = Integer.parseInt((String)hm.get("sid"));
				al.add(hm);
				this.selectJzaz(id, al, isgjd+1);
			}
		}
		
		return al;
	}
	
	public ArrayList getJzaz(int id,int isgjd){
		ArrayList al = new ArrayList();
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "";
				if(isgjd==0){
					sql = "select * from SKQ_JZAZ where SID="+id;
				}
				else{
					sql = "select * from SKQ_JZAZ where PARENTID="+id;
				}
				System.out.println("sql=="+sql);
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					HashMap hm = new HashMap();
					int sid = rs.getInt("SID");
					int parentid = rs.getInt("PARENTID");
					String xmmc = Util.iso8859togbk(rs.getString("XMMC"));
					double gck = rs.getDouble("GCK");
					double jgc = rs.getDouble("JGC");
					double ykje = rs.getDouble("YKJE");
					
					String sql1 = "select sum(HJZJE) as zcpzje from SKQ_JZAZKP where KPLX=1 and PARENTID="+sid;
					String fieldname = "zcpzje";
					double zcpzje = Query.getFieldDouble(sql1, fieldname);
					
					sql1 = "select sum(HJZJE) as tpzje from SKQ_JZAZKP where KPLX=2 and PARENTID="+sid;
					fieldname = "tpzje";
					double tpzje = Query.getFieldDouble(sql1, fieldname);
					
					double ykpzje = ykje+zcpzje-tpzje;
					
					hm.put("sid", String.valueOf(sid));
					hm.put("parentid", String.valueOf(parentid));
					hm.put("xmmc", xmmc);
					hm.put("htje", String.valueOf(gck+jgc));
					hm.put("djykje", String.valueOf(ykje));
					hm.put("ykpzje", String.valueOf(ykpzje));
					hm.put("isgjd", String.valueOf(isgjd));
					
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
	
	
	public String getJzazKp(int sid) {
		String sqlStr = "";
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select FPHM,FPDM from SKQ_JZAZKP where PARENTID="+sid;
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					int fphm = rs.getInt("FPHM");
					String fpdm = rs.getString("FPDM");

					if ("".equals(sqlStr)) {
						sqlStr = " and (( FPDM='" + fpdm + "' and FPHM=" + fphm
								+ ")";
					} else {
						sqlStr = sqlStr + " or ( FPDM='" + fpdm + "' and FPHM="
								+ fphm + ")";
					}
				}

				if (!"".equals(sqlStr)) {
					sqlStr = sqlStr + ")";
				}

				rs.close();
				ps.close();
				conn.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}
		}
		return sqlStr;
	}
}
