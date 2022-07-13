package sys_practice;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FileUpload extends HttpServlet {

	/*
	 * post-materila.jspから、素材情報を取得
	 * その後そのデータをRDSに格納し、元ページへリダイレクトする
	 * */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//文字コード等 基本設定
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		// 保存先ファイルの設定		S
		String dataDir = getServletContext().getRealPath("sys-practice/content");
		System.out.println(dataDir);
		File dataDirFile = new File(dataDir);
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(dataDirFile);
		factory.setSizeThreshold(1024);
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("UTF-8");
		upload.setSizeMax(-1);

		// 素材情報を格納
		ArrayList<String> content = new ArrayList<>();
		String filename = getRandomString(20);
		boolean uploaded= false;


		try {
			// アップロードされたファイル情報をFileItemオブジェクトのリストとして取得
			List<FileItem> objLst = new ServletFileUpload(factory).parseRequest(new ServletRequestContext(request));
			Iterator<FileItem> objItr = objLst.iterator();

			// フォームの情報を取得
			for (FileItem uploadItem : objLst) {
				if (uploadItem.isFormField()) {
					content.add(uploadItem.getString("UTF-8"));
				}
			}
			// ファイルデータの読み込み
			while (objItr.hasNext()) {
				FileItem objFi = (FileItem) objItr.next();
				//ファイルじゃない時
				if (!objFi.isFormField()) {
					String prev_filename = objFi.getName();
					if(prev_filename.length() <= 0) {
						break;
					}
					filename = filename + prev_filename.substring(prev_filename.lastIndexOf("."));
					if (filename != null && !filename.equals("")) {
						objFi.write(new File(dataDir + "/" + filename));
						uploaded = true;
					}
				}
			}
			// 情報が欠如していた場合に、エラー情報を付与し、リダイレクトする
			if(filename.equals("")) {
				response.sendRedirect("/sys-practice/sys-practice/jsp/Post-material.jsp?fileisNull=true");
				return;
			} else if (content.get(0).equals("")) {
				response.sendRedirect("/sys-practice/sys-practice/jsp/Post-material.jsp?materialNameisNull=true");
				return;
			} else if (content.get(1).equals("")) {
				response.sendRedirect("/sys-practice/sys-practice/jsp/Post-material.jsp?explanationisNull=true");
				return;
			} else if (content.get(2).equals("")) {
				response.sendRedirect("/sys-practice/sys-practice/jsp/Post-material.jsp?priceisNull=true");
				return;
			} else if (content.get(6).equals("")) {
				response.sendRedirect("/sys-practice/sys-practice/jsp/Home.jsp");
				return;
			}


			// SQL文発行
			Upload uploadMaterial = new Upload();
			uploadMaterial.uploadMaterial(content.get(0), content.get(1), content.get(2), content.get(3), content.get(4), content.get(5), content.get(6), filename);

			try {
				Cookie cookie[] = request.getCookies();
				String userId = null;
				if (cookie != null){
				    for (int i = 0 ; i < cookie.length ; i++){
				      if (cookie[i].getName().equals("userId"))
				        userId = cookie[i].getValue();
				    }
				}
				System.err.println(content.get(0)+", "+content.get(1));
			} catch (Exception e) {
				System.out.println(e);
			}
			// ファイル名をデータベースに登録する
			if (!uploaded)
				response.sendRedirect("/sys-practice/sys-practice/jsp/Post-material.jsp?isFailed=true");
				//processRequest(request, response); //エラー表示
			else {
				// 次の一覧表示ページへ転送する
;				response.sendRedirect("/sys-practice/sys-practice/jsp/Post-material.jsp?isSuccessed=true");
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		//エラーを表示するHTMLの出力
		try (PrintWriter out = response.getWriter()) {

			out.println("<html>");
			out.println("<head><title>エラー表示 POST時のエラー</title></head>");
		} catch (Exception e) {
			PrintWriter out = response.getWriter();
			out.println("<p>POST時のエラー</p>");
			out.println("<p>" + e + "</p>");
		} finally {
			PrintWriter out = response.getWriter();
			out.println("</html>");
		}
	}

	static String getRandomString(int i)
    {
        String theAlphaNumericS;
        StringBuilder builder;

        theAlphaNumericS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                    + "0123456789";

        //create the StringBuffer
        builder = new StringBuilder(i);

        for (int m = 0; m < i; m++) {

            // generate numeric
            int myindex
                = (int)(theAlphaNumericS.length()
                        * Math.random());

            // add the characters
            builder.append(theAlphaNumericS
                        .charAt(myindex));
        }

        return builder.toString();
    }
}