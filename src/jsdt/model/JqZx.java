package jsdt.model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jsdt.tools.DBConnection;
import jsdt.tools.Query;

public class JqZx implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String guid;
	private String nsrbm;
	private String jqbh;
    private String zxyy;
    private String shyj;
    private String clz;
    private String clsj;
    private String zxsj;
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
	public String getZxsj() {
		return zxsj;
	}
	public void setZxsj(String zxsj) {
		this.zxsj = zxsj;
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
	public String getZxyy() {
		return zxyy==null?"":zxyy;
	}
	public void setZxyy(String zxyy) {
		this.zxyy = zxyy;
	}
	public String getShyj() {
		return shyj==null?"":shyj;
	}
	public void setShyj(String shyj) {
		this.shyj = shyj;
	}
	public String getClz() {
		return clz==null?"":clz;
	}
	public void setClz(String clz) {
		this.clz = clz;
	}
	public String getClsj() {
		return clsj;
	}
	public void setClsj(String clsj) {
		this.clsj = clsj;
	}
    
	public void addJqZxXx(JqZx jqzx){
		String sql="INSERT INTO WLKP_ZX(GUID,NSRBM,JQBH,ZXYY,SHYJ,CLZ,CLSJ,ZXSJ)VALUES ('"+jqzx.getGuid()+"','"+jqzx.getNsrbm()+"','"+jqzx.getJqbh()+"','"+jqzx.getZxyy()+"','"+jqzx.getShyj()+"','"+jqzx.getClz()+"','"+jqzx.getClsj()+"','"+jqzx.getZxsj()+"')";
	    Query.updateField(sql);
	}
	
	public ArrayList zxJqxx(String jqzt,String nsrbm){
	       ArrayList jqzxList=null;
			
			Connection conn = null;
			try {
				conn = DBConnection.getConnection();
				if (conn != null) {
					String sql="select DISTINCT a.STATUS,a.JQXHBM,b.JQXHMC ,c.* from SKQ_JQXX a,SKQ_JQXH b,WLKP_ZX c where  a.JQXHBM = b.JQXHBM and c.NSRBM='"+nsrbm+"' and a.STATUS="+jqzt+" and c.CLZ IS NULL";
					System.out.println("sql:zx="+sql);
					PreparedStatement ps = conn.prepareStatement(sql);
					ResultSet rs = ps.executeQuery();
					jqzxList = new ArrayList();
					while (rs.next()) {
					    JqZx jqzx=new JqZx();
						jqzx.setGuid(rs.getString("GUID"));
						jqzx.setNsrbm(rs.getString("NSRBM"));
						jqzx.setJqbh(rs.getString("JQBH"));
						jqzx.setZxsj(rs.getString("ZXSJ"));
						jqzx.setJqxhbm(rs.getString("JQXHBM"));
						jqzx.setJqxhmc(rs.getString("JQXHMC"));
						jqzx.setJqzt(rs.getString("STATUS"));
						
						jqzxList.add(jqzx);
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
			
			return jqzxList;
		}
	public ArrayList selectZxJqxx(String jqbh,String nsrbm){
	       ArrayList jqzxList=null;
			
			Connection conn = null;
			try {
				conn = DBConnection.getConnection();
				if (conn != null) {
					String sql="select * from WLKP_ZX where NSRBM='"+nsrbm+"' and JQBH='"+jqbh+"'";
					PreparedStatement ps = conn.prepareStatement(sql);
					ResultSet rs = ps.executeQuery();
					jqzxList = new ArrayList();
					while (rs.next()) {
					    JqZx jqzx=new JqZx();
						jqzx.setGuid(rs.getString("GUID"));
						jqzx.setNsrbm(rs.getString("NSRBM"));
						jqzx.setJqbh(rs.getString("JQBH"));
						jqzx.setZxsj(rs.getString("ZXSJ"));
						jqzx.setJqxhbm(rs.getString("JQXHBM"));
						jqzx.setJqxhmc(rs.getString("JQXHMC"));
						jqzx.setJqzt(rs.getString("status"));
						
						jqzxList.add(jqzx);
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
			
			return jqzxList;
		}
	public void updateZxxx(JqZx jqzx){
		String sql="update WLKP_ZX set ZXYY='"+jqzx.getZxyy()+"',SHYJ='"+jqzx.getShyj()+"',CLSJ='"+jqzx.getClsj()+"',CLZ='"+jqzx.getClz()+"' where NSRBM='"+jqzx.getNsrbm()+"' and JQBH='"+jqzx.getJqbh()+"'";
	    Query.updateField(sql);
	}
	
}
