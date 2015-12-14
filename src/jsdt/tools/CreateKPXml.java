package jsdt.tools;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jsdt.model.Fpkj;
import jsdt.model.Nsrxx;
import jsdt.model.cxtj.cxFpkj;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;


@SuppressWarnings("unchecked")
public class CreateKPXml {

	public CreateKPXml() {
	}

	//
	public CreateKPXml(List<?> dataList) {
		this.dataList = dataList;
	}

	private List<?> dataList;

	public List<?> getDataList() {
		return dataList;
	}

	public void setDataList(List<?> dataList) {
		this.dataList = dataList;
	}

	/**
	 * 建立一个XML文档,文档名由输入属性决定,文档内容在内部由initialDocument（Document document）进行初始化
	 * 
	 * @param filename
	 *            需建立的文件名,如"test.xml",可以加路径
	 *            
	 * @return返回操作结果, 0表失败, 1表成功
	 */
	//1.正常票 2.退票 3.废票
	public int createXMLFile(String filename,String nsrwjbm,String type) {
		// 返回操作结果, 0表失败, 1表成功
		int returnValue = 0;
		// 建立document对象
		Document document = DocumentHelper.createDocument();
		// 对document初始化，加入xml所需要的内容节点
		if(type.equals("1")){
		document = initialZcpDocument(document,nsrwjbm);
		} 
		else if(type.equals("2")){
			document = initialTpDocument(document,nsrwjbm);
		} 
		else if(type.equals("3")){
			document = initialFpDocument(document,nsrwjbm);
		} 
		try {
			// 对document格式化输出到指定名称文件, 格式化后，使xml符合节点缩进样式
			formatXMLOutput(document, filename);
			// 输出正确，修改返回值为1
			returnValue = 1;
		} catch (Exception e) {
			System.out.println("格式化输出文件出错！");
			e.printStackTrace();
		}
		return returnValue;
	}

	/**
	 * <b>方法描述</b>： 创建xml字符串,文档内容在内部由initialDocument（Document document）进行初始化
	 * <p>
	 * <b>方法流程</b>：
	 * <p>
	 * 
	 * @return
	 */
	public String createZcpXMLStr(String nsrwjbm) {
		// 建立document对象
		Document document = DocumentHelper.createDocument();
		// 对document初始化，加入xml所需要的内容节点
		document = initialZcpDocument(document,nsrwjbm);
		return document.asXML();
	}

	/**
	 * <b>方法描述</b>： 初始化document对象
	 * <p>
	 * <b>方法流程</b>：
	 * <p>
	 * 
	 * @return 返回初始化完成对象
	 */

