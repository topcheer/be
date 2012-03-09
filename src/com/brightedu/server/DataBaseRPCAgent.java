package com.brightedu.server;

import java.io.File;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.brightedu.client.DataBaseRPC;
import com.brightedu.client.ds.Page;
import com.brightedu.dao.edu.AgentReturnMapper;
import com.brightedu.dao.edu.AgentReturnTypeMapper;
import com.brightedu.dao.edu.AgentTypeMapper;
import com.brightedu.dao.edu.AnnouncementMapper;
import com.brightedu.dao.edu.BatchIndexMapper;
import com.brightedu.dao.edu.ChargeTypeMapper;
import com.brightedu.dao.edu.CollegeAggregationMapper;
import com.brightedu.dao.edu.CollegeAgreementMapper;
import com.brightedu.dao.edu.CollegeMapper;
import com.brightedu.dao.edu.CollegeSubjectMapper;
import com.brightedu.dao.edu.CollegeSubjectViewMapper;
import com.brightedu.dao.edu.CurrentBatchMapper;
import com.brightedu.dao.edu.EntranceCostMapper;
import com.brightedu.dao.edu.FeeTypeMapper;
import com.brightedu.dao.edu.MessageRealMapper;
import com.brightedu.dao.edu.MessagesMapper;
import com.brightedu.dao.edu.PictureTypeMapper;
import com.brightedu.dao.edu.RecruitAgentMapper;
import com.brightedu.dao.edu.RecruitPlanMapper;
import com.brightedu.dao.edu.RightsCategoryFunctionMapper;
import com.brightedu.dao.edu.RightsCategoryMapper;
import com.brightedu.dao.edu.RightsDefaultMapper;
import com.brightedu.dao.edu.RightsFunctionMapper;
import com.brightedu.dao.edu.RightsOverrideMapper;
import com.brightedu.dao.edu.StudentClassifiedMapper;
import com.brightedu.dao.edu.StudentInfoMapper;
import com.brightedu.dao.edu.StudentPictureMapper;
import com.brightedu.dao.edu.StudentStatusMapper;
import com.brightedu.dao.edu.StudentTypeMapper;
import com.brightedu.dao.edu.SubjectsMapper;
import com.brightedu.dao.edu.UserMapper;
import com.brightedu.dao.edu.UserRightsEffectiveMapper;
import com.brightedu.dao.edu.UserRightsMapper;
import com.brightedu.dao.edu.UserTypeMapper;
import com.brightedu.model.edu.AgentReturnExample;
import com.brightedu.model.edu.AgentReturnKey;
import com.brightedu.model.edu.AgentReturnType;
import com.brightedu.model.edu.AgentReturnTypeExample;
import com.brightedu.model.edu.AgentType;
import com.brightedu.model.edu.AgentTypeExample;
import com.brightedu.model.edu.Announcement;
import com.brightedu.model.edu.AnnouncementExample;
import com.brightedu.model.edu.BatchIndex;
import com.brightedu.model.edu.BatchIndexExample;
import com.brightedu.model.edu.BatchIndexExample.Criteria;
import com.brightedu.model.edu.ChargeType;
import com.brightedu.model.edu.ChargeTypeExample;
import com.brightedu.model.edu.College;
import com.brightedu.model.edu.CollegeAggregation;
import com.brightedu.model.edu.CollegeAggregationExample;
import com.brightedu.model.edu.CollegeAgreement;
import com.brightedu.model.edu.CollegeAgreementExample;
import com.brightedu.model.edu.CollegeExample;
import com.brightedu.model.edu.CollegeSubject;
import com.brightedu.model.edu.CollegeSubjectExample;
import com.brightedu.model.edu.CollegeSubjectView;
import com.brightedu.model.edu.CollegeSubjectViewExample;
import com.brightedu.model.edu.CurrentBatch;
import com.brightedu.model.edu.EntranceCost;
import com.brightedu.model.edu.EntranceCostExample;
import com.brightedu.model.edu.FeeType;
import com.brightedu.model.edu.FeeTypeExample;
import com.brightedu.model.edu.MessageReal;
import com.brightedu.model.edu.MessageRealExample;
import com.brightedu.model.edu.Messages;
import com.brightedu.model.edu.MessagesExample;
import com.brightedu.model.edu.PictureType;
import com.brightedu.model.edu.PictureTypeExample;
import com.brightedu.model.edu.RecruitAgent;
import com.brightedu.model.edu.RecruitAgentExample;
import com.brightedu.model.edu.RecruitPlan;
import com.brightedu.model.edu.RecruitPlanExample;
import com.brightedu.model.edu.RightsCategory;
import com.brightedu.model.edu.RightsCategoryExample;
import com.brightedu.model.edu.RightsCategoryFunctionExample;
import com.brightedu.model.edu.RightsCategoryFunctionKey;
import com.brightedu.model.edu.RightsDefaultExample;
import com.brightedu.model.edu.RightsDefaultKey;
import com.brightedu.model.edu.RightsFunction;
import com.brightedu.model.edu.RightsFunctionExample;
import com.brightedu.model.edu.RightsOverride;
import com.brightedu.model.edu.StudentClassified;
import com.brightedu.model.edu.StudentClassifiedExample;
import com.brightedu.model.edu.StudentInfo;
import com.brightedu.model.edu.StudentInfoExample;
import com.brightedu.model.edu.StudentPicture;
import com.brightedu.model.edu.StudentPictureExample;
import com.brightedu.model.edu.StudentStatus;
import com.brightedu.model.edu.StudentStatusExample;
import com.brightedu.model.edu.StudentType;
import com.brightedu.model.edu.StudentTypeExample;
import com.brightedu.model.edu.Subjects;
import com.brightedu.model.edu.SubjectsExample;
import com.brightedu.model.edu.User;
import com.brightedu.model.edu.UserExample;
import com.brightedu.model.edu.UserRights;
import com.brightedu.model.edu.UserRightsEffective;
import com.brightedu.model.edu.UserRightsEffectiveExample;
import com.brightedu.model.edu.UserRightsExample;
import com.brightedu.model.edu.UserType;
import com.brightedu.model.edu.UserTypeExample;
import com.brightedu.server.util.ConnectionManager;
import com.brightedu.server.util.Log;
import com.brightedu.server.util.ReflectUtil;
import com.brightedu.server.util.ServerProperties;
import com.brightedu.server.util.Utils;
import com.brightedu.shared.OperatioinFailedException;

public class DataBaseRPCAgent implements DataBaseRPC {
	SqlSessionFactory sessionFactory;
	BrightServlet remoteServlet;
	String daoPackageName = "com.brightedu.dao.edu.";
	String modelPackageName = "com.brightedu.model.edu.";

	public DataBaseRPCAgent() {
		sessionFactory = ConnectionManager.getSessionFactory();
	}

	public static DataBaseRPC createAgentProxy(BrightServlet servlet) {
		DataBaseRPCAgent agt = new DataBaseRPCAgent();
		agt.setRemoteServlet(servlet);
		DataBaseRPCHandler handler = new DataBaseRPCHandler(agt);
		return (DataBaseRPC) Proxy.newProxyInstance(agt.getClass()
				.getClassLoader(), agt.getClass().getInterfaces(), handler);
	}

	/*********************** 批次管理 ************************************/

	public List getBatchList(int offset, int limit, boolean needTotalCounts) {
		SqlSession session = sessionFactory.openSession();
		try {
			// if (remoteServlet.getUser().getUser_id() == 1) {
			// OperatioinFailedException ex = new OperatioinFailedException(
			// "session expired or user not login");
			// throw ex;
			// }
			BatchIndexExample ex = new BatchIndexExample();
			if (offset != -1 || limit != -1) {
				ex.setPage(new Page(offset, limit));
			}
			ex.setOrderByClause("batch_id desc");

			BatchIndexMapper map = session.getMapper(BatchIndexMapper.class);
			List result = map.selectByExample(ex);
			if (needTotalCounts) {
				Integer counts = map.countByExample(null);
				result.add(counts);
			}
			return result;
		} finally {
			session.close();
		}
	}

