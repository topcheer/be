/*
Navicat PGSQL Data Transfer

Source Server         : external_edu
Source Server Version : 80409
Source Host           : localhost:55555
Source Database       : edu
Source Schema         : edu

Target Server Type    : PGSQL
Target Server Version : 80409
File Encoding         : 65001

Date: 2012-02-02 14:29:06
*/


-- ----------------------------
-- Table structure for "edu"."ag_return_amount"
-- ----------------------------
DROP TABLE "edu"."ag_return_amount";
CREATE TABLE "edu"."ag_return_amount" (
"agent_id" numeric(5) NOT NULL,
"batch_id" numeric(5) NOT NULL,
"ag_return_id" numeric(5) NOT NULL,
"sum_amount" numeric(10,2)
)
WITH (OIDS=FALSE)

;
COMMENT ON TABLE "edu"."ag_return_amount" IS '招生点已收费及返利统计';
COMMENT ON COLUMN "edu"."ag_return_amount"."agent_id" IS '招生点代码';
COMMENT ON COLUMN "edu"."ag_return_amount"."batch_id" IS '批次代码';
COMMENT ON COLUMN "edu"."ag_return_amount"."ag_return_id" IS '返率代码';
COMMENT ON COLUMN "edu"."ag_return_amount"."sum_amount" IS '已收总额';

-- ----------------------------
-- Records of ag_return_amount
-- ----------------------------

-- ----------------------------
-- Table structure for "edu"."agent_relation"
-- ----------------------------
DROP TABLE "edu"."agent_relation";
CREATE TABLE "edu"."agent_relation" (
"agent_id" numeric(5) NOT NULL,
"refer_agent" numeric(5) NOT NULL,
"register_date" timestamp(6) DEFAULT now() NOT NULL
)
WITH (OIDS=FALSE)

;
COMMENT ON TABLE "edu"."agent_relation" IS '招生点隶属关系';
COMMENT ON COLUMN "edu"."agent_relation"."agent_id" IS '招生点代码';
COMMENT ON COLUMN "edu"."agent_relation"."refer_agent" IS '隶属机构';
COMMENT ON COLUMN "edu"."agent_relation"."register_date" IS '登记时间';

-- ----------------------------
-- Records of agent_relation
-- ----------------------------

-- ----------------------------
-- Table structure for "edu"."agent_return"
-- ----------------------------
DROP TABLE "edu"."agent_return";
CREATE TABLE "edu"."agent_return" (
"ag_return_id" numeric(5) DEFAULT fun_table_seq('agent_return'::character varying, 'ag_return_id'::character varying, 'next'::character varying) NOT NULL,
"agent_id" numeric(5) NOT NULL,
"college_id" numeric(5),
"batch_id" numeric(5),
"head_count" numeric(10) NOT NULL,
"return_percent" numeric(3,2) NOT NULL
)
WITH (OIDS=FALSE)

;
COMMENT ON TABLE "edu"."agent_return" IS '招生点返利维护';
COMMENT ON COLUMN "edu"."agent_return"."ag_return_id" IS '协议返利代码';
COMMENT ON COLUMN "edu"."agent_return"."agent_id" IS '招生点';
COMMENT ON COLUMN "edu"."agent_return"."college_id" IS '学校';
COMMENT ON COLUMN "edu"."agent_return"."batch_id" IS '批次';
COMMENT ON COLUMN "edu"."agent_return"."head_count" IS '协议返利名称';
COMMENT ON COLUMN "edu"."agent_return"."return_percent" IS '返率';

-- ----------------------------
-- Records of agent_return
-- ----------------------------

-- ----------------------------
-- Table structure for "edu"."agent_type"
-- ----------------------------
DROP TABLE "edu"."agent_type";
CREATE TABLE "edu"."agent_type" (
"agent_type_id" numeric(5) DEFAULT fun_table_seq('agree_type'::character varying, 'agree_type_id'::character varying, 'next'::character varying) NOT NULL,
"agent_type_name" varchar(255) NOT NULL
)
WITH (OIDS=FALSE)

;
COMMENT ON TABLE "edu"."agent_type" IS '招生点类型';
COMMENT ON COLUMN "edu"."agent_type"."agent_type_id" IS '招生点类型代码';
COMMENT ON COLUMN "edu"."agent_type"."agent_type_name" IS '招生点类型名称';

-- ----------------------------
-- Records of agent_type
-- ----------------------------

-- ----------------------------
-- Table structure for "edu"."agree_return"
-- ----------------------------
DROP TABLE "edu"."agree_return";
CREATE TABLE "edu"."agree_return" (
"agree_return_id" numeric(5) DEFAULT fun_table_seq('agree_return'::character varying, 'agree_return_id'::character varying, 'next'::character varying) NOT NULL,
"agree_return_name" varchar(255) NOT NULL,
"register_date" timestamp(6) DEFAULT now() NOT NULL,
"comment" text,
"agree_copy" bytea
)
WITH (OIDS=FALSE)

;
COMMENT ON TABLE "edu"."agree_return" IS '招生点协议及返利系数管理';
COMMENT ON COLUMN "edu"."agree_return"."agree_return_id" IS '协议返利代码';
COMMENT ON COLUMN "edu"."agree_return"."agree_return_name" IS '返利名称';
COMMENT ON COLUMN "edu"."agree_return"."register_date" IS '录入时间';
COMMENT ON COLUMN "edu"."agree_return"."comment" IS '说明';
COMMENT ON COLUMN "edu"."agree_return"."agree_copy" IS '协议附件';

