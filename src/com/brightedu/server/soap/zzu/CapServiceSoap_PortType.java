/**
 * CapServiceSoap_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package com.brightedu.server.soap.zzu;

public interface CapServiceSoap_PortType extends java.rmi.Remote {

    /**
     * Web 服务提供的方法，添加报名信息。
     */
    public boolean addExaminee(com.brightedu.server.soap.zzu.holders.ClientexamineeHolder e, java.lang.String stationstr, java.lang.String gid, javax.xml.rpc.holders.StringHolder msg) throws java.rmi.RemoteException;

    /**
     * Web 服务提供的方法，修改报名基本信息。
     */
    public boolean updateExaminee(com.brightedu.server.soap.zzu.Clientexaminee e, java.lang.String gid, javax.xml.rpc.holders.StringHolder msg) throws java.rmi.RemoteException;

    /**
     * Web 服务提供的方法，修改专业信息，须变动准考证号。
     */
    public boolean modifiedMajor(com.brightedu.server.soap.zzu.Clientexaminee e, java.lang.String gid, javax.xml.rpc.holders.StringHolder msg) throws java.rmi.RemoteException;

    /**
     * Web 服务提供的方法，删除报名信息。
     */
    public java.lang.String delExam(java.lang.String bmh, java.lang.String gid) throws java.rmi.RemoteException;

    /**
     * Web 服务提供的方法，实例化报名对象。
     */
    public com.brightedu.server.soap.zzu.Clientexaminee getExaminee(java.lang.String bmh, java.lang.String gid) throws java.rmi.RemoteException;

    /**
     * Web 服务提供的方法，获取报名列表。
     */
    public com.brightedu.server.soap.zzu.ArrayOfAnyType getExamineeList(java.lang.String zkzh, java.lang.String xm, java.lang.String stationno, java.lang.String zyh, java.lang.String gid) throws java.rmi.RemoteException;

    /**
     * Web 服务提供的方法，验证客户端账号。
     */
    public boolean autoLogin(java.lang.String userid, java.lang.String password, javax.xml.rpc.holders.StringHolder stationno, java.lang.String gid, javax.xml.rpc.holders.StringHolder msg, javax.xml.rpc.holders.StringHolder stationname) throws java.rmi.RemoteException;

    /**
     * Web 服务提供的方法，返回是否文件上载成功与否。
     */
    public boolean tongBuUploadFile(byte[] fs, java.lang.String filename, java.lang.String gid, javax.xml.rpc.holders.StringHolder msg, java.lang.String stationno) throws java.rmi.RemoteException;

    /**
     * Web 服务提供的方法，返回是否文件上载成功与否。
     */
    public boolean uploadFile(byte[] fs, javax.xml.rpc.holders.StringHolder filename, java.lang.String gid, javax.xml.rpc.holders.StringHolder msg, java.lang.String stationno, java.lang.String file_type) throws java.rmi.RemoteException;

    /**
     * Web 服务提供的方法，返回软件版本。
     */
    public double getVersion(java.lang.String gid) throws java.rmi.RemoteException;

    /**
     * Web 服务提供的方法，返回考试是否结束的状态，以次确定是否显示录入考场考号。为真为考前录入环节
     */
    public boolean getStatus(java.lang.String gid) throws java.rmi.RemoteException;

    /**
     * Web 服务提供的方法，返回该学习中心所属管理中心能招生的专业列表
     */
    public com.brightedu.server.soap.zzu.ArrayOfString achiveMajors(java.lang.String gid, java.lang.String stationno) throws java.rmi.RemoteException;
}