	@Override
	public boolean deleteBatch(List<Integer> batch_ids) {
		SqlSession session = sessionFactory.openSession();
		try {
			BatchIndexMapper bim = session.getMapper(BatchIndexMapper.class);
			// int count = bim.deleteByPrimaryKey(batch_id);
			BatchIndexExample ex = new BatchIndexExample();
			Criteria cr = ex.createCriteria();
			cr.andBatch_idIn(batch_ids);
			bim.deleteByExample(ex);
			session.commit();
			return true;
		} finally {
			session.close();
		}
	}

	@Override
	public boolean saveBatch(BatchIndex editedBatch) {
		SqlSession session = sessionFactory.openSession();
		try {
			BatchIndexMapper bim = session.getMapper(BatchIndexMapper.class);
			bim.updateByPrimaryKey(editedBatch);
			session.commit();
			return true;
		} finally {
			session.close();
		}
	}

	/*********************** 学生层次管理 ************************************/
	@Override
	public List getStudentClassesList(int offset, int limit,
			boolean needTotalCounts) {
		SqlSession session = sessionFactory.openSession();
		try {
			StudentClassifiedMapper mp = session
					.getMapper(StudentClassifiedMapper.class);

			StudentClassifiedExample ex = new StudentClassifiedExample();
			if (offset != -1 || limit != -1) {
				ex.setPage(new Page(offset, limit));
			}
			ex.setOrderByClause("classified_id desc");
			List result = mp.selectByExample(ex);
			if (needTotalCounts) {
				Integer counts = mp.countByExample(null);
				result.add(counts);
			}
			return result;
		} finally {
			session.close();
		}

	}

	@Override
	public boolean deleteStudentClasses(List<Integer> studentClassesId) {
		SqlSession session = sessionFactory.openSession();
		try {
			StudentClassifiedMapper scm = session
					.getMapper(StudentClassifiedMapper.class);
			StudentClassifiedExample ex = new StudentClassifiedExample();
			StudentClassifiedExample.Criteria cr = ex.createCriteria();
			cr.andClassified_idIn(studentClassesId);
			scm.deleteByExample(ex);
			session.commit();
			return true;
		} finally {
			session.close();
		}
	}

	@Override
	public boolean saveStudentClasses(StudentClassified studentClass) {
		SqlSession session = sessionFactory.openSession();
		try {
			StudentClassifiedMapper scm = session
					.getMapper(StudentClassifiedMapper.class);
			scm.updateByPrimaryKey(studentClass);
			session.commit();
			return true;
		} finally {
			session.close();
		}
	}

	/*********************** 学生类型管理 ************************************/
	@Override
	public List<StudentType> getStudentTypeList(int offset, int limit,
			boolean needTotalCounts) {
		SqlSession session = sessionFactory.openSession();
		try {
			StudentTypeMapper mp = session.getMapper(StudentTypeMapper.class);
			StudentTypeExample ex = new StudentTypeExample();
			ex.setOrderByClause("student_type_id desc");
			if (offset != -1 || limit != -1) {
				ex.setPage(new Page(offset, limit));
			}
			List result = mp.selectByExample(ex);
			if (needTotalCounts) {
				Integer counts = mp.countByExample(null);
				result.add(counts);
			}
			// List<StudentType> result = session.selectList(
			// "com.brightedu.dao.edu.StudentTypeMapper.selectByExample",
			// ex, new RowBounds(offset, limit));
			return result;
		} finally {
			session.close();
		}
	}

	@Override
	public boolean deleteStudentType(List<Integer> studentTypeId) {
		SqlSession session = sessionFactory.openSession();
		try {
			StudentTypeMapper scm = session.getMapper(StudentTypeMapper.class);
			StudentTypeExample ex = new StudentTypeExample();
			StudentTypeExample.Criteria cr = ex.createCriteria();
			cr.andStudent_type_idIn(studentTypeId);
			scm.deleteByExample(ex);
			session.commit();
			return true;
		} finally {
			session.close();
		}
	}

	@Override
	public boolean saveStudentType(StudentType studenttype) {
		SqlSession session = sessionFactory.openSession();
		try {
			StudentTypeMapper scm = session.getMapper(StudentTypeMapper.class);
			scm.updateByPrimaryKey(studenttype);
			session.commit();
			return true;
		} finally {
			session.close();
		}
	}

	/*********************** 合作高校代码维护 ************************************/

	@Override
	public List<College> getCollegeList(int offset, int limit,
			boolean needTotalCounts) {
		SqlSession session = sessionFactory.openSession();
		try {
			CollegeExample ex = new CollegeExample();
			ex.setOrderByClause("college_id desc");
			CollegeMapper cm = session.getMapper(CollegeMapper.class);
			if (offset != -1 || limit != -1) {
				ex.setPage(new Page(offset, limit));
			}
			List result = cm.selectByExample(ex);
			if (needTotalCounts) {
				Integer counts = cm.countByExample(null);
				result.add(counts);
			}
			return result;
		} finally {
			session.close();
		}
	}

	@Override
	public boolean deleteCollege(List<Integer> college_ids) {
		SqlSession session = sessionFactory.openSession();
		try {
			CollegeMapper cm = session.getMapper(CollegeMapper.class);
			CollegeExample ex = new CollegeExample();
			CollegeExample.Criteria cr = ex.createCriteria();
			cr.andCollege_idIn(college_ids);
			cm.deleteByExample(ex);
			session.commit();
			return true;
		} finally {
			session.close();
		}
	}

	@Override
	public boolean saveCollege(College college) {
		SqlSession session = sessionFactory.openSession();
		try {
			CollegeMapper cm = session.getMapper(CollegeMapper.class);
			cm.updateByPrimaryKey(college);
			session.commit();
			return true;
		} finally {
			session.close();
		}
	}

	/*********************** 专业代码维护 ************************************/
	@Override
	public List<Subjects> getSubjectsList(int offset, int limit,
			boolean needTotalCounts) {
		SqlSession session = sessionFactory.openSession();
		try {
			SubjectsExample ex = new SubjectsExample();
			ex.setOrderByClause("subject_id desc");
			if (offset != -1 || limit != -1) {
				ex.setPage(new Page(offset, limit));
			}
			SubjectsMapper mp = session.getMapper(SubjectsMapper.class);
			List result = mp.selectByExample(ex);
			if (needTotalCounts) {
				Integer counts = mp.countByExample(null);
				result.add(counts);
			}
			return result;
		} finally {
			session.close();
		}
	}

	@Override
	public boolean deleteSubject(List<Integer> subject_ids) {
		SqlSession session = sessionFactory.openSession();
		try {
			SubjectsMapper cm = session.getMapper(SubjectsMapper.class);
			SubjectsExample ex = new SubjectsExample();
			SubjectsExample.Criteria cr = ex.createCriteria();
			cr.andSubject_idIn(subject_ids);
			cm.deleteByExample(ex);
			session.commit();
			return true;
		} finally {
			session.close();
		}
	}

	@Override
	public boolean saveSubject(Subjects subject) {
		SqlSession session = sessionFactory.openSession();
		try {
			SubjectsMapper cm = session.getMapper(SubjectsMapper.class);
			cm.updateByPrimaryKey(subject);
			session.commit();
			return true;
		} finally {
			session.close();
		}
	}

	/*********************** 机构类型维护 ************************************/
	@Override
	public List<AgentType> getAgentTypeList(int offset, int limit,
			boolean needTotalCounts) {
		SqlSession session = sessionFactory.openSession();
		try {
			AgentTypeExample ex = new AgentTypeExample();
			ex.setOrderByClause("agent_type_id desc");
			if (offset != -1 || limit != -1) {
				ex.setPage(new Page(offset, limit));
			}
			AgentTypeMapper mp = session.getMapper(AgentTypeMapper.class);
			List result = mp.selectByExample(ex);
			if (needTotalCounts) {
				Integer counts = mp.countByExample(null);
				result.add(counts);
			}
			return result;
		} finally {
			session.close();
		}
	}

	@Override
	public boolean deleteAgentType(List<Integer> agentType_ids) {
		SqlSession session = sessionFactory.openSession();
		try {
			AgentTypeMapper cm = session.getMapper(AgentTypeMapper.class);
			AgentTypeExample ex = new AgentTypeExample();
			AgentTypeExample.Criteria cr = ex.createCriteria();
			cr.andAgent_type_idIn(agentType_ids);
			cm.deleteByExample(ex);
			session.commit();
			return true;
		} finally {
			session.close();
		}
	}

