package com.assgnmnt.app.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import com.assgnmnt.app.model.CanonicalFileContent;
import com.assgnmnt.app.model.Dish;

/**
 * This class is a utility class for file operations
 * 
 * @author Arshal
 *
 */
public class FileReader {

	/**
	 * Reads a file from the given path {@link String}, and converts it into an
	 * JAVA understandable format i.e. a class {@link CanonicalFileContent}
	 * 
	 * @param filePath
	 * @return {@link CanonicalFileContent} canonicalFileContent
	 * @throws Exception
	 */
	public static CanonicalFileContent readContent(String filePath) throws Exception {
		CanonicalFileContent canonicalFileContent = new CanonicalFileContent();
		List<Dish> dishes = new LinkedList<>();
		canonicalFileContent.setDishes((LinkedList<Dish>) dishes);

		List<String> lines = readLines(filePath);
		String[] contentMetaData = lines.get(0).split(" ");
		if (contentMetaData.length != 2) {
			throw new Exception("timeLimit/dishCount not present");
		}
		canonicalFileContent.setTimeLimit(Integer.valueOf(contentMetaData[0]));
		canonicalFileContent.setDishCount(Integer.valueOf(contentMetaData[1]));

		for (int i = 1; i < lines.size(); i++) {
			String[] splitLine = lines.get(i).split(" ");
			if (splitLine.length != 2) {
				throw new Exception("dish consumeTime/satisfaction not present");
			}
			Integer satisfaction = Integer.valueOf(splitLine[0]);
			Integer consumeTime = Integer.valueOf(splitLine[1]);
			dishes.add(new Dish("Dish" + i, consumeTime, satisfaction));
		}

		return canonicalFileContent;
	}

	/**
	 * Reads a file from the provided file path line by line
	 * 
	 * @param filePath
	 * @return {@link List}<{@link String}> lines
	 * @throws IOException
	 */
	public static List<String> readLines(String filePath) throws IOException {
		List<String> lines = new ArrayList<String>();
		try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
			stream.forEach(lines::add);
		}
		return lines;
	}

	/**
	 * Gives absolute file path for the given file name, if it is present in
	 * classpath
	 * 
	 * @param fileName
	 * @return {@link String} filePath
	 * @throws FileNotFoundException
	 */
	@SuppressWarnings("unused")
	public static String getAbsoluteFilePath(String fileName) throws FileNotFoundException {
		ClassLoader classLoader = FileReader.class.getClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());
		if (file != null)
			return file.getAbsolutePath();
		else {
			throw new FileNotFoundException(fileName + " :: File Not Found In Classpath");
		}
	}
}
