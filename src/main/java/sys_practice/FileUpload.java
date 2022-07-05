package sys_practice;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UploadFile extends HttpServlet{

	private String materialName = ""; //タイトル
	private int price; //価格
	private String thumbnail = ""; //サムネイル
	private int categoryId; //カテゴリID
	private int providerId; //プロバイダID
	private String explanation = ""; //説明文
	private String category = ""; //カテゴリ
	private String fileName = ""; // ファイル名

	private final String dataDir = "/sys-practice/src/main/webapp/sys-practice/img"; //保存先のパス
	private final Upload file = new Upload();

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
}

@Override
public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
      response.setContentType("text/html; charset=UTF-8");
      request.setCharacterEncoding("UTF-8");

      // アップロードの可否
      boolean uploaded = false;

      // ファイル取得設定
      File dataDirFile = new File("aws/img"); //保存先ディレクトリの設定
      DiskFileItemFactory dfi = new DiskFileItemFactory();
      dfi.setRepository(dataDirFile); // 一時ファイルの保存先フォルダ
      dfi.setSizeThreshold(1024); // バッファサイズ
      ServletFileUpload sfu = new ServletFileUpload(dfi);
      sfu.setHeaderEncoding("UTF-8");
      sfu.setSizeMax(-1); // アップロードファイルの最大サイズ

      try {
        List objLst = sfu.parseRequest((RequestContext) request);
        Iterator objItr = objLst.iterator();
        while (objItr.hasNext()) {
          FileItem objFi = (FileItem) objItr.next();
          if (objFi.isFormField()) {
            String name = objFi.getFieldName();
            String value = objFi.getString(request.getCharacterEncoding());
            if(name.equals("materialname")) {
            	materialName = value;
            } else if(name.equals("explanation")) {
            	explanation = value;
            } else if(name.equals("price")) {
            	price = Integer.parseInt(value);
            } else if(name.equals("category")) {
            	category = value;
            } else if(name.equals("thumbnail")) {
            	explanation = value;
            } else if(name.equals("categoryId")) {
            	categoryId = Integer.parseInt(value);
            } else if(name.equals("providerId")) {
            	providerId = Integer.parseInt(value);
            }
          } else {
            fileName = objFi.getName();
            fileName = fileName.replaceAll("^.*[^A-Za-z0-9_¥¥-¥¥.]", "");
            if (fileName != null && !fileName.equals("")) {
              uploaded = true;
              objFi.write(new File(dataDir + "/" + fileName));
            }
          }
        }
        //ファイルをデータベースに登録する
        file.uploadMaterial(materialName, explanation, price, category, thumbnail, fileName, categoryId, providerId);

        //次のページへの移動
        if (!uploaded) {
          request.setAttribute("errorcode",1);
        } else {
          request.setAttribute("errorcode",0);
        }
        RequestDispatcher rd = request.getRequestDispatcher("post-material.jsp");
        rd.forward(request, response);
    } catch (Exception e) {
      request.setAttribute("errorcode",1);
      RequestDispatcher rd = request.getRequestDispatcher("post-material.jsp");
      rd.forward(request, response);
    }
  }
}