	@Override
	public boolean saveAgentType(AgentType agenttype) {
		SqlSession session = sessionFactory.openSession();
		try {
			AgentTypeMapper cm = session.getMapper(AgentTypeMapper.class);
			cm.updateByPrimaryKey(agenttype);
			session.commit();
			return true;
		} finally {
			session.close();
		}
	}

	/*********************** 费用类型维护 ************************************/
	@Override
	public List<FeeType> getFeeTypeList(int offset, int limit,
			boolean needTotalCounts) {
		SqlSession session = sessionFactory.openSession();
		try {
			FeeTypeExample ex = new FeeTypeExample();
			ex.setOrderByClause("fee_id desc");
			FeeTypeMapper mp = session.getMapper(FeeTypeMapper.class);
			if (offset != -1 || limit != -1) {
				ex.setPage(new Page(offset, limit));
			}
			List result = mp.selectByExample(ex);
			if (needTotalCounts) {
				result.add(mp.countByExample(null));
			}
			return result;
		} finally {
			session.close();
		}
	}

	@Override
	public boolean deletFeeType(List<Integer> feeType_ids) {
		SqlSession session = sessionFactory.openSession();
		try {
			FeeTypeMapper scm = session.getMapper(FeeTypeMapper.class);
			FeeTypeExample ex = new FeeTypeExample();
			FeeTypeExample.Criteria cr = ex.createCriteria();
			cr.andFee_idIn(feeType_ids);
			scm.deleteByExample(ex);
			session.commit();
			return true;
		} finally {
			session.close();
		}
	}

	@Override
	public boolean saveFeeType(FeeType agenttype) {
		SqlSession session = sessionFactory.openSession();
		try {
			FeeTypeMapper bim = session.getMapper(FeeTypeMapper.class);
			bim.updateByPrimaryKey(agenttype);
			session.commit();
			return true;
		} finally {
			session.close();
		}
	}

	/*********************** 入账类型维护 ************************************/
	@Override
	public List<ChargeType> getChargeTypeList(int offset, int limit,
			boolean needTotalCounts) {
		SqlSession session = sessionFactory.openSession();
		try {
			ChargeTypeMapper mp = session.getMapper(ChargeTypeMapper.class);
			ChargeTypeExample ex = new ChargeTypeExample();
			ex.setOrderByClause("charge_type_id desc");
			if (offset != -1 || limit != -1) {
				ex.setPage(new Page(offset, limit));
			}
			List result = mp.selectByExample(ex);
			if (needTotalCounts) {
				result.add(mp.countByExample(null));
			}
			return result;
		} finally {
			session.close();
		}
	}

	@Override
	public boolean deletChargeType(List<Integer> ChargeType_ids) {
		SqlSession session = sessionFactory.openSession();
		try {
			ChargeTypeMapper scm = session.getMapper(ChargeTypeMapper.class);
			ChargeTypeExample ex = new ChargeTypeExample();
			ChargeTypeExample.Criteria cr = ex.createCriteria();
			cr.andCharge_type_idIn(ChargeType_ids);
			scm.deleteByExample(ex);
			session.commit();
			return true;
		} finally {
			session.close();
		}
	}

	@Override
	public boolean saveChargeType(ChargeType agenttype) {
		SqlSession session = sessionFactory.openSession();
		try {
			ChargeTypeMapper bim = session.getMapper(ChargeTypeMapper.class);
			bim.updateByPrimaryKey(agenttype);
			session.commit();
			return true;
		} finally {
			session.close();
		}
	}

	/*********************** 用户类型维护 ************************************/
	@Override
	public List<UserType> getUserTypeList(int offset, int limit,
			boolean needTotalCounts) {
		SqlSession session = sessionFactory.openSession();
		try {
			UserTypeExample ex = new UserTypeExample();
			ex.setOrderByClause("user_type_id desc");
			UserTypeMapper mp = session.getMapper(UserTypeMapper.class);
			if (offset != -1 || limit != -1) {
				ex.setPage(new Page(offset, limit));
			}
			List result = mp.selectByExample(ex);
			if (needTotalCounts) {
				result.add(mp.countByExample(null));
			}
			return result;
		} finally {
			session.close();
		}
	}

	@Override
	public boolean deletUserType(List<Integer> UserType_ids) {
		SqlSession session = sessionFactory.openSession();
		try {
			UserTypeMapper scm = session.getMapper(UserTypeMapper.class);
			UserTypeExample ex = new UserTypeExample();
			UserTypeExample.Criteria cr = ex.createCriteria();
			cr.andUser_type_idIn(UserType_ids);
			scm.deleteByExample(ex);

			// TODO: also need to delete records from RightsDefault

			session.commit();
			return true;
		} finally {
			session.close();
		}
	}

	@Override
	public boolean saveUserType(UserType agenttype) {
		SqlSession session = sessionFactory.openSession();
		try {
			UserTypeMapper bim = session.getMapper(UserTypeMapper.class);
			bim.updateByPrimaryKey(agenttype);
			session.commit();
			return true;
		} finally {
			session.close();
		}
	}

	/*********************** 照片类型维护 ************************************/

	@Override
	public List<PictureType> getPictureTypeList(int offset, int limit,
			boolean needTotalCounts) {
		SqlSession session = sessionFactory.openSession();
		try {
			PictureTypeMapper mp = session.getMapper(PictureTypeMapper.class);
			PictureTypeExample ex = new PictureTypeExample();
			ex.setOrderByClause("pic_type_id desc");
			if (offset != -1 || limit != -1) {
				ex.setPage(new Page(offset, limit));
			}
			List result = mp.selectByExample(ex);
			if (needTotalCounts) {
				result.add(mp.countByExample(null));
			}
			return result;
		} finally {
			session.close();
		}
	}

	@Override
	public boolean deletPictureType(List<Integer> PictureType_ids) {
		SqlSession session = sessionFactory.openSession();
		try {
			PictureTypeMapper scm = session.getMapper(PictureTypeMapper.class);
			PictureTypeExample ex = new PictureTypeExample();
			PictureTypeExample.Criteria cr = ex.createCriteria();
			cr.andPic_type_idIn(PictureType_ids);
			scm.deleteByExample(ex);
			session.commit();
			return true;
		} finally {
			session.close();
		}
	}

	@Override
	public boolean savePictureType(PictureType agenttype) {
		SqlSession session = sessionFactory.openSession();
		try {
			PictureTypeMapper bim = session.getMapper(PictureTypeMapper.class);
			bim.updateByPrimaryKey(agenttype);
			session.commit();
			return true;
		} finally {
			session.close();
		}
	}

	/*********************** 学生状态类型维护 ************************************/

	@Override
	public List<StudentStatus> getStudentStatusList(int offset, int limit,
			boolean needTotalCounts) {
		SqlSession session = sessionFactory.openSession();
		try {
			StudentStatusMapper mp = session
					.getMapper(StudentStatusMapper.class);
			StudentStatusExample ex = new StudentStatusExample();
			ex.setOrderByClause("stu_status_id desc");
			if (offset != -1 || limit != -1) {
				ex.setPage(new Page(offset, limit));
			}
			List result = mp.selectByExample(ex);
			if (needTotalCounts) {
				result.add(mp.countByExample(null));
			}
			return result;
		} finally {
			session.close();
		}
	}

	@Override
	public boolean deletStudentStatus(List<Integer> StudentStatus_ids) {
		SqlSession session = sessionFactory.openSession();
		try {
			StudentStatusMapper scm = session
					.getMapper(StudentStatusMapper.class);
			StudentStatusExample ex = new StudentStatusExample();
			StudentStatusExample.Criteria cr = ex.createCriteria();
			cr.andStu_status_idIn(StudentStatus_ids);
			scm.deleteByExample(ex);
			session.commit();
			return true;
		} finally {
			session.close();
		}
	}

	@Override
	public boolean saveStudentStatus(StudentStatus agenttype) {
		SqlSession session = sessionFactory.openSession();
		try {
			StudentStatusMapper bim = session
					.getMapper(StudentStatusMapper.class);
			bim.updateByPrimaryKey(agenttype);
			session.commit();
			return true;
		} finally {
			session.close();
		}
	}

	public BrightServlet getRemoteServlet() {
		return remoteServlet;
	}

	public void setRemoteServlet(BrightServlet remoteServlet) {
		this.remoteServlet = remoteServlet;
	}

