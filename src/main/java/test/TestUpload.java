package test;

import sys_practice.Upload;

public class TestUpload {
	public static void main(String[] args) {
		Upload upload = new Upload();
		
		String[] arg = new String[10];
		
		arg[0] = "テスト素材名";
		arg[1] = "説明説明";
		arg[2] = "1000";
		arg[3] = "0";
		arg[4] = "テスト増やすカテゴリー名";
		arg[5] = "0";
		arg[6] = "1";
		arg[7] = "sample.jpg";
		
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
