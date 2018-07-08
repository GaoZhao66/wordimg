package com.company.project.model;

/**
 * 传入参数的一个实体类
 * @author 阳斌
 */
public class WordFile {
	/**
	 * 文档的全路径
	 */
	private String filename;

	/**
	 * 文档类型
	 */
	private String filetype;


	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFiletype() {
		return filetype;
	}

	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}

	@Override
	public String toString() {
		return "WordFile [filename=" + filename + ", filetype=" + filetype + "]";
	}

}