	/************************** agent管理 ************************************/

	public List<RecruitAgent> getRecruitAgentList(int offset, int limit,
			boolean needTotalCounts, boolean only_can_return) {
		SqlSession session = sessionFactory.openSession();
		try {
			RecruitAgentExample ex = new RecruitAgentExample();
			ex.setOrderByClause("agent_id desc");

			RecruitAgentMapper cm = session.getMapper(RecruitAgentMapper.class);
			if (offset != -1 || limit != -1) {
				ex.setPage(new Page(offset, limit));
			}

			if (only_can_return) {
				AgentTypeMapper am = session.getMapper(AgentTypeMapper.class);
				AgentTypeExample ate = new AgentTypeExample();
				ate.createCriteria().andIs_returnEqualTo(true);
				List<AgentType> typeList = am.selectByExample(ate);
				ArrayList<Integer> typeIdList = new ArrayList<Integer>();
				for (AgentType type : typeList) {
					typeIdList.add(type.getAgent_type_id());

				}
				ex.createCriteria().andAgent_type_idIn(typeIdList);
			}

			List result = cm.selectByExample(ex);
			if (needTotalCounts) {
				Integer counts = cm.countByExample(null);
				result.add(counts);
			}
			return result;
		} finally {
			session.close();
		}
	}

	@Override
	public boolean deletRecruitAgent(List<Integer> agent_ids) {
		SqlSession session = sessionFactory.openSession();
		try {
			RecruitAgentMapper scm = session
					.getMapper(RecruitAgentMapper.class);
			RecruitAgentExample ex = new RecruitAgentExample();
			RecruitAgentExample.Criteria cr = ex.createCriteria();
			cr.andAgent_idIn(agent_ids);
			scm.deleteByExample(ex);
			session.commit();
			return true;
		} finally {
			session.close();
		}
	}

	@Override
	public boolean saveRecruitAgent(RecruitAgent agent) {
		SqlSession session = sessionFactory.openSession();

		try {
			RecruitAgentMapper bim = session
					.getMapper(RecruitAgentMapper.class);
			bim.updateByPrimaryKey(agent);
			session.commit();
			return true;
		} finally {
			session.close();
		}
	}

	/*********************** 招生计划维护 ************************************/

	@Override
	public List<CollegeSubjectView> getCollegeSubjectList(int college,
			int level, int batch) {
		SqlSession session = sessionFactory.openSession();
		try {
			CollegeSubjectViewMapper mp = session
					.getMapper(CollegeSubjectViewMapper.class);
			CollegeSubjectViewExample ex = new CollegeSubjectViewExample();
			// ex.setOrderByClause("subeject_id");
			ex.createCriteria().andBatch_idEqualTo(batch)
					.andCollege_idEqualTo(college)
					.andClassified_idEqualTo(level);

			List<CollegeSubjectView> result = mp.selectByExample(ex);
			return result;

		} finally {
			session.close();
		}
	}

	@Override
	public boolean addCollegeSubject(List<CollegeSubject> collegeSubjects) {
		SqlSession session = sessionFactory.openSession();
		try {
			CollegeSubjectMapper mp = session
					.getMapper(CollegeSubjectMapper.class);

			// 先删除比较保险,连续调用两个RPC可能导致还没删除就已经开始插入了

			CollegeSubjectExample ex = new CollegeSubjectExample();
			ex.createCriteria()
					.andBatch_idEqualTo(collegeSubjects.get(0).getBatch_id())
					.andClassified_idEqualTo(
							collegeSubjects.get(0).getClassified_id())
					.andCollege_idEqualTo(
							collegeSubjects.get(0).getCollege_id());
			mp.deleteByExample(ex);

			for (int i = 0; i < collegeSubjects.size(); i++) {
				mp.insertSelective(collegeSubjects.get(i));
			}

			session.commit();
			return true;

		} finally {
			session.close();
		}
	}

	@Override
	public List<RecruitPlan> getRecruitPlanList(int batch) {
		SqlSession session = sessionFactory.openSession();
		try {
			RecruitPlanMapper mp = session.getMapper(RecruitPlanMapper.class);
			RecruitPlanExample ex = new RecruitPlanExample();
			ex.setOrderByClause("college_name,classified_name,subject_name");
			ex.createCriteria().andBatch_idEqualTo(batch);
			List<RecruitPlan> result = mp.selectByExample(ex);
			return result;

		} finally {
			session.close();
		}
	}

	@Override
	public boolean deletCollegeSubject(CollegeSubject collegeSubjects) {
		SqlSession session = sessionFactory.openSession();
		try {
			CollegeSubjectMapper mp = session
					.getMapper(CollegeSubjectMapper.class);

			CollegeSubjectExample ex = new CollegeSubjectExample();
			ex.createCriteria()
					.andBatch_idEqualTo(collegeSubjects.getBatch_id())
					.andClassified_idEqualTo(collegeSubjects.getClassified_id())
					.andCollege_idEqualTo(collegeSubjects.getCollege_id());
			mp.deleteByExample(ex);
			session.commit();
			return true;

		} finally {
			session.close();
		}
	}

	/*********************** 当前批次设置 ************************************/
	@Override
	public boolean addOrUpdateCurrentBatch(Integer batchNo) {
		SqlSession session = sessionFactory.openSession();
		try {
			CurrentBatchMapper mp = session.getMapper(CurrentBatchMapper.class);
			CurrentBatch cb = new CurrentBatch();
			cb.setCurrent_batch_id(batchNo);
			cb.setUpdate_date(new Date());

			if (mp.countByExample(null) > 0) {
				// update
				mp.updateByExample(cb, null);
			} else {
				// insert
				mp.insertSelective(cb);
			}
			session.commit();
			return true;

		} finally {
			session.close();
		}
	}

	@Override
	public Integer getCurrentBatch() {
		SqlSession session = sessionFactory.openSession();
		try {
			CurrentBatchMapper mp = session.getMapper(CurrentBatchMapper.class);
			ArrayList<CurrentBatch> cb = new ArrayList<CurrentBatch>();
			cb = (ArrayList<CurrentBatch>) mp.selectByExample(null);
			if (cb.size() == 0)
				return -1;

			return cb.get(0).getCurrent_batch_id();

		} finally {
			session.close();
		}
	}

	/*********************** 合作高校协议维护 ************************************/

	@Override
	public List<CollegeAgreement> getCollegeAgreementList(int offset,
			int limit, boolean needTotalCounts) {
		SqlSession session = sessionFactory.openSession();
		try {
			CollegeAgreementMapper mp = session
					.getMapper(CollegeAgreementMapper.class);
			CollegeAgreementExample ex = new CollegeAgreementExample();
			ex.setOrderByClause("agreement_id desc");
			List result = mp.selectByExample(ex);
			if (needTotalCounts) {
				Integer counts = mp.countByExample(null);
				result.add(counts);
			}
			return result;

		} finally {
			session.close();
		}
	}

	@Override
	public Serializable getObjectById(String mapperClassName, int id) {
		SqlSession session = sessionFactory.openSession();
		try {
			Class mapperClass = Class.forName(mapperClassName);
			Object mapper = session.getMapper(mapperClass);

			Method method = mapperClass.getMethod("selectByPrimaryKey",
					Integer.class);
			return (Serializable) method.invoke(mapper, id);

		} catch (Exception e) {
			OperatioinFailedException ex = new OperatioinFailedException(e);
			Log.e("", ex);
			throw ex;
		} finally {
			session.close();
		}

	}

	@Override
	public boolean deleteCollegeAgreement(List<CollegeAgreement> agreements) {
		SqlSession session = sessionFactory.openSession();
		try {
			List<Integer> ids = new ArrayList<Integer>();
			String agreementSubDir = new File(
					ServerProperties.getDataLocation()).getAbsolutePath()
					+ "/agreement/";
			for (CollegeAgreement c : agreements) {
				ids.add(c.getAgreement_id());
				try {
					File aggFile = new File(agreementSubDir
							+ c.getAgreement_name());
					if (aggFile.exists()) {
						aggFile.delete();
					}
				} catch (Exception e) {
					Log.e("", e);
				}
			}
			CollegeAgreementMapper cm = session
					.getMapper(CollegeAgreementMapper.class);
			CollegeAgreementExample ex = new CollegeAgreementExample();
			CollegeAgreementExample.Criteria cr = ex.createCriteria();
			cr.andAgreement_idIn(ids);
			cm.deleteByExample(ex);
			session.commit();
			return true;
		} finally {
			session.close();
		}
	}

