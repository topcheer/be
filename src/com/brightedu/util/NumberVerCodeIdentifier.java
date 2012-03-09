package com.brightedu.util;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.media.jai.JAI;
import javax.media.jai.RenderedOp;

/**
 * 数字验证码识别器
 * 
 * 算法如下： 分析验证码图片结构，将其分隔成4个独立的数字图片，把四个独立的数字图片处理成单色位图。
 * 把单色位图转换为0、1数组，然后分别和0-9的字模进行匹配，得到图片上的数字信息。
 * 
 */
public class NumberVerCodeIdentifier {

	static {
		System.setProperty("com.sun.media.jai.disableMediaLib", "true");
	}

	// 特征
	private static String a0 = "00111100010000100100001010000001";
	private static String a1 = "00011000001010000000100000001000";

	private static String a2 = "01111000100001000000001000000010";

	private static String a3 = "01111100100000100000001000000010";
	private static String a4 = "00000100000011000001010000010100";

	private static String a5 = "11111110100000001000000010000000";

	private static String a6 = "00011110001000010100000010000000";
	private static String a7 = "11111111000000010000001000000100";
	private static String a8 = "00111100010000100100001001000010";
	private static String a9 = "00111100010000101000000110000001";

	public static String recognize(BufferedImage image) {

		String result = "";

		BufferedImage newim[] = new BufferedImage[4];
		if (null == image) {
			throw new RuntimeException("iamage为null");
		}
		// 将图像分成四块，因为要处理的文件有四个数字。
		newim[0] = generateSingleColorBitMap(image.getSubimage(7, 4, 8, 10));
		newim[1] = generateSingleColorBitMap(image.getSubimage(17, 4, 8, 10));
		newim[2] = generateSingleColorBitMap(image.getSubimage(27, 4, 8, 10));
		newim[3] = generateSingleColorBitMap(image.getSubimage(37, 4, 8, 10));

		for (int k = 0; k < 4; k++) {
			int iw = newim[k].getWidth(null);
			int ih = newim[k].getHeight(null);

			StringBuffer numstr = new StringBuffer();
			// 因为是二值图像，这里的方法将像素读取出来的同时，转换为0，1的图像数组。
			for (int i = 0; i < ih; i++) {
				for (int j = 0; j < iw; j++) {

					int t = newim[k].getRGB(j, i);
					if (t == -1)
						numstr.append("0");
					else
						numstr.append("1");
					;

				}
			}
			// 得到像匹配的数字串。

			// System.out.println(numstr.toString());
			String aaaaa = numstr.toString().substring(0, 32);
			// System.out.println(aaaaa);

			if (aaaaa.equals(a0)) {
				result = result + 0;
			}

			if (aaaaa.equals(a1)) {
				result = result + 1;// System.out.println(1);
			}
			if (aaaaa.equals(a2)) {
				result = result + 2;// System.out.println(2);
			}
			if (aaaaa.equals(a3)) {
				result = result + 3;// System.out.println(3);
			}

			if (aaaaa.equals(a4)) {
				result = result + 4;// System.out.println(4);
			}
			if (aaaaa.equals(a5)) {
				result = result + 5;// System.out.println(4);
			}
			if (aaaaa.equals(a6)) {
				result = result + 6;// System.out.println(4);
			}
			if (aaaaa.equals(a7)) {
				result = result + 7;// System.out.println(7);
			}
			if (aaaaa.equals(a8)) {
				result = result + 8;// System.out.println(4);
			}
			if (aaaaa.equals(a9)) {
				result = result + 9;// System.out.println(9);
			}

		}
		// System.out.println(result);
		return result;

	}

	/**
	 * 单色位图转换
	 * 
	 * @return
	 */
	private static BufferedImage generateSingleColorBitMap(Image colorImage) {
		BufferedImage image = new BufferedImage(8, 10,
				BufferedImage.TYPE_BYTE_GRAY);
		Graphics g = image.getGraphics();
		g.drawImage(colorImage, 0, 0, null);
		g.dispose();
		RenderedOp ro = JAI.create("binarize", image, new Double(100));
		BufferedImage bi = ro.getAsBufferedImage();
		return bi;
	}

	/**
	 * 测试
	 * 
	 * @author sunyang
	 */
	public static void main(String args[]) {

		NumberVerCodeIdentifier nvi = new NumberVerCodeIdentifier();

		String res = "";
		try {
			// res = nvi.recognize(ImageIO.read(new File("c:\\image7.jpg")));
			String code = Math.random() + "";
			res = nvi.recognize(ImageIO.read(new URL(
					"http://xueli.upol.cn/M4/upol/platform/image.jsp?code="
							+ code)));
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
