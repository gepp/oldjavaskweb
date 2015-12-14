package jsdt.model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jsdt.tools.DBConnection;
import jsdt.tools.Query;

public class Tfy implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String guid;
	private String nsrbm;
	private String jqbh;
	private String tysj;
	private String shyj;
	private String tyczy;
	private String tyczsj;
	private String fysj;
	private String fyczy;
	private String fyczsj;
	private String tyyy;
	private String jqzt;
	private String jqxhbm;
	private String jqxhmc;
	
	
	public String getJqzt() {
		return jqzt;
	}
	public void setJqzt(String jqzt) {
		this.jqzt = jqzt;
	}
	public String getJqxhbm() {
		return jqxhbm;
	}
	public void setJqxhbm(String jqxhbm) {
		this.jqxhbm = jqxhbm;
	}
	public String getJqxhmc() {
		return jqxhmc;
	}
	public void setJqxhmc(String jqxhmc) {
		this.jqxhmc = jqxhmc;
	}
	public String getTyyy() {
		return tyyy==null?"":tyyy;
	}
	public void setTyyy(String tyyy) {
		this.tyyy = tyyy;
	}
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
	public String getTysj() {
		return tysj;
	}
	public void setTysj(String tysj) {
		this.tysj = tysj;
	}
	public String getShyj() {
		return shyj;
	}
	public void setShyj(String shyj) {
		this.shyj = shyj;
	}
	public String getTyczy() {
		return tyczy ==null?"":tyczy;
	}
	public void setTyczy(String tyczy) {
		this.tyczy = tyczy;
	}
	public String getTyczsj() {
		return tyczsj;
	}
	public void setTyczsj(String tyczsj) {
		this.tyczsj = tyczsj;
	}
	public String getFysj() {
		return fysj;
	}
	public void setFysj(String fysj) {
		this.fysj = fysj;
	}
	public String getFyczy() {
		return fyczy==null?"":fyczy;
	}
	public void setFyczy(String fyczy) {
		this.fyczy = fyczy;
	}
	public String getFyczsj() {
		return fyczsj;
	}
	public void setFyczsj(String fyczsj) {
		this.fyczsj = fyczsj;
	}
	/**
	 * 获取停业机器list
	 * @param tfy
	 * @return
	 */
	public ArrayList getTyJqxxList(Tfy tfy){
		ArrayList tfyList=null;
		
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql="select DISTINCT a.STATUS,a.JQXHBM,b.JQXHMC ,c.* from SKQ_JQXX a,SKQ_JQXH b,WLKP_TFY c where a.JQXHBM = b.JQXHBM and c.NSRBM='"+tfy.getNsrbm()+"' and a.STATUS="+tfy.getJqzt()+" and c.TYCZY IS NULL order by c.TYSJ desc";
				System.out.println("ty:="+sql);
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				tfyList = new ArrayList();
				while (rs.next()) {
					Tfy tfy1=new Tfy();
					tfy1.setGuid(rs.getString("GUID"));
					tfy1.setJqxhmc(rs.getString("JQXHMC"));
					tfy1.setJqxhbm(rs.getString("JQXHBM"));
					tfy1.setNsrbm(rs.getString("NSRBM"));
					tfy1.setJqbh(rs.getString("JQBH"));
					tfy1.setTysj(rs.getString("TYSJ"));
					tfy1.setShyj(rs.getString("SHYJ"));
					tfy1.setTyczsj(rs.getString("TYCZY"));
					tfy1.setTyczsj(rs.getString("TYCZSJ"));
					tfy1.setFysj(rs.getString("FYSJ"));
					tfy1.setFyczy(rs.getString("FYCZY"));
					tfy1.setTyyy(rs.getString("TYYY"));
					tfyList.add(tfy1);
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
		
		return tfyList;
	}
	public ArrayList getFyJqxxList(Tfy tfy){
		ArrayList tfyList=null;
		
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql="select DISTINCT a.STATUS,a.JQXHBM,b.JQXHMC ,c.* from SKQ_JQXX a,SKQ_JQXH b,WLKP_TFY c where a.JQXHBM = b.JQXHBM and c.NSRBM='"+tfy.getNsrbm()+"' and a.STATUS="+tfy.getJqzt()+" and c.FYCZY IS NULL and c.TYCZY IS NOT NULL";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				tfyList = new ArrayList();
				System.out.println("fysql:="+sql);
				while (rs.next()) {
					Tfy tfy1=new Tfy();
					tfy1.setGuid(rs.getString("GUID"));
					tfy1.setNsrbm(rs.getString("NSRBM"));
					tfy1.setJqxhmc(rs.getString("JQXHMC"));
					tfy1.setJqxhbm(rs.getString("JQXHBM"));
					tfy1.setJqbh(rs.getString("JQBH"));
					tfy1.setTysj(rs.getString("TYSJ"));
					tfy1.setShyj(rs.getString("SHYJ"));
					tfy1.setTyczsj(rs.getString("TYCZY"));
					tfy1.setTyczsj(rs.getString("TYCZSJ"));
					tfy1.setFysj(rs.getString("FYSJ"));
					tfy1.setFyczy(rs.getString("FYCZY"));
					tfy1.setTyyy(rs.getString("TYYY"));
					tfyList.add(tfy1);
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
		
		return tfyList;
	}
	public ArrayList getTFyJqxxList(String nsrbm,String jqbh){
		ArrayList tfyList=null;
		
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql="select * from WLKP_TFY where NSRBM='"+nsrbm+"' and JQBH='+"+jqbh+"+'";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				tfyList = new ArrayList();
				while (rs.next()) {
					Tfy tfy1=new Tfy();
					tfy1.setGuid(rs.getString("GUID"));
					tfy1.setNsrbm(rs.getString("NSRBM"));
					tfy1.setJqbh(rs.getString("JBQH"));
					tfy1.setTysj(rs.getString("TYSJ"));
					tfy1.setShyj(rs.getString("SHYJ"));
					tfy1.setTyczsj(rs.getString("TYCZY"));
					tfy1.setTyczsj(rs.getString("TYCZSJ"));
					tfy1.setFysj(rs.getString("FYSJ"));
					tfy1.setFyczy(rs.getString("FYCZY"));
					tfy1.setTyyy(rs.getString("TYYY"));
					tfyList.add(tfy1);
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
		
		return tfyList;
	}
	
	public void updateTyxx(Tfy tfy){
		String sql="update WLKP_TFY set TYYY='"+tfy.getTyyy()+"',SHYJ='"+tfy.getShyj()+"',TYCZSJ='"+tfy.getTyczsj()+
		"',TYCZY='"+tfy.getTyczy()+"' where NSRBM='"+tfy.getNsrbm()+"' and JQBH='"+tfy.getJqbh()+"'";
		System.out.println(sql);
		Query.updateField(sql);
	}
	public void updateFyxx(Tfy tfy){
		String sql="update WLKP_TFY set SHYJ='"+tfy.getShyj()+"',FYSJ='"+tfy.getFysj()+"',FYCZSJ='"+tfy.getFyczsj()+"',FYCZY='"+tfy.getFyczy()+"' where NSRBM='"+tfy.getNsrbm()+"' and JQBH='"+tfy.getJqbh()+"'";
		Query.updateField(sql);
	}
	public void fyUpdateJqzt(String nsrbm,String jqbh,String jqzt){
		String sql="update SKQ_JQXX set STATUS="+jqzt+" where NSRWJBM='"+nsrbm+"' and JQBH='"+jqbh+"'";
		Query.updateField(sql);
	}
	
}