	@Override
	public boolean saveCollegeAgreement(CollegeAgreement agreement) {
		SqlSession session = sessionFactory.openSession();
		try {
			CollegeAgreementMapper bim = session
					.getMapper(CollegeAgreementMapper.class);
			bim.updateByPrimaryKey(agreement);
			session.commit();
			return true;
		} finally {
			session.close();
		}
	}

	/************************ 权限基础数据设置 *********************************/

	@Override
	public List<RightsCategory> getRightsCategory() {
		SqlSession session = sessionFactory.openSession();
		try {
			RightsCategoryMapper mp = session
					.getMapper(RightsCategoryMapper.class);

			List<RightsCategory> result = mp.selectByExample(null);
			return result;

		} finally {
			session.close();
		}
	}

	@Override
	public List<RightsFunction> getRightsFunction() {
		SqlSession session = sessionFactory.openSession();
		try {
			RightsFunctionMapper mp = session
					.getMapper(RightsFunctionMapper.class);

			List<RightsFunction> result = mp.selectByExample(null);
			return result;

		} finally {
			session.close();
		}
	}

	@Override
	public List<RightsCategoryFunctionKey> getRightsCategoryFunction(
			String categoryID) {
		SqlSession session = sessionFactory.openSession();
		try {
			RightsCategoryFunctionMapper mp = session
					.getMapper(RightsCategoryFunctionMapper.class);
			RightsCategoryFunctionExample ex = new RightsCategoryFunctionExample();
			ex.createCriteria().andCategory_idEqualTo(categoryID);
			List<RightsCategoryFunctionKey> result = mp.selectByExample(ex);
			return result;

		} finally {
			session.close();
		}
	}

	@Override
	public boolean addRightsCatetoryFunctions(
			List<RightsCategoryFunctionKey> rightsCategoryFunctionList) {
		SqlSession session = sessionFactory.openSession();
		try {
			RightsCategoryFunctionMapper mp = session
					.getMapper(RightsCategoryFunctionMapper.class);

			// 先删除比较保险,连续调用两个RPC可能导致还没删除就已经开始插入了

			RightsCategoryFunctionExample ex = new RightsCategoryFunctionExample();
			ex.createCriteria().andCategory_idEqualTo(
					rightsCategoryFunctionList.get(0).getCategory_id());

			mp.deleteByExample(ex);

			for (int i = 0; i < rightsCategoryFunctionList.size(); i++) {
				mp.insertSelective(rightsCategoryFunctionList.get(i));
			}

			session.commit();
			return true;

		} finally {
			session.close();
		}
	}

	@Override
	public boolean deleteRightsCategory(RightsCategory category) {
		SqlSession session = sessionFactory.openSession();
		try {
			RightsCategoryMapper mp = session
					.getMapper(RightsCategoryMapper.class);
			RightsCategoryExample ex = new RightsCategoryExample();
			ex.createCriteria()
					.andCategory_idEqualTo(category.getCategory_id());

			int result = mp.deleteByExample(ex);
			session.commit();
			return true;

		} finally {
			session.close();
		}
	}

	@Override
	public boolean deleteRightsFunction(List<String> function) {
		SqlSession session = sessionFactory.openSession();
		try {
			RightsFunctionMapper mp = session
					.getMapper(RightsFunctionMapper.class);

			RightsFunctionExample ex = new RightsFunctionExample();
			ex.createCriteria().andFunction_idIn(function);
			mp.deleteByExample(ex);

			// also delete record from CategoryFunction
			RightsCategoryFunctionMapper mp2 = session
					.getMapper(RightsCategoryFunctionMapper.class);

			RightsCategoryFunctionExample ex2 = new RightsCategoryFunctionExample();
			ex2.createCriteria().andFunction_idIn(function);

			mp2.deleteByExample(ex2);

			session.commit();
			return true;

		} finally {
			session.close();
		}
	}

	@Override
	public boolean deleteRightsCatetoryFunctions(
			String rightsCategoryFunctionList) {
		SqlSession session = sessionFactory.openSession();
		try {
			RightsCategoryFunctionMapper mp = session
					.getMapper(RightsCategoryFunctionMapper.class);

			RightsCategoryFunctionExample ex = new RightsCategoryFunctionExample();
			ex.createCriteria().andCategory_idEqualTo(
					rightsCategoryFunctionList);

			mp.deleteByExample(ex);

			session.commit();
			return true;

		} finally {
			session.close();
		}

	}

	/*********************** 学生注册 ************************************/

	@Override
	public List<RightsDefaultKey> getRightsDefault(String user_typ_Id) {
		SqlSession session = sessionFactory.openSession();
		try {
			RightsDefaultMapper mp = session
					.getMapper(RightsDefaultMapper.class);
			RightsDefaultExample ex = new RightsDefaultExample();
			ex.createCriteria()
					.andUser_type_idEqualTo(new Integer(user_typ_Id));
			List<RightsDefaultKey> result = mp.selectByExample(ex);
			return result;

		} finally {
			session.close();
		}
	}

	@Override
	public boolean addRightsDefault(List<RightsDefaultKey> rightDefaultList) {
		SqlSession session = sessionFactory.openSession();
		try {
			RightsDefaultMapper mp = session
					.getMapper(RightsDefaultMapper.class);

			// 先删除比较保险,连续调用两个RPC可能导致还没删除就已经开始插入了

			RightsDefaultExample ex = new RightsDefaultExample();
			ex.createCriteria().andUser_type_idEqualTo(
					rightDefaultList.get(0).getUser_type_id());

			mp.deleteByExample(ex);

			for (int i = 0; i < rightDefaultList.size(); i++) {
				mp.insertSelective(rightDefaultList.get(i));
			}

			session.commit();
			return true;

		} finally {
			session.close();
		}
	}

	@Override
	public boolean deleteRightsDefault(Integer userType_id) {
		SqlSession session = sessionFactory.openSession();
		try {
			RightsDefaultMapper mp = session
					.getMapper(RightsDefaultMapper.class);

			RightsDefaultExample ex = new RightsDefaultExample();
			ex.createCriteria().andUser_type_idEqualTo(userType_id);
			mp.deleteByExample(ex);
			session.commit();
			return true;

		} finally {
			session.close();
		}

	}

	/************************ 用户管理 *********************************/

	@Override
	public List<User> getUserList(int offset, int limit, boolean needTotalCounts) {
		SqlSession session = sessionFactory.openSession();
		try {
			UserMapper mp = session.getMapper(UserMapper.class);

			UserExample ex = new UserExample();
			if (offset != -1 || limit != -1) {
				ex.setPage(new Page(offset, limit));
			}
			ex.setOrderByClause("user_id desc");
			List result = mp.selectByExample(ex);
			if (needTotalCounts) {
				Integer counts = mp.countByExample(null);
				result.add(counts);
			}
			return result;
		} finally {
			session.close();
		}
	}

	@Override
	public boolean deletUser(List<Integer> user_ids) {
		SqlSession session = sessionFactory.openSession();
		try {
			UserMapper scm = session.getMapper(UserMapper.class);
			UserExample ex = new UserExample();
			UserExample.Criteria cr = ex.createCriteria();
			cr.andUser_idIn(user_ids);
			scm.deleteByExample(ex);
			session.commit();
			return true;
		} finally {
			session.close();
		}
	}

	@Override
	public boolean saveUser(User user) {
		SqlSession session = sessionFactory.openSession();
		try {
			UserMapper scm = session.getMapper(UserMapper.class);
			User oldUser = scm.selectByPrimaryKey(user.getUser_id());
			if (!oldUser.getUser_password().equals(user.getUser_password())) {
				// if password changed, then update password to new one
				user.setUser_password(Utils.md5(user.getUser_password()));
			}
			scm.updateByPrimaryKey(user);
			session.commit();
			return true;
		} finally {
			session.close();
		}
	}

	@Override
	public boolean setOverridePriv(List<RightsCategoryFunctionKey> list,
			boolean addOrRemove) {

		return false;

	}

