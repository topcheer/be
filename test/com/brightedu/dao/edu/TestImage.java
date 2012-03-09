package com.brightedu.dao.edu;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.imageio.ImageIO;

import org.junit.Test;

import com.brightedu.util.NumberVerCodeIdentifier;

public class TestImage {

	@Test
	public void testRecognize() {
		NumberVerCodeIdentifier nvi = new NumberVerCodeIdentifier();

		String res = "";
		try {
			res = nvi.recognize(ImageIO.read(new File("c:\\image.jpg")));

			// res = nvi.recognize(ImageIO.read(new
			// URL("http://******.com/validationCode.jsp")));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(res);
	}

}
