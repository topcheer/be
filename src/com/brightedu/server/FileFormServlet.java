package com.brightedu.server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;

import com.brightedu.dao.edu.CollegeAgreementMapper;
import com.brightedu.model.edu.CollegeAgreement;
import com.brightedu.server.util.Log;
import com.brightedu.server.util.ServerProperties;
import com.brightedu.server.util.Utils;

/**
 * @author chetwang, 主要处理文件上传的form
 * 
 */
public class FileFormServlet extends BrightServlet {

	private String agreementSubDir;
	private String studentPicDir;
	private String tempFileRelative = ServerProperties.getDataLocation()
			+ "/tmp/";
	private String tempFileDir;

	public void init() {
		super.init();
		agreementSubDir = new File(ServerProperties.getDataLocation())
				.getAbsolutePath() + "/agreement/";
		File agreementsDir = new File(agreementSubDir);
		if (!agreementsDir.exists()) {
			agreementsDir.mkdirs();
		}
		studentPicDir = new File(ServerProperties.getDataLocation())
				.getAbsolutePath() + "/student_pics/";
		File sdutentPicFile = new File(studentPicDir);
		if (!sdutentPicFile.exists()) {
			sdutentPicFile.mkdirs();
		}
		tempFileDir = new File(tempFileRelative).getAbsolutePath();
		File tempFile = new File(tempFileDir);
		if (!tempFile.exists()) {
			tempFile.mkdirs();
		}
		
	}

	public void processPost(HttpServletRequest request,
			HttpServletResponse response) {
		process(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		process(request, response);
	}

	private void process(HttpServletRequest request,
			HttpServletResponse response) {

		try {
			String actionType = request.getParameter("action");
			if (actionType != null && !actionType.trim().equals("")) {
				actionType = actionType.toLowerCase();
				if (actionType.equals("getcollegeagreement")) {
					getCollegeAgreement(request, response);
				} else if (actionType.equals("addcollegeagreement")) {
					addCollegeAgreement(request, response);
				} else if (actionType.equals("updatecollegeagreement")) {
					updateCollegeAgreementFile(request, response);
				} else if (actionType.equals("pic_upload")) {
					addPicture(request, response);
				} else {
					Log.e("Undefied action for FileFormServlet: " + actionType);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void addPicture(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			ServletFileUpload upload = new ServletFileUpload();
			FileItemIterator iter = upload.getItemIterator(request);
			// pick up parameters first and note actual FileItem

			while (iter.hasNext()) {
				FileItemStream item = iter.next();
				String name = item.getFieldName();
				if (item.isFormField()) {
					Log.w("no definition for form field " + name);
				} else {
					String serverFileName = createTempFile(item, response);
					if (serverFileName == null) {
						response(response, "保存文件失败", false);
						return;
					} else {
						response(response, tempFileRelative + serverFileName,
								true);
					}
				}
			}
		} catch (Exception e) {
			Log.e(e.getMessage(), e);
			try {
				response(response, e.getMessage(), false);
			} catch (IOException e1) {
				Log.e(e1.getMessage(), e1);
			}
		}

	}

	private void updateCollegeAgreementFile(HttpServletRequest request,
			HttpServletResponse response) throws IOException,
			FileUploadException {
		int agreementId = Integer.parseInt(request.getParameter("id"));
		CollegeAgreement agreement = (CollegeAgreement) agent.getObjectById(
				CollegeAgreementMapper.class.getName(), agreementId);
		// System.out.println(agreement.getAgreement_name());
		ServletFileUpload upload = new ServletFileUpload();
		FileItemIterator iter = upload.getItemIterator(request);
		// pick up parameters first and note actual FileItem
		agreement.setUser_id(getUser().getUser_id());
		Date now = new Date();
		agreement.setUpdate_date(now);
		agreement.setRegister_date(now);
		while (iter.hasNext()) {
			FileItemStream item = iter.next();
			if (!item.isFormField()) {
				String serverFileName = createAgreementFile(item, response,
						agreement.getAgent_id(), now);
				if (serverFileName == null) {
					response(response, "更新文件失败", false);
					return;
				} else {
					agreement.setAgreement_name(serverFileName);
					agent.saveCollegeAgreement(agreement);
					response(response, serverFileName, true);
				}
			}
		}

	}

	private void getCollegeAgreement(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String agreement_filename = request.getParameter("agreement_name"); // 带日期标签
		agreement_filename = URLDecoder.decode(agreement_filename, "UTF-8");// 这个是GWT
																			// URL
																			// encode的编码
		String responseFileName = agreement_filename.substring(0,
				agreement_filename.lastIndexOf("."));
		String respContentType = agreement_filename
				.substring(agreement_filename.lastIndexOf("-") + 1);
		File serverAgreementFile = new File(agreementSubDir
				+ agreement_filename);
		Log.d("Server side file: " + serverAgreementFile.getAbsolutePath());
		respContentType = decodeContentTypeForURL(respContentType);
		// response.setHeader("Content-Type", respContentType + ";charset="
		// + ServerProperties.getLocalEncoding());
		// response.setCharacterEncoding(ServerProperties.getServletEncoding());
		response.setHeader("Content-Type", respContentType);
		Log.d("respContentType: " + respContentType);
		response.setHeader("Content-Length",
				String.valueOf(serverAgreementFile.length()));
		if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0)

			responseFileName = URLEncoder.encode(responseFileName, "UTF-8");// IE浏览器
		else {
			responseFileName = new String(responseFileName.getBytes("UTF-8"),
					"ISO8859-1");
		}
		response.setHeader("Content-disposition", "attachment;filename=\""
				+ responseFileName + "\"");
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(
				serverAgreementFile));
		BufferedOutputStream bos = new BufferedOutputStream(
				response.getOutputStream());
		byte[] buf = new byte[1024];
		while (true) {
			int length = bis.read(buf);
			if (length == -1)
				break;
			bos.write(buf, 0, length);
		}
		bos.flush();
		bos.close();
		bis.close();
	}

	private void addCollegeAgreement(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			ServletFileUpload upload = new ServletFileUpload();
			FileItemIterator iter = upload.getItemIterator(request);
			// pick up parameters first and note actual FileItem
			CollegeAgreement agreement = new CollegeAgreement();
			agreement.setUser_id(getUser().getUser_id());
			Date now = new Date();
			agreement.setUpdate_date(now);
			agreement.setRegister_date(now);
			agreement.setAgreement_id(Utils.getNextId(null,
					"college_agreement", "agreement_id"));
			while (iter.hasNext()) {
				FileItemStream item = iter.next();
				String name = item.getFieldName();
				if (item.isFormField()) {
					if (name.equals("college")) {
						agreement.setCollege_id(Integer.parseInt(Streams
								.asString(item.openStream())));
					} else if (name.equals("agent")) {
						agreement.setAgent_id(Integer.parseInt(Streams
								.asString(item.openStream())));
					} else if (name.equals("status")) {
						String status = (Streams.asString(item.openStream()));
						agreement.setAgreement_status(status.equals("1"));
					} else {
						Log.w("Unknown param form field: " + name);
					}
				} else {
					String serverFileName = createAgreementFile(item, response,
							agreement.getAgreement_id(), now);
					if (serverFileName == null) {
						response(response, "保存文件失败", false);
						return;
					}
					agreement.setAgreement_name(serverFileName);
				}
			}

			agent.addModel(agreement);

			response(response, "", true);
		} catch (Exception e) {
			Log.e(e.getMessage(), e);
			try {
				response(response, e.getMessage(), false);
			} catch (IOException e1) {
				Log.e(e1.getMessage(), e1);
			}
		}
	}

	private String createAgreementFile(FileItemStream item,
			HttpServletResponse response, int objectRelatedId, Date now)
			throws IOException {
		InputStream in = null;
		String serverFileName = null;
		try {
			in = item.openStream();
			String fileName = item.getName();
			String type = item.getContentType();
			int slash = fileName.lastIndexOf("/");
			if (slash < 0)
				slash = fileName.lastIndexOf("\\");
			if (slash > 0)
				fileName = fileName.substring(slash + 1);

			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");

			serverFileName = fileName + "." + objectRelatedId + "_"
					+ sdf.format(now) + "-" + encodeContentTypeForURL(type);
			File agreementFile = new File(agreementSubDir + serverFileName);

			RandomAccessFile raf = new RandomAccessFile(agreementFile, "rw");
			if (in != null) {
				byte[] buff = new byte[1024];
				int readLen = 0;
				while ((readLen = in.read(buff)) > 0) {
					raf.write(buff, 0, readLen);
				}
				raf.close();
				Log.i("stored agreement in " + agreementFile.getAbsolutePath());
			} else {
				Log.e("no inputsteam created for " + fileName);
			}

		} catch (Exception e) {
			Log.e("", e);
			response(response, e.getMessage(), false);

		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (Exception e) {
					Log.e("", e);
				}
			}
		}
		return serverFileName;
	}

