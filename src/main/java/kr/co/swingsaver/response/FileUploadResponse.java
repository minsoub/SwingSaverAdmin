package kr.co.swingsaver.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileUploadResponse {
    public String file_name;
    public String stre_file_name;
    public String filedownloaduri;
    public String file_ty;
    public long file_sz;

    public FileUploadResponse() {}
    public FileUploadResponse(String filename, String newFileName, String filedownloaduri, String fileType, long size) {
        this.file_name = filename;
        this.stre_file_name = newFileName;
        this.filedownloaduri = filedownloaduri;
        this.file_ty = fileType;
        this.file_sz = size;
    }
}
