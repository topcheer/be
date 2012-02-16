package com.brightedu.server;

import java.lang.reflect.Proxy;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.brightedu.client.DataBaseRPC;
import com.brightedu.client.ds.Page;
import com.brightedu.dao.edu.AgentTypeMapper;
import com.brightedu.dao.edu.BatchIndexMapper;
import com.brightedu.dao.edu.ChargeTypeMapper;
import com.brightedu.dao.edu.CollegeMapper;
import com.brightedu.dao.edu.CollegeSubjectMapper;
import com.brightedu.dao.edu.CollegeSubjectViewMapper;
import com.brightedu.dao.edu.FeeTypeMapper;
import com.brightedu.dao.edu.PictureTypeMapper;
import com.brightedu.dao.edu.RecruitAgentMapper;
import com.brightedu.dao.edu.StudentClassifiedMapper;
import com.brightedu.dao.edu.StudentStatusMapper;
import com.brightedu.dao.edu.StudentTypeMapper;
import com.brightedu.dao.edu.SubjectsMapper;
import com.brightedu.dao.edu.UserTypeMapper;
import com.brightedu.model.edu.AgentType;
import com.brightedu.model.edu.AgentTypeExample;
import com.brightedu.model.edu.BatchIndex;
import com.brightedu.model.edu.BatchIndexExample;
import com.brightedu.model.edu.BatchIndexExample.Criteria;
import com.brightedu.model.edu.ChargeType;
import com.brightedu.model.edu.ChargeTypeExample;
import com.brightedu.model.edu.College;
import com.brightedu.model.edu.CollegeAgreement;
import com.brightedu.model.edu.CollegeExample;
import com.brightedu.model.edu.CollegeSubject;
import com.brightedu.model.edu.CollegeSubjectExample;
import com.brightedu.model.edu.CollegeSubjectView;
import com.brightedu.model.edu.CollegeSubjectViewExample;
import com.brightedu.model.edu.FeeType;
import com.brightedu.model.edu.FeeTypeExample;
import com.brightedu.model.edu.PictureType;
import com.brightedu.model.edu.PictureTypeExample;
import com.brightedu.model.edu.RecruitAgent;
import com.brightedu.model.edu.RecruitAgentExample;
import com.brightedu.model.edu.StudentClassified;
import com.brightedu.model.edu.StudentClassifiedExample;
import com.brightedu.model.edu.StudentStatus;
import com.brightedu.model.edu.StudentStatusExample;
import com.brightedu.model.edu.StudentType;
import com.brightedu.model.edu.StudentTypeExample;
import com.brightedu.model.edu.Subjects;
import com.brightedu.model.edu.SubjectsExample;
import com.brightedu.model.edu.User;
import com.brightedu.model.edu.UserType;
import com.brightedu.model.edu.UserTypeExample;
import com.brightedu.server.util.ConnectionManager;

public class DataBaseRPCAgent implements DataBaseRPC {
	SqlSessionFactory sessionFactory;
	BrightServlet remoteServlet;

	public DataBaseRPCAgent() {
		sessionFactory = ConnectionManager.getSessionFactory();
	}
	
