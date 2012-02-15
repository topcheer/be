package com.brightedu.server;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServlet;
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
 * @author chetwang
 * 
 */
public class FileFormServlet extends HttpServlet {

	public FileFormServlet() {
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
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
		// HashMap<String, String> args = new HashMap<String, String>();
		boolean isGWT = true;
		try {

			ServletFileUpload upload = new ServletFileUpload();
			FileItemIterator iter = upload.getItemIterator(request);
			// pick up parameters first and note actual FileItem
			CollegeAgreement agreement = new CollegeAgreement();
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
					String contentType = item.getContentType();
					String fileName = item.getName();
					int slash = fileName.lastIndexOf("/");
					if (slash < 0)
						slash = fileName.lastIndexOf("\\");
					if (slash > 0)
						fileName = fileName.substring(slash + 1);

					InputStream in = null;
					try {
						in = item.openStream();
						SimpleDateFormat sdf = new SimpleDateFormat(
								"yyyyMMdd-HHmmss");
						File agreementsDir = new File(
								ServerProperties.getDataLocation()
										+ "/agreement/");

						if (in != null) {
							byte[] buff = new byte[1024];
							int totalLen = 0;
							int readLen = 0;
							while ((readLen = in.read(buff)) > 0) {
								totalLen += readLen;
							}
						} else {
							Log.e("no inputsteam created for " + fileName);
						}
					} catch (Exception e) {
						Log.e("", e);

					} finally {
						if (in != null)
							try {
								in.close();
							} catch (Exception e) {
								Log.e("", e);
							}
					}
				}
			}

			// response.sendRedirect("/success.html");
			// // TODO: need to handle conversion options and error reporting
			response(response, "aaaaa", false);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private void response(HttpServletResponse response, String msg,
			boolean success) throws IOException {
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println(success ? "success" : "failed");
		out.println("</head>");
		out.println("<body>");
		out.println(msg);
		out.println("</body>");
		out.println("</html>");
		out.flush();
	}
}