-- ----------------------------
-- Records of agree_return
-- ----------------------------

-- ----------------------------
-- Table structure for "edu"."batch_index"
-- ----------------------------
DROP TABLE "edu"."batch_index";
CREATE TABLE "edu"."batch_index" (
"batch_id" numeric(5) DEFAULT fun_table_seq('batch_index'::character varying, 'batch_id'::character varying, 'next'::character varying) NOT NULL,
"batch_name" varchar(255) NOT NULL,
"register_date" timestamp(6) DEFAULT now() NOT NULL
)
WITH (OIDS=FALSE)

;
COMMENT ON TABLE "edu"."batch_index" IS '批次代码维护';
COMMENT ON COLUMN "edu"."batch_index"."batch_id" IS '批次代码';
COMMENT ON COLUMN "edu"."batch_index"."batch_name" IS '批次名称';
COMMENT ON COLUMN "edu"."batch_index"."register_date" IS '登记时间';

-- ----------------------------
-- Records of batch_index
-- ----------------------------
INSERT INTO "edu"."batch_index" VALUES ('1', '2012春', '2012-02-01 16:51:07.331109');
INSERT INTO "edu"."batch_index" VALUES ('2', '2012秋', '2012-02-01 16:51:24.222614');

-- ----------------------------
-- Table structure for "edu"."charge_admin"
-- ----------------------------
DROP TABLE "edu"."charge_admin";
CREATE TABLE "edu"."charge_admin" (
"student_id" numeric(5) DEFAULT fun_table_seq('charge_admin'::character varying, 'student_id'::character varying, 'next'::character varying) NOT NULL,
"fee_need" numeric(10) NOT NULL,
"derate_id" numeric(5),
"charge_fee_paid" numeric(10,2),
"refund_id" numeric(5),
"register_date" timestamp(6) DEFAULT now() NOT NULL,
"update_date" timestamp(6)
)
WITH (OIDS=FALSE)

;
COMMENT ON TABLE "edu"."charge_admin" IS '收费管理';
COMMENT ON COLUMN "edu"."charge_admin"."student_id" IS '学生代码';
COMMENT ON COLUMN "edu"."charge_admin"."fee_need" IS '应收费用';
COMMENT ON COLUMN "edu"."charge_admin"."derate_id" IS '减免代码';
COMMENT ON COLUMN "edu"."charge_admin"."charge_fee_paid" IS '已交费用';
COMMENT ON COLUMN "edu"."charge_admin"."refund_id" IS '退费序号';
COMMENT ON COLUMN "edu"."charge_admin"."register_date" IS '登记时间';
COMMENT ON COLUMN "edu"."charge_admin"."update_date" IS '更改时间';

-- ----------------------------
-- Records of charge_admin
-- ----------------------------

-- ----------------------------
-- Table structure for "edu"."charge_type"
-- ----------------------------
DROP TABLE "edu"."charge_type";
CREATE TABLE "edu"."charge_type" (
"charge_type_id" numeric(5) DEFAULT fun_table_seq('charge_type'::character varying, 'charge_type_id'::character varying, 'next'::character varying) NOT NULL,
"charge_type_name" varchar(255) NOT NULL
)
WITH (OIDS=FALSE)

;
COMMENT ON TABLE "edu"."charge_type" IS '收费类型';
COMMENT ON COLUMN "edu"."charge_type"."charge_type_id" IS '收费类型代码';
COMMENT ON COLUMN "edu"."charge_type"."charge_type_name" IS '收费类型名称';

-- ----------------------------
-- Records of charge_type
-- ----------------------------

-- ----------------------------
-- Table structure for "edu"."co_college"
-- ----------------------------
DROP TABLE "edu"."co_college";
CREATE TABLE "edu"."co_college" (
"college_id" numeric(5) DEFAULT fun_table_seq('co_college'::character varying, 'college_id'::character varying, 'next'::character varying) NOT NULL,
"college_name" varchar(255) NOT NULL,
"register_date" timestamp(6) DEFAULT now() NOT NULL
)
WITH (OIDS=FALSE)

;
COMMENT ON TABLE "edu"."co_college" IS '合作高校代码维护';
COMMENT ON COLUMN "edu"."co_college"."college_id" IS '学校代码';
COMMENT ON COLUMN "edu"."co_college"."college_name" IS '学校名称';
COMMENT ON COLUMN "edu"."co_college"."register_date" IS '登记时间';

-- ----------------------------
-- Records of co_college
-- ----------------------------

-- ----------------------------
-- Table structure for "edu"."college_agreement"
-- ----------------------------
DROP TABLE "edu"."college_agreement";
CREATE TABLE "edu"."college_agreement" (
"agreement_id" numeric(5) DEFAULT fun_table_seq('college_agreement'::character varying, 'agreement_id'::character varying, 'next'::character varying) NOT NULL,
"college_id" numeric(5) NOT NULL,
"agent_id" numeric(5) NOT NULL,
"agreement" bytea,
"agreement_status" bool NOT NULL,
"user_id" numeric(5) NOT NULL,
"register_date" timestamp(6) DEFAULT now() NOT NULL,
"update_date" timestamp(6) NOT NULL
)
WITH (OIDS=FALSE)

