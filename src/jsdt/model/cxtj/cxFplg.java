package jsdt.model.cxtj;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jsdt.model.Fpjmx;
import jsdt.tools.DBConnection;
import jsdt.tools.Query;
import jsdt.tools.Util;

public class cxFplg {
	//查询纳税户领购发票信息
	public ArrayList selectFplg(String sqlStr) {
		ArrayList al = null;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select a.*,b.FPMC,c.NSRMC from SKQ_FPJMX a left outer join SKQ_FP b on a.FPBM=b.FPBM left outer join SKQ_NSRXX c on a.NSRWJBM=c.NSRWJBM where 1=1 and a.SID in("+sqlStr+") order by a.fpqsh DESC";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				al = new ArrayList();
				while (rs.next()) {
					Fpjmx fpjmx = new Fpjmx();
					fpjmx.setSid(rs.getInt("SID"));
					fpjmx.setNsrwjbm(rs.getString("NSRWJBM"));
					fpjmx.setNsrmc(Util.iso8859togbk(rs.getString("NSRMC")));
					fpjmx.setFpbm(rs.getString("FPBM"));
					fpjmx.setFpmc(Util.iso8859togbk(rs.getString("FPMC")));
					fpjmx.setFpdm(rs.getString("FPDM"));
					fpjmx.setFpqsh(rs.getInt("FPQSH"));
					fpjmx.setFpjzh(rs.getInt("FPJZH"));
					fpjmx.setFpdw(rs.getInt("FPDW"));
					fpjmx.setJqbh(rs.getString("JQBH"));
					fpjmx.setFpxfzt(rs.getInt("FPXFZT"));
					fpjmx.setFplgzt(rs.getInt("FPLGZT"));
					fpjmx.setMxsczt(rs.getInt("MXSCZT"));
					fpjmx.setFpzs(rs.getInt("FPJZH")+1-rs.getInt("FPQSH"));
					fpjmx.setFplgrq(rs.getString("FPLGRQ").substring(0,10));
                    String sql1="select top 1 * from SKQ_FPKJ where FPDM='"+Util.zerosFront(rs.getString("FPDM"),20)+"' and JQBH='"+fpjmx.getJqbh()+"' and FPHM>="+rs.getInt("FPQSH")+" and" +
                    		" FPHM<="+rs.getInt("FPJZH")+" order by FPHM DESC";
                    System.out.println("sql1=="+sql1);
                    int hasUsed=0;
                    int fpjzhm=Query.getFieldInt(sql1, "FPHM");//发票截至号码
                    System.out.println(fpjzhm);
                    if(fpjzhm==0){
                    	hasUsed=0;
                    }else{
                    	hasUsed=fpjzhm+1-fpjmx.getFpqsh();
                    }
                    fpjmx.setHasUsed(hasUsed);
					al.add(fpjmx);
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