	public static DataBaseRPC createAgentProxy(BrightServlet servlet){
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
	public boolean addBatch(String batch_name) {
		SqlSession session = sessionFactory.openSession();
		try {
			BatchIndexMapper bim = session.getMapper(BatchIndexMapper.class);
			BatchIndex bi = new BatchIndex();
			bi.setBatch_name(batch_name);
			int count = bim.insertSelective(bi);
			session.commit();
			return true;
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
	public boolean addStudentClass(StudentClassified studentClass) {
		SqlSession session = sessionFactory.openSession();
		try {
			StudentClassifiedMapper scm = session
					.getMapper(StudentClassifiedMapper.class);
			int count = scm.insertSelective(studentClass);
			session.commit();
			return true;
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
	public boolean addStudentType(String studentTypeName) {
		SqlSession session = sessionFactory.openSession();
		try {
			StudentTypeMapper scm = session.getMapper(StudentTypeMapper.class);
			StudentType st = new StudentType();
			st.setStudent_type_name(studentTypeName);
			int count = scm.insertSelective(st);
			session.commit();
			return true;
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
	public boolean addCollege(String collegeName) {
		SqlSession session = sessionFactory.openSession();
		try {
			CollegeMapper cm = session.getMapper(CollegeMapper.class);
			College co = new College();
			co.setCollege_name(collegeName);

			int count = cm.insertSelective(co);
			session.commit();
			return true;
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
	public boolean addSubject(String subjectName) {
		SqlSession session = sessionFactory.openSession();
		try {
			SubjectsMapper cm = session.getMapper(SubjectsMapper.class);
			Subjects co = new Subjects();
			co.setSubject_name(subjectName);
			int count = cm.insertSelective(co);
			session.commit();
			return true;
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
	public boolean addAgentType(AgentType agentType) {
		SqlSession session = sessionFactory.openSession();
		try {
			AgentTypeExample ex = new AgentTypeExample();
			Page p = new Page();
			AgentTypeMapper cm = session.getMapper(AgentTypeMapper.class);
			int count = cm.insertSelective(agentType);
			session.commit();
			return true;
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
	public boolean addFeeType(FeeType feetype) {
		SqlSession session = sessionFactory.openSession();
		try {
			FeeTypeMapper scm = session.getMapper(FeeTypeMapper.class);
			int count = scm.insertSelective(feetype);
			session.commit();
			return true;
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
	public boolean addChargeType(String typeName) {
		SqlSession session = sessionFactory.openSession();
		try {
			ChargeTypeMapper scm = session.getMapper(ChargeTypeMapper.class);
			ChargeType sc = new ChargeType();
			sc.setCharge_type_name(typeName);
			int count = scm.insertSelective(sc);
			session.commit();
			return true;
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
	public boolean addUserType(String typeName) {
		SqlSession session = sessionFactory.openSession();
		try {
			UserTypeMapper scm = session.getMapper(UserTypeMapper.class);
			UserType sc = new UserType();
			sc.setUser_type_name(typeName);
			int count = scm.insertSelective(sc);
			session.commit();
			return true;
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
	public boolean addPictureType(String typeName) {
		SqlSession session = sessionFactory.openSession();
		try {
			PictureTypeMapper scm = session.getMapper(PictureTypeMapper.class);
			PictureType sc = new PictureType();
			sc.setPic_type_name(typeName);
			int count = scm.insertSelective(sc);
			session.commit();
			return true;
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
	public boolean addStudentStatus(String typeName) {
		SqlSession session = sessionFactory.openSession();
		try {
			StudentStatusMapper scm = session
					.getMapper(StudentStatusMapper.class);
			StudentStatus sc = new StudentStatus();
			sc.setStu_status_name(typeName);
			int count = scm.insertSelective(sc);
			session.commit();
			return true;
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
			boolean needTotalCounts) {
		SqlSession session = sessionFactory.openSession();
		try {
			RecruitAgentExample ex = new RecruitAgentExample();
			ex.setOrderByClause("agent_id desc");

			RecruitAgentMapper cm = session.getMapper(RecruitAgentMapper.class);
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
	public boolean addRecruitAgent(RecruitAgent agent) {
		SqlSession session = sessionFactory.openSession();
		User user = (User) remoteServlet.getUser();
		agent.setUser_id(user.getUser_id());
		try {
			RecruitAgentMapper mp = session.getMapper(RecruitAgentMapper.class);
			int i = mp.insertSelective(agent);
			session.commit();
			return true;
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

	/*********************** 合作高校协议维护 ************************************/

	@Override
	public List<CollegeAgreement> getCollegeAgreementList(int offset, int limit,
			boolean needTotalCounts) {
		return null;
	}

	@Override
	public boolean addCollegeAgreement(CollegeAgreement agreement) {
		return false;
	}

	@Override
	public boolean deleteCollegeAgreement(List<Integer> agreement_ids) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean saveCollegeAgreement(CollegeAgreement agreement) {
		// TODO Auto-generated method stub
		return false;
	}
}