;
COMMENT ON TABLE "edu"."college_agreement" IS '合作高校协议';
COMMENT ON COLUMN "edu"."college_agreement"."agreement_id" IS '协议代码';
COMMENT ON COLUMN "edu"."college_agreement"."college_id" IS '学校代码';
COMMENT ON COLUMN "edu"."college_agreement"."agent_id" IS '我方学校名称';
COMMENT ON COLUMN "edu"."college_agreement"."agreement" IS '协议内容';
COMMENT ON COLUMN "edu"."college_agreement"."agreement_status" IS '协议状态';
COMMENT ON COLUMN "edu"."college_agreement"."user_id" IS '处理人';
COMMENT ON COLUMN "edu"."college_agreement"."register_date" IS '登记时间';
COMMENT ON COLUMN "edu"."college_agreement"."update_date" IS '更改时间';

-- ----------------------------
-- Records of college_agreement
-- ----------------------------

-- ----------------------------
-- Table structure for "edu"."college_subject"
-- ----------------------------
DROP TABLE "edu"."college_subject";
CREATE TABLE "edu"."college_subject" (
"college_id" numeric(5) NOT NULL,
"batch_id" numeric(5) NOT NULL,
"classified_id" numeric(5) NOT NULL,
"subeject_id" numeric(5) NOT NULL,
"register_date" timestamp(6) DEFAULT now() NOT NULL
)
WITH (OIDS=FALSE)

;
COMMENT ON TABLE "edu"."college_subject" IS '高校专业管理';
COMMENT ON COLUMN "edu"."college_subject"."college_id" IS '学校代码';
COMMENT ON COLUMN "edu"."college_subject"."batch_id" IS '批次代码';
COMMENT ON COLUMN "edu"."college_subject"."classified_id" IS '类别代码';
COMMENT ON COLUMN "edu"."college_subject"."subeject_id" IS '专业代码';
COMMENT ON COLUMN "edu"."college_subject"."register_date" IS '登记时间';

-- ----------------------------
-- Records of college_subject
-- ----------------------------

-- ----------------------------
-- Table structure for "edu"."derate_request"
-- ----------------------------
DROP TABLE "edu"."derate_request";
CREATE TABLE "edu"."derate_request" (
"derate_id" numeric(5) DEFAULT fun_table_seq('derate_request'::character varying, 'derate_id'::character varying, 'next'::character varying) NOT NULL,
"student_id" numeric(5) NOT NULL,
"derate_fee" numeric(10,2) NOT NULL,
"derate_content" varchar(255),
"attachment" bytea,
"approve" bool,
"approve_by" varchar(255),
"user_id" numeric(5) NOT NULL,
"register_date" timestamp(6) DEFAULT now() NOT NULL,
"update_date" timestamp(6)
)
WITH (OIDS=FALSE)

;
COMMENT ON TABLE "edu"."derate_request" IS '学生费用减免申请';
COMMENT ON COLUMN "edu"."derate_request"."derate_id" IS '减免代码';
COMMENT ON COLUMN "edu"."derate_request"."student_id" IS '学生代码';
COMMENT ON COLUMN "edu"."derate_request"."derate_fee" IS '减免费用';
COMMENT ON COLUMN "edu"."derate_request"."derate_content" IS '减免内容';
COMMENT ON COLUMN "edu"."derate_request"."attachment" IS '附件';
COMMENT ON COLUMN "edu"."derate_request"."approve" IS '是否批准';
COMMENT ON COLUMN "edu"."derate_request"."approve_by" IS '批准人';
COMMENT ON COLUMN "edu"."derate_request"."user_id" IS '处理人';
COMMENT ON COLUMN "edu"."derate_request"."register_date" IS '登记时间';
COMMENT ON COLUMN "edu"."derate_request"."update_date" IS '更改时间';

-- ----------------------------
-- Records of derate_request
-- ----------------------------

-- ----------------------------
-- Table structure for "edu"."entrance_cost"
-- ----------------------------
DROP TABLE "edu"."entrance_cost";
CREATE TABLE "edu"."entrance_cost" (
"college_id" numeric(5) NOT NULL,
"batch_id" numeric(5) NOT NULL,
"agent_id" numeric(5) NOT NULL,
"subject_id" numeric(5) NOT NULL,
"fee_id" numeric(5) NOT NULL,
"fee" numeric(10,2),
"register_date" timestamp(6) DEFAULT now() NOT NULL
)
WITH (OIDS=FALSE)

;
COMMENT ON TABLE "edu"."entrance_cost" IS '入学费用标准设置';
COMMENT ON COLUMN "edu"."entrance_cost"."college_id" IS '学校代码';
COMMENT ON COLUMN "edu"."entrance_cost"."batch_id" IS '批次代码';
COMMENT ON COLUMN "edu"."entrance_cost"."agent_id" IS '学习中心代码';
COMMENT ON COLUMN "edu"."entrance_cost"."subject_id" IS '专业代码';
COMMENT ON COLUMN "edu"."entrance_cost"."fee_id" IS '费用类型';
COMMENT ON COLUMN "edu"."entrance_cost"."fee" IS '费用';
COMMENT ON COLUMN "edu"."entrance_cost"."register_date" IS '登记时间';

-- ----------------------------
-- Records of entrance_cost
-- ----------------------------

-- ----------------------------
-- Table structure for "edu"."fee_type"
-- ----------------------------
DROP TABLE "edu"."fee_type";
CREATE TABLE "edu"."fee_type" (
"fee_id" numeric(5) DEFAULT fun_table_seq('fee_type'::character varying, 'fee_id'::character varying, 'next'::character varying) NOT NULL,
"fee_name" varchar(255) NOT NULL
)
WITH (OIDS=FALSE)

