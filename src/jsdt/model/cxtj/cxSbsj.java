package jsdt.model.cxtj;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import jsdt.model.Fpjmx;
import jsdt.model.Fpkj;
import jsdt.model.Sbsj;
import jsdt.model.Wsbsj;
import jsdt.model.baseClass;
import jsdt.tools.DBConnection;
import jsdt.tools.Env;
import jsdt.tools.Query;
import jsdt.tools.Util;

public class cxSbsj {
	//获取纳税人发票信息
	public HashMap selectFpxx(int pageNo,int pageSize,String nsrwjbm,String startdate,
			String enddate ) {
		HashMap map = new HashMap();
		ArrayList al = new ArrayList();
		Connection conn = null;
		String dateStr = "";
		if(startdate != null && !startdate.equals("")){
			dateStr += " and f.FPLGRQ >= '" + startdate + "'";
		}
		if(enddate != null && !enddate.equals("")){
			dateStr += " and f.FPLGRQ <= '" + enddate + "'";
		}
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select top " + pageSize + " * from SKQ_FPJMX f where SID not in (select top " 
							+ (pageNo-1)*pageSize +" SID from SKQ_FPJMX f where f.NSRWJBM ='"
							+ nsrwjbm + "' " + dateStr + " order by f.FPQSH ASC) and f.NSRWJBM ='"
							+ nsrwjbm + "' " + dateStr + " order by f.FPQSH ASC";
				String strsql = "select SID from SKQ_FPJMX f where  f.NSRWJBM ='"
							+ nsrwjbm+"'" + dateStr;
				System.out.println(sql);
				int maxCount = baseClass.getFieldCount(strsql);
				int maxPage = (int)Math.ceil((double)maxCount/pageSize);
				map.put("maxCount", maxCount);
				map.put("maxPage", maxPage);
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					Fpjmx fpjmx = new Fpjmx();
					fpjmx.setSid(rs.getInt("SID"));
					fpjmx.setNsrwjbm(rs.getString("NSRWJBM"));
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
					map.put("al", al);
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
		return map;
	}
	//查询单卷发票开具汇总信息
	public HashMap selectDjkjxx(int pageNo,int pageSize,String fpdm,
			int fpqsh,int fpjzh ) {
		HashMap map = new HashMap();
		Connection conn = null;
		int maxCount = (fpjzh - fpqsh + 1)/200;//总卷数
		int maxPage = (int)Math.ceil((double)maxCount/pageSize);
		map.put("maxCount", maxCount);
		map.put("maxPage", maxPage);
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
					ArrayList arrayList = new ArrayList();
					for(int i = (pageNo-1)*pageSize; i < (pageNo*pageSize<maxCount?pageNo*pageSize:maxCount); i++){
						HashMap m = new HashMap();
						//查询单卷正常开票数汇总
						String zcpsql = " select SID from SKQ_FPKJ where KPLX = 1 and FPDM=" + fpdm + " and FPHM>=" 
							+ (fpqsh + i*200) + " and FPHM <= " + ((fpqsh +  i*200 + 200 - 1)<=fpjzh?(fpqsh + i*200 + 200 - 1):fpjzh);
						//查询单卷退票数汇总
						String tpsql = " select SID from SKQ_FPKJ where KPLX = 2 and FPHM>=" 
							+ (fpqsh + i*200) + " and FPHM <= " + ((fpqsh +  i*200 + 200 - 1)<=fpjzh?(fpqsh + i*200 + 200 - 1):fpjzh);
						//查询废票数汇总
						String fpsql = " select SID from SKQ_FPKJ where KPLX = 3 and FPHM>=" 
							+ (fpqsh + i*200) + " and FPHM <= " + ((fpqsh +  i*200 + 200 - 1)<=fpjzh?(fpqsh + i*200 + 200 - 1):fpjzh);
						//查询单卷正常开票金额汇总
						String zcpzje = " select HJZJE from SKQ_FPKJ where KPLX = 1 and FPDM=" + fpdm + " and FPHM>=" 
						+ (fpqsh + i*200) + " and FPHM <= " + ((fpqsh +  i*200 + 200 - 1)<=fpjzh?(fpqsh + i*200 + 200 - 1):fpjzh);
						//查询单卷退票金额汇总
						String tpzje = " select HJZJE from SKQ_FPKJ where KPLX = 2 and FPDM=" + fpdm + " and FPHM>=" 
						+ (fpqsh + i*200) + " and FPHM <= " + ((fpqsh +  i*200 + 200 - 1)<=fpjzh?(fpqsh + i*200 + 200 - 1):fpjzh);
						m.put("fpqsh", fpqsh + i*200);
						m.put("fpjzh", fpqsh +  i*200 + 200 - 1);
						m.put("zcphz", baseClass.getFieldCount(zcpsql));
						m.put("tphz", baseClass.getFieldCount(tpsql));
						m.put("fphz", baseClass.getFieldCount(fpsql));
						m.put("zcpzje", baseClass.getFieldSum("hjzje",zcpzje));
						m.put("tpzje", baseClass.getFieldSum("hjzje",tpzje));
						
						arrayList.add(m);
				}
					map.put("arrayList", arrayList);
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
		return map;
	}
	
	//查询单卷发票开具明细汇总信息
	public HashMap selectDjkjmx(int pageNo,int pageSize,String fpdm,
			int fpqsh,int fpjzh ) {
		HashMap map = new HashMap();
		Connection conn = null;
		int maxCount = fpjzh - fpqsh + 1;//发票总数
		int maxPage = (int)Math.ceil((double)maxCount/pageSize);
		map.put("maxCount", maxCount);
		map.put("maxPage", maxPage);
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				ArrayList al = new ArrayList();
				for(int i = fpqsh+(pageNo-1)*pageSize; i < ((fpqsh+pageNo*pageSize-1<=fpjzh)?fpqsh+pageNo*pageSize:fpjzh); i++){
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
						al.add(fpkj);
					}
				}
				map.put("al", al);
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
		return map;
	}
	//2011-4-9
	public ArrayList newSelectWsbsj(String nsrwjbm, String startdate,
			String enddate) {
		ArrayList al = new ArrayList();
		String forSBSJ = "";
		Connection conn = null;
		  boolean wsb=false;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select a.nsrwjbm,a.nsrmc from SKQ_NSRXX a where 1=1";
				if (nsrwjbm != null && !nsrwjbm.equals("")) {
					sql = sql + " and a.nsrwjbm='" + nsrwjbm+"'";
				}
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					forSBSJ = "";
					wsb=false;
					Sbsj sbsj = new Sbsj();
					Wsbsj wsbsj = new Wsbsj();
					wsbsj.setNsrwjbm(rs.getString("NSRWJBM"));
					forSBSJ = forSBSJ + " and nsrwjbm='" + wsbsj.getNsrwjbm()+"'";
					wsbsj.setNsrmc(Util.iso8859togbk(rs.getString("NSRMC")));
					if (startdate != null && !"".equals(startdate)) {
						forSBSJ = forSBSJ + " and SSKSSJ>='" + startdate + "'";
					}
					if (enddate != null && !"".equals(enddate)) {
						forSBSJ = forSBSJ + " and SSKSSJ<='" + enddate + "'";
					}
					if (sbsj.selectWsb(forSBSJ) > 0) {
						wsb=true;
						wsbsj.setWsbsj(wsb);
					}
					if(wsb==false){
					al.add(wsbsj);
					//System.out.println(al.size());
					}
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
	//2011-4-9
	public ArrayList selectWsbsj(String nsrwjbm, String startdate,
			String enddate) {
		ArrayList al = new ArrayList();
		String forSBSJ = "";
		String forFPKJ = "";
		String forRJY = "";
		Connection conn = null;
		  boolean wsb=false;
		  boolean wsbmx=false;
		  boolean wsbrjy=false;

		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select a.nsrwjbm,a.nsrmc from SKQ_NSRXX a where 1=1";
				if (nsrwjbm != null && !nsrwjbm.equals("")) {
					sql = sql + " and a.nsrwjbm='" + nsrwjbm+"'";
				}
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					forSBSJ = "";
					forFPKJ = "";
					forRJY = "";
					wsb=false;
					wsbmx=false;
					wsbrjy=false;
					Sbsj sbsj = new Sbsj();
					Wsbsj wsbsj = new Wsbsj();
					wsbsj.setNsrwjbm(rs.getString("NSRWJBM"));
					forSBSJ = forSBSJ + " and nsrwjbm='" + wsbsj.getNsrwjbm()+"'";
					wsbsj.setNsrmc(Util.iso8859togbk(rs.getString("NSRMC")));
					if (startdate != null && !"".equals(startdate)) {
						forSBSJ = forSBSJ + " and SSKSSJ>='" + startdate + "'";
					}
					if (enddate != null && !"".equals(enddate)) {
						forSBSJ = forSBSJ + " and SSKSSJ<='" + enddate + "'";
					}
					
					if (sbsj.selectWsb(forSBSJ) > 0) {
						wsb=true;
						wsbsj.setWsbsj(wsb);
					}
					forFPKJ=forFPKJ+ " and nsrwjbm='" + wsbsj.getNsrwjbm()+"'";
					if (startdate != null && !"".equals(startdate)) {
						forFPKJ = forFPKJ + " and KPRQ>='" + startdate + "'";
					}
					if (enddate != null && !"".equals(enddate)) {
						forFPKJ = forFPKJ + " and KPRQ<='" + enddate + "'";
					}
					
					if(sbsj.selectWsbMX(forFPKJ)>0){
						wsbmx=true;
						wsbsj.setWsbmx(wsbmx);
					}
					if (startdate != null && !"".equals(startdate)) {
						forRJY = forRJY + " and DQRQ>='" + startdate + "'";
					}
					if (enddate != null && !"".equals(enddate)) {
						forRJY = forRJY + " and DQRQ<='" + enddate + "'";
					}
					String sql1 = "select JQBH from SKQ_JQXX where NSRWJBM='" +wsbsj.getNsrwjbm()
					+ "'";
			     String fieldname = "JQBH";

			           String jqbh = Query.getFieldStrCx(sql1, fieldname);
			           wsbsj.setJqbh(jqbh);
			           if (jqbh != null && !"".equals(jqbh)) {
			        	   forRJY = forRJY + " and JQBH in(" + jqbh + ")";
			   		}
					if(sbsj.selectWsbRJY(forRJY)>0){
						wsbrjy=true;
						wsbsj.setWsbrjy(wsbrjy);
					}
					if(wsb==false||wsbmx==false||wsbrjy==false){
					al.add(wsbsj);
					//System.out.println(al.size());
					}
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

	@SuppressWarnings("unused")
	public ArrayList selectSbsj(String sqlStr,String startdate,String enddate) {
		ArrayList al = new ArrayList();
		Connection conn = null;
		try { 
			conn = DBConnection.getConnection();
			if (conn != null) {
				if(startdate==null||startdate.trim().equals("")){
					startdate=Util.getPreviousMonthFirst();
				}
				if(enddate==null||enddate.trim().equals("")){
					enddate=Util.getPreviousMonthEnd();
				}
				//String oldStartDate=Env.getInstance().getProperty("OldStartDate");
				//oldStartDate=Util.toxkrq(oldStartDate);
				
				//String oldEndDate=Env.getInstance().getProperty("OldEndDate");
				//oldEndDate=Util.toxkrq(oldEndDate);
				//System.out.println(enddate+"  "+oldEndDate);
//				if (Util.compareData(startdate, oldEndDate) <=0) {
////					startdate=Util.toxkrq(startdate);
////					enddate=Util.toxkrq(enddate);
//					String sql="select * from SKQ_SBSJ_COMPARE where startdate>='"+startdate+"' and enddate <='"+enddate+"'  and  SID in("
//					+ sqlStr + ") order by CODE ASC";
//				System.out.println("1号："+sql);
//					PreparedStatement ps = conn.prepareStatement(sql);
//					ResultSet rs = ps.executeQuery();
//					while (rs.next()) {
//						Sbsj sbsj = new Sbsj();
//						sbsj.setSskssj(rs.getString("STARTDATE"));
//						sbsj.setSsjzsj(rs.getString("ENDDATE"));
//						sbsj.setZcpfs(rs.getInt("ZCPFS"));
//						sbsj.setTpfs(rs.getInt("TPFS"));
//						sbsj.setFpfs(rs.getInt("FPFS"));
//						sbsj.setZcpzje(new BigDecimal(rs.getDouble("ZCPZJE")).divide(new BigDecimal(100)).doubleValue());
//						sbsj.setTpzje(new BigDecimal(rs.getDouble("TPZJE")).divide(new BigDecimal(100)).doubleValue());
//						sbsj.setJqbh(rs.getString("MACHINE_NO"));
//						sbsj.setNsrwjbm(rs.getString("CODE"));
//						sbsj.setNsrmc(Util.iso8859togbk(rs.getString("NSRMC")));
//						sbsj.setYsbSum(rs.getInt("YSBSUM"));
//						al.add(sbsj);
//					}
//				}else{
					
				String sql = "select a.*,b.NSRMC,(select count(c.SID) from SKQ_SBSJMX c where c.PARENTID=a.SID) as num from SKQ_SBSJ a left outer join SKQ_NSRXX b on a.NSRWJBM=b.NSRWJBM and b.STATUS=1 where a.SID in("
						+ sqlStr + ") order by a.NSRWJBM ASC";
				System.out.println("sql:="+sql);
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					Sbsj sbsj = new Sbsj();
					sbsj.setSid(rs.getInt("SID"));
					sbsj.setSskssj(rs.getString("SSKSSJ").substring(0, 10));
					sbsj.setSsjzsj(rs.getString("SSJZSJ").substring(0, 10));
					sbsj.setZcpfs(rs.getInt("ZCPFS"));
					sbsj.setTpfs(rs.getInt("TPFS"));
					sbsj.setFpfs(rs.getInt("FPFS"));
					sbsj.setZcpzje(rs.getDouble("ZCPZJE"));
					sbsj.setTpzje(rs.getDouble("TPZJE"));
					sbsj.setJqbh(rs.getString("JQBH"));
					sbsj.setNsrwjbm(rs.getString("NSRWJBM"));
					sbsj.setNsrmc(Util.iso8859togbk(rs.getString("NSRMC")));
					sbsj.setSbzbnum(rs.getInt("num"));
                    String sql1="select count(*) as totalCount from SKQ_FPKJ WHERE NSRWJBM='"+rs.getString("NSRWJBM")+"' and JQBH='"+rs.getString("JQBH")+"' and KPRQ>='"+rs.getString("SSKSSJ").substring(0, 10)+"' and KPRQ<='"+rs.getString("SSJZSJ").substring(0, 10)+" 23:59:59'" ; 
					String field="totalCount";
                    sbsj.setYsbSum(Query.getFieldInt(sql1, field));
					al.add(sbsj);
				}
				rs.close();
				ps.close();
				
				conn.close();
				}
//			}

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
	//获取历史申报数据
	public ArrayList selectSbsjExcelForHistory(String sqlStr) {
		ArrayList al = new ArrayList();
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select  * from SKQ_SBSJ_COMPARE a where 1=1 "
						+ sqlStr + " order by a.CODE ASC";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					Sbsj sbsj = new Sbsj();
					sbsj.setSid(rs.getInt("SID"));
					sbsj.setSskssj(rs.getString("STARTDATE"));
					sbsj.setSsjzsj(rs.getString("ENDDATE"));
					sbsj.setZcpfs(rs.getInt("ZCPFS"));
					sbsj.setTpfs(rs.getInt("TPFS"));
					sbsj.setFpfs(rs.getInt("FPFS"));
					sbsj.setZcpzje(rs.getDouble("ZCPZJE"));
					sbsj.setTpzje(rs.getDouble("TPZJE"));
					sbsj.setJqbh(rs.getString("MACHINE_NO"));
					sbsj.setNsrwjbm(rs.getString("CODE"));
					sbsj.setNsrmc(Util.iso8859togbk(rs.getString("NSRMC")));
					sbsj.setSbzbnum(rs.getInt("YSBSUM"));

					al.add(sbsj);
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

	
	
	//获取申报数据
	public ArrayList selectSbsjExcel(String sqlStr) {
		ArrayList al = new ArrayList();
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select a.*,b.NSRMC,(select count(c.SID) from SKQ_SBSJMX c where c.PARENTID=a.SID) as num from SKQ_SBSJ a left outer join SKQ_NSRXX b on a.NSRWJBM=b.NSRWJBM and b.STATUS=1 where 1=1 "
						+ sqlStr + " order by a.NSRWJBM ASC";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					Sbsj sbsj = new Sbsj();
					sbsj.setSid(rs.getInt("SID"));
					sbsj.setSskssj(rs.getString("SSKSSJ").substring(0, 10));
					sbsj.setSsjzsj(rs.getString("SSJZSJ").substring(0, 10));
					sbsj.setZcpfs(rs.getInt("ZCPFS"));
					sbsj.setTpfs(rs.getInt("TPFS"));
					sbsj.setFpfs(rs.getInt("FPFS"));
					sbsj.setZcpzje(rs.getDouble("ZCPZJE"));
					sbsj.setTpzje(rs.getDouble("TPZJE"));
					sbsj.setJqbh(rs.getString("JQBH"));
					sbsj.setNsrwjbm(rs.getString("NSRWJBM"));
					sbsj.setNsrmc(Util.iso8859togbk(rs.getString("NSRMC")));
					sbsj.setSbzbnum(rs.getInt("num"));

					al.add(sbsj);
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

	// 获取申报数据明细信息
	public ArrayList selectSbsjzb(int parentid) {
		Connection conn = null;
		ArrayList al = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select a.JE,a.SMBM,a.KPLX,b.SL,b.SMMC from SKQ_SBSJMX a left outer join SKQ_PMSZ b on a.SMBM=b.SMBM where a.PARENTID = "
						+ parentid;
				System.out.println("sql==" + sql);
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				al = new ArrayList();
				while (rs.next()) {
					HashMap hm = new HashMap();
					hm.put("je", rs.getDouble("JE"));
					hm.put("smbm", rs.getString("SMBM"));
					hm.put("smmc", Util.iso8859togbk(rs.getString("SMMC")));
					hm.put("kplx", rs.getInt("KPLX"));
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

	public HashMap selectSbsjHz(String sqlStr) {
		HashMap hm = new HashMap();
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select sum(a.ZCPFS) as hjzcpfs,sum(a.TPFS) as hjtpfs,sum(a.FPFS) as hjfpfs,sum(a.ZCPZJE) as hjzcpzje,sum(a.TPZJE) as hjtpzje from SKQ_SBSJ a where 1=1 "
						+ sqlStr;
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					hm.put("hjzcpfs", String.valueOf(rs.getInt("hjzcpfs")));
					hm.put("hjtpfs", String.valueOf(rs.getInt("hjtpfs")));
					hm.put("hjfpfs", String.valueOf(rs.getInt("hjfpfs")));
					hm.put("hjzcpzje", String.valueOf(rs.getDouble("hjzcpzje")));
					hm.put("hjtpzje", String.valueOf(rs.getDouble("hjtpzje")));
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
		return hm;
	}
}
