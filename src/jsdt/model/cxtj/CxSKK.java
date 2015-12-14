package jsdt.model.cxtj;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import jsdt.tools.DBConnection;


public class CxSKK {

	public HashMap querySKKByMachine(String machineno)
	{
		HashMap hm = new HashMap();

		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String queryInfo = "select a.NSRWJBM,a.NSRSBH,a.NSRMC,a.SWJGBM,b.SKKH,b.KQYRQ,b.KYXRQ,b.SBFS,b.MXSBBZ,b.KPJZRQ,b.DZKPXE,b.YLJKPXE,b.YLJTPXE from SKQ_NSRXX a,SKQ_JQXX b where b.JQBH=? and a.NSRWJBM=b.NSRWJBM and a.STATUS=1 and b.STATUS=1";
				PreparedStatement ps = conn.prepareStatement(queryInfo);
				ps.setString(1,machineno);
				ResultSet rs = ps.executeQuery();
				
				while(rs.next())
				{
					hm.put("nsrwjbm", rs.getString(1));
					hm.put("nsrsbh", rs.getString(2));
					hm.put("nsrmc", rs.getString(3));
					hm.put("swjgbm", rs.getString(4));
					hm.put("skkh", rs.getString(5));
					hm.put("kqyrq", new SimpleDateFormat("yyyyMMdd").format(rs.getDate(6)));
					hm.put("kyxrq", new SimpleDateFormat("yyyyMMdd").format(rs.getDate(7)));
					hm.put("sbfs", rs.getString(8));
					hm.put("mxsbbz", rs.getString(9));
					hm.put("kpjzrq", new SimpleDateFormat("yyyyMMdd").format(rs.getDate(10)));
					DecimalFormat dformat = new DecimalFormat("0.00");
					hm.put("dzkpxe", dformat.format(rs.getDouble(11)));
					hm.put("yljkpxe", dformat.format(rs.getDouble(12)));
					hm.put("yljtpxe", dformat.format(rs.getDouble(13)));
				}
				
				rs.close();
				ps.close();
				
				String querySZ = "select DISTINCT a.SMSY from SKQ_PMSZ a,SKQ_JQSZSM b where b.JQBH=? and b.SMBM=a.SMBM";
				ps = conn.prepareStatement(querySZ);
				ps.setString(1, machineno);
				rs = ps.executeQuery();
				
				StringBuffer sb = new StringBuffer();
				while(rs.next())
				{
					String sy = rs.getString(1);
					if(sy.length()<2)
					{
						sy = "0"+sy;
					}
					sb.append(sy);
				}
				if(hm!=null&&!hm.isEmpty())
				{
					hm.put("szsmsy", sb.toString());
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
		
		return hm;
	}
}