;
COMMENT ON TABLE "edu"."fee_type" IS '费用类型';
COMMENT ON COLUMN "edu"."fee_type"."fee_id" IS '费用代码';
COMMENT ON COLUMN "edu"."fee_type"."fee_name" IS '费用名称';

-- ----------------------------
-- Records of fee_type
-- ----------------------------

-- ----------------------------
-- Table structure for "edu"."INMEMDB_POOL_DO_NOT_DELETE"
-- ----------------------------
DROP TABLE "edu"."INMEMDB_POOL_DO_NOT_DELETE";
CREATE TABLE "edu"."INMEMDB_POOL_DO_NOT_DELETE" (
"a" char(1)
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of INMEMDB_POOL_DO_NOT_DELETE
-- ----------------------------

-- ----------------------------
-- Table structure for "edu"."receive_fund"
-- ----------------------------
DROP TABLE "edu"."receive_fund";
CREATE TABLE "edu"."receive_fund" (
"fund_id" numeric(10) DEFAULT fun_table_seq('receive_fund'::character varying, 'fund_id'::character varying, 'next'::character varying) NOT NULL,
"student_id" numeric(5) NOT NULL,
"charge_type_id" numeric(5) NOT NULL,
"charge_time" timestamp(6) DEFAULT now() NOT NULL,
"fund_income" numeric(10,2) NOT NULL,
"user_id" numeric(5) NOT NULL,
"bank_code" varchar(255),
"remark" varchar(255)
)
WITH (OIDS=FALSE)

;
COMMENT ON TABLE "edu"."receive_fund" IS '学生到账率管理';
COMMENT ON COLUMN "edu"."receive_fund"."fund_id" IS '到账序号';
COMMENT ON COLUMN "edu"."receive_fund"."student_id" IS '学生代码';
COMMENT ON COLUMN "edu"."receive_fund"."charge_type_id" IS '缴费方式';
COMMENT ON COLUMN "edu"."receive_fund"."charge_time" IS '登记时间';
COMMENT ON COLUMN "edu"."receive_fund"."fund_income" IS '已交费用';
COMMENT ON COLUMN "edu"."receive_fund"."user_id" IS '收费处理人';
COMMENT ON COLUMN "edu"."receive_fund"."bank_code" IS '网银交易代码';
COMMENT ON COLUMN "edu"."receive_fund"."remark" IS '备注';

-- ----------------------------
-- Records of receive_fund
-- ----------------------------

-- ----------------------------
-- Table structure for "edu"."recruit_agent"
-- ----------------------------
DROP TABLE "edu"."recruit_agent";
CREATE TABLE "edu"."recruit_agent" (
"agent_id" numeric(5) DEFAULT fun_table_seq('recruit_agent'::character varying, 'agent_id'::character varying, 'next'::character varying) NOT NULL,
"agent_name" varchar(255) NOT NULL,
"agent_type_id" numeric(5) NOT NULL,
"responsible_person" varchar(255),
"contact_person" varchar(255),
"contact_address" varchar(255),
"contact_phone" varchar(255),
"contact_mobile" varchar(255),
"account_name" varchar(255),
"bank_name" varchar(255),
"bank_account" varchar(255),
"remark" text,
"user_id" numeric(5) NOT NULL,
"college_url" varchar(255),
"register_date" timestamp(6) DEFAULT now() NOT NULL
)
WITH (OIDS=FALSE)

;
COMMENT ON TABLE "edu"."recruit_agent" IS '招生点信息管理';
COMMENT ON COLUMN "edu"."recruit_agent"."agent_id" IS '招生点代码';
COMMENT ON COLUMN "edu"."recruit_agent"."agent_name" IS '招生点名称';
COMMENT ON COLUMN "edu"."recruit_agent"."agent_type_id" IS '招生点类型';
COMMENT ON COLUMN "edu"."recruit_agent"."responsible_person" IS '负责人';
COMMENT ON COLUMN "edu"."recruit_agent"."contact_person" IS '联系人';
COMMENT ON COLUMN "edu"."recruit_agent"."contact_address" IS '地址';
COMMENT ON COLUMN "edu"."recruit_agent"."contact_phone" IS '电话';
COMMENT ON COLUMN "edu"."recruit_agent"."contact_mobile" IS '手机';
COMMENT ON COLUMN "edu"."recruit_agent"."account_name" IS '账户名称';
COMMENT ON COLUMN "edu"."recruit_agent"."bank_name" IS '开户行';
COMMENT ON COLUMN "edu"."recruit_agent"."bank_account" IS '银行账户';
COMMENT ON COLUMN "edu"."recruit_agent"."remark" IS '备注';
COMMENT ON COLUMN "edu"."recruit_agent"."user_id" IS '录入人';
COMMENT ON COLUMN "edu"."recruit_agent"."college_url" IS '高校连接';
COMMENT ON COLUMN "edu"."recruit_agent"."register_date" IS '登记时间';

-- ----------------------------
-- Records of recruit_agent
-- ----------------------------

-- ----------------------------
-- Table structure for "edu"."refundment_admin"
-- ----------------------------
DROP TABLE "edu"."refundment_admin";
CREATE TABLE "edu"."refundment_admin" (
"refund_id" numeric(5) DEFAULT fun_table_seq('refundment_admin'::character varying, 'refund_id'::character varying, 'next'::character varying) NOT NULL,
"student_id" numeric(5) NOT NULL,
"refund_fee" numeric(10,2) NOT NULL,
"refund_desc" text NOT NULL,
"refund_flag" bool NOT NULL,
"user_id" numeric(5) NOT NULL,
"register_date" timestamp(6) DEFAULT now() NOT NULL,
"update_date" timestamp(6)
)
WITH (OIDS=FALSE)

;
COMMENT ON TABLE "edu"."refundment_admin" IS '退费审核';
COMMENT ON COLUMN "edu"."refundment_admin"."refund_id" IS '退费序号';
COMMENT ON COLUMN "edu"."refundment_admin"."student_id" IS '学生代码';
COMMENT ON COLUMN "edu"."refundment_admin"."refund_fee" IS '退费';
COMMENT ON COLUMN "edu"."refundment_admin"."refund_desc" IS '退费说明';
COMMENT ON COLUMN "edu"."refundment_admin"."refund_flag" IS '退费审批';
COMMENT ON COLUMN "edu"."refundment_admin"."user_id" IS '退费处理人';
COMMENT ON COLUMN "edu"."refundment_admin"."register_date" IS '登记时间';
COMMENT ON COLUMN "edu"."refundment_admin"."update_date" IS '更改时间';

-- ----------------------------
-- Records of refundment_admin
-- ----------------------------

-- ----------------------------
-- Table structure for "edu"."student_change"
-- ----------------------------
DROP TABLE "edu"."student_change";
CREATE TABLE "edu"."student_change" (
"change_id" numeric(5) DEFAULT fun_table_seq('student_change'::character varying, 'change_id'::character varying, 'next'::character varying) NOT NULL,
"student_id" numeric(5) NOT NULL,
"change_content" text,
"register_date" timestamp(6) DEFAULT now() NOT NULL
)
WITH (OIDS=FALSE)

;
COMMENT ON TABLE "edu"."student_change" IS '学籍异动管理';
COMMENT ON COLUMN "edu"."student_change"."change_id" IS '更改代码';
COMMENT ON COLUMN "edu"."student_change"."student_id" IS '学生代码';
COMMENT ON COLUMN "edu"."student_change"."change_content" IS '更改内容';
COMMENT ON COLUMN "edu"."student_change"."register_date" IS '登记时间';

-- ----------------------------
-- Records of student_change
-- ----------------------------

-- ----------------------------
-- Table structure for "edu"."student_classified"
-- ----------------------------
DROP TABLE "edu"."student_classified";
CREATE TABLE "edu"."student_classified" (
"classified_id" numeric(5) DEFAULT fun_table_seq('student_classified'::character varying, 'classified_id'::character varying, 'next'::character varying) NOT NULL,
"classified_name" varchar(255) NOT NULL,
"register_date" timestamp(6) DEFAULT now() NOT NULL
)
WITH (OIDS=FALSE)

;
COMMENT ON TABLE "edu"."student_classified" IS '学生类别代码维护';
COMMENT ON COLUMN "edu"."student_classified"."classified_id" IS '类别代码';
COMMENT ON COLUMN "edu"."student_classified"."classified_name" IS '类别名称';
COMMENT ON COLUMN "edu"."student_classified"."register_date" IS '登记时间';

-- ----------------------------
-- Records of student_classified
-- ----------------------------

-- ----------------------------
-- Table structure for "edu"."student_info"
-- ----------------------------
DROP TABLE "edu"."student_info";
CREATE TABLE "edu"."student_info" (
"student_id" numeric(5) DEFAULT fun_table_seq('student_info'::character varying, 'student_id'::character varying, 'next'::character varying) NOT NULL,
"student_name" varchar(255) NOT NULL,
"identity_card" varchar(19) NOT NULL,
"exam_num" varchar(255),
"student_address" varchar(255),
"student_phone" varchar(255) NOT NULL,
"student_linkman" varchar(255),
"linkman_phone" varchar(255),
"student_college_id" varchar(255),
"student_photo" bytea,
"identity_copy" bytea,
"diploma_copy" bytea,
"network_copy" bytea,
"college_owner" numeric(5),
"batch_owner" numeric(5),
"classified_owner" numeric(5),
"subject_owner" numeric(5),
"agent_owner" numeric(5),
"student_status" bool NOT NULL,
"register_date" timestamp(6) DEFAULT now() NOT NULL,
"update_date" timestamp(6)
)
WITH (OIDS=FALSE)

;
COMMENT ON TABLE "edu"."student_info" IS '学生基本信息管理';
COMMENT ON COLUMN "edu"."student_info"."student_id" IS '学生代码';
COMMENT ON COLUMN "edu"."student_info"."student_name" IS '学生姓名';
COMMENT ON COLUMN "edu"."student_info"."identity_card" IS '学生身份证号';
COMMENT ON COLUMN "edu"."student_info"."exam_num" IS '准考证号';
COMMENT ON COLUMN "edu"."student_info"."student_address" IS '地址';
COMMENT ON COLUMN "edu"."student_info"."student_phone" IS '电话';
COMMENT ON COLUMN "edu"."student_info"."student_linkman" IS '联系人';
COMMENT ON COLUMN "edu"."student_info"."linkman_phone" IS '联系人电话';
COMMENT ON COLUMN "edu"."student_info"."student_college_id" IS '学生学校学号';
COMMENT ON COLUMN "edu"."student_info"."student_photo" IS '学生照片';
COMMENT ON COLUMN "edu"."student_info"."identity_copy" IS '身份证复印件';
COMMENT ON COLUMN "edu"."student_info"."diploma_copy" IS '毕业证复印件';
COMMENT ON COLUMN "edu"."student_info"."network_copy" IS '学信网查询截图';
COMMENT ON COLUMN "edu"."student_info"."college_owner" IS '隶属学校';
COMMENT ON COLUMN "edu"."student_info"."batch_owner" IS '隶属批次';
COMMENT ON COLUMN "edu"."student_info"."classified_owner" IS '隶属类别';
COMMENT ON COLUMN "edu"."student_info"."subject_owner" IS '隶属专业';
COMMENT ON COLUMN "edu"."student_info"."agent_owner" IS '隶属招生点';
COMMENT ON COLUMN "edu"."student_info"."student_status" IS '学生状态';
COMMENT ON COLUMN "edu"."student_info"."register_date" IS '登记时间';
COMMENT ON COLUMN "edu"."student_info"."update_date" IS '更改时间';

-- ----------------------------
-- Records of student_info
-- ----------------------------

-- ----------------------------
-- Table structure for "edu"."subjects"
-- ----------------------------
DROP TABLE "edu"."subjects";
CREATE TABLE "edu"."subjects" (
"subject_id" numeric(5) DEFAULT fun_table_seq('subjects'::character varying, 'subject_id'::character varying, 'next'::character varying) NOT NULL,
"subject_name" varchar(255) NOT NULL,
"register_date" timestamp(6) DEFAULT now() NOT NULL
)
WITH (OIDS=FALSE)

;
COMMENT ON TABLE "edu"."subjects" IS '专业代码维护';
COMMENT ON COLUMN "edu"."subjects"."subject_id" IS '专业代码';
COMMENT ON COLUMN "edu"."subjects"."subject_name" IS '专业代码';
COMMENT ON COLUMN "edu"."subjects"."register_date" IS '登记时间';

-- ----------------------------
-- Records of subjects
-- ----------------------------

-- ----------------------------
-- Table structure for "edu"."table_sequence"
-- ----------------------------
DROP TABLE "edu"."table_sequence";
CREATE TABLE "edu"."table_sequence" (
"table_name" varchar(255) NOT NULL,
"column_name" varchar(255) NOT NULL,
"seq_id" numeric(5) NOT NULL
)
WITH (OIDS=FALSE)

;
COMMENT ON TABLE "edu"."table_sequence" IS '表计数器';
COMMENT ON COLUMN "edu"."table_sequence"."table_name" IS '表名';
COMMENT ON COLUMN "edu"."table_sequence"."column_name" IS '字段名';
COMMENT ON COLUMN "edu"."table_sequence"."seq_id" IS '序列号';

-- ----------------------------
-- Records of table_sequence
-- ----------------------------
INSERT INTO "edu"."table_sequence" VALUES ('agent_return', 'ag_return_id', '0');
INSERT INTO "edu"."table_sequence" VALUES ('agent_type', 'agent_type_id', '1');
INSERT INTO "edu"."table_sequence" VALUES ('agree_return', 'agree_return_id', '0');
INSERT INTO "edu"."table_sequence" VALUES ('batch_index', 'batch_id', '2');
INSERT INTO "edu"."table_sequence" VALUES ('charge_type', 'charge_type_id', '0');
INSERT INTO "edu"."table_sequence" VALUES ('co_college', 'college_id', '0');
INSERT INTO "edu"."table_sequence" VALUES ('college_agreement', 'agreement_id', '0');
INSERT INTO "edu"."table_sequence" VALUES ('derate_request', 'derate_id', '0');
INSERT INTO "edu"."table_sequence" VALUES ('fee_type', 'fee_id', '0');
INSERT INTO "edu"."table_sequence" VALUES ('receive_fund', 'fund_id', '0');
INSERT INTO "edu"."table_sequence" VALUES ('recruit_agent', 'agent_id', '0');
INSERT INTO "edu"."table_sequence" VALUES ('refundment_admin', 'refund_id', '0');
INSERT INTO "edu"."table_sequence" VALUES ('student_change', 'change_id', '0');
INSERT INTO "edu"."table_sequence" VALUES ('student_classified', 'classified_id', '0');
INSERT INTO "edu"."table_sequence" VALUES ('student_info', 'student_id', '0');
INSERT INTO "edu"."table_sequence" VALUES ('subject', 'subject_id', '0');
INSERT INTO "edu"."table_sequence" VALUES ('user_admin', 'user_id', '0');

-- ----------------------------
-- Table structure for "edu"."user_admin"
-- ----------------------------
DROP TABLE "edu"."user_admin";
CREATE TABLE "edu"."user_admin" (
"user_id" numeric(5) DEFAULT fun_table_seq('user_admin'::character varying, 'user_id'::character varying, 'next'::character varying) NOT NULL,
"user_name" varchar(255) NOT NULL,
"admin" bool,
"user_status" bool,
"agent_id" numeric(5),
"register_date" timestamp(6) DEFAULT now() NOT NULL,
"update_date" timestamp(6) NOT NULL
)
WITH (OIDS=FALSE)

;
COMMENT ON TABLE "edu"."user_admin" IS '用户管理';
COMMENT ON COLUMN "edu"."user_admin"."user_id" IS '用户代码';
COMMENT ON COLUMN "edu"."user_admin"."user_name" IS '用户名称';
COMMENT ON COLUMN "edu"."user_admin"."admin" IS '管理员标识';
COMMENT ON COLUMN "edu"."user_admin"."user_status" IS '用户状态';
COMMENT ON COLUMN "edu"."user_admin"."agent_id" IS '隶属招生点';
COMMENT ON COLUMN "edu"."user_admin"."register_date" IS '登记时间';
COMMENT ON COLUMN "edu"."user_admin"."update_date" IS '更改时间';

-- ----------------------------
-- Records of user_admin
-- ----------------------------

-- ----------------------------
-- Alter Sequences Owned By 
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table "edu"."ag_return_amount"
-- ----------------------------
ALTER TABLE "edu"."ag_return_amount" ADD PRIMARY KEY ("agent_id", "batch_id", "ag_return_id");

-- ----------------------------
-- Primary Key structure for table "edu"."agent_relation"
-- ----------------------------
ALTER TABLE "edu"."agent_relation" ADD PRIMARY KEY ("agent_id", "refer_agent");

-- ----------------------------
-- Primary Key structure for table "edu"."agent_return"
-- ----------------------------
ALTER TABLE "edu"."agent_return" ADD PRIMARY KEY ("ag_return_id");

-- ----------------------------
-- Primary Key structure for table "edu"."agent_type"
-- ----------------------------
ALTER TABLE "edu"."agent_type" ADD PRIMARY KEY ("agent_type_id");

-- ----------------------------
-- Primary Key structure for table "edu"."agree_return"
-- ----------------------------
ALTER TABLE "edu"."agree_return" ADD PRIMARY KEY ("agree_return_id");

-- ----------------------------
-- Primary Key structure for table "edu"."batch_index"
-- ----------------------------
ALTER TABLE "edu"."batch_index" ADD PRIMARY KEY ("batch_id");

-- ----------------------------
-- Primary Key structure for table "edu"."charge_admin"
-- ----------------------------
ALTER TABLE "edu"."charge_admin" ADD PRIMARY KEY ("student_id");

-- ----------------------------
-- Primary Key structure for table "edu"."charge_type"
-- ----------------------------
ALTER TABLE "edu"."charge_type" ADD PRIMARY KEY ("charge_type_id");

-- ----------------------------
-- Primary Key structure for table "edu"."co_college"
-- ----------------------------
ALTER TABLE "edu"."co_college" ADD PRIMARY KEY ("college_id");

-- ----------------------------
-- Primary Key structure for table "edu"."college_agreement"
-- ----------------------------
ALTER TABLE "edu"."college_agreement" ADD PRIMARY KEY ("agreement_id");

-- ----------------------------
-- Primary Key structure for table "edu"."college_subject"
-- ----------------------------
ALTER TABLE "edu"."college_subject" ADD PRIMARY KEY ("college_id", "batch_id", "classified_id", "subeject_id");

-- ----------------------------
-- Primary Key structure for table "edu"."derate_request"
-- ----------------------------
ALTER TABLE "edu"."derate_request" ADD PRIMARY KEY ("derate_id");

-- ----------------------------
-- Primary Key structure for table "edu"."entrance_cost"
-- ----------------------------
ALTER TABLE "edu"."entrance_cost" ADD PRIMARY KEY ("college_id", "batch_id", "agent_id", "subject_id", "fee_id");

-- ----------------------------
-- Primary Key structure for table "edu"."fee_type"
-- ----------------------------
ALTER TABLE "edu"."fee_type" ADD PRIMARY KEY ("fee_id");

-- ----------------------------
-- Primary Key structure for table "edu"."receive_fund"
-- ----------------------------
ALTER TABLE "edu"."receive_fund" ADD PRIMARY KEY ("fund_id");

-- ----------------------------
-- Primary Key structure for table "edu"."recruit_agent"
-- ----------------------------
ALTER TABLE "edu"."recruit_agent" ADD PRIMARY KEY ("agent_id");

-- ----------------------------
-- Primary Key structure for table "edu"."refundment_admin"
-- ----------------------------
ALTER TABLE "edu"."refundment_admin" ADD PRIMARY KEY ("refund_id");

-- ----------------------------
-- Primary Key structure for table "edu"."student_change"
-- ----------------------------
ALTER TABLE "edu"."student_change" ADD PRIMARY KEY ("change_id");

-- ----------------------------
-- Primary Key structure for table "edu"."student_classified"
-- ----------------------------
ALTER TABLE "edu"."student_classified" ADD PRIMARY KEY ("classified_id");

-- ----------------------------
-- Primary Key structure for table "edu"."student_info"
-- ----------------------------
ALTER TABLE "edu"."student_info" ADD PRIMARY KEY ("student_id");

-- ----------------------------
-- Primary Key structure for table "edu"."subjects"
-- ----------------------------
ALTER TABLE "edu"."subjects" ADD PRIMARY KEY ("subject_id");

-- ----------------------------
-- Primary Key structure for table "edu"."table_sequence"
-- ----------------------------
ALTER TABLE "edu"."table_sequence" ADD PRIMARY KEY ("table_name");

-- ----------------------------
-- Primary Key structure for table "edu"."user_admin"
-- ----------------------------
ALTER TABLE "edu"."user_admin" ADD PRIMARY KEY ("user_id");

-- ----------------------------
-- Foreign Key structure for table "edu"."ag_return_amount"
-- ----------------------------
ALTER TABLE "edu"."ag_return_amount" ADD FOREIGN KEY ("ag_return_id") REFERENCES "edu"."agent_return" ("ag_return_id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "edu"."ag_return_amount" ADD FOREIGN KEY ("batch_id") REFERENCES "edu"."batch_index" ("batch_id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "edu"."ag_return_amount" ADD FOREIGN KEY ("agent_id") REFERENCES "edu"."recruit_agent" ("agent_id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Key structure for table "edu"."agent_relation"
-- ----------------------------
ALTER TABLE "edu"."agent_relation" ADD FOREIGN KEY ("agent_id") REFERENCES "edu"."recruit_agent" ("agent_id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "edu"."agent_relation" ADD FOREIGN KEY ("refer_agent") REFERENCES "edu"."recruit_agent" ("agent_id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Key structure for table "edu"."agent_return"
-- ----------------------------
ALTER TABLE "edu"."agent_return" ADD FOREIGN KEY ("agent_id") REFERENCES "edu"."recruit_agent" ("agent_id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Key structure for table "edu"."charge_admin"
-- ----------------------------
ALTER TABLE "edu"."charge_admin" ADD FOREIGN KEY ("student_id") REFERENCES "edu"."student_info" ("student_id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Key structure for table "edu"."college_agreement"
-- ----------------------------
ALTER TABLE "edu"."college_agreement" ADD FOREIGN KEY ("college_id") REFERENCES "edu"."co_college" ("college_id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "edu"."college_agreement" ADD FOREIGN KEY ("agent_id") REFERENCES "edu"."recruit_agent" ("agent_id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Key structure for table "edu"."college_subject"
-- ----------------------------
ALTER TABLE "edu"."college_subject" ADD FOREIGN KEY ("batch_id") REFERENCES "edu"."batch_index" ("batch_id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "edu"."college_subject" ADD FOREIGN KEY ("college_id") REFERENCES "edu"."co_college" ("college_id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "edu"."college_subject" ADD FOREIGN KEY ("classified_id") REFERENCES "edu"."student_classified" ("classified_id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "edu"."college_subject" ADD FOREIGN KEY ("subeject_id") REFERENCES "edu"."subjects" ("subject_id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Key structure for table "edu"."derate_request"
-- ----------------------------
ALTER TABLE "edu"."derate_request" ADD FOREIGN KEY ("student_id") REFERENCES "edu"."student_info" ("student_id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "edu"."derate_request" ADD FOREIGN KEY ("user_id") REFERENCES "edu"."user_admin" ("user_id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Key structure for table "edu"."entrance_cost"
-- ----------------------------
ALTER TABLE "edu"."entrance_cost" ADD FOREIGN KEY ("batch_id") REFERENCES "edu"."batch_index" ("batch_id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "edu"."entrance_cost" ADD FOREIGN KEY ("college_id") REFERENCES "edu"."co_college" ("college_id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "edu"."entrance_cost" ADD FOREIGN KEY ("fee_id") REFERENCES "edu"."fee_type" ("fee_id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "edu"."entrance_cost" ADD FOREIGN KEY ("agent_id") REFERENCES "edu"."recruit_agent" ("agent_id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "edu"."entrance_cost" ADD FOREIGN KEY ("subject_id") REFERENCES "edu"."subjects" ("subject_id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Key structure for table "edu"."receive_fund"
-- ----------------------------
ALTER TABLE "edu"."receive_fund" ADD FOREIGN KEY ("user_id") REFERENCES "edu"."user_admin" ("user_id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "edu"."receive_fund" ADD FOREIGN KEY ("charge_type_id") REFERENCES "edu"."charge_type" ("charge_type_id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "edu"."receive_fund" ADD FOREIGN KEY ("student_id") REFERENCES "edu"."student_info" ("student_id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Key structure for table "edu"."recruit_agent"
-- ----------------------------
ALTER TABLE "edu"."recruit_agent" ADD FOREIGN KEY ("agent_type_id") REFERENCES "edu"."agent_type" ("agent_type_id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "edu"."recruit_agent" ADD FOREIGN KEY ("user_id") REFERENCES "edu"."user_admin" ("user_id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Key structure for table "edu"."refundment_admin"
-- ----------------------------
ALTER TABLE "edu"."refundment_admin" ADD FOREIGN KEY ("student_id") REFERENCES "edu"."student_info" ("student_id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "edu"."refundment_admin" ADD FOREIGN KEY ("user_id") REFERENCES "edu"."user_admin" ("user_id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Key structure for table "edu"."student_change"
-- ----------------------------
ALTER TABLE "edu"."student_change" ADD FOREIGN KEY ("student_id") REFERENCES "edu"."student_info" ("student_id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Key structure for table "edu"."student_info"
-- ----------------------------
ALTER TABLE "edu"."student_info" ADD FOREIGN KEY ("subject_owner") REFERENCES "edu"."subjects" ("subject_id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "edu"."student_info" ADD FOREIGN KEY ("college_owner") REFERENCES "edu"."co_college" ("college_id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "edu"."student_info" ADD FOREIGN KEY ("classified_owner") REFERENCES "edu"."student_classified" ("classified_id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "edu"."student_info" ADD FOREIGN KEY ("agent_owner") REFERENCES "edu"."recruit_agent" ("agent_id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "edu"."student_info" ADD FOREIGN KEY ("batch_owner") REFERENCES "edu"."batch_index" ("batch_id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Key structure for table "edu"."user_admin"
-- ----------------------------
ALTER TABLE "edu"."user_admin" ADD FOREIGN KEY ("user_id") REFERENCES "edu"."recruit_agent" ("agent_id") ON DELETE NO ACTION ON UPDATE NO ACTION;
