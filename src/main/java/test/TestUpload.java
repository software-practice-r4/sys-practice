package test;

import sys_practice.Upload;

public class TestUpload {
	public static void main(String[] args) {
		Upload upload = new Upload();
		
		String[] arg = new String[10];
		
		for(int i=0; i<100;i++) {
			arg[0] = "テスト素材名"+i;
			arg[1] = "説明説明";
			arg[2] = "111111";
			arg[3] = "2";
			arg[4] = "イラスト";
			arg[5] = "0";
			arg[6] = "11";
			arg[7] = "kawauso.jpeg";
			
			int err = -1;
			try {
				err = upload.uploadMaterial(arg[0], arg[1], arg[2], arg[3], arg[4], arg[5], arg[6], arg[7]);
			} catch (Exception e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}finally {
				System.out.println(err);
			}
		}
	}
}
