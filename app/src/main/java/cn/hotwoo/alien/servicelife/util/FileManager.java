package cn.hotwoo.alien.servicelife.util;

import android.content.Context;

import java.io.File;
import java.util.EnumMap;

/**
 * Created by alien on 2015/8/13.
 */
public class FileManager {

    private static FileManager instance;
    private static Context mContext;
    private static EnumMap<Dir,File> fileHashMap=new EnumMap<>(Dir.class);

    public enum Dir {
        OBJECT,IMAGE
    }
    private FileManager(){}

    public static FileManager getInstance(){
        if(instance==null){
            instance=new FileManager();
        }
        return instance;
    }

    public static void init(Context context){
        mContext=context;
        File cache=mContext.getCacheDir();
        for(Dir path: Dir.values()){
            File dirFile=new File(cache,path.name());
            if(!dirFile.exists()){
                dirFile.mkdirs();
            }
            fileHashMap.put(path,dirFile);
        }
    }

    public File getChild(Dir dir,String name){
        return new File(fileHashMap.get(dir),name);
    }

    public boolean deleteChild(Dir dir,String name){
        return new File(fileHashMap.get(dir),name).delete();
    }

    public String getCacheDir(Dir dir){
        return fileHashMap.get(dir).getPath();
    }

    /**
     * 删除目录下所有的文件
     * @param dir 目录路径
     */
    public boolean clearDir(Dir dir){
        File directory=fileHashMap.get(dir);
        if(directory.isDirectory()){
           String[] childs=directory.list();
            Utils.Log("files:"+childs.toString());
            for(String child:childs){
                boolean success=new File(directory,child).delete();
                if(!success){
                    return false;
                }
            }
        }
        return true;
    }
}
