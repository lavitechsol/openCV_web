package com.gotrip.storage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.gotrip.service.StorageService;



@Service
public class FileSystemStorageService implements StorageService {

	private final Path images;
	private final Path uploaded_images;
	private final Path processed_images;
	private final Path templates;
	@Autowired
	public FileSystemStorageService(StorageProperties properties) {
		this.images = Paths.get(properties.getImages());
		this.uploaded_images = Paths.get(properties.getImages() + "/" + properties.getUploaded_images());
		this.processed_images = Paths.get(properties.getImages() + "/" + properties.getProcessed_images());
		this.templates = Paths.get(properties.getImages() + "/" + properties.getTemplates());
	}

	public String getTemplateFolderPath() {
		return this.templates.toString();		
	}
	public String getImageFolderPath() {
		return this.images.toString();
	}
	
	public String getUploadedImageFolderPath() {
		return this.uploaded_images.toString();
	}
	
	public String getProcessedImageFolderPath() {
		return this.processed_images.toString();
	}
	
	public String getProcessedImageAbsolutePath() {
		return this.processed_images.toAbsolutePath().toString();
	}
	
	public String saveUploadedFiles(MultipartFile file) throws IOException {
		if (file.isEmpty()) {
            return null; 
        }       
        byte[] bytes = file.getBytes();
       
        Path path = Paths.get(getUploadedImageFolderPath() +"/"+ file.getOriginalFilename().replaceAll("[^a-zA-Z0-9\\.]", "_"));
        System.out.println("---------------" + file.getOriginalFilename());
        Files.write(path, bytes);
        return path.toAbsolutePath().toString();
    }
	

	public void saveMultipleUploadedFiles(List<MultipartFile> files) throws IOException {

        for (MultipartFile file : files) {

            if (file.isEmpty()) {
                continue; 
            }
           
            byte[] bytes = file.getBytes();
           
            Path path = Paths.get(getUploadedImageFolderPath() +"/"+ file.getOriginalFilename());
            System.out.println("---------------" + file.getOriginalFilename());
            Files.write(path, bytes);         
            
        }

    }
	@Override
	public Resource loadAsResource(String filename) {
		try {
			Path file = load(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new StorageFileNotFoundException("Could not read file: " + filename);

			}
		} catch (MalformedURLException e) {
			throw new StorageFileNotFoundException("Could not read file: " + filename, e);
		}
	}

	@Override
	public void deleteAll() {
		FileSystemUtils.deleteRecursively(images.toFile());
	}

	@Override
	public void init() {
		try {
			Files.createDirectories(images);
			Files.createDirectories(uploaded_images);
			Files.createDirectories(processed_images);
			//Files.createDirectories(templates);
			/*System.out.println(this.uploaded_images.toString());
			System.out.println(this.uploaded_images.toAbsolutePath().toString());*/
			
		} catch (IOException e) {
			throw new StorageException("Could not initialize storage", e);
		}
	}

	@Override
	public Stream<Path> loadAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Path load(String filename) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String store(MultipartFile file) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/*private void storeData() {
		String file = config.toString() + "/qumec_table_objects.json";
        System.out.println("Writing to file: " + file);
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(file))) {
            writer.write("{\n" + 
            		"    \"camera_index\" : 1,\n" + 
            		"    \"center_position\" : [ 320, 300 ],\n" + 
            		"    \"reference_position\" : [ 320, 450 ],\n" + 
            		"    \"udp_port\" : 8149,\n" + 
            		"    \"reference_line_allowance\" : 4,\n" + 
            		"    \"show_video_output\" : false,\n" + 
            		"    \"video_frame_sleep\" : 5000,\n" + 
            		"    \"video_output_wait_duration\" : 20,\n" + 
            		"    \"classification\" : false\n" + 
            		"}");
        } catch (IOException e) {
			e.printStackTrace();
		}
	}*/
}