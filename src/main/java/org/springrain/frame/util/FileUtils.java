package org.springrain.frame.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
	
	
	
	public static List<File> getPathAllFileExt(String path,String ext){
		List<File> list =new ArrayList<File>();
		
		getPathAllFileExt(list,path,ext);
		
		return list;
		
	}
	
	public  static  List<File> getPathAllFileExt(List<File> list,String path,String ext){
		
		File dir=new File(path);
		if(dir.isDirectory()==false)
			return list;
		
		File[] files=dir.listFiles();
		
	if(files==null||files.length<1)
		return list;
	for(File f:files){
		if(f.isDirectory()){
			String dirPath=f.getAbsolutePath();
			getPathAllFileExt(list,dirPath,ext);
		}else{
			if(f.getName().endsWith(ext)){
				
				list.add(f);
			}
		}
	}
		
		return list;
	}
	
	 /**
     * 获取文件后缀
     * 
     * @param originalFilename
     * @return
     */
    public static String getSuffix(String originalFilename) {
        return originalFilename.substring(originalFilename.lastIndexOf("."), originalFilename.length());
    }

    
    /**
     * 获取文件名
     * 
     * @param suffix
     * @return
     */
    public static String reSetFileName(String suffix) {
        return System.currentTimeMillis()+suffix;
    }

}
