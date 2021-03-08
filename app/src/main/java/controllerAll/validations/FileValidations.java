package controllerAll.validations;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class FileValidations {
    public boolean isFileExist(String imageName, String sub_folder_name) {
        File myDir = new File(Environment.getExternalStorageDirectory(), ".Trajilis");

        if (!myDir.exists()) {
            myDir.mkdir();
        }

        File my_sub_Dir = new File(myDir, sub_folder_name);

        if (!my_sub_Dir.exists()) {
            my_sub_Dir.mkdir();
        }

        File file = new File(my_sub_Dir, imageName);
        if (file.exists())
            return true;
        else
            return false;
    }

    public boolean isFileExistInGallery(String imageName, String subFolderName) {
        File myDir = new File(Environment.getExternalStorageDirectory(), "Trajilis Gallery");

        if (!myDir.exists()) {
            myDir.mkdir();
        }

        File my_sub_Dir = new File(myDir, subFolderName);

        if (!my_sub_Dir.exists()) {
            my_sub_Dir.mkdir();
        }

        File file = new File(my_sub_Dir, imageName);

        if (file.exists())
            return true;
        else
            return false;
    }


    public String getFileName(String imageName, String subFolderName) {

        File myDir = new File(Environment.getExternalStorageDirectory(), ".Trajilis");

        if (!myDir.exists()) {
            myDir.mkdir();
        }

        File my_sub_Dir = new File(myDir, subFolderName);

        if (!my_sub_Dir.exists()) {
            my_sub_Dir.mkdir();
        }


        File file = new File(my_sub_Dir, imageName);
        String CurrentString = file.toString();

        String separated = CurrentString.substring(CurrentString.lastIndexOf("/") + 1);
        return separated.trim();
    }

    public String getFileNameInGallery(String imageName, String subFolderName) {

        File myDir = new File(Environment.getExternalStorageDirectory(), "Trajilis Gallery");

        if (!myDir.exists())
            myDir.mkdir();

        File my_sub_Dir = new File(myDir, subFolderName);

        if (!my_sub_Dir.exists())
            my_sub_Dir.mkdir();

        File file = new File(my_sub_Dir, imageName);
        String CurrentString = file.toString();

        String separated = CurrentString.substring(CurrentString.lastIndexOf("/") + 1);

        return separated.trim();
    }

    public String getFilePath(String imageName, String subFolderName) {

        File myDir = new File(Environment.getExternalStorageDirectory(), ".Trajilis");

        if (!myDir.exists())
            myDir.mkdir();

        File my_sub_Dir = new File(myDir, subFolderName);

        if (!my_sub_Dir.exists())
            my_sub_Dir.mkdir();

        File file = new File(my_sub_Dir, imageName);
        String CurrentString = file.toString();

        return CurrentString.trim();
    }

    public String getFilePathGallery(String imageName, String subFolderName) {

        File myDir = new File(Environment.getExternalStorageDirectory(), "Trajilis Gallery");

        if (!myDir.exists())
            myDir.mkdir();

        File my_sub_Dir = new File(myDir, subFolderName);

        if (!my_sub_Dir.exists()) {
            my_sub_Dir.mkdir();
        }

        File file = new File(my_sub_Dir, imageName);
        String CurrentString = file.toString();

        return CurrentString.trim();
    }

    public String getDestinationPath(String subFolderName){
        File myDir = new File(Environment.getExternalStorageDirectory(), ".Trajilis");

        if (!myDir.exists()) {
            myDir.mkdir();
        }

        File my_sub_Dir = new File(myDir, subFolderName);
        if (!my_sub_Dir.exists()) {
            my_sub_Dir.mkdir();
        }

        String CurrentString = my_sub_Dir.toString();

        return CurrentString;
    }

    public String getDestinationPathVisibileToGallery(String subFolderName){
        File myDir = new File(Environment.getExternalStorageDirectory(), "Trajilis Gallery");

        if (!myDir.exists()) {
            myDir.mkdir();
        }

        File my_sub_Dir = new File(myDir, subFolderName);
        if (!my_sub_Dir.exists()) {
            my_sub_Dir.mkdir();
        }

        String CurrentString = my_sub_Dir.toString();

        return CurrentString;
    }

    public void copyFile(File sourceFile, File destFile) {
        if (!sourceFile.exists())
            return;

        FileChannel source = null;
        FileChannel destination = null;
        try {
            source = new FileInputStream(sourceFile).getChannel();
            destination = new FileOutputStream(destFile).getChannel();
            if (destination != null && source != null) {
                destination.transferFrom(source, 0, source.size());
            }
            if (source != null) {
                source.close();
            }
            if (destination != null) {
                destination.close();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getRealPathFromURI(Context context, Uri uri) {
        String[] filePathColumn = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(uri, filePathColumn, null, null, null);
        cursor.moveToFirst();

        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        String filePath = cursor.getString(columnIndex);
        cursor.close();
        return filePath;
    }

}
