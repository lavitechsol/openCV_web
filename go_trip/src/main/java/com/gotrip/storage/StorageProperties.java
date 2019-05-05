package com.gotrip.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("storage")
public class StorageProperties {

	private String images = "images";
	private String uploaded_images = "uploaded_images";
	private String processed_images = "processed_images";
	private String templates = "templates";

	public String getTemplates() {
		return templates;
	}
	public void setTemplates(String templates) {
		this.templates = templates;
	}
	public String getImages() {
		return images;
	}
	public void setImages(String images) {
		this.images = images;
	}
	public String getUploaded_images() {
		return uploaded_images;
	}
	public void setUploaded_images(String uploaded_images) {
		this.uploaded_images = uploaded_images;
	}
	public String getProcessed_images() {
		return processed_images;
	}
	public void setProcessed_images(String processed_images) {
		this.processed_images = processed_images;
	}

}
