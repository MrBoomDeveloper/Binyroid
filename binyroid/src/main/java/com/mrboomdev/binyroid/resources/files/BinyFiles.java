package com.mrboomdev.binyroid.resources.files;

import androidx.annotation.NonNull;

import java.io.File;

public class BinyFiles {

	public static boolean deleteFile(@NonNull File file) {
		if(file.isDirectory()) {
			File[] children = file.listFiles();
			if(children == null) return false;

			for(File child : children) {
				deleteFile(child);
			}
		}

		return file.delete();
	}

	public static long getFileSize(@NonNull File file) {
		long size = 0;

		if(file.isFile()) {
			size += file.length();
		} if(file.isDirectory()) {
			File[] kids = file.listFiles();
			if(kids == null) return 0;

			for(File childFile : kids) {
				size += getFileSize(childFile);
			}
		}

		return size;
	}
}