	private String createTempFile(FileItemStream item,
			HttpServletResponse response) throws IOException {
		InputStream in = null;
		String serverFileName = null;
		try {
			in = item.openStream();
			String fileName = item.getName();
			UUID uuid = UUID.randomUUID();
			serverFileName = uuid.toString() + fileName;
			File tempFile = new File(tempFileDir + serverFileName);
			RandomAccessFile raf = new RandomAccessFile(tempFile, "rw");
			if (in != null) {
				byte[] buff = new byte[1024];
				int readLen = 0;
				while ((readLen = in.read(buff)) > 0) {
					raf.write(buff, 0, readLen);
				}
				raf.close();
				Log.i("stored temp file in " + tempFile.getAbsolutePath());
			} else {
				Log.e("no inputsteam created for " + fileName);
			}

		} catch (IOException e) {
			Log.e("", e);
			response(response, e.getMessage(), false);
			return null;
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (Exception e) {
					Log.e("", e);
				}
			}
		}
		return serverFileName;
	}

	private void response(HttpServletResponse response, String msg,
			boolean success) throws IOException {
		PrintWriter out = response.getWriter();
		out.println("<html><head><title>");
		out.println(success ? "success" : "failed");
		out.println("</title></head><body>");
		out.println(msg);
		out.println("</body></html>");
		out.flush();
	}

	private String encodeContentTypeForURL(String realContentType) {
		return realContentType.replace("/", "XYAZ").replace(".", "ABXC")
				.replace("-", "UYXT");
	}

	private String decodeContentTypeForURL(String encodedContentType) {
		return encodedContentType.replace("XYAZ", "/").replace("ABXC", ".")
				.replace("UYXT", "-");
	}
}