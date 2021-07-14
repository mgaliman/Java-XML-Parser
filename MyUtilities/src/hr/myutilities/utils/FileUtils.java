/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.myutilities.utils;

import hr.myutilities.factory.UrlConnectionFactory;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

/**
 *
 * @author mgali
 */
public class FileUtils {

    private static final String UPLOAD = "Upload";
    
    public static void copyFromUrl(String pictureUrl, String picturePath) throws IOException {
        createDirHieararchy(picturePath);
        HttpURLConnection con = UrlConnectionFactory.getHttpUrlConnection(pictureUrl);
        try (InputStream is = con.getInputStream()) {
            Files.copy(is, Paths.get(picturePath));
        }
    }

    private static void createDirHieararchy(String path) throws IOException {
        String dir = path.substring(0, path.lastIndexOf(File.separator));
        if (!Files.exists(Paths.get(dir))) {
            Files.createDirectories(Paths.get(dir));
        }
    }

    public static void emptyDirectory(File directory) {
        for (File file : directory.listFiles()) {
            if (file.isDirectory()) {
                emptyDirectory(file);
            }
            file.delete();
        }
    }
    
    public static File uploadFile(String description, String... extensions) {
        JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        chooser.setFileFilter(new FileNameExtensionFilter(description, extensions));
        chooser.setDialogTitle(UPLOAD);
        chooser.setApproveButtonText(UPLOAD);
        chooser.setApproveButtonToolTipText(UPLOAD);
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();
            String extension = selectedFile.getName().substring(selectedFile.getName().lastIndexOf(".") + 1);
            return Arrays.asList(extensions).contains(extension.toLowerCase()) ? selectedFile : null;
        }
        return null;
    }
}