	private Document initialZcpDocument(Document document,String nsrwjbm) {
		List<Fpkj> items = (List<Fpkj>) this.getDataList();
		Nsrxx nsrxx=new Nsrxx(nsrwjbm);
		/** 建立XML文档的根taxML */
		Element taxMLElement = document.addElement("taxML");
				taxMLElement.addAttribute("xmlns","http://www.chinatax.gov.cn/dataspec/");
				taxMLElement.addAttribute("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");
				taxMLElement.addAttribute("cnName","批量信息－商场自开发票明细信息报文");
				taxMLElement.addAttribute("name","plxxNJSCZKPMxxx");
				taxMLElement.addAttribute("version","SW5001-2011");
		/**加入纳税人信息 */
		Element ZCFPXXElement = taxMLElement.addElement("ZCFPXX");
		Element NsrxxElement  =	ZCFPXXElement.addElement("Nsrxx");
		Element XSDW_MCElement=NsrxxElement.addElement("XSDW_MC");
				XSDW_MCElement.setText(nsrxx.getNsrmc());
				
		Element XSDW_SWDJZHM=NsrxxElement.addElement("XSDW_SWDJZHM");
				XSDW_SWDJZHM.setText(nsrxx.getNsrsbh());
		
		Element XSDW_DZDH=NsrxxElement.addElement("XSDW_DZDH");
				XSDW_DZDH.setText(nsrxx.getJydz());		
		
		Element XSDW_KHYHZH=NsrxxElement.addElement("XSDW_KHYHZH");
				XSDW_KHYHZH.setText("");
		
		/**添加发票信息 */
		/** 加入第一个Invoices节点 */
		Element invoicesElement = ZCFPXXElement.addElement("Fpxx");
		for (Fpkj item : items) {
			/** 加入NJSCZKPZBxx节点 */
			Element NJSCZKPZBxx = invoicesElement.addElement("NJSCZKPZBxx");
			/** 加入FP_DM节点 */
			Element FP_DM = NJSCZKPZBxx.addElement("FP_DM");
					FP_DM.setText(item.getFpdm());
			/** 加入FP_HM节点 */
			Element FP_HM = NJSCZKPZBxx.addElement("FP_HM");
					FP_HM.setText(item.getFphm() + "");//发票号码
			/** 加入DATE_KP节点 */
			Element DATE_KP = NJSCZKPZBxx.addElement("DATE_KP");
					DATE_KP.setText(Util.getZfDate(item.getFphm())); //作废时间
			/** 加入GHDW_MC节点 */
			Element GHDW_MC = NJSCZKPZBxx.addElement("GHDW_MC");
					GHDW_MC.setText(item.getFkdw()); //付款单位
			/** 加入GHDW_SWDJZDM节点 */
			Element GHDW_SWDJZDM = NJSCZKPZBxx.addElement("GHDW_SWDJZDM");
					GHDW_SWDJZDM.setText("无");// 购货单位税务登记代码
			/** 加入GHDW_DZDH节点 */
			Element GHDW_DZDH = NJSCZKPZBxx.addElement("GHDW_DZDH");
					GHDW_DZDH.setText("无");//
			/** 加入GHDW_KHYHZH节点 */
			Element GHDW_KHYHZH = NJSCZKPZBxx.addElement("GHDW_KHYHZH");
			GHDW_KHYHZH.setText("无");//
			/** 加入KP_JE节点 */
			Element KP_JE = NJSCZKPZBxx.addElement("KP_JE");
					KP_JE.setText(item.getHjzje()+"");// 购方税号
			/** 加入KPDZ节点 */
			Element KPDZ = NJSCZKPZBxx.addElement("KPDZ");
					KPDZ.setText(nsrxx.getJydz());//开票地址
			/** 加入KPRY节点 */
			Element KPRY = NJSCZKPZBxx.addElement("KPRY");
					KPRY.setText("01");//收款员
					
			/** 加入KPRY_MC节点 */
			Element KPRY_MC = NJSCZKPZBxx.addElement("KPRY_MC");
					KPRY_MC.setText(item.getSky());// 
			/** 加入KP_JEDX节点 */
			Element KP_JEDX = NJSCZKPZBxx.addElement("KP_JEDX");
					KP_JEDX.setText(Util.digitUppercase(item.getHjzje()) + "");
			/** 加入DYCS节点 */
			Element DYCS = NJSCZKPZBxx.addElement("DYCS");
					DYCS.setText(0+ "");
			/** 加入DYR节点 */
			Element DYR = NJSCZKPZBxx.addElement("DYR");
					DYR.setText(item.getSky());
					
			/** 加入DYSJ节点 */
			Element DYSJ = NJSCZKPZBxx.addElement("DYSJ");
					DYSJ.setText(item.getKprq());
			 
			Element invDetailsElement = invoicesElement.addElement("NJSCZKPMxxx");
			ArrayList<HashMap<String,Object>> fpxmxx=item.getFpxmxx();
			/* 循环商品 */
			for (HashMap info :fpxmxx) {
				/** 加入PM节点 */
				Element PM = invDetailsElement.addElement("PM");
						PM.setText(info.get("smmc")+"");// 
				/** 加入JLDW节点 */
				Element JLDW = invDetailsElement.addElement("JLDW");
						JLDW.setText("升");// 商品名称
				/** 加入SL节点 */
				Element SL = invDetailsElement.addElement("SL");
						SL.setText(info.get("sl")+"");
				/** 加入DJ节点 */
				Element DJ = invDetailsElement.addElement("DJ");
						DJ.setText(info.get("dj")+"");
				/** 加入JE节点 */
				Element JE = invDetailsElement.addElement("JE");
						JE.setText(info.get("je")+"");
				/** 加入BZ节点 */
				Element BZ = invDetailsElement.addElement("BZ");
						BZ.setText("");
				 
			}
		}
		return document;
	}

	
	private Document initialFpDocument(Document document,String nsrwjbm) {
		List<Fpkj> items = (List<Fpkj>) this.getDataList();
		Nsrxx nsrxx=new Nsrxx(nsrwjbm);
		/** 建立XML文档的根taxML */
		Element taxMLElement = document.addElement("taxML");
				taxMLElement.addAttribute("xmlns","http://www.chinatax.gov.cn/dataspec/");
				taxMLElement.addAttribute("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");
				taxMLElement.addAttribute("cnName","批量信息－商场自开发票明细信息作废票报文");
				taxMLElement.addAttribute("name","plxxNJSCZKPMxxxzfp");
				taxMLElement.addAttribute("version","SW5001-2011");
		/**加入纳税人信息 */
		Element ZCFPXXElement = taxMLElement.addElement("ZCFPXX");
		Element NsrxxElement  =	ZCFPXXElement.addElement("Nsrxx");
		Element XSDW_MCElement=NsrxxElement.addElement("XSDW_MC");
				XSDW_MCElement.setText(nsrxx.getNsrmc());
				
		Element XSDW_SWDJZHM=NsrxxElement.addElement("XSDW_SWDJZHM");
				XSDW_SWDJZHM.setText(nsrxx.getNsrsbh());
		
		Element XSDW_DZDH=NsrxxElement.addElement("XSDW_DZDH");
				XSDW_DZDH.setText(nsrxx.getJydz());		
		
		Element XSDW_KHYHZH=NsrxxElement.addElement("XSDW_KHYHZH");
				XSDW_KHYHZH.setText("");
		
		/**添加发票信息 */
		/** 加入第一个Invoices节点 */
		Element invoicesElement = ZCFPXXElement.addElement("Fpxx");
		for (Fpkj item : items) {
			/** 加入NJSCZKPZBxx节点 */
			Element NJSCZKPZBxx = invoicesElement.addElement("NJSCZKPZBxx");
			/** 加入FP_DM节点 */
			Element FP_DM = NJSCZKPZBxx.addElement("FP_DM");
					FP_DM.setText(item.getFpdm());
			/** 加入FP_HM节点 */
			Element FP_HM = NJSCZKPZBxx.addElement("FP_HM");
					FP_HM.setText(item.getFphm() + "");//发票号码
			/** 加入DATE_KP节点 */
			Element DATE_KP = NJSCZKPZBxx.addElement("DATE_KP");
					DATE_KP.setText(item.getKprq()); //作废时间
			 
			/** 加入CZY节点 */
			Element CZY = NJSCZKPZBxx.addElement("CZY");
					CZY.setText("01");//收款员
					
			/** 加入CZY_MC节点 */
			Element CZY_MC = NJSCZKPZBxx.addElement("CZY_MC");
					CZY_MC.setText(item.getSky());// 
			/** 加入CZY_MC节点 */
			Element ZF_YY = NJSCZKPZBxx.addElement("ZF_YY");
					ZF_YY.setText("作废");// 			 
		}
		return document;
	}
	private Document initialTpDocument(Document document,String nsrwjbm) {
		List<Fpkj> items = (List<Fpkj>) this.getDataList();
		Nsrxx nsrxx=new Nsrxx(nsrwjbm);
		/** 建立XML文档的根taxML */
		Element taxMLElement = document.addElement("taxML");
				taxMLElement.addAttribute("xmlns","http://www.chinatax.gov.cn/dataspec/");
				taxMLElement.addAttribute("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");
				taxMLElement.addAttribute("cnName","批量信息－商场自开发票明细信息红字票报文");
				taxMLElement.addAttribute("name","plxxNJSCZKPMxxxhzp");
				taxMLElement.addAttribute("version","SW5001-2011");
		/**加入纳税人信息 */
		Element ZCFPXXElement = taxMLElement.addElement("ZCFPXX");
		Element NsrxxElement  =	ZCFPXXElement.addElement("Nsrxx");
		Element XSDW_MCElement=NsrxxElement.addElement("XSDW_MC");
				XSDW_MCElement.setText(nsrxx.getNsrmc());
				
		Element XSDW_SWDJZHM=NsrxxElement.addElement("XSDW_SWDJZHM");
				XSDW_SWDJZHM.setText(nsrxx.getNsrsbh());
		
		Element XSDW_DZDH=NsrxxElement.addElement("XSDW_DZDH");
				XSDW_DZDH.setText(nsrxx.getJydz());		
		
		Element XSDW_KHYHZH=NsrxxElement.addElement("XSDW_KHYHZH");
				XSDW_KHYHZH.setText("");
		
		/**添加发票信息 */
		/** 加入第一个Invoices节点 */
		Element invoicesElement = ZCFPXXElement.addElement("Fpxx");
		for (Fpkj item : items) {
			/** 加入NJSCZKPZBxx节点 */
			Element NJSCZKPZBxx = invoicesElement.addElement("NJSCZKPZBxx");
			/** 加入FP_DM节点 */
			Element FP_DM = NJSCZKPZBxx.addElement("FP_DM");
					FP_DM.setText(item.getFpdm());
			/** 加入FP_HM节点 */
			Element FP_HM = NJSCZKPZBxx.addElement("FP_HM");
					FP_HM.setText(item.getFphm() + "");//发票号码
			/** 加入DATE_KP节点 */
			Element DATE_KP = NJSCZKPZBxx.addElement("DATE_KP");
					DATE_KP.setText(Util.getZfDate(item.getFphm())); //作废时间
			/** 加入GHDW_MC节点 */
			Element GHDW_MC = NJSCZKPZBxx.addElement("GHDW_MC");
					GHDW_MC.setText(item.getFkdw()); //付款单位
			/** 加入GHDW_SWDJZDM节点 */
			Element GHDW_SWDJZDM = NJSCZKPZBxx.addElement("GHDW_SWDJZDM");
					GHDW_SWDJZDM.setText("无");// 购货单位税务登记代码
			/** 加入GHDW_DZDH节点 */
			Element GHDW_DZDH = NJSCZKPZBxx.addElement("GHDW_DZDH");
					GHDW_DZDH.setText("无");//
			/** 加入GHDW_KHYHZH节点 */
			Element GHDW_KHYHZH = NJSCZKPZBxx.addElement("GHDW_KHYHZH");
			GHDW_KHYHZH.setText("无");//
			/** 加入KP_JE节点 */
			Element KP_JE = NJSCZKPZBxx.addElement("KP_JE");
					KP_JE.setText(item.getHjzje()+"");// 购方税号
			/** 加入KPDZ节点 */
			Element KPDZ = NJSCZKPZBxx.addElement("KPDZ");
					KPDZ.setText(nsrxx.getJydz());//开票地址
			/** 加入KPRY节点 */
			Element KPRY = NJSCZKPZBxx.addElement("KPRY");
					KPRY.setText("01");//收款员
					
			/** 加入KPRY_MC节点 */
			Element KPRY_MC = NJSCZKPZBxx.addElement("KPRY_MC");
					KPRY_MC.setText(item.getSky());// 
					
			/** 加入YL_FP_DM节点 */
			Element YL_FP_DM = NJSCZKPZBxx.addElement("YL_FP_DM");
					YL_FP_DM.setText(item.getFpdm());// 
			/** 加入YL_FP_DM节点 */
			Element YL_FP_HM = NJSCZKPZBxx.addElement("YL_FP_HM");
					YL_FP_HM.setText(item.getYfphm()+"");// 
			/** 加入KP_JEDX节点 */
			Element KP_JEDX = NJSCZKPZBxx.addElement("KP_JEDX");
					KP_JEDX.setText(Util.digitUppercase(item.getHjzje()) + "");
			/** 加入DYCS节点 */
			Element DYCS = NJSCZKPZBxx.addElement("DYCS");
					DYCS.setText(0+ "");
			/** 加入DYR节点 */
			Element DYR = NJSCZKPZBxx.addElement("DYR");
					DYR.setText(item.getSky());
					
			/** 加入DYSJ节点 */
			Element DYSJ = NJSCZKPZBxx.addElement("DYSJ");
					DYSJ.setText(item.getKprq());
			 
			Element invDetailsElement = invoicesElement.addElement("NJSCZKPMxxx");
			ArrayList<HashMap<String,Object>> fpxmxx=item.getFpxmxx();
			/* 循环商品 */
			for (HashMap info :fpxmxx) {
				/** 加入PM节点 */
				Element PM = invDetailsElement.addElement("PM");
						PM.setText(info.get("smmc")+"");// 
				/** 加入JLDW节点 */
				Element JLDW = invDetailsElement.addElement("JLDW");
						JLDW.setText("升");// 商品名称
				/** 加入SL节点 */
				Element SL = invDetailsElement.addElement("SL");
						SL.setText(info.get("sl")+"");
				/** 加入DJ节点 */
				Element DJ = invDetailsElement.addElement("DJ");
						DJ.setText(info.get("dj")+"");
				/** 加入JE节点 */
				Element JE = invDetailsElement.addElement("JE");
						JE.setText(info.get("je")+"");
				/** 加入BZ节点 */
				Element BZ = invDetailsElement.addElement("BZ");
						BZ.setText("");
				 
			}
		}
		return document;
	}
 
	private void formatXMLOutput(Document document, String filename)
			throws Exception {
		OutputFormat format = OutputFormat.createPrettyPrint();
		// 默认为Utf-8 编码，可以根据需要改变编码格式
		format.setEncoding("GBK");
		/** 将document中的内容写入文件中 */
		XMLWriter writer = new XMLWriter(new FileOutputStream(
				new File(filename)), format);
		writer.write(document);
		writer.close();
	}
	public static void main(String[] args) {
		CreateKPXml kpxml=new CreateKPXml();
		cxFpkj cx = new cxFpkj();
		String resultStr=" and a.nsrwjbm='0000000055580070'";
		ArrayList<Fpkj> fpkjList= cx.selectFpkjDcXml(resultStr);
		kpxml.setDataList(fpkjList);
		kpxml.createXMLFile("D:/taxML_NJSCZKP_320103721774778_00000001_20130805_000000000000_00000000_01.xml","0000000055580070","1");
	}

}
