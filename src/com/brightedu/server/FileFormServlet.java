package com.brightedu.server;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
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

/**
 * @author chetwang, 主要处理文件上传的form
 * 
 */
public class FileFormServlet extends BrightServlet {

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
			if (ServletFileUpload.isMultipartContent(request)) {
				processFiles(request, response);
			} else {
				// processQuery(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void processFiles(HttpServletRequest request,
			HttpServletResponse response) {
		String actionType = request.getParameter("action");
		if (actionType != null && !actionType.trim().equals("")) {
			if (actionType.toLowerCase().equals("collegeagreement")) {
				processCollegeAgreement(request, response);
			} else {
				Log.e("Undefied action for FileFormServlet: " + actionType);
			}
		} else {
			Log.e("empty action type for FileFormServlet");
		}

	}

	private void processCollegeAgreement(HttpServletRequest request,
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
						agreement.setAgreement_name(fileName);
						int slash = fileName.lastIndexOf("/");
						if (slash < 0)
							slash = fileName.lastIndexOf("\\");
						if (slash > 0)
							fileName = fileName.substring(slash + 1);

						in = item.openStream();
						// SimpleDateFormat sdf = new SimpleDateFormat(
						// "yyyyMMdd-HHmmss");
						File agreementsDir = new File(
								ServerProperties.getDataLocation()
										+ "/agreement/");
						if (!agreementsDir.exists()) {
							agreementsDir.mkdirs();
						}
						File agreementFile = new File(
								ServerProperties.getDataLocation()
										+ "/agreement/"
										+ agreement.getCollege_id() + fileName);
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

}