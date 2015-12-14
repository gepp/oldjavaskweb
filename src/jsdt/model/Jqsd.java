package jsdt.model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jsdt.tools.DBConnection;
import jsdt.tools.Query;

public class Jqsd implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String guid;
	private String nsrbm;
	private String jqbh;
	private String sdyy;
	private String sdsj;
	private String sdczy;
	private String jsczy;
	private String jssj;
	private String jqxhmc;
	private String jqzt;
	
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	public String getNsrbm() {
		return nsrbm;
	}
	public void setNsrbm(String nsrbm) {
		this.nsrbm = nsrbm;
	}
	public String getJqbh() {
		return jqbh;
	}
	public void setJqbh(String jqbh) {
		this.jqbh = jqbh;
	}
	public String getSdyy() {
		return sdyy;
	}
	public void setSdyy(String sdyy) {
		this.sdyy = sdyy;
	}
	public String getSdsj() {
		return sdsj;
	}
	public void setSdsj(String sdsj) {
		this.sdsj = sdsj;
	}
	public String getSdczy() {
		return sdczy ==null?"":sdczy;
	}
	public void setSdczy(String sdczy) {
		this.sdczy = sdczy;
	}
	
	public String getJsczy() {
		return jsczy==null?"":jsczy;
	}
	public void setJsczy(String jsczy) {
		this.jsczy = jsczy;
	}
	public String getJssj() {
		return jssj;
	}
	public void setJssj(String jssj) {
		this.jssj = jssj;
	}
	public String getJqxhmc() {
		return jqxhmc;
	}
	public void setJqxhmc(String jqxhmc) {
		this.jqxhmc = jqxhmc;
	}
	public String getJqzt() {
		return jqzt;
	}
	public void setJqzt(String jqzt) {
		this.jqzt = jqzt;
	}
	
	
	public void addJqSdxx(Jqsd jqsd){
		String sql="INSERT INTO WLKP_JQSD(GUID,NSRBM,JQBH,SDYY,SDCZY,SDSJ) VALUES ('"+jqsd.getGuid()+"','"+jqsd.getNsrbm()+"','"+jqsd.getJqbh()+"','"+jqsd.getSdyy()+"','"+jqsd.getSdczy()+"','"+jqsd.getSdsj()+"')";
	    Query.updateField(sql);
	}
	
	public void updateJqztSd(String jqzt,String jqbh,String nsrbm){
		String sql="   update SKQ_JQXX set STATUS='"+jqzt+"' where JQBH='"+jqbh+"' and NSRWJBM='"+nsrbm+"'";
		Query.updateField(sql);
	}
	public void updateJqSdxx(String jssj,String jsczy,String jqbh,String nsrbm){
		String sql="update WLKP_JQSD set JSSJ='"+jssj+"',JSCZY='"+jsczy+"' where JQBH='"+jqbh+"' and NSRBM='"+nsrbm+"'";
		Query.updateField(sql);
	}
	
	public ArrayList selectJsdJqxx(String jqbh,String nsrbm){
       ArrayList jqsdList=null;
		
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql=" select * from WLKP_JQSD where NSRBM='"+nsrbm+"' and JQBH='+"+jqbh+"+'";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				jqsdList = new ArrayList();
				while (rs.next()) {
					Jqsd jqsd=new Jqsd();
					jqsd.setGuid(rs.getString("GUID"));
					jqsd.setNsrbm(rs.getString("NSRBM"));
					jqsd.setJqbh(rs.getString("JQBH"));
					jqsd.setSdyy(rs.getString("SDYY"));
					jqsd.setSdczy(rs.getString("SDCZY"));
					jqsd.setSdsj(rs.getString("SDSJ"));
					jqsdList.add(jqsd);
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
		
		return jqsdList;
	}
	
	public ArrayList selectAllByNsrwjbmAndZt(String nsrwjbm,String status){
	  ArrayList jqsdList=null;
	  Connection conn = null;
		try {
			String sql="select a.JQBH,a.nsrwjbm,a.JQXHBM,a.status,a.CSHSJ,a.DZKPXE,a.LXKPZJE,a.LXKPZS,a.LXKPSJ,a.KQYRQ,a.KYXRQ,b.JQXHMC from skq_JQXX a,skq_JQXH b where  a.JQXHBM = b.JQXHBM  and a.NSRWJBM='"+nsrwjbm+"' and a.status="+status+" and  a.CSHSJ IS NOT NULL";
			System.out.println("jqsd_sql:="+sql);
			conn = DBConnection.getConnection();
			if (conn != null) {
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				jqsdList = new ArrayList();
				while (rs.next()) {
					Jqxx jqxx=new Jqxx();
					System.out.println(rs.getString("JQBH"));
					jqxx.setJqbh(rs.getString("JQBH"));
					jqxx.setJqxhbm(rs.getString("JQXHBM"));
					jqxx.setJqxhmc(rs.getString("JQXHMC"));
					jqxx.setDzkpxe(rs.getDouble("DZKPXE"));
					jqxx.setStatus(rs.getInt("STATUS"));
					jqxx.setLxkpzje(rs.getInt("LXKPZJE"));
					jqsdList.add(jqxx);
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
		System.out.println(jqsdList);
	  return jqsdList;
	}
	
}
