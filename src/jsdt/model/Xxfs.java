package jsdt.model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import jsdt.tools.DBConnection;
import jsdt.tools.Query;

public class Xxfs implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//消息ID
	private String guid;
	//消息内容
	private String xxnr;
	//税务机关编码
	private String swjgbm;
	//创建时间
	private String cjsj;
	//创建者
	private String cjz;
	
	public String getCjz() {
		return cjz==null?"":cjz;
	}
	public void setCjz(String cjz) {
		this.cjz = cjz;
	}
	public String getCjsj() {
		return cjsj==null?"":cjsj;
	}
	public void setCjsj(String cjsj) {
		this.cjsj = cjsj;
	}
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	public String getXxnr() {
		return xxnr;
	}
	public void setXxnr(String xxnr) {
		this.xxnr = xxnr;
	}
	public String getSwjgbm() {
		return swjgbm;
	}
	public void setSwjgbm(String swjgbm) {
		this.swjgbm = swjgbm;
	}
	
	/**
	 * 查询消息发送
	 * @param sql
	 * @return
	 */
	public ArrayList getXxfs(String sql){
		ArrayList list=new ArrayList();
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					Xxfs xxfs=new Xxfs();
					xxfs.setGuid(rs.getString("GUID"));
					xxfs.setXxnr(rs.getString("XXNR"));
					xxfs.setSwjgbm(rs.getString("SWJGBM"));
					xxfs.setCjsj(rs.getString("CJSJ"));
					xxfs.setCjz(rs.getString("CJZ"));
					list.add(xxfs);
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
		return list;

	}
	/**
	 * 删除消息发送
	 */
	public void deleteXxfs(String guid){
		String sql="delete  from WLKP_SWJGXX where guid='"+guid+"'";
		Query.updateField(sql);
	}
	/**
	 * 消息发送增加
	 */
	public void insertXxfs(Xxfs xxfs){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date=sdf.format(new Date());
		String sql="insert into WLKP_SWJGXX(GUID,XXNR,SWJGBM,CJZ,CJSJ) values ('"+xxfs.getGuid()+"','"+xxfs.getXxnr()+"','"+xxfs.getSwjgbm()+"','"+xxfs.getCjz()+"','"+date+"')";
		Query.updateField(sql);
		System.out.println(xxfs.getCjz());
		System.out.println(xxfs.getCjz().equals("system"));
		String condition="";
		if(!xxfs.getCjz().equals("system")){
			 condition=" and SWJGBM='"+xxfs.getSwjgbm()+"'";
			
		}
		this.updateXxfs(xxfs.getGuid(), condition);
	}
	/**
	 * 更新消息发送
	 * @param condition
	 */
	public void updateXxfs(String xxid,String condition){
		String sql="update SKQ_NSRXX set XXID ='"+xxid+"'"+" where 1=1 "+condition;
		System.out.println(sql);
		Query.updateField(sql);
	}
	public static Xxfs getXxfsByGuid(String guid){
		Xxfs xxfs=new Xxfs();
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				PreparedStatement ps = conn.prepareStatement("select * from wlkp_swjgxx where guid='"+guid+"'");
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					xxfs.setGuid(rs.getString("GUID"));
					xxfs.setXxnr(rs.getString("XXNR"));
					xxfs.setSwjgbm(rs.getString("SWJGBM"));
					xxfs.setCjsj(rs.getString("CJSJ"));
					xxfs.setCjz(rs.getString("CJZ"));
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
		return xxfs;
	}
	
}
