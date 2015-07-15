package kh.edu.iic.angkormeas;

import android.os.Bundle;

public final class Constant {
	
	public static String url = "192.168.178.214";
	//public static String url = "192.168.42.54";
	//public static String url = "172.16.100.74";
		//public static String url = "192.168.1.103";
	
	//url get json
	public static String urlhome = "http://" + url + "/json/jsonscript_all.php";
	public static String urllg = "http://" + url + "/json/jsonscript_alllg.php";
	public static String urlsony = "http://" + url + "/json/jsonscript_allsony.php";
	public static String urlmotorola = "http://" + url + "/json/jsonscript_allmoto.php";
	public static String urlsamsung = "http://" + url + "/json/jsonscript_allsamsung.php";
	
	
	//url get image
	public static String urlimghome = "http://"+ url + "/json/phone_pic/apple/";
	public static String urlimglg = "http://"+ url + "/json/phone_pic/LG/";
	public static String urlimgsony = "http://"+ url + "/json/phone_pic/Sony/";
	public static String urlimgmoto = "http://"+ url + "/json/phone_pic/Motorola/";
	public static String urlimgsamsung = "http://"+ url + "/json/phone_pic/samsung/";
	
	//url get data for detail
	public static String urlDlg = "http://"+ url + "/json/lgdetail.php";
	public static String urlDsony = "http://"+ url + "/json/sonydetail.php";
	public static String urlDmoto = "http://"+ url + "/json/motodetail.php";
	public static String urlDhome = "http://"+ url + "/json/homedetail.php";
	public static String urlDsamsung = "http://"+ url + "/json/samsungdetail.php";
	
	private Constant() {
	}
}