	@Override
	public List<UserRights> getUserRights(User user) {
		SqlSession session = sessionFactory.openSession();
		try {
			UserRightsMapper mp = session.getMapper(UserRightsMapper.class);

			UserRightsExample ex = new UserRightsExample();
			ex.setOrderByClause("category_id,function_id");
			ex.createCriteria().andUser_idEqualTo(user.getUser_id());
			List<UserRights> result = mp.selectByExample(ex);
			return result;
		} finally {
			session.close();
		}
	}

	@Override
	public List<UserRightsEffective> getUserRightsEffective(User user) {
		SqlSession session = sessionFactory.openSession();
		try {
			UserRightsEffectiveMapper mp = session
					.getMapper(UserRightsEffectiveMapper.class);

			UserRightsEffectiveExample ex = new UserRightsEffectiveExample();
			ex.setOrderByClause("category_id,function_id");
			ex.createCriteria().andUser_idEqualTo(user.getUser_id());
			List<UserRightsEffective> result = mp.selectByExample(ex);
			return result;
		} finally {
			session.close();
		}
	}

	@Override
	public List getStudentList(int offset, int limit, boolean needTotalCounts) {
		SqlSession session = sessionFactory.openSession();
		try {
			StudentInfoMapper mp = session.getMapper(StudentInfoMapper.class);

			StudentInfoExample ex = new StudentInfoExample();
			ex.setOrderByClause("batch_owner,student_name");
			List<RecruitAgent> agents = new ArrayList<RecruitAgent>();
			int userAgentId = remoteServlet.getUser().getAgent_id();
			getChildAgents(agents, session, userAgentId);
			List<Integer> agentIds = new ArrayList<Integer>(agents.size() + 1);
			agentIds.add(userAgentId);
			for (RecruitAgent ra : agents) {
				agentIds.add(ra.getAgent_id());
			}
			ex.createCriteria().andAgent_ownerIn(agentIds);
			List result = mp.selectByExample(ex);
			if (needTotalCounts) {
				Integer counts = mp.countByExample(null);
				result.add(counts);
			}
			return result;
		} finally {
			session.close();
		}
	}

	/**
	 * 获取当前用户所有可以查看/操作的机构
	 * 
	 * @return
	 */
	public void getChildAgents(List<RecruitAgent> agents, SqlSession session,
			int agentId) {
		RecruitAgentMapper mapper = session.getMapper(RecruitAgentMapper.class);
		RecruitAgentExample ex = new RecruitAgentExample();
		RecruitAgentExample.Criteria cr = ex.createCriteria();
		cr.andParent_agent_idEqualTo(agentId);
		List<RecruitAgent> childAgents = mapper.selectByExample(ex);
		agents.addAll(childAgents);
		for (RecruitAgent ra : childAgents) {
			getChildAgents(agents, session, ra.getAgent_id());
		}
	}

	@Override
	public boolean addModel(Serializable model) {
		return modelAction(new Serializable[] { model }, "insertSelective");
	}

	@Override
	public boolean deleteModel(String modelName, String id_field_name,
			List<Integer> modelIds) {
		SqlSession session = sessionFactory.openSession();
		try {
			Class mapperClass = Class.forName(daoPackageName + modelName
					+ "Mapper");
			Class beanClass = Class.forName(modelPackageName + modelName);
			Object mapper = session.getMapper(mapperClass);
			Class exampleClass = Class.forName(modelPackageName + modelName
					+ "Example");
			Method delMethod = mapperClass.getMethod("deleteByExample",
					exampleClass);

			Object example = exampleClass.newInstance();
			Method createCriteriaMethod = ReflectUtil.getDeclaredMethod(
					exampleClass, "createCriteria");
			Object criteriaObj = createCriteriaMethod.invoke(example);
			id_field_name = id_field_name.toLowerCase();
			id_field_name = id_field_name.replaceFirst(id_field_name.substring(
					0, 1), id_field_name.substring(0, 1).toUpperCase());
			Method andIdInMethod = ReflectUtil.getDeclaredMethod(
					criteriaObj.getClass(), "and" + id_field_name + "In",
					List.class);
			andIdInMethod.invoke(criteriaObj, modelIds);
			delMethod.invoke(mapper, example);
			session.commit();
		} catch (Exception e) {
			OperatioinFailedException ex = new OperatioinFailedException(e);
			Log.e("", ex);
			throw ex;
		} finally {
			session.close();
		}
		return true;
	}

	@Override
	public boolean saveModel(Serializable model) {
		return modelAction(new Serializable[] { model }, "updateByPrimaryKey");
	}

	@Override
	public boolean modelAction(Serializable[] models, String methodName) {
		SqlSession session = sessionFactory.openSession();
		try {
			for (Serializable model : models) {
				String modelName = model.getClass().getSimpleName();
				Class mapperClass = Class.forName(daoPackageName + modelName
						+ "Mapper");
				Class beanClass = Class.forName(modelPackageName + modelName);
				Object mapper = session.getMapper(mapperClass);
				Method method = mapperClass.getMethod(methodName, beanClass);
				method.invoke(mapper, model);
				session.commit();
			}
		} catch (Exception e) {
			OperatioinFailedException ex = new OperatioinFailedException(e);
			Log.e("", ex);
			throw ex;
		} finally {
			session.close();
		}
		return true;
	}

	@Override
	public List getNameValuePareList(String[] modelNames) {
		List nameValuePares = new ArrayList();
		SqlSession session = sessionFactory.openSession();

		try {
			for (String modelName : modelNames) {

				Class mapperClass = Class.forName(daoPackageName + modelName
						+ "Mapper");
				Class modelClass = Class.forName(modelPackageName + modelName);
				Object mapper = session.getMapper(mapperClass);
				Class exampleClass = Class.forName(modelPackageName + modelName
						+ "Example");
				Method method = mapperClass.getMethod("selectByExample",
						exampleClass);

				Object example = exampleClass.newInstance();

				Field[] fields = ReflectUtil.getDeclaredFields(modelClass);
				int nameFieldIndex = -1;
				for (int i = 0; i < fields.length; i++) {
					if (fields[i].getName().toLowerCase().contains("name")) { // 一般这种表中只有一个带name的字段
						nameFieldIndex = i;
						break;
					}
				}
				List result;
				if (nameFieldIndex != -1) {
					Method setOrderByClauseMethod = exampleClass
							.getDeclaredMethod("setOrderByClause", String.class);
					setOrderByClauseMethod.invoke(example,
							fields[nameFieldIndex].getName());

					result = (List) method.invoke(mapper, example);
				} else {
					result = (List) method
							.invoke(mapper, new Object[] { null });
					Log.d("No name field for class " + modelClass.getName());
				}
				nameValuePares.add(result);
			}
		} catch (Exception e) {
			OperatioinFailedException ex = new OperatioinFailedException(e);
			Log.e("", ex);
			throw ex;
		} finally {
			session.close();
		}
		return nameValuePares;
	}

