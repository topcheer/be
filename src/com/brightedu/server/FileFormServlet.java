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
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;

import com.brightedu.model.edu.CollegeAgreement;
import com.brightedu.server.util.Log;
import com.brightedu.server.util.ServerProperties;
import com.google.gwt.http.client.URL;

/**
 * @author chetwang, 主要处理文件上传的form
 * 
 */
public class FileFormServlet extends BrightServlet {

	private String agreementSubDir;

	public void init() {
		super.init();
		agreementSubDir = new File(ServerProperties.getDataLocation())
				.getAbsolutePath() + "/agreement/";
		File agreementsDir = new File(agreementSubDir);
		if (!agreementsDir.exists()) {
			agreementsDir.mkdirs();
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
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			if (ServletFileUpload.isMultipartContent(request)) {
				processFiles(request, response);
			} else {
				processQuery(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void processQuery(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String actionType = request.getParameter("action");
		if (actionType != null && !actionType.trim().equals("")) {
			if (actionType.toLowerCase().equals("getcollegeagreement")) {
				getCollegeAgreement(request, response);
			} else {
				Log.e("Undefied action for FileFormServlet: " + actionType);
			}
		}
	}

	private void processFiles(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String actionType = request.getParameter("action");
		if (actionType != null && !actionType.trim().equals("")) {
			if (actionType.toLowerCase().equals("addcollegeagreement")) {
				addCollegeAgreement(request, response);
			} else {
				Log.e("Undefied action for FileFormServlet: " + actionType);
			}
		} else {
			Log.e("empty action type for FileFormServlet");
		}
	}

	private void getCollegeAgreement(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String agreement_filename = request.getParameter("agreement_name"); // 带日期标签
		agreement_filename = URLDecoder.decode(agreement_filename,"UTF-8");
		// String reqEncoding = request.getCharacterEncoding();
		// Log.d("RequestEncoding: "+reqEncoding);
		 agreement_filename = new String(
		 agreement_filename.getBytes("ISO8859-1"),"UTF-8");
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
		response.setCharacterEncoding(ServerProperties.getServletEncoding());
		response.setHeader("Content-Type", respContentType);
		Log.d("respContentType: " + respContentType);
		response.setHeader("Content-Length",
				String.valueOf(serverAgreementFile.length()));
		String respName = new String(responseFileName.getBytes(ServerProperties
				.getLocalEncoding()), ServerProperties.getServletEncoding());
		Log.d("respFileName: " + respName);
		response.setHeader("Content-disposition", "attachment;filename=\""
				+ respName + "\"");
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
						System.out.println("status: " + status);
						agreement.setAgreement_status(status.equals("true"));
					} else {
						Log.w("Unknown param form field: " + name);
					}
				} else {
					InputStream in = null;
					try {
						String fileName = item.getName();
						String type = item.getContentType();
						int slash = fileName.lastIndexOf("/");
						if (slash < 0)
							slash = fileName.lastIndexOf("\\");
						if (slash > 0)
							fileName = fileName.substring(slash + 1);

						in = item.openStream();
						SimpleDateFormat sdf = new SimpleDateFormat(
								"yyyyMMdd_HHmmss");

						String serverFileName = fileName + "."
								+ agreement.getCollege_id() + "_"
								+ sdf.format(now) + "-"
								+ encodeContentTypeForURL(type);
						File agreementFile = new File(agreementSubDir
								+ serverFileName);
						agreement.setAgreement_name(serverFileName);
						RandomAccessFile raf = new RandomAccessFile(
								agreementFile, "rw");
						if (in != null) {
							byte[] buff = new byte[1024];
							int readLen = 0;
							while ((readLen = in.read(buff)) > 0) {
								raf.write(buff, 0, readLen);
							}
							raf.close();
							Log.i("stored agreement in "
									+ agreementFile.getAbsolutePath());
						} else {
							Log.e("no inputsteam created for " + fileName);
						}

					} catch (Exception e) {
						Log.e("", e);
						response(response, e.getMessage(), false);
						return;
					} finally {
						if (in != null) {
							try {
								in.close();
							} catch (Exception e) {
								Log.e("", e);
							}
						}
					}
				}
			}

			agent.addCollegeAgreement(agreement);

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