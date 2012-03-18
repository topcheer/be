package com.brightedu.server.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import gwtupload.server.UploadAction;
import gwtupload.server.exceptions.UploadActionException;
import gwtupload.shared.UConsts;

public class ExcelParserServlet extends UploadAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8110271740625428618L;

	Hashtable<String, String> receivedContentTypes = new Hashtable<String, String>();
	/** * Maintain a list with received files and their content types. */
	Hashtable<String, File> receivedFiles = new Hashtable<String, File>();

	@Override
	public String executeAction(HttpServletRequest request,
			List<FileItem> sessionFiles) throws UploadActionException {
		StringBuffer response = new StringBuffer();
		
		for (FileItem item : sessionFiles) {
			if (false == item.isFormField()) {
				
				try {
					
					if(item.getName().endsWith(".xls"))
					{
						POIFSFileSystem fs = new POIFSFileSystem(item.getInputStream());
						HSSFWorkbook wb = new HSSFWorkbook(fs);
						 System.out.println("Sheet Num:" + wb.getNumberOfSheets());
						 //only get first sheet,ignore others
							HSSFSheet sheet  = wb.getSheetAt(0);
							
							Iterator<Row> rows = sheet.rowIterator();
							while(rows.hasNext())
							{
								HSSFRow row = (HSSFRow)rows.next();
								Iterator<Cell> cells = row.cellIterator();
								while(cells.hasNext())
								{
									HSSFCell cell = (HSSFCell) cells.next();
									response.append(cell.toString()+":");
								}
								response.append("\n");
							}
			
					}
					else if(item.getName().endsWith(".xlsx"))
					{
						//POIFSFileSystem fs = new POIFSFileSystem(item.getInputStream());
						XSSFWorkbook wb = new XSSFWorkbook(item.getInputStream());
						 System.out.println("Sheet Num:" + wb.getNumberOfSheets());
						 //only get first sheet,ignore others
							XSSFSheet sheet  = wb.getSheetAt(0);
							
							Iterator<Row> rows = sheet.rowIterator();
							while(rows.hasNext())
							{
								XSSFRow row = (XSSFRow)rows.next();
								Iterator<Cell> cells = row.cellIterator();
								while(cells.hasNext())
								{
									XSSFCell cell = (XSSFCell) cells.next();
									response.append(cell.toString()+":");
								}
								response.append("\n");
							}
					}




				} catch (Exception e) {
					throw new UploadActionException(e);
				}
			}
		}
		// / Remove files from session because we have a copy of them
		removeSessionFileItems(request);
		// / Send your customized message to the client.
		return response.toString();
	}

	/** * Get the content of an uploaded file. */
	@Override
	public void getUploadedFile(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String fieldName = request.getParameter(UConsts.PARAM_SHOW);
		File f = receivedFiles.get(fieldName);
		if (f != null) {
			response.setContentType(receivedContentTypes.get(fieldName));
			FileInputStream is = new FileInputStream(f);
			copyFromInputStreamToOutputStream(is, response.getOutputStream());
		} else {
			renderXmlResponse(request, response, XML_ERROR_ITEM_NOT_FOUND);
		}
	}

	/** * Remove a file when the user sends a delete request. */
	@Override
	public void removeItem(HttpServletRequest request, String fieldName)
			throws UploadActionException {
		File file = receivedFiles.get(fieldName);
		receivedFiles.remove(fieldName);
		receivedContentTypes.remove(fieldName);
		if (file != null) {
			file.delete();
		}
	}
}
