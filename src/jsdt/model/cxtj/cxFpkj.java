package jsdt.model.cxtj;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

import javax.management.Query;

import jsdt.model.Fpjmx;
import jsdt.model.Fpkj;
import jsdt.model.Nsrxx;
import jsdt.model.baseClass;
import jsdt.tools.DBConnection;
import jsdt.tools.Util;

import jsdt.tools.*;;

public class cxFpkj extends baseClass {
	// 查询发票汇总信息
	public ArrayList selectDjhzDcExcel(String nsrwjbm,String startdate,
			String enddate ) {
		ArrayList al = new ArrayList();
		Connection conn = null;
		String dateStr = "";
		Nsrxx nsrxx = new Nsrxx(nsrwjbm);
		if(startdate != null && !startdate.equals("")){
			dateStr += " and f.FPLGRQ >= '" + startdate + "'";
		}
		if(enddate != null && !enddate.equals("")){
			dateStr += " and f.FPLGRQ <= '" + enddate + "'";
		}
		try {
			conn =DBConnection.getConnection();
			if (conn != null) {
				String sql = "select  * from SKQ_FPJMX f  where f.NSRWJBM ="
							+ nsrwjbm + dateStr + " order by f.FPQSH ASC";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					Fpjmx fpjmx = new Fpjmx();
					fpjmx.setSid(rs.getInt("SID"));
					fpjmx.setNsrwjbm(rs.getString("NSRWJBM"));
					fpjmx.setNsrmc(nsrxx.getNsrmc());
					fpjmx.setFpbm(rs.getString("FPBM"));
					fpjmx.setFpdm(rs.getString("FPDM"));
					fpjmx.setFpqsh(rs.getInt("FPQSH"));
					fpjmx.setFpjzh(rs.getInt("FPJZH"));
					fpjmx.setFpdw(rs.getInt("FPDW"));
					fpjmx.setFplgrq(rs.getString("FPLGRQ"));
					fpjmx.setFpxfzt(rs.getInt("FPXFZT"));
					fpjmx.setFplgzt(rs.getInt("FPLGZT"));
					fpjmx.setMxsczt(rs.getInt("MXSCZT"));
					fpjmx.setJqbh(rs.getString("JQBH"));
					al.add(fpjmx);
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
		return al;
	}
	//查询单卷发票开具汇总信息
	public ArrayList selectDjkjhzDcExcel(String nsrwjbm,String fpqsh,
			String fpjzh,String fpdm ) {
		Connection conn = null;
		ArrayList arrayList = new ArrayList();
		int qsh = Integer.parseInt(fpqsh);
		int jzh = Integer.parseInt(fpjzh);
		try {
			conn =DBConnection.getConnection();
			if (conn != null) {
					for(int i = 0; i < (jzh - qsh + 1)/200; i++){
						HashMap m = new HashMap();
						//查询单卷正常开票数汇总
						String zcpsql = " select SID from SKQ_FPKJ where KPLX = 1 and FPDM=" + fpdm + " and FPHM>=" 
							+ (qsh + i*200) + " and FPHM <= " + (qsh+(i+1)*200-1);
						System.out.println(zcpsql);
						//查询单卷退票数汇总
						String tpsql = " select SID from SKQ_FPKJ where KPLX = 2 and FPHM>=" 
							+ (qsh + i*200) + " and FPHM <= " + (qsh+(i+1)*200-1);
						System.out.println(tpsql);
						//查询废票数汇总
						String fpsql = " select SID from SKQ_FPKJ where KPLX = 3 and FPHM>=" 
							+ (qsh + i*200) + " and FPHM <= " + (qsh+(i+1)*200-1);
						System.out.println(fpsql);
						//查询单卷正常开票金额汇总
						String zcpzje = " select HJZJE from SKQ_FPKJ where KPLX = 1 and FPDM=" + fpdm + " and FPHM>=" 
						+ (qsh + i*200) + " and FPHM <= " + (qsh+(i+1)*200-1);
						System.out.println(zcpzje);
						//查询单卷退票金额汇总
						String tpzje = " select HJZJE from SKQ_FPKJ where KPLX = 2 and FPDM=" + fpdm + " and FPHM>=" 
						+ (qsh + i*200) + " and FPHM <= " + (qsh+(i+1)*200-1);
						String first = " select TOP 1 KPRQ from SKQ_FPKJ where  FPDM=" + fpdm + " and FPHM>=" 
								+ (qsh + i*200) + " and FPHM <= " + (qsh+(i+1)*200-1)+" ORDER BY KPRQ ASC";
						String end = " select TOP 1 KPRQ from SKQ_FPKJ where  FPDM=" + fpdm + " and FPHM>=" 
								+ (qsh + i*200) + " and FPHM <= " + (qsh+(i+1)*200-1)+" ORDER BY KPRQ desc";
						m.put("fpqsh", qsh + i*200);
						m.put("fpjzh", qsh+(i+1)*200-1);
						m.put("zcphz", baseClass.getFieldCount(zcpsql));
						m.put("tphz", baseClass.getFieldCount(tpsql));
						m.put("fphz", baseClass.getFieldCount(fpsql));
						m.put("zcpzje", baseClass.getFieldSum("HJZJE",zcpzje));
						m.put("tpzje", baseClass.getFieldSum("HJZJE",tpzje));
						m.put("first",jsdt.tools.Query.getFieldStr(first, "KPRQ").substring(0,10));
						m.put("end",jsdt.tools.Query.getFieldStr(end, "KPRQ").substring(0,10));
						arrayList.add(m);
				}
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
		return arrayList;
	}
	
	//查询单卷发票开具明细信息
	public ArrayList selectDjkjmxDcExcel(String fpqsh,
			String fpjzh,String fpdm ) {
		Connection conn = null;
		ArrayList arrayList = new ArrayList();
		int qsh = Integer.parseInt(fpqsh);
		int jzh = Integer.parseInt(fpjzh);
		try {
			conn =DBConnection.getConnection();
			if (conn != null) {
				for(int i = qsh; i <= jzh; i++){
					//查询单卷正常开票数汇总
					String sql = " select * from SKQ_FPKJ where FPDM=" + fpdm + " and FPHM=" + i;
					PreparedStatement ps = conn.prepareStatement(sql);
					ResultSet rs = ps.executeQuery();
					while(rs.next()){
						Fpkj fpkj = new Fpkj();
						fpkj.setNsrwjbm(rs.getString("NSRWJBM"));
						fpkj.setJqbh(rs.getString("JQBH"));
						fpkj.setFphm(rs.getInt("FPHM"));
						fpkj.setKprq(rs.getString("KPRQ"));
						fpkj.setKplx(rs.getInt("KPLX"));
						fpkj.setFpdm(rs.getString("FPDM"));
						fpkj.setHjzje(rs.getInt("HJZJE"));
						fpkj.setYfphm(rs.getInt("YFPHM"));
						arrayList.add(fpkj);
					}
				}
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
		return arrayList;
	}
	// 查询发票信息
	public ArrayList selectFpkj(String sqlStr) {
		Connection conn = null;
		ArrayList al = null;
		DecimalFormat dg = new DecimalFormat("0.00");
		try {
			conn =DBConnection.getConnection();
			if (conn != null) {
				//start modify by yinkun 20101221
//				String sql = "select a.*,b.NSRMC from SKQ_FPKJ a left outer join SKQ_NSRXX b on a.NSRWJBM=b.NSRWJBM and b.STATUS=1 where a.SID in("+sqlStr+") order by a.FPDM,a.FPHM DESC";
				String sql = "select distinct a.SID as id,a.*,b.NSRMC,c.XMMC,c.DJ,c.SL from SKQ_FPKJ a left outer join SKQ_NSRXX b on a.NSRWJBM=b.NSRWJBM and b.STATUS=1 left outer join SKQ_FPKJXM c on a.FPDM=c.FPDM and a.FPHM = c.FPHM where a.SID in("+sqlStr+") order by a.FPDM,a.FPHM asc";
				//end modify by yinkun 20101221
				System.out.println("cxFpkj_selectFpkj:sql=="+sql);
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				al = new ArrayList();
				 
				
				while (rs.next()) {
					Fpkj fpkj = new Fpkj();
					fpkj.setSid(Integer.valueOf(rs.getInt("SID")));
					fpkj.setNsrwjbm(rs.getString("NSRWJBM"));
					fpkj.setNsrmc(Util.iso8859togbk(rs.getString("NSRMC")));
					//start modify by yinkun 20101221
					//System.out.println("XMMC=="+rs.getString("XMMC"));
					fpkj.setXmmc(rs.getString("XMMC"));
					fpkj.setDj(Double.parseDouble(dg.format(rs.getDouble("DJ"))));
					fpkj.setSl(Double.parseDouble(dg.format(rs.getDouble("SL"))));
					//end modify by yinkun 20101221
					fpkj.setJqbh(rs.getString("JQBH"));
					fpkj.setKprq(rs.getString("KPRQ")==null?"":rs.getString("KPRQ").substring(0,19));
					fpkj.setKplx(Integer.valueOf(rs.getInt("KPLX")));
					fpkj.setFpdm(rs.getString("FPDM"));
					fpkj.setFphm(Integer.valueOf(rs.getInt("FPHM")));
					fpkj.setHjzje(Double.valueOf(rs.getDouble("HJZJE")));
					fpkj.setYfphm(Integer.valueOf(rs.getInt("YFPHM")));
					fpkj.setFkdw(Util.iso8859togbk(rs.getString("FKDW")));
					fpkj.setSky(Util.iso8859togbk(rs.getString("SKY")));
					fpkj.setXms(rs.getInt("XMS"));
					fpkj.setZkzje(rs.getDouble("ZKZJE"));
//					String sql1 = "select XMMC from SKQ_FPKJXM where PARENTID ="+rs.getInt("SID");
//					String fieldname= "XMMC";
//					String xmmc = Query.getFieldStr(sql1, fieldname);
//					fpkj.setXmmc(xmmc);
					//System.out.println("XMMCQQQ=="+fpkj.getXmmc());
					al.add(fpkj);
				}
				rs.close();
				ps.close();
				conn.close();
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
	
	
	// 查询发票信息
	public ArrayList selectFpkjDcExcel(String sqlStr) {
		Connection conn = null;
		ArrayList al = null;
		DecimalFormat dg = new DecimalFormat("0.00");
		try {
			conn =DBConnection.getConnection();
			if (conn != null) {
				//start modify by yinkun 20101221
//				String sql = "select a.*,b.NSRMC from SKQ_FPKJ a left outer join SKQ_NSRXX b on a.NSRWJBM=b.NSRWJBM and b.STATUS=1 where a.SID in("+sqlStr+") order by a.FPDM,a.FPHM DESC";
				String sql = "select distinct a.SID as id,a.*,b.NSRMC,c.XMMC,c.DJ,c.SL from SKQ_FPKJ a left outer join SKQ_NSRXX b on a.NSRWJBM=b.NSRWJBM and b.STATUS=1 left outer join SKQ_FPKJXM c on a.FPHM = c.FPHM where 1=1 "+sqlStr+" order by a.FPDM,a.FPHM asc";
				//end modify by yinkun 20101221
				System.out.println("sql=="+sql);
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				al = new ArrayList();
				while (rs.next()) {
					Fpkj fpkj = new Fpkj();
					fpkj.setSid(Integer.valueOf(rs.getInt("SID")));
					fpkj.setNsrwjbm(rs.getString("NSRWJBM"));
					fpkj.setNsrmc(Util.iso8859togbk(rs.getString("NSRMC")));
					//start modify by yinkun 20101221
					//System.out.println("XMMC=="+rs.getString("XMMC"));
					fpkj.setXmmc(rs.getString("XMMC"));
					fpkj.setDj(Double.parseDouble(dg.format(rs.getDouble("DJ"))));
					fpkj.setSl(Double.parseDouble(dg.format(rs.getDouble("SL"))));
					//end modify by yinkun 20101221
					fpkj.setJqbh(rs.getString("JQBH"));
					fpkj.setKprq(rs.getString("KPRQ")==null?"":rs.getString("KPRQ"));
					fpkj.setKplx(Integer.valueOf(rs.getInt("KPLX")));
					fpkj.setFpdm(rs.getString("FPDM"));
					fpkj.setFphm(Integer.valueOf(rs.getInt("FPHM")));
					fpkj.setHjzje(Double.valueOf(rs.getDouble("HJZJE")));
					fpkj.setYfphm(Integer.valueOf(rs.getInt("YFPHM")));
					fpkj.setFkdw(Util.iso8859togbk(rs.getString("FKDW")));
					fpkj.setSky(Util.iso8859togbk(rs.getString("SKY")));
					fpkj.setXms(rs.getInt("XMS"));
					fpkj.setZkzje(rs.getDouble("zkzje"));
//					String sql1 = "select XMMC from SKQ_FPKJXM where PARENTID ="+rs.getInt("SID");
//					String fieldname= "XMMC";
//					String xmmc = Query.getFieldStr(sql1, fieldname);
//					fpkj.setXmmc(xmmc);
					//System.out.println("XMMCQQQ=="+fpkj.getXmmc());
					al.add(fpkj);
				}
				rs.close();
				ps.close();
				conn.close();
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
	
	public ArrayList selectFpkjProj(int fphm) {
		Connection conn = null;
		PreparedStatement ps =null;
		ResultSet rs =null;
		ArrayList al = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select a.*,b.SMMC from SKQ_FPKJXM a left outer join SKQ_PMSZ b on a.SMBM=b.SMBM where a.fphm="
						+ fphm;
				 ps = conn.prepareStatement(sql);
				 rs = ps.executeQuery();
				al = new ArrayList();
				while (rs.next()) {
					HashMap hm = new HashMap();

					hm.put("sid", Integer.valueOf(rs.getInt("SID")));
					hm.put("parentid", Integer.valueOf(rs.getInt("PARENTID")));
					hm.put("xmmc", Util.iso8859togbk(rs.getString("XMMC")));
					hm.put("xsws", Integer.valueOf(rs.getInt("XSWS")));
					hm.put("sl", Double.valueOf(rs.getDouble("SL")));
					hm.put("dj", Double.valueOf(rs.getDouble("DJ")));
					hm.put("je", Double.valueOf(rs.getDouble("JE")));
					hm.put("smbm", rs.getString("SMBM"));
					hm.put("smmc", Util.iso8859togbk(rs.getString("SMMC")));
					al.add(hm);
				}
				rs.close();
				ps.close();
				conn.close();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnection.closeAll(conn, ps, rs);
		}
		return al;
	}
	
	
	// 查询发票信息
	public ArrayList selectFpkjDcXml(String sqlStr) {
		Connection conn = null;
		ArrayList al = null;
		DecimalFormat dg = new DecimalFormat("0.00");
		try {
			conn =DBConnection.getConnection();
			if (conn != null) {
				//start modify by yinkun 20101221
//				String sql = "select a.*,b.NSRMC from SKQ_FPKJ a left outer join SKQ_NSRXX b on a.NSRWJBM=b.NSRWJBM and b.STATUS=1 where a.SID in("+sqlStr+") order by a.FPDM,a.FPHM DESC";
				String sql = "select distinct a.SID as id,a.*,b.NSRMC,c.XMMC,c.DJ,c.SL from SKQ_FPKJ a left outer join SKQ_NSRXX b on a.NSRWJBM=b.NSRWJBM and b.STATUS=1 left outer join SKQ_FPKJXM c on a.FPHM = c.FPHM where 1=1 "+sqlStr+" order by a.FPDM,a.FPHM asc";
				//end modify by yinkun 20101221
				System.out.println("sql=="+sql);
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				al = new ArrayList();
				while (rs.next()) {
					Fpkj fpkj = new Fpkj();
					fpkj.setSid(Integer.valueOf(rs.getInt("SID")));
					fpkj.setNsrwjbm(rs.getString("NSRWJBM"));
					fpkj.setNsrmc(Util.iso8859togbk(rs.getString("NSRMC")));
					//start modify by yinkun 20101221
					//System.out.println("XMMC=="+rs.getString("XMMC"));
					fpkj.setXmmc(rs.getString("XMMC"));
					fpkj.setDj(Double.parseDouble(dg.format(rs.getDouble("DJ"))));
					fpkj.setSl(Double.parseDouble(dg.format(rs.getDouble("SL"))));
					//end modify by yinkun 20101221
					fpkj.setJqbh(rs.getString("JQBH"));
					fpkj.setKprq(rs.getString("KPRQ")==null?"":rs.getString("KPRQ"));
					fpkj.setKplx(Integer.valueOf(rs.getInt("KPLX")));
					fpkj.setFpdm(rs.getString("FPDM"));
					fpkj.setFphm(Integer.valueOf(rs.getInt("FPHM")));
					fpkj.setHjzje(Double.valueOf(rs.getDouble("HJZJE")));
					fpkj.setYfphm(Integer.valueOf(rs.getInt("YFPHM")));
					fpkj.setFkdw(Util.iso8859togbk(rs.getString("FKDW")));
					fpkj.setSky(Util.iso8859togbk(rs.getString("SKY")));
					fpkj.setXms(rs.getInt("XMS"));
					fpkj.setFpxmxx(selectFpkjProj(fpkj.getFphm()));
//					String sql1 = "select XMMC from SKQ_FPKJXM where PARENTID ="+rs.getInt("SID");
//					String fieldname= "XMMC";
//					String xmmc = Query.getFieldStr(sql1, fieldname);
//					fpkj.setXmmc(xmmc);
					//System.out.println("XMMCQQQ=="+fpkj.getXmmc());
					al.add(fpkj);
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
		return al;
	}
}
