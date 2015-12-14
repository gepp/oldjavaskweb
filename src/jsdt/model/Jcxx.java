package jsdt.model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jsdt.tools.DBConnection;
import jsdt.tools.Query;

public class Jcxx implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//每卷发票张数
	private int fpzs;
	//每页显示条数
	private int pagesize;
	//离线开票总金额
	private double lxkpzje;
	//离线开票张数
	private int lxkpzs;
	//离线开票天数
	private int lxkpts;
	
	
	public int getFpzs() {
		return fpzs;
	}
	public void setFpzs(int fpzs) {
		this.fpzs = fpzs;
	}
	public int getPagesize() {
		return pagesize;
	}
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}
	public double getLxkpzje() {
		return lxkpzje;
	}
	public void setLxkpzje(double lxkpzje) {
		this.lxkpzje = lxkpzje;
	}
	public int getLxkpzs() {
		return lxkpzs;
	}
	public void setLxkpzs(int lxkpzs) {
		this.lxkpzs = lxkpzs;
	}
	public int getLxkpts() {
		return lxkpts;
	}
	public void setLxkpts(int lxkpts) {
		this.lxkpts = lxkpts;
	}
	
	public static Jcxx getJcxx(){
		Jcxx jcxx=new Jcxx();
		Connection conn = null;
		String sql="select * from WLKP_XTJCSZ";
		try {
			conn = DBConnection.getConnection();
			
			if (conn != null) {
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					jcxx.setFpzs(rs.getInt("FPZS"));
					jcxx.setLxkpts(rs.getInt("LXKPTS"));
					jcxx.setLxkpzje(rs.getDouble("LXKPZJE"));
					jcxx.setLxkpzs(rs.getInt("LXKPZS"));
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

		return jcxx;
	}
	public static void updateJcxx(Jcxx jcxx){
		 String sql="update WLKP_XTJCSZ SET FPZS="+jcxx.getFpzs()+",LXKPZJE="
		 +jcxx.getLxkpzje()+",LXKPTS="+jcxx.getLxkpts()+",LXKPZS="+jcxx.getLxkpzs()
		 ;
		 Query.updateField(sql);
	}
	
}
