package com.lzw.library.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * //////////////////////////////////////////////////////////////////////////////
 * //
 * //      ┏┛ ┻━━━━━┛ ┻┓
 * //      ┃　　　　　　 ┃
 * //      ┃　　　━　　　┃
 * //      ┃　┗┛　  ┗┛　┃
 * //      ┃　　　　　　 ┃
 * //      ┃　　　┻　　　┃               @Author  lzw
 * //      ┃　　　　　　 ┃
 * //      ┗━┓　　　┏━━━┛               @Date  2017/1/19
 * //        ┃　　　┃   神兽保佑
 * //        ┃　　　┃   代码无BUG！      @Desc
 * //        ┃　　　┗━━━━━━━━━┓
 * //        ┃　　　　　　　    ┣━━━┛
 * //        ┃　　　　         ┏┛
 * //        ┗━┓ ┓ ┏━━━┳ ┓ ┏━┛
 * //          ┃ ┫ ┫   ┃ ┫ ┫
 * //          ┗━┻━┛   ┗━┻━┛
 * //
 * /////////////////////////////////////////////////////////////////////////////
 */
public class FileUtils {
    public static final String TAG = FileUtil.class.getSimpleName();
    /**
     * SD卡操作权限
     */
    private static final String EXTERNAL_STORAGE_PERMISSION = "android.permission.WRITE_EXTERNAL_STORAGE";
    /**
     * 最小可用内存
     */
    private static final int MIN_AVAILABLE_MEMORY = 2000;
    /**
     * 文件扩展名间隔符号
     */
    public final static String FILE_EXTENSION_SEPARATOR = ".";

