package Controller.Resource;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.*;

import java.io.*;

public class DropBoxConnexion {

    private static final String ACCESS_TOKEN = "-fzGF5VgtXAAAAAAAAAANO34PyIGJsq7gbYRUtocG196gblH80mHPT70QXhEskHJ";


    private DbxRequestConfig config;
    private DbxClientV2 client;


    public DropBoxConnexion() {
        this.config = new DbxRequestConfig("dropbox/ppm", "en_US");;
        this.client = new DbxClientV2(config, ACCESS_TOKEN);
    }



    public void uploadFile(String filepath, String dropboxpath) {
        try (InputStream in = new FileInputStream(filepath)) {
            try {
                FileMetadata metadata = client.files().uploadBuilder(dropboxpath).uploadAndFinish(in);
            } catch (UploadErrorException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (DbxException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void downloadFile(String downloadpath, String dropboxpath) {
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(downloadpath);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        try {
            FileMetadata metadata = client.files().downloadBuilder(dropboxpath).download(outputStream);
        } catch (DbxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteFile(String dropboxpath) {
        try {
            client.files().delete(dropboxpath);
        } catch (DeleteErrorException e) {
            e.printStackTrace();
        } catch (DbxException e) {
            e.printStackTrace();
        }
    }

    public void parcourArborescence(DbxClientV2 client, String dropboxpath) throws ListFolderErrorException, DbxException {
        ListFolderResult result = client.files().listFolder(dropboxpath);
        while (true) {
            for (Metadata metadata : result.getEntries()) {
                System.out.println(metadata.getPathLower());
                if(isFolder(metadata)) {
                    parcourArborescence(client,metadata.getPathLower());
                }
            }
            if (!result.getHasMore()) {
                break;
            }
            result = client.files().listFolderContinue(result.getCursor());
        }
    }

    public boolean isFolder(Metadata m) {
        boolean result = false;
        String[] splited = m.toString().replace("\"", "").split(",",11);
        result = splited[0].contains(".tag:folder");
        return result;
    }


}
