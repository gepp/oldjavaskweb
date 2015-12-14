alter table SKQ_FPJMX  add DQPH int
alter table SKQ_FPJMX  add FPSYZT varchar(10)
alter table SKQ_FPKJ   add YFPDM varchar(20)
alter table SKQ_FPKJ   add ZKZJE [numeric](18, 2) NULL 
alter table SKQ_JQXX   add CSHSJ [varchar](50)
alter table SKQ_JQXX   add [LXKPSJ] [varchar](50)
alter table SKQ_JQXX   add [LXKPZS] [varchar](50)
alter table SKQ_JQXX   add [XGSMMC] [varchar](50)
alter table SKQ_JQXX   add [LXKPZJE] [int] 
alter table SKQ_JQXX   add [KHDDZ] [varchar](50) 
alter table SKQ_JQXX   add [LXRQ] [varchar](50)  

alter table SKQ_NSRXX  ADD XXID VARCHAR(50)
alter table SKQ_NSRXX  ADD V_PID VARCHAR(100)

CREATE TABLE  [WLKP_JQSD](
	[GUID] [varchar](36) NOT NULL,
	[NSRBM] [varchar](20) NOT NULL,
	[JQBH] [varchar](16) NOT NULL,
	[SDYY] [varchar](100) NULL,
	[SDCZY] [varchar](20) NOT NULL,
	[SDSJ] [datetime] NULL,
	[JSCZY] [varchar](20) NULL,
	[JSSJ] [datetime] NULL
) ON [PRIMARY]

CREATE TABLE [dbo].[WLKP_SWJGXX](
	[GUID] [varchar](36) NOT NULL,
	[XXNR] [varchar](200) NOT NULL,
	[SWJGBM] [varchar](20) NULL,
	[CJSJ] [datetime] NULL,
	[CJZ] [varchar](20) NULL
) ON [PRIMARY]
CREATE TABLE [dbo].[WLKP_TFY](
	[GUID] [varchar](36) NOT NULL,
	[NSRBM] [varchar](20) NOT NULL,
	[JQBH] [varchar](16) NOT NULL,
	[TYSJ] [datetime] NOT NULL,
	[SHYJ] [varchar](100) NULL,
	[TYCZY] [varchar](20) NULL,
	[TYCZSJ] [datetime] NULL,
	[FYSJ] [datetime] NULL,
	[FYCZY] [varchar](20) NULL,
	[FYCZSJ] [datetime] NULL,
	[TYYY] [varchar](200) NULL
) ON [PRIMARY]
CREATE TABLE [dbo].[WLKP_XTJCSZ](
	[FPZS] [int] NULL,
	[PAGESIZE] [int] NULL,
	[LXKPZJE] [decimal](18, 0) NULL,
	[LXKPZS] [int] NULL,
	[LXKPTS] [int] NULL,
	[BDCDZKPXE] [int] NULL,
	[JZAZDZKPXE] [int] NULL,
	[TYDZKPXE] [int] NULL
) ON [PRIMARY]
CREATE TABLE [dbo].[WLKP_ZX](
	[GUID] [varchar](36) NOT NULL,
	[NSRBM] [varchar](20) NOT NULL,
	[JQBH] [varchar](16) NOT NULL,
	[ZXYY] [varchar](100) NULL,
	[SHYJ] [varchar](100) NULL,
	[CLZ] [varchar](20) NULL,
	[CLSJ] [datetime] NULL,
	[ZXSJ] [datetime] NULL
) ON [PRIMARY]
insert into WLKP_XTJCSZ values(200,100000,100000,100000,100000,100000,100000,100000);
 