    /**
     * 获取当前SD卡是否可用
     *
     * @return
     */
    public static boolean isSDCardExist() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return true;
        }
        return false;
    }

    /**
     * 判断是否有读取SD权限
     *
     * @param context
     * @return
     */
    public static boolean isHasSDCardPermission(Context context) {
        int permission = context
                .checkCallingOrSelfPermission(EXTERNAL_STORAGE_PERMISSION);
        return permission == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * 判断SD卡是否有可用内存
     *
     * @return
     */
    public static boolean isHasAvailableMemory() {
        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory()
                .getPath());
        if (statFs.getAvailableBlocks() > MIN_AVAILABLE_MEMORY) {
            return true;
        }
        Log.e(TAG, "SDCard No Available Memory");
        return false;
    }

    /**
     * 创建文件根目录
     *
     * @param context
     * @param path
     * @return
     */
    public static File createDirFile(Context context, String path) {
        if (StringUtil.isEmpty(path)) {
            return null;
        }
        File dirFile = null;
        if (!isSDCardExist()) {
            Log.e(TAG, "SDCard Unavailable");
            return null;
        }
        if (!isHasSDCardPermission(context)) {
            Log.e(TAG,
                    "No android.permission.WRITE_EXTERNAL_STORAGE Permission");
            return null;
        }
        if (path == null) {
            Log.e(TAG, "FilePath is null");
            return null;
        }
        dirFile = new File(path);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
        return dirFile;
    }

    /**
     * 创建文件
     *
     * @param context
     * @param path
     * @return
     */
    public static File createNewFile(Context context, String path) {
        File file = null;
        if (StringUtil.isEmpty(path)) {
            return null;
        }
        if (!isSDCardExist()) {
            Log.e(TAG, "SDCard Unavailable");
            return null;
        }
        if (!isHasSDCardPermission(context)) {
            Log.e(TAG,
                    "No android.permission.WRITE_EXTERNAL_STORAGE Permission");
            return null;
        }
        if (path == null) {
            Log.e(TAG, "FilePath is null");
            return null;
        }
        file = new File(path);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                Log.e(TAG, "Create New File Error");
                return null;
            }
        }
        return file;
    }

    /**
     * 判断文件是否存在
     *
     * @param filePath
     * @return
     */
    public static boolean isFileExist(String filePath) {
        if (StringUtil.isEmpty(filePath)) {
            return false;
        }

        File file = new File(filePath);
        return (file.exists() && file.isFile());
    }

    /**
     * 判断文件夹是否存在
     *
     * @param directoryPath
     * @return
     */
    public static boolean isFolderExist(String directoryPath) {
        if (StringUtil.isEmpty(directoryPath)) {
            return false;
        }

        File dire = new File(directoryPath);
        return (dire.exists() && dire.isDirectory());
    }

    /**
     * 删除文件
     *
     * @param path
     * @return
     */
    public static boolean deleteFile(String path) {
        if (StringUtil.isEmpty(path)) {
            return true;
        }

        File file = new File(path);
        if (!file.exists()) {
            return true;
        }
        if (file.isFile()) {
            return file.delete();
        }
        if (!file.isDirectory()) {
            return false;
        }
        for (File f : file.listFiles()) {
            if (f.isFile()) {
                f.delete();
            } else if (f.isDirectory()) {
                deleteFile(f.getAbsolutePath());
            }
        }
        return file.delete();
    }

    /**
     * 获取文件的大小
     *
     * @param path
     * @return
     */
    public static long getFileSize(String path) {
        if (StringUtil.isEmpty(path)) {
            return -1;
        }
        File file = new File(path);
        return (file.exists() && file.isFile() ? file.length() : -1);
    }

    /**
     * 获取文件的Uri
     *
     * @param context
     * @param path
     * @return
     */
    public static Uri getUriFromFile(Context context, String path) {
        if (StringUtil.isEmpty(path)) {
            return null;
        }
        File file = null;
        if (!isSDCardExist()) {
            Log.e(TAG, "SDCard Unavailable");
            return null;
        }
        if (!isHasSDCardPermission(context)) {
            Log.e(TAG,
                    "No android.permission.WRITE_EXTERNAL_STORAGE Permission");
            return null;
        }
        if (path == null) {
            Log.e(TAG, "FilePath is null");
            return null;
        }
        file = new File(path);
        if (!file.exists()) {
            return null;
        }
        return Uri.fromFile(file);
    }

    /**
     * 换算文件大小
     *
     * @param size
     * @return
     */
    public static String formatFileSize(long size) {
        if (size < 0) {
            return "0B";
        }
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "未知大小";
        if (size < 1024) {
            fileSizeString = df.format((double) size) + "B";
        } else if (size < 1048576) {
            fileSizeString = df.format((double) size / 1024) + "K";
        } else if (size < 1073741824) {
            fileSizeString = df.format((double) size / 1048576) + "M";
        } else {
            fileSizeString = df.format((double) size / 1073741824) + "G";
        }
        return fileSizeString;
    }

    /**
     * 读取文件
     *
     * @param context
     * @param filePath
     * @param charsetName
     * @return
     */
    public static StringBuilder readFile(Context context, File file,
                                         String charsetName) {
        if (isSDCardExist() && isHasSDCardPermission(context)) {
            if (StringUtil.isEmpty(charsetName)) {
                charsetName = "UTF-8";
            }
            StringBuilder fileContent = new StringBuilder("");
            if (file == null || !file.isFile()) {
                return null;
            }

            BufferedReader reader = null;
            try {
                InputStreamReader is = new InputStreamReader(
                        new FileInputStream(file), charsetName);
                reader = new BufferedReader(is);
                String line = null;
                while ((line = reader.readLine()) != null) {
                    if (!fileContent.toString().equals("")) {
                        fileContent.append("\r\n");
                    }
                    fileContent.append(line);
                }
                reader.close();
                return fileContent;
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return null;
    }

    /**
     * 根据字符串写文件
     *
     * @param filePath
     * @param content
     * @param append
     * @return
     */
    public static boolean writeFile(Context context, File file, String content,
                                    boolean append) {
        if (isSDCardExist() && isHasAvailableMemory()
                && isHasSDCardPermission(context)) {
            if (file == null || StringUtil.isEmpty(content)) {
                return false;
            }
            if (!isFileExist(file.getPath())) {
                String dirPath = file.getParentFile().getPath();
                File dirFile = createDirFile(context, dirPath);
                if (dirFile != null) {
                    file = createNewFile(context, file.getPath());
                }
                if (file == null) {
                    return false;
                }
            }
            FileWriter fileWriter = null;
            try {
                fileWriter = new FileWriter(file, append);
                fileWriter.write(content);
                fileWriter.close();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fileWriter != null) {
                    try {
                        fileWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return false;
    }

    /**
     * 通过输入流写文件
     *
     * @param filePath
     * @param stream
     * @return
     */
    public static boolean writeFile(Context context, String filePath,
                                    InputStream stream) {
        if (isSDCardExist() && isHasAvailableMemory()
                && isHasSDCardPermission(context)) {
            if (StringUtil.isEmpty(filePath) || stream == null) {
                return false;
            }
            OutputStream o = null;
            try {
                o = new FileOutputStream(filePath);
                byte data[] = new byte[1024];
                int length = -1;
                while ((length = stream.read(data)) != -1) {
                    o.write(data, 0, length);
                }
                o.flush();
                return true;
            } catch (FileNotFoundException e) {
                throw new RuntimeException("FileNotFoundException occurred. ",
                        e);
            } catch (IOException e) {
                throw new RuntimeException("IOException occurred. ", e);
            } finally {
                if (o != null) {
                    try {
                        o.close();
                        stream.close();
                    } catch (IOException e) {
                        throw new RuntimeException("IOException occurred. ", e);
                    }
                }
            }
        }
        return false;
    }

    /**
     * 读取文件存入List
     *
     * @param filePath
     * @param charsetName
     * @return
     */
    public static List<String> readFileToList(Context context, String filePath,
                                              String charsetName) {
        if (isSDCardExist() && isHasSDCardPermission(context)) {
            if (StringUtil.isEmpty(filePath)) {
                return null;
            }
            if (StringUtil.isEmpty(charsetName)) {
                charsetName = "UTF-8";
            }
            File file = new File(filePath);
            List<String> fileContent = new ArrayList<String>();
            if (file == null || !file.isFile()) {
                return null;
            }

            BufferedReader reader = null;
            try {
                InputStreamReader is = new InputStreamReader(
                        new FileInputStream(file), charsetName);
                reader = new BufferedReader(is);
                String line = null;
                while ((line = reader.readLine()) != null) {
                    fileContent.add(line);
                }
                reader.close();
                return fileContent;
            } catch (IOException e) {
                throw new RuntimeException("IOException occurred. ", e);
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        throw new RuntimeException("IOException occurred. ", e);
                    }
                }
            }
        }
        return null;
    }

    /**
     * 获取路径文件名称,不包含扩展名
     * <p>
     * <pre>
     *      getFileNameWithoutExtension(null)               =   null
     *      getFileNameWithoutExtension("")                 =   ""
     *      getFileNameWithoutExtension("   ")              =   "   "
     *      getFileNameWithoutExtension("abc")              =   "abc"
     *      getFileNameWithoutExtension("a.mp3")            =   "a"
     *      getFileNameWithoutExtension("a.b.rmvb")         =   "a.b"
     *      getFileNameWithoutExtension("c:\\")              =   ""
     *      getFileNameWithoutExtension("c:\\a")             =   "a"
     *      getFileNameWithoutExtension("c:\\a.b")           =   "a"
     *      getFileNameWithoutExtension("c:a.txt\\a")        =   "a"
     *      getFileNameWithoutExtension("/home/admin")      =   "admin"
     *      getFileNameWithoutExtension("/home/admin/a.txt/b.mp3")  =   "b"
     * </pre>
     *
     * @param filePath
     * @return
     */
    public static String getFileNameWithoutExtension(String filePath) {
        if (StringUtil.isEmpty(filePath)) {
            return filePath;
        }

        int extenPosi = filePath.lastIndexOf(FILE_EXTENSION_SEPARATOR);
        int filePosi = filePath.lastIndexOf(File.separator);
        if (filePosi == -1) {
            return (extenPosi == -1 ? filePath : filePath.substring(0,
                    extenPosi));
        }
        if (extenPosi == -1) {
            return filePath.substring(filePosi + 1);
        }
        return (filePosi < extenPosi ? filePath.substring(filePosi + 1,
                extenPosi) : filePath.substring(filePosi + 1));
    }

    /**
     * 获取路径文件名称,包含扩展名
     * <p>
     * <pre>
     *      getFileName(null)               =   null
     *      getFileName("")                 =   ""
     *      getFileName("   ")              =   "   "
     *      getFileName("a.mp3")            =   "a.mp3"
     *      getFileName("a.b.rmvb")         =   "a.b.rmvb"
     *      getFileName("abc")              =   "abc"
     *      getFileName("c:\\")              =   ""
     *      getFileName("c:\\a")             =   "a"
     *      getFileName("c:\\a.b")           =   "a.b"
     *      getFileName("c:a.txt\\a")        =   "a"
     *      getFileName("/home/admin")      =   "admin"
     *      getFileName("/home/admin/a.txt/b.mp3")  =   "b.mp3"
     * </pre>
     *
     * @param filePath
     * @return
     */
    public static String getFileName(String filePath) {
        if (StringUtil.isEmpty(filePath)) {
            return filePath;
        }

        int filePosi = filePath.lastIndexOf(File.separator);
        return (filePosi == -1) ? filePath : filePath.substring(filePosi + 1);
    }

    /**
     * 获取路径文件夹名称
     * <p>
     * <pre>
     *      getFolderName(null)               =   null
     *      getFolderName("")                 =   ""
     *      getFolderName("   ")              =   ""
     *      getFolderName("a.mp3")            =   ""
     *      getFolderName("a.b.rmvb")         =   ""
     *      getFolderName("abc")              =   ""
     *      getFolderName("c:\\")              =   "c:"
     *      getFolderName("c:\\a")             =   "c:"
     *      getFolderName("c:\\a.b")           =   "c:"
     *      getFolderName("c:a.txt\\a")        =   "c:a.txt"
     *      getFolderName("c:a\\b\\c\\d.txt")    =   "c:a\\b\\c"
     *      getFolderName("/home/admin")      =   "/home"
     *      getFolderName("/home/admin/a.txt/b.mp3")  =   "/home/admin/a.txt"
     * </pre>
     *
     * @param filePath
     * @return
     */
    public static String getFolderName(String filePath) {

        if (StringUtil.isEmpty(filePath)) {
            return filePath;
        }

        int filePosi = filePath.lastIndexOf(File.separator);
        return (filePosi == -1) ? "" : filePath.substring(0, filePosi);
    }

    /**
     * 获取路径文件的扩展名
     * <p>
     * <pre>
     *      getFileExtension(null)               =   ""
     *      getFileExtension("")                 =   ""
     *      getFileExtension("   ")              =   "   "
     *      getFileExtension("a.mp3")            =   "mp3"
     *      getFileExtension("a.b.rmvb")         =   "rmvb"
     *      getFileExtension("abc")              =   ""
     *      getFileExtension("c:\\")              =   ""
     *      getFileExtension("c:\\a")             =   ""
     *      getFileExtension("c:\\a.b")           =   "b"
     *      getFileExtension("c:a.txt\\a")        =   ""
     *      getFileExtension("/home/admin")      =   ""
     *      getFileExtension("/home/admin/a.txt/b")  =   ""
     *      getFileExtension("/home/admin/a.txt/b.mp3")  =   "mp3"
     * </pre>
     *
     * @param filePath
     * @return
     */
    public static String getFileExtension(String filePath) {
        if (StringUtil.isEmpty(filePath)) {
            return filePath;
        }

        int extenPosi = filePath.lastIndexOf(FILE_EXTENSION_SEPARATOR);
        int filePosi = filePath.lastIndexOf(File.separator);
        if (extenPosi == -1) {
            return "";
        }
        return (filePosi >= extenPosi) ? "" : filePath.substring(extenPosi + 1);
    }
}
