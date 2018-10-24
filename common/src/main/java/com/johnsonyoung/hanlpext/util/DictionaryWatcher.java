package com.hualongdata.hanlpext.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.List;
import java.util.Properties;

import com.hankcs.hanlp.dictionary.CustomDictionary;


public class DictionaryWatcher {

	public void customDictWatcher() throws IOException {
		Properties properties = new Properties();
//		InputStream is = new BufferedInputStream(new FileInputStream(new File(System.getProperty("user.dir") +File.separator + "hanlp.properties")));
		InputStream is = DictionaryWatcher.class.getClassLoader().getResourceAsStream("hanlp.properties");
        properties.load(is);
		String root = properties.getProperty("root", "").replaceAll("\\\\", "/");;
//		ClassLoader classLoader = this.getClass().getClassLoader();
//		URL resource = classLoader.getResource("data/dictionary/custom/");
		String path = root + "data/dictionary/custom/";
		WatchService watchService = FileSystems.getDefault().newWatchService();
		Path p = Paths.get(path);
		p.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY, StandardWatchEventKinds.ENTRY_DELETE,
				StandardWatchEventKinds.ENTRY_CREATE);

		Thread thread = new Thread(() -> {
			try {
				while (true) {
					WatchKey watchKey = watchService.take();
					List<WatchEvent<?>> watchEvents = watchKey.pollEvents();
					for (WatchEvent<?> event : watchEvents) {
						if(!event.context().toString().contains("CustomDictionary.txt.bin")) {
							CustomDictionary.reload();
						}
					}
					watchKey.reset();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		thread.setDaemon(false);
		thread.start();

		// 增加jvm关闭的钩子来关闭监听
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			try {
				watchService.close();
			} catch (Exception e) {
			}
		}));
	}
}