	@Override
	public boolean setOverride(RightsCategoryFunctionKey override, User user,
			boolean addOrRemove) {
		SqlSession session = sessionFactory.openSession();
		try {

			RightsDefaultMapper um = session
					.getMapper(RightsDefaultMapper.class);
			RightsDefaultExample exr = new RightsDefaultExample();
			exr.createCriteria().andUser_type_idEqualTo(user.getUser_type_id());

			List<RightsDefaultKey> rdl = um.selectByExample(exr);
			ArrayList<String> catIds = new ArrayList<String>();
			for (RightsDefaultKey rd : rdl) {
				catIds.add(rd.getCategory_id());
			}
			/* THE LOGIC */
			// check if override is in the list of the default granted function,
			// if it is
			// if it is then
			// if addOrRemove is set to be true , then we should remove any
			// record from RightsOverride related to this combination
			// if addOrRemove is set to be false, then we should add a record
			// into RightsOverride with the combination for this user

			// if it's not in default granted function, then
			// if addOrRemove is set to be true , then we should add a record
			// into RightsOverride with the combination for this user
			// if addOrRemove is set to be false, then we should remove any
			// record from RightsOverride related to this combination

			RightsCategoryFunctionMapper mp = session
					.getMapper(RightsCategoryFunctionMapper.class);

			RightsCategoryFunctionExample ex = new RightsCategoryFunctionExample();
			ex.createCriteria().andCategory_idIn(catIds)
					.andCategory_idEqualTo(override.getCategory_id())
					.andFunction_idEqualTo(override.getFunction_id());

			int count = mp.countByExample(ex);

			if (count > 0) {
				Log.d("in default granted list");
				if (addOrRemove) {
					Log.d("remove from override list");
					RightsOverride ro = new RightsOverride();
					ro.setUser_id(user.getUser_id());
					ro.setRights_category_id(override.getCategory_id());
					ro.setRights_function_id(override.getFunction_id());
					RightsOverrideMapper rom = session
							.getMapper(RightsOverrideMapper.class);

					rom.deleteByPrimaryKey(ro);
					session.commit();

				} else {
					Log.d("add into override list");
					RightsOverride ro = new RightsOverride();
					ro.setUser_id(user.getUser_id());
					ro.setRights_category_id(override.getCategory_id());
					ro.setRights_function_id(override.getFunction_id());
					ro.setOverride(false);
					RightsOverrideMapper rom = session
							.getMapper(RightsOverrideMapper.class);

					rom.insertSelective(ro);
					session.commit();

				}

			} else {
				Log.d("NOT in default granted list");
				if (addOrRemove) {
					Log.d("add into override list");
					RightsOverride ro = new RightsOverride();
					ro.setUser_id(user.getUser_id());
					ro.setRights_category_id(override.getCategory_id());
					ro.setRights_function_id(override.getFunction_id());
					ro.setOverride(true);
					RightsOverrideMapper rom = session
							.getMapper(RightsOverrideMapper.class);

					rom.insertSelective(ro);
					session.commit();

				} else {
					Log.d("remove from override list");
					RightsOverride ro = new RightsOverride();
					ro.setUser_id(user.getUser_id());
					ro.setRights_category_id(override.getCategory_id());
					ro.setRights_function_id(override.getFunction_id());
					RightsOverrideMapper rom = session
							.getMapper(RightsOverrideMapper.class);

					rom.deleteByPrimaryKey(ro);
					session.commit();
				}

			}

			// session.commit();
			return true;

		} finally {
			session.close();
		}

	}

	/************************ 入学费用设置 *********************************/
	@Override
	public List<EntranceCost> getEntranceCost(String batchID, String agentID) {
		SqlSession session = sessionFactory.openSession();
		try {
			EntranceCostMapper bim = session
					.getMapper(EntranceCostMapper.class);
			EntranceCostExample ex = new EntranceCostExample();

			ex.createCriteria().andBatch_idEqualTo(new Integer(batchID));
			if (agentID != null) {
				ex.createCriteria().andAgent_idEqualTo(new Integer(agentID));
			}
			return bim.selectByExample(ex);

		} finally {
			session.close();
		}
	}

	@Override
	public boolean saveEntranceCost(List<EntranceCost> entranceCosts) {
		SqlSession session = sessionFactory.openSession();
		try {
			EntranceCostMapper bim = session
					.getMapper(EntranceCostMapper.class);
			for (EntranceCost cost : entranceCosts) {
				// remove existing
				EntranceCostExample ex = new EntranceCostExample();
				ex.createCriteria().andAgent_idEqualTo(cost.getAgent_id())
						.andBatch_idEqualTo(cost.getBatch_id())
						.andCollege_idEqualTo(cost.getCollege_id())
						.andClassified_idEqualTo(cost.getCollege_id())
						.andSubject_idEqualTo(cost.getSubject_id())
						.andFee_idEqualTo(cost.getFee_id());

				bim.deleteByExample(ex);

				bim.insertSelective(cost);

			}
			session.commit();
			return true;

		} finally {
			session.close();
		}
	}

	@Override
	public boolean deleteEntranceCost(EntranceCost cost) {
		SqlSession session = sessionFactory.openSession();
		try {
			EntranceCostMapper bim = session
					.getMapper(EntranceCostMapper.class);

			bim.deleteByPrimaryKey(cost);

			session.commit();
			return true;

		} finally {
			session.close();
		}
	}

	/************************ 招生点返利设置 *********************************/
	@Override
	public AgentReturnType addAgentReturnType(AgentReturnType type) {
		SqlSession session = sessionFactory.openSession();
		try {
			AgentReturnTypeMapper bim = session
					.getMapper(AgentReturnTypeMapper.class);

			bim.insertSelective(type);

			session.commit();

			AgentReturnTypeExample ex = new AgentReturnTypeExample();
			ex.createCriteria().andAggregation_descEqualTo(
					type.getAggregation_desc());
			return bim.selectByExample(ex).get(0);
			// return true;

		} finally {
			session.close();
		}

	}

	@Override
	public boolean deleteAgentReturnType(AgentReturnType type) {
		SqlSession session = sessionFactory.openSession();
		try {
			AgentReturnTypeMapper bim = session
					.getMapper(AgentReturnTypeMapper.class);

			bim.deleteByPrimaryKey(type.getAg_return_type_id());

			session.commit();

			return true;

		} finally {
			session.close();
		}
	}

	@Override
	public boolean addAgentReturn(AgentReturnKey rtn) {
		SqlSession session = sessionFactory.openSession();
		try {
			AgentReturnMapper bim = session.getMapper(AgentReturnMapper.class);

			bim.insertSelective(rtn);

			session.commit();

			return true;

		} finally {
			session.close();
		}
	}

	@Override
	public boolean deleteAgentReturn(AgentReturnKey rtn) {
		SqlSession session = sessionFactory.openSession();
		try {
			AgentReturnMapper bim = session.getMapper(AgentReturnMapper.class);

			bim.deleteByPrimaryKey(rtn);

			session.commit();

			return true;

		} finally {
			session.close();
		}
	}

	@Override
	public boolean addCollegeAggregation(List<CollegeAggregation> list) {
		SqlSession session = sessionFactory.openSession();
		try {
			CollegeAggregationMapper bim = session
					.getMapper(CollegeAggregationMapper.class);

			for (CollegeAggregation item : list) {
				bim.insertSelective(item);
			}

			session.commit();

			return true;

		} finally {
			session.close();
		}
	}

	@Override
	public boolean deleteCollegeAggregation(CollegeAggregation item) {
		SqlSession session = sessionFactory.openSession();
		try {
			CollegeAggregationMapper bim = session
					.getMapper(CollegeAggregationMapper.class);

			bim.deleteByPrimaryKey(item);

			session.commit();

			return true;

		} finally {
			session.close();
		}
	}

	@Override
	public boolean saveCollegeAggregation(CollegeAggregation item) {
		SqlSession session = sessionFactory.openSession();
		try {
			CollegeAggregationMapper bim = session
					.getMapper(CollegeAggregationMapper.class);

			bim.updateByPrimaryKeySelective(item);

			session.commit();

			return true;

		} finally {
			session.close();
		}
	}

	@Override
	public List<College> getUnassignedCollegeList(String agentID, String batch) {
		SqlSession session = sessionFactory.openSession();
		try {

			AgentReturnMapper arm = session.getMapper(AgentReturnMapper.class);

			AgentReturnExample are = new AgentReturnExample();
			are.createCriteria().andAgent_idEqualTo(new Integer(agentID))
					.andBatch_idEqualTo(new Integer(batch));
			if (arm.selectByExample(are).size() == 0)
				return getCollegeList(-1, -1, false);
			ArrayList<Integer> agentRetrunTypeID = new ArrayList<Integer>();
			for (AgentReturnKey ark : arm.selectByExample(are)) {
				agentRetrunTypeID.add(ark.getAg_return_type_id());
			}

			CollegeAggregationMapper bim = session
					.getMapper(CollegeAggregationMapper.class);

			CollegeAggregationExample ex = new CollegeAggregationExample();
			ex.createCriteria().andAg_return_type_idIn(agentRetrunTypeID);
			ArrayList<Integer> collegeID = new ArrayList<Integer>();
			for (CollegeAggregation ca : bim.selectByExample(ex)) {
				collegeID.add(ca.getCollege_id());
			}

			CollegeMapper cm = session.getMapper(CollegeMapper.class);
			CollegeExample ce = new CollegeExample();
			ce.createCriteria().andCollege_idNotIn(collegeID);

			return cm.selectByExample(ce);

		} finally {
			session.close();
		}
	}

