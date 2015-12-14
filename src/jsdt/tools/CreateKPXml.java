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
	 * ����һ��XML�ĵ�,�ĵ������������Ծ���,�ĵ��������ڲ���initialDocument��Document document�����г�ʼ��
	 * 
	 * @param filename
	 *            �轨�����ļ���,��"test.xml",���Լ�·��
	 *            
	 * @return���ز������, 0��ʧ��, 1��ɹ�
	 */
	//1.����Ʊ 2.��Ʊ 3.��Ʊ
	public int createXMLFile(String filename,String nsrwjbm,String type) {
		// ���ز������, 0��ʧ��, 1��ɹ�
		int returnValue = 0;
		// ����document����
		Document document = DocumentHelper.createDocument();
		// ��document��ʼ��������xml����Ҫ�����ݽڵ�
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
			// ��document��ʽ�������ָ�������ļ�, ��ʽ����ʹxml���Ͻڵ�������ʽ
			formatXMLOutput(document, filename);
			// �����ȷ���޸ķ���ֵΪ1
			returnValue = 1;
		} catch (Exception e) {
			System.out.println("��ʽ������ļ�����");
			e.printStackTrace();
		}
		return returnValue;
	}

	/**
	 * <b>��������</b>�� ����xml�ַ���,�ĵ��������ڲ���initialDocument��Document document�����г�ʼ��
	 * <p>
	 * <b>��������</b>��
	 * <p>
	 * 
	 * @return
	 */
	public String createZcpXMLStr(String nsrwjbm) {
		// ����document����
		Document document = DocumentHelper.createDocument();
		// ��document��ʼ��������xml����Ҫ�����ݽڵ�
		document = initialZcpDocument(document,nsrwjbm);
		return document.asXML();
	}

	/**
	 * <b>��������</b>�� ��ʼ��document����
	 * <p>
	 * <b>��������</b>��
	 * <p>
	 * 
	 * @return ���س�ʼ����ɶ���
	 */

	private Document initialZcpDocument(Document document,String nsrwjbm) {
		List<Fpkj> items = (List<Fpkj>) this.getDataList();
		Nsrxx nsrxx=new Nsrxx(nsrwjbm);
		/** ����XML�ĵ��ĸ�taxML */
		Element taxMLElement = document.addElement("taxML");
				taxMLElement.addAttribute("xmlns","http://www.chinatax.gov.cn/dataspec/");
				taxMLElement.addAttribute("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");
				taxMLElement.addAttribute("cnName","������Ϣ���̳��Կ���Ʊ��ϸ��Ϣ����");
				taxMLElement.addAttribute("name","plxxNJSCZKPMxxx");
				taxMLElement.addAttribute("version","SW5001-2011");
		/**������˰����Ϣ */
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
		
		/**��ӷ�Ʊ��Ϣ */
		/** �����һ��Invoices�ڵ� */
		Element invoicesElement = ZCFPXXElement.addElement("Fpxx");
		for (Fpkj item : items) {
			/** ����NJSCZKPZBxx�ڵ� */
			Element NJSCZKPZBxx = invoicesElement.addElement("NJSCZKPZBxx");
			/** ����FP_DM�ڵ� */
			Element FP_DM = NJSCZKPZBxx.addElement("FP_DM");
					FP_DM.setText(item.getFpdm());
			/** ����FP_HM�ڵ� */
			Element FP_HM = NJSCZKPZBxx.addElement("FP_HM");
					FP_HM.setText(item.getFphm() + "");//��Ʊ����
			/** ����DATE_KP�ڵ� */
			Element DATE_KP = NJSCZKPZBxx.addElement("DATE_KP");
					DATE_KP.setText(Util.getZfDate(item.getFphm())); //����ʱ��
			/** ����GHDW_MC�ڵ� */
			Element GHDW_MC = NJSCZKPZBxx.addElement("GHDW_MC");
					GHDW_MC.setText(item.getFkdw()); //���λ
			/** ����GHDW_SWDJZDM�ڵ� */
			Element GHDW_SWDJZDM = NJSCZKPZBxx.addElement("GHDW_SWDJZDM");
					GHDW_SWDJZDM.setText("��");// ������λ˰��ǼǴ���
			/** ����GHDW_DZDH�ڵ� */
			Element GHDW_DZDH = NJSCZKPZBxx.addElement("GHDW_DZDH");
					GHDW_DZDH.setText("��");//
			/** ����GHDW_KHYHZH�ڵ� */
			Element GHDW_KHYHZH = NJSCZKPZBxx.addElement("GHDW_KHYHZH");
			GHDW_KHYHZH.setText("��");//
			/** ����KP_JE�ڵ� */
			Element KP_JE = NJSCZKPZBxx.addElement("KP_JE");
					KP_JE.setText(item.getHjzje()+"");// ����˰��
			/** ����KPDZ�ڵ� */
			Element KPDZ = NJSCZKPZBxx.addElement("KPDZ");
					KPDZ.setText(nsrxx.getJydz());//��Ʊ��ַ
			/** ����KPRY�ڵ� */
			Element KPRY = NJSCZKPZBxx.addElement("KPRY");
					KPRY.setText("01");//�տ�Ա
					
			/** ����KPRY_MC�ڵ� */
			Element KPRY_MC = NJSCZKPZBxx.addElement("KPRY_MC");
					KPRY_MC.setText(item.getSky());// 
			/** ����KP_JEDX�ڵ� */
			Element KP_JEDX = NJSCZKPZBxx.addElement("KP_JEDX");
					KP_JEDX.setText(Util.digitUppercase(item.getHjzje()) + "");
			/** ����DYCS�ڵ� */
			Element DYCS = NJSCZKPZBxx.addElement("DYCS");
					DYCS.setText(0+ "");
			/** ����DYR�ڵ� */
			Element DYR = NJSCZKPZBxx.addElement("DYR");
					DYR.setText(item.getSky());
					
			/** ����DYSJ�ڵ� */
			Element DYSJ = NJSCZKPZBxx.addElement("DYSJ");
					DYSJ.setText(item.getKprq());
			 
			Element invDetailsElement = invoicesElement.addElement("NJSCZKPMxxx");
			ArrayList<HashMap<String,Object>> fpxmxx=item.getFpxmxx();
			/* ѭ����Ʒ */
			for (HashMap info :fpxmxx) {
				/** ����PM�ڵ� */
				Element PM = invDetailsElement.addElement("PM");
						PM.setText(info.get("smmc")+"");// 
				/** ����JLDW�ڵ� */
				Element JLDW = invDetailsElement.addElement("JLDW");
						JLDW.setText("��");// ��Ʒ����
				/** ����SL�ڵ� */
				Element SL = invDetailsElement.addElement("SL");
						SL.setText(info.get("sl")+"");
				/** ����DJ�ڵ� */
				Element DJ = invDetailsElement.addElement("DJ");
						DJ.setText(info.get("dj")+"");
				/** ����JE�ڵ� */
				Element JE = invDetailsElement.addElement("JE");
						JE.setText(info.get("je")+"");
				/** ����BZ�ڵ� */
				Element BZ = invDetailsElement.addElement("BZ");
						BZ.setText("");
				 
			}
		}
		return document;
	}

	
	private Document initialFpDocument(Document document,String nsrwjbm) {
		List<Fpkj> items = (List<Fpkj>) this.getDataList();
		Nsrxx nsrxx=new Nsrxx(nsrwjbm);
		/** ����XML�ĵ��ĸ�taxML */
		Element taxMLElement = document.addElement("taxML");
				taxMLElement.addAttribute("xmlns","http://www.chinatax.gov.cn/dataspec/");
				taxMLElement.addAttribute("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");
				taxMLElement.addAttribute("cnName","������Ϣ���̳��Կ���Ʊ��ϸ��Ϣ����Ʊ����");
				taxMLElement.addAttribute("name","plxxNJSCZKPMxxxzfp");
				taxMLElement.addAttribute("version","SW5001-2011");
		/**������˰����Ϣ */
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
		
		/**��ӷ�Ʊ��Ϣ */
		/** �����һ��Invoices�ڵ� */
		Element invoicesElement = ZCFPXXElement.addElement("Fpxx");
		for (Fpkj item : items) {
			/** ����NJSCZKPZBxx�ڵ� */
			Element NJSCZKPZBxx = invoicesElement.addElement("NJSCZKPZBxx");
			/** ����FP_DM�ڵ� */
			Element FP_DM = NJSCZKPZBxx.addElement("FP_DM");
					FP_DM.setText(item.getFpdm());
			/** ����FP_HM�ڵ� */
			Element FP_HM = NJSCZKPZBxx.addElement("FP_HM");
					FP_HM.setText(item.getFphm() + "");//��Ʊ����
			/** ����DATE_KP�ڵ� */
			Element DATE_KP = NJSCZKPZBxx.addElement("DATE_KP");
					DATE_KP.setText(item.getKprq()); //����ʱ��
			 
			/** ����CZY�ڵ� */
			Element CZY = NJSCZKPZBxx.addElement("CZY");
					CZY.setText("01");//�տ�Ա
					
			/** ����CZY_MC�ڵ� */
			Element CZY_MC = NJSCZKPZBxx.addElement("CZY_MC");
					CZY_MC.setText(item.getSky());// 
			/** ����CZY_MC�ڵ� */
			Element ZF_YY = NJSCZKPZBxx.addElement("ZF_YY");
					ZF_YY.setText("����");// 			 
		}
		return document;
	}
	private Document initialTpDocument(Document document,String nsrwjbm) {
		List<Fpkj> items = (List<Fpkj>) this.getDataList();
		Nsrxx nsrxx=new Nsrxx(nsrwjbm);
		/** ����XML�ĵ��ĸ�taxML */
		Element taxMLElement = document.addElement("taxML");
				taxMLElement.addAttribute("xmlns","http://www.chinatax.gov.cn/dataspec/");
				taxMLElement.addAttribute("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");
				taxMLElement.addAttribute("cnName","������Ϣ���̳��Կ���Ʊ��ϸ��Ϣ����Ʊ����");
				taxMLElement.addAttribute("name","plxxNJSCZKPMxxxhzp");
				taxMLElement.addAttribute("version","SW5001-2011");
		/**������˰����Ϣ */
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
		
		/**��ӷ�Ʊ��Ϣ */
		/** �����һ��Invoices�ڵ� */
		Element invoicesElement = ZCFPXXElement.addElement("Fpxx");
		for (Fpkj item : items) {
			/** ����NJSCZKPZBxx�ڵ� */
			Element NJSCZKPZBxx = invoicesElement.addElement("NJSCZKPZBxx");
			/** ����FP_DM�ڵ� */
			Element FP_DM = NJSCZKPZBxx.addElement("FP_DM");
					FP_DM.setText(item.getFpdm());
			/** ����FP_HM�ڵ� */
			Element FP_HM = NJSCZKPZBxx.addElement("FP_HM");
					FP_HM.setText(item.getFphm() + "");//��Ʊ����
			/** ����DATE_KP�ڵ� */
			Element DATE_KP = NJSCZKPZBxx.addElement("DATE_KP");
					DATE_KP.setText(Util.getZfDate(item.getFphm())); //����ʱ��
			/** ����GHDW_MC�ڵ� */
			Element GHDW_MC = NJSCZKPZBxx.addElement("GHDW_MC");
					GHDW_MC.setText(item.getFkdw()); //���λ
			/** ����GHDW_SWDJZDM�ڵ� */
			Element GHDW_SWDJZDM = NJSCZKPZBxx.addElement("GHDW_SWDJZDM");
					GHDW_SWDJZDM.setText("��");// ������λ˰��ǼǴ���
			/** ����GHDW_DZDH�ڵ� */
			Element GHDW_DZDH = NJSCZKPZBxx.addElement("GHDW_DZDH");
					GHDW_DZDH.setText("��");//
			/** ����GHDW_KHYHZH�ڵ� */
			Element GHDW_KHYHZH = NJSCZKPZBxx.addElement("GHDW_KHYHZH");
			GHDW_KHYHZH.setText("��");//
			/** ����KP_JE�ڵ� */
			Element KP_JE = NJSCZKPZBxx.addElement("KP_JE");
					KP_JE.setText(item.getHjzje()+"");// ����˰��
			/** ����KPDZ�ڵ� */
			Element KPDZ = NJSCZKPZBxx.addElement("KPDZ");
					KPDZ.setText(nsrxx.getJydz());//��Ʊ��ַ
			/** ����KPRY�ڵ� */
			Element KPRY = NJSCZKPZBxx.addElement("KPRY");
					KPRY.setText("01");//�տ�Ա
					
			/** ����KPRY_MC�ڵ� */
			Element KPRY_MC = NJSCZKPZBxx.addElement("KPRY_MC");
					KPRY_MC.setText(item.getSky());// 
					
			/** ����YL_FP_DM�ڵ� */
			Element YL_FP_DM = NJSCZKPZBxx.addElement("YL_FP_DM");
					YL_FP_DM.setText(item.getFpdm());// 
			/** ����YL_FP_DM�ڵ� */
			Element YL_FP_HM = NJSCZKPZBxx.addElement("YL_FP_HM");
					YL_FP_HM.setText(item.getYfphm()+"");// 
			/** ����KP_JEDX�ڵ� */
			Element KP_JEDX = NJSCZKPZBxx.addElement("KP_JEDX");
					KP_JEDX.setText(Util.digitUppercase(item.getHjzje()) + "");
			/** ����DYCS�ڵ� */
			Element DYCS = NJSCZKPZBxx.addElement("DYCS");
					DYCS.setText(0+ "");
			/** ����DYR�ڵ� */
			Element DYR = NJSCZKPZBxx.addElement("DYR");
					DYR.setText(item.getSky());
					
			/** ����DYSJ�ڵ� */
			Element DYSJ = NJSCZKPZBxx.addElement("DYSJ");
					DYSJ.setText(item.getKprq());
			 
			Element invDetailsElement = invoicesElement.addElement("NJSCZKPMxxx");
			ArrayList<HashMap<String,Object>> fpxmxx=item.getFpxmxx();
			/* ѭ����Ʒ */
			for (HashMap info :fpxmxx) {
				/** ����PM�ڵ� */
				Element PM = invDetailsElement.addElement("PM");
						PM.setText(info.get("smmc")+"");// 
				/** ����JLDW�ڵ� */
				Element JLDW = invDetailsElement.addElement("JLDW");
						JLDW.setText("��");// ��Ʒ����
				/** ����SL�ڵ� */
				Element SL = invDetailsElement.addElement("SL");
						SL.setText(info.get("sl")+"");
				/** ����DJ�ڵ� */
				Element DJ = invDetailsElement.addElement("DJ");
						DJ.setText(info.get("dj")+"");
				/** ����JE�ڵ� */
				Element JE = invDetailsElement.addElement("JE");
						JE.setText(info.get("je")+"");
				/** ����BZ�ڵ� */
				Element BZ = invDetailsElement.addElement("BZ");
						BZ.setText("");
				 
			}
		}
		return document;
	}
 
	private void formatXMLOutput(Document document, String filename)
			throws Exception {
		OutputFormat format = OutputFormat.createPrettyPrint();
		// Ĭ��ΪUtf-8 ���룬���Ը�����Ҫ�ı�����ʽ
		format.setEncoding("GBK");
		/** ��document�е�����д���ļ��� */
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
