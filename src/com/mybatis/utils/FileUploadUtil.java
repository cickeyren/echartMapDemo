package com.mybatis.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import com.mybatis.utils.UUIDUtil;

/**
 * 附件上传工具类
 * @author rensq
 *
 */
public class FileUploadUtil {
	
	/**
	 * 判断路径是否存在，如果不存在则创建
	 * @param dir
	 */
    public static void mkdirs(String dir){
        if(StringHelper.isEmpty(dir)){
            return;
        }

        File file = new File(dir);
        if(file.isDirectory()){
            return;
        } else {
            file.mkdirs();
        }
    }
	/**
	 * 获取文件上传路径
	 * 
	 * @return
	 */
	public String getFileName(String fileRootPath) {
		String uploadDir = "audio";
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMM");
		String date = sf.format(cal.getTime());
		char letter = (char) (int) (Math.round(Math.random() * 100.0D) % 26L + 65L);
		String filePathName = fileRootPath + File.separator + uploadDir+ File.separator + date + File.separator + letter;
		File filePath = new File(filePathName);
		if (!filePath.exists()) {
			filePath.mkdirs();
		}
		String fileName = filePathName + File.separator+ UUIDUtil.generateUUID()+".mp3";
		return fileName;
	}
}