	@Override
	public List<CollegeAggregation> getCollegeAggregationList(
			AgentReturnType type) {
		SqlSession session = sessionFactory.openSession();
		try {
			CollegeAggregationMapper bim = session
					.getMapper(CollegeAggregationMapper.class);
			CollegeAggregationExample ex = new CollegeAggregationExample();
			ex.createCriteria().andAg_return_type_idEqualTo(
					type.getAg_return_type_id());

			return bim.selectByExample(ex);

		} finally {
			session.close();
		}
	}

	@Override
	public List<AgentReturnType> getAgentReturnType(String agentID, String batch) {
		SqlSession session = sessionFactory.openSession();
		try {

			AgentReturnMapper arm = session.getMapper(AgentReturnMapper.class);

			AgentReturnExample are = new AgentReturnExample();
			are.createCriteria().andAgent_idEqualTo(new Integer(agentID))
					.andBatch_idEqualTo(new Integer(batch));

			if (arm.selectByExample(are).size() == 0)
				return new ArrayList<AgentReturnType>();

			ArrayList<Integer> agentRetrunTypeID = new ArrayList<Integer>();
			for (AgentReturnKey ark : arm.selectByExample(are)) {
				agentRetrunTypeID.add(ark.getAg_return_type_id());
			}

			AgentReturnTypeMapper bim = session
					.getMapper(AgentReturnTypeMapper.class);

			AgentReturnTypeExample ex = new AgentReturnTypeExample();
			ex.createCriteria().andAg_return_type_idIn(agentRetrunTypeID);

			return bim.selectByExample(ex);

		} finally {
			session.close();
		}
	}

	@Override
	public boolean checkIfLastCollegeAggregation(CollegeAggregation item) {
		SqlSession session = sessionFactory.openSession();
		try {
			CollegeAggregationMapper bim = session
					.getMapper(CollegeAggregationMapper.class);
			CollegeAggregationExample cae = new CollegeAggregationExample();
			cae.createCriteria()
					.andAg_return_type_idEqualTo(item.getAg_return_type_id())
					.andCollege_idEqualTo(item.getCollege_id());

			int count = bim.countByExample(cae);

			if (count > 1)
				return false;

			return true;

		} finally {
			session.close();
		}
	}

	/************************** 站内用户短信 ******************************/

	@Override
	public boolean sendMessage(List<Messages> messages) {
		SqlSession session = sessionFactory.openSession();
		try {
			Date timestamp = new Date();
			long maxId = (long) Utils.getNextId(session, "messages",
					"message_id", messages.size());
			for (int i = 0; i < messages.size(); i++) {
				messages.get(i).setReceive_tstp(timestamp);
				messages.get(i).setMessage_id(maxId--);
			}
			MessagesMapper bim = session.getMapper(MessagesMapper.class);
			for (Messages mess : messages) {
				bim.insertSelective(mess);
			}
			session.commit();
			MessageService.enqueue(messages);
			return true;

		} finally {
			session.close();
		}
	}

	@Override
	public List<MessageReal> readMessage(User user) {
		SqlSession session = sessionFactory.openSession();
		try {

			// get list of unread mesages

			MessageRealMapper bim = session.getMapper(MessageRealMapper.class);

			MessageRealExample ex = new MessageRealExample();
			ex.setOrderByClause("message_id desc");
			if (user != null) {
				ex.createCriteria().andTo_userEqualTo(user.getUser_id());
			}
			List<MessageReal> list = bim.selectByExample(ex);
			return list;
		} finally {
			session.close();
		}
	}

	@Override
	public boolean markAsRead(MessageReal message) {

		SqlSession session = sessionFactory.openSession();
		try {

			// update unread messages to read
			MessagesMapper mm = session.getMapper(MessagesMapper.class);
			MessagesExample me = new MessagesExample();
			me.createCriteria().andMessage_idEqualTo(message.getMessage_id());
			Messages rec = new Messages();
			rec.setIs_read(true);
			rec.setRead_tstp(new Date());
			mm.updateByExampleSelective(rec, me);
			session.commit();
			return true;
		} finally {
			session.close();
		}
	}

	@Override
	public boolean checkNewMessages(User user) {
		SqlSession session = sessionFactory.openSession();
		try {
			// get count of unread mesages
			MessageRealMapper bim = session.getMapper(MessageRealMapper.class);

			MessageRealExample ex = new MessageRealExample();
			ex.createCriteria().andTo_userEqualTo(user.getUser_id());
			int count = bim.countByExample(ex);

			if (count > 0)
				return true;

			return false;

		} finally {
			session.close();
		}
	}

	/************************** 通知通告管理 ******************************/
	@Override
	public boolean addAnnouncement(Announcement ann) {
		SqlSession session = sessionFactory.openSession();
		try {

			AnnouncementMapper map = session
					.getMapper(AnnouncementMapper.class);
			map.insertSelective(ann);
			session.commit();
			return true;

		} finally {
			session.close();
		}
	}

	@Override
	public boolean deleteAnnouncement(List<Integer> annIDs) {
		SqlSession session = sessionFactory.openSession();
		try {

			AnnouncementMapper map = session
					.getMapper(AnnouncementMapper.class);
			for (Integer annId : annIDs) {
				map.deleteByPrimaryKey(annId);
			}

			session.commit();
			return true;

		} finally {
			session.close();
		}
	}

	@Override
	public boolean saveAnnouncement(Announcement ann) {
		SqlSession session = sessionFactory.openSession();
		try {

			AnnouncementMapper map = session
					.getMapper(AnnouncementMapper.class);
			map.updateByPrimaryKeySelective(ann);
			session.commit();
			return true;

		} finally {
			session.close();
		}
	}

	@Override
	public List<Announcement> getAnnouncementList(int offset, int limit,
			boolean needTotalCounts) {
		SqlSession session = sessionFactory.openSession();
		try {
			AnnouncementExample ex = new AnnouncementExample();
			if (offset != -1 || limit != -1) {
				ex.setPage(new Page(offset, limit));
			}
			ex.setOrderByClause("ann_id desc");

			AnnouncementMapper map = session
					.getMapper(AnnouncementMapper.class);
			List result = map.selectByExample(ex);
			if (needTotalCounts) {
				Integer counts = map.countByExample(null);
				result.add(counts);
			}
			return result;
		} finally {
			session.close();
		}
	}

	@Override
	public boolean addStudent(StudentInfo stu, List<StudentPicture> pictures) {
		SqlSession session = sessionFactory.openSession();
		try {
			int stu_id = Utils.getNextId(session, "student_info", "student_id");
			stu.setStudent_id(stu_id);
			stu.setRegister_date(new Date());
			StudentInfoMapper map = session.getMapper(StudentInfoMapper.class);
			map.insertSelective(stu);
			StudentPictureMapper picMap = session
					.getMapper(StudentPictureMapper.class);
			StudentFileHandler fileHandler = new StudentFileHandler(stu,
					pictures);
			boolean result = fileHandler.movePictrues();
			for (StudentPicture p : pictures) {
				p.setStudent_id(stu_id);
				picMap.insertSelective(p);
			}
			
			if (result)
				session.commit();
			else
				session.rollback();
			return result;
		} finally {
			session.close();
		}
	}

	@Override
	public List<StudentPicture> getPictures(int student_id) {
		SqlSession session = sessionFactory.openSession();
		try {
			StudentPictureExample ex = new StudentPictureExample();
			ex.createCriteria().andStudent_idEqualTo(student_id);

			StudentPictureMapper map = session
					.getMapper(StudentPictureMapper.class);
			List<StudentPicture> result = map.selectByExample(ex);
			return result;
		} finally {
			session.close();
		}
	}
	
	@Override
	public boolean saveStudent(StudentInfo stu, List<StudentPicture> pictures) {
		SqlSession session = sessionFactory.openSession();
		try {
			stu.setUpdate_date(new Date());
			StudentInfoMapper map = session.getMapper(StudentInfoMapper.class);
			map.updateByPrimaryKey(stu);
			StudentPictureMapper picMap = session
					.getMapper(StudentPictureMapper.class);
			for (StudentPicture p : pictures) {
				picMap.updateByPrimaryKey(p);
			}
			StudentFileHandler fileHandler = new StudentFileHandler(stu,
					pictures);
			boolean result = fileHandler.movePictrues();
			if (result)
				session.commit();
			else
				session.rollback();
			return result;
		} finally {
			session.close();
		}
	}

}
